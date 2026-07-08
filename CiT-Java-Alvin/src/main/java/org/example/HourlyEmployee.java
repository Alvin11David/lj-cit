package org.example;

public class HourlyEmployee extends Employee implements Taxable {
    private int hours;
    private double rate;

    public HourlyEmployee(String name, int hours, double rate) {
        super(name);
        this.hours = hours;
        this.rate = rate;
    }



    @Override
    public double calculateMonthlyPay() {
        return rate * hours;
    }

    @Override
    public double calculateTax() {
        return calculateMonthlyPay() * 0.10;
    }
}
