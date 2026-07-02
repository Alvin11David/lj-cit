package org.example;

public class AccessModifiers {
    private double balance;
    private String pin;

    public String accountNumber;
    public String ownerName;

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    private void logTransaction(String type, double amount) {
        System.out.println(type + ": UGX" + amount + "| Balance: " + balance);
    }
}
