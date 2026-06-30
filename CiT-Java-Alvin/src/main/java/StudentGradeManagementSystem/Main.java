package StudentGradeManagementSystem;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        StudentDAO dao = new StudentDAO();

        // Comment this block out after the first run, then re-run to confirm
        // the same students reappear from the database (proves persistence).
        try {
            dao.insertStudent(new Student("REG001", "Alvin", 78.9, 89.4, 90.2, 99.12));
            dao.insertStudent(new Student("REG002", "David", 67.3, 75.8, 88.3, 84.4));
            dao.insertStudent(new Student("REG003", "Waluube", 89.3, 90.3, 90.2, 87.3));
            dao.insertStudent(new Student("REG004", "Falijala", 89.9, 99.3, 76.2, 98.2));
        } catch (IllegalArgumentException e) {
            System.out.println("Could not add student: " + e.getMessage());
        }

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