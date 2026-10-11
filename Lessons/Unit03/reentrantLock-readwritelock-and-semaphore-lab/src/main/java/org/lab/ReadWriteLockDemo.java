package org.lab;

// TODO: Import ReentrantReadWriteLock from java.util.concurrent.locks

class ReadWriteCounter {
    // TODO: Initialize value to 0
    // TODO: Declare a ReentrantReadWriteLock

    public int readValue() {
        // TODO: Lock readLock
        try {
            // TODO: Print the current value and thread name
        } finally {
            // TODO: Unlock readLock
        }
        return 0;
    }

    public void writeValue(int newValue) {
        // TODO: Lock writeLock
        try {
            // TODO: Print the new value and thread name
        } finally {
            // TODO: Unlock writeLock
        }
    }
}

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        System.out.println("ReentrantLockDemo");
        // TODO: Create an instance of ReadWriteCounter

        // TODO: Create multiple reader threads
        // TODO: Call readValue method multiple times
        // TODO: Set name for reader threads
        // TODO: Start reader threads

        // TODO: Create multiple writer threads
        // TODO: Call writeValue method with incrementing value
        // TODO: Set name for writer threads
        // TODO: Start writer threads
    }
}