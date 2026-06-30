package StudentGradeProject;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        StudentService studentService = new StudentService();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n1. Add Student");
            System.out.println("2. Add Score");
            System.out.println("3. List Students");
            System.out.println("4. Find Student");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1":
                        System.out.print("Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Registration Number: ");
                        String regNumber = scanner.nextLine();
                        studentService.addStudent(new Student(name, regNumber));
                        System.out.println("Student added.");
                        break;

                    case "2":
                        System.out.print("Registration Number: ");
                        String regForScore = scanner.nextLine();
                        System.out.print("Score: ");
                        int score = Integer.parseInt(scanner.nextLine());
                        if (score < 0 || score > 100) {
                            System.out.println("Error: Score must be between 0 and 100.");
                            break;
                        }
                        studentService.addScoreToStudent(regForScore, score);
                        System.out.println("Score added.");
                        break;

                    case "3":
                        List<Student> students = studentService.listStudents();
                        for (Student s : students) {
                            System.out.println(s + " | Grade: " + GradeCalculator.calculateGrade(s.calculateAverage()));
                        }
                        break;

                    case "4":
                        System.out.print("Registration Number: ");
                        String regToFind = scanner.nextLine();
                        Student found = studentService.findStudent(regToFind);
                        System.out.println(found + " | Grade: " + GradeCalculator.calculateGrade(found.calculateAverage()));
                        break;

                    case "5":
                        running = false;
                        break;

                    default:
                        System.out.println("Invalid option.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Score must be a whole number.");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (RuntimeException e) {
                System.out.println("Database error: " + e.getMessage());
            }
        }

        scanner.close();
    }
}