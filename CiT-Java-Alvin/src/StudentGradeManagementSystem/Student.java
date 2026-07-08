package StudentGradeManagementSystem;

import java.util.LinkedHashMap;
import java.util.Map;

public class Student {

    private int studentId;
    private String registrationNumber;
    private String name;
    private final Map<String, Double> scores = new LinkedHashMap<>();

    public Student(String registrationNumber, String name) {
        this.registrationNumber = registrationNumber;
        this.name = name;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getName() {
        return name;
    }

    public void setScore(String subjectName, double score) {
        if (score < 0 || score > 100) {
            throw new IllegalArgumentException(
                    subjectName + " score must be between 0 and 100, got: " + score);
        }
        scores.put(subjectName, score);
    }

    public double getScore(String subjectName) {
        return scores.getOrDefault(subjectName, 0.0);
    }

    public Map<String, Double> getScores() {
        return scores;
    }

    public double getAverageScore() {
        if (scores.isEmpty()) return 0.0;
        return scores.values().stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }

    public String getLetterGrade() {
        double avg = getAverageScore();
        if (avg >= 80) return "A";
        if (avg >= 70) return "B";
        if (avg >= 60) return "C";
        if (avg >= 50) return "D";
        return "F";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("ID: %-3s | RegNo: %-10s | Name: %-15s | ",
                studentId, registrationNumber, name));
        for (Map.Entry<String, Double> entry : scores.entrySet()) {
            sb.append(String.format("%s: %.1f | ", entry.getKey(), entry.getValue()));
        }
        sb.append(String.format("Average: %.2f | Grade: %s", getAverageScore(), getLetterGrade()));
        return sb.toString();
    }
}