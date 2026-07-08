package org.example;

public class SmsNotification extends Notification {

    @Override
    public void send() {
        System.out.println("Sending notification via email");
    }
}
