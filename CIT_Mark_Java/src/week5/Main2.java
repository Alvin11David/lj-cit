package week5;


abstract class Employee implements Taxable{
    String name;

    public Employee(String name){
        this.name = name;
    }
    public void clockIn(){
        System.out.println("Clocked in");
    }

    public abstract double calculateMonthlyPay();
}

interface Taxable{
    public double calculateTax();
}


class SalariedEmployee extends Employee implements Taxable{
    double monthlySalary;


    public SalariedEmployee(String name,double monthlySalary){
        super(name);
        this.monthlySalary = monthlySalary;
    }
    @Override
    public double calculateMonthlyPay(){
        return monthlySalary;
    }

    @Override
    public double calculateTax(){
        return monthlySalary*0.1;
    }
}

class  HourlyEmployee extends Employee implements Taxable{
    double rate;
    double hours;

    public HourlyEmployee(String name,double rate, double hours){
        super(name);
        this.rate = rate;
        this.hours = hours;
    }
    @Override
    public double calculateMonthlyPay(){
        return hours * rate;
    }

    @Override
    public double calculateTax() {
        return (hours*rate)*0.09;
    }
}


public class Main2 {
    public static void main(String[] args){
        Employee[] employees = {new HourlyEmployee("Mark",25000,10),
                new SalariedEmployee("Alvin",3000000)
        };

        for(Employee employee : employees){
            System.out.println(employee.name + " monthly Pay is: "+employee.calculateMonthlyPay());
            System.out.println(employee.name + " monthly Tax is: "+ employee.calculateTax());
        }

    }

}