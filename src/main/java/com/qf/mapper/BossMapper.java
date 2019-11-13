package com.qf.mapper;

import com.qf.pojo.Employee;
import com.qf.pojo.Employee_Holiday;
import com.qf.pojo.Score;
import com.qf.pojo.Student_Holiday;

import java.util.List;


public interface BossMapper {
    //查看个人资料
    public Employee getBossInfo(int uid);
    //修改密码
    public int updateUpwd(int uid);
    //查看待审批的学生假条集合
    public List<Student_Holiday> getStudent_HolidayList(List<String> list);
    //查看待审批的老师假条集合
    public List<Employee_Holiday> getEmployee_HolidayList(List<String> list);
    //更改教师假条状态
    public int updateEmployee_Holiday(int hid,int boss);
    //更改学生假条状态
    public int updateStudent_holiday(int hid,int boss);
    //查看成绩
    public List<Score> getClassScoreList();
    //查看学生成绩
    public List<Score> getStudentScoreList();

}
