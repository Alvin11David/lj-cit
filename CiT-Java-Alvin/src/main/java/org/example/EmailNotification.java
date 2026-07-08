package org.example;

public class EmailNotification extends Notification {

    @Override
    public void send() {
        System.out.println("Sending notification via email");
    }
}
