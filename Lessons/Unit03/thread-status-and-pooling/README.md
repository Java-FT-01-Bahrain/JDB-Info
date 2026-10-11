# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Thread Status And Pooling

| Title                     | Type    | Duration | Author               |
|---------------------------|---------|----------|----------------------|
| Thread Status And Pooling | Lecture | 2:10     | Suresh Melvin Sigera |

## Learning objectives

- Understand the different states of a thread in Java's thread lifecycle and their significance in concurrent
  programming.
- Explore the Java Executor framework and its components for managing thread pools and task execution.
- Learn about the advantages of using thread pools and scenarios where thread pooling is beneficial in real-world
  applications.
- Implement thread pools using Java's Executor framework and explore best practices for configuring and managing thread
  pools.
- Identify common pitfalls and performance issues when working with thread pools and learn strategies to avoid them.
- Gain practical experience through code-along examples demonstrating the implementation of thread pools and utilization
  of Executor factory methods.

### Introduction to Thread Status (40 minutes)

Understanding thread status and management in Java is crucial for developing efficient multithreaded applications. This
section introduces the different states of a thread in the Java Thread lifecycle and explains the significance of each.

**Definition and Significance of Thread States in Java**:

In Java, a thread can be in one of several states. These states are important for developers to understand as they
provide insights into thread behavior, resource management, and the efficiency of an application. Knowing these states
helps in debugging issues related to threading and ensuring that applications run smoothly without deadlock, excessive
CPU usage, or other concurrency issues.

**Overview of the Java Thread Lifecycle**:

The lifecycle of a thread in Java includes several states:

- **NEW**: The thread is created but not yet started.
- **RUNNABLE**: The thread is executing in the Java virtual machine (JVM).
- **BLOCKED**: The thread is blocked waiting for a monitor lock.
- **WAITING**: The thread is waiting indefinitely for another thread to perform a particular action.
- **TIMED_WAITING**: The thread is waiting for another thread to perform an action for up to a specified waiting time.
- **TERMINATED**: The thread has exited.

These states are governed by the `Thread.State` enum in Java, which helps in monitoring and controlling thread behavior.

Let’s explore each state with code examples to see how they work in practice.

**NEW** state:

```java
public class NewStateExample {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println("This is a new thread.");
        });
        System.out.println("Thread state: " + thread.getState());  // Output: NEW
    }
}
```

In this example, the thread is created but not yet started, so its state is `NEW`.

**RUNNABLE** state:

```java
public class RunnableStateExample {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                Math.sin(i);  // Some computation to keep the thread busy
            }
        });
        thread.start();
        System.out.println("Thread state (might be RUNNABLE or TERMINATED): " + thread.getState());
    }
}
```

Once a thread is started, it's state can be `RUNNABLE`. Note that the actual output might vary because the thread may
complete its execution before the state is printed, leading it to show `TERMINATED`.

**BLOCKED** state:

```java
public class BlockedStateExample {
    public static void main(String[] args) throws InterruptedException {
        final Object lock = new Object();

        Runnable runnable = () -> {
            synchronized (lock) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        t1.start();
        Thread.sleep(100);  // Give t1 time to start and acquire the lock
        t2.start();
        Thread.sleep(100);  // Give t2 time to attempt to acquire the lock
        System.out.println("Thread t2 state: " + t2.getState());  // Output: BLOCKED
    }
}
```

In this scenario, `t2` will be `BLOCKED` as it cannot enter the synchronized block while t1 holds the lock.

**WAITING** state:

```java
public class WaitingStateExample {
    public static void main(String[] args) throws InterruptedException {
        final Object lock = new Object();

        Runnable waitingTask = () -> {
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread waitingThread = new Thread(waitingTask);
        waitingThread.start();
        Thread.sleep(100);  // Allow some time for the thread to start and execute
        System.out.println("Thread state: " + waitingThread.getState());  // Output: WAITING
    }
}
```

This thread is in the `WAITING` state because it is waiting indefinitely for another thread to call `notify()`
or `notifyAll()` on the object's monitor.

**TIMED_WAITING** state:

