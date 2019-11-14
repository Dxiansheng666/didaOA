package com.qf.mapper;
import com.qf.pojo.*;

import java.util.List;

/**
 * @author FJM
 * @create 2019/11/11
 * @Time 19:36
 */
public interface StudentMapper {
    //通过uid查询sid，用于成绩表的删除
    public int getSidByUid(int uid);
    //通过uid删除学生
    public int deleteStudent(int uid);


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
    public List<WeekReport> getWeekReportList(int uid);
    //发起请假
    public int addHoliday(Student_Holiday student_holiday);

    //查询周报分数
    public List<WeekReport> getWeekReportScoreList(int uid);
    //查找角色集合
    public List<User> getRoleNameList();
//根据uid查找班级
    public Classes getClassesByUid(int uid);
    //增加学生
    public int addStudent(Student student);

    public List<Student> getStudentListByClassId(int class_id);
    //根据角色名找到uid；再根据uid找到校长
    public String getEnameByRolaName();

}
