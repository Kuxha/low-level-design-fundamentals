
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        // 1. Create a Pool with exactly 2 workers.
        // Even if we submit 1000 tasks, only 2 run at a time.
        ExecutorService executor = Executors.newFixedThreadPool(2);

        System.out.println("--- Submitting 5 Tasks ---");

        for (int i = 1; i <= 5; i++) {
            final int taskId = i;

            // 2. Submit the job (Instead of t.start())
            executor.submit(() -> {
                String threadName = Thread.currentThread().getName();
                System.out.println("Task " + taskId + " started by " + threadName);

                try {
                    // Simulate work (2 seconds)
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Task " + taskId + " FINISHED by " + threadName);
            });
        }

        // 3. Close the factory (Stop accepting new tasks)
        // The program won't exit until all submitted tasks are done.
        executor.shutdown();
    }
}