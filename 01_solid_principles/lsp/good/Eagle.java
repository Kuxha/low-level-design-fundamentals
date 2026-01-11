package lsp.good;

public class Eagle implements Bird, Flyable {

    @Override
    public void fly() {
        System.out.println("flying");
    }

    @Override
    public void eat() {
        System.out.println("Eating");
    }

}
