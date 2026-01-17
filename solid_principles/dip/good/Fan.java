package dip.good;

public class Fan implements Switchable {
    private boolean fan_status;

    @Override
    public void turnOn() {
        // TODO Auto-generated method stub
        fan_status = true;
        System.out.println("Fan is on");
    }

    @Override
    public void turnOff() {
        fan_status = false;
        // TODO Auto-generated method stub
        System.out.println("Fan is off");
    }

}
