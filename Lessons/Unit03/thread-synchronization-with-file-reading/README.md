# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Thread Synchronization with File Reading Lab

| Title                                    | Type | Duration | Author               |
|------------------------------------------|------|----------|----------------------|
| Thread Synchronization with File Reading | Lab  | 2:00     | Suresh Melvin Sigera |

## Learning objectives:

- Understand the concept of thread synchronization in Java.
- Learn to use synchronized methods and synchronized blocks to ensure thread safety.
- Apply synchronization techniques to a scenario involving concurrent file reading by multiple threads.

### Instructions:

In this lab, you will explore the importance of thread synchronization in Java programming. You will work with a
scenario where multiple threads read from a file concurrently and add the read lines to a shared data structure. Your
task is to ensure that the shared data structure is accessed and modified safely by employing synchronized methods and
synchronized blocks.

1. **Understanding the Starter Code**:
   - Review the provided starter code (`FileReadingLab.java`) to understand the structure of the program.
   - Identify the shared resource and the methods involved in reading from the file and manipulating the shared
     resource.
2. **Implementing Synchronization**:
   - Modify the code to ensure that access to the shared resource (sharedResource) is synchronized.
   - Implement synchronization using both synchronized methods and synchronized blocks.
   - Ensure that only one thread can modify the shared resource at a time to prevent data corruption and race
     conditions.
3. **Testing the Synchronization**:
   - Compile and run the modified program to observe the behavior of multiple threads reading from the file
     concurrently.
   - Verify that the shared resource is accessed safely by all threads and that no data inconsistencies occur.
4. **Reflecting on Thread Synchronization**:
   - Discuss the importance of thread synchronization in multi-threaded programming.
   - Compare the use of synchronized methods and synchronized blocks in ensuring thread safety.
   - Reflect on potential performance implications of synchronization and strategies for optimizing synchronization in
     real-world applications.

**Additional Notes**:

- You can use the provided sample file content ("`data.txt`") for testing the program. Ensure that the file is saved in
  the same directory as your Java source code.
- Experiment with different numbers of threads to observe how thread synchronization affects the behavior of the
  program.
- Document any observations, challenges faced, and insights gained during the lab exercise for discussion during the
  debriefing session.

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