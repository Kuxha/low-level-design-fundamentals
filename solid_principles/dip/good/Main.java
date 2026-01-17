package dip.good;

public class Main {
    public static void main(String[] args) {
        // 1. Create the Low-Level Module (Fan)
        Switchable fan = new Fan();

        // 2. Inject it into the High-Level Module (Switch)
        ElectricPowerSwitch fanSwitch = new ElectricPowerSwitch(fan);

        // 3. Use it
        fanSwitch.press(); // Output: Fan is on
        fanSwitch.press(); // Output: Fan is off

        // 4. DIP Power Move: Swap for a LightBulb without changing the Switch code!
        Switchable bulb = new LightBulb();
        ElectricPowerSwitch bulbSwitch = new ElectricPowerSwitch(bulb);
        bulbSwitch.press();
    }
}