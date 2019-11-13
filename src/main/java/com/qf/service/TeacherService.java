package com.qf.service;

import com.qf.pojo.*;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface TeacherService {
    //个人资料管理
    public Employee getTeacherByUid(int uid);
    //修改密码
    public int updateUpwdByUname(User user);

    //周报查看
    public List<WeekReport> getWeekReportList();
    //根据wid查询单个信息
    public  WeekReport getWeekReport(int wid);
    //周报打分，并修改周报状态
    public int updateWeekReport(int score,int state,int wid);

    //查询待审批列表
    public List<Student_Holiday> getStudent_HolidayList(String ename);
    //更改假条状态
    public int updateStudent_Holiday(int hid,int teacher_state,String ename);

    //成绩查询
    public List<Score> getScoreList();
    //成绩录入
    public int addScore(Score score);

    //查看本班学生信息
    public List<Student> getStudentList(Employee employee);

    //发起请假
    public int addEmployeeHoliday(Employee_Holiday employee_holiday);

//    //查询每个阶段学生的成绩
//    public List<Score> getScoreBySid(int stage,String ename);
    public int getAvgScore(int stage,String ename);
    //查询单个学生各阶段分数走势图
    public int getScore(int sid,int stage);
}
