package week5.exercise2;

public class Main {
    public static void main(String[] args) {
        HourlyEmployee hourlyEmployee = new HourlyEmployee("Alvin", 40.3, 17);
        SalariedEmployee salariedEmployee = new SalariedEmployee("David", 50000);


        System.out.println("The hourly employee tax is: " + hourlyEmployee.calculateTax() + " and the hourly employee salary is " + hourlyEmployee.calculateMonthlyPay() + " dollars");
        System.out.println("The monthly salaried employee tax is: " + salariedEmployee.calculateTax() + " and the monthly salary is " + salariedEmployee.calculateMonthlyPay());

    }
}
