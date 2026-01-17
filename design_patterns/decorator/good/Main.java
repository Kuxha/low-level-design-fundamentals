
public class Main {
    public static void main(String[] args) {

        SimpleCoffee coffee = new SimpleCoffee();
        Milk milk = new Milk(coffee);
        Sugar sugar = new Sugar(milk);
        System.out.println(new Sugar(new Milk(coffee)).getCost());
        System.out.println(sugar.getCost());

    }
}
