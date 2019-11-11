package com.qf.pojo;

public class Score {
    private int score_id;
    private int sid;
    private int course_id;
    private String score;
    private String stage;

    public int getScore_id() {
        return score_id;
    }

    public void setScore_id(int score_id) {
        this.score_id = score_id;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    @Override
    public String toString() {
        return "Score{" +
                "score_id=" + score_id +
                ", sid=" + sid +
                ", course_id=" + course_id +
                ", score='" + score + '\'' +
                ", stage='" + stage + '\'' +
                '}';
    }

    public Score(int sid, int course_id, String score, String stage) {
        this.sid = sid;
        this.course_id = course_id;
        this.score = score;
        this.stage = stage;
    }

    public Score() {
    }
}
