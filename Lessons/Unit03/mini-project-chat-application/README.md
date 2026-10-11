# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Java Chat Application

| Title                 | Type         | Duration  | Creator       |
|-----------------------|--------------|-----------|---------------|
| Java Chat Application | Mini Project | 3:00 days | Suresh Sigera |

## Overview

This project requires you to develop a Java Chat Application that enables real-time communication between multiple
clients through a central server using sockets. This application will serve as an excellent tool to understand network
communication, threading, and real-time data exchange in Java.

## Objectives

- Learn to implement TCP/IP networking with Java sockets.
- Understand and apply Java threading for handling multiple client connections.
- Develop an understanding of real-time communication mechanisms.

## Requirements

Create a Spring Boot project that includes:

1. **Networking:**
    - Implement server and client communication using `java.net.Socket`.
    - The server should be capable of accepting connections from multiple clients simultaneously.

2. **Concurrency:**
    - Use threading to handle multiple clients.
    - Ensure that the server can manage simultaneous messages without dropping or mixing messages.

3. **Basic Functionality:**
    - Clients should be able to connect to the server, send messages to the server, and receive messages from the server
      broadcast to all clients.
    - The server should broadcast any message received from one client to all other connected clients in real-time.

4. **User Interface:**
    - Implement a simple text-based interface where users can type and view messages.
    - Provide clear prompts and responses so users know messages are sent or received.

5. **Special features:**
    - Allow clients to send private messages to each other.
    - Integrate a database to log chat history for future retrieval.
    - Unsend message
    - File sharing
    - Encryption
    - Search
    - Unread message count
    - Authentication
    - Unread messages should be marked with gray check
    - Edit message:  The sender can edit message unless it's read. If it's not read, the message can be edited within 10 minutes

6. **Version Control:**
    - The project should have at least 30 commits with consistent and clear messages

## Technical Specifications

- **Language:** Java
- **Networking Library:** java.net
- **Concurrency Library:** java.util.concurrent

## Deliverables

1. **Source Code:**
    - Complete source code.
    - Code should be well-commented to explain critical sections and choices.

2. **Documentation:**
    - Update this README.md to include any additional steps or important information about your implementation.
    - Include comments in your code to explain the functionality of methods and logic used.

3. **Demonstration:**
    - Prepare to demonstrate the working application during the project review.
    - Be ready to explain your code, particularly how you handle networking and concurrency.

## Evaluation Criteria

- Functionality: Does the application meet the functional requirements?
- Code Quality: Is the code well-organized, commented, and easy to understand?
- Stability: Does the application handle multiple clients without errors or crashes?
- Presentation: Is the README clear, and is the demonstration well-prepared?

## Challenges (Optional)

- Implement a GUI using JavaFX or Swing to enhance user interaction. 
- Add a feature to allow clients to post on an specific topic and let anyone join the topic
- Group chat
- For group chats, the users can have roles: Admin (they can add people to the group chat), Any User
- Dark and Light mode
- Checking Time Zone: In registration they should choose their Time Zone. If the message is sent after 8pm (according to the user's time zone), an alert will display asking if they are sure about sending the message
- Reactions to messages

## Conclusion

This project will help you solidify your understanding of sockets, threading, and real-time communications in Java. It
provides a practical scenario to apply theoretical concepts in a networked application environment.
