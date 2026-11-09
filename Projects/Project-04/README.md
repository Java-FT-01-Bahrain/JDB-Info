# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Java - Productionization Project

| Title                                               | Type         | Duration | Creator    |
| --------------------------------------------------- | ------------ | -------: | ---------- |
| Unit 4 Project - Productionized Backend Application | Unit Project |   10 days | Saad Iqbal |

# Overview

This is the **final stage of your backend application**.

You will continue working on the **same application you developed in project 2 and extended in project 3**.

Do **not** start a completely new application.

In project 2, you built the core backend application.

In project 3, you extended the application with concurrency and multithreading.

In this final project, your goal is to take that application and move it toward a **production-ready, distributed, containerized, and deployable system**.

You will apply the DevOps and distributed-systems concepts covered in this unit, including:

* Microservices Architecture
* Docker
* Docker Compose
* Kafka
* CI/CD
* AWS
* Cloud Deployment
* Infrastructure and deployment automation

The goal is to move from:

> **"My application works on my computer."**

to:

> **"My application can be built, containerized, deployed, scaled, monitored, and maintained using production-oriented practices."**

---

# Project Expectations

Your final application should:

* Continue from your project 3 codebase.
* Preserve the existing application functionality.
* Refactor appropriate parts of the application into a microservices architecture.
* Containerize the application using Docker.
* Introduce asynchronous communication using Apache Kafka.
* Use caching where appropriate.
* Implement a CI/CD pipeline.
* Deploy the application to AWS.
* Document the architecture and deployment process.
* Demonstrate that the system can be built and deployed without relying entirely on manual steps.

You are **productionizing an existing application**, not rebuilding it from scratch.

---

# Continue Your Existing Application

Your project 4 application must be a continuation of project 3.

You should retain the business domain, users, authentication, database, business workflows, concurrency functionality, and other meaningful features already developed.

The new work should focus on **changing how the application is structured, deployed, communicated, and operated**.

You may refactor existing code where necessary to support the new architecture.

For example:

```text
project 2
    ↓
Monolithic Spring Boot Application
    ↓
project 3
    ↓
Concurrency + Multithreading
    ↓
project 4
    ↓
Microservices + Docker + Kafka
    ↓
CI/CD + AWS
```

---

# Technical Requirements

## 1. Microservices Architecture

Refactor your existing application into a **microservices-oriented architecture**.

You should identify logical business capabilities within your application and separate them into appropriate services.

For example:

```text
                    API / Gateway
                         |
          +--------------+--------------+
          |              |              |
          ↓              ↓              ↓
     User Service   Booking Service  Product Service
          |              |              |
          +--------------+--------------+
                         |
                       Kafka
                         |
          +--------------+--------------+
          |                             |
          ↓                             ↓
 Notification Service            Other Services
```

The exact services depend on your application.

Do not split the application into services simply to increase the number of services.

Each service should have a meaningful responsibility.

---

## 2. Service Boundaries

Document why you created each service.

For every service, explain:

* Its responsibility.
* The business functionality it owns.
* Its APIs.
* Its data.
* How it communicates with other services.
* Whether communication is synchronous or asynchronous.

Your service boundaries should follow **business responsibilities**, not simply individual controllers or Java packages.

---

## 3. Independent Spring Boot Services

Each major microservice should be independently runnable.

A service should have its own:

* Spring Boot application.
* Configuration.
* Controllers where required.
* Services.
* Repositories where required.
* Dockerfile.
* Dependencies.
* API endpoints.

Services should not depend on another service's internal Java classes.

Communication between services should happen through defined APIs or messaging.

---

## 4. Service Data Ownership

Where appropriate, each microservice should own the data required for its business responsibility.

Avoid creating a distributed system where every service directly accesses every other service's database tables.

Document:

* Which service owns which data.
* Which schema it uses.
* How other services obtain information they need.

---

## 5. Docker

Containerize the application.

Create appropriate Dockerfiles for your services.

Each Dockerfile should define the necessary:

* Base image
* Dependencies
* Application build
* Runtime configuration
* Exposed ports
* Startup command

The application should be able to run without requiring the developer to manually install all application dependencies on the host machine.

---

## 6. Docker Compose

Use **Docker Compose** to run the local distributed environment.

