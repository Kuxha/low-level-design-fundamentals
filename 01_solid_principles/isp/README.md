# Interface Segregation Principle (ISP)

## Definition
> "Clients should not be forced to depend on methods they do not use."

## The "Fat Interface" Problem (Analogy)
Imagine a "Universal Remote" that has buttons for TV, Netflix, PlayStation, and Fan.
* If you buy a cheap Kitchen TV, it shouldn't be forced to have a "PlayStation" button that does nothing.
* Creating massive, "do-it-all" interfaces forces implementing classes to write empty or dummy methods for features they don't support.

## What We Refactored

### The "Bad" Way (The God Interface)
We created a single `RestaurantEmployee` interface.

    public interface RestaurantEmployee {
        void cookFood();
        void serveCustomers();
        void washDishes();
    }

The Problem:
* If we hire a Waiter, they implement `RestaurantEmployee`.
* The compiler forces the Waiter to implement `cookFood()`.
* This is dangerous (Waiters shouldn't cook) and messy (we have to write `throw new Exception` or leave it empty).

### The "Good" Way (Segregation)
We split the roles into specific interfaces.
1. Interface `ChefInterface`: Has `cook()`.
2. Interface `WaiterInterface`: Has `take_order()`.

Result:
* The `Waiter` class only implements `WaiterInterface`. It has zero knowledge of cooking.
* The `Chef` class only implements `ChefInterface`.
* The code is safer because we cannot accidentally ask a Waiter to cook.