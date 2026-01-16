package decorator.good;

public abstract class CoffeeDecorator implements Coffee {
    // 1. Change to protected so children (Milk/Sugar) can use it.
    protected Coffee coffee;

    public CoffeeDecorator(Coffee c) {
        this.coffee = c;
    }

    // Default implementation just passes the call through
    public double getCost() {
        return coffee.getCost();
    }

    public String getDescription() {
        return coffee.getDescription();
    }
}