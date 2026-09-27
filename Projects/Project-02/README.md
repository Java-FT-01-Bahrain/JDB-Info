# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Project 2 - Build a Secure Spring Boot REST API

| Title                           | Type         | Duration | Creator     |
| ------------------------------- | ------------ | -------: | ----------- |
| Unit 2 Project - Backend Application | Unit Project |  7 days | Saad Iqbal |

## Overview

This is your opportunity to put your knowledge of **Java, Object-Oriented Programming, Spring Boot, Spring Security, REST APIs, PostgreSQL, and software engineering practices** into practice by building a complete backend application from the ground up.

You will design and build an application based on **your own idea**.

Your application could be a booking platform, event management system, marketplace, learning platform, fitness application, service-management system, food platform, travel application, or another meaningful business idea.

The idea is yours.

The **technical requirements are NOT optional**.

Your goal is to build an application that demonstrates how a real-world backend application is designed, secured, documented, and maintained.

---

# Project Expectations

Your application must:

* Be built using **Java and Spring Boot**.
* Use **PostgreSQL** as the relational database.
* Run using the embedded **Tomcat** server provided by Spring Boot.
* Expose functionality through a properly designed **REST API**.
* Follow REST conventions and appropriate HTTP methods/status codes.
* Use a layered architecture with separate:

  * Controllers
  * Services
  * Repositories
  * Models/Entities
* Follow **OOP principles** throughout the application.
* Apply **KISS** and **DRY** principles.
* Be developed incrementally using Git and GitHub.

The application should feel like a real backend rather than a collection of unrelated endpoints.

---

# Choose Your Own Application

You are responsible for deciding what you want to build.

Your application should solve a meaningful problem and have clearly defined users.

Examples include:

* Event booking platform
* Restaurant reservation system
* Fitness/class booking platform
* Hotel or travel management system
* Marketplace
* Learning management platform
* Appointment scheduling system
* Service-provider platform
* Library management system
* Sports facility booking platform
* Pet-care platform
* Car rental system
* Any other approved application idea

Your idea should provide enough complexity to satisfy all the technical requirements below.

### Important

Do not simply build a collection of CRUD endpoints.

Your application should contain **business rules and workflows**.

For example:

> A user should not be able to book an unavailable resource.

> A cancelled booking should release the resource.

> An administrator should be able to deactivate a user.

> A user should only be able to modify their own profile.

> An unverified user should not be allowed to log in.

---

# Technical Requirements

## 1 Database & Data Models

Your application must persist at least **five models/entities** in PostgreSQL.

Your models should have meaningful relationships where appropriate.

Examples of relationships could include:

* One-to-One
* One-to-Many
* Many-to-One
* Many-to-Many

Your database design should include:

* Primary keys
* Foreign keys
* Appropriate data types
* Appropriate constraints
* Meaningful relationships
* Appropriate nullable/non-nullable fields

You must provide an **Entity Relationship Diagram (ERD)** showing your database design.

---

## 2. Spring Profiles

Environment-specific settings must be configured using **Spring Profiles**.

At minimum, demonstrate appropriate separation between environments such as:

* Development
* Test

Sensitive configuration such as database credentials and secrets should **not be hard-coded** into your source code.

Use environment variables or appropriate configuration mechanisms for sensitive information.

---

## 3. REST API & CRUD

Your application must provide RESTful API endpoints.

Where it makes logical sense for your business domain, resources should support complete:

* Create
* Read
* Update
* Delete

operations.

Use appropriate REST conventions.

For example:

```text
GET     /api/products
GET     /api/products/{id}
POST    /api/products
PUT     /api/products/{id}
DELETE  /api/products/{id}
```

Do not create unnecessary CRUD operations simply to satisfy a requirement. The API should reflect your application's actual business requirements.

---

## 4. HTTP Status Codes

Your API must return appropriate HTTP status codes.

Examples include:

* `200 OK`
* `201 Created`
* `204 No Content`
* `400 Bad Request`
* `401 Unauthorized`
* `403 Forbidden`
* `404 Not Found`
* `409 Conflict`
* `422 Unprocessable Entity`
* `500 Internal Server Error`

