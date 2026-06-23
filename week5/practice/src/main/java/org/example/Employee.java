package org.example;

abstract class Employee {
    String name;

    Employee(String name){
        this.name = name;
    }

    public void clockIn(){
        System.out.printf("%s ----> comes at 8:00am",name);
    }

    public abstract void calculateMonthlyPay();
}


class SalariedEmployee extends Employee implements Taxable{
    double fixedMonthlySalary;

    SalariedEmployee(String name, double fixedMonthlySalary){
        super(name);
        this.fixedMonthlySalary = fixedMonthlySalary;
    }

    @Override
    public void calculateMonthlyPay(){
        System.out.printf("The Salaried Employee: %s has a monthly salary of %.3f",name,fixedMonthlySalary);
    }

    @Override
    public double claculateTax(){
        return (0.18*fixedMonthlySalary);
    }
}


class HourlyEmployee extends Employee implements Taxable{
    double rate;
    double hours;

    HourlyEmployee(String name, double rate, double hours){
        super(name);
        this.hours= hours;
        this.rate = rate;
    }

    public void calculateMonthlyPay(){
        System.out.printf("The Hourly Employee --> %s gets %.3f",name,hours*rate);
    }

    @Override
    public double claculateTax(){
        return (0.18*rate*hours);
    }
}


class TestOutAbstractionAndInterfaces{
    public static void main(String[] args){
        System.out.println("=".repeat(20));
        System.out.println("Salaried Employee");
        SalariedEmployee userOne = new SalariedEmployee("Jacob Jeremiah",2000000.00);
        userOne.clockIn();
        userOne.calculateMonthlyPay();
        System.out.printf("Jacob Jeremiah's Tax: %.2f%n%n", userOne.claculateTax());
        System.out.println("=".repeat(20));
        System.out.println("Hourly Employee");
        HourlyEmployee userTwo = new HourlyEmployee("Alex Mutungi",0.8,12);
        userTwo.clockIn();
        userTwo.calculateMonthlyPay();
        System.out.printf("Alex Mutungi's Tax: %.2f%n", userTwo.claculateTax());
    }
}
