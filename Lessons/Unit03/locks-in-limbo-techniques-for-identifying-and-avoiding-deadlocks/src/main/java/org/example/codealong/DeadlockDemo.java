package org.example.codealong;

public class DeadlockDemo {
    private static final Object Lock1 = new Object();
    private static final Object Lock2 = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            synchronized (Lock1) {
                System.out.println("Thread 1: Holding lock 1...");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Handle the interrupt appropriately.
                }
                System.out.println("Thread 1: Waiting for lock 2...");
                synchronized (Lock2) {
                    System.out.println("Thread 1: Holding lock 1 and lock 2...");
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (Lock1) { // Acquire Lock1 first, as in thread1
                System.out.println("Thread 2: Holding lock 1...");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Handle the interrupt appropriately.
                }
                System.out.println("Thread 2: Waiting for lock 2...");
                synchronized (Lock2) {
                    System.out.println("Thread 2: Holding lock 1 and lock 2...");
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
