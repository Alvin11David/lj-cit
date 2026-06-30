package StudentGradeManagementSystem;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    private static final StudentDAO dao = new StudentDAO();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        System.out.println("=== Student Grade Management System ===");

        while (running) {
            printMenu();
            int choice = readInt("Choose an option: ");

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> recordScores();
                case 3 -> listAllStudents();
                case 4 -> lookupStudent();
                case 5 -> running = false;
                default -> System.out.println("Invalid option, please choose 1-5.");
            }
            System.out.println();
        }

        System.out.println("Goodbye!");
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("""
                ----------------------------
                1. Add Student
                2. Record / Update Scores
                3. List All Students
                4. Look Up Student by Registration Number
                5. Exit
                ----------------------------""");
    }

    private static void addStudent() {
        System.out.print("Enter registration number: ");
        String regNo = scanner.nextLine().trim();

        if (dao.existsByRegistrationNumber(regNo)) {
            System.out.println("A student with that registration number already exists.");
            return;
        }

        System.out.print("Enter name: ");
        String name = scanner.nextLine().trim();

        double math = readScore("Math score (0-100): ");
        double english = readScore("English score (0-100): ");
        double science = readScore("Science score (0-100): ");
        double social = readScore("Social Studies score (0-100): ");

        try {
            Student student = new Student(regNo, name, math, english, science, social);
            dao.insertStudent(student);
        } catch (IllegalArgumentException e) {
            System.out.println("Could not add student: " + e.getMessage());
        }
    }

    private static void recordScores() {
        System.out.print("Enter registration number: ");
        String regNo = scanner.nextLine().trim();

        if (!dao.existsByRegistrationNumber(regNo)) {
            System.out.println("No student found with that registration number.");
            return;
        }

        double math = readScore("Math score (0-100): ");
        double english = readScore("English score (0-100): ");
        double science = readScore("Science score (0-100): ");
        double social = readScore("Social Studies score (0-100): ");

        boolean updated = dao.updateScores(regNo, math, english, science, social);
        System.out.println(updated ? "Scores updated." : "Update failed.");
    }

    private static void listAllStudents() {
        List<Student> students = dao.getAllStudents();

        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        System.out.println("===== ALL STUDENTS =====");
        for (Student s : students) {
            System.out.println(s);
        }
    }

    private static void lookupStudent() {
        System.out.print("Enter registration number: ");
        String regNo = scanner.nextLine().trim();

        Optional<Student> result = dao.findByRegistrationNumber(regNo);

        if (result.isPresent()) {
            System.out.println(result.get());
        } else {
            System.out.println("No student found with registration number: " + regNo);
        }
    }

    // ---- Input helpers ----

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a whole number.");
            }
        }
    }

    private static double readScore(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                double value = Double.parseDouble(input);
                if (value < 0 || value > 100) {
                    System.out.println("Score must be between 0 and 100.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}