Your Compose configuration should orchestrate the required components, such as:

* Microservices
* PostgreSQL
* Kafka
* Kafka-related infrastructure
* Other required services

Services should communicate using Docker networking rather than relying on hard-coded localhost addresses.

For example:

```text
docker-compose.yml

    user-service
    booking-service
    notification-service
    postgres
    kafka
```

---

## 7. Configuration & Environment Variables

Move environment-specific configuration out of the source code.

Use environment variables or appropriate configuration mechanisms for values such as:

* Database credentials
* Database URLs
* JWT secrets
* Kafka configuration
* Service URLs
* AWS configuration
* Cache configuration

Do not commit passwords, API keys, private keys, or other secrets to GitHub.

---

## 8. Apache Kafka

Introduce **Apache Kafka** for asynchronous communication between appropriate services.

Identify at least one meaningful workflow where an event should be published and consumed asynchronously.

For example:

```text
Booking Service
      |
      | BookingCreated
      ↓
    Kafka
      |
      +------------→ Notification Service
      |
      +------------→ Analytics Service
```

Possible events include:

* UserRegistered
* BookingCreated
* BookingConfirmed
* BookingCancelled
* OrderCreated
* PaymentCompleted
* InventoryUpdated

Choose events that make sense for your application.

---

## 9. Kafka Producers & Consumers

Implement at least:

* One Kafka producer.
* One Kafka consumer.
* One meaningful event/topic.

Document:

* Topic name.
* Event structure.
* Producer service.
* Consumer service.
* What happens when the event is received.

For example:

```text
Topic:
booking-events

Event:
{
    "bookingId": 123,
    "userId": 45,
    "status": "CONFIRMED"
}
```

---

## 10. Event-Driven Processing

At least one important workflow should use asynchronous event processing.

The producer should not need to directly call every downstream service synchronously when an event-based architecture is more appropriate.

Demonstrate the benefit of decoupling services.

For example:

```text
Booking Service
      |
      ↓
BookingCreated Event
      |
      ↓
Kafka
      |
      +----→ Notification Service
      |
      +----→ Analytics Service
```

---

## 11. Kafka Reliability

Consider what happens when:

* A consumer is temporarily unavailable.
* A message is processed more than once.
* A service fails while processing a message.
* Messages arrive faster than they can be processed.

Implement reasonable handling for the scenarios relevant to your application.

Document the assumptions and limitations of your implementation.

---

## 12. CI/CD Pipeline

Create a CI/CD pipeline for your application.

The pipeline should automate as much of the software delivery process as practical.

A basic pipeline should include:

```text
Git Push
   ↓
Build
   ↓
Run Tests
   ↓
Build Docker Images
   ↓
Push Images
   ↓
Deploy
```

Your pipeline should fail when an important build or test step fails.

---

## 13. Continuous Integration

Your CI pipeline should automatically perform appropriate checks when code is pushed or a pull request is created.

At minimum, include:

* Build.
* Automated tests.
* Application/package validation.

Where appropriate, also include:

* Code quality checks.
* Docker image building.
* Security checks.

---

## 14. Docker Image Registry

Push your application Docker images to an image registry.

You may use an appropriate registry such as:

* Amazon ECR
* Docker Hub
* Another approved container registry

Images should use meaningful version tags.

Avoid relying only on the `latest` tag.

For example:

```text
booking-service:1.0.0
booking-service:1.1.0
```

---

## 15. AWS Deployment

Deploy your productionized application to **AWS**.

The exact AWS services may depend on the architecture you choose.

Possible components include:

* EC2
* ECR
* VPC
* IAM
* Load Balancer
* RDS
* EKS
* CloudWatch
* S3

You do not need to use every AWS service.

Use services that make sense for your architecture.

---

## 16. Cloud Database

Your deployed application should use an appropriate cloud-accessible database.

Where appropriate, use a managed database service such as **Amazon RDS** rather than running a production database manually inside an application container.

Your application should use environment-specific database configuration.

---

## 17. AWS Security

Apply appropriate cloud security practices.

Consider:

* IAM permissions.
* Security groups.
* Network access.
* Secrets.
* Database access.
* Public versus private resources.

Do not expose databases unnecessarily to the public internet.

Do not commit AWS credentials to GitHub.

---

## 18. Deployment Architecture

