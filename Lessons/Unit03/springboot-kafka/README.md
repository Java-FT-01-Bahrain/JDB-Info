# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Integrating Kafka in a Spring Boot Application

|                     Title                      |  Type   | Duration |       Creator        |
|:----------------------------------------------:|:-------:|:--------:|:--------------------:|
| Integrating Kafka in a Spring Boot Application | Lecture |   3:30   | Suresh Melvin Sigera |

### Lesson Objectives

- Understand the key dependencies and frameworks used in a Spring Boot Kafka application.
- Create and configure the `KafkaConsumerConfig` class for managing Kafka consumer settings.
- Develop the `KafkaConsumer` class to handle messages related to books and authors from Kafka topics.
- Implement the `KafkaProducer` class to produce and send messages to Kafka topics.
- Integrate Kafka with REST APIs for managing book data, including creating and updating book entries.
- Verify the status of Kafka containers and observe logs for successful Kafka operations within a Spring Boot
  application.
- Use Kafka console consumer tool to manually check messages and validate the data flow in the system.

### Overview of Project Dependencies: Key Libraries and Frameworks (30 minutes)

Let's dive into the provided Spring Boot project that comes with this lesson. The project is pre-configured with the
necessary dependencies and configurations to build a Kafka application. Here are the key dependencies included in the
project:

- **Spring for Apache Kafka**: This dependency provides the necessary components and abstractions for working with Kafka
  in a Spring environment. It simplifies the configuration and usage of Kafka producers, consumers, and streams.
- **Spring Boot Starter Web**: We'll include this dependency to build web-based applications with Spring Boot. It
  provides a convenient way to create RESTful APIs and handle HTTP requests and responses.
- **Lombok**: Lombok is a Java library that helps reduce boilerplate code by automatically generating getters, setters,
  constructors, and other common methods. It makes our code more concise and readable.
- **JPA (Jakarta Persistence API)**: JPA is a Java specification for managing relational data in applications. It
  provides an object-oriented approach to interact with databases, allowing us to map Java objects to database tables.
- **H2**: H2 is an in-memory database that we'll use for development and testing purposes. It provides a lightweight and
  fast database solution without the need for a separate database installation.

You will notice that it already includes the required dependencies for Kafka, Spring Boot, Lombok, JPA, and H2. Here's
an example of how the dependencies section look like:

```xml

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.kafka</groupId>
        <artifactId>spring-kafka</artifactId>
    </dependency>
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.kafka</groupId>
        <artifactId>spring-kafka-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

The provided Spring Boot project contains two configuration files: `application.properties`
and `application-dev.properties`. Let's explore the purpose and contents of each file.

**application.properties**:

```text
spring.profiles.active=dev
```

This file is the main configuration file for the Spring Boot application. It specifies the active profile using
the `spring.profiles.active` property. In this case, the active profile is set to "dev", which means the application
will use the configuration defined in the `application-dev.properties` file.

**application-dev.properties**:

```text
spring.application.name=kafkademo
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password

spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=none

spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

kafka.bootstrap-servers=localhost:9092
```

This file contains the configuration properties specific to the "dev" profile. Here's a breakdown of the properties:

- **spring.application.name**: Sets the name of the Spring Boot application to `kafkademo`.
- **spring.datasource.url**: Specifies the URL for the `H2` in-memory database. In this case, it is set
  to `jdbc:h2:mem:testdb`, which creates an in-memory database named `testdb`.
- **spring.datasource.driverClassName**: Specifies the driver class name for the `H2` database.
- **spring.datasource.username and spring.datasource.password**: Sets the username and password for accessing the `H2`
  database.
- **spring.jpa.database-platform**: Specifies the Hibernate dialect for the `H2` database, which
  is `org.hibernate.dialect.H2Dialect`.
- **spring.jpa.hibernate.ddl-auto**: Configures the DDL (Data Definition Language) auto-generation strategy. In this
  case, it is set to "none", meaning no DDL statements will be generated automatically.
- **spring.h2.console.enabled**: Enables the `H2` console, which is a web-based database management tool.
- **spring.h2.console.path**: Sets the path for accessing the `H2` console, which is `/h2-console`.
- **kafka.bootstrap-servers**: Specifies the address of the Kafka broker to which the client should connect, which in
  this case is `localhost:9092`.

By having separate configuration files for different profiles, you can easily switch between different environments (
e.g., development, production) by setting the active profile accordingly.

### Navigating the Project Structure: A Closer Look at the Core Components (10 minutes)

Here’s a high-level overview of the initial project structure:

```text
└── src
    ├── main
    │   ├── java
    │   │   └── org
    │   │       └── example
    │   │           └── kafkademo
    │   │               ├── KafkademoApplication.java
    │   │               ├── model
    │   │               │   ├── Author.java
    │   │               │   └── Book.java
    │   │               └── repository
    │   │                   ├── AuthorRepository.java
    │   │                   └── BookRepository.java
