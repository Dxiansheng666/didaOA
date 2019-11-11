package com.qf.pojo;

public class Role_permission {
    private int pid;
    private String permission;
    private String rolename;

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    @Override
    public String toString() {
        return "Role_permission{" +
                "pid=" + pid +
                ", permission='" + permission + '\'' +
                ", rolename='" + rolename + '\'' +
                '}';
    }

    public Role_permission(String permission, String rolename) {
        this.permission = permission;
        this.rolename = rolename;
    }

    public Role_permission() {
    }
}
