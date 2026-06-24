package org.example;

public class MobileMoney {
    private String phoneNumber;
    private String ownerName;
    private double balance;
    private int failedPinAttempts;
    private boolean isLocked;

    public MobileMoney(String phoneNumber, String ownerName, double balance, int failedPinAttempts, boolean isLocked) {
        this.phoneNumber = phoneNumber;
        this.ownerName = ownerName;
        this.balance = balance;
        this.failedPinAttempts = failedPinAttempts;
        this.isLocked = isLocked;
    }

    //phone number getter
    public String getPhoneNumber() {
        return phoneNumber;
    }

    //owner name getter
    public String getOwnerName() {
        return ownerName;
    }

    //balance getter
    public double getBalance() {
        return balance;
    }

    //failed pin attempts getter
    public int getFailedPinAttempts() {
        return failedPinAttempts;
    }

    //is locked getter
    public boolean isLocked() {
        return isLocked;
    }

    //owner Name setter
    public void setOwnerName(String ownerName) {
        if (ownerName == null || ownerName.length() == 0) {
            System.out.println("owner name is null");
        } else {
            this.ownerName = ownerName;
        }
    }

    public void receiveMoney(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("You have received amount " + amount + " and you new balance is " + balance);
        } else {
            System.out.println("Amount must be positive");
        }
    }

    public void sendMoney(double amount) {
        if (!isLocked &&  amount > 0 && balance >= amount) {
                balance -= amount;
                System.out.println("You have successfully sent UGX: " + amount);
        }

        if (isLocked) {
            System.out.println("Account is locked");
            return;
        }
    }

    public void recordFailedAttempts() {
        failedPinAttempts++;

        if (failedPinAttempts >= 3) {
            isLocked = true;
        System.out.println("Account is locked");
        }
    }

    public static void main(String[] args) {
        MobileMoney mtn = new MobileMoney("0758862363", "Waluube Alvin David", 100000, 2, false);

        mtn.recordFailedAttempts();

        System.out.println(mtn.isLocked());

        mtn.setOwnerName("Naikooba Drena");
        System.out.println(mtn.getOwnerName());
    }
}