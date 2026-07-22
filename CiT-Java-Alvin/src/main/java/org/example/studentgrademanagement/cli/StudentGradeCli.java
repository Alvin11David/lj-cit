package org.example.studentgrademanagement.cli;

import org.example.studentgrademanagement.model.StudentResponse;
import org.example.studentgrademanagement.service.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Component
@ConditionalOnProperty(name = "app.cli.enabled", havingValue = "true", matchIfMissing = false)
public class StudentGradeCli implements CommandLineRunner {

    private final StudentService studentService;
    private final Scanner scanner = new Scanner(System.in);

    public StudentGradeCli(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public void run(String... args) {
        System.out.println("\n=== Student Grade Management System ===");

        while (true) {
            System.out.println("\n1. Add Student");
            System.out.println("2. List All Students");
            System.out.println("3. Find Student by Registration Number");
            System.out.println("4. Update Scores");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> addStudent();
                case "2" -> listAllStudents();
                case "3" -> findStudent();
                case "4" -> updateScores();
                case "5" -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option. Please enter 1-5.");
            }
        }
    }

    private void addStudent() {
        try {
            System.out.print("Enter registration number: ");
            String regNo = scanner.nextLine().trim();

            System.out.print("Enter student name: ");
            String name = scanner.nextLine().trim();

            Map<String, Double> scores = new LinkedHashMap<>();
            System.out.println("Enter scores for subjects (leave subject blank to stop):");
            while (true) {
                System.out.print("  Subject name: ");
                String subject = scanner.nextLine().trim();
                if (subject.isEmpty()) break;

                System.out.print("  Score for " + subject + ": ");
                String scoreStr = scanner.nextLine().trim();
                double score;
                try {
                    score = Double.parseDouble(scoreStr);
                } catch (NumberFormatException e) {
                    System.out.println("  Invalid number. Try again.");
                    continue;
                }
                scores.put(subject, score);
            }

            StudentResponse response = studentService.createStudent(regNo, name, scores);
            System.out.println("Student created successfully!");
            printStudent(response);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void listAllStudents() {
        List<StudentResponse> students = studentService.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        System.out.println("\n--- All Students (" + students.size() + ") ---");
        for (StudentResponse s : students) {
            printStudent(s);
            System.out.println("---");
        }
    }

    private void findStudent() {
        System.out.print("Enter registration number: ");
        String regNo = scanner.nextLine().trim();
        try {
            StudentResponse response = studentService.findByRegistrationNumber(regNo);
            printStudent(response);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void updateScores() {
        try {
            System.out.print("Enter registration number: ");
            String regNo = scanner.nextLine().trim();

            Map<String, Double> scores = new LinkedHashMap<>();
            System.out.println("Enter scores to update/add (leave subject blank to stop):");
            while (true) {
                System.out.print("  Subject name: ");
                String subject = scanner.nextLine().trim();
                if (subject.isEmpty()) break;

                System.out.print("  New score for " + subject + ": ");
                String scoreStr = scanner.nextLine().trim();
                double score;
                try {
                    score = Double.parseDouble(scoreStr);
                } catch (NumberFormatException e) {
                    System.out.println("  Invalid number. Try again.");
                    continue;
                }
                scores.put(subject, score);
            }

            StudentResponse response = studentService.recordOrUpdateScores(regNo, scores);
            System.out.println("Scores updated successfully!");
            printStudent(response);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void printStudent(StudentResponse s) {
        System.out.println("  ID: " + s.getStudentId());
        System.out.println("  Reg No: " + s.getRegistrationNumber());
        System.out.println("  Name: " + s.getName());
        System.out.println("  Scores: " + s.getScores());
        System.out.printf("  Average: %.2f%n", s.getAverage());
        System.out.println("  Grade: " + s.getLetterGrade());
    }
}
