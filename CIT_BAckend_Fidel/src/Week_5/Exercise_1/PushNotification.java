package Week_5.Exercise_1;

class PushNotification extends Notification {

    @Override
    public void send(String message) {
        System.out.println("[PUSH] " + message);
    }
}