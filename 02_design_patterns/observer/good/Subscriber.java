public class Subscriber implements Observer {

    private String name;

    Subscriber(String name) {
        this.name = name;
    }

    @Override
    public void update(String videoTitle) {

        System.out.println("Subscriber : " + name + ", notification: New video uploaded: " + videoTitle);
    }

}
