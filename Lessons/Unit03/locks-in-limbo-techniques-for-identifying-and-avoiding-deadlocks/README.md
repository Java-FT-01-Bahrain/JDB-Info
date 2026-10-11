# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Locks in Limbo Techniques for Identifying and Avoiding Deadlocks

| Title                                                            | Type    | Duration | Author               |
|------------------------------------------------------------------|---------|----------|----------------------|
| Locks in Limbo Techniques for Identifying and Avoiding Deadlocks | Lecture | 3:40     | Suresh Melvin Sigera |

## Learning objectives

- Define deadlocks and understand their potential impact on software applications.
- Discuss and understand various strategies for managing deadlocks, including prevention, avoidance, and detection
  techniques.
- Get familiar with deadlock detection algorithms, especially the Banker’s algorithm, and its application in real
  systems.
- Understand the role of interrupts in managing the lifecycle of threads.
- Gain a thorough understanding of Java’s `wait()`, `notify()`, and `notifyAll()` methods.

### Understanding Deadlocks (10 minutes)

**Brief Recap on the Importance of Locks and Synchronization in Concurrent Programming**:

In concurrent programming, locks and synchronization are essential for maintaining data integrity and consistency when
multiple threads access shared resources. Locks act as gatekeepers, ensuring that only one thread can access a resource
at a time to prevent race conditions—situations where outcomes depend on the sequence of events. Synchronization,
involving locks and other methods, coordinates threads to ensure predictable and reliable execution. This is crucial for
preventing issues such as data races, deadlocks, and livelocks, thereby supporting robust and error-minimized
multi-threaded applications.

### Introduction to the Concept of Deadlocks, Emphasizing Their Impact on Applications (10 minutes):

A deadlock occurs in concurrent programming when two or more threads each wait for the other to release needed
resources, leading to a situation where none of the threads can proceed, effectively freezing the application. This can
severely affect performance and user experience, especially in critical systems like healthcare, finance, or real-time
data processing, where the consequences can range from data loss to jeopardizing lives.

For example, imagine two chefs working in a kitchen: Chef A needs a blender that Chef B is using, and Chef B needs a
mixer that Chef A is using. If neither chef is willing to give up their equipment, they both end up waiting
indefinitely, unable to complete their dishes. This scenario in the kitchen mirrors how deadlocks can paralyze
applications, preventing progress and disrupting operations.

**Deadlocks typically occur because of several conditions being met simultaneously**:

- **Mutual Exclusion**: Only one thread can use a resource at any given time.
- **Hold and Wait**: A thread holding at least one resource is waiting to acquire additional resources held by other
  threads.
- **No Preemption**: Resources cannot be forcibly removed from the threads that are holding them; they must be released
  voluntarily.
- **Circular Wait**: There exists a set of threads `{T1, T2, ..., Tn}`, where each thread `Ti` is waiting for a resource
  that is held by the next thread in the sequence (`Tn` waits for `T1`, forming a cycle).

Understanding how deadlocks are formed and the conditions that lead to them is essential for developing strategies to
avoid or resolve them, which is crucial for maintaining the stability and reliability of software applications.
Deadlocks need to be managed proactively by employing various programming techniques and design patterns aimed at
deadlock prevention and resolution, thus safeguarding the application from potential stalls or crashes that can arise
from these deadlock situations.

### Real-World Scenarios of Deadlocks (10 minutes):

Deadlocks can occur in various environments, ranging from operating systems to database management and multithreaded
applications. Here are some examples:

- **Database Systems**: In databases, deadlocks can occur when two or more transactions hold locks on parts of the
  database and then each waits for the other to release their locks before proceeding. For example, Transaction A holds
  a lock on Row 1 and needs a lock on Row 2, while Transaction B holds a lock on Row 2 and needs a lock on Row 1. Both
  transactions will wait indefinitely unless the deadlock is detected and resolved by the database system.
- **Operating Systems**: Deadlocks in operating systems can happen when processes request system resources in an
  inconsistent order. If Process A holds a printer and requests access to a tape drive while Process B holds the tape
  drive and requests access to the printer, both processes will wait indefinitely.
- **Multithreading in Applications**: In a multithreaded application, threads may require exclusive access to objects
  for operations. If Thread 1 locks Object A and then attempts to lock Object B while Thread 2 has locked Object B and
  attempts to lock Object A, a deadlock occurs.

### Code along a Deadlock (35 minutes)

