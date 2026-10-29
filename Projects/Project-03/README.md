# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Unit 3 Project - Concurrency & Multithreading Backend Application

| Title                                                             | Type         | Duration | Creator    |
| ----------------------------------------------------------------- | ------------ | -------: | ---------- |
| Unit 3 Project - Concurrency & Multithreading Backend Application | Unit Project |   7 days | Saad Iqbal |

# Overview

This project is the **second stage of your backend application**.

You will continue developing the **same application you built in Project 2**. You should **not start a completely new application**.

In Project 2, you built the foundation of your application, including its database, REST API, authentication, business workflows, documentation, and other backend functionality.

In Project 3, your goal is to **extend that application** and introduce more advanced backend behavior, with a particular focus on:

* Concurrency
* Multithreading
* Thread safety
* Race conditions
* Concurrent database operations
* Asynchronous processing
* Maintaining data consistency under concurrent requests

Your application should now demonstrate that it can handle situations where **multiple users or operations attempt to access or modify the same resources at the same time**.

> **Project 2: Build → Project 3: Extend & Handle Concurrency → 4: Productionize**

The goal is not to rebuild what you already have.

The goal is to **take your existing application to the next level**.

---

# Project Expectations

Your Project 3 application should:

* Continue from your completed Project 2 application.
* Preserve the existing functionality that is still relevant.
* Add meaningful new functionality.
* Introduce concurrency and multithreading into appropriate parts of the application.
* Identify at least one real business scenario where concurrent operations can cause a problem.
* Implement an appropriate solution to protect shared resources and maintain data consistency.
* Demonstrate and test the concurrent behavior.
* Update your existing documentation and project planning.
* Continue developing incrementally using Git and GitHub.

Do not add concurrency simply for the sake of using threads.

The concurrency implementation should solve a **real problem in your application's business domain**.

For example:

* A booking system may need to prevent two users from booking the same resource simultaneously.
* A car rental system may need to prevent the same vehicle from being rented twice.
* A restaurant reservation system may need to prevent overbooking.
* A marketplace may need to prevent inventory from becoming negative.
* A sports facility system may need to prevent two users from reserving the same time slot.
* A ticketing system may need to prevent the same ticket from being sold multiple times.

Your specific concurrency problem should make sense for **your application's domain**.

---

# Technical Requirements

## 1. Extend Your Existing Application

Continue working with the application you created in Project 2.

You must:

* Keep your existing database and application structure.
* Continue using your existing authentication and authorization system.
* Continue using your existing REST API.
* Extend existing functionality where appropriate.
* Add new functionality that supports the requirements of this project.

Your Project 3 application should clearly feel like the **next version of the same application**.

---

## 2. Extend Your Data Model

Expand your existing database with **at least five additional meaningful model/entity**.

Your application should contain **at least ten meaningful models/entities in total**.

The new model should support functionality introduced in Project 3 rather than being added only to increase the model count.

Update your:

* Entity relationships
* Database schema
* ERD
* Seed data
* DTOs
* API documentation

where necessary.

---

## 3. Add New Business Functionality

Add at least **one significant new business workflow** to your application.

The new functionality should provide an opportunity for concurrent operations.

Examples:

* Booking or reservation management
* Inventory allocation
* Ticket purchasing
* Seat reservation
* Appointment scheduling
* Resource allocation
* Order processing
* Queue management
* Payment/order processing
* Limited-capacity event registration

The functionality should contain meaningful business rules rather than being another simple CRUD feature.

---

## 4. Concurrency & Multithreading

This is the **main focus of Project 3**.

You must identify a part of your application where multiple operations can occur concurrently.

You should demonstrate your understanding of concepts such as:

* Threads
* `Runnable`
* `Callable`
* `ExecutorService`
* Thread pools
* `Future`
* Shared state
* Race conditions
* Thread safety
* Synchronization
* Locks
* Atomic operations
* Concurrent collections

You do not need to use every concurrency API.

Use the techniques that are appropriate for the problem you are solving.

---

## 5. Create a Concurrency-Sensitive Business Scenario