Do not return `200 OK` for every situation.

---

## 5. Validation

Implement server-side validation for incoming data.

Your API should validate things such as:

* Required fields
* Email formats
* Password requirements
* String lengths
* Numeric ranges
* Dates
* Business-specific rules

Invalid input should produce a useful and structured error response.

---

## 6. Exception Handling

Your application must gracefully handle exceptions.

- Do not expose raw Java stack traces or internal implementation details to API users. 
- In the event that an exception occurs, you should send appropriate error messages back to the user
- Create a consistent error-response structure.

For example:

```json
{
  "timestamp": "2026-09-21T10:30:00",
  "status": 404,
  "error": "RESOURCE_NOT_FOUND",
  "message": "Booking with ID 42 was not found",
  "path": "/api/bookings/42"
}
```

Use appropriate Spring exception-handling mechanisms such as a global exception handler.

---

## 7. Authentication & Security

Use:

* **Spring Security**
* **JWT authentication**

to secure your application.

The majority of your application routes should require authentication.

The following types of routes may remain public where appropriate:

* User registration
* User login
* Email verification
* Password recovery

All other protected functionality should require a valid JWT.

---

## 8. Login & JWT

Implement a secure login workflow.

Successful authentication should provide a JWT that can be used to access protected endpoints.

Demonstrate that:

* Public endpoints can be accessed without authentication.
* Protected endpoints require authentication.
* Invalid/expired tokens are rejected.
* Users cannot access resources they are not authorized to access.

---

## 9. Role Management

Your application must contain at least **two different user roles**.

For example:

* USER
* ADMIN

You may create additional roles if your application requires them.

Roles must actually affect what users are allowed to do.

For example:

* A normal user cannot access administrative endpoints.
* An admin can manage users.
* A user can manage their own resources.
* An admin can deactivate users.

Simply storing a role in the database without using it for authorization does not satisfy this requirement.

---

## 10. User Registration & Email Verification

Users must be able to register for an account.

Implement an email verification workflow.

A user should:

1. Register.
2. Receive an email verification mechanism.
3. Verify their email.
4. Become eligible to log in.

Unverified users should not be allowed to access protected functionality.

You may use a development-friendly email service or mock email mechanism if a real email provider is not available.

---

## 11. Password Management

Implement:

### 11.1  Forgot Password / Password Recovery

Users should be able to recover their account if they forget their password.

---

### 11.2 Change Password

Authenticated users should be able to change their password.

Passwords must never be stored as plain text.

Use an appropriate password hashing mechanism provided by Spring Security.

---

## 12. User Profile

Users must be able to manage their own profile.

The profile should contain appropriate information for your application.

Users should be able to update their own profile, including a **profile picture**.

A user must not be able to modify another user's profile unless they have the appropriate administrative permissions.

---

## 13. File Upload

Implement a file-upload feature appropriate to your application.

Users should be able to upload:

* A single file/image
* Multiple files/images

depending on your business requirements.

* Profile picture(s) (required)

Examples:

* Product images
* Documents
* Event images
* Attachments

Validate uploaded files appropriately.

---

## 14. Soft Delete

Implement soft deletion where appropriate.

For users, an administrator deleting a user must **not physically remove the user from the database**.

Instead, update an appropriate status field, such as:

```text
userStatus = INACTIVE
```

Inactive users should not be able to authenticate or use protected functionality where appropriate.

Apply the same principle to other important business entities where permanent deletion would be inappropriate.

---

## 15. Booking / Reservation Workflow

Your application must include a meaningful **booking, reservation, appointment, allocation, or availability-based workflow**.

The exact terminology should match your application idea.

For example:

* A customer books a room.
* A student books a class.
* A patient books an appointment.
* A customer books a service.
* A user reserves an event seat.

Your application must support:

* Availability management
* Creating a booking
* Viewing booking status
* Cancelling a booking
* Updating booking status
* Preventing invalid bookings
* Preventing **double-booking**

### Double-booking requirement

Your application must ensure that two users cannot successfully reserve the same unavailable resource/time slot.

