package com.qf.service;

import com.qf.pojo.*;

import java.util.List;

public interface HeadMasterService {
    /**
     * 查看个人资料
     */
    public Employee getHeadMasterByUid(int uid);

    /**
     * 修改密码
     */
    public int updateUpwdByUid(User user);

    /**
     * 查看周报
     */
    public List<WeekReport> getWeekReportListBySid(int sid);

    /**
     * 查看班级学生信息
     */
    public List<Student> getStudentListByClassId(int class_id);

    /**
     * 个人发起请假
     */
    public int addHeadMasterHoliday(Employee_Holiday employee_holiday);

    /**
     * 查看班级成绩
     */
    public List<Score> getScoreListByClassId(List<Student> studentList);

    /**
     * 查看单个学生成绩
     */
    public List<Score> getScoreBySid(int sid);

    /**
     * 审批学生请假
     */
    public int updateStudentHoliday();
}