Your application must contain at least **one business operation that can be affected by concurrent requests**.

For example:

```text
Available seats: 1

User A → attempts to reserve the seat
User B → attempts to reserve the same seat
```

Your application must prevent both users from successfully claiming the same resource.

The exact scenario should be appropriate to your application.

---

## 6. Document a Race Condition

You must be able to explain:

1. What shared resource is being accessed.
2. Why multiple operations can occur at the same time.
3. What race condition could occur.
4. What incorrect result could occur without concurrency control.
5. How your implementation prevents the problem.

Your documentation should explain the problem in the context of your own application.

---

## 7. Implement Thread-Safe Processing

Implement an appropriate technique to make your concurrent operation thread-safe.

Depending on your application, this could involve:

* `synchronized`
* `Lock`
* `ReentrantLock`
* Atomic classes
* Thread-safe collections
* Database transactions
* Optimistic locking
* Pessimistic locking
* Database constraints
* Atomic database updates

Choose an approach that makes sense for your business problem.

You should be able to explain **why you selected that approach**.

---

## 8. Use ExecutorService or an Appropriate Thread Pool

Use `ExecutorService` or another appropriate thread-pool mechanism for at least one meaningful concurrent task.

Demonstrate that you understand:

* Task submission
* Multiple concurrent tasks
* Thread-pool management
* Task completion
* Handling task results where appropriate

For example:

```java
ExecutorService executor = Executors.newFixedThreadPool(5);
```

The thread pool should be used for an actual application requirement rather than being included only as a demonstration.

---

## 9. Concurrent REST Requests

Your application must demonstrate what happens when multiple requests are made to the same endpoint at approximately the same time.

For example:

```text
POST /api/bookings
```

Two or more requests should attempt to access the same limited resource.

The application must produce a consistent result.

For example:

```text
Request A → 201 Created
Request B → 409 Conflict
```

The exact response will depend on your application's business rules.

---

## 10. Database Concurrency

Your concurrency solution must consider the database.

Explain and demonstrate how your application maintains database consistency when multiple operations occur concurrently.

You may use appropriate techniques such as:

* Transactions
* Optimistic locking
* Pessimistic locking
* Database constraints
* Atomic updates
* Appropriate isolation behavior

The database should not be left in an inconsistent state simply because two requests happened at the same time.

---

## 11. Extend Your Booking / Resource Workflow

If your application already contains a booking, reservation, inventory, or resource-allocation workflow, extend it to support concurrent operations.

For example:

```text
Project 2:
User → Book Resource

Project 3:
User A ─┐
        ├──→ Book Resource → Concurrency Control
User B ─┘
```

The application must correctly handle competing requests.

If your application does not use bookings or reservations, implement an equivalent concurrency-sensitive workflow appropriate to your domain.

---

## 12. Asynchronous / Background Processing

Implement at least **one meaningful asynchronous or background operation**.

Examples include:

* Sending email notifications
* Processing notifications
* Generating reports
* Processing uploaded files
* Background data processing
* Updating derived information
* Performing a long-running calculation

The operation should not unnecessarily block the main request when it can reasonably be processed asynchronously.

---

## 13. Extend Real-Time Functionality

Continue using the real-time functionality introduced in Project 3 where applicable.

Update your WebSocket/SSE functionality if the new Project 3 features require additional events or notifications.

For example:

```text
Booking Created
Booking Confirmed
Booking Failed
Resource Unavailable
```

The events should reflect meaningful changes in your application's business workflow.

---

## 14. Update Existing APIs

Update your existing REST API to support the new Project 3 functionality.

Add any required:

* Endpoints
* Request DTOs
* Response DTOs
* Validation
* Business rules
* Error responses
* Status codes

Do not duplicate existing endpoints unnecessarily.

---

## 15. Update API Documentation

Update your existing Swagger/OpenAPI documentation.

Document all new or modified endpoints, including:

* Request parameters
* Request bodies
* Response bodies
* Status codes
* Validation errors
* Authentication requirements

Your documentation should reflect the **current version of your application**.