For example:

> If Room A is already booked from 2:00 PM–3:00 PM, another user must not be able to create a booking for Room A during the same unavailable period.

Implement appropriate business logic to enforce this rule.

---

## 16. Booking Status

Bookings should have meaningful statuses.

For example:

```text
PENDING
CONFIRMED
CANCELLED
COMPLETED
```

The exact statuses should depend on your business domain.

The API should enforce valid transitions between statuses.

For example, a cancelled booking should not simply become confirmed again without appropriate business logic.


---

## 17. API Documentation — Swagger / OpenAPI

Your REST API must be documented using **Swagger/OpenAPI**.

Your documentation should include:

* Available endpoints
* HTTP methods
* Endpoint descriptions
* Path parameters
* Query parameters
* Request bodies
* Response bodies
* HTTP status codes
* Authentication requirements
* JWT/security configuration
* Example requests/responses where appropriate

Someone unfamiliar with your application should be able to use your Swagger documentation to understand how to interact with your API.

---

## 18. DTOs & API Design

Where appropriate, do not expose your database entities directly through your API.

Use **DTOs (Data Transfer Objects)** for API requests and responses where they provide a clear benefit.

For example:

```text
UserEntity
      ↓
UserResponseDTO
```

Avoid exposing sensitive information such as:

* Password hashes
* Internal security information
* Unnecessary database fields

---

## 19. Database Seeding

Provide a **seed mechanism** that populates the database with initial data when the application is first configured.

Your seed data should include enough information to demonstrate your application.

For example:

* Admin user
* Normal users
* Categories
* Sample resources
* Sample bookings
* Other required reference data

The application should be easy for another developer or instructor to run without manually entering every record.

---

## 20. Business Rules

Your application must contain meaningful business logic.

Do not put all logic inside controllers.

Business rules should be implemented in the appropriate service layer.

Examples:

* A user cannot book an unavailable resource.
* A user cannot cancel a completed booking.
* An inactive user cannot log in.
* An admin can manage users.
* A user cannot modify another user's private information.
* A booking cannot be created for a date in the past.

Your application should contain at least **five meaningful business rules** that go beyond simple CRUD operations.

---

## 21. Real-Time Notifications

Implement WebSockets or Server-Sent Events (SSE) to notify connected clients when an important event occurs.

The implementation must be demonstrable without requiring a frontend application. Students may use Postman, a browser-based client, or another appropriate testing tool to establish the connection and demonstrate that the server can push an event to the connected client.

Demonstrate at least one meaningful real-time workflow, such as:

 - Booking confirmed
 - Booking cancelled
 - Booking status changed
 - New message received
 - Appointment updated
 - Resource becomes available
 - Admin notification

Example: A client establishes a WebSocket connection. When a booking is confirmed through the REST API, the server immediately sends a notification through the WebSocket connection. The client receives the notification without making another REST request.

---

## 22. Logging

Implement appropriate application logging.

Log important events such as:

* Authentication attempts
* Important business operations
* Errors/exceptions
* Booking creation/cancellation
* Administrative actions

Do not log sensitive information such as passwords or JWT secrets.

---

## 23. API Search / Filtering

Your API should support at least basic filtering or searching where it makes sense for your application.

For example:

```text
GET /api/products?category=electronics
GET /api/bookings?status=CONFIRMED
GET /api/events?location=Manama
```

The filtering requirements should be meaningful for your chosen application.

---

## 24. API Response Design

Responses should be consistent and meaningful.

Avoid returning unnecessary database fields.

Where appropriate, include:

* IDs
* Relevant resource information
* Status
* Messages
* Timestamps
* Related resource information

Your API should be understandable to another developer consuming it.

---

## 25. Timestamps & Auditing

Important entities should maintain appropriate timestamps, such as:

```text
createdAt
updatedAt
```

For important business operations, consider maintaining information such as:

```text
createdBy
updatedBy
```

This should be applied where it makes sense for your application.

---

## 26. Architecture

Follow a clean layered architecture.

