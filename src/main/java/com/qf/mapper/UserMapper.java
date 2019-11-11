package com.qf.mapper;

import com.qf.pojo.User;

/**
 * @author FJM
 * @create 2019/11/11
 * @Time 14:15
 */
public interface UserMapper {

    //通过名字得到密码
    public String getUpwdByUname(String uname);
    //通过名字得到角色
    public String getRolenameByUname(String uname);
    //通过名字得到对象信息
    public User getUserByUname(String uname);
}
