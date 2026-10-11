# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Understanding Race Conditions

| Title                         | Type   | Duration | Author               |
|-------------------------------|--------|----------|----------------------|
| Understanding Race Conditions | Lesson | 3:30     | Suresh Melvin Sigera |

## Learning objectives

**By the end of this lesson, students will be able to:**

- Define race condition and recognize its significance in software development, particularly in scenarios involving
  shared resources and concurrent access.
- Identify real-life examples illustrating race conditions, such as bank account transactions and online ticket booking,
  and understand the risks and consequences associated with race conditions in software applications.
- Develop the ability to write and execute simple Java programs without synchronization to demonstrate race conditions,
  and analyze the unpredictable behavior resulting from race conditions.
- Explore synchronization mechanisms in Java, including the synchronized keyword, locks, mutexes, and atomic operations,
  and comprehend their usage for ensuring thread safety and preventing race conditions.
- Enhance Java programs with synchronization to mitigate race conditions, utilizing the synchronized keyword and other
  synchronization mechanisms.
- Learn best practices for effective and efficient synchronization in Java, including strategies for avoiding deadlocks,
  livelocks, and understanding performance considerations when using synchronization.

### Definition and Concept of a Race Condition (20 minutes)

In the realm of concurrent programming, a race condition occurs when the behavior of a program depends on the
interleaving or timing of multiple threads or processes. It's a situation where the outcome of the execution depends on
the non-deterministic order of execution of instructions in parallel.

To elaborate, imagine two or more threads or processes accessing shared resources or variables concurrently without
proper synchronization. Due to the unpredictable scheduling of these threads by the operating system, the order of
execution may vary, leading to different outcomes. In some cases, these outcomes may be incorrect or unexpected, which
can result in bugs and system failures.

### Real-life Examples Illustrating Race Conditions

- **Bank Account Transactions**: Consider a scenario where two transactions are being processed simultaneously: one to
  withdraw money from an account, and another to deposit money into the same account. If the withdrawal operation reads
  the account balance, and before it updates the balance, another transaction deposits money into the account, the final
  balance might be incorrect. This happens because the withdrawal operation didn't anticipate changes made by the
  deposit operation, leading to a race condition.

- **Online Ticket Booking**: In a ticket booking system, multiple users might simultaneously try to reserve the last
  available ticket for an event. If the system doesn't handle concurrent access properly, it might allow multiple users
  to book the same ticket, resulting in overbooking. This happens because the system fails to ensure exclusive access to
  the ticket inventory, leading to a race condition.

### Risks and Consequences of Race Conditions in Software Applications

Race conditions pose significant risks to software applications, including:

- **Data Corruption**: Concurrent access to shared resources without proper synchronization can lead to data corruption
  or inconsistency. For instance, in a multi-threaded application, simultaneous writes to a shared variable might
  overwrite each other's changes, resulting in corrupted data.
- **Incorrect Behavior**: Race conditions can cause programs to exhibit incorrect behavior that is difficult to
  reproduce and debug. Since the outcome depends on timing and interleaving of threads, the program may work correctly
  most of the time but fail unpredictably under certain conditions.
- **Security Vulnerabilities**: Race conditions can also introduce security vulnerabilities in software applications.
  For example, a race condition in a web application could allow an attacker to manipulate shared resources concurrently
  and bypass security checks or gain unauthorized access to sensitive data.

### Code along : simulating a race condition (40 minutes)

Here's simple Java class representing a bank account and simulating a race condition:

```java
package org.example.racecondition;

public class BankAccount {
  private double balance;

  public BankAccount(double initialBalance) {
    this.balance = initialBalance;
  }

  public double getBalance() {
    return balance;
  }

  public void deposit(double amount) {
    balance += amount;
  }

  public void withdraw(double amount) {
    balance -= amount;
  }
}

```

Now, let's create a scenario where a race condition can occur. We'll create multiple threads that simultaneously deposit
and withdraw money from the same bank account without proper synchronization:

```java
package org.example.racecondition;

public class RaceConditionSimulation {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000);

        // Create multiple threads for deposit and withdrawal
        Thread depositThread = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                account.deposit(1);
            }
        });

        Thread withdrawThread = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                account.withdraw(1);
            }
        });

        // Start the threads
        depositThread.start();
        withdrawThread.start();

        // Wait for threads to finish
        try {
            depositThread.join();
            withdrawThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print the final balance
        System.out.println("Final Balance: " + account.getBalance());
    }
}

```

