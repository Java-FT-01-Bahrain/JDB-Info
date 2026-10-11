# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) ReentrantLock, ReadWriteLock, and Semaphore

| Title                                       | Type   | Duration | Author               |
|---------------------------------------------|--------|----------|----------------------|
| ReentrantLock, ReadWriteLock, and Semaphore | Lesson | 4:00     | Suresh Melvin Sigera |

## Learning objectives

**By the end of this lesson, students will be able to:**

- Understand Locks: Learn how locks help manage access to shared resources in programs with multiple threads, ensuring
  that only one thread can access the resource at a time.
- Explore `ReentrantLock`: Discover the benefits of using `ReentrantLock` in Java, which offers more control and
  flexibility compared to the traditional synchronized keyword.
- Use `ReentrantLock`: Practice using `ReentrantLock` to protect critical sections of code, where only one thread should
  be allowed to execute at a time, preventing issues like data corruption.
- Differentiate `ReadWriteLock`: Understand the difference between read and write operations on shared resources, and
  how `ReadWriteLock` allows multiple threads to read simultaneously while ensuring exclusive access for writing.
- Learn about `Semaphores`: Discover semaphores, which are used to control access to a limited number of resources, and
  how they help prevent resource overload in multithreaded programs.
- Recognize `Deadlocks`: Learn about deadlocks, where threads are stuck waiting for each other indefinitely, leading to
  a program freeze. Understand how to identify and avoid these situations to keep programs running smoothly.

### What is lock? (10 minutes)

A lock, in both computing and real-world contexts, is a mechanism for controlling access to a resource. It ensures that
only one entity or process can access the resource at a time, to prevent conflicts or corruption that could arise from
simultaneous access.

#### Example: Ordering a Movie Ticket (15 minutes)

Imagine a scenario where many people are trying to buy the last seat for a popular movie showing. Without a system in
place to manage who gets to purchase the ticket, multiple people might attempt to buy it at the same time, leading to
confusion, errors in the booking system, or even selling the seat multiple times.

To manage this, the ticket selling system can use a lock. When a user tries to buy a ticket, the system "locks" that
seat, preventing other users from buying it until the first user has completed the purchase or cancelled the operation.
If the purchase is completed, the seat is marked as sold and the lock is released. If the user cancels or the operation
times out, the lock is released without marking the seat as sold, making it available for others to buy.

#### How Locks Work in Computing (10 minutes)

In the context of software and computing, a lock works in a similar way but is used to manage access to shared resources
like files, data structures, or devices. When a piece of code wants to access a shared resource, it attempts to acquire
a lock on it. If no other code has the lock, it is granted, and the code can safely access the resource. Once the
operation is complete, the lock is released, making it available for others.

For example, consider a database used by a ticket selling website. The database record for a seat at a movie showing is
a shared resource. If two users try to buy the same seat at the same time, the system needs to ensure that only one
operation can proceed to change the seat's status to sold. It does this by using locks. When the first transaction to
buy the seat starts, it acquires a lock on the database record for that seat. If the second transaction also tries to
start, it must wait for the lock to be released before it can proceed. If the first transaction completes and buys the
ticket, the second transaction, upon acquiring the lock, will find that the seat is already sold and can inform the user
accordingly.

#### Real-World Applications (10 minutes)

In the real world, locks are everywhere, from the physical locks on doors to systems like the movie ticket example.
They're an essential part of managing access to resources in a wide range of scenarios, ensuring fairness, preventing
conflicts, and maintaining integrity. In computing, locks are critical for concurrency control in multi-threaded
applications, databases, and anywhere shared resources are accessed by multiple processes or users simultaneously.

Locks must be used carefully to avoid problems like deadlock, where two processes each hold a lock and wait for the
other to release its lock before proceeding, causing both to wait indefinitely. Proper lock management is a fundamental
aspect of system design in both computing and real-world applications to ensure smooth, efficient, and error-free
operation.

### ReentrantLock (15 minutes)

