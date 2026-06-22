package week5;

class Notifications {
    public void send(){
        System.out.println("Sending...");
    }
}

class SmsNotification extends Notifications{
    @Override
    public void send() {
        System.out.println("Sending SMS Notification");
    }
}

class EmailNotification extends Notifications{
    @Override
    public void send(){
        System.out.println("Sending Email Notification");
    }
}

class PushNotification extends Notifications{
    @Override
    public void send(){
        System.out.println("Sending Push Notification....");
    }
}

public class Main1 {
    public static void main(String[] args){
        Notifications[] notifications = {new EmailNotification(),new PushNotification(), new SmsNotification()};
        for(Notifications notification : notifications){
            notification.send();
        }

    }
}