```java
package org.example.codealong;

public class DeadlockDemo {
    private static final Object Lock1 = new Object();
    private static final Object Lock2 = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            synchronized (Lock1) {
                System.out.println("Thread 1: Holding lock 1...");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
                System.out.println("Thread 1: Waiting for lock 2...");
                synchronized (Lock2) {
                    System.out.println("Thread 1: Holding lock 1 and lock 2...");
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (Lock2) {
                System.out.println("Thread 2: Holding lock 2...");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
                System.out.println("Thread 2: Waiting for lock 1...");
                synchronized (Lock1) {
                    System.out.println("Thread 2: Holding lock 1 and lock 2...");
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}

```

In this code, two threads are created where each starts by locking one resource and then tries to lock another while
holding the first. This situation leads to a classic deadlock because each thread waits for the other to release the
lock on the second resource, which never happens.

**Questions on the Deadlock Example**

<details>
<summary>What is the sequence of events that leads to the deadlock in the provided code?</summary>
<br>
Answer: In the code, `Thread 1` starts by acquiring `Lock1` and then attempts to acquire `Lock2`. Concurrently, 
`Thread 2` acquires `Lock2` and then tries to acquire `Lock1`. Both threads enter a waiting state for the other's lock 
to be released, which never happens, leading to a deadlock. This is because each thread holds one lock and is waiting 
for the other lock, creating a circular wait condition.
</details>

<details>
<summary>Identify which of the four necessary conditions for a deadlock are present in this scenario.</summary>
- Mutual Exclusion: Both `Lock1` and `Lock2` are accessed exclusively by one thread at a time.
- Hold and Wait: Each thread holds one lock while waiting to acquire another.
- No Preemption: Locks in Java are non-preemptive; a lock cannot be forcibly removed from a thread; it must be released by the thread itself.
- Circular Wait: There is a circular dependency where `Thread 1` needs `Lock2` held by `Thread 2,` and `Thread 2` needs `Lock1` held by `Thread 1`.
</details>

<details>
<summary>How could this deadlock be avoided? Propose a solution or change to the code.</summary>
Answer: One straightforward way to avoid this deadlock is to ensure that all threads acquire locks in the same order. 

This prevents the circular wait condition. For instance, both threads should first try to acquire `Lock1` and then
`Lock2`. Here is the revised code snippet for both threads:

```java
public class DeadlockDemo {
    private static final Object Lock1 = new Object();
    private static final Object Lock2 = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            synchronized (Lock1) {
                System.out.println("Thread 1: Holding lock 1...");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Handle the interrupt appropriately.
                }
                System.out.println("Thread 1: Waiting for lock 2...");
                synchronized (Lock2) {
                    System.out.println("Thread 1: Holding lock 1 and lock 2...");
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (Lock1) { // Acquire Lock1 first, as in thread1
                System.out.println("Thread 2: Holding lock 1...");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Handle the interrupt appropriately.
                }
                System.out.println("Thread 2: Waiting for lock 2...");
                synchronized (Lock2) {
                    System.out.println("Thread 2: Holding lock 1 and lock 2...");
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
```

In this modified version:

- Both `thread1` and `thread2` first acquire `Lock1` and then `Lock2`. This consistent ordering prevents the circular
  wait condition necessary for a deadlock.
- I have added proper handling for `InterruptedException` by calling `Thread.currentThread().interrupt();`. This is a
  best practice to handle the interrupted state of a thread, allowing the thread to respond appropriately to the
  interrupt.

</details>

### Interrupts and Thread Management (10 minutes)

**Concept of Interrupts in Thread Life-Cycle Management**:

Thread interruption in Java is a cooperative mechanism that allows one thread to signal another to cease its current
operations and handle another task, typically to terminate. This method is crucial for managing the lifecycle of threads
within multi-threaded applications, ensuring that threads can be stopped safely without leaving shared resources in an
inconsistent state. Unlike outdated and unsafe methods like `Thread.stop()`, which can disrupt the program's stability,
thread interruption politely requests a thread to conclude its operations when it next checks for such a request.

For example, consider a chef (representing a thread) who is preparing a time-consuming dish in a kitchen. If the
restaurant needs to shift focus due to changing business needs—say, a sudden large group reservation needing immediate
attention—the chef is informed (`interrupted`) to stop what they're doing and prepare for the new task. This
interruption
allows the chef to clean up and save the current progress before switching tasks, similar to how a thread would handle
its resources before stopping.

