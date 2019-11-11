package com.qf.mapper;

import com.qf.pojo.Course;

import java.util.List;

/**
 * @author FJM
 * @create 2019/11/11
 * @Time 15:46
 */
public interface CourseMapper {
    //增加课程
    public int addCourse(Course course);
    //减少
    public int deleteCourse(int course_id);
    //修改
    public int updateCourse(Course course);
    //查询课程
    public List<Course> getCourseList();
    //通过course_id去查询某一课程
    public Course getCourseById(int course_id);
}
