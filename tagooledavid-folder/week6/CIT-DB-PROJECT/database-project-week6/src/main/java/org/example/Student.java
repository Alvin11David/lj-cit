package org.example;

public class Student {
    private int student_id;
    private String regNo;
    private String name;

    Student(String regNo, String name){
        this.regNo = regNo;
        this.name = name;
    }

    Student(int student_id, String regNo, String name){
        this.student_id = student_id;
        this.regNo = regNo;
        this.name = name;
    }

    public int getStudent_id() { return student_id; }

    public void setName(String name){
        this.name = name;
    }

    public void setRegNo(String regNo){
        this.regNo = regNo;
    }

    public String getRegNo() { return regNo; }
    public String getName() { return name; }
}