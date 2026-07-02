package org.example.studentgrademanagement;

import java.util.List;

public class Student {
    private String name;
    private String registrationNumber;
    private List<Integer> scores;

    public Student(String name, String registrationNumber, List<Integer> scores) {
        this.name = name;
        this.registrationNumber = registrationNumber;
        this.scores = scores;
    }

    //name - getter
    public String getName() {
        return name;
    }

    //reg number - getter
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    //scores - getter
    public List<Integer> getScores() {
        return scores;
    }

    //add score method
    public void addScore(int score) {
        scores.add(score);
    }

    //cal average
    public double calculateAverage() {
        if (scores.isEmpty()) {
            return 0;
        }

        int total = 0;

        for (int score : scores) {
            total += score;
        }

        return (double) total / scores.size();
    }
}