---

## 16. Update Logging

Add meaningful logging around the new concurrent and asynchronous operations.

Logs should help you understand:

* Which operation was executed.
* When it started.
* When it completed.
* Whether it succeeded or failed.
* Relevant identifiers.
* Concurrency-related failures.

Do not log passwords, tokens, or other sensitive information.

---

## 17. Testing

Extend your existing tests to cover the new functionality.

You must include tests for:

* New business functionality.
* New business rules.
* Successful concurrent operations.
* Conflicting concurrent operations.
* Race-condition scenarios.
* Thread-safe behavior.
* Important failure scenarios.

Your tests should demonstrate that the application produces a consistent result when multiple operations occur concurrently.

---

## 18. Code Quality & Refactoring

Review the code you created in Project 2 and refactor where necessary.

Your Project 3 code should demonstrate:

* Clear separation of responsibilities.
* Reusable services.
* Appropriate abstractions.
* Meaningful naming.
* Minimal duplication.
* Maintainable concurrency code.
* Appropriate exception handling.

Concurrency code can become difficult to maintain quickly.

Keep the implementation as simple as possible while correctly solving the problem.

---

## 19. Git & Incremental Development

Continue using the existing Git repository.

Create meaningful commits throughout the project.

Use branches where appropriate, for example:

```text
feature/concurrency
feature/thread-pool
feature/booking-locking
feature/async-processing
feature/concurrency-tests
```

Your Git history should demonstrate genuine incremental development.

---

## 20. User Stories

Continue maintaining the user stories from Project 2.

Add new user stories for the functionality introduced in Project 3.

At least one user story must describe a concurrency-sensitive scenario.

For example:

> **As a user, I want to reserve an available resource so that I can use it at my selected time.**

And:

> **As a user, I want the system to prevent another user from reserving the same resource at the same time.**

Your user stories should describe the **new functionality and behavior introduced in Project 3**.

---

## 21. Planning

Update your existing project plan rather than creating an unrelated plan.

Your planning documentation should show:

* Project 3 scope.
* New features.
* Concurrency-related tasks.
* Database changes.
* API changes.
* Testing tasks.
* Documentation tasks.
* Progress.

Break the work into manageable tasks.

For example:

```text
Day 1
→ Extend data model
→ Add new business workflow

Day 2
→ Implement concurrent scenario
→ Identify race condition

Day 3
→ Implement thread-safety solution
→ Implement ExecutorService/thread pool

Day 4
→ Implement asynchronous processing
→ Update APIs

Day 5
→ Testing
→ Concurrent testing

Day 6
→ Documentation
→ Refactoring

Day 7
→ Final testing
→ Presentation preparation
```

---

## 22. API Documentation

Update the API documentation created in 2.

Your Swagger/OpenAPI documentation should include the new and modified endpoints introduced during Project 3.

Pay particular attention to documenting:

* Successful responses.
* Conflict responses.
* Validation errors.
* Authentication requirements.
* Concurrency-related business errors.

---

## 23. README.md Requirements

Update the existing `README.md`.

Do not create a completely separate README from Project 2.

Add a section explaining the major changes introduced in Project 3.

Your README should clearly explain:

### Project 3 Changes

Describe:

* New functionality.
* New models.
* Concurrency-sensitive workflow.
* Race condition being addressed.
* Concurrency technique used.
* Database consistency approach.
* Asynchronous processing.

### Concurrency Explanation

Explain:

* Where concurrency occurs.
* What could go wrong without concurrency control.
* How your implementation prevents the problem.
* Why you selected your particular approach.

### Testing

Explain how you tested concurrent operations.

Include examples where appropriate.

### Updated API

Document the new or modified endpoints.

### Challenges

Describe the major challenges you encountered while implementing concurrency.

### Future Improvements

Explain what you would improve with additional time.

### Credits & External Resources

Continue updating the credits section from Project 2.

Document any external resources used during Project 3, including:

* Documentation
* Tutorials
* Stack Overflow
* GitHub
* Libraries
* AI tools
* Other technical resources

