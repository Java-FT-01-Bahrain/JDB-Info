# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Introduction to Concurrency in Java

| Title                               | Type   | Duration | Author               |
|-------------------------------------|--------|----------|----------------------|
| Introduction to Concurrency in Java | Lesson | 3:35     | Suresh Melvin Sigera |

## Learning objectives

**By the end of this lesson, students will be able to:**

- Understand the concept of concurrency in Java and its importance in software development.
- Differentiate between process-based concurrency and thread-based concurrency.
- Identify the states of a thread in Java and explain their significance.
- Implement concurrency in Java using the `Thread` class and the `Runnable` interface.
- Describe the four common states of a thread in Java (New, Runnable, Blocked/Waiting, Terminated).
- Analyze scenarios where concurrency can enhance performance, responsiveness, and resource utilization in software
  systems.

### Introduction to Concurrency (10 minutes)

Concurrency refers to the ability of a system to execute multiple tasks or processes simultaneously. In a concurrent
system, various activities progress independently, potentially overlapping in time, allowing for efficient resource
utilization and increased throughput.

An apt analogy for concurrency can be drawn from a bustling kitchen, where multiple chefs work concurrently on different
dishes. Each chef focuses on their assigned task, such as chopping vegetables, grilling meat, or preparing sauces, all
happening concurrently. While one chef is sautéing vegetables, another might be grilling steak, and yet another could be
plating a finished dish. Despite the simultaneous execution of these tasks, the kitchen operates smoothly, optimizing
efficiency and minimizing wait times for customers.

However, managing concurrency comes with its challenges, much like orchestrating a kitchen's operations. Coordination
and synchronization are crucial to ensure that tasks are completed correctly and in the proper order. Without effective
synchronization, conflicts may arise, such as two chefs attempting to use the same ingredient simultaneously or trying
to access a limited resource like an oven at the same time. Similarly, in software development, concurrent execution
requires careful consideration of synchronization mechanisms to prevent issues like race conditions and data
inconsistencies. Just as a head chef in a kitchen coordinates the activities of various chefs to ensure a harmonious
workflow, in software, developers utilize concurrency control techniques to manage and synchronize multiple threads
effectively.

### Definition of concurrency (10 minutes)

Within Java, concurrent programming is facilitated through built-in support for running multiple threads concurrently
within a single program. A thread, often referred to as a lightweight process, represents a distinct sequential flow of
programming operations, commencing with a clear start and concluding at a defined end. Throughout a thread's lifecycle,
only one point of execution exists.

It's worth noting that a thread, on its own, cannot function as a standalone program; instead, it operates within the
context of a program. The following illustration depicts a program featuring three threads executing under a single CPU:

```text
   +-----------------+
   |                 |
   |    Program      |
   |                 |
   +--------+--------+
            |
   +--------v--------+
   |                 |
   |   CPU (Core)    |
   |                 |
   +-----------------+
            |
   +--------v--------+
   |                 |
   |    Thread 1     |
   |                 |
   +-----------------+
            |
   +--------v--------+
   |                 |
   |    Thread 2     |
   |                 |
   +-----------------+
            |
   +--------v--------+
   |                 |
   |    Thread 3     |
   |                 |
   +-----------------+
```

In the diagram:

- The "Program" represents the entire software application.
- The "CPU (Core)" symbolizes the central processing unit, which executes instructions.
- Three threads, labeled as "Thread 1", "Thread 2", and "Thread 3", are depicted running concurrently under the CPU.
- Each thread represents a single sequential flow of programming operations within the program.

### Why concurrency is important in software development (10 minutes)

Concurrency is paramount in software development due to its ability to **enhance performance**, **responsiveness**, and
**resource utilization** in modern computing environments. Below are several key reasons why concurrency is vital in
software development:

- Improved Performance: Concurrency allows programs to execute multiple tasks simultaneously, thereby reducing overall
  execution time. For example, in a web server application, concurrent handling of multiple client requests enables
  efficient resource utilization and faster response times. By utilizing concurrency, software systems can make better
  use of available resources, leading to improved throughput and reduced latency.

- Enhanced Responsiveness: Concurrency enables applications to remain responsive even when performing time-consuming
  tasks. For instance, in a graphical user interface (GUI) application, concurrency ensures that user interactions, such
  as clicking buttons or typing text, are handled independently of background processes. This prevents the user
  interface from becoming unresponsive or frozen during lengthy computations or I/O operations.

- Scalability: Concurrency plays a crucial role in building scalable software systems that can handle increasing
  workloads without sacrificing performance. By utilizing multiple threads or processes, applications can distribute
  computational tasks across available resources, enabling horizontal scalability. This scalability is essential for web
  servers, database systems, and other distributed systems that need to accommodate growing user bases or data volumes.

- Resource Utilization: Concurrency allows efficient utilization of hardware resources, such as CPU cores and memory, by
  executing multiple tasks concurrently. In a multi-core system, concurrent execution enables parallel processing of
  tasks, maximizing CPU utilization and reducing idle time. Moreover, concurrency facilitates better utilization of I/O
  resources, such as disk drives and network interfaces, by overlapping I/O operations with computation.

