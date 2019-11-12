package com.qf.mapper;

import com.qf.pojo.*;

import java.util.List;

public interface TeacherMapper {

    //个人资料管理
    public Employee getTeacherByUid(int uid);
    //修改密码
    public int updateUpwdByUname(User user);

    //周报查看
    public List<WeekReport> getWeekReportList();
    //周报打分，并修改周报状态
    public int updateWeekReport(WeekReport weekReport);

    //查询待审批列表
    public List<Student_Holiday> getStudent_HolidayList(List<String> list);
    //更改假条状态
    public int updateStudent_Holiday(int hid,int teacher_state);

    //成绩查询
    public List<Score> getScoreList();
    //成绩录入
    public int addScore(Score score);

    //查看学生信息
    public List<Student> getStudentList();

    //发起请假
    public int addEmployeeHoliday(Employee_Holiday employee_holiday);
    //根据rolename获取校长的uid
    public User getUidByRoleName();

}