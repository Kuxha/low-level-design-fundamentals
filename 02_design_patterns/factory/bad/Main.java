package factory.bad;

public class Main {
    public static void main(String[] args) {
        // BAD: The client knows EXACTLY how to create a Car.
        // If the Car constructor changes (e.g., requires 'Engine'), this code breaks.
        Car car = new Car(); 
        car.drive();

        Bike bike = new Bike();
        bike.drive();
    }
}