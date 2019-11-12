package com.qf.mapper;
import com.qf.pojo.Student;
import com.qf.pojo.WeekReport;

import java.util.List;


/**
 * @author FJM
 * @create 2019/11/11
 * @Time 20:07
 */
public interface WeekReportMapper {
    //通过sid删除周报
    public int deleteWeekReportBySid(int sid);
    //获取班级周报
    List<WeekReport> getWeekReportList(List<Student> list);

}