**Practical Use Cases for Interrupting Threads**:

- **Cancelling Long-Running Tasks**: If a thread is performing a long-running task that suddenly becomes unnecessary (
  e.g., user cancels a file download), the thread can be interrupted to stop processing.
- **Timeouts**: When a thread is waiting for a response that may never come (like a network response), you can use
  interrupts to enforce a timeout.
- **Service Shutdown**: During the shutdown of a service, you may need to cleanly stop background threads. Sending an
  interrupt request allows these threads to terminate their operations safely.
- **Resource Management**: If a thread holds resources that should be released quickly (like file handles or database
  connections), interrupts can be used to signal the thread to release these resources and terminate.

### Exploring Java's `Thread.interrupt()` Method (10 minutes):

**How Thread.interrupt() Works and Its Impact on Thread States**:

The `Thread.interrupt()` method sets the interrupt status of the thread. This method does not actually stop the target
thread; it merely flags it as interrupted. It's up to the code running in the thread to check and respond appropriately
to the interrupt status.

When `interrupt()` is called on a thread, the following can happen depending on the state of the thread:

- If the thread is in a blocking operation (like waiting, sleeping, or otherwise occupied in blocking I/O operations),
  it will throw an `InterruptedException` and clear the interrupt status.
- If the thread is not in a blocking state, it just sets the interrupt status. The thread needs to periodically check
  this flag with `Thread.interrupted()` or `Thread.isInterrupted()` to see if it should stop executing.

### Code along using `Thread.interrupt()` (15 minutes):

```java
package org.example.codealong;

public class InterruptDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread taskThread = new Thread(() -> {
            try {
                for (int i = 0; i < Integer.MAX_VALUE; i++) {
                    if (Thread.interrupted()) {
                        System.out.println("Thread was interrupted, stopping.");
                        break;
                    }
                    // Simulate task processing
                    Math.sin(i);
                }
            } catch (Throwable e) {
                System.out.println("Caught in Thread: " + e);
            }
        });

        taskThread.start();
        // Allow the thread to run for a bit.
        Thread.sleep(1000);
        // Interrupt the thread
        taskThread.interrupt();
    }
}

```

In this example, a thread is started to perform a potentially infinite task (calculating the sine of increasing
integers). After a second, it is interrupted. The thread checks for interruption and exits the loop if it has been
interrupted, thus stopping the task gracefully.

**Scenarios Where `interrupt()` Is Essential**:

- **Cancelling a long-running task**: As shown in the example, interrupting a long processing task allows the
  application to regain control and potentially direct resources to more critical tasks.
- **Implementing application shutdown**: For a clean shutdown, threads can be interrupted to stop their processing and
  close resources.
- **Handling user cancellations**: In user-driven applications, tasks that no longer are needed based on user input can
  be stopped using interrupts.

Using **Thread.interrupt()** allows for safe and responsive thread management, crucial for developing robust
multi-threaded Java applications. This approach promotes a smooth operational flow, especially in resource-sensitive
environments or applications requiring high responsiveness.

### Strategies for Deadlock Management (20 minutes)

Effectively managing deadlocks in concurrent programming involves implementing strategies to prevent, avoid, or detect
these scenarios. These strategies help ensure that software systems operate efficiently and remain robust against
deadlock conditions.

**Deadlock Prevention Techniques**:
Deadlock prevention strategies aim to eliminate one or more of the necessary conditions for a deadlock to occur.

- **Careful Lock Ordering**: To prevent circular waits, ensure that all threads acquire locks in a consistent global
  order. Typically, this involves defining a numerical or logical order for locks and enforcing that locks are always
  acquired in ascending order.

**Example Code for Careful Lock Ordering**:

```java
package org.example.codealong;

public class LockOrder {
    private final Object Lock1 = new Object();
    private final Object Lock2 = new Object();

    /**
     * Method that corrects lock acquisition order based on system hash codes
     * to prevent deadlocks.
     */
    void correctMethod() {
        int firstId = System.identityHashCode(Lock1);
        int secondId = System.identityHashCode(Lock2);

        // Determine the lock order based on the system identity hash codes
        Object firstLock = firstId < secondId ? Lock1 : Lock2;
        Object secondLock = firstId < secondId ? Lock2 : Lock1;

        // Acquire locks in the determined order
        synchronized (firstLock) {
            synchronized (secondLock) {
                // perform actions within the synchronized block
                System.out.println("Both locks have been acquired in order and actions can be performed safely.");
            }
        }
    }

    public static void main(String[] args) {
        LockOrder lockOrder = new LockOrder();
        lockOrder.correctMethod();
    }
}

```

