package com.qf.serviceImpl;

import com.qf.pojo.Course;
import com.qf.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author FJM
 * @create 2019/11/11
 * @Time 16:24
 */
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseService courseService;
    @Override
    public int addCourse(String course_name) {
        return courseService.addCourse(course_name);
    }

    @Override
    public int deleteCourse(int course_id) {
        return courseService.deleteCourse(course_id);
    }

    @Override
    public int updateCourse(Course course) {
        return courseService.updateCourse(course);
    }

    @Override
    public List<Course> getCourseList() {
        return courseService.getCourseList();
    }

    @Override
    public Course getCourseById(int course_id) {
        return courseService.getCourseById(course_id);
    }
}
