package concurrency.basics;

public class SequentialMain {
    public static void main(String[] args) {
        // Task 1: Download
        for (int i = 0; i < 5; i++) {
            System.out.println("Downloading File... " + i + "%");
        }

        // Task 2: Play Music
        // This CANNOT start until the download loop finishes.
        for (int i = 0; i < 5; i++) {
            System.out.println("Playing Music... ");
        }
    }
}