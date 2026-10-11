package org.example.racecondition;

public class RaceConditionSimulation {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000);

        // Create multiple threads for deposit and withdrawal
        Thread depositThread = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                account.deposit(1);
            }
        });

        Thread withdrawThread = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                account.withdraw(1);
            }
        });

        // Start the threads
        depositThread.start();
        withdrawThread.start();

        // Wait for threads to finish
        try {
            depositThread.join();
            withdrawThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print the final balance
        System.out.println("Final Balance: " + account.getBalance());
    }
}
