package dip.good;

public class LightBulb implements Switchable {
    private boolean light_status;

    @Override
    public void turnOn() {
        // TODO Auto-generated method stub
        light_status = true;
        System.out.println("Fan is on");
    }

    @Override
    public void turnOff() {
        light_status = false;
        // TODO Auto-generated method stub
        System.out.println("Fan is off");
    }

}
