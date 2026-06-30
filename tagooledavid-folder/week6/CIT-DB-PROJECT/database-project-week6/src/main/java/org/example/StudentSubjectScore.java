package org.example;

public class StudentSubjectScore {
    private Integer student_id;
    private Integer subject_id;
    private Integer score;


    StudentSubjectScore(Integer student_id,Integer subject_id,Integer score){
        this.student_id = student_id;
        this.subject_id=subject_id;
        this.score = score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }

    public void setSubject_id(Integer subject_id) {
        this.subject_id = subject_id;
    }

    public Integer getStudent_id() {
        return student_id;
    }

    public Integer getSubject_id() {
        return subject_id;
    }

    public Integer getScore() {
        return score;
    }
}
