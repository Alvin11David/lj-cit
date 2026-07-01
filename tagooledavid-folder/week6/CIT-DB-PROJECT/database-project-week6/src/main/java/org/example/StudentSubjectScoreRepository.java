package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentSubjectScoreRepository implements CRUDRepository<StudentSubjectScore,Integer> {

    @Override
    public void save(StudentSubjectScore entity) {
        String sql = "INSERT INTO studentsubjectscore (student_id, subject_id, score) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, entity.getStudent_id());
            ps.setInt(2, entity.getSubject_id());
            ps.setInt(3, entity.getScore());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public StudentSubjectScore findById(Integer integer) {

        String sql = "SELECT * FROM studentsubjectscore WHERE sss_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
        ){
            ps.setInt(1,integer);

            ResultSet rs = ps.executeQuery();


            if (rs.next()){
                Integer student_id = rs.getInt("student_id");
                Integer subject_id = rs.getInt("subject_id");
                Integer score = rs.getInt("score");
                return new StudentSubjectScore(student_id,subject_id,score);
            }

        } catch( SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<StudentSubjectScore> loadAll() {
        List<StudentSubjectScore> studentSubjectScores = new ArrayList<>();
        String sql = "SELECT * FROM studentsubjectscore ";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
        ){
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Integer student_id = rs.getInt("student_id");
                Integer subject_id = rs.getInt("subject_id");
                Integer score = rs.getInt("score");
                studentSubjectScores.add(new StudentSubjectScore(student_id,subject_id,score));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return studentSubjectScores;
    }


    public char calculateLetterGrade(int score){
        if (score >= 80) {
            return  'A';
        } else if (score >= 75) {
            return'B';
        } else if (score >= 65) {
            return'C';
        } else if (score >= 50) {
            return 'D';
        } else {
            return 'F';
        }
    }

    // needs implementation
    public double calculateStudentAverage(){
        double average = 0.0;
        String sql = "SELECT AVG(score) AS avg_score FROM studentsubjectscore";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                average = rs.getDouble("avg_score");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return average;
    }

    // list students with averages
    public void listAllStudentsWithTheirAverages(){
        String sql = "SELECT s.regno, s.name, AVG(sss.score) AS avg_score " +
                     "FROM student s " +
                     "JOIN studentsubjectscore sss ON s.student_id = sss.student_id " +
                     "GROUP BY s.student_id, s.regno, s.name " +
                     "ORDER BY s.regno";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ResultSet rs = ps.executeQuery();
            System.out.println("\n--- Student Averages & Grades ---");
            System.out.printf("%-10s %-20s %-10s %-6s%n", "Reg No", "Name", "Average", "Grade");
            System.out.println("-".repeat(50));
            while (rs.next()) {
                String regNo = rs.getString("regno");
                String name = rs.getString("name");
                double avg = rs.getDouble("avg_score");
                char grade = calculateLetterGrade((int) avg);
                System.out.printf("%-10s %-20s %-10.2f %-6c%n", regNo, name, avg, grade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // to implement
    public void getStudentDetailsWithRegistrationNumber(String studentRegNo){
        String sql = "SELECT s.regno, s.name, sub.name AS subject_name, sss.score " +
                     "FROM student s " +
                     "JOIN studentsubjectscore sss ON s.student_id = sss.student_id " +
                     "JOIN subject sub ON sss.subject_id = sub.subject_id " +
                     "WHERE s.regno = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, studentRegNo);
            ResultSet rs = ps.executeQuery();
            System.out.println("\n--- Student Details: " + studentRegNo + " ---");
            System.out.printf("%-20s %-10s %-6s%n", "Subject", "Score", "Grade");
            System.out.println("-".repeat(40));
            while (rs.next()) {
                String name = rs.getString("name");
                String subjectName = rs.getString("subject_name");
                int score = rs.getInt("score");
                char grade = calculateLetterGrade(score);
                System.out.printf("%-20s %-10d %-6c%n", subjectName, score, grade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // to implement
    public void clearTheDatabase(){
        String sql1 = "DELETE FROM studentsubjectscore";
        String sql2 = "DELETE FROM student";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps1 = conn.prepareStatement(sql1);
             PreparedStatement ps2 = conn.prepareStatement(sql2)
        ) {
            ps1.executeUpdate();
            ps2.executeUpdate();
            System.out.println("All student subject scores have been cleared.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // reset a student marks to 0
    public void resetStudentMarksToZero(String studentRegNo){
        String sql = "UPDATE studentsubjectscore SET score = 0 " +
                     "WHERE student_id = (SELECT student_id FROM student WHERE regno = ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, studentRegNo);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Marks reset to 0 for student: " + studentRegNo);
            } else {
                System.out.println("No scores found for student: " + studentRegNo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    }



