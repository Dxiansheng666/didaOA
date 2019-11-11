package com.qf.pojo;

public class Student_Holiday {
    private int hid;
    private int uid;
    private String start_date;
    private String end_date;
    private String reason;
    private int teacher_state;
    private int headmaster;
    private int boss;

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

    public int getTeacher_state() {
        return teacher_state;
    }

    public void setTeacher_state(int teacher_state) {
        this.teacher_state = teacher_state;
    }

    public int getHeadmaster() {
        return headmaster;
    }

    public void setHeadmaster(int headmaster) {
        this.headmaster = headmaster;
    }

    public int getBoss() {
        return boss;
    }

    public void setBoss(int boss) {
        this.boss = boss;
    }

    @Override
    public String toString() {
        return "Student_Holiday{" +
                "hid=" + hid +
                ", uid=" + uid +
                ", start_date='" + start_date + '\'' +
                ", end_date='" + end_date + '\'' +
                ", reason='" + reason + '\'' +
                ", teacher_state=" + teacher_state +
                ", headmaster=" + headmaster +
                ", boss=" + boss +
                '}';
    }

    public Student_Holiday(int uid, String start_date, String end_date, String reason, int teacher_state, int headmaster, int boss) {
        this.uid = uid;
        this.start_date = start_date;
        this.end_date = end_date;
        this.reason = reason;
        this.teacher_state = teacher_state;
        this.headmaster = headmaster;
        this.boss = boss;
    }

    public Student_Holiday() {
    }
}
