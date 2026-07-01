package Week_5.Exercise_2;

public abstract class Employee {
    protected String name;

    public Employee(String name) {
        this.name = name;
    }


    public void clockIn() {
        System.out.println(name + " clocked in.");
    }


    public abstract double calculateMonthlyPay();
}

