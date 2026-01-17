# Decorator Pattern

## 1. Definition
> "Attach additional responsibilities to an object dynamically. Decorators provide a flexible alternative to subclassing for extending functionality."

## 2. The Analogy: The Russian Doll (or Starbucks)
* **The Core:** A basic cup of Coffee ($5).
* **The Wrappers:** Milk ($1), Sugar ($0.5), Whip ($0.2).
* **The Mechanism:** You don't create a `CoffeeWithMilkAndSugar` class. You take the Coffee object and "wrap" it inside a Milk object, then wrap *that* inside a Sugar object.
* **The Result:** The outer "Sugar" object looks like a Coffee, but when you ask for the cost, it adds its own price before asking the inner object.

## 3. The Refactoring Journey

### The "Bad" Way (Inheritance Explosion)
Using standard inheritance to add features.

    public class MilkCoffee extends Coffee { ... }
    public class SugarCoffee extends Coffee { ... }
    public class MilkAndSugarCoffee extends Coffee { ... } // EXPLOSION

**Why this fails:**
* **Class Explosion:** For N features, you need 2^N subclasses to cover every combination.
* **Rigidity:** You cannot add "Double Milk" easily without making a `DoubleMilkCoffee` class.

### The "Good" Way (The Decorator)
Using Composition (Wrappers) instead of Inheritance.

    Coffee c = new SimpleCoffee();
    c = new Milk(c);  // Wrap it
    c = new Sugar(c); // Wrap it again
    System.out.println(c.getCost()); // 6.5

**Why this is superior:**
* **Dynamic:** You can add/remove features at runtime (e.g., user clicks a checkbox).
* **Stackable:** You can wrap an object 10 times (e.g., `new Milk(new Milk(c))`) without new classes.

## 4. Execution Flow (The Trace)

When you run `new Sugar(new Milk(new SimpleCoffee())).getCost()`, the following chain reaction occurs:

1.  **Call:** `Sugar.getCost()`
    * *Action:* Adds **$0.50** + calls `super.getCost()` (the inner object).
2.  **Call:** `Milk.getCost()`
    * *Action:* Adds **$1.00** + calls `super.getCost()` (the inner object).
3.  **Call:** `SimpleCoffee.getCost()`
    * *Action:* Returns **$5.00** (Base Case).
4.  **Return:** `Milk` receives $5.00, adds $1.00, returns **$6.00**.
5.  **Return:** `Sugar` receives $6.00, adds $0.50, returns **$6.50**.

**Key Concept:** The request goes *down* the stack to the core, and the cost accumulates *up* the stack as it returns.

## 5. Code Breakdown

### The Abstract Decorator (`CoffeeDecorator.java`)
    public abstract class CoffeeDecorator implements Coffee {
        protected Coffee coffee; // PROTECTED: Visible to children

        public CoffeeDecorator(Coffee c) {
            this.coffee = c;
        }

        public double getCost() {
            return coffee.getCost(); // Default: Pass to inner
        }
    }
* **Role:** It acts as the "Middleman." It holds the inner object and defines the baseline behavior (just passing the call along).

### The Concrete Decorator (`Milk.java`)
    public class Milk extends CoffeeDecorator {
        public Milk(Coffee c) {
            super(c); // Pass to Dad
        }

        public double getCost() {
            return super.getCost() + 1.0; // My Logic + Inner Logic
        }
    }
* **Role:** It adds the specific behavior (Price + $1.0) and uses `super` to trigger the chain reaction.

## 6. Architectural Trade-offs
* **Complexity:** Decorators can result in "Small Object" proliferation. Debugging a chain of 10 wrappers can be annoying because the stack trace is deep.
* **Identity Crisis:** A `Milk` wrapper *is* a `Coffee`, but if you rely on `instanceof SimpleCoffee`, it will fail. Decorators hide the true identity of the core object.