A typical structure may look like:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Database
```

You should also organize your application appropriately for:

* DTOs
* Security
* Exceptions
* Configuration
* Utilities
* Mappers
* WebSocket/SSE functionality

Do not put all application logic into one class.

---

## 27. Git & Branching

Do not develop the entire project on the main branch.

Use appropriate Git branches during development.

For example:

```text
main
develop
feature/authentication
feature/bookings
feature/file-upload
feature/notifications
```

Your Git history should tell the story of how your application was developed.

Commit early and commit often.

Avoid meaningless commit messages such as:

```text
update
changes
fix
final
test
```

Use meaningful messages such as:

```text
Add JWT authentication
Implement booking availability validation
Add email verification workflow
Create global exception handler
Add Swagger API documentation
Implement real-time booking notifications
```

---

## 28. Code Quality

Your code should:

* Follow Java naming conventions.
* Be formatted consistently.
* Avoid unnecessary duplication.
* Avoid unused code.
* Avoid unnecessary complexity.
* Follow KISS principles.
* Follow DRY principles.
* Use appropriate OOP principles.
* Have clear responsibilities for classes and methods.

Do not submit dangling or unused code.

---

## 29. Documentation & Comments

It is important to document your code appropriately.

Use:

* JavaDoc
* Inline comments where useful
* Meaningful class/method names

Do not comment every obvious line.

Comments should explain **why** something is being done when the reason is not obvious.

For example:

```java
/**
 * Creates a booking after verifying that the requested
 * resource is available for the selected time period.
 *
 * @param request booking information supplied by the user
 * @return the newly created booking
 */
