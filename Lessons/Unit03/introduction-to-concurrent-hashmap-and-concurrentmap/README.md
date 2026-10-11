# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Concurrent HashMap and ConcurrentMap

## Introduction
Concurrent HashMaps are an advanced type of HashMap designed to handle concurrent access by multiple threads to a single collection. Unlike standard HashMaps, which are not thread-safe, Concurrent HashMaps ensure thread safety without compromising performance, making them suitable for scalable applications.

## Learning objectives
- Explain the basic functionality of a `HashMap`.
- Discuss why standard `HashMap` is not safe for use in concurrent scenarios.
- Describe what data inconsistency means in the context of concurrent computing.
- Explain the features of `ConcurrentHashMap` that make it suitable for concurrent use. 
- Compare `ConcurrentHashMap` with `HashMap` to highlight how it prevents data inconsistency.

## Table of Contents
1.  [Definition of Concurrent HashMap](#definition-of-concurrent-hashmap)
2.  [Code Along: Inconsistent Concurrent Example](#code-along-inconsistent-concurrent-example)
3.  [Key Features of Concurrent HashMap](#key-features-of-concurrent-hashmap)
4.  [Importance of Concurrent HashMap](#importance-of-concurrent-hashmap)
5.  [Code Along: Consistent Concurrent Example](#code-along-consistent-concurrent-example)
6.  [Exploration of ConcurrentMap Interface](#exploration-of-concurrentmap-interface)
7.  [Code Along: Advanced Concurrent Example](#code-along-advanced-concurrent-example)
8.  [Summary and Key Takeaways](#summary-and-key-takeaways)

## Definition of Concurrent `HashMap` (10 minutes)

Concurrent `HashMap`s are an advanced type of `HashMap` designed to accommodate the concurrent access of multiple
threads to a single collection. Unlike standard `HashMap`s, which are not thread-safe, Concurrent `HashMap`s ensure that
your application can scale while maintaining thread safety without compromising on performance.

To provide a better example demonstrating data inconsistency in a concurrent environment without using
`ConcurrentHashMap`, let's consider a scenario where multiple threads increment values associated with the same key in a
`HashMap`. This scenario can lead to data inconsistency because `HashMap` is not thread-safe for concurrent
modifications.

## Code along : Inconsistent Concurrent Example (30 minutes)

```java
package org.example.codealong;

import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Map;

public class DataInconsistencyExample {
    public static void main(String[] args) {
        // Creating a non-thread-safe Map instance (regular HashMap)
        Map<String, Integer> map = new HashMap<>();

        // Creating and starting two threads to concurrently modify the map
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                incrementValue(map, "counter");
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                incrementValue(map, "counter");
            }
        });

        thread1.start();
        thread2.start();

        // Waiting for both threads to complete
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Printing the final value associated with the key "counter"
        System.out.println("Final value: " + map.get("counter"));
    }

    private static void incrementValue(Map<String, Integer> map, String key) {
        boolean modified = false;
        do {
            try {
                // Retrieve the current value associated with the key
                Integer value = map.get(key);
                if (value == null) {
                    // If the key doesn't exist, initialize it with value 1
                    map.put(key, 1);
                } else {
                    // Increment the value and put it back into the map
                    map.put(key, value + 1);
                }
                modified = true;
            } catch (ConcurrentModificationException ex) {
                // Handle ConcurrentModificationException by retrying the operation
            }
        } while (!modified);
    }
}
```

In this example:

- Two threads (`thread1` and `thread2`) concurrently call the incrementValue method to increment the value associated
  with the key "`counter`" in the `HashMap`.
- The `incrementValue` method retrieves the current value associated with the key, increments it, and puts it back into
  the map.
- Since `HashMap` is not thread-safe, concurrent modifications from multiple threads can lead to data inconsistency and
  potential `ConcurrentModificationException`.
- If a `ConcurrentModificationException` occurs during the modification of the map, the method will retry the operation
  until it succeeds without throwing an exception.

When you run this code, you may encounter a `ConcurrentModificationException` or observe incorrect final values
associated with the key "`counter`", demonstrating data inconsistency due to concurrent modifications without proper
synchronization. However, due to the lack of synchronization and the use of a non-thread-safe HashMap, the actual final
value may vary. It could be less than `2000` due to data inconsistency caused by concurrent modifications without proper
synchronization. In practice, running the code multiple times may yield different final values, but they will likely be
less than the expected value of `2000` due to data inconsistency issues.

## Key Features of Concurrent `HashMap` (10 minutes): 

- **Thread-Safety**: Concurrent `HashMap`s provide thread-safe operations on the map, ensuring that data remains
  consistent and free from corruption when accessed by multiple threads.
- **Scalability**: These maps are designed to scale well with the number of threads, offering high throughput and
  performance in concurrent applications.
- **Lock Stripping Technique**: Instead of locking the whole map, Concurrent `HashMap`s use a segment-based locking
  mechanism, which means only a part of the map is locked for updates, thereby allowing concurrent reads and writes
  efficiently.

## Importance of Concurrent `HashMap` (10 minutes)

In multi-threaded applications, ensuring data consistency and integrity is paramount. Concurrent `HashMap`s are designed
to address these challenges by providing a thread-safe way to access and modify shared data. This is crucial in
applications where data is frequently read and modified by multiple threads, such as in web servers, real-time games,
and financial applications.

## Code along: Consistent Concurrent Example (30 minutes)

```java
package org.example.codealong;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ConcurrentMapExamples {
    public static void main(String[] args) {
        // Creating a thread-safe ConcurrentMap instance (ConcurrentHashMap)
        ConcurrentMap<String, Integer> map = new ConcurrentHashMap<>();

        // Creating and starting two threads to concurrently modify the map
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                incrementValue(map, "counter");
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                incrementValue(map, "counter");
            }
        });

        thread1.start();
        thread2.start();

        // Waiting for both threads to complete
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Printing the final value associated with the key "counter"
        System.out.println("Final value: " + map.get("counter"));
    }

    private static void incrementValue(ConcurrentMap<String, Integer> map, String key) {
        // Using compute to safely increment the value associated with the key
        map.compute(key, (k, v) -> (v == null) ? 1 : v + 1);
    }
}
```

In this example:

- We're using a `ConcurrentHashMap` as the implementation of the `ConcurrentMap` interface, ensuring thread-safe
  concurrent access and modification.
- Each thread calls the `incrementValue` method to increment the value associated with the key "`counter`" in the
  concurrent map using the compute method, which safely handles concurrent updates without the need for external
  synchronization.
- The final value printed should be `2000`, as both threads increment the value by `1000` each without encountering data
  inconsistency issues.

## Exploration of ConcurrentMap Interface (30 minutes)

Let's dive into the `ConcurrentMap` interface, a key component in Java's concurrent programming toolkit. This interface
provides a thread-safe variant of the Map interface, allowing for concurrent read and write operations without the need
for external synchronization. We'll explore its most common implementation, Concurrent`HashMap`, and discuss the unique
methods it provides to safely manipulate data in a concurrent environment.

#### Introduction to the ConcurrentMap Interface

The `ConcurrentMap` interface extends the `Map` interface, inheriting its basic operations while also adding atomic
operations that help avoid common concurrency issues. It is designed to handle data structures in multi-threaded
scenarios efficiently.

#### Common Implementations:

- **Concurrent`HashMap`**: The most widely used implementation of ConcurrentMap, designed for high throughput and
  scalability in concurrent applications. Methods Provided by the ConcurrentMap Interface

`ConcurrentMap` introduces several important methods that extend the capabilities of the standard Map interface,
focusing on atomicity and concurrency:

- **putIfAbsent(K key, V value)**: Adds a key-value pair if the key is not already associated with a value.
- **remove(Object key, Object value)**: Removes the entry for a key only if it is currently mapped to a given value.
- **replace(K key, V oldValue, V newValue)**: Replaces the entry for a key only if currently mapped to a specified
  value.
- **replace(K key, V value)**: Replaces the entry for a key only if it is currently mapped to some value.
- **compute(K key, BiFunction**<? super K,? super V,? extends V> remappingFunction): Attempts to compute a mapping for
  the specified key and its current mapped value (or null if there is no current mapping).

These methods are designed to perform compound actions atomically, ensuring data integrity and eliminating the need
for external synchronization.

Let's consider an advanced real-world example involving a caching system for a web application. This caching system
temporarily stores the results of expensive database queries or computations. We aim to ensure that the cache is
efficient, thread-safe, and able to handle concurrent requests without causing duplicated work or data inconsistencies.

## Code Along: Advanced Concurrent Example (30 minutes)

```java
package org.example.codealong;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Logger;

public class UserProfileCacheDemo {
    private static final Logger logger = Logger.getLogger(UserProfileCacheDemo.class.getName());
    // Simulated database storage for user profiles
    private static final ConcurrentMap<String, String> database = new ConcurrentHashMap<>();
    // Cache for storing and retrieving user profiles
    private final ConcurrentMap<String, String> cache = new ConcurrentHashMap<>();

    public UserProfileCacheDemo() {
        // Pre-populate the "database" with user profiles
        database.put("user1", "Profile of User 1");
        database.put("user2", "Profile of User 2");
        database.put("user3", "Profile of User 3");
    }

    // Fetches a user profile, either from the cache or from the "database"
    public String getUserProfile(String userId) {
        return cache.computeIfAbsent(userId, this::fetchUserProfileFromDatabase);
    }

    // Simulates fetching a user profile from a "database"
    private String fetchUserProfileFromDatabase(String userId) {
        // Simulate a time-consuming database operation
        try {
            logger.info("Fetching data for " + userId + " from database...");
            Thread.sleep(2000); // Simulate delay
        } catch (InterruptedException e) {
            logger.severe("Thread interrupted during database fetch operation");
            Thread.currentThread().interrupt();
        }
        return database.getOrDefault(userId, "Profile not found");
    }

    public static void main(String[] args) {
        UserProfileCacheDemo cache = new UserProfileCacheDemo();

        // Simulate multiple threads fetching user profiles
        Runnable fetchUserProfileTask = () -> {
            String profile = cache.getUserProfile("user1");
            logger.info(Thread.currentThread().getName() + " retrieved: " + profile);
        };

        Thread thread1 = new Thread(fetchUserProfileTask);
        Thread thread2 = new Thread(fetchUserProfileTask);
        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.severe("Main thread interrupted");
        }
    }
}

```

In this scenario, we'll implement a cache using `ConcurrentHashMap` that stores user profiles accessed frequently. If a
profile is not in the cache, it's fetched from a database (simulated here as a time-consuming operation) and then stored
in the cache. We'll use the `computeIfAbsent` method to ensure that the database fetch is only executed once per
uncached user, even if multiple threads request the same profile simultaneously.

## Summary & Key Takeaways (5 minutes)

- `ConcurrentHashMap` is an advanced version of `HashMap` that is designed to handle concurrent modifications by
  multiple threads without the need for external synchronization.
- It provides thread safety and maintains high performance in multithreaded environments.
- Key Features:
    - **Thread Safety**: Ensures that data remains consistent and free from corruption when accessed by multiple
      threads.
    - **Scalability**: Designed to scale efficiently with the number of threads, providing high throughput in concurrent
      applications.
    - **Lock Stripping Technique**: Uses a segment-based locking mechanism, which allows a part of the map to be locked
      for updates, thus enabling efficient concurrent reads and writes.
- Suitable for applications where data is frequently read and modified by multiple threads, such as web servers,
  real-time games, and financial applications.

