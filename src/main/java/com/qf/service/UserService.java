package com.qf.service;

import com.qf.pojo.User;

public interface UserService {
    //登录验证
    public String UserLogin(User user);
    //通过名字得到密码
    public String getUpwdByUname(String uname);
    //通过名字得到角色
    public String getRoleByUname(String uname);
}
