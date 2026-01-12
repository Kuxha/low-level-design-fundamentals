package dip.good;

public class ElectricPowerSwitch {
    // FIX 1: Rename 'switch' to 'device'
    private Switchable device;
    private boolean on;

    // FIX 2: Constructor Injection (The Heart of DIP)
    public ElectricPowerSwitch(Switchable device) {
        this.device = device;
        this.on = false;
    }

    // New Feature: Actually pressing the button
    public void press() {
        boolean checkOn = this.on;
        if (checkOn) {
            device.turnOff();
            this.on = false;
        } else {
            device.turnOn();
            this.on = true;
        }
    }
}