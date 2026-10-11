package org.example.synchronization;

import java.util.logging.Logger;

public class SynchronizedExample {

    private static final Logger logger = Logger.getLogger(SynchronizedExample.class.getName());

    // Shared resource
    private int count = 0;

    // Synchronized method to increment count
    public synchronized void increment() {
        count++; // This operation is atomic within the synchronized method
        logger.info("Incremented count to " + count);
    }

    // Method with synchronized block to decrement count
    public void decrement() {
        synchronized (this) {
            count--; // This operation is atomic within the synchronized block
            logger.info("Decremented count to " + count);
        }
    }

    // Method to print the current count
    public void printCount() {
        logger.info("Current count: " + count);
    }

    public static void main(String[] args) {
        SynchronizedExample example = new SynchronizedExample();

        // Create multiple threads to increment count
        Thread incrementThread1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                example.increment();
            }
        });

        Thread incrementThread2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                example.increment();
            }
        });

        // Create thread to decrement count
        Thread decrementThread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                example.decrement();
            }
        });

        // Start all threads
        incrementThread1.start();
        incrementThread2.start();
        decrementThread.start();

        // Wait for all threads to finish
        try {
            incrementThread1.join();
            incrementThread2.join();
            decrementThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print final count
        example.printCount();
    }
}
