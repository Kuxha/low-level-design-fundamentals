package decorator.good;

public class Sugar extends CoffeeDecorator {

    public Sugar(Coffee c) {
        super(c);
    }

    public double getCost() {
        return super.getCost() + 0.5;
    }
}