For each significant external resource, include:

* Resource name
* Full URL
* What you used it for

You should also acknowledge classmates, instructors, or other people who provided meaningful assistance.

Do not claim someone else's work as your own.

---

# Required Deliverables

Submit the updated version of your Project 3 application containing the Project 3 work.

You must submit:

### 1. GitHub Repository

Continue using your Project 2 GitHub repository.

Do not create an unrelated repository for Project 3.

### 2. Source Code

The complete updated application.

### 3. Updated README.md

Your README must document the Project 3 changes.

### 4. Updated ERD

Update your ERD to reflect any new models or relationships.

### 5. Updated User Stories

Include the new Project 3 user stories.

### 6. Updated Planning Documentation

Show the Project 3 scope, tasks, timeline, and progress.

### 7. Updated API Documentation

Swagger/OpenAPI documentation reflecting the current application.

### 8. Seed Data

Update seed data where required by the new functionality.

### 9. Git History

Your repository should demonstrate genuine incremental development throughout Project 3.

### 10. Concurrency Demonstration

You must provide evidence that your application can handle the selected concurrent scenario correctly.

This may include:

* Screenshots
* Test results
* API requests/responses
* Automated tests
* Console output
* Other appropriate evidence

---

# Bonus Requirements

The following features are optional but can earn additional credit.

## Bonus 1 — Advanced Concurrency

Implement a more advanced concurrency scenario involving multiple resources or multiple stages of processing.

---

## Bonus 2 — CompletableFuture

Use `CompletableFuture` for meaningful asynchronous processing.

Demonstrate appropriate use of:

* Chaining
* Combining tasks
* Error handling
* Asynchronous execution

---

## Bonus 3 — Advanced Thread Pool Management

Implement and justify a more advanced executor configuration.

Explain why the selected configuration is appropriate for your workload.

---

## Bonus 4 — Concurrency Performance Comparison

Compare two different approaches to solving a problem.

For example:

```text
Sequential Processing
        vs
Concurrent Processing
```

Document:

* Test scenario
* Number of operations
* Execution time
* Observed results
* Limitations of your comparison

---

## Bonus 5 — Advanced Database Locking

Implement and demonstrate an advanced database concurrency strategy such as:

* Optimistic locking
* Pessimistic locking

Explain the trade-offs of your chosen approach.

---

## Bonus 6 — Advanced Real-Time Processing

Extend your real-time functionality to support more complex concurrent events.

For example:

* Live booking updates
* Real-time resource availability
* Live order processing updates
* Support notifications

---

## Bonus 7 — Frontend

Continue or create a frontend that consumes your REST API.

The frontend should demonstrate the new Project 3 functionality.

---

## Bonus 8 — Third-Party API

Integrate a relevant third-party API that adds meaningful functionality to your application.

---

## Bonus 9 — Advanced Testing

Implement additional automated tests specifically designed to exercise concurrent behavior.

For example:

* Multiple simultaneous requests
* Stress testing
* Repeated race-condition tests
* Concurrent integration tests

---

# Submission

Submit your updated project through the designated course submission tracker.

When submitting, include:

* GitHub repository
* Project name
* Application description
* Summary of Project 3 changes
* Concurrency scenario
* Concurrency technique used
* Any questions you would like answered
* Specific areas where you would like feedback

After submission, notify the team in the appropriate Slack channel.

---

# Presentation

You will have approximately **15 minutes** to present your project.

Your presentation should focus on what you **added and improved in Project 3** rather than repeating the entire Project 2 presentation.

You should cover:

### 1. Application Overview

Briefly explain your application and the new functionality.

### 2. New Business Functionality

Explain what you added in Project 3.

### 3. Concurrency Problem

Explain the real-world concurrency problem your application addresses.

### 4. Race Condition

Demonstrate what could happen without appropriate concurrency control.

### 5. Solution

Explain and demonstrate how your implementation prevents the problem.

### 6. Multithreading

Show where you used:

* Threads
* ExecutorService
* Thread pools
* Callable/Future
* Synchronization
* Locks
* Or another appropriate concurrency technique

