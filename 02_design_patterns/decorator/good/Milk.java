package decorator.good;

public class Milk extends CoffeeDecorator {

    public Milk(Coffee c) {
        super(c);
    }

    public double getCost() {
        return super.getCost() + 1.0;
    }

}