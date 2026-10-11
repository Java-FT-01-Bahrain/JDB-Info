package org.example.codealong;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadlockDemo {
    private static final Lock lock1 = new ReentrantLock();
    private static final Lock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            lock1.lock(); // Acquire lock1
            System.out.println(Thread.currentThread().getName() + " acquired lock1.");
            try {
                Thread.sleep(1000); // Simulate work
                lock2.lock(); // Attempt to acquire lock2
                System.out.println(Thread.currentThread().getName() + " acquired lock2.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock2.unlock(); // Release lock2
                lock1.unlock(); // Release lock1
            }
        });

        Thread thread2 = new Thread(() -> {
            lock2.lock(); // Acquire lock2
            System.out.println(Thread.currentThread().getName() + " acquired lock2.");
            try {
                Thread.sleep(1000); // Simulate work
                lock1.lock(); // Attempt to acquire lock1
                System.out.println(Thread.currentThread().getName() + " acquired lock1.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock1.unlock(); // Release lock1
                lock2.unlock(); // Release lock2
            }
        });

        thread1.start();
        thread2.start();
    }
}
