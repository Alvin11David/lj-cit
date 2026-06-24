package org.example;

public class If {
    public static void main(String[] args) {
        double dataUsedGB = 12.5;

        if (dataUsedGB < 0 ) {
            System.out.println("Invalid data usage entered");
        } else if (dataUsedGB == 0 ) {
            System.out.println("No data used yet. Full speed: 100Mbps");
        } else if (dataUsedGB >= 5 && dataUsedGB <= 10) {
            System.out.println("Reduced speed: 20Mbps");
        } else if (dataUsedGB >= 10 && dataUsedGB <= 20) {
            System.out.println("Throttled: 5Mbps");
        } else if (dataUsedGB > 20) {
            System.out.println("Blocked: 0Mbps - buy more data");
        }

        String usage = (dataUsedGB < 10) ? "Data status: On track" : "Data status: Needs top-up";
        System.out.println(usage);
    }
}