package Week_5.Exercise_2;

public class SalariedEmployee extends Employee implements Taxable {
    private final double monthlySalary;

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
        return monthlySalary * 0.18;
    }
}
