package decorator.bad;

// BAD: What if I want Double Sugar? Or Sugar without Milk?
// We can't keep making classes for every combo now. What if i want milk only without coffee ._.
public class MilkAndSugarCoffee extends Coffee {
    @Override
    public double getCost() {
        return super.getCost() + 1.5;
    }
}