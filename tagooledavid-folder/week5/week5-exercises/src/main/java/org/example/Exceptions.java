package org.example;

public class Exceptions {
    public static int divide(int a, int b){
	try {

         return a/b;

        } catch (ArithmeticException e) {
            System.out.println("Division by Zero not allowed");
            return 0;
        } finally {
            System.out.println("Done..");
        }

    }
    public static void main(String[] args){
            divide(32,8);
            divide(24,0);

        
    }
}
