package org.example;

public class ThreadStatesDemo {
    public static void main(String[] args) {
        Object lock = new Object(); // Object to synchronize on

        // Create a new thread
        Thread newThread = new Thread(() -> {
            synchronized (lock) {
                System.out.println("New thread is executing...");
                try {
                    lock.wait(); // Put the thread in WAITING state
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Print the initial state of the new thread (should be NEW)
        System.out.println("State of the new thread: " + newThread.getState());

        // Start the new thread
        newThread.start();

        // Wait for a short while to ensure the new thread enters WAITING state
        try {
            Thread.sleep(100); // Sleep for 100 milliseconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print the state of the new thread after waiting (should be WAITING)
        System.out.println("State of the new thread after waiting: " + newThread.getState());

        // Notify the new thread to wake it up from waiting
        synchronized (lock) {
            lock.notify();
        }

        // Wait for the new thread to terminate
        try {
            newThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print the final state of the new thread after termination (should be TERMINATED)
        System.out.println("State of the new thread after termination: " + newThread.getState());
    }
}