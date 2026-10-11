package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;

public class LabQuestion3 {
    private static final String FILE_PATH = "src/main/java/org/example/numbers.txt";

    // TODO: Declare 'running' as a volatile boolean initialized to true to control thread execution.

    public static void main(String[] args) {
        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>();

        // TODO: Create a producer thread that attempts to read numbers from the specified FILE_PATH and add them to the queue.
        // Ensure that readFile method handles IOExceptions appropriately rather than just printing the stack trace.
        // In the finally block, verify and possibly refactor the mechanism for setting the running flag to false to ensure thread safety.
        // Additionally, print "Producer finished" to signal end of operation. Consider implementing more robust exception handling
        // and better synchronization when changing the running state.
        Thread producer = new Thread(() -> {

        });

        // TODO: Create a consumer thread that continuously checks if the producer is still running or if the queue is not empty.
        // Utilize the queue.poll method with a 100 millisecond timeout to avoid indefinitely blocking the consumer.
        // If a number is successfully polled from the queue, print "Consumed: " followed by the number.
        // If no number is available, print "Waiting for more numbers or termination..." to indicate the waiting state.
        // Handle InterruptedException by restoring the thread's interrupt status and printing "Consumer interrupted".
        // After exiting the loop, print "Consumer finished" to signify that the consumer thread has completed processing.
        // Review the while loop condition to ensure it accurately reflects the intended logic and consider enhancing the interrupt handling.
        Thread consumer = new Thread(() -> {

        });

        // TODO: Start both the producer and consumer threads.

        try {
            producer.join(); // Ensure that joining logic is robust and handles exceptions properly
            consumer.interrupt(); // Check if interrupt is always necessary here
            consumer.join(); // Confirm that consumer finishes properly after interruption
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Ensure that interrupt status is handled throughout all threads
            System.out.println("Main thread interrupted");
        }
    }

    public static void readFile(String fileName, LinkedBlockingQueue<Integer> queue) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int number = Integer.parseInt(line);
                queue.put(number);
                System.out.println("Produced: " + number);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IOException("Thread interrupted during file reading", e);
        }
    }
}
