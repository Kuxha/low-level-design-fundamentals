package isp.good;

public class Main {
    public static void main(String[] args) {

        Chef chef = new Chef();
        chef.cook();

        Waiter waiter = new Waiter();
        waiter.take_order();
    }
}
