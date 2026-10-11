# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Understanding Stacks

## Introduction

Data structures like stacks and queues are crucial in programming for efficient data management. This lesson will focus
on these structures, their implementations using arrays, and their fundamental operations.

## Learning Objectives

By the end of this lesson, you will be able to:

1. Explain the concept of stacks and their LIFO (Last In, First Out) operation.
2. Understand and implement basic operations on stacks: push, pop, and peek.

## Lesson Overview

1. [Stacks: Introduction, Implementation, and Operations](#stacks-introduction-implementation-and-operations)
    - [Push Operation](#push-operation)
    - [Pop Operation](#pop-operation)
    - [Peek Operation](#peek-operation)
2. [Demo: Implement a Stack](#demo-java-stack-implementation)
3. [Summary and Key Takeaways](#summary-and-key-takeaways)

## Stacks: Introduction, Implementation, and Operations

In this section, we will look at two new types of data structures. First, we will see stacks and its implementation
using arrays. Then, we will move on to the second type of data structure called queues and its implementation using
arrays.

You will also see what are double ended queues and how to implement them using arrays. So let's just start with the
stacks. Stacks are one of the very basic data structures. A stack can be thought of as a container in which we can
insert data from the top. So that means that every new data element you push into the stack sits on the top.

### push

This operation of inserting new data into the top of the stack is called push. So every new element that you push onto
the stack sits on top of the stack .

### pop

You can also remove or take out data elements from the stack, but note that we can remove only the topmost element of
the stack. We cannot take out a data element from the middle or bottom of the stack. So every time we take out an
element, it will always be the top element of the stack. We call this as popping an item from the top of the stack, or
it's called a pop operation. So what the pop method or operation does is it removes the top element of the stack and
returns it. So if you want to remove the last element of the stack, you will have to take out all the other elements on
top of it.

### peek

There is another operation that we can use on stacks, and that is to peek at the stack. What the peek method does is it
reads the value of the topmost data element without removing it and returns that value. So if we call the peek method on
this stack, it will read the value of the topmost element, which is two. That's it. Stacks are also called LIFO, which
stands for Last In First Out, because of the way data is arranged in the stack. That is, the last element to go into the
stack is on the top, which also happens to be the first one to come out.

So to sum up, there are three operations that can be carried out on stacks, push, pop, and peek.

Notice that a push puts the new element at the end of the array, not the beginning. Inserting at the beginning of an
array is expensive, an **O(n)** operation, because it requires all existing array elements to be shifted in memory.
Adding at the end is **O(1)**; it always takes the same amount of time, regardless of the size of the array.

**Fun fact about stacks:** Each time you call a function or a method, the CPU places the return address on a stack. When
the function ends, the CPU uses that return address to jump back to the caller. That's why if you call too many
functions -- for example in a recursive function that never ends -- you get a so-called "stack overflow" as the CPU
stack has run out of space.

### Demo: Java Stack Implementation

A stack is a fundamental data structure in computer science, characterized by its Last In First Out (LIFO) behavior. In
this session, we will explore how stacks operate and the common operations associated with them such as `push`, `pop`,
and `peek`.

```java
public class Stack {
    int capacity;
    int[] array;
    int top;

    Stack(int sizeOfStack) {
        capacity = sizeOfStack;
        array = new int[capacity];
        top = -1;
    }

    void push(int element) {
        if (isFull()) {
            System.out.println("Stack is full!!! No more push operations are allowed.");
            return;
        }
        System.out.println("Inserting element : " + element + " into the stack.");
        this.array[++top] = element;
    }

    int pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty!!! Cannot perform pop operation.");
            return -1;
        }
        System.out.println("Removing element :" + this.array[top] + " from the stack");
        return this.array[top--];
    }

    int peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty!!! Cannot perform peek operation.");
            return -1;
        }
        System.out.println("Peek of the stack is : " + this.array[top]);
        return this.array[top];
    }

    boolean isEmpty() {
        return (top == -1);
    }

    boolean isFull() {
        return (top == (capacity - 1));
    }
}
```

```java
public class StackDemo {
    public static void main(String[] args) {
        Stack stack = new Stack(10);
        stack.push(10);
        stack.push(20);
        System.out.println(stack.peek());
        stack.push(30);
        System.out.println(stack.pop());
        System.out.println(stack.peek());
    }
}
```

### Reflection

- **Discuss the Challenges**: After completing the exercise, reflect on the challenges you encountered while
  implementing the stack methods.
- **Evaluate Your Understanding**: Consider how well you understand the operations of a stack and what might still be
  unclear.
- **Feedback**: Provide feedback on the exercise and how it helped you understand the stack operations better.

## Summary and Key Takeaways

1. **Understanding of LIFO Operations**: Students gain a thorough understanding of the Last In First Out (LIFO)
   principle crucial to stack operations. The practical exercise of implementing `push`, `pop`, and `peek` methods
   allows students to experience how data is managed in a stack.
2. **Practical Java Programming Skills**: Completing the stack implementation in Java enhances students' practical
   programming skills. They learn to write clean and efficient code, handling tasks such as checking if the stack is
   full or empty—skills vital for building robust software applications.
3. **Problem-Solving and Debugging**: The exercise provides an opportunity to apply theoretical knowledge practically,
   sharpening problem-solving skills. Debugging the stack implementation deepens understanding of data structures and
   improves coding accuracy, preparing students for complex programming challenges.

Remember, the key to mastering programming concepts is practice and more practice. Don’t hesitate to revisit the
concepts discussed and re-implement the stack if necessary.
