package StudentGradeManagementSystem;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    public void insertStudent(Student student) {
        String sql = "INSERT INTO students (name, math_score, english_score, science_score, sst_score) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, student.getName());
            stmt.setDouble(2, student.getMathScore());
            stmt.setDouble(3, student.getEnglishScore());
            stmt.setDouble(4, student.getScienceScore());
            stmt.setDouble(5, student.getSocialStudiesScore());

            stmt.executeUpdate();
            System.out.println("Student inserted: " + student.getName());
        } catch (SQLException e) {
            System.out.println("Error inserting student: " + e.getMessage());
        }
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT student_id, name, math_score, english_score, science_score, sst_score FROM students";

        try (Connection connection = DatabaseConnection.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet  result_set = stmt.executeQuery(sql)) {

            while (result_set.next()) {
                Student s = new Student(
                        result_set.getString("name"),
                        result_set.getDouble("math_score"),
                        result_set.getDouble("english_score"),
                        result_set.getDouble("science_score"),
                        result_set.getDouble("sst_score")
                );

                s.setStudentId(result_set.getInt("student_id"));

                students.add(s);
            }
        } catch (SQLException e) {
            System.out.println("Error loading students: " + e.getMessage());
        }

        return students;
    }
}