```java
public class TimedWaitingStateExample {
    public static void main(String[] args) throws InterruptedException {
        final Object lock = new Object();

        Runnable timedWaitingTask = () -> {
            synchronized (lock) {
                try {
                    lock.wait(500);  // Wait for up to 500 milliseconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread timedWaitingThread = new Thread(timedWaitingTask);
        timedWaitingThread.start();
        Thread.sleep(100);  // Allow some time for the thread to start and execute
        System.out.println("Thread state: " + timedWaitingThread.getState());  // Output: TIMED_WAITING
    }
}

```

Here, the thread is in the `TIMED_WAITING` state because it is waiting for a notification for up to 500 milliseconds.

**TERMINATED state**:

```java
public class TerminatedStateExample {
    public static void main(String[] args) throws InterruptedException {
        Thread completedThread = new Thread(() -> {
            System.out.println("Running and finishing.");
        });
        completedThread.start();
        Thread.sleep(100);  // Wait long enough for the thread to complete
        System.out.println("Thread state: " + completedThread.getState());  // Output: TERMINATED
    }
}
```

This thread has completed its execution and has therefore moved to the `TERMINATED` state.

### Controlling Thread Status (10 minutes)

In Java, controlling thread execution is fundamental to achieving proper synchronization and managing concurrency. The
`Thread` class provides several methods to control the life cycle of threads. Below, we'll explore these
methods —`start()`, `sleep()`, `yield()`, `join()`, and `interrupt()` — and see how they can be used to manage thread
execution. To illustrate their usage, we will draw an analogy with a bustling restaurant kitchen where multiple chefs (
threads) work under the head chef’s supervision to coordinate and manage their tasks effectively. This ensures meals are
prepared smoothly and efficiently.

- **start()** : A new chef starts their shift in the kitchen.
    - Java Explanation: Similar to invoking the `start()` method on a thread, which initiates the thread’s execution, a
      new chef beginning their shift represents them starting their series of tasks in the kitchen.
- **sleep()**: The chef takes a short break between preparing dishes.
    - Java Explanation: The `sleep()` method pauses the thread for a specified amount of time. In the kitchen, a chef
      might take a brief pause after a rush of orders, allowing other kitchen activities to proceed before they resume.
- **yield()**: A chef momentarily steps back to let another chef handle a task that needs immediate attention.
    - Java Explanation: When a thread calls `yield()`, it signals that it's willing to relinquish its current use of the
      processor, although it's still ready to execute. In the kitchen, this can be seen when a chef steps aside to allow
      another chef to quickly use a particular kitchen station.
- **join()** : Waiting for a specific chef to finish preparing a part of the meal before the next steps can be
  completed.
    - Java Explanation: By using `join()`, one thread waits for another to finish its task completely before it
      proceeds. In kitchen terms, this might occur when a dessert chef waits for the pastry base prepared by another
      chef to complete a dish.
- **interrupt()**: Urgently calling a chef to switch tasks due to an unexpected situation, like a sudden change in order
  priorities.
    - Java Explanation: The `interrupt()` method is used to signal a thread that it should stop what it’s doing and do
      something else, similar to a chef being asked to stop working on a less urgent task to address a more immediate
      need, such as a special request from a VIP customer.

These methods help manage how chefs (threads) operate within the restaurant kitchen (the program), ensuring that tasks
are handled efficiently and effectively, much like how threads need to be managed in a computer program for optimal
performance and resource utilization. This analogy provides a vivid and relatable understanding of how thread management
functions in Java, illustrating the practical implications of each method in a real-world setting.

### Code along: Building a Multi-Threaded Java Application (15 minutes)

In this enhanced code along, we'll delve deeper into Java's thread control methods by simulating a complex restaurant
kitchen scenario. Our chefs, named Alice, Bob, Charlie, and Suresh, will demonstrate the use of
the `start()`, `sleep()`, `yield()`, `join()`, and `interrupt()` methods to manage their concurrent tasks effectively.

