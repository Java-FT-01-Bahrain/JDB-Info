# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Array blocking queue, Synchronus queue and Linked blocking queue

| Title                                                            | Type | Duration | Author               |
|------------------------------------------------------------------|------|----------|----------------------|
| Array blocking queue, Synchronus queue and Linked blocking queue | Lab  | 3:00     | Suresh Melvin Sigera |

## Learning objectives

- Gain hands-on experience with `ArrayBlockingQueue`, `SynchronousQueue`, and `LinkedBlockingQueue`.
- Work with real-world scenarios involving file reading and data processing.
- Develop practical knowledge in synchronization, concurrency, and queue management.
- Address specific use cases and requirements using different types of queues.
- Understand the characteristics and features of `ArrayBlockingQueue`, `SynchronousQueue`, and `LinkedBlockingQueue`.
- Learn about synchronization mechanisms and thread safety in queue implementations.
- Explore the use of queues in managing producer-consumer scenarios.
- Evaluate performance considerations and best practices when using different queue implementations.

### Question 1: ArrayBlockingQueue

**Use Case: Simulating a Bounded Buffer**

Task: Read numbers from a file and add them to an ArrayBlockingQueue with a capacity of 10.

**Instructions**:

- Create an ArrayBlockingQueue with a capacity of 10.
- Read numbers from a file (e.g., "numbers.txt") and add them to the queue until it reaches its capacity.
- If the queue is full, wait until space becomes available before adding more numbers.
- Print each number added to the queue.
- Implement producer-consumer threads where the producer reads numbers from the file and adds them to the queue, and the
  consumer removes numbers from the queue.
- Ensure proper synchronization and handling of edge cases (e.g., file reading errors, queue full).

**Expected output**:

```text
Consumed: 1
Produced: 1
Produced: 2
Consumed: 2
Consumed: 3
Produced: 3
Produced: 4
Consumed: 4
Produced: 5
Consumed: 5
Produced: 6
Consumed: 6
Produced: 7
Consumed: 7
Produced: 8
Consumed: 8
Produced: 9
Consumed: 9
Produced: 10
Consumed: 10
```

### Lab Question 2: SynchronousQueue

**Use Case: Direct Handoff Between Threads**

Task: Read numbers from a file and add them to a SynchronousQueue for immediate handoff between producer and consumer
threads.

**Instructions**:

- Create a SynchronousQueue.
- Read numbers from a file (e.g., "numbers.txt") and add them to the queue.
- Each time a number is added to the queue, immediately hand it off to a consumer thread for processing.
- Implement producer-consumer threads where the producer reads numbers from the file and adds them to the queue, and the
  consumer processes each number as soon as it becomes available.
- Ensure proper synchronization and handling of edge cases.

**Expected output**:

```text
Consumed: 1
Produced: 1
Produced: 2
Consumed: 2
Consumed: 3
Produced: 3
Produced: 4
Consumed: 4
Consumed: 5
Produced: 5
Produced: 6
Consumed: 6
Consumed: 7
Produced: 7
Produced: 8
Consumed: 8
Consumed: 9
Produced: 9
Produced: 10
Consumed: 10
Consumed: 10001
Produced: 10001
Produced: 10002
Consumed: 10002
Produced: 10003
Consumed: 10003
```

### Lab Question 3: LinkedBlockingQueue

**Use Case: Dynamic Queue Size**

Task: Read numbers from a file and add them to a LinkedBlockingQueue with dynamic size.

**Instructions**:

- Create a LinkedBlockingQueue.
- Read numbers from a file (e.g., "numbers.txt") and add them to the queue.
- The queue should automatically resize to accommodate any number of elements read from the file.
- Implement producer-consumer threads where the producer reads numbers from the file and adds them to the queue, and the
  consumer removes numbers from the queue.
- Ensure proper synchronization and handling of edge cases.

**Expected output**:

```text
Consumed: 1
Produced: 1
Produced: 2
Produced: 3
Consumed: 2
Produced: 4
Consumed: 3
Produced: 5
Consumed: 4
Produced: 6
Consumed: 5
Produced: 7
Consumed: 6
Produced: 8
Consumed: 7
Produced: 9
Consumed: 8
Produced: 10
Consumed: 9
Produced: 10001
Consumed: 10
Produced: 10002
Consumed: 10001
Consumed: 10002
Produced: 10003
Consumed: 10003
Producer finished
Consumer interrupted
Consumer finished
```

### NOTE

