package Week_5.Exercise_1;

public class NotificationFactory {


    private NotificationFactory() {}

    public static Notification create(String channel) {
        if (channel == null || channel.trim().isEmpty()) {
            throw new IllegalArgumentException("Notification channel cannot be null or empty.");
        }

        switch (channel.toUpperCase()) {
            case "EMAIL": return new EmailNotification();
            case "SMS":   return new SmsNotification();
            case "PUSH":  return new PushNotification();
            default:
                throw new IllegalArgumentException(
                        "Unknown notification channel: '" + channel + "'. Valid options are: EMAIL, SMS, PUSH."
                );
        }
    }
}