```java
package org.example.codealong;

class Chef implements Runnable {
    private final String name;
    private static final String[] dishes = {"salad", "soup", "steak", "pasta", "dessert"};
    private volatile boolean interrupted = false; // Use volatile for thread safety

    public Chef(String name) {
        this.name = name;
    }

    public void interruptChef() {
        interrupted = true;
    }

    @Override
    public void run() {
        for (String dish : dishes) {
            if (interrupted) {
                System.out.println(name + " received an urgent call from the supplier and must step away immediately.");
                return; // Exit the loop and terminate the thread's task
            }
            System.out.println(name + " is preparing " + dish);
            try {
                Thread.sleep(1000);  // Simulate time taken to prepare a dish
            } catch (InterruptedException e) {
                System.out.println(name + " was interrupted while sleeping and will now halt the task.");
                return;  // This line cleanly exits the thread if it's interrupted during sleep
            }
        }
    }
}

public class Restaurant {
    public static void main(String[] args) throws InterruptedException {
        Chef chef1 = new Chef("Alice");
        Thread chef1Thread = new Thread(chef1, "Alice");
        Chef chef2 = new Chef("Bob");
        Thread chef2Thread = new Thread(chef2, "Bob");
        Chef chef3 = new Chef("Charlie");
        Thread chef3Thread = new Thread(chef3, "Charlie");
        Chef chef4 = new Chef("Suresh");
        Thread chef4Thread = new Thread(chef4, "Suresh");

        chef1Thread.start();  // Start Alice's thread
        chef2Thread.start();  // Start Bob's thread
        chef3Thread.start();  // Start Charlie's thread
        chef4Thread.start();  // Start Suresh's thread

        Thread.sleep(2500);  // Wait a bit to ensure Bob is in the middle of his tasks

        // Interrupt Bob's thread using the custom method
        chef2.interruptChef();

        chef1Thread.join();  // Wait for Alice to finish
    }
}
```

In this Java application, we simulate a restaurant environment where multiple chefs, represented by threads,
concurrently prepare different dishes. The core focus of this example is to demonstrate both Java's built-in thread
interruption mechanisms and a custom interruption technique implemented through a `volatile boolean` flag. This dual
approach showcases flexibility in managing thread interruptions in a Java application.

### Thread Pooling (10 minutes)

Thread pooling is a crucial concept in concurrent programming, especially in scenarios where multiple tasks need to be
executed concurrently while efficiently managing system resources.

**Definition and advantages of using thread pools**:

A thread pool is a managed collection of pre-initialized threads, ready to execute tasks. Instead of creating a new
thread for each task, a thread pool maintains a set of worker threads, allowing tasks to be submitted for execution.
This approach offers several advantages:

- **Improved Performance**: By reusing existing threads, thread pools reduce the overhead of thread creation, leading to
  improved performance and responsiveness.
- **Resource Management**: Thread pools provide a mechanism for controlling the number of concurrent threads, preventing
  resource exhaustion and excessive context switching.
- **Scalability**: Thread pools can adapt to varying workloads by adjusting the size of the thread pool dynamically.
- **Simplified Task Management**: Task submission and execution are decoupled, allowing developers to focus on the logic
  of the tasks rather than thread management.

**Scenarios Where Thread Pooling is Beneficial**:

Thread pooling is particularly useful in scenarios involving:

- **Server Applications**: Web servers, application servers, and other server-based applications often handle multiple
  client requests concurrently. Thread pools can efficiently manage the execution of these requests, ensuring optimal
  resource utilization.
- **Asynchronous Processing**: In applications requiring asynchronous processing, such as event-driven systems or
  message processing frameworks, thread pools facilitate the execution of tasks in the background without blocking the
  main thread.
- **Batch Processing**: Tasks involving batch processing, data processing, or parallel computations can benefit from
  thread pooling to parallelize execution and improve throughput.

**Overview of Java's Executor Framework**:

Java's Executor framework provides a high-level abstraction for thread management and task execution. It consists of the
following key components:

- **Executor Interface**: Represents an object capable of executing tasks. It defines a single method, execute(Runnable
  task), for submitting tasks for execution.
- **ExecutorService Interface**: Extends the Executor interface and provides additional methods for managing the
  execution of tasks, such as submitting tasks with return values, controlling the lifecycle of the executor, and
  obtaining the status of submitted tasks.
- **ThreadPoolExecutor Class**: A concrete implementation of the ExecutorService interface, ThreadPoolExecutor manages a
  pool of worker threads, allowing tasks to be executed concurrently. It provides configurable parameters for thread
  pool size, task queueing, and thread lifecycle management.
