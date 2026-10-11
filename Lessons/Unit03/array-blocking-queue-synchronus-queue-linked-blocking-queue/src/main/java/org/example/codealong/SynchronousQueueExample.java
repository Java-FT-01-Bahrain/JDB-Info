package org.example.codealong;

import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueExample {
    public static void main(String[] args) {
        // Create a SynchronousQueue
        SynchronousQueue<Integer> queue = new SynchronousQueue<>();

        // Producer thread
        Thread producer = new Thread(() -> {
            try {
                int data = 42;
                System.out.println("Producer is waiting to hand off data: " + data);
                queue.put(data); // Block until a consumer is ready to receive the data
                System.out.println("Producer handed off data: " + data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Consumer thread
        Thread consumer = new Thread(() -> {
            try {
                System.out.println("Consumer is waiting to receive data");
                int data = queue.take(); // Block until a producer is ready to provide the data
                System.out.println("Consumer received data: " + data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Start producer and consumer threads
        producer.start();
        consumer.start();
    }
}

