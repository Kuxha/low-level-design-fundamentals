package concurrency.deadlock;

public class Main {

    public static final Object lock1 = new Object();
    public static final Object lock2 = new Object();

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("Thread 1 :holding lock 1");
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                }

                System.out.println("watiing for lock 2");

                synchronized (lock2) {
                    System.out.println("Thread 1 wins");
                }
            }
        });

        Thread t2 = new Thread(() -> {

            synchronized (lock2) {

                System.out.println("thread 2 callinglock 2");

                try {

                    Thread.sleep(100);

                } catch (Exception e) {
                    // TODO: handle exception
                }

                System.out.println("watiing for lock 1");

                synchronized (lock1) {
                    System.out.println("Thread 2 wins");
                }
            }

        });

        t1.start();
        t2.start();
    }
}
