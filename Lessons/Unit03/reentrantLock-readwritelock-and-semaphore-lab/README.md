# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) ReentrantLock, ReadWriteLock, and Semaphore Lab

| Title                                       | Type | Duration | Author               |
|---------------------------------------------|------|----------|----------------------|
| ReentrantLock, ReadWriteLock, and Semaphore | Lab  | 3:00     | Suresh Melvin Sigera |

## Learning objectives

In this lab, students will gain hands-on experience implementing synchronization mechanisms in Java, specifically
`ReentrantLock`, `ReadWriteLock`, and `Semaphore`. Students will understand how these mechanisms provide thread-safe
access to shared resources and explore their features and advantages.

### Tasks:

1. Implement `ReentrantLock`:
    - Create a class named `Counter` with a method `increment()` that increments a shared counter variable.
    - Use `ReentrantLock` to protect the critical section of code in the `increment()` method, ensuring that only one
      thread can increment the counter at a time.
    - Implement a simple test program to create multiple threads that concurrently call the `increment()` method and
      verify the correctness of the counter value.
2. Implement `ReadWriteLock`:
    - Create a class named `ReadWriteCounter` with methods `readValue()` and `writeValue(int value)` to read and write
      to a shared counter variable.
    - Use `ReadWriteLock` to protect read and write operations on the counter variable, allowing multiple threads to
      read simultaneously but ensuring exclusive access for writing.
    - Write a test program to create multiple reader threads and writer threads that concurrently read and write to the
      counter variable and verify the correctness of the counter value.
3. Implement Semaphore:
    - Create a class named `ConnectionPool` representing a pool of database connections.
    - Use Semaphore to limit the number of concurrent connections to the pool, allowing only a fixed number of threads
      to acquire connections at a time.
    - Implement methods `getConnection()` and `releaseConnection()` to acquire and release connections from the pool
      using semaphores.
    - Write a test program to simulate multiple threads attempting to acquire and release connections concurrently and
      verify the correct behavior of the connection pool.

### Expected Output:

- Each task should demonstrate the correct usage of the respective synchronization mechanism (`ReentrantLock`,
  `ReadWriteLock`, `Semaphore`).
- The test programs should run without errors and produce the expected output, demonstrating thread-safe access to
  shared resources.

### Additional Challenges (Optional):

- Implement fair ordering with `ReentrantLock`.
- Explore timed lock attempts with `ReentrantLock`.
- Experiment with different fairness policies for `ReadWriteLock`.
- Use semaphores to implement a bounded buffer or producer-consumer problem.

### Bonus Question: Potential Deadlock Scenario

Consider the following scenario involving two shared resources, `ResourceA` and `ResourceB`, and two threads, `ThreadA`
and `ThreadB`. Both threads need access to both resources to complete their tasks. However, they acquire the resources
in different orders, potentially leading to a deadlock situation.

#### Starter Code:

```java
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

```