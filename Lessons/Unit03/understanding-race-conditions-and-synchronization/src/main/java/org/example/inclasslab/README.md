# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Introduction to Concurrency in Java

| Title                             | Type | Duration | Author               |
|-----------------------------------|------|----------|----------------------|
| Implementing a Concurrent Program | Lab  | 1:30     | Suresh Melvin Sigera |

## Scenario

You are tasked with developing a multi-threaded application for a library management system. The library has multiple
copies of the same book available for lending. However, to maintain consistency, only a limited number of borrowers are
allowed to borrow the same book concurrently. Your task is to implement a program that simulates book borrowing and
returning using concurrency control mechanisms.

### Instructions:

- Review the starter code provided above. It simulates a library with a limited number of available copies for
  borrowing. The `Semaphore` is used to control access to the shared resource (book copies). Each borrower thread acquires
  a permit from the semaphore before borrowing a book and releases the permit after returning the book.
- Your task is to enhance the program to handle multiple books with different titles. Implement logic to specify the
  title of the book being borrowed or returned.
- Modify the program to include error handling and logging mechanisms for better reliability and debugging.
- Experiment with different concurrency control mechanisms such as locks from the `java.util.concurrent` package, atomic
  variables, or synchronized methods to achieve thread safety and prevent race conditions.
- Test your implementation by running the program with multiple borrower threads and observe the behavior. Ensure that
  the program correctly handles concurrent book borrowing and returning without violating the maximum limit of available
  copies.
- Discuss and analyze the performance implications of different concurrency control mechanisms used in your
  implementation. Compare and contrast their strengths and weaknesses in terms of scalability, complexity, and overhead.


