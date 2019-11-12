package com.qf.mapper;

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
}