This code snippet ensures that locks are always acquired in the same global order, thus preventing circular wait
conditions regardless of which thread accesses them.

- The `System.identityHashCode()` function is used to obtain a consistent hash code for each lock object. This is
  helpful to avoid reliance on the default `hashCode()` method, which might be overridden.
- The locks are then sorted based on their hash codes. This ordering determines which lock is acquired first, ensuring
  that the lock acquisition order is consistent across all threads.
- The thread synchronizes on the first lock and then on the second lock within the nested synchronized block. This
  ordering avoids circular wait conditions—one of the four necessary conditions for a deadlock.
- Within the nested synchronized block, you can place the code that needs both resources locked.

**Lock Timeout**: Implementing a timeout when trying to acquire a lock can prevent a thread from indefinitely waiting.
If a lock is not available within the timeout period, the thread can release any locks it already holds and retry later,
thus avoiding deadlocks.

**Example Code for Lock Timeout Using `tryLock()`**:

```java
package org.example.codealong;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;

public class LockTimeoutExample {
    private final Lock lock1 = new ReentrantLock();
    private final Lock lock2 = new ReentrantLock();

    public void tryToLockBothLocks() throws InterruptedException {
        boolean lock1Acquired = false;
        boolean lock2Acquired = false;

        try {
            // Try to acquire both locks without waiting indefinitely
            lock1Acquired = lock1.tryLock(1000, TimeUnit.MILLISECONDS);
            lock2Acquired = lock2.tryLock(1000, TimeUnit.MILLISECONDS);

            if (lock1Acquired && lock2Acquired) {
                // Both locks have been acquired
                System.out.println("Both locks acquired");
                // Perform some operations here
            } else {
                // Could not acquire one or both locks
                System.out.println("Could not acquire both locks");
            }
        } finally {
            // Release each lock if it was acquired
            if (lock1Acquired) {
                lock1.unlock();
                System.out.println("Lock 1 released");
            }
            if (lock2Acquired) {
                lock2.unlock();
                System.out.println("Lock 2 released");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        LockTimeoutExample example = new LockTimeoutExample();
        example.tryToLockBothLocks();
    }
}

```

- **tryLock with Timeout**: The method `tryLock(1000, TimeUnit.MILLISECONDS)` attempts to acquire the lock within 1
  second. If the lock is not available during this time, it returns `false`.
- **Handling Locks**: The method tries to acquire both `lock1` and `lock2`. If both are not acquired successfully, it
  avoids a potential deadlock by not waiting indefinitely.
- **Resource Management**: Any acquired lock is released in the `finally` block, ensuring no lock remains held if not
  needed, thus preventing potential deadlocks from improperly released locks.

### Deadlock Avoidance Techniques (10 minutes)

**Banker’s Algorithm**: This is a popular deadlock avoidance algorithm used in operating systems. It simulates the
allocation for each process before actually allocating the resources. This simulation helps to ensure that the system
will remain in a safe state (not leading to deadlock).

