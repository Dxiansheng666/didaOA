package com.qf.service;

import com.qf.pojo.*;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface StudentService {
    //查看个人资料
    public Student getStudnetInfo(int uid);
    //修改个人资料
    public int updateStudentInfo(Student student);
    //修改密码
    public int updateUpwd(int uid);
    //新增周报
    public int addWeekReport(WeekReport weekReport);
    //删除周报
    public int deleteWeekReport(int sid);
    //查询周报
    public List<WeekReport> getWeekReportList(int sid);
    //发起请假
    public int addHoliday(Student_Holiday student_holiday, HttpSession session);
    //根据学生查找老师
    public Classes getClassBySid(int uid);
    //查询周报分数
    public List<WeekReport> getWeekReportScoreList(int uid);
    //查找角色集合
    public List<User> getRoleNameList();
    //通过uid查询sid
    public int getSidByUid(int uid);


    int addStudent(Student student);

    //根据uid查找班级
    public Classes getClassesByUid(int uid);


}
