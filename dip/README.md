# Dependency Inversion Principle (DIP)

## Definition
> "High-level modules should not depend on low-level modules. Both should depend on abstractions."

## The "Senior" Translation
**Don't solder the lamp directly to the wall.**
* **Bad (Soldering):** If the lamp breaks, you have to rip open the wall to replace it.
* **Good (Plug & Socket):** You use a standard Plug (Interface). You can swap the lamp for a fan, a toaster, or a new lamp without touching the wall.

## The "Code Smell": The Hidden `new`
If you see the keyword `new` inside a class constructor, you are likely violating DIP. You are "hardcoding" a dependency.

## What We Refactored

### The "Bad" Way (Hardwiring)
The Switch creates the Fan itself.

    public class ElectricPowerSwitch {
        private Fan fan;
        public ElectricPowerSwitch() {
            // VIOLATION: We are "soldering" the Fan to the Switch.
            // We cannot use this switch for a LightBulb.
            this.fan = new Fan();
        }
    }

### The "Good" Way (Dependency Injection)
The Switch asks for a device.

    public class ElectricPowerSwitch {
        private Switchable device;
    
        // GOOD: We ask for the dependency. 
        // The Switch doesn't care if it's a Fan or a LightBulb.
        public ElectricPowerSwitch(Switchable device) {
            this.device = device;
        }
    }

## Real-World Use Case: Spring Boot & Go
* **Spring Boot:** Uses "Dependency Injection" (IoC). You never write `new UserService()`. You simply ask for it in the constructor (`@Autowired`), and the framework injects it.
* **Golang:** You define interfaces for your Database or API Client and pass them into your `NewServer(db Database)` function in `main.go`.