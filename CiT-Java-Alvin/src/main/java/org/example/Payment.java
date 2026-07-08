package org.example;

public class Payment {
    protected double amount;

    public Payment(double amount) {
        this.amount = amount;
    }

    public boolean process() {
        System.out.println("Processing a generic payment of UGX " + amount);
        return false;
    }
}
