package org.example;

public class MobileMoney {
    // Requirements: All fields must be private
    private String phoneNumber;
    private String ownerName;
    private double balance;
    private int failedPinAttempts = 0;
    private boolean isLocked = false;

    public MobileMoney(String phoneNumber, String ownerName, double initialBalance) {
        this.phoneNumber = phoneNumber;
        this.setOwnerName(ownerName);
        this.balance = initialBalance;
    }

    // phoneNumber: getter only
    public String getPhoneNumber() {
        return phoneNumber;
    }

    // ownerName: getter and setter (rejects null or blank)
    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        if (ownerName != null && !ownerName.isBlank()) {
            this.ownerName = ownerName;
        } else {
            System.out.println("Error: Owner name cannot be null or blank.");
        }
    }

    // balance: getter only
    public double getBalance() {
        return balance;
    }

    // failedPinAttempts: getter only
    public int getFailedPinAttempts() {
        return failedPinAttempts;
    }

    // isLocked: getter only
    public boolean isLocked() {
        return isLocked;
    }

    // Method to record failed attempts and lock account at 3
    public void recordFailedAttempt() {
        failedPinAttempts++;
        if (failedPinAttempts >= 3) {
            isLocked = true;
            System.out.println("Account LOCKED due to too many failed attempts.");
        }
    }

    public void sendMoney(double amount) {
        if (isLocked) {
            System.out.println("Transaction failed: Account is locked.");
        } else if (amount <= 0) {
            System.out.println("Transaction failed: Insufficent Balance. Current Balance:"+ balance);
        } else if (amount > balance) {
            System.out.println("Transaction failed: Insufficient balance. Current Balance:"+ balance);
        } else {
            balance -= amount;
            System.out.println("Successfully sent: " + amount + ". New balance: " + balance);
        }
    }

    public void receiveMoney(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Received: " + amount + ". New balance: " + balance);
        }
    }

    public static void main(String[] args) {
        MobileMoney myAcc = new MobileMoney("0770000000", "Alvin", 5000.0);

        // Test normal transaction
        myAcc.sendMoney(2000.0);

        // Test failed attempts and locking
        myAcc.recordFailedAttempt();
        myAcc.recordFailedAttempt();
        myAcc.recordFailedAttempt(); // Should lock here

        // Test transaction after locking
        myAcc.sendMoney(500.0);
    }
}