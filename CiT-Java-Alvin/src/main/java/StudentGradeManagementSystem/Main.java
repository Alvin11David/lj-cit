package StudentGradeManagementSystem;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        StudentDAO dao = new StudentDAO();

        dao.insertStudent(new Student( "Alvin", 78.9, 89.4, 90.2, 99.12));
        dao.insertStudent(new Student( "David", 67.3, 75.8, 88.3, 84.4));
        dao.insertStudent(new Student( "Waluube", 89.3, 90.3, 90.2, 87.3));
        dao.insertStudent(new Student(  "Falijala",89.9, 99.3, 76.2, 98.2));

        System.out.println("===== ALL STUDENTS ====");
        List<Student> students = dao.getAllStudents();

        for (Student student : students) {
            System.out.println(student);
        }


        if (!students.isEmpty()) {
            Student first = students.get(0);
            System.out.println("\n" + first.getName() + "'s average score: " + first.getAverageScore());
        }
    }
}
