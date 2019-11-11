package com.qf.pojo;

public class Teach_Course {
    private int eid;
    private int course_id;

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    @Override
    public String toString() {
        return "Teach_Course{" +
                "eid=" + eid +
                ", course_id=" + course_id +
                '}';
    }

    public Teach_Course(int eid, int course_id) {
        this.eid = eid;
        this.course_id = course_id;
    }

    public Teach_Course() {
    }
}