Create an architecture diagram showing how the productionized application works.

The diagram should show the major components, such as:

```text
                         Internet
                            |
                            ↓
                     Load Balancer
                            |
                            ↓
                     Kubernetes
                         Cluster
                            |
          +-----------------+----------------+
          |                 |                |
          ↓                 ↓                ↓
     User Service     Booking Service   Other Services
          |                 |
          +--------+--------+
                   |
                 Kafka
                   |
                   ↓
          Notification Service

                   |
                 Ignite
                   |
                   ↓
                  RDS
```

Your actual diagram should reflect your implementation.

---

## 19. Deployment Documentation

Document the complete deployment process.

A developer should be able to understand:

1. How to build the application.
2. How to build the Docker images.
3. How to run the application locally.
4. How to start Kafka.
7. How to configure AWS.
8. How to deploy the services.
9. How to verify the deployment.

---

## 20. Monitoring & Logging

Ensure that your productionized application produces useful logs.

Where appropriate, demonstrate how you can inspect:

* Application logs.
* Container logs.
* Service failures.
* Kafka consumer/producer activity.

If you use AWS monitoring services, document how they are used.

---

## 21. Preserve project 3 Concurrency

Do not remove the concurrency functionality from project 3.

Your productionized application should continue to handle the concurrent business scenario correctly.

Where appropriate, demonstrate how concurrency behaves when multiple service instances are running.

For example:

```text
             Load Balancer
                  |
        +---------+---------+
        |                   |
        ↓                   ↓
 Booking Pod 1         Booking Pod 2
        |                   |
        +---------+---------+
                  |
              Database
```

Your design should maintain data consistency across multiple instances.

---

## 22. Testing

Continue using the automated tests from the previous projects.

Add tests where necessary for the productionized architecture.

Test important areas such as:

* Microservice APIs.
* Kafka event processing.
* Concurrent behavior.
* Service communication.
* Dockerized application behavior.
* Configuration.
* Health endpoints.

Where practical, verify that the application still behaves correctly after being containerized and deployed.

---

## 23. Cucumber Testing

Use Cucumber and Behavior-Driven Development (BDD) to test key application workflows and business requirements.

Create clear Gherkin feature files that describe expected application behavior using scenarios written in Given, When, Then format.

Add Cucumber tests where appropriate to cover important areas of the productionized architecture, such as:

* Microservice API workflows and responses.
* Service-to-service communication.
* Kafka event publishing and consumption.
* Concurrent application behavior where applicable.
* Application configuration and environment-specific behavior.
* Health and monitoring endpoints.

---

## 24. Code Quality & Refactoring

Review the entire application before final submission.

Remove:

* Unused code.
* Dead code.
* Debug statements.
* Hard-coded credentials.
* Hard-coded environment-specific URLs.
* Unnecessary dependencies.

Maintain:

* KISS.
* DRY.
* Clear service boundaries.
* Meaningful naming.
* Appropriate documentation.
* Maintainable configuration.

---
---
---

# Architecture Documentation

Create documentation explaining your final architecture.

Your documentation should include:

### Current Architecture

Explain the final microservices architecture.

### Service Responsibilities

Explain what each service does.

### Communication

Explain:

* REST communication.
* Kafka communication.
* Synchronous versus asynchronous operations.

### Data

Explain database ownership and data flow.

### Infrastructure

Explain Docker, AWS, and supporting infrastructure.

### Deployment

Explain how CI/CD is used.

---

# User Stories

Continue maintaining the user stories from the previous projects.

Add or update user stories where the production architecture introduces meaningful operational requirements.

For example:

> **As a system administrator, I want the application services to be independently deployable so that one service can be updated without rebuilding the entire application.**

> **As a system administrator, I want services to automatically restart when a container fails so that the application can recover from common failures.**

> **As a user, I want my booking request to be processed reliably even when multiple users are using the application at the same time.**

---

# Planning

Update your existing project plan.

Your planning should include the productionization work.

For example:

```text
Day 1
→ Identify microservice boundaries
→ Design architecture
→ Refactor application

Day 2
→ Create Dockerfiles
→ Docker Compose
→ Container networking

Day 3
→ Kafka producers/consumers
→ Event-driven workflow

Day 4
→ AWS infrastructure

Day 5
→ CI/CD

Day 6
→ Deployment
→ Testing
→ Monitoring/logging

Day 7
→ Fix issues
→ Documentation
→ Final presentation
```

