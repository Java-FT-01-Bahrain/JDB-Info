package org.example.synchronization;

public class BankSimulation {
    public static void main(String[] args) throws InterruptedException {
        BankAccount account = new BankAccount(1000);

        // Create multiple threads for deposit and withdrawal
        Thread[] depositThreads = new Thread[10];
        Thread[] withdrawThreads = new Thread[10];

        for (int i = 0; i < 10; i++) {
            depositThreads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    account.depositSyncMethod(1);
                    // account.depositSyncBlock(1);
                    // account.depositWithReentrantLock(1);
                    // account.depositAtomic(1);
                }
            });

            withdrawThreads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    account.withdrawSyncMethod(1);
                    // account.withdrawSyncBlock(1);
                    // account.withdrawWithReentrantLock(1);
                    // account.withdrawAtomic(1);
                }
            });

            depositThreads[i].start();
            withdrawThreads[i].start();
        }

        // Wait for all threads to finish
        for (int i = 0; i < 10; i++) {
            depositThreads[i].join();
            withdrawThreads[i].join();
        }

        // Print the final balance
        System.out.println("Final Balance: " + account.getBalance());
        // System.out.println("Final Balance: " + account.getAtomicBalance());
    }
}
