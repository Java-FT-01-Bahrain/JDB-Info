package org.example;

import org.example.bank.NonConcurrentBankAccount;

public class NonConcurrentDemo3 {
    public static void main(String[] args) {
        NonConcurrentBankAccount account = new NonConcurrentBankAccount(1000);

        Runnable task1 = () -> {
            for (int i = 0; i < 100; i++) {
                account.deposit(10);
                System.out.println("Thread 1: Deposited $10. Balance: " + account.getBalance());
            }
        };

        Runnable task2 = () -> {
            for (int i = 0; i < 100; i++) {
                account.withdraw(10);
                System.out.println("Thread 2: Withdrawn $10. Balance: " + account.getBalance());
            }
        };

        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);

        thread1.start();
        thread2.start();
    }
}
