package org.example;

public class StudentEnrollment {
    String studentId;
    String fullName;
    String programme;
    int yearOfStudy;
    double gpa;

    public StudentEnrollment(String studentId, String fullName, String programme, int yearOfStudy, double gpa) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.programme = programme;
        this.yearOfStudy = yearOfStudy;
        this.gpa = gpa;
    }

    StudentEnrollment(String studentId, String fullName) {
        this(studentId, fullName, "Backend Engineering", 1, 0.0);
    }

    void printProfile() {
        System.out.println("Student ID: "  + studentId + " FullName: " + fullName + " Programme: " + programme + " Year Of Study: " + yearOfStudy + " GPA: " + gpa);
    }

    public static void main(String[] args) {
        StudentEnrollment student1 = new StudentEnrollment("1", "Alvin", "CS", 3, 4.7);
        StudentEnrollment student2 = new StudentEnrollment("2", "David", "SE", 4, 4.4);
        StudentEnrollment student3 = new StudentEnrollment("3", "Denis");

        student1.printProfile();
        student2.printProfile();
        student3.printProfile();
        
    }
}