public BookingResponse createBooking(CreateBookingRequest request) {
    // Business validation is performed before creating the booking.
}
```

---

## 30. Testing

Although this project is primarily focused on application development, you should write tests for important functionality.

At minimum, demonstrate testing of important business logic such as:

* Authentication
* Validation
* Booking availability
* Booking cancellation
* Role-based authorization
* Important service-layer logic

You are encouraged to follow a **Test Driven Development (TDD)** approach:

1. Write a test.
2. Write the minimum code required to pass it.
3. Refactor.
4. Repeat.

Advanced testing requirements will be expanded in later projects.

---

## 31. Security Considerations

Your application should demonstrate awareness of common security concerns.

At minimum:

* Never store plain-text passwords.
* Never commit secrets to GitHub.
* Validate user input.
* Protect private endpoints.
* Validate JWTs.
* Enforce role-based authorization.
* Restrict access to user-owned resources.
* Avoid exposing sensitive information in API responses.
* Validate file uploads.
* Handle authentication failures appropriately.

---

## 32 Email Notifications

Send automated emails for important events.

For example:

* Registration
* Email verification
* Password recovery
* Booking confirmation
* Booking cancellation
* Status changes

---

## 33 Pagination

Implement pagination for large collections.

For example:

```text
GET /api/products?page=0&size=10
```

Return appropriate pagination metadata.

---

## 34 Sorting

Allow API consumers to sort results.

For example:

```text
GET /api/products?sort=price,asc
```

---

## 35 API Rate Limiting

Implement basic rate limiting for selected public endpoints such as:

* Login
* Registration
* Password recovery

---

## 36 Audit Log

Create an audit trail for important administrative and business actions.

For example:

```text
User 42 cancelled Booking 108
Admin 1 deactivated User 42
User 15 updated their profile
```

---

## 37. User Stories

Before implementation, define your application's users and their needs.

Create user stories in Trello using the format:

> As a [type of user], I want to [perform an action], so that [reason].

Example:

> As a customer, I want to book an available appointment so that I can reserve a service at a convenient time.

Your project must contain meaningful user stories covering the major functionality of your application.

---

## 38. Planning

Create a project plan in Trello showing how you will complete the project within the available timeframe.

Break the project into smaller deliverables.

For example:

```text
Day 1   Planning, user stories, ERD, project setup
Day 2   Database models and repositories
Day 3   CRUD APIs
Day 4   Authentication and JWT
Day 5   User profiles and file uploads
Day 6   Booking/business rules
Day 7   Email verification/password recovery
Day 8   WebSockets/SSE + Swagger
Day 9   Testing, validation and bug fixing
Day 10  Documentation, cleanup and presentation
```

Your actual plan should reflect your own application.

---

## 39. API Documentation

Your README must include a clear API endpoint reference.

For example:

| Request Type | URL                    | Functionality       | Access  |
| ------------ | ---------------------- | ------------------- | ------- |
| POST         | `/auth/users/login`    | User login          | Public  |
| POST         | `/auth/users/register` | User registration   | Public  |
| GET          | `/api/categories`      | Get categories      | Private |
| GET          | `/api/bookings`        | Get user's bookings | Private |
| POST         | `/api/bookings`        | Create booking      | Private |
| DELETE       | `/api/bookings/{id}`   | Cancel booking      | Private |
| GET          | `/api/admin/users`     | Manage users        | Admin   |

This table should be consistent with your actual implementation.

Swagger/OpenAPI must provide the detailed interactive documentation.

---

## 40. README.md Requirements

Your GitHub repository must contain a comprehensive `README.md`.

It must include:

### Project Information

* Project title
* Project description
* Application purpose
* Main features

### Technologies

Document the tools and technologies used.

For example:

* Java
* Spring Boot
* Spring Security
* JWT
* PostgreSQL
* Maven/Gradle
* Swagger/OpenAPI
* WebSockets/SSE
* Git/GitHub

### Architecture

Explain your application's architecture and major components.

### General Approach

Include a couple of paragraphs explaining:

* How you approached the project.
* How you structured your application.
* How you implemented the major features.

### User Stories

Provide a link to your user stories.

### ERD

Provide a link/image of your ERD.

### Planning

Provide a link to your planning documentation/GitHub Project showing:

* Deliverables
* Timeline
* Scope
* Progress

### API Documentation

Provide access to your Swagger/OpenAPI documentation.

### Installation

Provide clear instructions explaining how another developer can:

1. Clone the repository.
2. Configure the application.
3. Configure PostgreSQL.
4. Configure environment variables.
5. Seed the database.
6. Start the application.
7. Access the API.
8. Access Swagger/OpenAPI.

### Unsolved Problems

Document any unresolved issues.

### Major Challenges

Explain the major technical problems you encountered and how you solved them.

### Future Improvements

Explain what you would add if you had more time.

---

## 41. Credits & External Resources

Software engineers regularly use documentation, tutorials, Stack Overflow, GitHub, AI tools, libraries, and other resources.

You must give appropriate credit to resources that helped you.

For external resources, document:

* Resource name
* Full URL
* What you used it for

You should also acknowledge classmates, instructors, or other people who provided meaningful assistance.

Do not claim someone else's work as your own.

---

# Required Deliverables

You must submit:

### 1. GitHub Repository

Create a **new repository on your personal GitHub account**.

**Do not fork the provided repository.**

The repository should be public unless otherwise instructed.

### 2. Source Code

The complete working application.

### 3. README.md

Containing all required documentation.

### 4. ERD

A complete database relationship diagram.

### 5. User Stories

Documented user stories for your application.

### 6. Planning Documentation

Your project plan, scope, timeline and progress.

### 7. API Documentation

Swagger/OpenAPI documentation.

### 8. Seed Data

The database should be populated with useful sample data.

### 9. Git History

Aim for approximately **100 commits or more** throughout the project.

The goal is not to artificially create commits.

Your Git history should demonstrate genuine incremental development.

Commit early and commit often.

---

# Bonus Requirements

The following features are optional but can earn additional credit.

## Bonus 1 —> Frontend

Develop a frontend for your application.

You may use technologies such as:

* React
* JSP
* Another approved frontend technology

The frontend should consume your REST API rather than bypassing the backend.

---

## Bonus 2 —> Admin Panel

Create a separate admin interface for **Admin-only** functionality.

Administrators could:

* Manage users
* Manage categories
* View bookings
* Change booking statuses
* View application statistics
* Manage uploaded content

---

## Bonus 3 —> Responsive Design

If you build a frontend, make it responsive and usable across:

* Desktop
* Tablet
* Mobile

---

## Bonus 4 —> Client-Side Validation

If you build a frontend, implement client-side validation in addition to server-side validation.

---

## Bonus 5 —> Third-Party API

Integrate a relevant third-party API.

Examples:

* Maps/location API
* Payment service
* Email service
* Weather API
* Currency API
* AI API
* SMS/notification service

The integration should have a meaningful purpose in your application.

---

## Bonus 6 —> Real-Time Chat

Extend your WebSocket/SSE implementation to support a meaningful real-time communication feature.

For example:

* User-to-admin messaging
* Customer-to-service-provider chat
* Support chat
* Event notifications

---

## Bonus 7 —> Advanced Search

Implement more advanced search functionality involving multiple criteria.

For example:

```text
GET /api/events/search?
    category=sports
    &location=Manama
    &date=2026-10-10
