
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        System.out.println("--- Starting Dashboard Loading ---");

        // 1. Kick off Task 1 (Fetch User)
        // supplyAsync = "Run this on a background thread and return a value"
        CompletableFuture<String> userFuture = CompletableFuture.supplyAsync(() -> {
            simulateDelay(1); // 1 second
            System.out.println("Fetched User Profile on: " + Thread.currentThread().getName());
            return "User: Laba";
        });

        // 2. Kick off Task 2 (Fetch Orders) - concurrently!
        CompletableFuture<String> ordersFuture = CompletableFuture.supplyAsync(() -> {
            simulateDelay(2); // 2 seconds
            System.out.println("Fetched Orders on: " + Thread.currentThread().getName());
            return "Orders: [Book, Laptop]";
        });

        // 3. Combine the results when BOTH are done
        // This line doesn't wait. It just defines the "Recipe".
        CompletableFuture<String> dashboardFuture = userFuture.thenCombine(ordersFuture, (user, orders) -> {
            return user + " | " + orders;
        });

        // 4. NOW we wait for the final result (Blocking only at the end)
        String finalDashboard = dashboardFuture.join();

        long endTime = System.currentTimeMillis();

        System.out.println("--- Final Dashboard ---");
        System.out.println(finalDashboard);
        System.out.println("Total Time: " + (endTime - startTime) + "ms");
    }

    // Helper to simulate DB latency
    private static void simulateDelay(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}