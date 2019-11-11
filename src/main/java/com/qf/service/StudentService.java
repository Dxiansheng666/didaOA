package com.qf.service;

import com.qf.pojo.Student;
import com.qf.pojo.Student_Holiday;
import com.qf.pojo.User;
import com.qf.pojo.WeekReport;

import java.util.List;

public interface StudentService {
    //查看个人资料
    public Student getStudnetInfo(int sid);
    //修改个人资料
    public Student updateStudentInfo(int sid);
    //修改密码
    public int updateUpwd(User user);
    //新增周报
    public int addWeekReport(int sid);
    //删除周报
    public int deleteWeekReport(int sid);
    //查询周报
    public List<WeekReport> getWeekReportList(int sid);
    //发起请假
    public int addHoliday(Student_Holiday student_holiday);
    //根据学生查找老师
    public String getTeacherName(Student student);
    //查询周报分数
    public List<WeekReport> getWeekReportScoreList(int sid);
    //查找角色集合
    public List<User> getRoleNameList();
}
