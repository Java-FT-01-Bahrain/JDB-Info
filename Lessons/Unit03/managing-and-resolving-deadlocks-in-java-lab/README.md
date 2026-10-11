# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Managing and Resolving Deadlocks in Java: A Practical Approach

| Title                                                          | Type | Duration | Author               |
|----------------------------------------------------------------|------|----------|----------------------|
| Managing and Resolving Deadlocks in Java: A Practical Approach | Lab  | 3:00     | Suresh Melvin Sigera |

## Learning objectives

- Demonstrate creating and resolving a deadlock using Java
- Implement a simple deadlock prevention mechanism in Java
- Familiarize with the Banker’s algorithm for deadlock avoidance
- Understand the role of Java’s thread management methods: `wait()`, `notify()`, and `notifyAll()`
- Implement these methods in a producer-consumer problem scenario to manage thread communication effectively.

### Task 1: Understanding and Demonstrating Deadlocks

Create a simple Java application that intentionally causes a deadlock. Then, modify the application to resolve the
deadlock using a basic strategy like lock ordering.

### Task 2: Deadlock Management Strategies

Extend the previous application to incorporate a deadlock prevention mechanism, such as acquiring locks in a consistent
order.

### Task 3: Java Thread Management

Create a Java program that simulates a producer-consumer problem using `wait()` and `notify()` methods, incorporating a
controlled stop mechanism to manage thread execution effectively.

### Bonus : Deadlock Detection with Banker’s Algorithm

Simulate a scenario where multiple threads request resources and implement the Banker’s algorithm to safely allocate
resources without causing deadlocks.

```java
public class BankersAlgorithm {
    // Implement the Banker's algorithm logic here
    // This is a conceptual task and may require a thorough explanation and guided setup.
}
```

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