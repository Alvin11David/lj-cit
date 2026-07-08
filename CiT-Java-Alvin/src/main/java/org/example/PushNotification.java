package org.example;

public class PushNotification extends Notification {

    @Override
    public void send() {
        System.out.println("Sending notification via push");
    }
}