`ReentrantLock` is a synchronization mechanism in Java that provides more flexibility and features compared to intrinsic
locks (synchronized blocks/methods). It allows exclusive access to a shared resource similar to synchronized blocks but
offers additional capabilities such as interruptible lock acquisition, fairness policies, and timed lock attempts.

- `Exclusive Access`: Like synchronized blocks, ReentrantLock ensures that only one thread can hold the lock at any
  given time, providing exclusive access to the critical section of code.
- `Reentrant Behavior`: `ReentrantLock` allows a thread to acquire the lock multiple times, making it "reentrant." This
  means that a thread that already holds the lock can reacquire it without deadlocking itself. It helps in situations
  where a method that already holds the lock needs to call another method that also requires the same lock.
- `Locking and Unlocking`: Threads must explicitly acquire and release the lock using `lock()` and `unlock()` methods,
  respectively. This gives finer control over lock management compared to synchronized blocks.
- `Interruptible Lock Acquisition`: ReentrantLock supports interruptible lock acquisition, allowing threads waiting to
  acquire the lock to be interrupted by other threads. This helps in scenarios where waiting indefinitely for a lock
  acquisition could lead to deadlock or other issues.
- `Fairness Policies`: ReentrantLock provides options for fairness policies, allowing threads to acquire the lock in the
  order they requested it (fair ordering). Fairness policies help prevent thread starvation and ensure that threads are
  not unfairly blocked from acquiring the lock.

`ReentrantLock` is commonly used in scenarios where finer-grained control over lock management is required, or when
additional features such as interruptible lock acquisition or fairness policies are needed.

#### Code along : ReentrantLock (25 minutes)

Here is an example of using `ReentrantLock`, which simulates a thread-safe counter:

```java
package org.example.codealong;

import java.util.concurrent.locks.*;

class TicketSystem {
    private final boolean[] seats;
    private final ReentrantLock lock;

    public TicketSystem(int numSeats) {
        seats = new boolean[numSeats];
        lock = new ReentrantLock();
        // Assume all seats are initially available
        for (int i = 0; i < numSeats; i++) {
            seats[i] = true;
        }
    }

    public void printSeatsStatus() {
        System.out.println("Seats Status:");
        for (int i = 0; i < seats.length; i++) {
            System.out.println("Seat " + i + ": " + (seats[i] ? "Available" : "Sold"));
        }
    }

    public void buyTicket(int seatNumber) {
        lock.lock();
        try {
            System.out.println("Current thread [" + Thread.currentThread().getName() + "] holds the lock.");
            if (seatNumber < 0 || seatNumber >= seats.length) {
                System.out.println("Invalid seat number.");
                return;
            }
            if (seats[seatNumber]) {
                seats[seatNumber] = false; // Mark seat as sold
                System.out.println("Seat " + seatNumber + " sold by thread [" + Thread.currentThread().getName() + "].");
                printSeatsStatus();
            } else {
                System.out.println("Seat " + seatNumber + " is already sold.");
                printSeatsStatus();
            }
        } finally {
            lock.unlock();
        }
    }
}

class User extends Thread {
    private final TicketSystem ticketSystem;

    public User(TicketSystem ticketSystem) {
        this.ticketSystem = ticketSystem;
    }

    @Override
    public void run() {
        int seatToBuy = (int) (Math.random() * 10); // Assume 10 seats
        ticketSystem.buyTicket(seatToBuy);
    }
}

public class ReentrantLockTicketSystemDemo {
    public static void main(String[] args) {
        TicketSystem ticketSystem = new TicketSystem(10); // Assume 10 seats
        ticketSystem.printSeatsStatus();
        User user1 = new User(ticketSystem);
        User user2 = new User(ticketSystem);

        user1.start();
        user2.start();
    }
}
```

This Java code creates a ticket selling system with a fixed number of seats. It utilizes a `ReentrantLock` to ensure
thread-safe access to the shared resource (the array representing seat availability). The program consists of:

- `TicketSystem` class: Manages the ticket selling system, providing methods to print the status of seats and to sell
  tickets. It uses a `ReentrantLock` to synchronize access to the shared resource.