```java
package org.example.codealong;

public class BankersAlgorithm {
    private final int numberOfResources;
    private final int numberOfProcesses;
    private final int[] available;  // Available amount of each resource
    private int[][] maximum;  // Maximum demand of each process
    private final int[][] allocation;  // Current allocation to each process
    private final int[][] need;  // Remaining needs of each process

    public BankersAlgorithm(int numberOfResources, int numberOfProcesses, int[] available, int[][] maximum, int[][] allocation) {
        this.numberOfResources = numberOfResources;
        this.numberOfProcesses = numberOfProcesses;
        this.available = available;
        this.maximum = maximum;
        this.allocation = allocation;
        this.need = new int[numberOfProcesses][numberOfResources];

        // Initialize the need matrix
        for (int i = 0; i < numberOfProcesses; i++) {
            for (int j = 0; j < numberOfResources; j++) {
                need[i][j] = maximum[i][j] - allocation[i][j];
            }
        }
    }

    // Check if the system is in a safe state
    private boolean checkSafe() {
        boolean[] finish = new boolean[numberOfProcesses];
        int[] work = available.clone();
        boolean found;

        do {
            found = false;
            for (int i = 0; i < numberOfProcesses; i++) {
                if (!finish[i]) {
                    boolean possible = true;
                    for (int j = 0; j < numberOfResources; j++) {
                        if (need[i][j] > work[j]) {
                            possible = false;
                            break;
                        }
                    }
                    if (possible) {
                        for (int k = 0; k < numberOfResources; k++) {
                            work[k] += allocation[i][k];
                        }
                        finish[i] = true;
                        found = true;
                    }
                }
            }
        } while (found);

        // Check if all processes could finish
        for (boolean completed : finish) {
            if (!completed) {
                return false;
            }
        }
        return true;
    }

    // Attempt to request resources for a process
    public boolean requestResources(int processId, int[] request) {
        for (int i = 0; i < numberOfResources; i++) {
            if (request[i] > need[processId][i] || request[i] > available[i]) {
                System.out.println("Request cannot be granted immediately.");
                return false;
            }
        }

        // Try to allocate requested resources temporarily
        for (int i = 0; i < numberOfResources; i++) {
            available[i] -= request[i];
            allocation[processId][i] += request[i];
            need[processId][i] -= request[i];
        }

        // Check if the system is still in a safe state
        if (checkSafe()) {
            System.out.println("Request can be granted safely.");
            return true;
        } else {
            // Rollback the allocation since it's not safe
            for (int i = 0; i < numberOfResources; i++) {
                available[i] += request[i];
                allocation[processId][i] -= request[i];
                need[processId][i] += request[i];
            }
            System.out.println("Request cannot be granted as it leads to an unsafe state.");
            return false;
        }
    }

    public static void main(String[] args) {
        int[] available = {3, 3, 2};  // Example resource instances
        int[][] maximum = {{7, 5, 3}, {3, 2, 2}, {9, 0, 2}, {2, 2, 2}, {4, 3, 3}};
        int[][] allocation = {{0, 1, 0}, {2, 0, 0}, {3, 0, 2}, {2, 1, 1}, {0, 0, 2}};

        BankersAlgorithm banker = new BankersAlgorithm(3, 5, available, maximum, allocation);

        // Example request
        // Example resource request by Process 1
        int[] request1 = {0, 2, 0};  // Process 1 requests 0 units of R0, 2 units of R1, and 0 units of R2
        System.out.println("Process 1 is requesting resources: " + java.util.Arrays.toString(request1));
        boolean requestResult = banker.requestResources(1, request1);
        if (requestResult) {
            System.out.println("The resources were successfully allocated to Process 1.");
        } else {
            System.out.println("The request by Process 1 could not be granted without risking a deadlock.");
        }
    }
}

```

This example demonstrates how to manage resource allocation among multiple processes while ensuring that the system
remains in a safe state, thus avoiding deadlocks.

The Banker's Algorithm uses several matrices and vectors to track the current state of the system, including the
available resources, the maximum demand of each process, the current allocation, and the remaining needs of each
process. Before a resource request is granted, the algorithm simulates whether fulfilling the request will leave the
system in a safe state.

### Introduction to Thread Interruption (10 minutes)

**Concept of Interrupts in Thread Life-Cycle Management**:

In multithreaded programming, effective thread management is essential for creating efficient and robust applications.
An important component of this is the ability to interrupt threads, which serves as a polite notification signaling a
thread to terminate or change its course of action. This feature is particularly useful in managing long-running or
blocking operations that may otherwise continue indefinitely.

In Java, the main mechanism for interrupting a thread is the `Thread.interrupt()` method, which does not stop the thread
outright but sets an interrupt flag. The thread can then periodically check this flag and gracefully conclude its
operations, allowing for proper resource release and state saving.

For example, imagine a chef (`thread`) in a busy kitchen who is tasked with preparing a complex dish that takes hours to
cook. If the restaurant suddenly needs to close (`interrupt`), the chef checks this signal and instead of continuing
with the dish, starts cleaning up and securing the kitchen. This orderly shutdown process parallels how a thread can
handle an interrupt in programming, ensuring that tasks are concluded properly without abrupt stops.

**Practical Use Cases for Interrupting Threads**:

Interrupting threads has several practical applications in software development:

- **Cancellation of Operations**: In user-driven applications or services, users might want to cancel operations that
  are taking too long, such as a file download or a complex calculation. Interrupts allow these lengthy operations to be
  stopped in a controlled manner.
- **Timeouts**: For operations that might hang or take longer than a certain period (e.g., network operations or file
  I/O), interrupts can be used to implement timeouts.
