package concurrency.race;

public class BankAccount {
    private int balance;

    public void addMoney() {
        for (int i = 0; i < 10000; i++) {
            balance++;
        }
    }

    public int getBalance() {
        return balance;
    }

}
