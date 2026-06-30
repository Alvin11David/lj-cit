package org.example;

import java.util.Scanner;
import java.util.Map;

public class Main {

    /**
     * Displays a stylized menu for the GradeBook system.
     */
    void printMenu() {
        System.out.println("\n" + "=".repeat(55));
        System.out.printf("%-10s %s %n", "", "THE COMMANDLINE GRADEBOOK SYSTEM");
        System.out.println("=".repeat(55));
        System.out.println("  [0] Exit Application");
        System.out.println("  [1] Register Student");
        System.out.println("  [2] Get All Students");
        System.out.println("  [3] Get Unique Student Details");
        System.out.println("  [4] Delete Student");
        System.out.println("  [5] Empty the Database");
        System.out.println("-".repeat(55));
        System.out.print("Select an Option: ");
    }

    /**
     * Helper to print student summaries for selection (Cases 3 & 4)
     */
    void displaySummary(Map<String, String> summary) {
        if (summary.isEmpty()) {
            System.out.println("(!) No students currently in the system.");
            return;
        }
        System.out.printf("%n%-10s | %-20s%n", "STUDENT ID", "NAME");
        System.out.println("-".repeat(35));
        summary.forEach((id, name) -> System.out.printf("%-10s | %-20s%n", id, name));
        System.out.println("-".repeat(35));
    }

    public static void main(String[] args) {
        Main app = new Main();
        GradeBook manager = new GradeBook();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            app.printMenu();

            // Basic validation for non-integer input
            if (!scanner.hasNextInt()) {
                System.out.println("(!) Error: Please enter a numeric choice.");
                scanner.next(); // Clear invalid input
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Student Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Student Email: ");
                    String email = scanner.nextLine();
                    manager.registerStudent(name, email);
                    break;

                case 2:
                    System.out.println("\n--- FULL STUDENT LIST ---");
                    manager.showAllStudents();
                    break;

                case 3:
                    System.out.println("\n--- VIEW STUDENT DETAILS ---");
                    app.displaySummary(manager.getStudentNamesById());

                    System.out.print("Enter Student ID to view: ");
                    String searchId = scanner.nextLine();
                    Student s = manager.getStudent(searchId);

                    if (s != null) {
                        System.out.println("\n[Record Found]");
                        System.out.printf("ID      : %s%n", s.getId());
                        System.out.printf("NAME    : %s%n", s.getName());
                        System.out.printf("EMAIL   : %s%n", s.getEmail());
                    } else {
                        System.out.println("(!) Student not found.");
                    }
                    break;

                case 4:
                    System.out.println("\n--- DELETE STUDENT ---");
                    app.displaySummary(manager.getStudentNamesById());

                    System.out.print("Enter ID to delete: ");
                    String delId = scanner.nextLine();
                    manager.deleteStudent(delId);
                    System.out.println("[✓] Process completed.");
                    break;

                case 5:
                    System.out.print("Are you sure you want to empty the database? (y/n): ");
                    if (scanner.nextLine().equalsIgnoreCase("y")) {
                        manager.clearDatabase();
                        System.out.println("[✓] Database cleared.");
                    }
                    break;

                case 0:
                    running = false;
                    System.out.println("Shutting down system. Goodbye!");
                    break;

                default:
                    System.out.println("(!) Invalid choice. Please select 0-5.");
            }
        }
        scanner.close();
    }
}