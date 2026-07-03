package org.example;

import java.util.Scanner;

public class StudentApp {
    private final StudentService service;
    private final Scanner scanner = new Scanner(System.in);

    public StudentApp(StudentService service) {
        this.service = service;
    }

    public void run() {
        boolean running = true;

        while (running) {
            printMenu();

            if (!scanner.hasNextInt()) {
                System.out.println("Please enter a numeric choice.");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 0:
                    System.out.println("Exiting Application... Goodbye!");
                    running = false;
                    break;
                case 1:
                    System.out.print("Enter Student Full Name: ");
                    service.registerStudent(scanner.nextLine());
                    break;
                case 2:
                    service.showAllStudents();
                    break;
                case 3:
                    service.showStudentDetailsByRegNo(scanner);
                    break;
                case 4:
                    service.addStudentGrades(scanner);
                    break;
                case 5:
                    service.showAllStudentAveragesAndDetails();
                    break;
                case 6:
                    service.deleteStudent(scanner);
                    break;
                case 7:
                    service.clearDatabase(scanner);
                    break;
                default:
                    System.out.println("Invalid Option.... Please select an option between 0 and 7.");
            }
        }

        scanner.close();
    }

    private void printMenu() {
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
}