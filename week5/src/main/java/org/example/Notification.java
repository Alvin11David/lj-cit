package org.example;

class Notification {
    String message;

    public Notification(String message){
        this.message = message;
    }

    public void send(){
        System.out.println(message + " ----> Is from the Notification class");
    }
}

class EmailNotification extends Notification{
    public EmailNotification(String message){super(message);}

    @Override
    public void send(){
        System.out.println(message + " ---->  Is from the EmailNotification class");
    }
}




class SMSNotification extends Notification {
    public SMSNotification(String message){super (message);}

    @Override
    public void send(){
        System.out.println(message + " ----> Is from SMSNotifiaction class");
    }
}


class PushNotifications extends Notification{
    public PushNotifications(String message){super(message);}


    @Override
    public void send() {
        System.out.println(message + " ----> Is from the PushNotifications class");
    }
}

class TestOutPolymorphism{
    public static void main(String[] args){
        Notification[] notificationsList = {
                new Notification("Tagoole Mugwa David"),
                new EmailNotification("CIT is cool"),
                new SMSNotification("Spring Boot is on the way"),
                new PushNotifications("Java development...")
        };

        for (Notification n: notificationsList){
            n.send();
        }
    }
}
