package com.qf.pojo;

public class WeekReport {
    private int wid;
    private String title;
    private String create_date;
    private String info;
    private String score;
    private int sid;
    private int state;
    private Student student;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getWid() {
        return wid;
    }

    public void setWid(int wid) {
        this.wid = wid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "WeekReport{" +
                "wid=" + wid +
                ", title='" + title + '\'' +
                ", create_date='" + create_date + '\'' +
                ", info='" + info + '\'' +
                ", score='" + score + '\'' +
                ", sid=" + sid +
                ", state=" + state +
                '}';
    }

    public WeekReport(String title, String create_date, String info, String score, int sid, int state) {
        this.title = title;
        this.create_date = create_date;
        this.info = info;
        this.score = score;
        this.sid = sid;
        this.state = state;
    }

    public WeekReport() {
    }
}
