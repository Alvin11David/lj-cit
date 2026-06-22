package week5.exercise1;

public class SmsNotification extends Notification {
    @Override
    public void send() {
        System.out.println("This is via SMS Notification...");
    }
}