- Asynchronous Programming: Concurrency facilitates asynchronous programming paradigms, which are essential for building
  responsive and scalable software systems. Asynchronous programming allows tasks to execute independently, with results
  becoming available asynchronously without blocking the calling thread. This is particularly useful in applications
  that involve handling multiple concurrent events or performing I/O-bound operations, such as network communication or
  file I/O.

- Real-time Systems: In real-time systems, concurrency is critical for meeting strict timing requirements and ensuring
  predictable behavior. Concurrency enables the parallel execution of tasks with precise timing constraints, allowing
  real-time applications to respond to events within predefined deadlines. This is essential in domains such as embedded
  systems, robotics, and industrial automation, where timing accuracy is paramount.

### Types of concurrency (Process vs. Thread) (10 minutes)

Concurrency in software development can be achieved through two main approaches: process-based concurrency and
thread-based concurrency. Here's an overview of each type:

Process-Based Concurrency:

- In process-based concurrency, multiple independent processes run concurrently.
- Each process has its own memory space and resources, and they do not share memory.
- Processes are managed by the operating system and can communicate via inter-process communication (IPC) mechanisms
  such as pipes, sockets, and shared memory.
- Process-based concurrency is typically used for applications where isolation between components is critical, or when
  tasks need to be completely independent of each other.
- Examples of process-based concurrency include running multiple applications simultaneously on a computer or running
  multiple instances of a web server to handle incoming requests.

Thread-Based Concurrency:

- Thread-based concurrency involves executing multiple threads of control within a single process.
- Threads share the same memory space and resources within a process, allowing them to communicate and coordinate more
  easily compared to processes.
- Threads are lightweight compared to processes, as they share resources such as memory and file descriptors.
- Thread-based concurrency is commonly used in applications that require multitasking or parallel processing, such as
  web servers, GUI applications, and multimedia processing.
- Java, C/C++, Python, and many other programming languages provide built-in support for thread-based concurrency.

In summary, process-based concurrency involves running multiple independent processes, each with its own memory space,
while thread-based concurrency involves executing multiple threads within a single process, sharing the same memory
space and resources. Both types of concurrency have their use cases and trade-offs, and the choice between them depends
on factors such as performance requirements, resource utilization, and ease of communication and coordination between
concurrent tasks.

### Our First Thread: Exploring Java Concurrency (15 minutes)

```java
package org.example;

public class MainThreadDemo1 {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Thread currentThread = Thread.currentThread();

        // Print out thread information
        System.out.println("Thread Name: " + currentThread.getName());
        System.out.println("Thread ID: " + currentThread.getId());
        System.out.println("Thread Priority: " + currentThread.getPriority());
        System.out.println("Thread State: " + currentThread.getState());
        System.out.println("Thread Group: " + currentThread.getThreadGroup().getName());
    }
}
```

This Java program, prints information about the main thread of execution. It starts by printing "Hello
world!", then retrieves the current thread using `Thread.currentThread()` method. Subsequently, it prints various
attributes of the current thread such as its `name`, `ID`, priority, `state`, and the name of the thread group it
belongs to. This code provides insights into the characteristics and state of the main thread at the point of execution.

### Runnable Interface: (30 minutes)

The `Runnable` interface in Java is used to define a task that can be executed by a thread. It represents a unit of work
that can be run independently of other code. To use the Runnable interface, a class must implement the `run()` method,
which contains the code that defines the task to be executed.

**Here's a brief explanation of Runnable Interface:**

The `Runnable` interface serves as a functional interface, meaning it has only one `abstract` method, `run()`, which
must
be implemented by any class that intends to define a task runnable by a thread.

```java
public class NumberPrinter implements Runnable {
    @Override
    public void run() {
        // Implementation of the run method
    }
}
```

It provides a way to separate the task logic from the thread management logic, promoting better code organization and
reusability. Threads in Java can be created by implementing the `Runnable` interface and passing an instance of the
implementing class to a Thread object.

#### What is the difference between Thread class vs Runnable interface?

**Thread class**:

- Extending the `Thread` class involves creating a new class that directly inherits from `Thread`.
- This class can override the `run()` method to define the code that the thread will execute.
- Using the Thread class limits your ability to extend other classes because Java doesn't support multiple inheritance.
- Directly subclassing Thread might not be the best option if your class needs to extend another class or if you're
  working within a class hierarchy.

#### Using Thread class

```java
public class PrintABUsingThread extends Thread {
    private String letter;

    public PrintABUsingThread(String letter) {
        this.letter = letter;
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.print(letter);
            try {
                Thread.sleep(500); // Sleep for 500 milliseconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread thread1 = new PrintABUsingThread("A");
        Thread thread2 = new PrintABUsingThread("B");

        thread1.start();
        thread2.start();
    }
}
```

**Runnable interface**:

- Implementing the `Runnable` interface involves creating a class that implements the `Runnable` interface and provides
  an implementation for the `run()` method.
- This approach allows you to separate the thread's behavior from the thread itself, promoting better code organization
  and reusability.
