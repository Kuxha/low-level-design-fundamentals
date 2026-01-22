package decorator.bad;

public class MilkCoffee extends Coffee {
    @Override
    public double getCost() {
        return super.getCost() + 1.0;
    }
}