- **Service or Application Shutdown**: During the shutdown phase of an application, all running threads need to be
  stopped. Interrupts can signal these threads to terminate, allowing them to conclude their operations gracefully.
- **Cooperative Multitasking**: In complex applications, managing different tasks that share resources can require
  frequently pausing and resuming tasks. Interrupts can signal a thread to pause its operation so that other threads can
  perform necessary tasks.

### Code along : Using `Thread.interrupt()` in Java (20 minutes)

```java
package org.example.codealong;

public class InterruptExample implements Runnable {
    public void run() {
        int count = 0;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                // Simulate a task that takes time
                Thread.sleep(1000);
                System.out.println("Processing " + (++count));
            } catch (InterruptedException e) {
                System.out.println("Interrupted during sleep");
                // Properly handling InterruptedException by breaking the loop
                break;
            }
        }
        System.out.println("Thread exiting under request...");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread myThread = new Thread(new InterruptExample());
        myThread.start();

        // Let the thread run for a bit
        Thread.sleep(3500);

        // Interrupt the thread
        System.out.println("Requesting interrupt");
        myThread.interrupt();

        // Wait for the thread to finish
        myThread.join();
        System.out.println("Thread has stopped.");
    }
}

```

In the above example, the thread employs a methodical approach to manage its execution and potential interruption. It
routinely checks its interruption status using `Thread.currentThread().isInterrupted()`, ensuring it remains responsive
to stop requests. If an interruption occurs while the thread is sleeping, it responds by catching
an `InterruptedException`.

This exception triggers the thread to exit its current operational loop through a break statement, a widely used
practice for cleaning up and halting a thread. Following the interruption, the thread wraps up any pending tasks, if
necessary, before completing its execution gracefully. This method ensures that all resources are properly released and
that the thread does not terminate abruptly, maintaining system stability and data integrity.

### Exploring Java's Thread.interrupt() Method (10 minutes)

**Deep Dive into the `Thread.interrupt()` Method**:

The `Thread.interrupt()` method in Java plays a crucial role in managing the execution flow of threads, providing a
mechanism to request a thread to stop its current tasks. This method does not actually terminate a thread; instead, it
sets the thread's interrupt status to true. It's important to understand that the behavior of `interrupt()` depends
significantly on the state of the thread at the moment of interruption.

**How Thread.interrupt() Works**:

When `interrupt()` is called on a thread, if the thread is engaged in a non-blocking operation, it continues execution
but with its interrupt flag set. The thread must periodically check this flag by calling `Thread.interrupted()`
or `Thread.isInterrupted()`. These methods help the thread determine whether it should voluntarily terminate, typically
by returning from its run method or by throwing an exception that ends its execution.

If the thread is currently blocked in an invocation of `wait()`, `sleep()`, or `join()`, it will immediately throw
an `InterruptedException`. This exception is a clear signal that the thread should cease its blocked state and handle
the interruption, usually by cleaning up and terminating.

**Impact on Thread States**:

The `interrupt()` method can affect thread states in several ways:

- `Blocked State`: A thread in a blocked state due to `wait()`, `sleep()`, or `join()` is thrown into a `Runnable` state
  when interrupted, indicated by the throwing of `InterruptedException`.
- `Runnable State`: A thread in the runnable state will have its interrupt flag set but continue execution until it
  checks its interrupt status.
- `Terminated State`: If the interruption leads to the completion of the thread's run method or an
  unhandled `InterruptedException`, the thread moves to the terminated state.

**Scenarios Where `interrupt()` is Essential**:

One of the most common uses for `interrupt()` is to provide a safe way to stop a thread that is performing a
long-running task. This could be a computation-intensive task, an I/O operation, or any process that takes an
indeterminate amount of time to complete. For example, a thread running a heavy simulation or data analysis might need
to be stopped if the user cancels the operation or if new, overriding commands are received.

### Code along Canceling a Long-Running Task (15 minutes)

