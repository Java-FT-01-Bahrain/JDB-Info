package org.lab;

// TODO: Import Semaphore from java.util.concurrent package

class ConnectionPool {
    // TODO: Define maximum number of connections
    // TODO: Initialize Semaphore with MAX_CONNECTIONS and fairness

    public void getConnection() throws InterruptedException {
        // TODO: Acquire a permit from the semaphore
        // TODO: Print message after acquiring a connection
        // TODO: Simulate connection usage
    }

    public void releaseConnection() {
        // TODO: Release a permit to the semaphore
        // TODO: Print message after releasing a connection
    }
}

public class SemaphoreDemo {
    public static void main(String[] args) {
        System.out.println("SemaphoreDemo");
        // TODO: Create an instance of ConnectionPool
        // TODO: Create multiple threads to acquire and release connections
            // TODO: Acquire connection
            // TODO: Simulate connection usage
            // TODO: Release connection
            // TODO: Handle InterruptedException
        // TODO: Set thread name
        // TODO: Start the thread
    }
}
