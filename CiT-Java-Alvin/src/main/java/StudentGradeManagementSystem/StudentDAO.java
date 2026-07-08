package StudentGradeManagementSystem;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class StudentDAO {

    public boolean insertStudent(Student student) {
        String insertStudentSql = "INSERT INTO students (registration_number, name) VALUES (?, ?) RETURNING student_id";

        try (Connection connection = DatabaseConnection.getConnection()) {
            connection.setAutoCommit(false);

            int studentId;
            try (PreparedStatement stmt = connection.prepareStatement(insertStudentSql)) {
                stmt.setString(1, student.getRegistrationNumber());
                stmt.setString(2, student.getName());
                try (ResultSet rs = stmt.executeQuery()) {
                    rs.next();
                    studentId = rs.getInt("student_id");
                }
            }
            student.setStudentId(studentId);

            upsertScores(connection, studentId, student.getScores());

            connection.commit();
            System.out.println("Student inserted: " + student.getName());
            return true;
        } catch (SQLException e) {
            System.out.println("Error inserting student: " + e.getMessage());
            return false;
        }
    }

    public List<Student> getAllStudents() {
        Map<Integer, Student> studentMap = new LinkedHashMap<>();
        String sql = """
                SELECT s.student_id, s.registration_number, s.name,
                       sub.subject_name, sc.score
                FROM students s
                LEFT JOIN scores sc ON sc.student_id = s.student_id
                LEFT JOIN subjects sub ON sub.subject_id = sc.subject_id
                ORDER BY s.student_id
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                mapRowInto(studentMap, rs);
            }
        } catch (SQLException e) {
            System.out.println("Error loading students: " + e.getMessage());
        }

        return new ArrayList<>(studentMap.values());
    }

    public Optional<Student> findByRegistrationNumber(String registrationNumber) {
        Map<Integer, Student> studentMap = new LinkedHashMap<>();
        String sql = """
                SELECT s.student_id, s.registration_number, s.name,
                       sub.subject_name, sc.score
                FROM students s
                LEFT JOIN scores sc ON sc.student_id = s.student_id
                LEFT JOIN subjects sub ON sub.subject_id = sc.subject_id
                WHERE s.registration_number = ?
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, registrationNumber);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    mapRowInto(studentMap, rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error looking up student: " + e.getMessage());
        }

        return studentMap.values().stream().findFirst();
    }

    public boolean updateScores(String registrationNumber, Map<String, Double> newScores) {
        Optional<Student> existing = findByRegistrationNumber(registrationNumber);
        if (existing.isEmpty()) return false;

        int studentId = existing.get().getStudentId();

        try (Connection connection = DatabaseConnection.getConnection()) {
            connection.setAutoCommit(false);
            upsertScores(connection, studentId, newScores);
            connection.commit();
            return true;
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
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            System.out.println("Error checking student existence: " + e.getMessage());
            return false;
        }
    }



    private void upsertScores(Connection connection, int studentId, Map<String, Double> scores) throws SQLException {
        String sql = """
                INSERT INTO scores (student_id, subject_id, score)
                SELECT ?, subject_id, ? FROM subjects WHERE subject_name = ?
                ON CONFLICT (student_id, subject_id)
                DO UPDATE SET score = EXCLUDED.score
                """;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            for (Map.Entry<String, Double> entry : scores.entrySet()) {
                stmt.setInt(1, studentId);
                stmt.setDouble(2, entry.getValue());
                stmt.setString(3, entry.getKey());
                stmt.addBatch();
            }
            stmt.executeBatch();
        }
    }

    private void mapRowInto(Map<Integer, Student> studentMap, ResultSet rs) throws SQLException {
        int studentId = rs.getInt("student_id");

        Student student = studentMap.get(studentId);
        if (student == null) {
            student = new Student(rs.getString("registration_number"), rs.getString("name"));
            student.setStudentId(studentId);
            studentMap.put(studentId, student);
        }

        String subjectName = rs.getString("subject_name");
        if (subjectName != null) {
            double score = rs.getDouble("score");
            student.setScore(subjectName, score);
        }
    }
}