package com.qf.mapper;

import com.qf.pojo.Student_Holiday;

import java.util.List;

/**
 * @author FJM
 * @create 2019/11/11
 * @Time 20:12
 */
public interface StudentHolidayMapper {
    //通过uid删除学生请假记录
    public int deleteStudentHolidayByUid(int uid);

    //班主任修改学生请假记录
    public int updateStudentHoliday(int uid, int state);

    //根据查询待审批请假信息
    List<Student_Holiday> getApproveHolidayList(List<String> list);

    //删除学生之前查询是否有请假记录 有的话一起删除
    public List<Student_Holiday> getStudentHolidayByUid(int uid);
}