In this simulation, we have two threads running concurrently: one for depositing money into the bank account (
`depositThread`) and another for withdrawing money from the bank account (`withdrawThread`). Both threads perform `1000`
deposit or withdrawal operations.

Since the `deposit()` and `withdraw()` methods of the `BankAccount` class are not synchronized, a race condition can
occur when multiple threads access the balance variable concurrently. As a result, the final balance printed may vary
depending on the interleaving of operations by different threads, and it may not always be the expected value of `1000`.

This example illustrates how a race condition can lead to unexpected behavior in a multi-threaded environment. To
prevent race conditions and ensure correct behavior, proper synchronization mechanisms need to be employed, which we'll
cover in the next part of the lecture.

## Introduction to Synchronization in Java (30 minutes)

In Java, synchronization is a mechanism used to control access to shared resources, ensuring that only one thread can
access the resource at a time. This prevents race conditions and ensures data consistency in multi-threaded
environments.

### Overview of Synchronization Mechanisms in Java

Java provides several synchronization mechanisms to achieve thread synchronization:

- **Synchronized methods**: You can declare methods as synchronized to ensure that only one thread can execute the
  method at a time. This is achieved by acquiring an intrinsic lock associated with the object instance or class.

- **Synchronized blocks**: In addition to synchronizing entire methods, you can use synchronized blocks to synchronize
  specific blocks of code. This allows for finer-grained control over synchronization and can lead to better
  performance.

- **Locks**: The `java.util.concurrent.locks.Lock` interface provides a more flexible synchronization mechanism than
  intrinsic locks. It allows for more advanced locking strategies such as reentrant locks, fair locks, and condition
  variables.

- **Atomic operations**: Java provides atomic classes such as AtomicInteger, AtomicLong, and AtomicReference in the
  **java.util.concurrent.atomic** package. These classes provide atomic operations that are guaranteed to be executed
  without interruption, making them useful for implementing thread-safe operations on primitive types and object
  references.

### Introduction to the synchronized Keyword and Its Usage

The `synchronized` keyword in Java is used to define `synchronized` methods and blocks. When applied to a method or
block, it ensures that **only one thread can execute the synchronized code at a time**. There are two primary ways to
use the synchronized keyword:

- **Synchronized methods**: You can declare entire methods as `synchronized` by using the `synchronized` keyword in the
  method signature. This ensures that only one thread can execute the method at a time.
  ```java
  public synchronized void synchronizedMethod(){
          // Synchronized method body
  }
  ```
- **Synchronized blocks**: Alternatively, you can use synchronized blocks to synchronize specific blocks of code within
  a method. This allows for more fine-grained control over synchronization and can lead to better performance.
  ```java
  public void methodWithSynchronizedBlock() {
      synchronized (lockObject) {
      // Synchronized block of code
      }
  }
  ```

### Code along : Using synchronized methods and blocks to manage access to a shared resource (40 minutes)

```java
package org.example.synchronization;

import java.util.logging.Logger;

public class SynchronizedExample {

    private static final Logger logger = Logger.getLogger(SynchronizedExample.class.getName());

    // Shared resource
    private int count = 0;

    // Synchronized method to increment count
    public synchronized void increment() {
        count++; // This operation is atomic within the synchronized method
        logger.info("Incremented count to " + count);
    }

    // Method with synchronized block to decrement count
    public void decrement() {
        synchronized (this) {
            count--; // This operation is atomic within the synchronized block
            logger.info("Decremented count to " + count);
        }
    }

    // Method to print the current count
    public void printCount() {
        logger.info("Current count: " + count);
    }

    public static void main(String[] args) {
        SynchronizedExample example = new SynchronizedExample();

        // Create multiple threads to increment count
        Thread incrementThread1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                example.increment();
            }
        });

        Thread incrementThread2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                example.increment();
            }
        });

        // Create thread to decrement count
        Thread decrementThread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                example.decrement();
            }
        });

        // Start all threads
        incrementThread1.start();
        incrementThread2.start();
        decrementThread.start();

        // Wait for all threads to finish
        try {
            incrementThread1.join();
            incrementThread2.join();
            decrementThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print final count
        example.printCount();
    }
}
```

This Java code exemplifies the usage of synchronization to ensure thread safety when multiple threads are accessing and
modifying a shared resource concurrently. Here's a detailed breakdown:

- **Shared Resource**:
  - A variable named count is declared to hold an integer value. This variable represents the shared
    resource that multiple threads will access and modify concurrently.
