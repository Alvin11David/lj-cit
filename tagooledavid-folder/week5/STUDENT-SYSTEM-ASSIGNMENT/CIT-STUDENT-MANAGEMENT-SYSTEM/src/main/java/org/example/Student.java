package org.example;
import java.util.Map;
import java.util.HashMap;


public class Student {
    private String regNo;
    private String name;
    private Map<Subject, Integer> grades;


    Student(String regNo,String name,Map<Subject,Integer>grades){
        this.regNo = regNo;
        this.name = name;
        this.grades = (grades != null)?grades:new HashMap<>();
    }

    Student(String regNo,String name){
        this(regNo,name,new HashMap<>());
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrades(Subject subject, Integer score){
        grades.put(subject,score);
    }

    public Map<Subject, Integer> getGrades() {
        return grades;
    }

    public String getName() {
        return name;
    }

    public String getRegNo() {
        return regNo;
    }

    public double calculateAverage(Map<Subject, Integer> grades){
        double count =0;
        for (Integer integerValue: grades.values()){
            count += integerValue;
        }
        return count/4;
    }

    public static char  calculateGrade(int score){


            if (score >= 80) {
                return  'A';
            } else if (score >= 75) {
                return'B';
            } else if (score >= 65) {
                return'C';
            } else if (score >= 50) {
                return 'D';
            } else {
                return 'F';
            }



    }

}
