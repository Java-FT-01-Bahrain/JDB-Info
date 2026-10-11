package org.example.codealong;

public class TaskRunner implements Runnable {
    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                // Simulate a task that can be interrupted
                System.out.println("Running task");
                Thread.sleep(1000); // Thread may be interrupted during sleep
            }
        } catch (InterruptedException e) {
            System.out.println("Thread was interrupted, stopping...");
            Thread.currentThread().interrupt(); // Restore interrupted status
            // Perform any necessary cleanup tasks here before exiting
        } finally {
            cleanUp();
        }
        System.out.println("Thread exiting gracefully");
    }

    private void cleanUp() {
        // Cleanup code here, e.g., close files, release locks, etc.
        System.out.println("Cleaning up resources");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new TaskRunner());
        thread.start();
        // Allow the thread to run for a bit
        Thread.sleep(3000);
        thread.interrupt(); // Interrupt the thread
        thread.join(); // Wait for the thread to finish execution
        System.out.println("Main thread exiting");
    }
}
