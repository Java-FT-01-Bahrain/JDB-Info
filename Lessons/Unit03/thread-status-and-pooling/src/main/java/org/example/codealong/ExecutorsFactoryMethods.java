package org.example.codealong;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorsFactoryMethods {
    public static void main(String[] args) {
        // Fixed-size thread pool
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        // Cached thread pool
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        // Single thread pool
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

        // Submit tasks for execution
        for (int i = 1; i <= 10; i++) {
            final int taskId = i;
            fixedThreadPool.submit(() -> {
                System.out.println("Task " + taskId + " executed by fixedThreadPool");
                // Simulate task execution time
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            cachedThreadPool.submit(() -> {
                System.out.println("Task " + taskId + " executed by cachedThreadPool");
                // Simulate task execution time
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            singleThreadExecutor.submit(() -> {
                System.out.println("Task " + taskId + " executed by singleThreadExecutor");
                // Simulate task execution time
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        // Shutdown the executors after all tasks are completed
        fixedThreadPool.shutdown();
        cachedThreadPool.shutdown();
        singleThreadExecutor.shutdown();
    }
}
