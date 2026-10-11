# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Product and Category Management Lab

|                               Title                               | Type | Duration |       Creator        |
|:-----------------------------------------------------------------:|:----:|:--------:|:--------------------:|
| Product and Category Management | Lab  |   3:00   | Suresh Melvin Sigera |

## Objective

In modern software systems, managing and processing data in real-time is critical, especially in applications with high
data throughput and dynamic updates. Apache Kafka, a distributed streaming platform, is widely used for such real-time
data processing needs. This lab focuses on integrating Kafka with a Spring Boot application to handle real-time data for
products and categories.

### Scenario

Imagine you are building an e-commerce application that needs to manage a large catalog of products and categories
efficiently. Your system needs to handle a variety of operations such as:

- Product Management: Adding new products, updating existing ones, and deleting products.
- Category Management: Adding new categories, updating categories, and associating products with categories.

In this scenario, real-time processing and seamless integration between various parts of the application are crucial.
For instance:

1. Product Updates: When a new product is added or an existing product is updated, the changes need to be propagated
   immediately to other parts of the system, such as inventory management, pricing services, and recommendation engines.
2. Category Changes: When categories are updated (e.g., a new category is created or an existing one is modified), these
   changes need to be reflected across the system to ensure that product categorization is consistent.

## Tasks

1. Setup Kafka Environment
    - Action: Launch Kafka and Zookeeper containers using Docker.
    - Verification: Ensure both services are running and accessible. Use Kafka command-line tools to verify that topics
      can be created and accessed.
2. Spring Boot Project Setup
    - Action: Create a new Spring Boot project or use an existing one with Kafka dependencies. Ensure that your project
      includes necessary dependencies for Kafka and JSON serialization.
    - Verification: Build the project to ensure all dependencies are correctly included. Verify that the project
      compiles without errors.
3. Create Models
    - Action: Define the Product and Category models in your Spring Boot application. These models will represent the
      data structure for products and categories.
    - Product Model: Include fields such as id, name, categoryId, and price.
    - Category Model: Include fields such as id and name.
    - Verification: Ensure that the models compile correctly and are ready for use in Kafka messaging.
4. Configure Kafka
    - Action: Implement the Kafka configuration necessary for both producing and consuming messages. This involves
      setting up Kafka producers and consumers, including configuring serializers and deserializers.
    - Description: You will need to create configuration classes to set up Kafka consumer and producer properties,
      including bootstrap servers, group IDs, and serialization settings. Ensure that Kafka is configured to handle the
      Product and Category models correctly.
5. Implement Kafka Consumer
    - Action: Develop a Kafka consumer that will listen for messages on Kafka topics and process them. The consumer
      should be able to handle messages related to products and categories.
    - Description: Create a service that uses the @KafkaListener annotation to consume messages from specific Kafka
      topics. Ensure the consumer processes the incoming messages and handles them appropriately.
6. Implement Kafka Producer
    - Action: Develop a Kafka producer that will send messages to Kafka topics. The producer should be able to send
      messages related to products and categories.
    - Description: Create a service that uses Kafka’s KafkaTemplate to produce messages and send them to the appropriate
      Kafka topics. Ensure the producer can handle the Product and Category models.
7. Integrate with REST API
    - Action: Develop REST API endpoints to interact with Kafka. Implement endpoints that will trigger Kafka producers
      to send messages when certain operations are performed.
    - Description: Create REST controllers that allow for CRUD operations on Product and Category data. Ensure that
      these operations result in Kafka messages being produced and sent to the appropriate topics.
8. Testing
    - Action: Perform end-to-end testing to ensure that the Kafka integration works as expected.
        - Send Product Data: Use Postman or curl to send POST requests with product data to the REST API endpoints you
          have created.
        - Verify Kafka Topics: Use Kafka console consumer tools to check messages in the Kafka topics.
        - Check Application Logs: Ensure that the Kafka consumer correctly receives and processes the messages. Review
          logs for successful message consumption and any errors.
    - Verification: Confirm that messages are correctly produced and consumed, and that the entire flow from sending
      data to consuming it is functional.
9. Troubleshooting
    - Action: Simulate common issues such as configuration errors or network problems. Resolve these issues and ensure
      the application can handle and recover from them.
    - Verification: Verify that the application remains functional and that issues are effectively resolved.
10. Deliverables
    - Source Code: Complete implementations for Kafka configuration, consumer, producer, and REST API endpoints.
    - Documentation: Brief documentation outlining the steps taken, configuration details, and any issues encountered
      along with their resolutions.
    - Screenshots: Evidence of successful message production and consumption, including Kafka console output and
      application logs.

This lab will help you develop a functional Spring Boot application with Kafka integration, focusing on managing product
and category data.
