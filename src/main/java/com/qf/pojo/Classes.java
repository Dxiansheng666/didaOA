package com.qf.pojo;

public class Classes {
    private int class_id;
    private String class_name;
    private String class_course;
    private String class_teacher;
    private String class_headteacher;
    private int peopleNum;

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getClass_course() {
        return class_course;
    }

    public void setClass_course(String class_course) {
        this.class_course = class_course;
    }

    public String getClass_teacher() {
        return class_teacher;
    }

    public void setClass_teacher(String class_teacher) {
        this.class_teacher = class_teacher;
    }

    public String getClass_headteacher() {
        return class_headteacher;
    }

    public void setClass_headteacher(String class_headteacher) {
        this.class_headteacher = class_headteacher;
    }

    public int getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(int peopleNum) {
        this.peopleNum = peopleNum;
    }

    @Override
    public String toString() {
        return "Classes{" +
                "class_id=" + class_id +
                ", class_name='" + class_name + '\'' +
                ", class_course='" + class_course + '\'' +
                ", class_teacher='" + class_teacher + '\'' +
                ", class_headteacher='" + class_headteacher + '\'' +
                ", peopleNum=" + peopleNum +
                '}';
    }

    public Classes(String class_name, String class_course, String class_teacher, String class_headteacher, int peopleNum) {
        this.class_name = class_name;
        this.class_course = class_course;
        this.class_teacher = class_teacher;
        this.class_headteacher = class_headteacher;
        this.peopleNum = peopleNum;
    }

    public Classes() {
    }
}
