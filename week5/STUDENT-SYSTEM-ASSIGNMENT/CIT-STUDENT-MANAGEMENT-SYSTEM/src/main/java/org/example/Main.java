package org.example;

import java.util.Scanner;

public class Main {

    void printMenu() {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("=".repeat(55));
        System.out.printf("%-10s %s %n", "", "THE CIT STUDENT MANAGEMENT SYSTEM ");
        System.out.println("=".repeat(55));
        System.out.println("  [0] Exit Application");
        System.out.println("  [1] Register Student");
        System.out.println("  [2] Get All Students ");
        System.out.println("  [3] Get Unique Student And Grades (By Reg No)");
        System.out.println("  [4] Add Student Grades");
        System.out.println("  [5] View All Student Averages & Grades");
        System.out.println("  [6] Delete Student");
        System.out.println("  [7] Empty the Entire Database");
        System.out.println("-".repeat(55));
        System.out.print("Select an Option: ");
    }

    public static void main(String[] args) {
        Main app = new Main();
        StudentService manager = new StudentService();
        boolean running = true;
        Scanner userInput = new Scanner(System.in);

        while (running) {
            app.printMenu();
            if (!userInput.hasNextInt()) {
                System.out.println("Please enter a numeric choice.");
                userInput.next();
                continue;
            }

            int choice = userInput.nextInt();
            userInput.nextLine();

            switch (choice) {
                case 0:
                    System.out.println("Exiting Application... Goodbye!");
                    running = false;
                    break;

                case 1:
                    System.out.print("Enter Student Full Name: ");
                    String name = userInput.nextLine();
                    manager.registerStudent(name);
                    break;

                case 2:
                    manager.showAllStudents();
                    break;

                case 3:
                    System.out.println("Available students...");
                    manager.listStudentregNoAndName();
                    System.out.print("Enter Student Registration Number (e.g., CIT1): ");
                    String targetRegNo = userInput.nextLine().trim();
                    Student foundStudent = manager.getStudentDataByregNo(targetRegNo);


                    if (foundStudent != null) {
                        System.out.println("\nStudent Found!");
                        System.out.println("ID: " + foundStudent.getRegNo());
                        System.out.println("Name: " + foundStudent.getName());
                        System.out.println("Grades: "+ foundStudent.getGrades());

                    } else {
                        System.out.println("(!) No student found with Registration Number: " + targetRegNo);
                    }
                    break;


                case 4:
                    System.out.println("Available students...");
                    manager.listStudentregNoAndName();
                    System.out.print("Enter Student Registration Number (e.g., CIT1): ");
                    String targetRegNumber = userInput.nextLine().trim();
                    Student wantedStudent = manager.getStudentDataByregNo(targetRegNumber);


                    if (wantedStudent != null) {
                        System.out.println("\nStudent Found!");
                        System.out.println("ID: " + wantedStudent.getRegNo());
                        System.out.println("Name: " + wantedStudent.getName());


                        for (Subject subject : Subject.values()) {
                            int score = -1;

                            while (true) {
                                System.out.print("Enter score for " + subject + " (0-100): ");
                                String input = userInput.nextLine().trim();
                                if (input.isEmpty()) {
                                    System.out.println("(!) Score cannot be empty. Please try again.");
                                    continue;
                                }
                                try {
                                    score = Integer.parseInt(input);
                                } catch (NumberFormatException e) {
                                    System.out.println("(!) Invalid input. Please enter a whole number.");
                                    continue;
                                }
                                if (score < 0 || score > 100) {
                                    System.out.println("(!) Score must be between 0 and 100.");
                                    continue;
                                }
                                break;
                            }
                            wantedStudent.setGrades(subject, score);
                        }
                        System.out.println("Grades updated successfully!");

                    } else {
                        System.out.println("No student found with Registration Number: " +targetRegNumber);
                    }
                    break;
                case 5:
                    manager.showAllStudentAveragesAndDetails();
                    break;

                case 6:
                    System.out.println("Available students...");
                    manager.listStudentregNoAndName();
                    System.out.print("Enter Registration Number to delete: ");
                    String regToDelete = userInput.nextLine().trim();
                    manager.deleteStudent(regToDelete);
                    break;

                case 7:
                    System.out.print("Are you sure you want to empty the database? (yes/no): ");
                    String confirm = userInput.nextLine().trim().toLowerCase();
                    if (confirm.equals("yes") || confirm.equals("y")) {
                        manager.clearDatabase();
                    } else {
                        System.out.println("Operation canceled.");
                    }
                    break;

                default:
                    System.out.println("Invalid Option.... Please select an option between 0 and 7.");
            }
        }
        userInput.close();
    }
}