package org.example.codealong;

import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueueExample {
    public static void main(String[] args) {
        // Create a LinkedBlockingQueue with capacity 10
        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>(10);

        // Producer thread
        Thread producer = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    queue.put(i); // Add elements to the queue
                    System.out.println("Produced: " + i);
                    Thread.sleep(1000); // Simulate some processing time
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Consumer thread
        Thread consumer = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    int value = queue.take(); // Remove and retrieve elements from the queue
                    System.out.println("Consumed: " + value);
                    Thread.sleep(2000); // Simulate some processing time
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Start producer and consumer threads
        producer.start();
        consumer.start();
    }
}
