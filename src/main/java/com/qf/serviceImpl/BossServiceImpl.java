package com.qf.serviceImpl;

import com.qf.mapper.BossMapper;
import com.qf.pojo.Employee;
import com.qf.pojo.Employee_Holiday;
import com.qf.pojo.Student_Holiday;
import com.qf.service.BossService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class BossServiceImpl implements BossService {
    @Autowired
    private BossMapper bossMapper;
    @Autowired
    private TaskService taskService;
    @Autowired
    private RuntimeService runtimeService;
    @Override
    public Employee getBossInfo(int uid) {
        return bossMapper.getBossInfo(uid);
    }

    @Override
    public int updateUpwd(int uid) {
        return bossMapper.updateUpwd(uid);
    }

    @Override
    public List<Student_Holiday> getStudent_HolidayList(String uname) {
        //待办任务集合
        List<Task> list = taskService.createTaskQuery().taskAssignee(uname).list();
        List<String> bussinessKeys=new ArrayList<String>();
        //循环遍历获取bussinessKeys，即请假条的id
        for (Task task:list){
            ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
            bussinessKeys.add(processInstance.getBusinessKey());
        }
       List<Student_Holiday> getStudent_HolidayList = bossMapper.getStudent_HolidayList(bussinessKeys);
        return getStudent_HolidayList;
    }

    @Override
    public List<Employee_Holiday> getEmployee_HolidayList(String uname) {
        List<Task> list = taskService.createTaskQuery().taskAssignee(uname).list();
        List<String> bussinessKeys = new ArrayList<String>();
        for (Task task:list){
            ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
            bussinessKeys.add(processInstance.getBusinessKey());
        }
        List<Employee_Holiday> getEmployee_HolidayList=bossMapper.getEmployee_HolidayList(bussinessKeys);
        return getEmployee_HolidayList;
    }

    @Override
    public int updateEmployee_Holiday(int hid, int boss,String uname) {
        Task task = taskService.createTaskQuery().processInstanceBusinessKey(hid+"").taskAssignee(uname).singleResult();
        taskService.complete(task.getId());
        //审核通过
        return bossMapper.updateEmployee_Holiday(hid,boss);
    }

    @Override
    public int updateStudent_holiday(int hid, int boss,String uname) {
        Task task = taskService.createTaskQuery().processInstanceBusinessKey(hid+"").taskAssignee(uname).singleResult();
        taskService.complete(task.getId());
        //审核通过
        return bossMapper.updateStudent_holiday(hid,boss);
    }
}