- **Executors Utility Class**: Provides factory methods for creating pre-configured instances of thread pools, such as
  fixed-size thread pools, cached thread pools, and scheduled thread pools.

### Code along a thread pooling in Java using the Executor (15 minutes)

```java
package org.example.codealong;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExample {
    public static void main(String[] args) {
        // Create a fixed-size thread pool with 3 worker threads
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Submit tasks for execution
        for (int i = 1; i <= 10; i++) {
            final int taskId = i;
            executor.submit(() -> {
                System.out.println("Task " + taskId + " is being executed by thread: " + Thread.currentThread().getName());
                try {
                    // Simulate task execution time
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Task " + taskId + " completed.");
            });
        }

        // Shutdown the executor after all tasks are completed
        executor.shutdown();
    }
}

```

In this example, we create a fixed-size thread pool with three worker threads using `Executors.newFixedThreadPool(3)`.
We
then submit ten tasks for execution, each printing its ID and the name of the executing thread. Finally, we shut down
the executor to release the associated resources once all tasks are completed.

### Implementing Thread Pools (10 minutes)

Implementing thread pools in Java is vital for effective concurrent programming. To understand thread pools, imagine a
kitchen in a restaurant. Here, the thread pool represents the kitchen, and the threads are like chefs. Each chef (
thread) is responsible for preparing different dishes (tasks). This analogy helps illustrate how a thread pool manages a
group of threads that execute tasks concurrently.

A thread pool controls the number of threads in operation, ensuring that the system isn't overwhelmed by managing too
many threads at once, much like a kitchen manages a limited number of chefs to efficiently handle the cooking without
overcrowding the space. This setup not only optimizes resource utilization but also improves the application's
performance by reducing the overhead associated with creating and destroying threads repeatedly.

The Java Executor framework facilitates this by providing a suite of tools to create and manage thread pools with ease.
Using this framework, developers can tailor the execution environment of their concurrent applications, enhancing
flexibility and control over thread operations. This approach simplifies the management of concurrent tasks, allowing
for scalable and efficient application development.

**Using Executors Factory Methods**:

Java's Executors class provides several factory methods for creating different types of thread pools:

- **newFixedThreadPool(int nThreads)**: Creates a fixed-size thread pool with a specified number of worker threads.
  Tasks are queued if all threads are busy.
- **newCachedThreadPool()**: Creates a thread pool that dynamically adjusts its size based on the workload. Threads are
  added or removed as needed.
- **newSingleThreadExecutor()**: Creates a thread pool with a single worker thread. Tasks are queued and executed
  sequentially.

**Best Practices for Configuring and Managing Thread Pools**:

When configuring and managing thread pools, consider the following best practices:

- **Choosing the Right Pool Size**: Determine the optimal number of threads based on the application's workload and
  available resources. Avoid overloading the system with too many threads or underutilizing resources with too few
  threads.
- **Setting Task Queue Capacity**: Use bounded queues to prevent excessive memory consumption in scenarios with high
  task submission rates. Choose an appropriate queue capacity based on expected workload and system constraints.
- **Monitoring and Tuning**: Regularly monitor thread pool metrics, such as thread utilization, task throughput, and
  queue size. Adjust the thread pool configuration as needed to optimize performance and resource utilization.
- **Handling Exceptions**: Implement proper error handling mechanisms to handle exceptions thrown by tasks. Consider
  using a ThreadFactory to customize thread creation and exception handling behavior.
- **Graceful Shutdown**: Ensure proper shutdown of the thread pool to release associated resources gracefully. Use the
  shutdown() or shutdownNow() methods to initiate shutdown and awaitTermination() to wait for tasks to complete before
  proceeding.

**Handling Common Pitfalls and Performance Issues**:

Avoid common pitfalls and performance issues when working with thread pools:

- **Thread Starvation**: Ensure fair distribution of tasks among worker threads to prevent thread starvation. Consider
  using thread prioritization or custom task scheduling strategies.
- **Deadlock and Livelock**: Avoid potential deadlock and livelock situations by carefully designing task dependencies
  and resource access patterns. Use thread-safe data structures and synchronization mechanisms to prevent concurrent
  access issues.
