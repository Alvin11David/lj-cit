package week4.exercise1.exercise2;

public class Student {
    String studentId;
    String fullName;
    String programme;
    int yearOfStudy;
    double gpa;

    public Student(String studentId, String fullName, String programme, int yearOfStudy, double gpa) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.programme = programme;
        this.yearOfStudy = yearOfStudy;
        this.gpa = gpa;
    }

    public Student(String studentId, String fullName) {
        this(studentId,  fullName, "Backend Engineering", 1, 0.0);
    }

    public void printProfile() {
        System.out.println("The student id is: " + studentId + " the full names are: " + fullName + " the programme is: " + programme + " the year if study is: " + yearOfStudy + "the gpa is: " + gpa);
    }
}
