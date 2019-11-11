package com.qf.serviceImpl;

import com.qf.mapper.EmployeeMapper;
import com.qf.mapper.UserMapper;
import com.qf.pojo.*;
import com.qf.service.HeadMasterService;
import com.qf.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HeadMasterServiceImpl implements HeadMasterService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private UserMapper userMapper;
    //获取个人资料
    @Override
    public Employee getHeadMasterByUid(int uid) {
        return employeeMapper.getHeadMasterByUid(uid);
    }
    //修改密码
    @Override
    public int updateUpwdByUid(User user) {
        return userMapper.updateUpwdByUid(MD5Utils.md5(user.getUpwd()),user.getUid());
    }
    //获取班级周报信息
    @Override
    public List<WeekReport> getWeekReportListByUid(int uid) {
        return null;
    }
    //获取班级学生信息
    @Override
    public List<Student> getStudentListByClassId(int class_id) {
        return null;
    }
    //班主任发起请假
    @Override
    public int addHeadMasterHoliday(Employee_Holiday employee_holiday) {
        return 0;
    }
    //查看班级成绩
    @Override
    public List<Score> getScoreListByClassId(List<Student> studentList) {
        return null;
    }
    //查看单个学生信息
    @Override
    public List<Score> getScoreBySid(int sid) {
        return null;
    }
    //审批学生请假
    @Override
    public int updateStudentHoliday() {
        return 0;
    }
}
