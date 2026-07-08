package org.example;

import java.util.Arrays;

public class TicketSales {
    public static void main(String[] args) {
        int ticketNumber = 0;
        int budgetTickets = 0;
        int premiumTickets = 0;
        int[] ticketPrices = {50000, 75000, 50000, 100000, 75000, 50000, 150000, 75000, 100000, 50000};

        for (int i : ticketPrices) {
            ticketNumber++;
            System.out.println("Sale " + ticketNumber + " UGX " + i);

            if (i == 50000) {
                budgetTickets++;
            } else if (i > 75000) {
                premiumTickets++;
            }
        }

        System.out.println("Total Sales: " + ticketPrices.length);
        System.out.println("Budget Tickets (50k): " + budgetTickets);
        System.out.println("Premium Tickets (75k): " + premiumTickets);
        System.out.println("Total Revenue: " + Arrays.stream(ticketPrices).sum() );


    }
}
