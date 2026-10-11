package org.example.codealong;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockCounterDemo {
    private final ReentrantLock lock = new ReentrantLock();
    private int count = 0;

    public void increment() {
        lock.lock();
        try {
            count++;
            System.out.println("Current thread [" + Thread.currentThread().getName() + "] holds the lock, count = " + count
                    + ", hold count = " + lock.getHoldCount());
        } finally {
            lock.unlock();
        }
    }

    public void tryIncrement() {
        if (lock.tryLock()) {
            try {
                count++;
                System.out.println("Current thread [" + Thread.currentThread().getName() + "] holds the lock, count = " + count);
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println("Current thread [" + Thread.currentThread().getName() + "] failed to acquire the lock");
        }
    }

    public void tryIncrementWithTimeout() {
        try {
            if (lock.tryLock(1, TimeUnit.SECONDS)) {
                try {
                    count++;
                    System.out.println("Current thread [" + Thread.currentThread().getName() + "] holds the lock, count = " + count);
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println("Current thread [" + Thread.currentThread().getName() + "] failed to acquire the lock");
            }
        } catch (InterruptedException e) {
            System.out.println("Current thread [" + Thread.currentThread().getName() + "] is interrupted");
        }
    }

    public void incrementInterruptibly() {
        try {
            lock.lockInterruptibly();
            try {
                count++;
                System.out.println("Current thread [" + Thread.currentThread().getName() + "] holds the lock, count = " + count);
            } finally {
                lock.unlock();
            }
        } catch (InterruptedException e) {
            System.out.println("Current thread [" + Thread.currentThread().getName() + "] is interrupted");
        }
    }

    public void printCount() {
        System.out.println("Current count = " + count);
    }

    public static void main(String[] args) {
        ReentrantLockCounterDemo reentrantLockCounterDemo = new ReentrantLockCounterDemo();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                reentrantLockCounterDemo.increment();
            }
        }, "T1");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                reentrantLockCounterDemo.tryIncrement();
            }
        }, "T2");

        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                reentrantLockCounterDemo.tryIncrementWithTimeout();
            }
        }, "T3");

        Thread t4 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                reentrantLockCounterDemo.incrementInterruptibly();
            }
        }, "T4");

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        reentrantLockCounterDemo.printCount();
    }
}
