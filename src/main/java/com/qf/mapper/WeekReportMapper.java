package com.qf.mapper;

import com.qf.pojo.Student;
import com.qf.pojo.WeekReport;

import java.util.List;

public interface WeekReportMapper {
    //获取班级周报
    List<WeekReport> getWeekReportList(List<Student> studentList);

}
