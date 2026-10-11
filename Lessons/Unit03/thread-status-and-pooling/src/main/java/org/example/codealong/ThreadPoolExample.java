package org.example.codealong;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExample {
    public static void main(String[] args) {
        // Create a fixed-size thread pool with 3 worker threads
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Submit tasks for execution
        for (int i = 1; i <= 10; i++) {
            final int taskId = i;
            executor.submit(() -> {
                System.out.println("Task " + taskId + " is being executed by thread: " + Thread.currentThread().getName());
                try {
                    // Simulate task execution time
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Task " + taskId + " completed.");
            });
        }

        // Shutdown the executor after all tasks are completed
        executor.shutdown();
    }
}
