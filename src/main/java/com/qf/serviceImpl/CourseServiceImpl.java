package com.qf.serviceImpl;

import com.qf.mapper.CourseMapper;
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
    private CourseMapper courseMapper;
    @Override
    public int addCourse(String course_name,String course_duration) {
        return courseMapper.addCourse(course_name,course_duration);
    }

    @Override
    public int deleteCourse(int course_id) {
        return courseMapper.deleteCourse(course_id);
    }

    @Override
    public int updateCourse(Course course) {
        return courseMapper.updateCourse(course);
    }

    @Override
    public List<Course> getCourseList() {
        return courseMapper.getCourseList();
    }

    @Override
    public Course getCourseById(int course_id) {
        return courseMapper.getCourseById(course_id);
    }
}
