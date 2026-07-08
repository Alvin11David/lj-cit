package org.example;

public class BankAccount { //Bank Account class
        String accountNumber;
        String ownerName;
        double balance;
        boolean isActive;

        void deposit(double amount) {//Deposit method
            balance = balance + amount;
            System.out.println("Deposited UGX " + amount + ". New Balance is " + balance);
        }

        void withdraw(double amount) { //Withdraw method
            balance = balance - amount;
            System.out.println("Withdrawn UGX " + amount + " New Balance is " + balance);
        }

        void printSummary() {
            System.out.println("Account: " + accountNumber + "Owner Name: " + ownerName);
            System.out.println("Balance: UGX " + balance + " Active: " + isActive);
        }

        //Creating methods
        public static void main(String[] args) {
            BankAccount bankAccount = new BankAccount();

            bankAccount.accountNumber = "123456789";
            bankAccount.ownerName = "Waluube Alvin David";
            bankAccount.balance = 38000;
            bankAccount.isActive = true;

            bankAccount.deposit(8000);
            bankAccount.withdraw(1000);
            bankAccount.printSummary();
        }
}


