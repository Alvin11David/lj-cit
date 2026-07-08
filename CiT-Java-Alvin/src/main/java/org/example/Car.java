package org.example;

public class Car {
    private String name;
    private int year;
    private boolean active;

    public Car(String name, int year, boolean active) {
        this.name = name;
        this.year = year;
        this.active = active;
    }

    //name getter
    public String getName() {
        return name;
    }

    //year getter
    public int getYear() {
        return year;
    }

    //active getter
    public boolean isActive() {
        return active;
    }

    public static void main(String[] args) {
        Car car1 = new Car("Brabas", 2026, true);

        System.out.println(car1.getName());
        System.out.println(car1.getYear());
        System.out.println(car1.isActive());
    }
}