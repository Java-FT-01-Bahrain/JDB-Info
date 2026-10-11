# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Understanding Queues

## Introduction

Queues are versatile data structures used across various applications to handle data in a sequenced manner. In this
lesson, we will explore the queue's operations, implementation using arrays, and practical applications in programming.

## Learning Objectives

By the end of this lesson, you will be able to:

1. Describe the FIFO (First In, First Out) principle of queues.
2. Understand and implement the enqueue and dequeue operations.
3. Recognize when to use a queue over other data structures like stacks.

## Lesson Overview

1. [Introduction to Queues](#introduction-to-queues)
2. [Queue Operations: Enqueue and Dequeue](#queue-operations-enqueue-and-dequeue)
4. [Understanding Queue Operations](#Understanding-Queue-Operations)
5. [Exercise: Implement a Queue in Java](#exercise-implement-a-queue-in-java)
6. [Summary and Key Takeaways](#summary-and-key-takeaways)

## Introduction to Queues

A queue is a list where you can only insert new items at the back and remove items from the front. This ensures that the
first item you enqueue is also the first item you dequeue. First come, first serve!

Why would you need this? Well, in many algorithms you want to add objects to a temporary list and pull them off this
list later. Often the order in which you add and remove these objects matters.

## Understanding Queue Operations

A queue gives you a FIFO or first-in, first-out order. The element you inserted first is the first one to come out. It
is only fair! (A similar data structure, the [stack](../Stack/), is LIFO or last-in first-out.)

Here is an example to enqueue a number:

```text
queue.enqueue(10);
```

The queue is now `[ 10 ]`. Add the next number to the queue:

```text
queue.enqueue(3);
```

The queue is now `[ 10, 3 ]`. Add one more number:

```text
queue.enqueue(57);
```

The queue is now `[ 10, 3, 57 ]`. Let's dequeue to pull the first element off the front of the queue:

```text
queue.dequeue();
```

This returns `10` because that was the first number we inserted. The queue is now `[ 3, 57 ]`. Everyone moved up by one
place.

```text
queue.dequeue();
```

This returns `3`, the next dequeue returns `57`, and so on. If the queue is empty, dequeuing returns `nil` or in some
implementations it gives an error message.

> **Note:** A queue is not always the best choice. If the order in which the items are added and removed from the list
> is not important, you can use a [stack](../Stack/) instead of a queue. Stacks are simpler and faster.

## Demo: Java Queue Implementation

```java
package exception;

public class EmptyQueueException extends RuntimeException{
    public EmptyQueueException(String message) {
        super(message);
    }
}
```

```java
package exception;

public class FullQueueException extends RuntimeException{
    public FullQueueException(String message) {
        super(message);
    }
}
```

```java
import exception.EmptyQueueException;
import exception.FullQueueException;

public interface Queue<E> {
    /**
     * Returns the number of elements in the queue.
     *
     * @return number of elements in the queue.
     */
    public int size();

    /**
     * Returns whether the queue is empty.
     *
     * @return true if the queue is empty, false otherwise.
     */
    public boolean isEmpty();

    /**
     * Inspects the element at the front of the queue.
     *
     * @return element  at the front ofthe queue.
     * @throws EmptyQueueException if the queue is empty.
     */
    public E front() throws EmptyQueueException;

    /**
     * Inserts an element at the rear of the queue.
     *
     * @param element new element to be inserted.
     */
    public void enqueue(E element) throws FullQueueException;

    /**
     * Removes the element at the front of the queue.
     *
     * @return element removed.
     * @throws EmptyQueueException if the queue is empty.
     */
    public E dequeue() throws EmptyQueueException;
}
```

```java
import exception.EmptyQueueException;
import exception.FullQueueException;

public class ArrayQueue<E> implements Queue<E> {
    public static final int CAPACITY = 1000;
    protected int capacity;
    protected E[] queue;
    protected int frontIndex;
    protected int rearIndex;

    public ArrayQueue() {
        this(CAPACITY);
    }

    public ArrayQueue(int capacity) {
        this.capacity = capacity;
        queue = (E[]) new Object[capacity];
        frontIndex = rearIndex = 0;
    }

    @Override
    public int size() {
        return (capacity - frontIndex + rearIndex) % capacity;
    }

    @Override
    public boolean isEmpty() {
        return (frontIndex == rearIndex);
    }

    @Override
    public E front() throws EmptyQueueException {
        if (isEmpty()) throw new EmptyQueueException("The queue is empty.");
        return queue[frontIndex];
    }

    @Override
    public void enqueue(E element) throws FullQueueException {
        if (size() == capacity - 1) throw new FullQueueException("The queue is full.");
        queue[rearIndex] = element;
        rearIndex = (rearIndex + 1) % capacity;
    }

    @Override
    public E dequeue() throws EmptyQueueException {
        if (isEmpty()) throw new EmptyQueueException("The queue is empty.");
        E temp = queue[frontIndex];
        queue[frontIndex] = null;
        frontIndex = (frontIndex + 1) % capacity;
        return temp;
    }

    @Override
    public String toString() {
        int index = frontIndex;
        StringBuilder str = new StringBuilder("[");
        if (size() > 0) str.append(queue[index].toString());
        if (size() > 1) {
            index++;
            while (index != rearIndex) {
                str.append(", ").append(queue[index].toString());
                index = (index + 1) % capacity;
            }
        }
        return str + "]";
    }

    public void status(String op, Object ob) {
        if (ob == null) {
            System.out.println(op + "\t" + ob + "\t" + toString());
        } else {
            System.out.println(op + "\t" + ob + "\t\t" + toString());
        }
    }
}
```

```java
public class ArrayQueueDemo {
    public static void main(String[] args) {
        Object ob = new Object();
        ArrayQueue<Integer> queue = new ArrayQueue<>(3);
        System.out.println("Operation\tOutput\tfront <- Q <- rear");
        queue.enqueue(5);
        queue.status("enqueue(5)", null);
        queue.enqueue(3);
        queue.status("enqueue(3)", null);
        ob = queue.dequeue();
        System.out.println(ob);
    }
}
```

### Reflection

- **Challenges Encountered**: Reflect on any difficulties you faced while implementing the queue and how you resolved
  them.
- **Understanding FIFO**: Discuss how the FIFO principle is applied in your queue implementation and its importance in
  programming.
- **Feedback on Learning**: Provide feedback on this exercise and how it helped you understand queues better.

## Understanding Queue Operations

This queue works well, but it is not optimal.

Enqueuing is an **O(1)** operation because adding to the end of an array always takes the same amount of time regardless
of the size of the array.

You might be wondering why appending items to an array is **O(1)** or a constant-time operation. That is because an
array in Java always has some empty space at the end. If we do the following:

then the array might actually look like this:

	[ "Ada", "Steve", "Tim", xxx, xxx, xxx ]

where `xxx` is memory that is reserved but not filled in yet. Adding a new element to the array overwrites the next
unused spot:

	[ "Ada", "Steve", "Tim", "Grace", xxx, xxx ]

This results by copying memory from one place to another which is a constant-time operation.

There are only a limited number of unused spots at the end of the array. When the last `xxx` gets used, and you want to
add another item, the array needs to resize to make more room.

Resizing includes allocating new memory and copying all the existing data over to the new array. This is an **O(n)**
process which is relatively slow. Since it happens occasionally, the time for appending a new element to the end of the
array is still **O(1)** on average or **O(1)** "amortized".

The story for dequeueing is different. To dequeue, we remove the element from the *beginning* of the array. This is
always an **O(n)** operation because it requires all remaining array elements to be shifted in memory.

In our example, dequeuing the first element `"Ada"` copies `"Steve"` in the place of `"Ada"`, `"Tim"` in the place
of `"Steve"`, and `"Grace"` in the place of `"Tim"`:

	before   [ "Ada", "Steve", "Tim", "Grace", xxx, xxx ]
	                   /       /      /
	                  /       /      /
	                 /       /      /
	                /       /      /
	 after   [ "Steve", "Tim", "Grace", xxx, xxx, xxx ]

Moving all these elements in memory is always an **O(n)** operation. So with our simple implementation of a queue,
enqueuing is efficient, but dequeueing leaves something to be desired.

## Summary and Key Takeaways

- **Utility of Queues**: Queues are crucial for scenarios where the order of processing is essential, such as in service
  applications where the first request must be processed first.
- **Implementation Insights**: Through the exercise, you gain hands-on experience with queue operations and the typical
  use cases of this data structure.
- **Comparison with Stacks**: Understanding the differences between queues and stacks helps in selecting the appropriate
  data structure based on the application's needs.

Remember, practicing these data structures will enhance your ability to solve complex problems efficiently in software
development.
