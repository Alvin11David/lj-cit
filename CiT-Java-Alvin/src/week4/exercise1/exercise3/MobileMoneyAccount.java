package week4.exercise1.exercise3;

public class MobileMoneyAccount {
    private String phoneNumber;
    private String ownerName;
    private double balance;
    private int failedPinAttempts;
    private boolean isLocked;

    public MobileMoneyAccount(String phoneNumber, String ownerName, double balance) {
        this.phoneNumber = phoneNumber;
        this.ownerName = ownerName;
        this.balance = balance;
        this.failedPinAttempts = 0;
        this.isLocked = false;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public double getBalance() {
        return balance;
    }

    public int getFailedPinAttempts() {
        return failedPinAttempts;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setOwnerName(String ownerName) {
        if(ownerName.trim().isEmpty() && ownerName != null) {
            this.ownerName = ownerName;
        } else {
            System.out.println("Owner name cannot be blank.");
        }
    }


    public void sendMoney(double amount) {
        if (!isLocked) {
            if (amount >= 0 && amount <= balance) {
                balance -= amount;
                System.out.println("Sent amount UGX: " + amount + " to " + getOwnerName());
            }
        }
    }

    public void receiveMoney(double amount) {
        if (!isLocked) {
            balance += amount;
            System.out.println("You have received " + amount + " to " + getOwnerName());
        }
    }

    public void recordFailedAttempt() {
        failedPinAttempts++;
        if (failedPinAttempts >= 3) {
            isLocked = true;
            System.out.println("Account " + getOwnerName() + " has been locked.");
        } else {
            isLocked = false;
        }
    }

    public static void main(String[] args) {
        MobileMoneyAccount mtn = new MobileMoneyAccount("0758862363", "Alvin David", 780000);
        System.out.println(mtn.getOwnerName());
        mtn.recordFailedAttempt();
        mtn.recordFailedAttempt();
        mtn.recordFailedAttempt();

        mtn.sendMoney(10000);
        System.out.println(mtn.getBalance());
    }
}
