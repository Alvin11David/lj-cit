package Week_5.Exercise_2;

public class HourlyEmployee extends Employee implements Taxable {
    private double hourlyRate;
    private double hoursWorked;

    public HourlyEmployee(String name, double hourlyRate, double hoursWorked) {
        super(name);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    @Override
    public double calculateMonthlyPay() {
        return hourlyRate * hoursWorked;
    }

    @Override
    public double calculateTax() {
        return calculateMonthlyPay() * 0.20;
    }
}
