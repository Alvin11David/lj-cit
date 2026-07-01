package StudentGradeProject;

import java.util.ArrayList;
import java.util.List;

public class Student {

    private final String name;
    private final String registrationNumber;
    private final List<Integer> scores;

    public Student(String name, String registrationNumber) {
        this.name = name;
        this.registrationNumber = registrationNumber;
        this.scores = new ArrayList<>();
    }

    public void addScore(int score) {
        if (score < 0 || score > 100) {
            throw new IllegalArgumentException("Score must be between 0 and 100. Got: " + score);
        }
        scores.add(score);
    }

    public double calculateAverage() {
        if (scores.isEmpty()) {
            return 0.0;
        }
        int total = 0;
        for (int score : scores) {
            total += score;
        }
        return (double) total / scores.size();
    }

    public String getName() {
        return name;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public List<Integer> getScores() {
        return new ArrayList<>(scores);
    }

    @Override
    public String toString() {
        return registrationNumber + " | " + name + " | Average: " + calculateAverage();
    }
}