| Title       | Type   | Duration | Author        |
| ----------- | ------ | -------- |   ----------  |
| The Heap and The Garbage Collection | Lesson | 0:50     |  Suresh Sigera |


# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) The Heap and The Garbage Collection

## Learning Objectives

At the end of this lesson, students will be able to:

- Describe what the garbage collector is and how it works.
- Specify the heap size when you launch a program.
- Differentiate between the heap and stack.
- Prevent an `OutofMemory` error.

## Opening (5 min)

We've already seen quite a bit about how Java allocates data, objects, arrays, collections, etc. But we've glossed over a few subtle points:
- Where does Java put all this data?
- We can assume that memory is allocated as needed, but where does that memory come from?
- How does Java manage that memory?
- How does memory get reclaimed when the data is no longer required?

This is where the garbage collector comes in. (Don't worry, it isn't as ominous as it sounds.)

## The Garbage Collector (10 min) 

Before Java entered the picture, earlier languages like C and C++ required the programmer to **allocate** memory as needed, then **deallocate** it once it was no longer needed. Java introduced the concept of a **garbage collector**, which relinquished programmers from basic memory allocation or deallocation duties. 

The technology behind garbage collection has dramatically evolved in the last 20 years, and large companies are making a career in optimizing garbage collection. We won't get into the precise details, but the common theme is that the JVM allocates an area of memory called the **heap**, where it stores all objects.
 
When the heap starts to fill up, Java runs a background process (the garbage collector) that looks at every object in the heap and traces its references transitively to determine if they're still directly or indirectly referenced by any live thread. If they're not, then they're eligible for collection.

The garbage collector will mark those for collection and then, in a sweep process, remove that memory and perform compaction so the memory once again becomes available.

Consider the following program:

```java
for (int i = 0; i < 100; i++) {
    String message = "This is message " + i;
    System.out.println(message);
}
```

> **Knowledge Check**: What happens to the `message` object created during the loop?

<details>
    
<summary>Answer</summary>
    
At the end of each loop iteration, the `message` object created during that iteration is no longer reachable. It wasn't assigned, has no references, and has no way to get it back.
    
</details>

> **Knowledge Check**: Based on this information, is the `message` object eligible for collection?

<details>
    
<summary>Answer</summary>
    
Yes, it's eligible for garbage collection. 

</details>


## Heap vs. Stack (10 min) 

You can specify the heap size when you launch your program using launch flags:

```sbtshell
-Xms allocates the initial (and minimum) heap size.
-Xmx allocates the maximum heap size. 
```

For example, the following will allocate an initial memory of 10 GB and increase that to 100 GB:

```java
java -Xms10g -Xmx100g MyCode
```

If your `-Xmx` setting is greater than your `-Xms` setting, Java will start with an initial heap size equal to your `-Xms` setting and allocate more heap as needed until the `-Xmx` value is reached. 

#### Default Heap Size

If you don't specify a heap size, Java will allocate a default size based on your machine's physical memory. However, it's a good practice to determine your maximum expected heap size by observing the program during execution using tools like Linux's `ps` or `top`.

#### The Stack

Note that, when your program executes, even if there isn't a single object allocated, it still uses some memory to keep track of the call stack of every thread running.

For example, if Method A calls Method B calls Method C, Java must remember that when Method C exits, it must return to the spot in Method B that called it and, when that returns, return to Method A. This memory is called the **stack** and is distinct from the heap. 

The stack holds any method-local data that will be swept off once the method returns. The stack works on a 
"last in, first out" (aka LIFO) basis: It's allocated, and data is assigned, then methods call other methods, allocating more stack space. Once a method returns, it is popped from the stack, so the next frame in the stack (representing the calling method) becomes the top of the stack, and so on.

## Demo: `OutOfMemory` Error (10 min) 

Does this mean Java can never run out of memory? 

What will happen when we run this program?

```java
public class OOMError {
    public static void main(String[] args) {
        List<String[]> list = new ArrayList<>();
        
    for(int i = 0; ; i++) {
            Runtime runtime = Runtime.getRuntime();
            System.out.printf("Iteration: %d total memory: %d free memory: %d%n", i, runtime.totalMemory(), runtime.freeMemory());
            list.add(new String[10_000_000]);
 }
 }
}

```

> **Knowledge Check**: Can someone explain what happened? 

This program continuously creates a large array and stores it in a `List`. On each iteration, we display the total and available memory.

Eventually, nothing is eligible for garbage collection because every object is referenced by our `List`, and we exhaust the heap. Java runtime throws up its hands with an `OutOfMemory` error.

Here are two reasons a program might ever run out of memory:

1. The program's memory requirements might exceed the allocated memory. If you have a lot of live data or objects, you need to allocate enough memory for the program to run. You can determine this by watching the program run in development under a production-like load.
1. You might have a **memory leak**. A memory leak occurs when objects are allocated but not released when done — for example, collecting them without end in a list and never letting them go. Another common cause of memory leaks comes from forgetting to release connection resources, such as a database or file system connections. To locate these, review your code, look at all your collections to ensure they're not growing without bound, and check all your connections to ensure you release them when done. Use Java 7's `try... with` resources syntax to defend against memory leaks. 

## Resolving an `OutOfMemory` Error (10 min)

It's possible to run out of memory. That means we should ask ourselves, "What do we do when that happens?"

Our memory leak generator is an extreme example of what can happen if our program creates objects recklessly. To prevent this, do not allow collections to grow indefinitely.

If your collection belongs to a singleton object or is a static reference, many objects might be added. You must rethink your program design if an unlimited number of objects are being added. 

Some other preventive measures include:
- Reusing duplicates.
- Using least-recently-used structures, automatically removing items that have not been referenced for a specified time.
- Consider using weak references (see Java's [WeakHashMap](https://docs.oracle.com/javase/8/docs/api/java/util/WeakHashMap.html)), which clean up when memory is running out.

The bottom line is that **fixing** an `OutOfMemory` error is tricky and generally means changing how your program is designed. (Quickly search on Stack Overflow, and you'll see how many people are trying to figure this out.) It's essential to take **preventive measures** when you set up a program to ensure this doesn't happen (and yes, we know — easier said than done).

## Conclusion (5 min) 

Let's check what we covered:
- How does the garbage collector determine what's eligible for collection? 
- How do you set the heap size for a program?
- What happens if you don't set one?
- How does the stack compare to the heap?
- Describe how to prevent an `OutofMemory` error.
