package org.example.codealong;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;

public class LockTimeoutExample {
    private final Lock lock1 = new ReentrantLock();
    private final Lock lock2 = new ReentrantLock();

    public void tryToLockBothLocks() throws InterruptedException {
        boolean lock1Acquired = false;
        boolean lock2Acquired = false;

        try {
            // Try to acquire both locks without waiting indefinitely
            lock1Acquired = lock1.tryLock(1000, TimeUnit.MILLISECONDS);
            lock2Acquired = lock2.tryLock(1000, TimeUnit.MILLISECONDS);

            if (lock1Acquired && lock2Acquired) {
                // Both locks have been acquired
                System.out.println("Both locks acquired");
                // Perform some operations here
            } else {
                // Could not acquire one or both locks
                System.out.println("Could not acquire both locks");
            }
        } finally {
            // Release each lock if it was acquired
            if (lock1Acquired) {
                lock1.unlock();
                System.out.println("Lock 1 released");
            }
            if (lock2Acquired) {
                lock2.unlock();
                System.out.println("Lock 2 released");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        LockTimeoutExample example = new LockTimeoutExample();
        example.tryToLockBothLocks();
    }
}