```java
package org.example.codealong;

public class LongRunningTask implements Runnable {
    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        long elapsedTime = 0L;
        while (!Thread.currentThread().isInterrupted() && elapsedTime < 60000) { // run for up to 60 seconds
            try {
                // Simulate a task that takes time (e.g., processing data)
                System.out.println("Task is running...");
                Thread.sleep(10000); // Sleep for 10 seconds
                elapsedTime = System.currentTimeMillis() - startTime;
            } catch (InterruptedException e) {
                System.out.println("Thread was interrupted during sleep. Cleaning up and stopping the task.");
                Thread.currentThread().interrupt(); // Optional: reset the interrupt flag
                break;
            }
        }
        if (!Thread.currentThread().isInterrupted()) {
            System.out.println("Task completed without interruption.");
        } else {
            System.out.println("Task was interrupted and has now stopped.");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread taskThread = new Thread(new LongRunningTask());
        taskThread.start();

        // Interrupt the thread after a short delay to demonstrate interruption
        Thread.sleep(15000); // Allow the thread to run for 15 seconds before interrupting
        taskThread.interrupt();

        // Wait for the thread to finish execution
        taskThread.join();
        System.out.println("Main thread has completed execution.");
    }
}

```

### Handling InterruptedExceptions (5 minutes)

In Java, the `InterruptedException` is thrown when a thread that is sleeping, waiting, or otherwise occupied is
interrupted. This exception provides a mechanism for the thread to handle being stopped prematurely and perform
necessary cleanup operations. The thread must check for and handle this exception in situations where it might be
thrown, such as when calling methods like `Thread.sleep()`, `Object.wait()`, or `Thread.join()`.

When a thread catches an `InterruptedException`, it's essential to respond appropriately to ensure the program remains
stable and resources are not left in an inconsistent state. Here are the steps to handle an `InterruptedException`:

- `Restore the Interrupted Status`: After catching `InterruptedException`, you should restore the interrupt status by
  calling `Thread.currentThread().interrupt()`. This allows other parts of the program to recognize that an interrupt
  was issued, enabling further handling up the call stack.
- `Clean Up Resources`: Perform any necessary cleanup of resources, such as closing file streams or releasing locks, to
  avoid resource leaks and ensure data consistency.
- `Exit Gracefully`: If the operation cannot continue due to the interruption, make sure the thread exits cleanly
  without causing side effects that might affect the rest of the application.

### Code along Responding to Interruption (15 minutes)

Here’s a practical example showing how to handle interrupts effectively in Java:

```java
package org.example.codealong;

public class TaskRunner implements Runnable {
    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                // Simulate a task that can be interrupted
                System.out.println("Running task");
                Thread.sleep(1000); // Thread may be interrupted during sleep
            }
        } catch (InterruptedException e) {
            System.out.println("Thread was interrupted, stopping...");
            Thread.currentThread().interrupt(); // Restore interrupted status
            // Perform any necessary cleanup tasks here before exiting
        } finally {
            cleanUp();
        }
        System.out.println("Thread exiting gracefully");
    }

    private void cleanUp() {
        // Cleanup code here, e.g., close files, release locks, etc.
        System.out.println("Cleaning up resources");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new TaskRunner());
        thread.start();
        // Allow the thread to run for a bit
        Thread.sleep(3000);
        thread.interrupt(); // Interrupt the thread
        thread.join(); // Wait for the thread to finish execution
        System.out.println("Main thread exiting");
    }
}

```

In the above example,

- The thread in this example runs a loop that checks for interruption. It simulates a task by sleeping for one second in
  each iteration.
- Handling `InterruptedException`: If the thread is interrupted during sleep, it catches the `InterruptedException`,
  restores the interrupt status, and exits the loop.
- `Cleanup and Exit`: The `finally` block ensures that resource cleanup is performed regardless of how the thread exits
  the loop, guaranteeing no resources are left dangling.

### Introduction to Inter-thread Communication (10 minutes)

Inter-thread communication is crucial in multi-threaded environments where threads need to coordinate their actions or
share data efficiently. Proper synchronization between threads can significantly enhance application performance,
prevent race conditions, and ensure consistent states.

**Importance of Efficient Inter-thread Communication**:

Effective communication between threads is essential for:

- Minimizing CPU usage by avoiding busy-waiting.
- Ensuring data is safely shared and updated between threads.
- Coordinating workflow in complex multi-threaded applications.

#### Introduction to wait(), notify(), and notifyAll() Methods in Java

Java provides built-in support for inter-thread communication through the object monitoring
methods: `wait()`, `notify()`, and `notifyAll()`. These methods are used to coordinate the activities of threads:

- `wait()`: Causes the current thread to wait until another thread invokes `notify()` or `notifyAll()` on the same
  object.
- `notify()`: Wakes up a single thread that is waiting on the object's monitor.
- `notifyAll()`: Wakes up all threads that are waiting on the object's monitor.