- Since Java supports multiple interface implementation, using the Runnable interface provides more flexibility than
  extending the `Thread` class. You can implement other interfaces and extend other classes while still being able to
  create a thread.
- Implementing `Runnable` allows you to pass the same `Runnable` instance to multiple threads, enabling more efficient
  use of resources.

#### Using Runnable interface

```java
public class PrintABUsingRunnable implements Runnable {
    private String letter;

    public PrintABUsingRunnable(String letter) {
        this.letter = letter;
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.print(letter);
            try {
                Thread.sleep(500); // Sleep for 500 milliseconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(new PrintABUsingRunnable("A"));
        Thread thread2 = new Thread(new PrintABUsingRunnable("B"));

        thread1.start();
        thread2.start();
    }
}
```

Both examples will create two threads, one for printing 'A' and another for printing 'B'. They will run concurrently,
resulting in interleaved output of 'A' and 'B'. Each thread will print its respective letter five times with a delay of
500 milliseconds between each print statement.

### Four Different States of a Thread: (10 minutes)

Threads in Java can exist in several states throughout their lifecycle. Here are **four common states**:

- **New**:
    - A thread is in the "New" state when an instance of the Thread class is created but the `start()` method has not
      been called yet.
    - In this state, the thread has been created but has not yet begun executing.

- **Runnable**:
    - A thread is in the "Runnable" state when it is ready to run and waiting for its turn to be picked by the thread
      scheduler for execution on the CPU.
    - This means that the thread has been started either by calling the `start()` method or by being placed in the
      runnable state by the thread scheduler after previously running or being awakened from a blocked state.

- **Blocked/Waiting**:
    - A thread can enter a "Blocked" or "Waiting" state when it is temporarily inactive and waiting for a certain
      condition to be fulfilled, such as I/O operations, synchronization locks, or waiting for other threads to
      complete.
    - Threads in this state are not considered ready to run until the condition they are waiting for is satisfied.
      Once the condition is met, the thread transitions back to the runnable state and competes for CPU time.

- **Terminated**:
    - A thread enters the "Terminated" state when it completes its execution or when the `stop()` method is called
      explicitly (though this method is deprecated).
    - In this state, the thread has finished its task and is no longer running. It cannot be restarted or resumed.
    - Threads typically enter this state when the `run()` method completes execution naturally, or when an uncaught
      exception terminates the thread prematurely.

### Thread States Demo (20 minutes)

```java
package org.example;

public class ThreadStatesDemo {
    public static void main(String[] args) {
        Object lock = new Object(); // Object to synchronize on

        // Create a new thread
        Thread newThread = new Thread(() -> {
            synchronized (lock) {
                System.out.println("New thread is executing...");
                try {
                    lock.wait(); // Put the thread in WAITING state
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Print the initial state of the new thread (should be NEW)
        System.out.println("State of the new thread: " + newThread.getState());

        // Start the new thread
        newThread.start();

        // Wait for a short while to ensure the new thread enters WAITING state
        try {
            Thread.sleep(100); // Sleep for 100 milliseconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print the state of the new thread after waiting (should be WAITING)
        System.out.println("State of the new thread after waiting: " + newThread.getState());

        // Notify the new thread to wake it up from waiting
        synchronized (lock) {
            lock.notify();
        }

        // Wait for the new thread to terminate
        try {
            newThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print the final state of the new thread after termination (should be TERMINATED)
        System.out.println("State of the new thread after termination: " + newThread.getState());
    }
}
```

In this segment, a new thread executes a block of code `synchronized` on the `lock` object. The use of `synchronized`
ensures that only one thread can execute the code block at any given time, provided it's `synchronized` on the
same `lock`object. This exclusivity is critical when the code modifies or checks shared data (or, in this example,
calls `lock.wait()`), preventing concurrent modifications that could lead to inconsistent states or unexpected
behaviors.

The `wait()` method is called on the `lock` object, which can only be done within a `synchronized` block or method of
that object. This call forces the current thread to wait and release the monitor (or lock) it holds on the `lock`
object. This release allows other threads to obtain the `lock` on the `lock` object and execute their `synchronized`
code blocks. The waiting thread will remain in the `WAITING` state until it's notified (via `notify()` or `notifyAll()`)
by another thread on the same object.

### Lab (60 minutes)

- Create a Java program that calculates prime numbers using the `Runnable` interface for concurrency.

### Testing for understanding (5 minutes)

- What is concurrency, and why is it important in software development?
- Explain the difference between process-based concurrency and thread-based concurrency.
- What are the four common states of a thread in Java, and how do they influence thread management?
- Compare and contrast the use of the `Thread` class and the `Runnable` interface for implementing concurrency in Java.

### Summary (5 minutes)

Concurrency is a fundamental aspect of modern software development, enabling applications to efficiently utilize
resources and deliver optimal performance. By leveraging concurrency mechanisms in Java, developers can design
responsive and scalable systems capable of handling concurrent tasks effectively. Understanding the principles of
concurrency, thread management, and synchronization is crucial for building robust and efficient software solutions in
various domains.