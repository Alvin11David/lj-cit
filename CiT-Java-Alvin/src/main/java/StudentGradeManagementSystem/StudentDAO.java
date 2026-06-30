package StudentGradeManagementSystem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    public void insertStudent(Student student) {
        String sql = "INSERT INTO students (registration_number, name, math_score, english_score, science_score, social_studies_score) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, student.getRegistrationNumber());
            stmt.setString(2, student.getName());
            stmt.setDouble(3, student.getMathScore());
            stmt.setDouble(4, student.getEnglishScore());
            stmt.setDouble(5, student.getScienceScore());
            stmt.setDouble(6, student.getSocialStudiesScore());

            stmt.executeUpdate();
            System.out.println("Student inserted: " + student.getName());
        } catch (SQLException e) {
            System.out.println("Error inserting student: " + e.getMessage());
        }
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT student_id, registration_number, name, math_score, english_score, science_score, social_studies_score FROM students";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet result_set = stmt.executeQuery(sql)) {

            while (result_set.next()) {
                Student s = new Student(
                        result_set.getString("registration_number"),
                        result_set.getString("name"),
                        result_set.getDouble("math_score"),
                        result_set.getDouble("english_score"),
                        result_set.getDouble("science_score"),
                        result_set.getDouble("social_studies_score")
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