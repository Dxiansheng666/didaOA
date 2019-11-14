package com.qf.serviceImpl;

import com.qf.mapper.RoleMapper;
import com.qf.mapper.UserMapper;
import com.qf.pojo.Role;
import com.qf.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author FJM
 * @create 2019/11/11
 * @Time 16:40
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    public int addRole(String rolename) {
        return roleMapper.addRole(rolename);
    }

    @Override
    public int deleteRole(int roleid) {
        Role role = roleMapper.getRolenameById(roleid);
        if (userMapper.getUserByRolename(role.getRolename())!=null){
           userMapper.updateUserByDeleteRole(role.getRolename());
        }
        return roleMapper.deleteRole(roleid);
    }

    @Override
    public int updateRole(String rolename, int roleid) {
        return roleMapper.updateRole(rolename,roleid);
    }

    @Override
    public List<Role> getRoleList() {
        return roleMapper.getRoleList();
    }

    @Override
    public Role getRolenameById(int roleid) {
        return roleMapper.getRolenameById(roleid);
    }
}
