package org.example.codealong;

public class LongRunningTask implements Runnable {
    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        long elapsedTime = 0L;
        while (!Thread.currentThread().isInterrupted() && elapsedTime < 60000) { // run for up to 60 seconds
            try {
                // Simulate a task that takes time (e.g., processing data)
                System.out.println("Task is running...");
                Thread.sleep(10000); // Sleep for 10 seconds
                elapsedTime = System.currentTimeMillis() - startTime;
            } catch (InterruptedException e) {
                System.out.println("Thread was interrupted during sleep. Cleaning up and stopping the task.");
                Thread.currentThread().interrupt(); // Optional: reset the interrupt flag
                break;
            }
        }
        if (!Thread.currentThread().isInterrupted()) {
            System.out.println("Task completed without interruption.");
        } else {
            System.out.println("Task was interrupted and has now stopped.");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread taskThread = new Thread(new LongRunningTask());
        taskThread.start();

        // Interrupt the thread after a short delay to demonstrate interruption
        Thread.sleep(15000); // Allow the thread to run for 15 seconds before interrupting
        taskThread.interrupt();

        // Wait for the thread to finish execution
        taskThread.join();
        System.out.println("Main thread has completed execution.");
    }
}
