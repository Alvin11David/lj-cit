package week5.exercise2;

public class HourlyEmployee extends Employee implements TaxableInterface {
    private double hours;
    private double rate;

    public HourlyEmployee(String name, double rate, double hours) {
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
        return calculateMonthlyPay() * 0.18;
    }
}
