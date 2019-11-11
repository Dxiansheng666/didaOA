package com.qf.serviceImpl;

import com.qf.mapper.*;
import com.qf.pojo.User;
import com.qf.pojo.WeekReport;
import com.qf.service.UserService;
import com.qf.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private WeekReportMapper weekReportMapper;
    @Autowired
    private StudentHolidayMapper studentHolidayMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private ScoreMapper scoreMapper;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private EmployeeHolidayMapper employeeHolidayMapper;

    @Override
    public String getUpwdByUname(String uname) {
        return userMapper.getUpwdByUname(uname);
    }

    @Override
    public String getRoleByUname(String uname) {
        return userMapper.getRolenameByUname(uname);
    }

    @Override
    public User getUserByUname(String uname) {
        return userMapper.getUserByUname(uname);
    }

    @Override
    public int addUser(String uname, String upwd, String rolename) {
        return userMapper.addUser(uname,upwd,rolename);
    }

    @Override
    public int deleteUser(int uid) {
        String rolename=userMapper.getRolenameByUid(uid);
        if(rolename.equals("学生")){
            int sid = studentMapper.getSidByUid(uid);
            weekReportMapper.deleteWeekReportBySid(sid);
            studentHolidayMapper.deleteStudentHolidayByUid(uid);
            scoreMapper.deleteScoreBySid(sid);
            studentMapper.deleteStudent(uid);
            userMapper.deleteUser(uid);
            return 1;
        }else {
            employeeMapper.deleteEmployeeByUid(uid);
            employeeHolidayMapper.deleteEmployeeHolidayByUid(uid);
            userMapper.deleteUser(uid);
            return 2;
        }
    }

    @Override
    public int updatePasswordByAdmin(int uid) {
        String upwd = MD5Utils.md5("123456");
        return userMapper.updatePasswordByAdmin(uid, upwd);
    }

    @Override
    public List<User> getLikeUser(int uid, String uname, String rolename) {
        return userMapper.getLikeUser(uid, "%"+uname+"%", rolename);
    }
}
