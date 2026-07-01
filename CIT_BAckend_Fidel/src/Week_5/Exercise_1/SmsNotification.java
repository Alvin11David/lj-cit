package Week_5.Exercise_1;

class SmsNotification extends Notification {

    @Override
    public void send(String message) {
        System.out.println("[SMS] " + message);
    }
}