package org.example.synchronization;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private double balance;
    private final Lock lock = new ReentrantLock();
    private final AtomicInteger atomicBalance = new AtomicInteger(1000);

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    // Synchronized method to deposit money
    public synchronized void depositSyncMethod(double amount) {
        balance += amount;
    }

    // Synchronized method to withdraw money
    public synchronized void withdrawSyncMethod(double amount) {
        balance -= amount;
    }

    // Synchronized block to deposit money
    public void depositSyncBlock(double amount) {
        synchronized (this) {
            balance += amount;
        }
    }

    // Synchronized block to withdraw money
    public void withdrawSyncBlock(double amount) {
        synchronized (this) {
            balance -= amount;
        }
    }

    // ReentrantLock to deposit money
    public void depositWithReentrantLock(double amount) {
        lock.lock();
        try {
            balance += amount;
        } finally {
            lock.unlock();
        }
    }

    // ReentrantLock to withdraw money
    public void withdrawWithReentrantLock(double amount) {
        lock.lock();
        try {
            balance -= amount;
        } finally {
            lock.unlock();
        }
    }

    // Atomic operations to deposit money
    public void depositAtomic(double amount) {
        atomicBalance.addAndGet((int) amount);
    }

    // Atomic operations to withdraw money
    public void withdrawAtomic(double amount) {
        atomicBalance.addAndGet((int) -amount);
    }

    // Get balance using synchronized method
    public synchronized double getBalance() {
        return balance;
    }

    // Get balance using atomic operations
    public double getAtomicBalance() {
        return atomicBalance.doubleValue();
    }
}
