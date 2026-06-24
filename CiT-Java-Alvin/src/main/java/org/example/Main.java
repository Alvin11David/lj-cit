package org.example;

public class Main {
    /*public static void main(String[] args) {
        EmailNotification email = new EmailNotification();
        SmsNotification sms = new SmsNotification();
        PushNotification push = new PushNotification();

        Notification[] notifications = {
                new EmailNotification(),
                new SmsNotification(),
                new PushNotification()
        };

        for (Notification notification : notifications) {
            notification.send();
        }
    }*/

    public static void main(String[] args) {
        SalariedEmployee employee1 = new SalariedEmployee("Alvin", 2000000);
        HourlyEmployee employee2 = new HourlyEmployee("David", 12, 20.3);

        employee1.clockIn();
        System.out.println(employee1.calculateMonthlyPay());
        System.out.println(employee1.calculateTax());
        System.out.println(employee1.getName());

        employee2.clockIn();
        System.out.println(employee2.calculateMonthlyPay());
        System.out.println(employee2.calculateTax());
        System.out.println(employee2.getName());


    }
}