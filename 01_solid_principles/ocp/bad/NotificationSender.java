package ocp.bad;

public class NotificationSender {

    public void send(String type, String message) {
        if (type.equals("email")) {
            System.out.println("Sending Email: " + message);
        } else if (type.equals("sms")) {
            System.out.println("Sending sms: " + message);
        }
        // now what if we want to add push notification
        // we have to open thi sfile, modify sen dmnoethod , and add anoter if else
    }

}
