package com.qf.serviceImpl;

import com.qf.mapper.RoleMapper;
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
    @Override
    public int addRole(String rolename) {
        return roleMapper.addRole(rolename);
    }

    @Override
    public int deleteRole(int roleid) {
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
}
