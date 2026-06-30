package Week_5.Exercise_1;

public class Main {

    public static void main(String[] args) {


        Notification[] notifications = {
                NotificationFactory.create("EMAIL"),
                NotificationFactory.create("SMS"),
                NotificationFactory.create("PUSH")
        };

        String message = "Fidel JOnes is innit .";


        for (Notification n : notifications) {
            n.send(message);
        }
    }
}




