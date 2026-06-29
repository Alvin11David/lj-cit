package org.example;

public class Student {
    private String regNo;
    private String name;

    Student(String regNo, String name){
        this.regNo = regNo;
        this.name = name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setRegNo(String regNo){
        this.regNo = regNo;
    }

    public String getRegNo() { return regNo; }
    public String getName() { return name; }
}