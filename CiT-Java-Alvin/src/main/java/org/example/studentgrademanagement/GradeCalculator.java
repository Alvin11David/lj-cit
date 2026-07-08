package org.example.studentgrademanagement;

public class GradeCalculator {
    public static double calculateGrade(double average) {
        if (average >= 90) {
            return 'A';
        } else if (average >= 80) {
            return 'B';
        } else if (average >= 70) {
            return 'C';
        } else if (average >= 60) {
            return 'D';
        } else if (average >= 50) {
            return 'E';
        } else {
            return 'F';
        }
    }
}
