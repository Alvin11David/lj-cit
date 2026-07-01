package StudentGradeProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentService {

    public void addStudent(Student student) {
        String insertStudentSql = "INSERT INTO students (name, registration_number) VALUES (?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(insertStudentSql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, student.getName());
            statement.setString(2, student.getRegistrationNumber());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int studentId = generatedKeys.getInt(1);
                insertScores(connection, studentId, student.getScores());
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to add student: " + e.getMessage(), e);
        }
    }

    private void insertScores(Connection connection, int studentId, List<Integer> scores) throws SQLException {
        String insertScoreSql = "INSERT INTO scores (student_id, score) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(insertScoreSql)) {
            for (int score : scores) {
                statement.setInt(1, studentId);
                statement.setInt(2, score);
                statement.executeUpdate();
            }
        }
    }

    public void addScoreToStudent(String registrationNumber, int score) {
        String findIdSql = "SELECT id FROM students WHERE registration_number = ?";
        String insertScoreSql = "INSERT INTO scores (student_id, score) VALUES (?, ?)";

        try (Connection connection = DatabaseConnection.getConnection()) {

            int studentId;
            try (PreparedStatement findStatement = connection.prepareStatement(findIdSql)) {
                findStatement.setString(1, registrationNumber);
                ResultSet resultSet = findStatement.executeQuery();
                if (!resultSet.next()) {
                    throw new IllegalArgumentException("No student found with registration number: " + registrationNumber);
                }
                studentId = resultSet.getInt("id");
            }

            try (PreparedStatement insertStatement = connection.prepareStatement(insertScoreSql)) {
                insertStatement.setInt(1, studentId);
                insertStatement.setInt(2, score);
                insertStatement.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to add score: " + e.getMessage(), e);
        }
    }

    public Student findStudent(String registrationNumber) {
        String selectStudentSql = "SELECT id, name, registration_number FROM students WHERE registration_number = ?";

        int id;
        String name;
        String regNumber;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectStudentSql)) {

            statement.setString(1, registrationNumber);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                throw new IllegalArgumentException("No student found with registration number: " + registrationNumber);
            }

            id = resultSet.getInt("id");
            name = resultSet.getString("name");
            regNumber = resultSet.getString("registration_number");

        } catch (SQLException e) {
            throw new RuntimeException("Failed to find student: " + e.getMessage(), e);
        }

        return buildStudentWithScores(id, name, regNumber);
    }

    public List<Student> listStudents() {
        String selectAllSql = "SELECT id, name, registration_number FROM students";
        List<int[]> idsOnly = new ArrayList<>();
        List<Student> students = new ArrayList<>();
        List<String[]> rows = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectAllSql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                rows.add(new String[] {
                        String.valueOf(resultSet.getInt("id")),
                        resultSet.getString("name"),
                        resultSet.getString("registration_number")
                });
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to list students: " + e.getMessage(), e);
        }

        for (String[] row : rows) {
            int id = Integer.parseInt(row[0]);
            students.add(buildStudentWithScores(id, row[1], row[2]));
        }

        return students;
    }

    private Student buildStudentWithScores(int studentId, String name, String registrationNumber) {
        Student student = new Student(name, registrationNumber);
        String selectScoresSql = "SELECT score FROM scores WHERE student_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectScoresSql)) {

            statement.setInt(1, studentId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                student.addScore(resultSet.getInt("score"));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to load scores: " + e.getMessage(), e);
        }

        return student;
    }
}