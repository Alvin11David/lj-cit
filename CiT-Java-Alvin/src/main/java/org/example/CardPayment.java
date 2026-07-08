package org.example;

public class CardPayment extends Payment {
    public CardPayment(double amount) {
        super(amount);
    }

    @Override
    public boolean process() {
        System.out.println("Charging UGX " + amount + " to a bank card..");
        return false;
    }


}
