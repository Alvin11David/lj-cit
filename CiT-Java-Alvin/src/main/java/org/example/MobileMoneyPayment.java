package org.example;

public class MobileMoneyPayment extends Payment {
    public MobileMoneyPayment(double amount) {
        super(amount);
    }

    @Override
    public boolean process() {
        System.out.println("Sending UGX " + amount + " via Mobile Money...");
        return false;
    }
}
