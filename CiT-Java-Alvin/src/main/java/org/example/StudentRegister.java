package org.example;

import java.util.Scanner;

public class StudentRegister {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String name;
        String studentClass;
        System.out.print("Enter student name: ");
        name = input.nextLine();

        System.out.print("Enter class: ");
        studentClass = input.nextLine();

        System.out.println("Student Registered Successfully");
        System.out.println("Name: " + name);
        System.out.println("Class: " + studentClass);
    }
}