package org.example.codealong;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class SharedMap {
    private final Map<String, String> map = new HashMap<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public String getValue(String key) {
        lock.readLock().lock(); // Acquire read lock
        try {
            return map.get(key); // Perform read operation
        } finally {
            lock.readLock().unlock(); // Release read lock
        }
    }

    public void putValue(String key, String value) {
        lock.writeLock().lock(); // Acquire write lock
        try {
            map.put(key, value); // Perform write operation
        } finally {
            lock.writeLock().unlock(); // Release write lock
        }
    }
}

public class ReadWriteLockSharedMapDemo {
    public static void main(String[] args) {
        SharedMap sharedMap = new SharedMap();

        // Multiple reader threads reading from the map
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    System.out.println(Thread.currentThread().getName() + " reads: " + sharedMap.getValue("key"));
                }
            }).start();
        }

        // Single writer thread modifying the map
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                sharedMap.putValue("key", "value" + i);
                System.out.println(Thread.currentThread().getName() + " writes: value" + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