```

The project is organized into packages that segregate the application's functionality:

- **model**: Contains the entity classes, `Author` and `Book`, which map to database tables.
    - **Author**: Represents the author entity with properties id (primary key), name, and biography. It uses
      annotations to denote it as an entity and to automatically handle getter, setter, and string representation
      methods through Lombok.
    - **Book**: Represents the book entity with properties id (primary key), title, description, and a relationship to
      `Author`. The `@ManyToOne` annotation indicates that each book is associated with one author, establishing a
      foreign key in the `Book` table linked to the Author’s primary key.
- **repository**: Contains interfaces that extend `JpaRepository`, allowing basic CRUD operations on the entities
  without the need for explicit SQL.
    - **AuthorRepository and BookRepository**: These interfaces are part of the persistence layer, extending
      `JpaRepository`, which provides JPA related methods such as saving, deleting, and finding entities. These
      repositories link the database operations directly to the Author and Book models, abstracting the need for manual
      database interaction.

### Demo: Understanding the `KafkaConsumerConfig` Class (20 minutes)

Apache Kafka is an incredibly powerful distributed event streaming platform, designed to handle trillions of events each
day. When integrating Kafka into Java and Spring Boot applications, setting up configuration classes is crucial. These
classes facilitate communication with Kafka brokers, enabling efficient production and consumption of messages.

The `KafkaConsumerConfig` class plays a vital role in abstracting and encapsulating all Kafka-related configuration
settings.

This design makes it easier to manage and modify settings without impacting other parts of the application. It
streamlines the connection to Kafka and the message-sending process by providing pre-configured beans that can be easily
injected and utilized in various application components.

Now, let's develop the `KafkaConsumerConfig` class in the `org.example.kafkademo.config` package, a key component for
configuring the Kafka producer within a Spring Boot application. Each part of this class has a specific role in the
configuration process:

```java
package org.example.kafkademo.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "book-group");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return props;
    }

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
        return factory;
    }
}
```

In this Java code, we define a Kafka consumer configuration class named `KafkaConsumerConfig` in
the `org.example.kafkademo.config package`. The purpose of this class is to set up and configure the necessary
components for consuming messages from Kafka topics using the Spring Kafka framework. The class is annotated
with `@Configuration`, indicating that it is a Spring configuration class. It includes several methods annotated
with `@Bean`, which means they will be managed by the Spring container and can be injected into other beans.
The `consumerConfigs()` method defines the Kafka consumer configuration properties, such as the bootstrap server URLs,
key and value deserializers, consumer group ID, and auto offset reset configuration.

The `consumerFactory()` method creates a `ConsumerFactory` instance using the consumer configuration properties.
Finally, the `kafkaListenerContainerFactory()` method sets up a `ConcurrentKafkaListenerContainerFactory` to handle the
consumption of messages from Kafka topics.

This factory is configured with the `ConsumerFactory` and sets the acknowledgment mode to `MANUAL_IMMEDIATE`, indicating
that the consumer will manually acknowledge the messages immediately after processing.

Overall, this configuration class provides the necessary setup for consuming messages from Kafka topics in our
Spring-based application.

### Demo: Understanding the `KafkaConsumer` Class (20 minutes)

Now, let's develop the `KafkaConsumer` class within the `org.example.kafkademo.consumer` package. This class is
essential for consuming messages from a Kafka topic in a Spring Boot application and is instrumental in processing these
messages. Utilizing the `@KafkaListener` annotation, it efficiently listens to and responds to messages from a
designated topic. This setup not only simplifies message consumption but also lays a robust foundation for adding more
complex processing capabilities or data persistence features, demonstrating a practical implementation of event-driven
architecture within a Spring Boot application. The focus is on real-time message handling and effective consumer
management.

```java
package org.example.kafkademo.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.kafkademo.model.Author;
import org.example.kafkademo.model.Book;
import org.example.kafkademo.repository.AuthorRepository;
import org.example.kafkademo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class KafkaConsumer {
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public KafkaConsumer(BookRepository bookRepository, AuthorRepository authorRepository, ObjectMapper objectMapper) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.objectMapper = objectMapper;
        // Log that the KafkaConsumer class has been loaded
        logger.info("KafkaConsumer class loaded and KafkaConsumer bean created.");
    }

    @KafkaListener(topics = {"new-book-topic", "update-book-topic"}, groupId = "book-group")
    public void consume(String message) {
        try {
            Book book = objectMapper.readValue(message, Book.class);
            processBook(book);
        } catch (Exception e) {
            logger.error("Error processing message: {}", message, e);
        }
    }

    private void processBook(Book book) {
        Author author = book.getAuthor();

        // Check if the author exists, save if not
        Author existingAuthor = authorRepository.findByNameAndBiography(author.getName(), author.getBiography())
                .orElseGet(() -> authorRepository.save(author));

        book.setAuthor(existingAuthor);

        // Check if the book already exists based on a unique attribute, such as title and author
        Book existingBook = bookRepository.findByTitleAndAuthor(book.getTitle(), existingAuthor)
                .orElse(null);

        if (existingBook == null) {
            Book savedBook = bookRepository.save(book);
            logger.info("Processed and saved new book: {}", savedBook);
        } else {
            logger.info("Book already exists and was not saved: {}", existingBook);
        }
    }
}
```

In this Java code, we define a Kafka consumer within a Spring Boot application, specifically designed to handle messages
related to books and their authors. We organize the code within the `org.example.kafkademo.consumer` package, which
suggests that it's part of the consumer module for the `kafkademo` application. We decorate the class with
the `@Service` annotation, indicating that it's a business service managed by the Spring framework.

A static logger from SLF4J is employed for logging information and errors, which aids in debugging and monitoring the
application's operations. Three key dependencies are autowired through the
constructor: `BookRepository`, `AuthorRepository`, and `ObjectMapper`. `BookRepository` and `AuthorRepository` are
utilized for data access operations on `Book` and `Author` entities, respectively, while `ObjectMapper` manages the
conversion of JSON data into `Book` objects. Upon initialization, the constructor logs the creation of
the `KafkaConsumer` bean.

The core functionality is defined in the `consume` method, annotated with `@KafkaListener` to listen to the Kafka topics
`new-book-topic` and `update-book-topic` under the group ID `book-group`. Triggered by messages published to these
topics, the method attempts to deserialize the received JSON message into a `Book` object and processes it. Errors
encountered during this operation are logged, assisting in troubleshooting message consumption issues.

The `processBook` method encapsulates the logic for processing each book. It retrieves or saves the associated `Author`
of the book depending on their existence in the database. For books, the method checks existing records based on the
title and author; if the book isn’t already in the database, it is saved. If it already exists, a log entry is created
to note that the book was not saved due to duplication. This approach ensures that each book and author is uniquely
stored, maintaining data integrity within the system's database.

Through this setup, we efficiently manage book and author data triggered by Kafka messages, ensuring no duplications and
consistent data storage.

### Demo: Understanding the `KafkaProducer` Class (20 minutes)

Let's develop the `KafkaProducer` class within the `org.example.kafkademo.producer` package. This class plays a crucial
role in producing and sending messages to Kafka topics in a Spring Boot application. By leveraging the `KafkaTemplate`,
the `KafkaProducer` class efficiently handles the sending of messages to the designated topics, which in this case
are `NEW_BOOK_TOPIC` and `UPDATE_BOOK_TOPIC`.

```java
package org.example.kafkademo.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.kafkademo.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
    private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);
    private static final String NEW_BOOK_TOPIC = "new-book-topic";  // Renamed to match consumer topic
    private static final String UPDATE_BOOK_TOPIC = "update-book-topic";  // Use constant for clarity

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    private ObjectMapper objectMapper = new ObjectMapper();

    public KafkaProducer() {
        // Log that the KafkaProducer class has been loaded
        logger.info("KafkaProducer class loaded and KafkaProducer bean created.");
    }

    public void sendNewBookDetails(Book book) {
        try {
            String message = objectMapper.writeValueAsString(book);
            kafkaTemplate.send(NEW_BOOK_TOPIC, message);
            logger.info("New book details sent: {}", message);
        } catch (JsonProcessingException e) {
            logger.error("Failed to serialize book details: {}", book, e);
        }
    }

    public void sendUpdatedBookDetails(Book book) {
        try {
            String message = objectMapper.writeValueAsString(book);
            kafkaTemplate.send(UPDATE_BOOK_TOPIC, message);
            logger.info("Updated book details sent: {}", message);
        } catch (JsonProcessingException e) {
            logger.error("Failed to serialize book details: {}", book, e);
        }
    }
}
```

We annotate it with `@Service`, indicating that it is a Spring service component. To help in monitoring and debugging
the application's operations, we utilize a static logger from SLF4J for logging information and errors. We also define
two constants, `NEW_BOOK_TOPIC` and `UPDATE_BOOK_TOPIC`, representing the names of the Kafka topics to which the
producer sends messages.

In the `KafkaProducer` class, we have two dependencies: `KafkaTemplate` and `ObjectMapper`. We autowire
the `KafkaTemplate` using the `@Autowired` annotation, allowing the producer to send messages to Kafka topics. We use
the `ObjectMapper` to serialize `Book` objects into JSON strings before sending them as messages.

In the constructor of the `KafkaProducer` class, we log a message when the bean is created, indicating that the class
has been loaded and the bean has been instantiated.

We provide two public methods for sending book-related messages:

1. `sendNewBookDetails(Book book)`: This method receives a `Book` object as a parameter and sends its details as a
   message to the `NEW_BOOK_TOPIC` Kafka topic. We use the `ObjectMapper` to serialize the `Book` object into a JSON
   string. If the serialization is successful, we send the message using the `KafkaTemplate`, and we record a log
   message. If the serialization fails, we log an error along with the `Book` object and the exception details.
2. `sendUpdatedBookDetails(Book book)`: This method is similar to `sendNewBookDetails`, but we send the updated book
   details as a message to the `UPDATE_BOOK_TOPIC` Kafka topic. The serialization and logging process is the same as
   described above.

By using this `KafkaProducer` class, we enable other components of the application to easily send book-related messages
to the appropriate Kafka topics. The class takes care of serializing the `Book` objects into JSON strings and handles
the sending of messages using the `KafkaTemplate`. We also provide logging to track the success or failure of message
sending operations.

Overall, we designed this class to act as a convenient and centralized way to produce book-related messages and send
them to the designated Kafka topics within the application.

### Integrating Kafka with REST APIs for Book Management (5 minutes)

In modern microservice architectures, messaging systems like Apache Kafka play a crucial role in decoupling service
components, enabling asynchronous data processing and enhancing system resilience and scalability. This scenario
illustrates the use of Kafka within a Spring Boot application to manage book-related data, leveraging REST APIs for
different operations. Specifically, one API is dedicated to publishing new book entries to a Kafka topic, while another
API handles updates to existing books. This dual-API approach ensures a clear separation of concerns and demonstrates a
more sophisticated use of Kafka in handling distinct data workflows.

### Demo: New Book Creation Controller: Managing Book Entries via REST API (30 minutes)

To create the API endpoint for capturing new book details and publishing them to a Kafka topic, follow these steps:
First, create the package `com.example.bookservice.controller` in your Java project. You can create this package
structure by creating the necessary directories within your project's source folder.
After creating the package, create a new Java class file named `NewBookController` within
the `com.example.bookservice.controller` package. This class will serve as the REST API controller to accept new book
details from client requests and publish them to a Kafka topic.

```java
package org.example.kafkademo.controller;

