package Learning;

import java.util.Scanner;

public class SwitchCodeChallenge {
    public static void main(String[] args) {
        Scanner choice = new Scanner(System.in);

        System.out.println("=====SELECT A CHOICE====");
        System.out.println("1. Coffee");
        System.out.println("2. Tea");

        System.out.println("Enter your choice: ");
        switch (choice.nextInt()) {
            case 1:
                System.out.println("Coffee is on its way");
                break;
            case 2:
                System.out.println("Tea is on its way");
                break;
            default:
                System.out.println("Invalid Choice, please select either 1 or 2");
        }
        choice.close();
    }
}
