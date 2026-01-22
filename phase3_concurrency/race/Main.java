package concurrency.race;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // 1. The Shared Resource (The Vault)

        // 2. The Task (The Job Description)
        // We use a Lambda: "Hey Thread, go call addMoney() on the bank object."
        // 1. Create a task class that knows about the bank
        class AddMoneyTask implements Runnable {
            BankAccount bank;

            AddMoneyTask(BankAccount b) {
                this.bank = b;
            }

            public void run() {
                bank.addMoney();
            }
        }

        // 3. The Workers (The Robbers)
        BankAccount bank = new BankAccount();
        AddMoneyTask task = new AddMoneyTask(bank); // Give the worker the vault key
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        // 4. Start the heist
        t1.start();
        t2.start();

        // 5. Wait for them to finish (Join)
        t1.join();
        t2.join();

        // 6. Inspect the damage
        System.out.println("Expected: 20000");
        System.out.println("Actual:   " + bank.getBalance());
    }
}