package org.example;

import java.util.Map;
import java.util.Scanner;

public class StudentService {
    private final StudentRepository repository;
    private static int studentNumber = 0;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public void registerStudent(String name) {
        studentNumber++;
        String generatedId = "CIT" + studentNumber;
        Student student = new Student(generatedId, name);
        repository.save(student);
        System.out.println("Student " + name + " registered with ID: " + generatedId + " Successfully");
    }

    public void showAllStudents() {
        if (repository.isEmpty()) {
            System.out.println("Currently there are no students in the Database");
            return;
        }

        System.out.println("Students in the Database");
        for (Student student : repository.findAll()) {
            System.out.println("Name: " + student.getName());
        }
    }

    public void showStudentDetailsByRegNo(Scanner scanner) {
        System.out.println("Available students...");
        listStudentregNoAndName();
        System.out.print("Enter Student Registration Number (e.g., CIT1): ");
        String regNo = scanner.nextLine().trim();

        Student student = repository.findByRegNo(regNo);
        if (student != null) {
            System.out.println("\nStudent Found!");
            System.out.println("ID: " + student.getRegNo());
            System.out.println("Name: " + student.getName());
            System.out.println("Grades: " + student.getGrades());
        } else {
            System.out.println("(!) No student found with Registration Number: " + regNo);
        }
    }

    public void addStudentGrades(Scanner scanner) {
        System.out.println("Available students...");
        listStudentregNoAndName();
        System.out.print("Enter Student Registration Number (e.g., CIT1): ");
        String regNo = scanner.nextLine().trim();

        Student student = repository.findByRegNo(regNo);
        if (student == null) {
            System.out.println("No student found with Registration Number: " + regNo);
            return;
        }

        System.out.println("\nStudent Found!");
        System.out.println("ID: " + student.getRegNo());
        System.out.println("Name: " + student.getName());

        for (Subject subject : Subject.values()) {
            int score;
            while (true) {
                System.out.print("Enter score for " + subject + " (0-100): ");
                String input = scanner.nextLine().trim();

                try {
                    score = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a whole number.");
                    continue;
                }

                if (score < 0 || score > 100) {
                    System.out.println("Score must be between 0 and 100.");
                    continue;
                }
                break;
            }
            student.setGrades(subject, score);
        }

        System.out.println("Grades updated successfully!");
    }

    public void showAllStudentAveragesAndDetails() {
        if (repository.isEmpty()) {
            System.out.println("No averages to show since database is empty..");
            return;
        }

        for (Student student : repository.findAll()) {
            System.out.println("--".repeat(20));
            System.out.println("Registration Number: " + student.getRegNo());
            System.out.println("Name: " + student.getName());
            System.out.println();
            loopThroughGradesMap(student.getGrades());
            System.out.println();
        }
    }

    public void deleteStudent(Scanner scanner) {
        System.out.println("Available students...");
        listStudentregNoAndName();
        System.out.print("Enter Registration Number to delete: ");
        String regNo = scanner.nextLine().trim();
        repository.delete(regNo);
        System.out.println("Student deleted successfully.");
    }

    public void clearDatabase(Scanner scanner) {
        System.out.print("Are you sure you want to empty the database? (yes/no): ");
        String confirm = scanner.nextLine().trim().toLowerCase();
        if (confirm.equals("yes") || confirm.equals("y")) {
            repository.clear();
            System.out.println("Database cleared.");
        } else {
            System.out.println("Operation canceled.");
        }
    }

    public void listStudentregNoAndName() {
        if (repository.isEmpty()) {
            System.out.println("Currently there are no students in the Database.");
            return;
        }

        for (Student student : repository.findAll()) {
            System.out.println(student.getRegNo() + " | " + student.getName());
        }
    }

    public void loopThroughGradesMap(Map<Subject, Integer> grades) {
        if (grades == null || grades.isEmpty()) {
            System.out.println("No Grades Available..");
            return;
        }

        for (Map.Entry<Subject, Integer> entry : grades.entrySet()) {
            Subject subject = entry.getKey();
            Integer score = entry.getValue();
            char letterGrade = Student.calculateGrade(score);
            System.out.printf("Subject: %-13s Score: %d -> Grade: %c%n", subject, score, letterGrade);
        }
    }
}