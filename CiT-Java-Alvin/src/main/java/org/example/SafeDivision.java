package org.example;

public class SafeDivision {

    public static int divide(int a, int b) {

        try {
            return a / b;
        } catch (ArithmeticException e) {
            System.out.println("Division by zero is not allowed");
            return 0;
        } finally {
            System.out.println("Done.");
        }
    }

    public static void main(String[] args) {
        System.out.println("Result: " + divide(20, 5));
        System.out.println();
        System.out.println("Result: " + divide(4, 0));
    }
}
