package org.example;

public class WhileLoop {
    public static void main(String[] args) {
        int attempts = 0;
        int maxAttempts = 3;
        boolean pinCorrect = false;

        while (attempts < maxAttempts && !pinCorrect) {
            attempts++;
            System.out.println("Attempt " + attempts + ": Enter your PIN");
        }

        if (!pinCorrect) {
            System.out.println("3 incorrect attempts. Account Locked");
        }
    }
}
