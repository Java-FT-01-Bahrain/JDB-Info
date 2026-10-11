package org.example;

import java.util.concurrent.CountDownLatch;

public class MainThreadDemo2 {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Thread currentThread = Thread.currentThread();

        // Print out thread information
        System.out.println("Thread Name: " + currentThread.getName());
        System.out.println("Thread ID: " + currentThread.getId());
        System.out.println("Thread Priority: " + currentThread.getPriority());
        System.out.println("Thread State: " + currentThread.getState());
        System.out.println("Thread Group: " + currentThread.getThreadGroup().getName());

        // Blocking the thread to simulate WAITING state
        // Create a CountDownLatch with initial count 1
        CountDownLatch latch = new CountDownLatch(1);

        // Starting a thread to simulate the WAITING state
        new Thread(() -> {
            synchronized (currentThread) {
                try {
                    latch.await(); // Waits until latch count becomes zero
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        // Print the state of the thread after blocking
        System.out.println("Thread State after blocking: " + currentThread.getState());

        // Simulating TIMED_WAITING state
        try {
            Thread.sleep(1000); // Putting the current thread into TIMED_WAITING state for 1 second
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print the state of the thread after sleep
        System.out.println("Thread State after sleep: " + currentThread.getState());

        // Simulating BLOCKED state
        synchronized (currentThread) {
            // Empty synchronized block
            // This would put the thread in BLOCKED state if another thread tries to acquire the lock on the same object
        }

        // Print the state of the thread after synchronized block
        System.out.println("Thread State after synchronized block: " + currentThread.getState());

        // Terminating the thread to simulate TERMINATED state
        System.exit(0);
    }
}