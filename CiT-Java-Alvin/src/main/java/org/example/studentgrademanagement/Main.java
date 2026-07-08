package org.example.studentgrademanagement;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        StudentService service = new StudentService();

        while (true) {
            System.out.println("\n====STUDENT SYSTEM====");
            System.out.println("1. Add Student");
            System.out.println("2. Add Score");
            System.out.println("3. Lis Students");
            System.out.println("4. Find Students");
            System.out.println("5. Exit");

            System.out.println("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Registration Number: ");
                    String registrationNumber = scanner.nextLine();
            }
        }
    }
}