import org.example.kafkademo.model.Book;
import org.example.kafkademo.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/new-books")
public class NewBookController {

    private KafkaProducer kafkaProducer;

    @Autowired
    private void setKafkaProducer(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping
    public void addNewBook(@RequestBody Book book) {
        kafkaProducer.sendNewBookDetails(book);
    }
}
```

The `@Autowired` annotation is used to inject an instance of the `KafkaProducer` class into the `NewBookController`, and
the `addNewBook` method accepts a `Book` object in the request body and invokes the `sendNewBookDetails` method of the
injected `KafkaProducer` instance to publish the book details to a Kafka topic.

### Demo: Controller for Updating Books (15 minutes)

Let's proceed with creating the `UpdateBookController` class within the existing `org.example.kafkademo.controller`
package.

```java
package org.example.kafkademo.controller;

import org.example.kafkademo.model.Book;
import org.example.kafkademo.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/update-books")
public class UpdateBookController {

    private KafkaProducer kafkaProducer;

    @Autowired
    private void setKafkaProducer(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PutMapping
    public void updateBook(@RequestBody Book book) {
        kafkaProducer.sendUpdatedBookDetails(book);
    }
}
```

### Running The Application (10 minutes)

Before launching the application, let's ensure that the Kafka Docker containers are operational.

To do this, run the `docker-compose.yml` file located in the project directory using the following command:

```text
sudo docker compose up -d
```

After starting the containers, verify their status by executing the command `sudo docker ps`.

```text
```text
<container-id>   bitnami/kafka:latest       "/opt/bitnami/script…"   17 minutes ago   Up 17 minutes   0.0.0.0:9092->9092/tcp, :::9092->9092/tcp                                 springboot-kafaka-kafka-1
<container-id>   bitnami/zookeeper:latest   "/opt/bitnami/script…"   17 minutes ago   Up 17 minutes   2888/tcp, 3888/tcp, 0.0.0.0:2181->2181/tcp, :::2181->2181/tcp, 8080/tcp   springboot-kafaka-zookeeper-1
```

When your Spring Boot application is running correctly with Apache Kafka, you should observe output similar to the
following in your logs:

```text
2024-06-11T13:42:00.113-04:00  INFO 16178 --- [kafkademo] [ntainer#0-0-C-1] o.a.k.c.c.internals.SubscriptionState    : [Consumer clientId=consumer-book-group-1, groupId=book-group] Resetting offset for partition update-book-topic-0 to position FetchPosition{offset=0, offsetEpoch=Optional.empty, currentLeader=LeaderAndEpoch{leader=Optional[127.0.0.1:9092 (id: 1 rack: null)], epoch=0}}.
2024-06-11T13:42:00.114-04:00  INFO 16178 --- [kafkademo] [ntainer#0-0-C-1] o.a.k.c.c.internals.SubscriptionState    : [Consumer clientId=consumer-book-group-1, groupId=book-group] Resetting offset for partition new-book-topic-0 to position FetchPosition{offset=0, offsetEpoch=Optional.empty, currentLeader=LeaderAndEpoch{leader=Optional[127.0.0.1:9092 (id: 1 rack: null)], epoch=0}}.
2024-06-11T13:42:00.115-04:00  INFO 16178 --- [kafkademo] [ntainer#0-0-C-1] o.s.k.l.KafkaMessageListenerContainer    : book-group: partitions assigned: [new-book-topic-0, update-book-topic-0]
```

This log output indicates that the Kafka producers and consumers are functioning correctly within your Spring Boot
application, connecting to the Kafka cluster, joining groups, and successfully managing partitions and offsets. These
logs are crucial for monitoring the operational status and performance of Kafka within your application.

### Create A New Book (10 minutes)

Let's add a new book using the following `curl` command:

```shell
curl -X POST http://localhost:8080/api/new-books \
-H "Content-Type: application/json" \
-d '{
  "title": "Java",
  "description": "A First Book of Java blends Gary Bronson'\''s successful experience teaching introductory programming courses with key elements students need to know when programming in Java.",
  "author": {
    "name": "Gary J. Bronson",
    "biography": "Author biography detailing background, interests, and other relevant information."
  }
}'
```

In this command:

- `-X POST` specifies that the request is a POST request.
- `-H "Content-Type: application/json"` sets the content type to JSON.
- `-d` provides the JSON data payload, which is enclosed in single quotes (`'`). Inside the JSON payload, any single
  quotes in the text are escaped with a backslash (`\'`).

### Update A New Book (10 minutes)

To update a book object in the database using the provided JSON data, you can use the following curl command in the
terminal:

```shell
curl -X PUT http://localhost:8080/api/update-books \
-H "Content-Type: application/json" \
-d '{
  "title": "Effective Java",
  "description": "A First Book of Java blends Gary Bronson'\''s successful experience teaching introductory programming courses with key elements students need to know when programming in Java.",
  "author": {
    "name": "Gary J. Bronson",
    "biography": "Author biography detailing background, interests, and other relevant information."
  }
}'
```

However, to further verify the functionality and ensure that your Kafka setup is properly processing messages, you can
directly consume messages from the Kafka topic using the Kafka console consumer tool. This can be done by executing the
following command in your terminal, which connects to your Kafka container and listens to messages on the specified
topic:

```text
sudo docker exec -it <your_container_id> kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic new-book-topic --from-beginning
```

Replace `<your_container_id>` with the actual container `ID` of your Kafka Docker container. This command will start the
Kafka console consumer that subscribes to the `book-topic`, and by using the `--from-beginning` flag, it will read all
messages on this topic from the beginning. This is an effective way to manually check the messages being produced and
consumed in your system, providing a straightforward method to debug and validate the flow of data through Kafka.

### Summary (5 minutes)

In this lesson, we explored the integration of Apache Kafka with a Spring Boot application to manage book-related data.
We began by setting up a Spring Boot project with essential dependencies and configurations for Kafka, Spring Boot,
Lombok, JPA, and H2. We navigated through the project structure to understand the core components, including models and
repositories.

We then focused on configuring the Kafka consumer and producer, highlighting the roles of
the `KafkaConsumerConfig`, `KafkaConsumer`, and `KafkaProducer` classes. The `KafkaConsumer` class was developed to
consume messages from Kafka topics and process book data, while the `KafkaProducer` class was responsible for producing
and sending messages to Kafka topics. Additionally, we integrated REST APIs for creating and updating book entries,
demonstrating a practical use case of Kafka in managing book data.

Finally, we ensured that the Kafka Docker containers were operational, verified their status, and observed the logs for
successful Kafka operations within the Spring Boot application. We also discussed how to use the Kafka console consumer
tool to manually check messages and validate the data flow in the system.

### Key Takeaways (5 minutes)

- **Kafka Consumer Configuration**: Created and configured the `KafkaConsumerConfig` class for managing Kafka consumer
  settings.
- **Kafka Consumer Implementation**: Developed the `KafkaConsumer` class to handle messages from Kafka topics and
  process book data.
- **Kafka Producer Implementation**: Implemented the `KafkaProducer` class to produce and send messages to Kafka topics.
- **Log Verification**: Observed logs for successful Kafka operations within the Spring Boot application.
- **Manual Message Check**: Used the Kafka console consumer tool to manually check messages and validate the data flow
  in the system.
