package org.example;

public class Loops {
    public static void main(String[] args) {
        int[] scores = {86, 89, 90, 67};

        for (int i : scores) {
            System.out.println("Marks: " + i);
        }

        String[] students = {"Alvin", "David", "Denis", "Larrie"};

        for (String student : students) {
            System.out.println("Welcome, " + student);
        }
    }
}
