package org.example;

public class Ride {
    public static void main(String[] args) {
    double distanceKm = 8;
    int hour = 8;
    boolean isRaining = true;
    double distanceCost = 0;
    double baseFare = 3000;

    if (distanceKm <= 0) {
        System.out.println("Invalid distance.");
        return;
    } else if (distanceKm > 0 && distanceKm <= 5) {
        distanceCost = 1000 * distanceKm;
        System.out.println(distanceCost);
    } else if (distanceKm > 5 && distanceKm <= 15) {
        distanceCost = 800 * distanceKm;
        System.out.println(distanceCost);
    } else {
        distanceCost = 600 * distanceKm;
        System.out.println(distanceCost);
    }

    double total = distanceCost + baseFare;


    double status = (isRaining) ? (total +  500) : total;
        System.out.println("Final Fare: UGX " +  status);
    }
}