- **Memory Leaks**: Be mindful of potential memory leaks caused by long-lived threads or improperly managed resources.
  Dispose of thread-local resources and clean up thread-related objects when no longer needed.

### Code along Executors Factory Methods (15 minutes)

```java
package org.example.codealong;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorsFactoryMethods {
    public static void main(String[] args) {
        // Fixed-size thread pool
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        // Cached thread pool
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        // Single thread pool
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

        // Submit tasks for execution
        for (int i = 1; i <= 10; i++) {
            final int taskId = i;
            fixedThreadPool.submit(() -> {
                System.out.println("Task " + taskId + " executed by fixedThreadPool");
                // Simulate task execution time
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            cachedThreadPool.submit(() -> {
                System.out.println("Task " + taskId + " executed by cachedThreadPool");
                // Simulate task execution time
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            singleThreadExecutor.submit(() -> {
                System.out.println("Task " + taskId + " executed by singleThreadExecutor");
                // Simulate task execution time
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        // Shutdown the executors after all tasks are completed
        fixedThreadPool.shutdown();
        cachedThreadPool.shutdown();
        singleThreadExecutor.shutdown();
    }
}

```

In this example, we create three different types of thread pools using the Executors factory methods: a fixed-size
thread pool, a cached thread pool, and a single thread pool. We submit ten tasks for execution to each thread pool,
simulating task execution with a sleep delay. Finally, we shut down the executors to release resources after all tasks
are completed.

### Knowledge Check (10 minutes)

<details>
<summary>What are the different states of a thread in Java, and what triggers transitions between these states?</summary>
<br>

In Java, a thread can be in one of six states: New, Runnable, Blocked, Waiting, Timed Waiting, and Terminated.
Transitions between these states occur due to various actions:

- **New**: The thread has been created but not yet started.
- **Runnable**: The thread is executing in the JVM but may be waiting for resource allocation.
- **Blocked**: The thread is blocked waiting for a monitor lock.
- **Waiting**: The thread is waiting indefinitely for another thread to perform a particular action.
- **Timed Waiting**: The thread is waiting for another thread to perform an action for up to a specified waiting time.
- **Terminated**: The thread has completed its execution or was terminated prematurely.

</details>

<details>
<summary>Why use thread pools instead of creating new threads for each task? Can you discuss scenarios where thread pooling is particularly advantageous?</summary>
<br>

Thread pools are used instead of creating new threads for each task to save resources, reduce overhead associated with
thread creation and destruction, and to manage system stability. They are particularly advantageous in scenarios like
web servers handling multiple simultaneous HTTP requests, applications requiring background tasks management, or
real-time data processing systems where tasks occur frequently and need quick execution start.
</details>

<details>
<summary>What are some typical mistakes developers make when using thread pools? How can these be avoided?</summary>
<br>

- Misconfiguring the size of the thread pool, either overloading or underutilizing the system resources.
- Poor handling of thread exceptions which can lead to thread leakage.
- Inadequate management of task queue that may lead to memory overflow.

These issues can be avoided by carefully planning thread pool size based on system capability and task requirements,
implementing robust error handling within tasks, and using appropriate queue sizes and types for tasks.

</details>

<details>
<summary>How are thread pools utilized in real-world applications?</summary>
<br>

Thread pools are widely used in managing background tasks in web and application servers, handling requests in real-time
systems, and managing connection pools in database systems. They help in efficiently managing resources, reducing
latency, and improving application performance by reusing existing threads.
</details>

<details>
<summary>How would you determine the optimal size of a thread pool for a given application?</summary>
<br>
The optimal size of a thread pool depends on the characteristics of the tasks to be executed and the hardware capabilities of the system. It usually involves balancing the number of CPUs, the memory usage, the nature of the tasks (CPU-intensive vs. IO-intensive), and the overall performance goals of the application.
</details>

### Summary  (5 minutes)

In today's session, we delved into the functionality and benefits of Java thread pools, discussing their roles in
enhancing concurrency management and system efficiency, alongside a practical demonstration using a fixed thread pool
for a simple server. We also engaged in a comprehensive discussion on real-world applications and potential pitfalls,
enriching our understanding through practical examples and theoretical questions to better equip participants for
real-world implementation and further studies.






