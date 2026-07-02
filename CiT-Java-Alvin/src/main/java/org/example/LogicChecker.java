package org.example;

public class LogicChecker {
    public static void main(String[] args) {
        boolean isLoggedIn = true;
        boolean isAdmin = false;
        System.out.println("Regular User: " + (isLoggedIn && !isAdmin));
        System.out.println("Has access: " + (isLoggedIn || isAdmin));
        System.out.println("Not Logged in: " + (!isLoggedIn));
    }
}