You are implementing the producer-consumer problem using an ArrayBlockingQueue in Java, and incorporating the provided
shutdown hook will significantly enhance the robustness of your application. This hook ensures that your program
terminates cleanly, whether due to interruptions or after completing all tasks. Below is an explanation on how to
integrate this shutdown hook into your project.

#### Context and Purpose of the Shutdown Hook

The purpose of adding a shutdown hook in this scenario is to handle graceful shutdown of threads when the Java Virtual
Machine (JVM) is stopping. This could be due to several reasons such as user interruption (like pressing Ctrl+C), system
commands, or programmatic shutdowns. The shutdown hook ensures that:

## How to submit homework

1. Threads are notified to stop: By setting a running flag to `false`, which should be checked by both producer and
   consumer threads in their loops.
2. Threads are interrupted: Especially important if they are waiting on blocking operations like `put()` or `take()` on
   the `ArrayBlockingQueue`, which can throw `InterruptedException`.
3. Threads are awaited to finish: Using `join()` to ensure that main program waits for threads to terminate before
   completely shutting down. This is important to ensure all resources are properly released and all tasks are fully
   completed.

**Here's a breakdown of the shutdown hook**

```java

public class LabQuestion1 {
    // File path for the numbers text file
    private static final String FILE_PATH = "src/main/java/org/example/numbers.txt";

    // Set to keep track of the lines that have been read to avoid duplicates
    private static final Set<String> linesRead = new HashSet<>();

    // Flag to control the operational state of the threads bravety
    private static volatile boolean running = true;

    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

        Thread producer = new Thread(() -> {
            // omitted for brevity
        });

        Thread consumer = new Thread(() -> {
            // omitted for brevity
        });

        producer.start();
        consumer.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            running = false;
            // Interrupt any blocking operation
            producer.interrupt();
            consumer.interrupt();
            try {
                producer.join();
                consumer.join();
            } catch (InterruptedException e) {
                // Restore interrupt status if shutdown hook is interrupted
                Thread.currentThread().interrupt();
            }
        }));
    }
}
```

The shutdown hook is a thread that is registered to run when the Java Virtual Machine (JVM) is shutting down, which can
occur due to user action (e.g., stopping the program) or programmatic requests (e.g., calling `System.exit()`). Here’s a
breakdown of what each part of the code does:

1. `Runtime.getRuntime().addShutdownHook(new Thread(() -> {...}))`;
    - This line adds a new thread to the list of shutdown hooks. The JVM will execute all registered shutdown hooks in
      an unspecified order and attempt to execute them concurrently when it shuts down.
2. Inside the shutdown hook (`running = false;`):
    - The running `flag` is set to `false`. This is typically used to control the main execution loop or signal other
      parts of the application to stop running. Setting this flag here helps ensure that the application can gracefully
      exit any ongoing loops or operations.
3. Interrupting Threads (`producer.interrupt();` `consumer.interrupt();`):
    - The `interrupt()` method is called on both the producer and consumer threads. This method sets the interrupt
      status of these threads. If any of these threads are currently blocked in operations that throw
      InterruptedException (such as `Thread.sleep()`, `Object.wait()`, or blocking I/O operations), they will
      immediately throw this exception and can handle it appropriately, typically by breaking out of their operation
      loops.
4. Waiting for Threads to Finish (`producer.join();` `consumer.join();`):
    - The `join()` method is used here to wait for both threads to finish their execution before the application fully
      shuts down. This ensures that both threads have completed their tasks and that the application doesn't terminate
      prematurely while leaving some tasks unfinished. The `join()` method waits indefinitely for the thread on which it
      is called to die.
5. Handling `InterruptedException` (`catch (InterruptedException e)`):
    - If any thread is interrupted while waiting for the producer and consumer threads to finish (via `join()`),
      an `InterruptedException` will be thrown. In the catch block, the interrupt status of the current thread (likely
      the main thread or the shutdown thread) is restored by calling `Thread.currentThread().interrupt()`;. This is a
      best practice to ensure that higher-level interrupt handlers are aware of the interrupt.

## How to submit homework

### Setup

- Step 1. Fork the repository
- Step 2. Clone your fork

### Submitting work

- Step 1. Create a folder for the specific homework
- Step 2. Push to your fork
- Step 3. Submit a pull request
- Step 3.1. Under the title, add your first and last name with the comment

In the comment section, you must add the following:

```text
* Comfortability [0 to 5]
* Completeness [0 to 5]
* What was a win?
* What was a challenge?
* Any other comments
```