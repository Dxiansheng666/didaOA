package com.qf.service;

import com.qf.pojo.User;

import java.util.List;

public interface UserService {
    //通过名字得到密码
    public String getUpwdByUname(String uname);
    //通过名字得到角色
    public String getRoleByUname(String uname);
    //通过名字得到对象信息
    public User getUserByUname(String uname);
    //导入用户时使用的方法
    public int addUser(String uname,String upwd,String rolename);
    //删除用户(要将其他表中的数据一起删除)
    public int deleteUser(int uid);
    //重置密码
    public int updatePasswordByAdmin(int uid);
    //搜索用户(名字用的是模糊查询，其他精确查询)
    public List<User> getLikeUser(int uid, String uname, String rolename);
}
