package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Calculator {
    public static int add(int a, int b) {
        return a + b;
    }

    public static int subtract(int a, int b) {
        return a - b;
    }

    public static double multiply(double a, double b) {
        return a * b;
    }

    public static double divide(double a, double b) {
        if (b == 0) {
            System.out.println("Error: Cannot divide by zero.");
            return 0;
        } else {
            return a / b;
        }
    }

    public static boolean isEven(int n) {
        return n % 2 == 0;
    }

    public static int max(int a, int b, int c) {
        int largest = a;
        if (b > largest) {
            largest = b;
        }
        if (c > largest) {
            largest = c;
        }

        return largest;
    }
    public static void main(String[] args) {
        System.out.println("5+3=" + add(5,3));
        System.out.println("10-4=" + subtract(10,4));
        System.out.println("2.5*4=" + multiply(2.5,4));
        System.out.println("20/5=" + divide(20,5));
        System.out.println("20/0=" + divide(20,0));
        System.out.println("8 is even:" + isEven(8));
        System.out.println("7 is even: " + isEven(7));
        System.out.println("Max of 12, 7, 25 is " + max(12,7,25));
        }

}