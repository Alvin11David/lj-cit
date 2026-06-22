package week5.exercise1;

public class Main {
    public static void main(String[] args) {
        Notification[] notification = {
                new EmailNotification(),
                new PushNotification(),
                new SmsNotification()
        };

        for (Notification notifications : notification) {
            notifications.send();
        }
    }
}
