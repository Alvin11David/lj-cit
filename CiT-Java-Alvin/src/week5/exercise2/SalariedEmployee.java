package week5.exercise2;

public class SalariedEmployee extends Employee implements TaxableInterface {
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
        return calculateMonthlyPay() * 0.18;
    }
}
