package com.qf.pojo;

public class User {
    private int uid;
    private String uname;
    private String upwd;
    private String rolename;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpwd() {
        return upwd;
    }

    public void setUpwd(String upwd) {
        this.upwd = upwd;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", uname='" + uname + '\'' +
                ", upwd='" + upwd + '\'' +
                ", rolename='" + rolename + '\'' +
                '}';
    }

    public User(String uname, String upwd, String rolename) {
        this.uname = uname;
        this.upwd = upwd;
        this.rolename = rolename;
    }

    public User() {
    }
}