- **Synchronized Method (`increment()`)**:
  - The method `increment()` is defined with the synchronized keyword, ensuring that only one thread can execute it at
    a time. This prevents race conditions where multiple threads might try to increment count simultaneously. Inside
    this method, the count variable is incremented atomically.
- **Synchronized Block (`decrement()`)**:
  - The method `decrement()` doesn't use the synchronized keyword for the entire method. Instead, it employs a
    synchronized block using synchronized (`this`). This approach allows for more fine-grained control over
    synchronization, ensuring that only one thread can execute the block of code within it at a time. Inside the
    synchronized block, the count variable is decremented atomically.
- **Print Count Method (`printCount()`)**:
  - This method simply logs the current value of the count variable. It doesn't require synchronization since it's
    only reading the value of count and not modifying it.
- **Main Method**:
  - The main method serves as the entry point of the program.
  - An instance of the `SynchronizedExample` class named example is created.
  - Multiple threads are created to demonstrate concurrent execution:
    - Two threads (`incrementThread1` and `incrementThread2`) are created to increment the count variable using
      the `increment()` method.
    - One thread (decrementThread) is created to decrement the count variable using the decrement() method.
  - All threads are started using the `start()` method.
  - The main thread waits for all the threads to finish their execution using the `join()` method to ensure proper
    synchronization.
  - Finally, the `printCount()` method is called to print the final value of the count variable.

By employing synchronization techniques, this code ensures that concurrent access to the shared resource (`count`) is
managed effectively, preventing race conditions and maintaining the consistency of data across multiple threads.

### Explanation of Locks, Mutexes, and Atomic Operations

- **Locks**: In Java, locks provide a more flexible and powerful synchronization mechanism compared to intrinsic locks.
  They allow for advanced locking strategies such as reentrant locks, fair locks, and lock conditions. Locks are
  implemented through the `java.util.concurrent.locks.Lock` interface and its implementations like `ReentrantLock`.
    ```java
    Lock lock = new ReentrantLock();
    lock.lock(); // Acquire the lock
    try {
        // Critical section
    } finally {
        lock.unlock(); // Release the lock
    }
    ```

Let's modify the bank simulation example to use synchronization mechanisms such as `synchronized` methods, synchronized
`blocks`, `ReentrantLock`, and `AtomicInteger` to prevent race conditions and ensure thread safety.

### Code along : Modifying the Example to Use Synchronization (40 minutes)

```java
package org.example.synchronization;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
  private double balance;
  private final Lock lock = new ReentrantLock();
  private final AtomicInteger atomicBalance = new AtomicInteger(1000);

  public BankAccount(double initialBalance) {
    this.balance = initialBalance;
  }

  // Synchronized method to deposit money
  public synchronized void depositSyncMethod(double amount) {
    balance += amount;
  }

  // Synchronized method to withdraw money
  public synchronized void withdrawSyncMethod(double amount) {
    balance -= amount;
  }

  // Synchronized block to deposit money
  public void depositSyncBlock(double amount) {
    synchronized (this) {
      balance += amount;
    }
  }

  // Synchronized block to withdraw money
  public void withdrawSyncBlock(double amount) {
    synchronized (this) {
      balance -= amount;
    }
  }

  // ReentrantLock to deposit money
  public void depositWithReentrantLock(double amount) {
    lock.lock();
    try {
      balance += amount;
    } finally {
      lock.unlock();
    }
  }

  // ReentrantLock to withdraw money
  public void withdrawWithReentrantLock(double amount) {
    lock.lock();
    try {
      balance -= amount;
    } finally {
      lock.unlock();
    }
  }

  // Atomic operations to deposit money
  public void depositAtomic(double amount) {
    atomicBalance.addAndGet((int) amount);
  }

  // Atomic operations to withdraw money
  public void withdrawAtomic(double amount) {
    atomicBalance.addAndGet((int) -amount);
  }

  // Get balance using synchronized method
  public synchronized double getBalance() {
    return balance;
  }

  // Get balance using atomic operations
  public double getAtomicBalance() {
    return atomicBalance.doubleValue();
  }
}

```

