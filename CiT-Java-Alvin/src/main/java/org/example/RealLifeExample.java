package org.example;

public class RealLifeExample {
    public static void main(String[] args)  {
        int items = 50;
        float costPerItem = 9000.00f;
        double totalCost = items * costPerItem;
        char currency = '$';

        System.out.println("Number of items is: " + items);
        System.out.println("Cost per item is: " + currency + totalCost);
        System.out.println("Total Cost is: " + currency + totalCost );
    }
}