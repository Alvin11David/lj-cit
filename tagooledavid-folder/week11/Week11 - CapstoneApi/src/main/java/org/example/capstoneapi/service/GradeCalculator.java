package org.example.capstoneapi.service;

import org.springframework.stereotype.Service;

@Service
public class GradeCalculator {
    public char turnAverageToLetterGrade(double gradeValue){
        char letterGrade;
        if (gradeValue > 5.0){
            letterGrade = 'A';
        } else if (gradeValue > 4.0) {
            letterGrade = 'B';
        } else if (gradeValue > 3.0) {
            letterGrade = 'C';
        } else if (gradeValue > 2.0) {
            letterGrade = 'D';
        } else letterGrade = 'E';
        return letterGrade;
    }
}