```

## Bonus 8 —> Health Check

Create an endpoint that reports the health/status of your application and its important dependencies.

For example:

```text
GET /api/health
```

---



# Submission

Submit your project through the designated course [submission tracker](https://docs.google.com/spreadsheets/d/1rJ2j18dy11KLDCFmvNZHMj1GMiWqbhn4pn7Ddpb3ML4/edit?gid=1436972657#gid=1436972657).

When submitting, include:

* GitHub repository
* Project name
* Application description
* Any questions you would like answered
* Specific areas where you would like feedback

After submission, notify the team in the appropriate Slack channel.

---

# Presentation

You will have approximately **15 minutes** to present your project.

Everyone is expected to attend the presentations in their entirety.

Do not work on your own code while another student is presenting.

Use the presentations as an opportunity to learn how other students approached similar engineering problems.

Your presentation should cover:

### The Application

* What did you build?
* What problem does it solve?
* Who are the users?

### Technical Implementation

* Architecture
* Database design
* Authentication/security
* REST API
* Business logic
* Booking/availability workflow
* WebSockets/SSE
* Swagger/OpenAPI

### Development Process

* How did you plan the project?
* How did you use Git?
* What was difficult?
* What problems did you encounter?

### Reflection

* What are you most proud of?
* What did you learn?
* What would you do differently?
* What would you build next?

### Q&A

Be prepared to explain your technical decisions.

---

# Plagiarism & Academic Integrity

This project is designed to give you practical experience building a real application.

These assignments are similar to tasks you may encounter during:

* Technical interviews
* Take-home coding challenges
* Junior software engineering roles
* Backend development work

You are expected to understand and be able to explain the code you submit.

Do not copy and paste another student's work or directly from AI.

Do not submit an application that you cannot explain.

You are encouraged to use:

* Official documentation
* Tutorials
* Stack Overflow
* GitHub
* AI coding assistants
* Other legitimate learning resources

However, you remain responsible for understanding, testing, and validating the code you submit.

Give credit to resources and people who helped you.

If you are struggling, ask your instructors for help. The purpose of this project is to learn, not simply to produce a finished application.

---

# Useful Resources

You are encouraged to use official documentation, including:

- **[Java API Documentation](https://docs.oracle.com/en/java/javase/17/docs/api/)**
- **[Spring Framework Documentation](https://docs.spring.io/spring-framework/reference/)**
- **[Spring Boot Documentation](https://docs.spring.io/spring-boot/documentation.html)**
- **[Spring Security Documentation](https://docs.spring.io/spring-security/reference/)**
- **[PostgreSQL Documentation](https://www.postgresql.org/docs/)**
- **[Swagger / OpenAPI Documentation](https://swagger.io/docs/)**
- **[GitHub Documentation](https://docs.github.com/)**
- **[ERD / Database Design Tool (dbdiagram.io)](https://dbdiagram.io/)**

---

# Final Project Goal

By the end of this project, you should have built a **secure, documented, database-driven Spring Boot REST API** that resembles a small real-world application.

Your application should demonstrate that you can:

**Design → Model → Build → Secure → Validate → Document → Test → Demonstrate**

Do not focus only on making the endpoints work.

Focus on building an application that another developer could realistically understand, run, use, and continue developing.

**Build something you would be proud to show in a technical interview.**


---
