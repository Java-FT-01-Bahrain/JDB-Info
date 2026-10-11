package org.lab.bonus;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ResourceA {
}

class ResourceB {
}

public class DeadlockDemo {
    private static final ResourceA resourceA = new ResourceA();
    private static final ResourceB resourceB = new ResourceB();
    private static final Lock lockA = new ReentrantLock();
    private static final Lock lockB = new ReentrantLock();

    public static void main(String[] args) {
        Thread threadA = new Thread(() -> {
            // Acquire lockA
            // Simulate work with resourceA
            // Attempt to acquire lockB
            // Simulate work with resourceB
            // Release lockB
            // Release lockA
        });

        Thread threadB = new Thread(() -> {
            // Acquire lockB
            // Simulate work with resourceB
            // Attempt to acquire lockA
            // Simulate work with resourceA
            // Release lockA
            // Release lockB
        });

        threadA.start();
        threadB.start();
    }
}
