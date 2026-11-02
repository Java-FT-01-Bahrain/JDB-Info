# Full-Stack Hackathon
## One-Day Spring Boot Microservices Project Challenge

## 1. Team Setup

- Work in **groups of 4 students**.
- Choose one unique project idea as a team.
- Your project must be different from the original **Unit Projects** you are currently developing.
- You may choose one of the suggested ideas below or propose your own idea.
- Focus on building a working MVP within the allocated time.

## 2. Project Ideas

Choose one of the following ideas or come up with your own unique concept:

1. **Smart Restaurant Queue System** – Customers join a queue and receive live updates.
3. **Book Exchange Platform** – Users list, request, and exchange books.
4. **Smart Parking System** – Manage parking spaces and reservations.
5. **Community Challenge Platform** – Users create and participate in challenges.
6. **Gaming Tournament Manager** – Create tournaments, register players, and manage matches.
7. **Flash Sale Platform** – Manage limited-time sales and product inventory.
8. **Travel Buddy Matcher** – Connect users with similar travel plans.
9. **Recipe Sharing Platform** – Users create, save, and rate recipes.
10. **Pet Adoption Management System** – Manage pets, applications, and adoption requests.
11. **Student Study Group Finder** – Create and join study groups.
12. **Local Delivery Platform** – Create and manage delivery requests.
13. **Equipment Rental Platform** – Users rent and return equipment.
14. **Movie Seat Reservation System** – Browse movies and reserve available seats.
15. **Freelancer Marketplace** – Connect clients with freelancers and manage projects.

**You are encouraged to come up with your own interesting and unique idea!**

## 3. Architecture Requirement: Microservices

Your application **must use Microservices Architecture**.

### Monolithic vs. Microservices

- **Monolithic Architecture:** The entire application is developed and deployed as one large application. All modules are part of the same codebase and deployment unit.
- **Microservices Architecture:** The application is divided into smaller, independent services. Each service handles a specific business responsibility and can be developed and deployed independently.

For this challenge, you must use Microservices Architecture.

Example services:

- Authentication/User Service
- Main Business Service
- Booking/Order Service
- Notification Service

You do not need to implement all these services. Choose services that make sense for your project.

## 4. Backend Requirements

- Use **Java and Spring Boot**.
- Implement Microservices Architecture.
- Follow layered architecture:
  - Controller
  - Service
  - Repository
  - Model (atleast 5)
- Create RESTful APIs.
- Implement proper exception handling.
- Use appropriate HTTP status codes.
- Implement request validation.
- Ensure each microservice has clearly defined responsibilities.

## 5. Authentication and Authorization

- Implement JWT Authentication.
- Users must be able to:
  - Register.
  - Login.
  - Receive a JWT token.
- Protect appropriate endpoints using JWT.
- Implement role-based authorization where appropriate (e.g., USER and ADMIN).
- Ensure unauthorized users cannot access protected endpoints.

## 6. Database Requirements

- Use **MongoDB** as your primary database.
- Use a local or remote MongoDB database through **MongoDB Atlas**.
- Create appropriate MongoDB collections.
- Implement CRUD operations.
- Design appropriate relationships and references between collections.
- Use environment variables for database credentials.
- Do not commit database credentials to GitHub.

## 7. Frontend Requirements

You can use any frontend library or framework, including:

- React
- Angular
- Vue
- Any other approved frontend technology.

Requirements:

- Build a functional user interface.
- Integrate the frontend with your Spring Boot APIs.
- Implement login and registration screens.
- Display relevant data from MongoDB through your APIs.
- Implement basic form validation and error handling.

## 8. Concurrency and Multithreading

Your project must demonstrate concepts covered in class related to **Concurrency, Multithreading, and Locking**.

Requirements:

- Include at least one realistic scenario involving multiple users accessing the same resource simultaneously.
- Implement a multithreaded operation or concurrent processing scenario.
- Demonstrate how shared resources are protected.
- Implement at least one locking mechanism.
- Prevent race conditions and inconsistent data updates.

Example scenarios:

- Multiple users trying to book the same event ticket.
- Multiple users attempting to reserve the same parking space.
- Multiple users purchasing the last available product.
- Multiple users trying to reserve the same movie seat.
- Multiple users joining an event with limited capacity.

Use appropriate Java concurrency mechanisms, such as:

- Threads and Runnable.
- ExecutorService.
- ReentrantLock.

Your implementation should demonstrate why locking is necessary and how it protects shared resources.

## 9. Docker and Deployment

- Create Dockerfile(s) for your application.
- Containerize your Spring Boot microservices.
- Use Docker Compose to manage multiple services.
- Configure the services to communicate with each other.
- Ensure the application can run using Docker.
- Deploy your application if time permits.

## 10. GitHub and Team Collaboration

Every group must create a shared GitHub repository.

Requirements:

- Create a shared GitHub repository.
- Use meaningful feature branches.
- Do not allow everyone to work directly on the main branch.
- Create separate branches for different features.
- Make regular and meaningful commits.
- Use descriptive commit messages.
- Create Pull Requests.
- Review and merge changes into the main branch.
- Resolve merge conflicts where necessary.
- Ensure every team member contributes to the repository.

Example branches:

- `main`
- `saad/feature/authentication`
- `saad/feature/frontend`
- `ali/feature/bookings`
- `saad/feature/concurrency`
- `ali/feature/docker`

## 11. Documentation Requirements

Your GitHub repository must contain a README.md file with:

- Project name and description.
- Problem statement.
- Team members.
- Technologies used.
- Microservices architecture overview.
- Instructions for running the application.
- MongoDB Atlas configuration.
- API endpoint documentation.
- Environment variable setup.
- Docker setup and execution instructions.
- Screenshots of the application.

Additional documentation:

- ERD / Database Design Diagram.
- Microservices Architecture Diagram.
- API documentation.

## 12. Stretch Goals

If you complete the core requirements early, try implementing some of the following:

- API Gateway.
- Service Discovery.
- Inter-service communication using OpenFeign or WebClient.
- Refresh Tokens.
- Advanced Role-Based Access Control.
- Email Notifications.
- Real-time notifications using WebSockets.
- Redis caching.
- API Rate Limiting.
- Unit and Integration Testing.
- CI/CD using GitHub Actions.
- Centralized Logging.
- Spring Boot Actuator and Health Checks.
- Cloud Deployment.

## 13. Final Presentation and Demonstration

Each group must give a 5–10 minute demonstration covering:

- Project idea and problem being solved.
- Microservices architecture.
- JWT Authentication.
- MongoDB Atlas integration.
- Frontend functionality.
- Concurrency, multithreading, and locking implementation.
- Docker setup.
- GitHub branches, commits, and Pull Requests.
- Challenges faced and how they were solved.

## Final Goal

By the end of this challenge, your team should have a working full-stack application using:

- Java and Spring Boot.
- Microservices Architecture.
- JWT Authentication.
- MongoDB Atlas.
- A frontend framework of your choice.
- Concurrency, Multithreading, and Locking.
- Docker and Docker Compose.
- GitHub collaboration and version control.
- Proper project documentation.

**Important:** Focus on completing the core requirements first. Stretch goals are optional. Build something functional, demonstrate what you have learned, and make sure every team member understands the implementation.