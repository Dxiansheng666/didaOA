package com.qf.serviceImpl;

import com.qf.mapper.TeacherMapper;
import com.qf.pojo.*;
import com.qf.service.TeacherService;
import com.qf.util.MD5Utils;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;

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

    public TeacherMapper getTeacherMapper() {
        return teacherMapper;
    }

    public void setTeacherMapper(TeacherMapper teacherMapper) {
        this.teacherMapper = teacherMapper;
    }

    @Override
    public Employee getTeacherByUid(int uid) {
        return teacherMapper.getTeacherByUid(uid);
    }

    @Override
    public int updateUpwdByUname(User user) {
        return teacherMapper.updateUpwdByUname(user.getUname(), MD5Utils.md5(user.getUpwd()));
    }

    @Override
    public List<WeekReport> getWeekReportList() {
        return teacherMapper.getWeekReportList();
    }

    @Override
    public WeekReport getWeekReport(int wid) {
        return teacherMapper.getWeekReport(wid);
    }

    @Override
    public int updateWeekReport(int score,int state,int wid) {
        return teacherMapper.updateWeekReport(score,state,wid);
    }

    @Override
    public List<Student_Holiday> getStudent_HolidayList(String ename) {
        List<Task> list = taskService.createTaskQuery().taskAssignee(ename).list();
        List<String> bussinessKeys = new ArrayList<>();
        for (Task task:list) {
            ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
            bussinessKeys.add(processInstance.getBusinessKey());
        }
        List<Student_Holiday> student_holidayList = teacherMapper.getStudent_HolidayList(bussinessKeys);
        return student_holidayList;
    }

    @Override
    public int updateStudent_Holiday(int hid, int teacher_state,String ename) {
        Task task = taskService.createTaskQuery().processInstanceBusinessKey(hid+"").taskAssignee(ename).singleResult();
        taskService.complete(task.getId());
        return teacherMapper.updateStudent_Holiday(hid,teacher_state);
    }

    @Override
    public List<Score> getScoreList() {
        return teacherMapper.getScoreList();
    }

    @Override
    public int addScore(Score score) {
        return teacherMapper.addScore(score);
    }

    @Override
    public List<Student> getStudentList(Employee employee) {
        Classes classes = teacherMapper.getClasses(employee.getEname());

        return teacherMapper.getStudentList(classes.getClass_id());
    }

    @Override
    public int addEmployeeHoliday(Employee_Holiday employee_holiday) {
        //获取校长的名字
//        User user = teacherMapper.getUidByRoleName();
//        Employee employee = teacherMapper.getTeacherByUid(user.getUid());
        teacherMapper.addEmployeeHoliday(employee_holiday);
        Map<String,Object> map = new HashMap<>();
        map.put("teacher",employee_holiday.getEmployee().getEname());
        map.put("bname","王");
        runtimeService.startProcessInstanceByKey("TeacherHoliday",employee_holiday.getHid()+"",map);

        Task task = taskService.createTaskQuery().taskAssignee(employee_holiday.getEmployee().getEname()).singleResult();
        taskService.complete(task.getId());

        return employee_holiday.getHid();
    }
    //查询每个阶段学生的成绩
    @Override
    public List<String>  getAvgScore(String ename) {
        Classes classes = teacherMapper.getClasses(ename);
        List<Student> studentList = teacherMapper.getStudentList(classes.getClass_id());
        List<Integer> sids = new ArrayList<>();

        for (Student student:studentList) {
            System.out.println(student);
            sids.add(student.getSid());
        }
        for (int i:sids
             ) {
            System.out.println(i);
        }
        return teacherMapper.getAvgScore(sids);
    }
//    public int getAvgScore(int stage,String ename){
//        Classes classes = teacherMapper.getClasses(ename);
//         return teacherMapper.getAvgScore(classes.getClass_id(),stage);
//    }
    //查询单个学生各阶段分数走势图
    public List<Integer> getScore(int sid){
        return teacherMapper.getScore(sid);
    }
}
