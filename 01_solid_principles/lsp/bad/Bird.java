package lsp.bad;

import java.util.ArrayList;

public class Bird {
    public void fly() {
        System.out.println("I am flying high!");
    }
}

class Swan extends Bird {
    // Inherits fly() - Works perfectly.
}

class Penguin extends Bird {
    @Override
    public void fly() {
        throw new UnsupportedOperationException("Help! I cannot fly!");
    }
}
