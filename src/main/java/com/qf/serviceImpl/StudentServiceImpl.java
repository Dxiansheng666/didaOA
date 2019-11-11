package com.qf.serviceImpl;

import com.qf.mapper.StudentMapper;
import com.qf.pojo.*;
import com.qf.service.StudentService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;

    public StudentMapper getStudentMapper() {
        return studentMapper;
    }

    public void setStudentMapper(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    public RuntimeService getRuntimeService() {
        return runtimeService;
    }

    public void setRuntimeService(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    public TaskService getTaskService() {
        return taskService;
    }

    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }


    @Override
    public Student getStudnetInfo(int sid) {
        return studentMapper.getStudnetInfo(sid);
    }

    @Override
    public Student updateStudentInfo(int sid) {
        return studentMapper.updateStudentInfo(sid);
    }

    @Override
    public int updateUpwd(User user) {
        return studentMapper.updateUpwd(user);
    }

    @Override
    public int addWeekReport(int sid) {
        return studentMapper.addWeekReport(sid);
    }

    @Override
    public int deleteWeekReport(int sid) {
        return studentMapper.deleteWeekReport(sid);
    }

    @Override
    public List<WeekReport> getWeekReportList(int sid) {
        return studentMapper.getWeekReportList(sid);
    }

    @Override
    public int addHoliday(Student_Holiday student_holiday) {
        studentMapper.addHoliday(student_holiday);
        List<User> userList = studentMapper.getRoleNameList();

        /**
         * 启动流程
         * startProcessInstanceByKey(String,String,map)
         * 参数说明
         * 第一个string表示流程的key
         * 第二个string表示业务的key，即bussiness key，往往业务表的主键值
         * 第三个map表示流程变量
         */
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("stuName",student_holiday.getUser().getUname());
        for (User u:userList) {
            if (u.getRolename().equals("teacher")) {
                map.put("teacherName", "u.getRolename()");
            }else if (u.getRolename().equals("headmaster")){
                map.put("headmasterName","u.getRolename()");
            }else if (u.getRolename().equals("boss")){
                map.put("bossName","u.getRolename()");
            }
        }
        int days = getDays(student_holiday.getStart_date(),student_holiday.getEnd_date());
        map.put("days",days);
        //发起流程实例
        runtimeService.startProcessInstanceByKey("student_holiday",student_holiday.getHid()+"",map);
        //完成任务
         Task task= taskService.createTaskQuery().taskAssignee(student_holiday.getUser().getUname()).singleResult();
         taskService.complete(task.getId());
        return student_holiday.getHid();
    }

    /**
     * 根据日期获取天数
     * @return
     */
    public int getDays(String startDate,String endDate){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date start=null;
        Date end = null;
        try{
            start=simpleDateFormat.parse(startDate);
            end = simpleDateFormat.parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long daysTime=end.getTime()-start.getTime();
        int days = (int) (daysTime/(24*60*60*1000));
        return days;
    }

    @Override
    public String getTeacherName(Student student) {
        return studentMapper.getTeacherName(student);
    }

    @Override
    public List<WeekReport> getWeekReportScoreList(int sid) {
        return studentMapper.getWeekReportScoreList(sid);
    }

    @Override
    public List<User> getRoleNameList() {
        return studentMapper.getRoleNameList();
    }
}
