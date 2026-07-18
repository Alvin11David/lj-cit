package org.example.studentgrademanagement.model;

import java.util.Map;

public class ScoreRequest {

    private Map<String, Double> scores;

    public Map<String, Double> getScores() {
        return scores;
    }

    public void setScores(Map<String, Double> scores) {
        this.scores = scores;
    }
}
