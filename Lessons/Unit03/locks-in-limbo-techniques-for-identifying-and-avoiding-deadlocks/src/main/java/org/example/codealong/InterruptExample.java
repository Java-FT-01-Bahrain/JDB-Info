package org.example.codealong;

public class InterruptExample implements Runnable {
    public void run() {
        int count = 0;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                // Simulate a task that takes time
                Thread.sleep(1000);
                System.out.println("Processing " + (++count));
            } catch (InterruptedException e) {
                System.out.println("Interrupted during sleep");
                // Properly handling InterruptedException by breaking the loop
                break;
            }
        }
        System.out.println("Thread exiting under request...");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread myThread = new Thread(new InterruptExample());
        myThread.start();

        // Let the thread run for a bit
        Thread.sleep(3500);

        // Interrupt the thread
        System.out.println("Requesting interrupt");
        myThread.interrupt();

        // Wait for the thread to finish
        myThread.join();
        System.out.println("Thread has stopped.");
    }
}
