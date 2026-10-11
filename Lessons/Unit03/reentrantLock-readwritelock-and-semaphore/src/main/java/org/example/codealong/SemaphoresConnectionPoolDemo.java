package org.example.codealong;

import java.util.concurrent.Semaphore;

class ConnectionPool {
    private static final int MAX_CONNECTIONS = 5;
    private Semaphore semaphore;

    public ConnectionPool() {
        semaphore = new Semaphore(MAX_CONNECTIONS);
    }

    public void getConnection() throws InterruptedException {
        semaphore.acquire(); // Acquire permit
        System.out.println(Thread.currentThread().getName() + " acquired connection.");
        // Simulate using the connection
        Thread.sleep(1000);
    }

    public void releaseConnection() {
        semaphore.release(); // Release permit
        System.out.println(Thread.currentThread().getName() + " released connection.");
    }
}

public class SemaphoresConnectionPoolDemo {
    public static void main(String[] args) {
        ConnectionPool connectionPool = new ConnectionPool();

        // Multiple threads attempting to acquire and release connections
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    connectionPool.getConnection(); // Attempt to acquire connection
                    // Simulate using the connection
                    Thread.sleep(1000);
                    connectionPool.releaseConnection(); // Release connection
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
