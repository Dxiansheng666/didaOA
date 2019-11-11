package com.qf.pojo;

public class Employee_Holiday {
    private int hid;
    private int eid;
    private String start_date;
    private String end_date;
    private String reason;
    private int boss;

    public int getHid() {
        return hid;
    }

    public void setHid(int hid) {
        this.hid = hid;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
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
                ", eid=" + eid +
                ", start_date='" + start_date + '\'' +
                ", end_date='" + end_date + '\'' +
                ", reason='" + reason + '\'' +
                ", boss=" + boss +
                '}';
    }

    public Employee_Holiday(int eid, String start_date, String end_date, String reason, int boss) {
        this.eid = eid;
        this.start_date = start_date;
        this.end_date = end_date;
        this.reason = reason;
        this.boss = boss;
    }

    public Employee_Holiday() {
    }
}