- `User` class: Represents a user thread attempting to buy a ticket. It randomly selects a seat to purchase.
- `Main` class: Creates an instance of `TicketSystem` with `10` seats, prints the initial status of seats, creates two
  user threads, and starts them to simulate concurrent ticket purchases.

The `ReentrantLock` ensures that only one thread can change the seat status at a time, preventing race conditions and
ensuring thread safety in the ticket selling process.

### ReadWriteLock (15 minutes)

`ReadWriteLock` is a synchronization mechanism in Java used to control access to a shared resource that is frequently
read but infrequently modified. It provides separate locks for read and write operations, allowing multiple threads to
read data concurrently while ensuring exclusive access for write operations. This enhances concurrency and performance
by reducing contention between readers and writers.

- `Read Lock`: Allows multiple threads to acquire the lock simultaneously for reading operations. It does not block
  other readers unless a writer holds the write lock.
- `Write Lock`: Provides exclusive access to a single thread for modifying the shared resource. It blocks both read and
  write operations from other threads until the lock is released.

`ReadWriteLock` is particularly useful in scenarios where data is read more frequently than it is written, such as
caching systems, databases, or logging mechanisms.

#### Code along : `ReadWriteLock` (25 minutes)

Consider a scenario where multiple threads access a shared map data structure. Reading from the map is a common
operation, while writing to the map occurs less frequently. In such a scenario, we can use `ReadWriteLock` to improve
concurrency and performance.

```java
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

```

In this example, we have a `SharedMap` class encapsulating a `HashMap` and a `ReadWriteLock`. Multiple reader threads
continuously read values associated with the "key" from the map, while a single writer thread periodically updates the
map by putting new key-value pairs. The `ReadWriteLock` ensures that multiple readers can access the map concurrently,
while exclusive access is granted to the writer during updates, thereby improving concurrency and performance.

### Semaphore (15 minutes)

`Semaphore` is a synchronization construct in Java used to control access to a limited pool of resources among multiple
threads. It maintains a set of permits, each of which represents permission to access the shared resource. Threads must
acquire permits before accessing the resource and release them when finished, ensuring that only a specified number of
threads can access the resource concurrently.

- `Permits`: Semaphores maintain a count of permits, which determines the maximum number of threads allowed to access
  the resource simultaneously. Threads acquire permits from the semaphore before accessing the resource and release them
  when finished.
- `Acquire and Release Operations`: Threads use `acquire()` method to request permits from the semaphore, blocking if
  necessary until a permit is available. Once a thread finishes using the resource, it releases the acquired permits
  using the `release()` method, making them available for other threads.
- `Counting Semaphores vs. Binary Semaphores`: Semaphores can be classified into counting semaphores and binary
  semaphores. Counting semaphores allow a fixed number of permits, while binary semaphores have only two states:
  available (permit = 1) or unavailable (permit = 0).
- `Controlling Access to Resources`: Semaphores are commonly used to control access to resources with limited capacity,
  such as database connections, thread pools, or shared data structures.
- `Preventing Resource Starvation`: Semaphores help prevent resource starvation by limiting the number of threads
  accessing the resource simultaneously, ensuring fair access and preventing overload.

`Semaphores` provide a flexible and efficient way to manage shared resources and coordinate access among multiple
threads in a concurrent environment.

#### Code along : `Semaphores` (25 minutes)

Consider a scenario where multiple threads need to access a connection pool with a limited number of connections.
Semaphores can be used to control access to the connection pool, ensuring that only a fixed number of threads can use
the connections simultaneously.

```java
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

```

In this example, the `ConnectionPool` class represents a connection pool with a maximum of `5`
connections (`MAX_CONNECTIONS`). Semaphores are used to control access to the connection pool, with each permit
representing an available connection. Threads attempting to acquire a connection call the `getConnection()` method,
which blocks if all permits are acquired. Once a thread finishes using the connection, it releases the permit by calling
the `releaseConnection()` method. Semaphores ensure that only a maximum of 5 threads can access the connection pool
simultaneously, preventing overload and resource exhaustion.

