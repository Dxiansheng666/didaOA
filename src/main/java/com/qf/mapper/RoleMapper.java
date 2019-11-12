package com.qf.mapper;

import com.qf.pojo.Role;

import java.util.List;

/**
 * @author FJM
 * @create 2019/11/11
 * @Time 16:28
 * 对角色进行管理
 */
public interface RoleMapper {
    //增加角色
    public int addRole(String rolename);
    //删除角色
    public int deleteRole(int roleid);
    //修改角色
    public int updateRole(String rolename,int roleid);
    //查询角色
    public List<Role> getRoleList();
    //通过角色id得到角色信息
    public Role getRolenameById(int roleid);
}
