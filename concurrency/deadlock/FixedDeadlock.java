package concurrency.deadlock;

public class FixedDeadlock {
    public static final Object lock1 = new Object();
    public static final Object lock2 = new Object();

    public static void main(String[] args) {

        // Thread 1: Lock 1 -> Lock 2
        Thread t1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("Thread 1: Holding Lock 1...");
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                }

                System.out.println("Thread 1: Waiting for Lock 2...");
                synchronized (lock2) {
                    System.out.println("Thread 1: Win!");
                }
            }
        });

        // Thread 2: FIXED! We changed the order to match Thread 1.
        // Lock 1 -> Lock 2
        Thread t2 = new Thread(() -> {
            // Wait here if T1 already has Lock 1. Don't grab Lock 2 yet!
            synchronized (lock1) {
                System.out.println("Thread 2: Holding Lock 1...");
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                }

                System.out.println("Thread 2: Waiting for Lock 2...");
                synchronized (lock2) {
                    System.out.println("Thread 2: Win!");
                }
            }
        });

        t1.start();
        t2.start();
    }
}