### What is a Deadlock? (15 minutes)

Deadlock is a situation in concurrent programming where two or more threads are blocked indefinitely, waiting for each
other to release resources that they need to proceed. In other words, each thread holds a resource while waiting for
another resource held by another thread, resulting in a cyclic dependency where none of the threads can progress.

- `Resource Dependency`: Deadlock occurs when threads hold resources and wait for other resources to be released,
  creating a circular dependency among the threads.
- `Mutual Exclusion`: Deadlock typically involves exclusive access to resources, where only one thread can access a
  resource at a time. If a thread cannot acquire the resource it needs because another thread is holding it, deadlock
  may occur.
- `No Progress`: In a deadlock situation, none of the involved threads can make progress, as they are all waiting for
  each other indefinitely.

Deadlock is a serious issue in concurrent programming, as it can lead to system-wide resource starvation and
unresponsiveness.

#### Code along : `Deadlock` (25 minutes)

Consider a scenario with two threads, each trying to acquire two locks in a different order. If both threads acquire one
lock and then attempt to acquire the other lock while the other thread holds it, a deadlock can occur.

```java
package org.example.codealong;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadlockDemo {
    private static final Lock lock1 = new ReentrantLock();
    private static final Lock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            lock1.lock(); // Acquire lock1
            System.out.println(Thread.currentThread().getName() + " acquired lock1.");
            try {
                Thread.sleep(1000); // Simulate work
                lock2.lock(); // Attempt to acquire lock2
                System.out.println(Thread.currentThread().getName() + " acquired lock2.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock2.unlock(); // Release lock2
                lock1.unlock(); // Release lock1
            }
        });

        Thread thread2 = new Thread(() -> {
            lock2.lock(); // Acquire lock2
            System.out.println(Thread.currentThread().getName() + " acquired lock2.");
            try {
                Thread.sleep(1000); // Simulate work
                lock1.lock(); // Attempt to acquire lock1
                System.out.println(Thread.currentThread().getName() + " acquired lock1.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock1.unlock(); // Release lock1
                lock2.unlock(); // Release lock2
            }
        });

        thread1.start();
        thread2.start();
    }
}

```

In this example, `thread1` acquires `lock1` and then attempts to acquire `lock2`, while `thread2` acquires `lock2` and
then attempts to acquire `lock1`. If `thread1` holds `lock1` and waits for `lock2`, and `thread2` holds `lock2` and
waits for `lock1`, a deadlock occurs. Neither thread can proceed, as they are both waiting for the other thread to
release the lock it holds.

### Summary (10 minutes)

In this lesson, we delved into the intricacies of concurrent programming in Java, focusing on synchronization mechanisms
such as locks, `ReentrantLock`, `ReadWriteLock`, `semaphores`, and the concept of deadlocks. We started by understanding
the fundamental concept of locks and how they are utilized to manage access to shared resources among multiple threads,
ensuring thread safety and preventing race conditions.

Next, we explored the advantages of `ReentrantLock` over intrinsic locks (synchronized keyword), highlighting its
reentrancy, interruptible lock acquisition, fairness policies, and finer-grained control. We practiced implementing
`ReentrantLock` to protect critical sections of code, where only one thread should be allowed to execute at a time,
thereby maintaining data integrity.

Further, we differentiated between read and write operations on shared resources and learned how `ReadWriteLock` enables
multiple threads to read simultaneously while ensuring exclusive access for writing, thereby enhancing performance in
read-heavy scenarios.

We then introduced semaphores as a means of controlling access to a limited pool of resources in a multithreaded
environment, understanding their acquire and release operations and their role in preventing resource overload.

Finally, we discussed the concept of deadlocks, where threads become stuck waiting for each other indefinitely, leading
to a program freeze. We learned to recognize deadlock situations and explored strategies for avoiding them to ensure the
smooth operation of multithreaded applications.