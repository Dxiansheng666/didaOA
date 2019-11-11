package com.qf.pojo;

public class Teach_classes {
    private int eid;
    private int class_id;

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    @Override
    public String toString() {
        return "Teach_classes{" +
                "eid=" + eid +
                ", class_id=" + class_id +
                '}';
    }

    public Teach_classes(int eid, int class_id) {
        this.eid = eid;
        this.class_id = class_id;
    }

    public Teach_classes() {
    }
}
