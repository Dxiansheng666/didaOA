package com.qf.serviceImpl;

import com.qf.mapper.UserMapper;
import com.qf.pojo.User;
import com.qf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;


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
}
