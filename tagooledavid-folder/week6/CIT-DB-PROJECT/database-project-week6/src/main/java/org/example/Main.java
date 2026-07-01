package org.example;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentRepository studentRepo = new StudentRepository();
        SubjectRepository subjectRepo = new SubjectRepository();
        StudentSubjectScoreRepository scoreRepo = new StudentSubjectScoreRepository();
        List<Student> students = studentRepo.loadAll();

        boolean running = true;

        while (running) {
            System.out.println();
            System.out.println("=========================================");
            System.out.println("       STUDENT MANAGEMENT SYSTEM");
            System.out.println("=========================================");
            System.out.println("  [1] Register a new student");
            System.out.println("  [2] View all students");
            System.out.println("  [3] Add grades for a student");
            System.out.println("  [4] View all student averages");
            System.out.println("  [5] View student details by Reg No");
            System.out.println("  [6] Reset a student marks to zero");
            System.out.println("  [7] Clear the database");
            System.out.println("  [0] Exit");
            System.out.println("=========================================");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter student registration number: ");
                    String regNo = scanner.nextLine();
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    Student newStudent = new Student(regNo, name);
                    studentRepo.save(newStudent);
                    System.out.println("Student registered successfully!");
                    break;

                case 2:
                    studentRepo.printStudentNamesAndRegNosNicely(students);
                    break;

                case 3:
                    studentRepo.printStudentNamesAndRegNosNicely(students);
                    System.out.print("Enter student registration number: ");
                    String targetRegNo = scanner.nextLine();
                    Student foundStudent = studentRepo.findById(targetRegNo);
                    if (foundStudent == null) {
                        System.out.println("Student not found!");
                        break;
                    }
                    System.out.println("Student found: " + foundStudent.getName());
                    List<Subject> subjects = subjectRepo.loadAll();
                    for (Subject sub : subjects) {
                        int score = -1;
                        while (true) {
                            System.out.print("Enter score for " + sub.getName() + " (0-100): ");
                            String input = scanner.nextLine().trim();
                            try {
                                score = Integer.parseInt(input);
                            } catch (NumberFormatException e) {
                                System.out.println("Please enter a valid number.");
                                continue;
                            }
                            if (score < 0 || score > 100) {
                                System.out.println("Score must be between 0 and 100.");
                                continue;
                            }
                            break;
                        }
                        StudentSubjectScore sss = new StudentSubjectScore(
                            foundStudent.getStudent_id(), sub.getSubject_id(), score);
                        scoreRepo.save(sss);
                    }
                    System.out.println("Grades added successfully!");
                    break;

                case 4:
                    scoreRepo.listAllStudentsWithTheirAverages();
                    break;

                case 5:
                    studentRepo.printStudentNamesAndRegNosNicely(students);
                    System.out.print("Enter student registration number: ");
                    String detailRegNo = scanner.nextLine();
                    scoreRepo.getStudentDetailsWithRegistrationNumber(detailRegNo);
                    break;

                case 6:
                    studentRepo.printStudentNamesAndRegNosNicely(students);
                    System.out.print("Enter student registration number: ");
                    String resetRegNo = scanner.nextLine();
                    scoreRepo.resetStudentMarksToZero(resetRegNo);
                    break;

                case 7:
                    System.out.print("Are you sure you want to clear all scores? (yes/no): ");
                    String confirm = scanner.nextLine().trim().toLowerCase();
                    if (confirm.equals("yes") || confirm.equals("y")) {
                        scoreRepo.clearTheDatabase();
                    } else {
                        System.out.println("Operation cancelled.");
                    }
                    break;

                case 0:
                    System.out.println("Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
        scanner.close();
    }
}
