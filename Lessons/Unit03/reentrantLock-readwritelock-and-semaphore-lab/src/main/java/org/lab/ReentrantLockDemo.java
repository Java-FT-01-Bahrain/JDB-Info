package org.lab;

// TODO: Import ReentrantLock from java.util.concurrent.locks

class Counter {
    // TODO: Initialize count to 0
    // TODO: Declare a ReentrantLock

    public void increment() {
        // TODO: Acquire the lock
        try {
            // TODO: Increment the count
            // TODO: Print the counter value and thread name
        } finally {
            // TODO: Release the lock
        }
    }
}


public class ReentrantLockDemo {
    public static void main(String[] args) {
        System.out.println("ReentrantLockDemo");
        // TODO: Create an instance of Counter
        // TODO: Create multiple threads to increment the counter concurrently
                  // TODO: Call increment method multiple times
            // TODO: Set the name for each thread
            // TODO: Start each thread
    }
}