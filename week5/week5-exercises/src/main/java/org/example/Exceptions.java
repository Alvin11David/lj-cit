package org.example;

public class Exceptions {
    public static double divide(double a, double b){
        return a/b;
    }
    public static void main(String[] args){

        try {
            divide(32,8);
            divide(24,0);

        } catch (ArithmeticException e) {
            System.out.println("Division by Zero not allowed");
        } finally {
            System.out.println("Done..");
        }
    }
}
