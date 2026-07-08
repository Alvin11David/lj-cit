package week3.exercise1.exercise2;

public class MobileMoneyValidator {
    int senderBalance = 150000;
    int transferAmount = 50000;
    boolean senderIsVerified = true;
    boolean receiverExists = true;

    public void validateTransaction() {
    if (transferAmount <= 0) {
        System.out.println("Error: Transfer amount must be greater.");
    }  else if(!senderIsVerified) {
        System.out.println("Error: Sender account is not verified.");
    } else if(!receiverExists) {
        System.out.println("Error: Receiver not found.");
    } else if (senderBalance < transferAmount) {
        System.out.println("Error: Insufficient funds.");
    } else {
        System.out.println("Transfer successful.");
        System.out.println("New balance is: " + senderBalance);
    }
    }

    public static void main(String[] args) {
        MobileMoneyValidator validator = new MobileMoneyValidator();

        validator.validateTransaction();
    }
}
