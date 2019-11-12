package com.qf.serviceImpl;

import com.qf.mapper.TeacherMapper;
import com.qf.pojo.*;
import com.qf.service.TeacherService;
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
        return teacherMapper.updateUpwdByUname(user);
    }

    @Override
    public List<WeekReport> getWeekReportList() {
        return teacherMapper.getWeekReportList();
    }

    @Override
    public int updateWeekReport(WeekReport weekReport) {
        return teacherMapper.updateWeekReport(weekReport);
    }

    @Override
    public List<Student_Holiday> getStudent_HolidayList(String uname) {
        List<Task> list = taskService.createTaskQuery().taskAssignee(uname).list();
        List<String> bussinessKeys = new ArrayList<>();
        for (Task task:list) {
            ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
            bussinessKeys.add(processInstance.getBusinessKey());
        }
        List<Student_Holiday> student_holidayList = teacherMapper.getStudent_HolidayList(bussinessKeys);
        return student_holidayList;
    }

    @Override
    public int updateStudent_Holiday(int hid, int teacher_state,String uname) {
        Task task = taskService.createTaskQuery().processInstanceBusinessKey(hid+"").taskAssignee(uname).singleResult();
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
    public List<Student> getStudentList() {
        return teacherMapper.getStudentList();
    }

    @Override
    public int addEmployeeHoliday(Employee_Holiday employee_holiday,int uid) {
        //获取自己的名字
        Employee employee2 = teacherMapper.getTeacherByUid(uid);
        //获取校长的名字
        User user = teacherMapper.getUidByRoleName();
        Employee employee = teacherMapper.getTeacherByUid(user.getUid());
        teacherMapper.addEmployeeHoliday(employee_holiday);
        Map<String,Object> map = new HashMap<>();
        map.put("teacher",employee2.getEname());
        map.put("bname",employee.getEname());
        runtimeService.startProcessInstanceByKey("TeacherHoliday",employee_holiday.getHid()+"",map);

        Task task = taskService.createTaskQuery().taskAssignee(employee2.getEname()).singleResult();
        taskService.complete(task.getId());

        return employee_holiday.getHid();
    }
}