### 7. Database Consistency

Explain how you protect the database from inconsistent concurrent operations.

### 8. Demonstration

Perform a live demonstration of concurrent requests or concurrent tasks.

### 9. Testing

Show how you tested the concurrency behavior.

### 10. Challenges

Discuss the most difficult technical problem you encountered and how you solved it.

---

# Plagiarism & Academic Integrity

All work submitted must be your own.

You may use:

* Documentation
* Tutorials
* Stack Overflow
* GitHub
* AI tools
* Other learning resources

However, you are responsible for understanding the code you submit.

You should be able to explain:

* Your architecture.
* Your business logic.
* Your concurrency implementation.
* Your synchronization strategy.
* Your database consistency approach.
* Your tests.

External resources and AI assistance should be appropriately acknowledged.

Do not submit code that you cannot explain or defend.

---

# Useful Resources

Continue using the resources from Project 2 where they remain relevant.

For Project 3, focus particularly on documentation and learning resources related to:

- [Java Concurrency](https://docs.oracle.com/javase/tutorial/essential/concurrency/)
- [Java Threads](https://docs.oracle.com/javase/tutorial/essential/concurrency/threads.html)
- [`Runnable`](https://docs.oracle.com/en/java/javase/26/docs/api/java.base/java/lang/Runnable.html)
- [`Callable`](https://docs.oracle.com/en/java/javase/26/docs/api/java.base/java/util/concurrent/Callable.html)
- [`ExecutorService`](https://docs.oracle.com/en/java/javase/26/docs/api/java.base/java/util/concurrent/ExecutorService.html)
- [Thread Pools](https://docs.oracle.com/en/java/javase/26/docs/api/java.base/java/util/concurrent/ThreadPoolExecutor.html)
- [`Future`](https://docs.oracle.com/en/java/javase/26/docs/api/java.base/java/util/concurrent/Future.html)
- [`CompletableFuture`](https://docs.oracle.com/en/java/javase/26/docs/api/java.base/java/util/concurrent/CompletableFuture.html)
- [Synchronization](https://docs.oracle.com/javase/tutorial/essential/concurrency/sync.html)
- [Locks](https://docs.oracle.com/en/java/javase/26/docs/api/java.base/java/util/concurrent/locks/package-summary.html)
- [Atomic Classes](https://docs.oracle.com/en/java/javase/26/docs/api/java.base/java/util/concurrent/atomic/package-summary.html)
- [Thread-Safe Collections](https://docs.oracle.com/en/java/javase/26/docs/api/java.base/java/util/concurrent/package-summary.html#ConcurrentCollections)
- [Spring Transactions](https://docs.spring.io/spring-framework/reference/data-access/transaction.html)
- [Database Locking](https://www.postgresql.org/docs/current/explicit-locking.html)
- [Optimistic Locking](https://docs.oracle.com/middleware/1212/toplink/TLJPA/a_optimisticlocking.htm#TLJPA485)
- [Pessimistic Locking](https://docs.jmix.io/2.x/jmix/2.1/features/pessimistic-lock.html)
- [Concurrent Testing](https://docs.oracle.com/en/java/javase/26/docs/api/java.base/java/util/concurrent/package-summary.html)
- [Spring Boot Asynchronous Processing](https://docs.spring.io/spring-framework/reference/integration/scheduling.html)

Use official documentation and reputable technical resources whenever possible.

---

# Final Project Goal

By the end of Project 3, your application should demonstrate that you can take an existing backend application and **extend it to safely handle concurrent operations**.

You should be able to demonstrate the complete progression:

> **Build → Extend → Identify Shared State → Create Concurrent Operations → Observe Race Conditions → Apply Thread Safety → Protect Database Consistency → Process Tasks Concurrently → Test → Document**

The most important question is not:

> **"Did you use threads?"**

It is:

> **"Can your application correctly handle multiple operations happening at the same time?"**

Your final application should demonstrate that you understand not only **how to create concurrent code**, but also **when concurrency creates problems and how to design a safe solution**.
