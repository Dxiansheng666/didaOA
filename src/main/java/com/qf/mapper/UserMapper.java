package com.qf.mapper;

import com.qf.pojo.Employee;
import com.qf.pojo.Score;
import com.qf.pojo.User;

import java.util.List;

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

    //修改密码
    public int updateUpwdByUid(String upwd,int uid);
    //导入用户时使用的方法
    public int addUser(String uname,String upwd,String rolename);
    //删除用户(要将其他表中的数据一起删除)
    public int deleteUser(int uid);
    //重置密码
    public int updatePasswordByAdmin(int uid,String upwd);
    //搜索用户(名字用的是模糊查询，其他精确查询)
    public List<User> getLikeUser(String sname);
    //通过uid得到角色
    public String getRolenameByUid(int uid);
    //通过拼音名得到对应的uid
    public int getUidByPinyin(String pinyin);


    //单独对角色进行删除时，对被删除角色用户改为待分配
    public int updateUserByDeleteRole(String rolename);
    //分配班级时进行分配老师和班主任需要查询
    public List<String> getTea(String rolename);

    //得到所有用户
    public List<User> getUserList();
    //超级管理员修改员工信息时，通过uid同时将用户表里的角色进行修改
    public int updateRoleByUid(int uid,String rolename);

    //通过角色名字得到是否存在拥有这个角色的用户
    public List<User> getUserByRolename(String rolename);
}
