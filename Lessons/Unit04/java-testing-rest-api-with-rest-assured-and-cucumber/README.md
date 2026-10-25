# Java REST API Testing with REST Assured

**Total Estimated Time: 105 minutes (1 hour 45 minutes)**

#### Learning Objectives (5 minutes)

- Explain how Cucumber-Spring works and why BDD improves test maintainability.
- Create an entry point using @RunWith Cucumber and configure test runners.
- Add dependency injection to Cucumber tests using Spring Boot.
- Create step definition classes that map to Gherkin scenarios.
- Implement HTTP GET, POST, PUT, and DELETE requests using REST Assured.

#### Introduction (5 minutes)

Unit tests validate that individual methods work correctly. Integration tests verify that components interact properly. But neither tells you whether your application actually delivers the behavior users need. You can have perfect test coverage and still build the wrong thing. Behavior-Driven Development (BDD) addresses this by expressing requirements as executable specifications written in plain language that stakeholders can read.

Cucumber brings BDD to Java through Gherkin—a syntax that describes application behavior using Given-When-Then scenarios. Given establishes context, When triggers an action, Then validates the outcome. These scenarios serve dual purposes: they're documentation that stays current because tests fail when behavior changes, and they're executable tests that verify the system works as specified. REST Assured provides a fluent API for testing HTTP endpoints, making it natural to validate REST APIs within Cucumber scenarios.

This lesson builds a complete BDD testing framework for a REST API. You'll write feature files in Gherkin, generate step definitions that execute REST Assured requests, and integrate everything with Spring Boot's test context. The result is a test suite that reads like requirements documentation while providing the same rigor as traditional integration tests.

#### Why BDD Matters for API Testing (10 minutes)

Traditional integration tests suffer from readability problems. The code mixes setup, execution, and assertions in ways that obscure what's actually being tested. Mock objects appear without context. Assertions scatter throughout the test method. When you return to these tests months later—or when someone else needs to understand them—reconstructing the intended behavior requires archaeological detective work.

BDD imposes structure through Given-When-Then. Given sections set up preconditions—creating test data, configuring system state. When sections execute the action being tested—calling an API endpoint, submitting a form. Then sections verify outcomes—checking response codes, validating returned data. This structure makes tests self-documenting. You can read the feature file and understand exactly what the system should do without examining implementation code.

The mapping between requirements and tests becomes explicit. Product owners write scenarios describing desired behavior. Developers implement step definitions that execute those scenarios. When requirements change, the feature file changes, tests fail, and you know exactly what needs updating. There's no separate documentation to maintain because the executable specifications are the documentation.

#### Setting Up Dependencies (15 minutes)

Add these dependencies to your `pom.xml`:

```xml
<properties>
    <java.version>17</java.version>
    <cucumber.version>6.8.1</cucumber.version>
</properties>

<dependencies>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-rest</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <scope>runtime</scope>
    <optional>true</optional>
</dependency>
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jdbc</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<!-- JUnit 4 -->
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <scope>test</scope>
</dependency>

<!-- Cucumber -->
<dependency>
    <groupId>io.cucumber</groupId>
    <artifactId>cucumber-java</artifactId>
    <version>${cucumber.version}</version>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>io.cucumber</groupId>
    <artifactId>cucumber-junit</artifactId>
    <version>${cucumber.version}</version>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>io.cucumber</groupId>
    <artifactId>cucumber-spring</artifactId>
    <version>${cucumber.version}</version>
    <scope>test</scope>
</dependency>

<!-- REST Assured -->
<dependency>
    <groupId>io.rest-assured</groupId>
    <artifactId>rest-assured</artifactId>
    <version>4.3.0</version>
    <scope>test</scope>
</dependency>
</dependencies>

<build>
<plugins>
    <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
    </plugin>
    <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-surefire-plugin</artifactId>
        <version>2.19.1</version>
    </plugin>
</plugins>
</build>
```

Run `mvn clean install` to download all dependencies.

#### Project Structure and Feature Files (5 minutes)

Create the directory structure for BDD tests. Maven expects `src/test/resources` for test resources. Create a `features` directory inside it:

```
src/test/
├── java/
│   ├── config/
│   │   └── CucumberSpringConfiguration.java
│   ├── definitions/
│   │   └── BookAPIStepDefinitions.java
│   └── runners/
│       └── TestRunner.java
└── resources/
    └── features/
        └── BookAPI.feature
```

Create `BookAPI.feature` inside the features directory:

```gherkin
Feature: REST API functionalities

  Scenario: User able to manage books
    Given A list of books are available
    When I add a book to my reading list
    Then The book is added
    When I edit book from my reading list
    Then The book content is edited
    When I remove book from my reading list
    Then The book is removed
```

