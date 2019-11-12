package com.qf.serviceImpl;

import com.qf.mapper.*;
import com.qf.pojo.*;
import com.qf.service.HeadMasterService;
import com.qf.util.MD5Utils;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class HeadMasterServiceImpl implements HeadMasterService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private WeekReportMapper weekReportMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private StudentHolidayMapper studentHolidayMapper;
    @Autowired
    private EmployeeHolidayMapper employeeHolidayMapper;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    //获取个人资料
    @Override
    public Employee getHeadMasterByUid(int uid) {
        return employeeMapper.getHeadMasterByUid(uid);
    }
    //修改密码
    @Override
    public int updateUpwdByUid(User user) {
        return userMapper.updateUpwdByUid(MD5Utils.md5(user.getUpwd()),user.getUid());
    }
    //获取学生周报信息
    @Override
    public List<WeekReport> getWeekReportListByUid(int uid) {
        return weekReportMapper.getWeekReportListByUid(uid);
    }
    //获取班级学生信息
    @Override
    public List<Student> getStudentListByClassId(int class_id) {
        return null;
    }
    //班主任发起请假
    @Override
    public int addHeadMasterHoliday(Employee_Holiday employee_holiday) {
        int i = employeeHolidayMapper.addHoliday(employee_holiday);
        /**
         * 启动流程
         * startProcessInstanceByKey(String,String,map)
         * 参数说明：
         * 第一个string表示流程的key
         * 第二个string表示业务的key，即bussiness key，往往业务表的主键值
         * 第3个map表示流程变量
         */
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("tname",employee_holiday.getEmployee().getEname());
        map.put("bname","王");
        int days = getDays(employee_holiday.getStart_date(),employee_holiday.getEnd_date());
        map.put("days",days);
        //发起流程实例
        runtimeService.startProcessInstanceByKey("employee_holiday",employee_holiday.getHid()+"",map);
        //完成任务
        Task task = taskService.createTaskQuery().taskAssignee(employee_holiday.getEmployee().getEname()).singleResult();
        taskService.complete(task.getId());
        return employee_holiday.getHid();
    }
    //查看班级成绩
    @Override
    public List<Score> getScoreListByClassId(List<Student> studentList) {
        return null;
    }
    //查看单个学生信息
    @Override
    public List<Score> getScoreBySid(int sid) {
        return null;
    }
    //审批学生请假
    @Override
    public int updateStudentHoliday(int hid,int state,String ename) {
        Task task = taskService.createTaskQuery().processInstanceBusinessKey(hid + "").taskAssignee(ename).singleResult();
        taskService.complete(task.getId());
        return studentHolidayMapper.updateStudentHoliday(hid,state);
    }
    //获取待审批假条
    @Override
    public List<Student_Holiday> getStudentHolidayByUid(int uid) {
        Employee headMasterByUid = employeeMapper.getHeadMasterByUid(uid);
        List<Task> list = taskService.createTaskQuery().taskAssignee(headMasterByUid.getEname()).list();
        List<String> bussinessKeys = new ArrayList<String>();
        for (Task task : list) {
            ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                    .processInstanceId(task.getProcessInstanceId()).singleResult();
            bussinessKeys.add(processInstance.getBusinessKey());
        }
        List<Student_Holiday> approveHolidayList = studentHolidayMapper.getApproveHolidayList(bussinessKeys);
        return approveHolidayList;
    }
    /**
     * 根据日期获取天数
     * @param startDate
     * @param endDate
     * @return
     */
    private int getDays(String startDate, String endDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date start = null;
        Date end = null;
        try {
            start = simpleDateFormat.parse(startDate);
            end = simpleDateFormat.parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long daysTime = end.getTime() - start.getTime();
        int days = (int)daysTime/(24*60*60*1000);
        return days;
    }
}
