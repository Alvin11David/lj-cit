package org.example;

public class ErrorBlock {
    public static void main(String[] args) {
        try {
            int result =  100/0 ;
            System.out.println(result);
        } catch (ArithmeticException e) {
            System.out.println("Cannot divide by zero!");
            System.out.println(e);
        }
        System.out.println("Program continues...");
    }
}
