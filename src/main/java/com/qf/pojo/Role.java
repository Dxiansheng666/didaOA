package com.qf.pojo;

/**
 * @author FJM
 * @create 2019/11/11
 * @Time 16:37
 */
public class Role {
    private int roleid;
    private String rolename;

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleid=" + roleid +
                ", rolename='" + rolename + '\'' +
                '}';
    }

}
