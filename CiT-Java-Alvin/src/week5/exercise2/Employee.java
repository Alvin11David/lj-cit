package week5.exercise2;

public abstract class Employee {
    private String name;

    public Employee(String name) {
        this.name = name;
    }

    public void clockIn() {
        System.out.println("Clock In");
    }

    public abstract double calculateMonthlyPay();
}
