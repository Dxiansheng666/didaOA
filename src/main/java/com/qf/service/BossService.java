package com.qf.service;

import com.qf.pojo.Employee;
import com.qf.pojo.Employee_Holiday;
import com.qf.pojo.Student_Holiday;

import java.util.List;

public interface BossService {
    //查看个人资料
    public Employee getBossInfo(int uid);
    //修改密码
    public int updateUpwd(int uid);
    //查看待审批的学生假条集合
    public List<Student_Holiday> getStudent_HolidayList(String uname);
    //查看待审批的老师假条集合
    public List<Employee_Holiday> getEmployee_HolidayList(String uname);
    //更改教师假条状态
    public int updateEmployee_Holiday(int hid,int boss,String uname);
    //更改学生假条状态
    public int updateStudent_holiday(int hid,int boss,String uname);
}
