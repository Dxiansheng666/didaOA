package com.qf.pojo;

public class Employee_Holiday {
    private int hid;
    private int uid;
    private String start_date;
    private String end_date;
    private String reason;
    private int boss;
    private Employee employee;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getHid() {
        return hid;
    }

    public void setHid(int hid) {
        this.hid = hid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getBoss() {
        return boss;
    }

    public void setBoss(int boss) {
        this.boss = boss;
    }

    @Override
    public String toString() {
        return "Employee_Holiday{" +
                "hid=" + hid +
                ", uid=" + uid +
                ", start_date='" + start_date + '\'' +
                ", end_date='" + end_date + '\'' +
                ", reason='" + reason + '\'' +
                ", boss=" + boss +
                '}';
    }

    public Employee_Holiday(int eid, String start_date, String end_date, String reason, int boss) {
        this.uid = eid;
        this.start_date = start_date;
        this.end_date = end_date;
        this.reason = reason;
        this.boss = boss;
    }

    public Employee_Holiday() {
    }
}
