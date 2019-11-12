package com.qf.mapper;

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
    public List<User> getLikeUser(int uid,String uname,String rolename);
    //通过uid得到角色
    public String getRolenameByUid(int uid);

}
