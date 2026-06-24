package org.example;

public class Contractor  {
    private double hourlyRate;
    private int hoursWorked;

    public Contractor( double hourlyRate, int hoursWorked) {

        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    public void logHours(int hours) {
        this.hoursWorked += hours;
    }


}
