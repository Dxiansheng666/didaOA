package com.qf.service;

import com.qf.pojo.User;

public interface UserService {
    //通过名字得到密码
    public String getUpwdByUname(String uname);
    //通过名字得到角色
    public String getRoleByUname(String uname);
    //通过名字得到对象信息
    public User getUserByUname(String uname);
}
