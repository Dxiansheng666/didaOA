package com.qf.pojo;

public class Score {
    private int score_id;
    private Student student;
    private Course course;
    private String score;
    private String stage;

    public Score(int score_id, Student student, Course course, String score, String stage) {
        this.score_id = score_id;
        this.student = student;
        this.course = course;
        this.score = score;
        this.stage = stage;
    }

    public Score(int score_id, String score, String stage) {
        this.score_id = score_id;
        this.score = score;
        this.stage = stage;
    }

    public Score() {
    }

    public int getScore_id() {
        return score_id;
    }

    public void setScore_id(int score_id) {
        this.score_id = score_id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
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
                ", student=" + student +
                ", course=" + course +
                ", score='" + score + '\'' +
                ", stage='" + stage + '\'' +
                '}';
    }


}