Adjust the plan according to your application.

---

# API Documentation

Continue maintaining your existing API documentation.

Update Swagger/OpenAPI documentation for any new or modified APIs.

Document service-specific APIs where appropriate.

Your documentation should clearly identify:

* Endpoint.
* HTTP method.
* Request.
* Response.
* Authentication.
* Status codes.

Kafka events should also be documented separately from REST APIs.

---

# README.md Requirements

Update the existing `README.md`.

Your README should now describe the **final productionized application**.

Include:

### Project Overview

Explain the application and what it does.

### Architecture

Explain the microservices architecture.

### Technologies

Include the technologies used, such as:

* Java
* Spring Boot
* PostgreSQL
* Docker
* Docker Compose
* Apache Kafka
* CI/CD
* AWS

### Services

List each microservice and its responsibility.

### Architecture Diagram

Include your final architecture diagram.

### Kafka

Document:

* Topics.
* Producers.
* Consumers.
* Events.

### Docker

Explain how to build and run the containers.

### CI/CD

Explain the pipeline stages.

### AWS

Document the AWS services used.

### Deployment

Provide deployment instructions.

### Environment Variables

Document required configuration without exposing secrets.

### Testing

Explain how the application was tested.

### Challenges

Describe major technical challenges.

### Unsolved Problems

Document anything that remains unresolved.

### Future Improvements

Describe what you would improve with more time.

---

# Credits & External Resources

Continue updating your credits from the previous projects.

Document external resources used during project 4, including:

* Official documentation.
* Tutorials.
* GitHub repositories.
* Stack Overflow.
* AI tools.
* Docker resources.
* AWS documentation.
* Kafka documentation.
* Other technical resources.

For each significant resource, include:

* Resource name.
* Full URL.
* What you used it for.

Also acknowledge meaningful assistance from instructors, classmates, or other people.

---

# Required Deliverables

You must submit the **productionized version of your project 3 application**.

### 1. GitHub Repository

Continue using your existing project repository.

### 2. Source Code

Complete source code for all services.

### 3. Docker Configuration

Include:

* Dockerfiles.
* Docker Compose configuration.
* Supporting Docker configuration.

### 4. Kafka Configuration

Include:

* Producers.
* Consumers.
* Event models.
* Topic configuration where applicable.

### 5. CI/CD Configuration

Include the pipeline configuration.

### 6. AWS Deployment

Provide evidence of the AWS deployment.

This may include:

* Deployment screenshots.
* AWS architecture diagram.
* Running application endpoint.
* Relevant AWS resources.

Do not include credentials or other secrets.

### 7. Architecture Diagram

Provide the final production architecture diagram.

### 08. Updated README.md

The README must contain the complete documentation for the final application.

### 9. Updated ERD

Update the ERD where the architecture or data model has changed.

### 10. Updated Planning

Provide the updated project plan and progress.

### 11. API Documentation

Provide updated Swagger/OpenAPI documentation.

### 12. Git History

Your Git history should demonstrate genuine incremental development.

Commit early and often.

---

# Bonus Requirements

The following features are optional but can earn additional credit.

## Bonus 1 — API Gateway

Implement an API Gateway so clients do not need to communicate directly with every microservice.

---

## Bonus 2 — Service Discovery

Implement service discovery appropriate to your architecture.

---

## Bonus 3 — Kubernetes Autoscaling

Implement Kubernetes horizontal autoscaling for an appropriate service.

Demonstrate the conditions under which additional replicas are created.

---

## Bonus 4 — Kubernetes Ingress

Configure an Ingress solution to expose the appropriate services through a unified entry point.

---

## Bonus 5 — Advanced Kafka

Implement additional Kafka capabilities such as:

* Multiple consumers.
* Consumer groups.
* Multiple topics.
* Retry handling.
* Dead-letter processing.

---

## Bonus 6 — Advanced Caching

Implement advanced Apache Ignite caching behavior.

For example:

* Cache expiration.
* Cache invalidation.
* Distributed cache.
* Cache-aside pattern.

---

## Bonus 7 — Infrastructure as Code

Extend the Ansible implementation to automate more of the infrastructure setup.

