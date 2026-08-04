package com.example.Week8Java;

import jakarta.persistence.*;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String regNo;

    @Column(nullable = false)
    private String name;

    public Student() {}

    public Student(String regNo, String name) {
        this.regNo = regNo;
        this.name = name;
    }

    public Long getId() { return id; }

    public String getRegNo() { return regNo; }
    public void setRegNo(String regNo) { this.regNo = regNo; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}