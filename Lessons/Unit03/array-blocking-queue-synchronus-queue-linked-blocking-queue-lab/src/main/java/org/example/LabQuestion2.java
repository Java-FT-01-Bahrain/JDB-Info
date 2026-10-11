package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class LabQuestion2 {
    private static final String FILE_PATH = "src/main/java/org/example/numbers.txt"; // File path constant

    // TODO: Declare 'running' as a volatile boolean initialized to true to control thread execution.

    public static void main(String[] args) {
        SynchronousQueue<Integer> queue = new SynchronousQueue<>();

        // TODO: Create a producer thread that reads from a file specified by FILE_PATH and adds items to the queue.
        // Handle any IOExceptions that occur during the file reading process, possibly by implementing more sophisticated error handling.
        // In the finally block, ensure to set 'running' to false to signal that production has completed, which is critical for thread coordination.
        // Also, interrupt the current thread to potentially break any blocking waits, particularly affecting the consumer thread.
        // Review thread interruption and termination logic to ensure it behaves as expected without unintended side effects.
        Thread producer = new Thread(() -> {

        });

        // TODO: Create a consumer thread that processes elements from the queue as long as 'running' is true or the queue is not empty.
        // Use queue.poll with a 100-millisecond timeout to avoid indefinitely blocking the consumer. If a number is retrieved,
        // print "Consumed: " followed by the number.
        // Handle InterruptedException by restoring the thread's interrupt status, ensuring that the thread can respond appropriately
        // to an interrupt signal. Consider adding additional logic or feedback for cases where the consumer thread is interrupted,
        // to aid in debugging and operational monitoring.
        Thread consumer = new Thread(() -> {

        });

        // TODO: Start both the producer and consumer threads.

        try {
            producer.join(); // Ensure producer finishes
            consumer.join(); // Ensure consumer finishes
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void readFile(String fileName, SynchronousQueue<Integer> queue) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int number = Integer.parseInt(line);
                queue.put(number); // Blocks until the consumer takes an element
                System.out.println("Produced: " + number);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Preserve interrupt status
            throw new IOException("Thread interrupted during file reading", e);
        }
    }
}
