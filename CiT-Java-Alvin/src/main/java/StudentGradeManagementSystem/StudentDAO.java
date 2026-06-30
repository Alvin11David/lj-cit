package StudentGradeManagementSystem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentDAO {

    public boolean insertStudent(Student student) {
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
            return true;
        } catch (SQLException e) {
            System.out.println("Error inserting student: " + e.getMessage());
            return false;
        }
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT student_id, registration_number, name, math_score, english_score, science_score, social_studies_score FROM students ORDER BY student_id";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet result_set = stmt.executeQuery(sql)) {

            while (result_set.next()) {
                students.add(mapRow(result_set));
            }
        } catch (SQLException e) {
            System.out.println("Error loading students: " + e.getMessage());
        }

        return students;
    }

    public Optional<Student> findByRegistrationNumber(String registrationNumber) {
        String sql = "SELECT student_id, registration_number, name, math_score, english_score, science_score, social_studies_score " +
                "FROM students WHERE registration_number = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, registrationNumber);

            try (ResultSet result_set = stmt.executeQuery()) {
                if (result_set.next()) {
                    return Optional.of(mapRow(result_set));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error looking up student: " + e.getMessage());
        }

        return Optional.empty();
    }

    public boolean updateScores(String registrationNumber, double math, double english, double science, double socialStudies) {
        String sql = "UPDATE students SET math_score = ?, english_score = ?, science_score = ?, social_studies_score = ? " +
                "WHERE registration_number = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, math);
            stmt.setDouble(2, english);
            stmt.setDouble(3, science);
            stmt.setDouble(4, socialStudies);
            stmt.setString(5, registrationNumber);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error updating scores: " + e.getMessage());
            return false;
        }
    }

    public boolean existsByRegistrationNumber(String registrationNumber) {
        String sql = "SELECT 1 FROM students WHERE registration_number = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, registrationNumber);
            try (ResultSet result_set = stmt.executeQuery()) {
                return result_set.next();
            }
        } catch (SQLException e) {
            System.out.println("Error checking student existence: " + e.getMessage());
            return false;
        }
    }

    private Student mapRow(ResultSet result_set) throws SQLException {
        Student s = new Student(
                result_set.getString("registration_number"),
                result_set.getString("name"),
                result_set.getDouble("math_score"),
                result_set.getDouble("english_score"),
                result_set.getDouble("science_score"),
                result_set.getDouble("social_studies_score")
        );
        s.setStudentId(result_set.getInt("student_id"));
        return s;
    }
}