### Understanding the Wait-Notify Protocol (10 minutes)

The wait-notify mechanism relies on synchronized blocks and uses the intrinsic locks, or monitor locks, of objects to
manage inter-thread communication. Here's a breakdown of how it typically functions:

**Lock Acquisition**: Before a thread can invoke `wait()`, `notify()`, or `notifyAll()`, it must secure the lock on the
object associated with these methods.
**Waiting**: Upon executing `wait()`, a thread relinquishes the lock on the object and transitions into a waiting state,
where it remains until it receives a notification.
Notification: Another thread may call `notify()` or `notifyAll()` on the same object. This action signals one or all
waiting threads, respectively, that they may proceed with execution.

**Common Pitfalls and Prevention Strategies**:

- Spurious Wakeups: Occasionally, a thread might awaken without receiving a notification. To handle this, use a while
  loop to continuously check the condition under which the thread is waiting.
- Missed Signals: If `notify()` is activated before a thread enters the `wait()` state, the notification is lost. To
  prevent this, ensure that the conditions prompting the wait-notify logic are stable and verified before a thread
  initiates the waiting process.

### Code along Wait-Notify Protocol (10 minutes)

Let's use a kitchen scenario to illustrate the inter-thread communication with the `wait()`, `notify()`,
and `notifyAll()`methods in Java, using a chef as an analogy.

Imagine a busy restaurant kitchen where two chefs are working together to prepare a complex dish. One chef (Chef A) is
responsible for the main course, while the other (Chef B) handles the sauce and garnishing, which cannot be started
until the main course is partially cooked.

- Chef A (The Main Course Chef): Prepares the main component of the dish. Once a critical point in the preparation is
  reached, Chef A needs to signal Chef B to start the sauce.
- Chef B (The Sauce Chef): Must wait until Chef A reaches that critical preparation point; otherwise, starting too early
  would ruin the synchrony of the dish.

```java
package org.example.codealong;

public class KitchenSync {
    private static final Object kitchenLock = new Object();
    private static boolean isMainCourseReady = false;

    public static void main(String[] args) {
        Thread chefA = new Thread(() -> {
            synchronized (kitchenLock) {
                try {
                    System.out.println("Chef A: Preparing the main course.");
                    Thread.sleep(2000);  // Simulate the cooking time
                    isMainCourseReady = true;
                    kitchenLock.notify();  // Chef A notifies that the main course is ready
                    System.out.println("Chef A: Main course ready, notifying Chef B.");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        Thread chefB = new Thread(() -> {
            synchronized (kitchenLock) {
                while (!isMainCourseReady) {
                    try {
                        System.out.println("Chef B: Waiting for the main course to be ready.");
                        kitchenLock.wait();  // Chef B waits for the main course to be ready
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
                System.out.println("Chef B: Notified that the main course is ready, starting the sauce.");
            }
        });

        chefB.start();  // Start Chef B's thread first
        chefA.start();  // Start Chef A's thread
    }
}

```

In the above example,

- **Lock and Monitor**: The kitchenLock object acts as the monitor, similar to a synchronization point in the kitchen
  where chefs coordinate their actions.
- **Waiting and Notifying**: Chef B waits until Chef A reaches a critical point in the cooking process and notifies him.
  This mimics how threads interact in Java, where one thread waits for a condition to be met and another thread signals
  the condition change.
- **Handling Delays and Coordination**: Just as chefs in a kitchen must handle timing carefully to ensure everything is
  cooked perfectly, threads must manage synchronization to ensure the program runs smoothly and efficiently.

### Wrap-up and Q&A (10 minutes)

In today's lecture, we emphasized the critical importance of inter-thread communication in multi-threaded applications,
highlighting its role in ensuring smooth operations and preventing issues like data corruption or inconsistent states.
We introduced Java's built-in communication mechanisms — `wait()`, `notify()`, and `notifyAll()` —which enable threads
to
communicate their states and coordinate actions within synchronized blocks or methods, thereby preventing operational
interference. We delved into the wait-notify protocol, explaining how `wait()` allows a thread to pause its execution
and
release its monitor until notified by another thread, while `notify()` wakes a single waiting thread and `notifyAll()`
wakes
all waiting threads on the same object's monitor. We also discussed common pitfalls, such as spurious wakeups and missed
signals, underscoring the best practices of using `wait()` in a loop to continually check the condition and ensuring
`notify()` or `notifyAll()` is executed after a condition change to avoid missed