This Gherkin describes a complete CRUD workflow. Each line maps to a step definition method that executes actual REST API calls.

#### Creating Spring Configuration (10 minutes)

Create a separate Spring configuration class to manage the test context. Create package `config` under `src/test/java`:

```java
package config;

import com.example.bookapi.BookApiApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = BookApiApplication.class
)
public class CucumberSpringConfiguration {
}
```

The `@CucumberContextConfiguration` annotation marks this as Cucumber's Spring context configuration. The `@SpringBootTest` with `RANDOM_PORT` starts an embedded server on a random port to prevent conflicts. The `classes` parameter specifies your main Spring Boot application class.

#### Creating the Test Runner (10 minutes)

The test runner serves as the entry point for Cucumber tests. Create package `runners` under `src/test/java`:

```java
package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {
        "definitions",  // step definitions package
        "config"        // cucumber spring config package
    },
    plugin = {
        "pretty",
        "summary"
    }
)
public class TestRunner {
}
```

The `@RunWith(Cucumber.class)` annotation tells JUnit to use Cucumber's test runner. The `@CucumberOptions` specifies where feature files live (`features`) and where to find both step definitions and configuration (`glue`). The `pretty` plugin formats console output readably. The `summary` plugin provides a concise test summary.

#### Implementing Step Definitions with REST Assured (25 minutes)

Step definitions connect Gherkin steps to executable code. Create package `definitions` under `src/test/java`:

```java
package definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.List;

public class BookAPIStepDefinitions {

    private static final Logger logger = LoggerFactory.getLogger(BookAPIStepDefinitions.class);
    private static final String BASE_URL = "http://localhost:";

    @LocalServerPort
    private String port;

    private Response response;
    private int createdBookId;

    // HTTP GET - Retrieve list of books
    @Given("A list of books are available")
    public void getBooksAvailable() {
        logger.info("Testing GET /api/books/");

        RestAssured.baseURI = BASE_URL + port;

        response = RestAssured.given()
                .header("Content-Type", "application/json")
                .when()
                .get("/api/books/");

        Assert.assertEquals(200, response.getStatusCode());

        // Verify the response structure matches your API
        List<Object> books = response.jsonPath().getList("data");
        Assert.assertNotNull(books);
        Assert.assertTrue(books.size() > 0);

        logger.info("GET request successful - Found {} books", books.size());
    }

    // HTTP POST - Create a new book
    @When("I add a book to my reading list")
    public void postNewBook() throws JSONException {
        logger.info("Testing POST /api/books/");

        JSONObject book = new JSONObject();
        book.put("name", "Clean Code");
        book.put("description", "A handbook of agile software craftsmanship");

        response = RestAssured.given()
                .baseUri(BASE_URL + port)
                .header("Content-Type", "application/json")
                .body(book.toString())
                .when()
                .post("/api/books/");

        logger.info("POST request sent - Status: {}", response.getStatusCode());
    }

    @Then("The book is added")
    public void verifyBookAdded() {
        Assert.assertEquals(201, response.getStatusCode());

        // Extract from the "data" object in your API response
        createdBookId = response.jsonPath().getInt("data.id");
        String bookName = response.jsonPath().getString("data.name");
        String message = response.jsonPath().getString("message");

        Assert.assertNotNull(createdBookId);
        Assert.assertEquals("Clean Code", bookName);
        Assert.assertEquals("success", message);

        logger.info("Book created successfully with ID: {}", createdBookId);
    }

    // HTTP PUT - Update existing book
    @When("I edit book from my reading list")
    public void putUpdateBook() throws JSONException {
        logger.info("Testing PUT /api/books/{}", createdBookId);

        JSONObject updatedBook = new JSONObject();
        updatedBook.put("name", "Clean Code: Updated Edition");
        updatedBook.put("description", "Updated handbook of agile software craftsmanship");

        response = RestAssured.given()
                .baseUri(BASE_URL + port)
                .header("Content-Type", "application/json")
                .body(updatedBook.toString())
                .when()
                .put("/api/books/" + createdBookId);

        logger.info("PUT request sent - Status: {}", response.getStatusCode());
    }

    @Then("The book content is edited")
    public void verifyBookUpdated() {
        Assert.assertEquals(200, response.getStatusCode());

        // Extract from the "data" object
        String updatedName = response.jsonPath().getString("data.name");
        String message = response.jsonPath().getString("message");

        Assert.assertTrue(updatedName.contains("Updated Edition"));
        Assert.assertTrue(message.contains("successfully updated"));

        logger.info("Book updated successfully");
    }

    // HTTP DELETE - Remove book
    @When("I remove book from my reading list")
    public void deleteBook() {
        logger.info("Testing DELETE /api/books/{}", createdBookId);

        response = RestAssured.given()
                .baseUri(BASE_URL + port)
                .header("Content-Type", "application/json")
                .when()
                .delete("/api/books/" + createdBookId);

        logger.info("DELETE request sent - Status: {}", response.getStatusCode());
    }

    @Then("The book is removed")
    public void verifyBookDeleted() {
        Assert.assertEquals(200, response.getStatusCode());

        String message = response.jsonPath().getString("message");
        Assert.assertTrue(message.contains("successfully deleted"));

        logger.info("Book deleted successfully");

        // Verify book no longer exists
        Response verifyResponse = RestAssured.given()
                .baseUri(BASE_URL + port)
                .header("Content-Type", "application/json")
                .when()
                .get("/api/books/" + createdBookId);

        Assert.assertEquals(404, verifyResponse.getStatusCode());
        logger.info("Verified book no longer exists");
    }
}
```

