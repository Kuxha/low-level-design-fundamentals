public class Main {
    public static void main(String[] args) {

        NavigationContext navigation = new NavigationContext();
        CarStrategy car = new CarStrategy();
        navigation.setStrategy(car);
        navigation.executeRoute();

        BikeStrategy bike = new BikeStrategy();
        navigation.setStrategy(bike);
        navigation.executeRoute();
    }
}
