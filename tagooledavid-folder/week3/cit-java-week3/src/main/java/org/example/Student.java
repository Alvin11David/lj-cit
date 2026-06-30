package org.example;

public class Student {
    String studentId;
    String fullName;
    String programme;
    int yearOfStudy;
    double gpa;

    //full constructor
    public Student(String studentId, String fullName, String programme, int yearOfStudy, double gpa) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.yearOfStudy = yearOfStudy;
        this.programme = programme;
        this.gpa = gpa;
    }

    //constructor2
    public Student(String studentId, String fullName) {
        this(studentId, fullName, "Backend Engineering", 1, 0.0);
    }


    void printProfile() {
        System.out.println("Student Details:[ Name: "  + fullName + "Student Full Name: " + fullName + "Programme of Study: " + programme  + "GPA: " + gpa +"]");
    }

    public static void main(String[] args) {
        Student student1 = new Student("100", "Tagoole Mugwa David", "Backend Engineer", 1, 4);
        Student student2 = new Student("200", "George Charles", "Frontend Engineer", 2, 4);
        Student student3 = new Student("300", "Ebiri Janet");

        student1.printProfile();
        student2.printProfile();
        student3.printProfile();
    }
}