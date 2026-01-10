package ocp.good;

public class Main {
    public static void main(String[] args) {
        NotificationSender sender = new NotificationSender();

        Notification email = new EmailNotification();
        sender.sendNotification(email, "This is an email");

        Notification sms = new SMSNotification();
        sender.sendNotification(sms, "This is a SMS");

        // if we ever need to have a new notiifaction
        // just create a new class . eg WhatsappNotification.java that implements
        // Notificatio interface
        // and we can use it as Notification whatsapp = new WhatsappNotification()
        // and use it simply as sender.sendNotifaction(whatsapp,"whatsapp");
    }
}