```java
package org.example.synchronization;

public class BankSimulation {
  public static void main(String[] args) throws InterruptedException {
    BankAccount account = new BankAccount(1000);

    // Create multiple threads for deposit and withdrawal
    Thread[] depositThreads = new Thread[10];
    Thread[] withdrawThreads = new Thread[10];

    for (int i = 0; i < 10; i++) {
      depositThreads[i] = new Thread(() -> {
        for (int j = 0; j < 1000; j++) {
          account.depositSyncMethod(1);
          // account.depositSyncBlock(1);
          // account.depositWithReentrantLock(1);
          // account.depositAtomic(1);
        }
      });

      withdrawThreads[i] = new Thread(() -> {
        for (int j = 0; j < 1000; j++) {
          account.withdrawSyncMethod(1);
          // account.withdrawSyncBlock(1);
          // account.withdrawWithReentrantLock(1);
          // account.withdrawAtomic(1);
        }
      });

      depositThreads[i].start();
      withdrawThreads[i].start();
    }

    // Wait for all threads to finish
    for (int i = 0; i < 10; i++) {
      depositThreads[i].join();
      withdrawThreads[i].join();
    }

    // Print the final balance
    System.out.println("Final Balance: " + account.getBalance());
    // System.out.println("Final Balance: " + account.getAtomicBalance());
  }
}

```

In this updated code:

- **Synchronized methods** (`depositSyncMethod` and `withdrawSyncMethod`) ensure that only one thread can execute these
  methods at a time, preventing race conditions.
- **Synchronized blocks** (`depositSyncBlock` and `withdrawSyncBlock`) synchronize specific blocks of code within
  methods, providing finer-grained control over synchronization.
- **ReentrantLock** is used to provide a more flexible synchronization mechanism, allowing explicit locking and
  unlocking of resources.
- **AtomicInteger** (`atomicBalance`) is used to perform atomic operations without the need for explicit
  synchronization, ensuring thread safety.

You can uncomment each synchronization mechanism one by one and test the code to observe how each approach prevents race
conditions and ensures thread safety in the bank simulation example.

### Alternatives to Synchronization (20 minutes)

In addition to synchronization mechanisms, Java provides several alternative concurrency control mechanisms that offer
different trade-offs in terms of performance, simplicity, and scalability. Let's explore some of these alternatives:

**Volatile Variables**

- **Overview**: The volatile keyword in Java ensures that changes to a variable are immediately visible to other
  threads. It provides a lightweight form of synchronization by ensuring that reads and writes to the variable are
  atomic and happen in a predictable order.
- **Usage**: Volatile variables are suitable for cases where a variable is accessed by multiple threads for reading and
  writing, and synchronization overhead needs to be minimized. They are commonly used for flags, status indicators, and
  simple shared variables.
- **Limitations**: Volatile variables are limited in their applicability and cannot be used to perform compound actions
  or enforce atomicity across multiple operations.

**Atomic Classes**

- **Overview**: Java provides a set of atomic classes in the java.util.concurrent.atomic package, such as AtomicInteger,
  AtomicLong, and AtomicReference. These classes offer atomic operations for common operations like read-modify-write,
  compare-and-swap, and get-and-set.
- **Usage**: Atomic classes are suitable for cases where fine-grained atomic operations are required on primitive types
  and object references. They eliminate the need for explicit synchronization by ensuring that operations are performed
  atomically without the risk of race conditions.
- **Performance**: Atomic classes are generally more performant than synchronization mechanisms like locks, especially
  in highly contended scenarios, due to their lock-free design.

**Locks from java.util.concurrent Package**

- **Overview**: Java provides a comprehensive set of locks and synchronization utilities in the `java.util.concurrent`
  package, such as `ReentrantLock`, `ReadWriteLock`, and `Semaphore`. These locks offer more flexibility and advanced
  features compared to intrinsic locks.
- **Usage**: Locks from the `java.util.concurrent` package are suitable for cases where fine-grained control over
  synchronization is required, or when additional features like fairness policies, interruptible locking, or condition
  variables are needed.
- **Performance**: While locks from the `java.util.concurrent` package offer more features and flexibility, they may
  incur higher overhead compared to intrinsic locks, especially in low-contention scenarios.

**Concurrent Collections**

- **Overview**: Java provides thread-safe implementations of common collection classes in the `java.util.concurrent`
  package, such as `ConcurrentHashMap`, `ConcurrentLinkedQueue`, and `CopyOnWriteArrayList`. These collections offer
  high concurrency and scalability by allowing multiple threads to access and modify them concurrently.
- **Usage**: Concurrent collections are suitable for cases where multiple threads need to access and modify shared data
  concurrently without the need for explicit synchronization. They provide better scalability and performance compared
  to synchronized collections.

### Summary (10 minutes)

Java offers a variety of concurrency control mechanisms, each with its own strengths and weaknesses. When
choosing the appropriate mechanism for your application, consider factors such as performance requirements, scalability,
complexity, and the level of fine-grained control over synchronization needed. Experiment with different approaches and
select the one that best fits your application's concurrency requirements.