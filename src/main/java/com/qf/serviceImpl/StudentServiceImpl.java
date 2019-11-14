package com.qf.serviceImpl;

import com.qf.mapper.StudentMapper;
import com.qf.pojo.*;
import com.qf.service.StudentService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
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
    public Student getStudnetInfo(int uid) {
        return studentMapper.getStudnetInfo(uid);
    }

    @Override
    public int updateStudentInfo(Student student) {
        return studentMapper.updateStudentInfo(student);
    }

    @Override
    public int updateUpwd(int uid) {
        return studentMapper.updateUpwd(uid);
    }

    @Override
    public int addWeekReport(WeekReport weekReport) {
        return studentMapper.addWeekReport(weekReport);
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
    public int addHoliday(Student_Holiday student_holiday, HttpSession session) {
        User user = (User) session.getAttribute("user");
        int sid = studentMapper.getSidByUid(user.getUid());

        studentMapper.addHoliday(student_holiday);
        System.out.println();
        Classes classes = studentMapper.getClassesByUid(user.getUid());
        List<User> userList = studentMapper.getRoleNameList();
        String ename = studentMapper.getEnameByRolaName();


        /**
         * 启动流程
         * startProcessInstanceByKey(String,String,map)
         * 参数说明
         * 第一个string表示流程的key
         * 第二个string表示业务的key，即bussiness key，往往业务表的主键值
         * 第三个map表示流程变量
         */
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("sname",student_holiday.getStudent().getSname());
            map.put("tname",classes.getClass_teacher());
            map.put("lname",classes.getClass_headteacher());
            map.put("bname",ename);


        int days = getDays(student_holiday.getStart_date(),student_holiday.getEnd_date());
        map.put("days",days);
        //发起流程实例
        runtimeService.startProcessInstanceByKey("StudentHoliday",student_holiday.getHid()+"",map);
        //完成任务
         Task task= taskService.createTaskQuery().taskAssignee(student_holiday.getStudent().getSname()).singleResult();
         taskService.complete(task.getId());
        return student_holiday.getHid();
    }

    @Override
    public Classes getClassBySid(int uid) {
        return null;
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
    public List<WeekReport> getWeekReportScoreList(int uid) {
        return studentMapper.getWeekReportScoreList(uid);
    }

    @Override
    public List<User> getRoleNameList() {
        return null;
    }

    @Override
    public int getSidByUid(int uid) {
        return studentMapper.getSidByUid(uid);
    }

    @Override
    public int addStudent(Student student) {
        return studentMapper.addStudent(student);
    }

    @Override
    public Classes getClassesByUid(int uid) {
        return studentMapper.getClassesByUid(uid);
    }


}
