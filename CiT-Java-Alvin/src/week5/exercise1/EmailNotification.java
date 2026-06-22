package week5.exercise1;

public class EmailNotification extends Notification {
    @Override
    public void send() {
        System.out.println("This is a notification via Email...");
    }
}
