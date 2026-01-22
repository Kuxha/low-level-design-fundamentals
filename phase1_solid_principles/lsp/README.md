# Liskov Substitution Principle (LSP)

## Definition
> "Subtypes must be substitutable for their base types."

So basically ,if you have a function that accepts a Parent Class (e.g., Bird), you should be able to pass ANY Child Class (e.g., Penguin) into it without the code crashing or behaving weirdly.

## The "Duck Test" Analogy
> "If it looks like a Duck, quacks like a Duck, but needs batteries... you have the wrong abstraction."

If a child class needs to throw an exception to "turn off" a feature of the parent (like a Penguin throwing an exception for `fly()`), it violates LSP.

## What We Refactored

### The "Bad" Way
Forcing the Penguin to lie.

    class Penguin extends Bird {
        @Override
        public void fly() {
            throw new Exception("I can't fly!");
        }
    }

Risk: If you loop through List<Bird> and call .fly(), the app crashes in production because the Penguin lied about being a "flying" bird.

### The "Good" Way (Separation)
We separated the capabilities into different interfaces.
1. Interface Bird: Has eat(). (Everyone does this).
2. Interface Flyable: Has fly(). (Only some do this).
3. Classes:
   * Eagle implements Bird, Flyable
   * Penguin implements Bird

Result:
* You can safely call eat() on any Bird.
* The compiler prevents you from calling fly() on a Penguin.
* We replaced a Runtime Crash with a Compile-Time Safety Check.