The Spring configuration is now separated from the step definitions. The `@LocalServerPort` annotation injects the random port so requests target the correct endpoint.

Each step definition method matches a Gherkin step through its annotation and method name. REST Assured's fluent API makes HTTP requests readable—you configure the request by chaining method calls, then execute it with `post()`, `get()`, `put()`, or `delete()`. The response stores the result for validation in Then steps.

#### Running the Tests (5 minutes)

Execute tests with Maven:

```bash
mvn clean test
```

Cucumber prints each step as it executes:

```
Scenario: User able to manage books
  Given A list of books are available      ✓
  When I add a book to my reading list     ✓
  Then The book is added                   ✓
  When I edit book from my reading list    ✓
  Then The book content is edited          ✓
  When I remove book from my reading list  ✓
  Then The book is removed                 ✓

1 Scenarios (1 passed)
7 Steps (7 passed)
```

#### Understanding REST Assured's Fluent API (15 minutes)

REST Assured uses a fluent, given-when-then style that mirrors BDD's structure. The `given()` method starts the request specification where you configure headers, parameters, and request body. The `when()` method specifies the HTTP method and endpoint. The response object provides access to status codes and body content.

Request configuration chains naturally:

```java
response = RestAssured.given()
                     .baseUri("http://localhost:8080")
                     .header("Content-Type", "application/json")
                     .header("Authorization", "Bearer " + token)
                     .queryParam("page", 1)
                     .queryParam("size", 20)
                     .body(requestBody)
                     .when()
                     .post("/api/books/");
```

Response validation uses JsonPath to extract values from JSON responses:

```java
int id = response.jsonPath().getInt("data.id");
String name = response.jsonPath().getString("data.book.name");
List<String> authors = response.jsonPath().getList("data.authors");
```

JsonPath expressions navigate nested JSON structures. Dot notation accesses nested fields. Array indices access list elements. This makes validation of complex responses straightforward without manual JSON parsing.

#### Summary (5 minutes)

BDD transforms integration testing from a technical exercise into executable documentation. Feature files written in Gherkin describe system behavior in terms stakeholders understand. Step definitions implement these specifications using REST Assured to exercise actual HTTP endpoints. The tests serve dual purposes—they verify the API works correctly and they document what the API should do.

Cucumber's integration with Spring Boot provides full access to the test context. The `@CucumberContextConfiguration` annotation loads Spring's dependency injection, making services and configuration available in step definitions. Separating the Spring configuration into its own class keeps step definitions clean and focused. Random port assignment through `WebEnvironment.RANDOM_PORT` prevents conflicts in continuous integration environments where multiple test suites run concurrently.

REST Assured's fluent API makes HTTP requests readable and maintainable. The given-when-then structure of its methods mirrors BDD's structure, creating natural alignment between test intent and implementation. JsonPath expressions extract values from responses without verbose JSON parsing code. Assertions validate both status codes and response content, ensuring the API returns correct data in the correct format.

The separation between feature files, configuration, and step definitions provides flexibility. Product owners can modify scenarios in feature files without touching implementation code. Developers can refactor step definitions without changing feature files. When requirements change, you update the Gherkin, tests fail, and you know exactly what needs implementation. The feature file remains accurate documentation because outdated documentation causes test failures.

This approach scales well. You can organize features by domain area, reuse step definitions across scenarios, and compose complex workflows from simple steps. The logging provides developers with diagnostic information when tests fail. The combination of BDD structure, REST Assured's expressiveness, and Spring Boot's test support creates a robust foundation for API testing.
