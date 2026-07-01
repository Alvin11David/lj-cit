package Week_5.Exercise_1;


class EmailNotification extends Notification {

    @Override
    public void send(String message) {
        System.out.println("[EMAIL] " + message);
    }
}