package ocp.good;

public class NotificationSender {
    // There is no if else here
    // we do not ask if its an email or what
    // we just say send youself
    // whatever comes in notification will call its own function
    public void sendNotification(Notification notification, String message) {
        notification.send(message);
    }

}
