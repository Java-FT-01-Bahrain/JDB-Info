package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class FileReadingLab {
    private static final String FILE_PATH = "src/main/java/org/example/data.txt";
    private static final Set<String> sharedResource = new HashSet<>();
    private static final Set<String> linesRead = new HashSet<>(); // Set to keep track of lines read by threads

    // Synchronized block to add lines from file to shared resource
    public static void readAndAddToSharedResource() {
        try {
            readFile(FILE_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to read file and add lines to shared resource
    public static void readFile(String fileName) throws IOException {
        Path pathToFile = Paths.get(fileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(Paths.get(pathToFile.toUri()).toFile()))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                // TODO: Implement synchronized block to ensure thread safety
                synchronized (linesRead) {
                    // TODO: Check if the line has already been read by any thread
                    if (!linesRead.contains(currentLine)) {
                        // TODO: Mark the current line as read and add it to the linesRead set
                        // TODO: Add the line to shared resource
                        synchronized (sharedResource) {
                            // TODO: Add the line to shared resource
                            // TODO: Print a message indicating that the line was added to the shared resource
                        }
                    }
                }
                Thread.sleep(100); // Simulate some processing time
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // TODO Implement a synchronized method to print shared resource content
    public synchronized static void printSharedResource() {
        // TODO: Print each line in the shared resource
    }

    public static void main(String[] args) {
        // TODO: Create multiple threads to read from the file concurrently and add to shared resource
        for (int i = 0; i < 5; i++) {
            // TODO: Create a new thread to execute the readAndAddToSharedResource method
            // TODO: Start the thread to begin concurrent execution
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // TODO: Print the shared resource content
        System.out.println("\nShared Resource Content:");
    }
}


