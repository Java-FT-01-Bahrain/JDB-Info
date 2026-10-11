# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Introduction to Concurrency in Java

| Title                               | Type | Duration | Author               |
|-------------------------------------|------|----------|----------------------|
| Introduction to Concurrency in Java | Lab  | 1:30     | Suresh Melvin Sigera |

## Objective:

To create a Java program that calculates prime numbers using the Runnable interface for concurrency.

### Instructions:

Instructions for creating and using the `PrimeCalculator` class:

1. Create a new Java class named `PrimeCalculator`.
2. Ensure the class implements the `Runnable` interface by adding implements `Runnable` after the class declaration.
3. Define instance variables `start` and `end` to store the range of numbers to check for prime numbers.
4. Implement a constructor that takes two parameters (`start` and `end`) to initialize the instance variables.
5. Implement the `run()` method required by the Runnable interface:
    - Within the `run()` method, print a message indicating the range of numbers being checked for prime numbers.
    - Iterate over each number within the specified range (`start` to `end` inclusive).
    - For each number, call the `isPrime()` method to determine if it is a prime number.
    - If a number is prime, print a message indicating that it is a prime number.
6. Implement a helper method named `isPrime(int num)` to check if a given number is prime:
    - If the number is less than or equal to `1`, return `false` (as prime numbers must be greater than `1`).
    - Iterate from `2` to the square root of the number:
        - If the number is divisible evenly by any of the integers in this range, return `false`.
    - If the above conditions are not met, return true, indicating that the number is prime.

Instructions for using the `PrimeNumberLab` class:

1. In the `main()` method, a `Scanner` object is created to read user input from the console.
2. Prompt the user to input the start and `end range of numbers to check for prime numbers:
    - Use the `Scanner` object to read an `integer` value for the start range.
    - Use the `Scanner` object to read another `integer` value for the end range.
3. Prompt the user to input the number of threads to use for calculating prime numbers:
    - Use the `Scanner` object to read an `integer` value for the number of threads.
4. Close the `Scanner` object to release system resources.
5. Calculate the segment size for each thread:
    - Subtract the start range from the end range and add `1` to include the end range.
    - Divide the result by the number of threads to determine the segment size.
6. Create a fixed thread pool executor using `Executors.newFixedThreadPool(numThreads)`.
7. Iterate over the number of threads specified by the user:
    - Calculate the start and end values for each thread's segment:
        - For the `i-th` thread, set the start value to `startRange + i * segmentSize`.
        - If it's the last thread `(i == numThreads - 1)`, set the end value to `endRange`.
        - Otherwise, set the end value to `start + segmentSize - 1`.
    - Create a `PrimeCalculator` instance with the calculated start and end values.
    - Execute the `PrimeCalculator` instance using the executor's `execute()` method.
8. After all tasks are submitted, shut down the executor using the `executor.shutdown()` method to release resources.

### Testing and Analysis:

- Test the program with various input ranges to check for prime numbers.
- Analyze the results to ensure that prime numbers are correctly identified and displayed.
- Observe the efficiency of the program with different numbers of threads and input ranges.
- Pay attention to any race conditions or synchronization issues and ensure they are properly handled in the code.