---

## Bonus 8 — Advanced CI/CD

Extend the pipeline with additional stages such as:

* Docker image security scanning.
* Automated deployment.
* Environment promotion.
* Rollback.
* Versioned releases.

---

## Bonus 9 — AWS Observability

Implement additional monitoring and observability using appropriate AWS services.

---

## Bonus 10 — Blue/Green or Rolling Deployment

Implement a deployment strategy that minimizes application downtime during releases.

---

# Submission

Submit the final productionized project through the designated course submission tracker.

Include:

* GitHub repository.
* Application name.
* Application description.
* Architecture diagram.
* AWS deployment information.
* Summary of productionization work.
* Technologies used.
* Any questions you would like answered.
* Specific areas where you would like feedback.

After submission, notify the team in the appropriate Slack channel.

---

# Asking for Help — GitHub Issues

Use GitHub Issues when you need technical help.

Issues should clearly describe:

```text
I tried ___.

I expected ___ to happen.

___ happened instead.

My repository is ___.

My question is about ___.
```

Include relevant:

* Error messages.
* Logs.
* Configuration.
* Steps to reproduce.

Do not include passwords, API keys, AWS credentials, private keys, or other secrets.

Close your issue once the problem has been resolved and document the solution where appropriate.

---

# Presentation

You will have approximately **15 minutes** to present your final project.

Your presentation should focus on the journey from the original application to the final productionized system.

Suggested structure:

### 1. Application Overview

Briefly explain your application.

### 2. Architecture Before

Show how the application was structured before productionization.

### 3. Architecture After

Show your final microservices architecture.

### 4. Microservices

Explain:

* Services created.
* Responsibilities.
* Service boundaries.

### 5. Docker

Demonstrate your containerized application.

### 6. Kafka

Demonstrate an event flowing between services.

For example:

```text
Service A
   ↓
Kafka
   ↓
Service B
```

### 7. CI/CD

Show your pipeline and explain the stages.

### 8. AWS

Demonstrate your cloud deployment and explain the AWS architecture.

### 9. Production Challenges

Explain the biggest challenges you encountered.

### 10. What You Would Improve

Explain what you would change if you had more time.

### 11. What You Learned

Discuss the most important technical concepts you learned.

### 12. Q&A

Be prepared to answer questions about your architecture and implementation.

---

# Plagiarism & Academic Integrity

All work submitted must be your own.

You may use:

* Official documentation.
* Tutorials.
* Stack Overflow.
* GitHub.
* AI tools.
* Other learning resources.

However, you are responsible for understanding the architecture and code you submit.

You should be able to explain:

* Why you selected your microservice boundaries.
* How your services communicate.
* How Kafka works in your application.
* How Docker is used.
* How your CI/CD pipeline works.
* How your AWS deployment works.

Do not submit infrastructure or application code that you cannot explain.

External resources and AI assistance should be appropriately acknowledged.

---

# Useful Resources

Use official documentation and reputable technical resources whenever possible.

Recommended areas of study include:

* Spring Boot
* Spring Cloud
* Microservices Architecture
* Docker
* Docker Compose
* Apache Kafka
* CI/CD
* AWS
* Amazon ECR
* Amazon RDS
* Amazon EKS
* IAM
* CloudWatch
* Load Balancing
* Container networking
* Infrastructure automation

---

# Final Project Goal

This is the **final stage of your application development journey**.

You started by building a backend application.

Then you introduced concurrency and multithreading.

Now you will take that application and move it toward a production-oriented distributed system.

The progression is:

```text
project 2
Build the Application
        ↓
Java + Spring Boot
REST API
PostgreSQL
Security
Business Logic
        ↓
project 3
Make It Concurrent
        ↓
Threads
Concurrency
Race Conditions
Thread Safety
Async Processing
        ↓
project 4
Productionize It
        ↓
Microservices
Docker
Kafka
CI/CD
AWS
        ↓
Production-Oriented Application
```

By the end of this project, you should be able to answer:

> **How do I take a Spring Boot application that works locally and turn it into a distributed, containerized, automated, and cloud-deployed system?**

Your final application should demonstrate not only that the application **works**, but that you understand how to **package, communicate, deploy, scale, automate, and operate** it using modern DevOps and distributed-system practices.
