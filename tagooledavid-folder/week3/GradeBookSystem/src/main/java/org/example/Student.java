package org.example;

import java.util.HashMap;
import java.util.Map;


public class Student {
    private String id;
    private String email;
    private String name;
    private Map<Subject, Integer> grades = new HashMap<>();

    // Main constructur
    Student(String id, String email, String name, Map<Subject, Integer> grades) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.grades = (grades != null) ? grades : new HashMap<>();
    }

    // Second Constructor
    Student(String id, String email, String name) {
        this(id, email, name, new HashMap<>());
    }

    // setters
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGrades(Subject subject, int score) {
        grades.put(subject, score);
    }

    // setters
    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public Map<Subject, Integer> getGrades() {
        return grades;
    }

    public static void main(String[] args){
        Student newStudent = new Student("R1-2","tagooledavid@gmail.com","Tags Davit");


        System.out.println(""+newStudent.getGrades());

    }

}
// constructor 1
