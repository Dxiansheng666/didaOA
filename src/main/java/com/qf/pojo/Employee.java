package com.qf.pojo;

public class Employee {
    private int eid;
    private String ename;
    private String esex;
    private int eage;
    private int ephone;
    private String eemail;
    private String job;
    private int uid;

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getEsex() {
        return esex;
    }

    public void setEsex(String esex) {
        this.esex = esex;
    }

    public int getEage() {
        return eage;
    }

    public void setEage(int eage) {
        this.eage = eage;
    }

    public int getEphone() {
        return ephone;
    }

    public void setEphone(int ephone) {
        this.ephone = ephone;
    }

    public String getEemail() {
        return eemail;
    }

    public void setEemail(String eemail) {
        this.eemail = eemail;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "eid=" + eid +
                ", ename='" + ename + '\'' +
                ", esex='" + esex + '\'' +
                ", eage=" + eage +
                ", ephone=" + ephone +
                ", eemail='" + eemail + '\'' +
                ", job='" + job + '\'' +
                ", uid=" + uid +
                '}';
    }

    public Employee(String ename, String esex, int eage, int ephone, String eemail, String job, int uid) {
        this.ename = ename;
        this.esex = esex;
        this.eage = eage;
        this.ephone = ephone;
        this.eemail = eemail;
        this.job = job;
        this.uid = uid;
    }

    public Employee() {
    }
}
