package org.example;


public class SalariedEmployee extends Employee implements Taxable {
    private double monthlySalary;

    public SalariedEmployee(String name, double monthlySalary) {
        super(name);
        this.monthlySalary = monthlySalary;
    }

    @Override
    public double calculateMonthlyPay() {
        return monthlySalary;
    }

    @Override
    public double calculateTax() {
        return monthlySalary * 0.10;
    }
}
