
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    // 1. Create the Lock (The shared resource guard)
    private static final Lock inventoryLock = new ReentrantLock();

    public static void main(String[] args) {

        Thread shopper1 = new Thread(() -> shop("Shopper 1"));
        Thread shopper2 = new Thread(() -> shop("Shopper 2"));

        shopper1.start();
        shopper2.start();
    }

    public static void shop(String name) {
        System.out.println(name + ": Trying to access inventory...");

        try {
            // 2. THE MAGIC: tryLock()
            // Wait for 2 seconds. If you get the lock, return true.
            // If time runs out, return false.
            boolean acquired = inventoryLock.tryLock(2, TimeUnit.SECONDS);

            if (acquired) {
                try {
                    System.out.println(name + ": ACQUIRED lock! Buying item...");
                    Thread.sleep(3000); // Simulate heavy work (taking longer than the timeout)
                    System.out.println(name + ": Finished shopping.");
                } finally {
                    // 3. CRITICAL: Always unlock in finally
                    System.out.println(name + ": Releasing lock.");
                    inventoryLock.unlock();
                }
            } else {
                // 4. The Backup Plan (No blocking!)
                System.out.println(name + ": COULD NOT acquire lock. Giving up and leaving.");
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}