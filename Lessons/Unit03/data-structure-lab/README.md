# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Lab: Array Manipulation, Expression Balancing, and Stack Reversal

## Introduction

Welcome to today's lab, where we will explore practical applications of arrays and stacks in solving common programming
problems. This lesson includes three exercises designed to enhance your understanding of these data structures. We will
use the default Java Stack class from the java.util package. This choice ensures that we are working with a
well-understood and widely used implementation of the stack data structure, which supports all the necessary operations
we'll need to perform the reversal.

## Learning Objectives

By the end of this lab, you should be able to:

1. Implement and understand the array rotation.
2. Use a stack to verify the correctness of bracketed expressions.
3. Reverse a stack using only standard stack operations.

## Lesson Overview

1. [Excercise 1](#exercise-1-rotate-an-array)
2. [Excercise 2](#exercise-2-expression-balancing-using-stack)
3. [Excercise 3](#exercise-3-reverse-a-stack)
4. [Summary and Key Takeaways](#summary-and-key-takeaways)

## Exercises

### Exercise 1: Rotate an Array

**Objective**: Write a function `rotate(ar[], d, n)` that rotates `arr[]` of size `n` by `d` elements.

- **Input**: An array `arr[]`, the number of positions `d` to rotate, and the size of the array `n`.
- **Output**: The array rotated by `d` positions.

### Instructions

1. Implement the rotation in a function called `rotate`.
2. Handle cases where `d > n`.
3. Test the function with multiple values of `d` and array sizes.

### Exercise 2: Expression Balancing Using Stack

**Objective**: Write a program using the provided Stack class to check whether the pairs and the orders
of `{`, `}`, `(`, `)`, `[`, `]` are correct in an expression string `exp`.

### Instructions

1. Utilize the provided Stack class.
2. Ensure that every opening bracket has a corresponding and correctly ordered closing bracket.
3. Test the program with various expressions to ensure accuracy.

**SingleArrayStack Class**:

```java
public class SingleArrayStack {
    private final int maxSize;
    private final char[] stackArray;
    private int top;

    public SingleArrayStack(int s) {
        this.maxSize = s;
        this.stackArray = new char[maxSize];
        top = -1;
    }

    public char push(char data) {
        if (top < maxSize - 1) {
            top++;
            stackArray[top] = data;
            return data;
        }
        return 0;
    }

    public char pop() {
        if (top >= 0) {
            return stackArray[top--];
        }
        return 0;
    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public boolean isFull() {
        return (top == maxSize - 1);
    }

    public char top() {
        return stackArray[top];
    }

    public void printStack() {
        if (top >= 0) {
            System.out.println("Elements in stack : ");
            for (int i = 0; i <= top; i++) {
                System.out.println(stackArray[i]);
            }
        }
    }
}
```

### Exercise 3: Reverse a Stack

#### Objective

Reverse the elements of a stack using only stack operations (push and pop).

#### Instructions

1. **Use Java Stack**: Utilize the default Java `Stack` data structure from `import java.util.Stack;`.
2. **Method Implementation**: Implement a method to reverse the stack without using any additional data structures.
3. **Testing**: Demonstrate the reversal with different stack contents to ensure the method works correctly.

#### Reflection

After completing the exercise, reflect on:

- **Challenges**: What challenges did you encounter during the implementation?
- **Efficiency**: How efficient is your solution? Could there be a more optimal approach?
- **Applications**: Consider how the stack data structure can be applied in other programming problems. Discuss any
  ideas or insights.

## Summary and Key Takeaways

Today's lab provided hands-on experience with arrays and stacks, highlighting their utility and application in
programming. Understanding these fundamental operations prepares you for more complex data manipulation and algorithmic
challenges. Reflect on how mastering these operations enhances your problem-solving skills in computer science.