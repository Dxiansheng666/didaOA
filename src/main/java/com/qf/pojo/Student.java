package com.qf.pojo;

public class Student {
    private int sid;
    private String sname;
    private String ssex;
    private String sage;
    private int sphone;
    private String semail;
    private User user;
    private Classes classes;



    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSsex() {
        return ssex;
    }

    public void setSsex(String ssex) {
        this.ssex = ssex;
    }

    public String getSage() {
        return sage;
    }

    public void setSage(String sage) {
        this.sage = sage;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getSphone() {
        return sphone;
    }

    public void setSphone(int sphone) {
        this.sphone = sphone;
    }

    public String getSemail() {
        return semail;
    }

    public void setSemail(String semail) {
        this.semail = semail;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sid='" + sid + '\'' +
                ", sname='" + sname + '\'' +
                ", ssex='" + ssex + '\'' +
                ", sage='" + sage + '\'' +
                ", sphone='" + sphone + '\'' +
                ", semail='" + semail + '\'' +
                ", user=" + user +
                ", classes=" + classes +
                '}';
    }

    public Student(String sname, String ssex, String sage, int sphone, String semail, User user, Classes classes) {
        this.sname = sname;
        this.ssex = ssex;
        this.sage = sage;
        this.sphone = sphone;
        this.semail = semail;
        this.user = user;
        this.classes = classes;
    }

    public Student() {
    }
}
