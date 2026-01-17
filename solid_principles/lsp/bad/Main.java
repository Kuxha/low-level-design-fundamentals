package lsp.bad;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Bird> birds = new ArrayList<>();
        birds.add(new Swan());
        birds.add(new Penguin());

        for (Bird b : birds) {
            b.fly(); // CRASH! The program dies when it hits Penguin.
        }
    }
}
