package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.HashSet;
import java.util.Set;

public class LabQuestion1 {
    private static final String FILE_PATH = "src/main/java/org/example/numbers.txt";
    private static final Set<String> linesRead = new HashSet<>();
    private static volatile boolean running = true;

    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

        // TODO: Create a producer thread that reads numbers from the file specified by FILE_PATH and adds them to the queue.
        // Include error handling for IOExceptions, potentially improving beyond just printing the stack trace for better error management.
        // In the finally block, set 'running' to false to clearly signal to the consumer that production has ceased.
        // Review the synchronization of the 'running' variable across different threads to ensure it updates and reads correctly without delay.
        Thread producer = new Thread(() -> {

        });

        // TODO: Set up a consumer thread that continues to operate as long as the 'running' flag is true or there are still items in the queue.
        // Utilize queue.poll with a 100-millisecond timeout to efficiently handle situations where the queue might be empty temporarily.
        // If a number is successfully polled, output "Consumed: " followed by the number to indicate successful consumption.
        // In case of an InterruptedException, ensure the interrupt status of the thread is restored to handle future interrupts properly.
        // Consider additional handling or logging for the InterruptedException to improve monitoring and debugging of the thread's behavior.
        Thread consumer = new Thread(() -> {

        });

        producer.start();
        consumer.start();

        // TODO: Register a shutdown hook to ensure a graceful shutdown of the application.
        // Set the 'running' flag to false to signal all threads to stop processing.
        // Interrupt both the producer and consumer threads to break any ongoing blocking operations.
        // Attempt to join both threads, ensuring they complete before the application fully shuts down.
        // Handle any InterruptedExceptions during the join process by restoring the thread's interrupt status.
        // Consider enhancing error handling or adding logging within this shutdown hook to better trace shutdown activities and potential issues.
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {

        }));
    }

    public static void readFile(String fileName, BlockingQueue<Integer> queue) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            int count = 0;  // Count to keep track of how many numbers are processed
            while (running && count < 10 && (line = reader.readLine()) != null) { // Limit to 10 reads
                synchronized (linesRead) {
                    if (!linesRead.contains(line)) {
                        linesRead.add(line);
                        int number = Integer.parseInt(line);
                        queue.put(number); // This will block if the queue is full
                        System.out.println("Produced: " + number);
                        count++; // Increment count after successfully adding to queue
                    }
                }
                // TODO: Simulate some processing delay 100-millisecond
            }
        } catch (InterruptedException e) {
            // TODO: Properly handle InterruptedException
        }
    }
}
