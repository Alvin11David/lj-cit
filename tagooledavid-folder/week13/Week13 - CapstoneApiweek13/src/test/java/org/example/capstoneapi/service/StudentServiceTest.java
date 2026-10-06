package org.example.capstoneapi.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class StudentServiceTest {
    @Test
    void shouldReturnLetterGradeB(){
        GradeCalculator gradeCalculator = new GradeCalculator();
        StudentService studentService = new StudentService(null,gradeCalculator);
        char letterResult = studentService.calculateLetterGrade(4.5);
        assertEquals('B',letterResult);
    }

    @Test
    void shouldReturnLetterGradeA() {
        GradeCalculator calculator = new GradeCalculator();
        StudentService service = new StudentService(null, calculator);

        char result = service.calculateLetterGrade(5.5);

        assertEquals('A', result);
    }

    @Test
    void shouldReturnLetterGradeC() {
        GradeCalculator calculator = new GradeCalculator();
        StudentService service = new StudentService(null, calculator);

        char result = service.calculateLetterGrade(3.5);

        assertEquals('C', result);
    }

    @Test
    void shouldReturnLetterGradeD() {
        GradeCalculator calculator = new GradeCalculator();
        StudentService service = new StudentService(null, calculator);

        char result = service.calculateLetterGrade(2.5);

        assertEquals('D', result);
    }

    @Test
    void shouldReturnLetterGradeE() {
        GradeCalculator calculator = new GradeCalculator();
        StudentService service = new StudentService(null, calculator);

        char result = service.calculateLetterGrade(1.0);

        assertEquals('E', result);
    }
}
