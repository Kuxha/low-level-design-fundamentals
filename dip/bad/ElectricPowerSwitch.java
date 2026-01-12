package dip.bad;

public class ElectricPowerSwitch {
    // BAD: We are dependent on a concrete class, not an interface.
    // If you want to switch to a Fan, you have to rewrite this entire file.
    public LightBulb lightBulb;
    public boolean on;

    public ElectricPowerSwitch() {
        // BAD: The hidden "new".
        // We are forcing this switch to ONLY work with LightBulbs.
        // This is "soldering the lamp to the wall."
        this.lightBulb = new LightBulb();
        this.on = false;
    }

    public boolean isOn() {
        return this.on;
    }

    public void press() {
        boolean checkOn = isOn();
        if (checkOn) {
            lightBulb.turnOff();
            this.on = false;
        } else {
            lightBulb.turnOn();
            this.on = true;
        }
    }
}
