package Week_5.Exercise_1;

public class Main {
    public static void main(String[] args) {

                Notification[] notifications = {
                new EmailNotification(),
                new SmsNotification(),
                new PushNotification()
        };


        for (Notification n : notifications) {
            n.send();
        }
    }
}

