package org.example;

public class ConstructorStudent {
    String studentId;
    String fullName;
    String programme;
    int yearOfStudy;
    double gpa;
    //full constructor
    public ConstructorStudent(String studentId, String fullName, String programme, int yearOfStudy, double gpa) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.yearOfStudy = yearOfStudy;
        this.programme = programme;
        this.gpa = gpa;
    }
    //Convienece constructor
    public ConstructorStudent(String studentId, String fullName) {
        this(studentId, fullName, "Backend Engineering", 1, 2.0);
    }
    //printProfile method
    void printProfile() {
        System.out.println("Student Name: "  + fullName + "Student Full Name: " + fullName + "Programme of Study: " + programme  + "GPA: " + gpa );
    }

    public static void main(String[] args) {
        ConstructorStudent student1 = new ConstructorStudent("100", "Alvin David", "Backend Engineer", 1, 4);
        ConstructorStudent student2 = new ConstructorStudent("200", "Otieno Fidel", "Frontend Engineer", 2, 4);
        ConstructorStudent student3 = new ConstructorStudent("300", "Denis Tendo");

        student1.printProfile();
        student2.printProfile();
        student3.printProfile();
    }
}