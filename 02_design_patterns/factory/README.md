# Factory Pattern

## 1. Definition
> "A creational pattern that provides an interface for creating objects in a superclass, but allows subclasses to alter the type of objects that will be created."

In simple terms: It centralizes object creation. Instead of the client using `new`, the client asks a Factory to "make" the object for them.

## 2. The Analogy: The Restaurant Kitchen
* **The Client (Customer):** Orders a "Burger." They do not know (or care) how to grind meat, slice onions, or bake buns. They just want the final object.
* **The Factory (Kitchen):** Takes the order ("Burger") and handles the complex initialization logic.
* **The Result:** The client receives a `Vehicle` (or Burger) ready to use, without knowing the details of its construction.

## 3. The Refactoring Journey

### The "Bad" Way (Tight Coupling)
The client creates objects directly using the `new` keyword.

    // Main.java
    Vehicle v = new Car(); // TIGHT COUPLING!

**Why this is an anti-pattern:**
* **Fragility:** If the `Car` constructor changes (e.g., to `new Car(Engine e, Tire t)`), you must find and update every single file in your codebase where `new Car()` is written.
* **Rigidity:** To switch from `Car` to `Bike`, you have to rewrite the client code. The client knows too much about the specific implementation.

### The "Good" Way (The Simple Factory)
The client delegates creation to a Factory class.

    // Main.java
    Vehicle v = VehicleFactory.getVehicle("car");

**Why this is superior:**
* **Decoupling:** The `Main` class has zero knowledge of the `Car` class constructor. It only knows the `Vehicle` interface.
* **Centralized Logic:** If the logic for creating a `Car` changes, we only edit **one file** (`VehicleFactory`), not the entire application.

## 4. Code Breakdown & Decisions

### The Interface (`Vehicle.java`)
    public interface Vehicle {
        void drive();
    }
* **Reasoning:** We program to an **interface**, not an implementation. This allows the system to be polymorphic. The client treats `Car` and `Bike` exactly the same.

### The Concrete Classes (`Car.java`, `Bike.java`)
    public class Car implements Vehicle { ... }
* **Reasoning:** These are the actual implementations. They remain hidden from the client logic.

### The Factory (`VehicleFactory.java`)
    public class VehicleFactory {
        public static Vehicle getVehicle(String type) {
            if (type.equals("car")) {
                return new Car();
            } else if (type.equals("bike")) {
                return new Bike();
            }
            return null;
        }
    }
* **Reasoning:** This class acts as the **Gatekeeper**. It is the *only* place in the application where the keyword `new` is allowed for Vehicles. It absorbs the complexity so the rest of the app remains clean.

## 5. Architectural Trade-offs & Deep Dive

### The "Localized Complexity" Decision
* **Critique:** The `VehicleFactory` uses conditional logic (`if-else`), which technically violates the Open/Closed Principle (OCP) at the factory level.
* **Justification:** This is a deliberate architectural trade-off.
    * By accepting a violation in this **single factory class**, we eliminate coupling in the consuming modules.
    * The alternative (modification of 100+ client files) is a far greater risk than modifying one central factory.

### Factory Method vs. Abstract Factory
* **Current Implementation:** We utilized the **Simple Factory** idiom for low-overhead object creation.
* **Scalability Note:** If the system expands to require family-based creation (e.g., Windows vs. Mac UI components), this should be refactored into an **Abstract Factory**. For current requirements, a Simple Factory prevents over-engineering.

### Dependency Injection (DI) Note
* **Production Context:** In a Spring environment, this static factory would likely be replaced by a DI Container to facilitate better unit testing and mocking. This manual implementation demonstrates the underlying mechanics often hidden by those frameworks.