package org.example.codealong;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockManager {
    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();

    /**
     * Attempts to acquire both locks without waiting indefinitely.
     * Releases any acquired locks if both cannot be acquired.
     *
     * @return true if both locks are acquired, false otherwise.
     */
    public boolean tryLockBothLocks() {
        boolean lock1Acquired = lock1.tryLock();
        boolean lock2Acquired = lock2.tryLock();

        if (lock1Acquired && lock2Acquired) {
            return true;
        }

        if (lock1Acquired) {
            lock1.unlock();
        }
        if (lock2Acquired) {
            lock2.unlock();
        }

        return false;
    }

    public static void main(String[] args) {
        LockManager lockManager = new LockManager();
        // Attempt to lock both locks and print the result
        boolean result = lockManager.tryLockBothLocks();
        if (result) {
            System.out.println("Both locks were successfully acquired.");
            // Ensure to release the locks after successful acquisition
            lockManager.lock1.unlock();
            lockManager.lock2.unlock();
        } else {
            System.out.println("Failed to acquire both locks.");
        }
    }
}
