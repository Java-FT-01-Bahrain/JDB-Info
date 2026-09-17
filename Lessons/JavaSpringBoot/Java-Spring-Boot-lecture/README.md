# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) RESTFUL JSON API with Java Spring Boot

| Title                                  | Type   | Duration | Author               |
|----------------------------------------|--------|----------|----------------------|
| RESTFUL JSON API with Java Spring Boot | Lesson | 25:00    | Suresh Melvin Sigera |


## DAY 01

### LEARNING OBJECTIVES

- Explain how Tomcat is use.
- Build and manage projects using Maven
- Setting Up a Spring Boot Project
- Creating an API in Spring Boot
- Integrate APIs into your project using Postman
- Explain spring profiles

In this Spring Boot lesson. we will have a detail look at the Spring Boot REST example. Spring Boot complements Spring
REST support by providing default dependencies/converters out of the box. Writing RESTFUL services in Spring Boot is
easy with support from Spring Boot autoconfiguration feature.

### What Are Profiles?

Every enterprise application has many environments, like:

Dev | Test | Stage | UAT / Pre-Prod | Prod

Each environment requires a setting that is specific to them. For example, in DEV, we do not need to constantly check
database consistency. Whereas in TEST and STAGE, we need to. These environments host specific configurations called
Profiles.

### How Do we Maintain Profiles?

This is simple — properties files!

We make properties files for each environment and set the profile in the application accordingly, so it will pick the
respective properties file. Don't worry, we will see how to set it up.

This lesson will demonstrate how to set up Profiles for your Spring Boot application.

![](lecture/images/01-img.png)

In this demo application, we will see how to configure different server ports at runtime based on the specific
environment by their respective profiles.

As the DB connection is better to be kept in a property file, it remains external to an application and can be changed.
We will do so here. But, Spring Boot — by default — provides just one property file (`application.properties`). So, how
will we segregate the properties based on the environment?

The solution would be to create more property files and add the `profile` name as the suffix and configure Spring Boot
to pick the appropriate properties based on the profile.

Then, we need to create three `application.properties`:

```text
application-dev.properties
application-test.properties
application-prod.properties
```

Of course, the application.properties will remain as a master properties file, but if we override any key in the
profile-specific file, the latter will gain precedence.

I will now define server configuration properties for in respective properties file and add code
in `DBConfiguration.class` to pick the appropriate settings.

Here is the dev `application-dev.properties`:

```text
server.port=9092
```

Let's create `JavaDevConfig.java` in `com.food.demo` package

```java
package com.food.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;

@Profile("dev")
@Configuration
public class JavaDevConfig {
    @PostConstruct
    public void test() {
        System.out.println("Loading dev profile");
    }
}
```

We have used the `@Profile("Dev")` to let the system know that this is the BEAN that should be picked up when we set the
application profile to `DEV`. The other two beans will not be created at all.

One last setting is how to let the system know that this is `DEV`, `TEST`, or `PROD`. But, how do we do this?

We will use the `application.properties` to use the key below

```
server.port=9091
spring.profiles.active=dev
```

From here, Spring Boot will know which profile to pick. Let's run the application now!

```text
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v3.0.0)

2022-12-21T12:54:07.636-05:00  INFO 16890 --- [  restartedMain] com.food.demo.RecipesApplication         : Starting RecipesApplication using Java 18.0.2.1 with PID 16890 (/Users/suresh/Documents/ga/java/demo/target/classes started by suresh in /Users/suresh/Documents/ga/java/demo)
2022-12-21T12:54:07.640-05:00  INFO 16890 --- [  restartedMain] com.food.demo.RecipesApplication         : The following 1 profile is active: "dev"
2022-12-21T12:54:07.723-05:00  INFO 16890 --- [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : Devtools property defaults active! Set 'spring.devtools.add-properties' to 'false' to disable
2022-12-21T12:54:07.723-05:00  INFO 16890 --- [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : For additional web related logging consider setting the 'logging.level.web' property to 'DEBUG'
2022-12-21T12:54:09.100-05:00  INFO 16890 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 9092 (http)
2022-12-21T12:54:09.115-05:00  INFO 16890 --- [  restartedMain] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2022-12-21T12:54:09.115-05:00  INFO 16890 --- [  restartedMain] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.1]
2022-12-21T12:54:09.186-05:00  INFO 16890 --- [  restartedMain] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2022-12-21T12:54:09.187-05:00  INFO 16890 --- [  restartedMain] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 1463 ms
2022-12-21T12:54:10.111-05:00  WARN 16890 --- [  restartedMain] o.s.b.d.a.OptionalLiveReloadServer       : Unable to start LiveReload server
2022-12-21T12:54:10.135-05:00  INFO 16890 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 9092 (http) with context path ''
2022-12-21T12:54:10.144-05:00  INFO 16890 --- [  restartedMain] com.food.demo.RecipesApplication         : Started RecipesApplication in 2.931 seconds (process running for 3.568)
```

That's it! We just have to change it once at the `application.properties` to let Spring Boot know which environment the
code is deployed in, and it will do the magic with the setting.

## Day02

### LEARNING OBJECTIVES

- Explain how the Repository Pattern is used in Spring Data
- Integrate a Spring Boot application with a Postgres database
- Create endpoints for your application using Spring Data
- Use annotations to get the right data from a relational database into your Spring application

### Quick Introduction to REST

REST is a short form of representational State Transfer. Introduced by Roy Fielding, REST is an architecture style for
distributed hypermedia systems. REST is not a standard but think of it as a set of constraints or principles it must
satisfy if we want to refer to an interface as RESTFUL. While working on the REST API, we should keep in mind the
following HTTP methods:

- `GET` – To fetch resources
- `POST` – To create resources
- `PUT` – Update resources
- `DELETE` – Delete resources
- `PATCH` – Partial update to a resource

### Creating Spring Boot Project

To start with our Spring Boot REST example, let’s create a Spring Boot web application. We can either use Spring
Initializr or use IDE, or we can create an application using Spring Boot CLI to create Spring Boot application.

### Spring Boot REST Controller

Let’s create our REST controller for this exercise. Before we start, let’s keep following point in mind:

We will use `@RestController` annotation for our controller.
The `@ResstController` is a convenience annotation that is itself annotated with `@ResponseBody`.

- HTTP `GET` will return category or category list
- HTTP `POST` will create a category
- HTTP `PUT` will update a category
- HTTP `DELETE` method will remove a category from the system

```java
package com.food.demo.controller;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/api")
public class CategoryController {

    @GetMapping(path = "/categories/")
    public String getCategories() {
        return "get all categories";
    }

    @GetMapping(path = "/categories/{categoryId}")
    public String getCategory(@PathVariable Long categoryId) {
        return "getting the category with the id of " + categoryId;
    }

    @PostMapping("/categories/")
    public String createCategory(@RequestBody String body) {
        return "creating a category " + body;
    }

    @PutMapping("/categories/{categoryId}")
    public String updateCategory(@PathVariable(value = "categoryId") Long categoryId, @RequestBody String body) {
        return "updating the category with the id of " + categoryId + body;
    }

    @DeleteMapping("/categories/{categoryId}")
    public String deleteCategory(@PathVariable(value = "categoryId") Long categoryId) {
        return "deleting the category with the id of " + categoryId;
    }
}
```

There are multiple things happening in out sample REST controller. Let’s take a close look at these important points:

- The `@GetMapping` annotation without any mapping (first mapping in our example) map the HTTP `GET` to
  the `/categories` mapping (look at the class level)
- `@PostMapping` annotation map the HTTP `POST` to the `/categories` mapping which pass the execution to
  the `createCategory()`method
- The `@PathVariable` maps the `{id}` to the id method parameter

One of the main difference between a web application and REST API is the response body in REST web service do not use
the template engine to render/generate the HTML for the view, it directly writes the returned object to the HTTP
response and Spring HTTP message convertor translate it to JSON object.The `@RestController` annotation is a combination
of `@ResponseBody`.

This is our `Category` model class to hold the data:

```java
package com.food.demo.model;

public class Category {
    private Long id;
    private String name;
    private String description;

    public Category(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Category() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
```

### Spring Boot Main Class

Here is our Spring Boot main class:

```java
package com.food.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RecipesApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecipesApplication.class, args);
    }
}
```

This is the core component of our Spring Boot REST example.We need a way to let Spring know about REST controller and
other configuration.Spring Boot autoconfiguration provide an intelligent mechanism to scan our application and provide
a setup to run application with minimal code.In our case, autoconfiguration detects the spring-boot-starter-web in our
class path and will configure the embedded tomcat and other configuration automatically for us.

The `@SpringBootApplication` annotation performs the following 3 operation for us:

- `@Configure`
- `@EnableAutoConfiguration`
- `@ComponentScan`

For our case, the `@SpringBootApplication` annotation performing following tasks for us

It automatically detects out the type of our application (MVC in our case) and will configure and setup default
configuration for us e.g. setting up dispatch servlet, scan the component with the `@RestController` and similar
annotations.

- Configure embedded tomcat for us.
- Enable spring mvc default setup.
- Configure and set up the Jackson for JSON.

### Accessing Data with JPA - What is Java Persistence API?

Java Persistence API is a specification that defines an object-relational mapping (ORM) standard for storing, accessing,
and managing Java objects in a relational database.

While originally intended for use with relational/SQL databases only, JPA's ORM model has been since extended for use
with NoSQL data stores as well. At the moment, two most popular implementations of JPA's specification are Hibernate and
EclipseLink.

Spring Data JPA is not a JPA provider but just an extra layer of abstraction on top of an existing JPA provider such as
Hibernate. This means that it uses all features defined by the JPA specification such as the entity and association
mappings, the entity lifecycle management, and JPA's query capabilities.

On top of this, Spring Data JPA defines its own cool features such as no-code repositories and the ability to generate
queries based on method names. Thus, eliminating the need for writing too much boilerplate code for executing simple
queries.

### Why Spring Data JPA

Although we can use any JPA implementation like Hibernate or EclipseLink directly in our project, using Spring Data JPA
gives us additional benefits. This significantly reduces the boilerplate code and makes the overall development much
faster.

The extra layer on top of the JPA specification also allows us to build Spring-powered applications that use JPA for
data access layers.

The real strength of Spring Data JPA lies in the repository abstraction provided by the Spring Data Commons project. It
hides the data store specific implementation details and allows you to write your business logic code on a higher
abstraction level.

You only need to learn how to use Spring Data repository interfaces without worrying about the underlying implementation
of the repository abstraction.

Here is a list of features that makes the Spring Data JPA a go-to choice:

1. Repository Abstraction
2. Less Boilerplate Code
3. Auto-Generated Queries

For the completeness of this lesson, we will use Spring Boot JPA capabilities to store and retrieve categories section.

### Using Spring Data JPA with Spring Boot

As we have discussed above, Spring Data JPA makes the implementation of your data access layer much easier by reducing
the boilerplate code. So that you easily build a Spring-based application using any data access technology.

In this section, I'll show you how to add and configure Spring Data JPA in a Spring Boot application using Hibernate as
a persistence provider. Follow the below steps to add Spring Data JPA support to your Spring Boot project.

Since we are using Maven, add the following dependencies to your `pom.xml` file:

```xml 
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <scope>runtime</scope>
</dependency>    
```

Spring Boot Starter Data JPA includes everything — all required dependencies and activates the default configuration. To
start a Spring Boot application from scratch, use the Spring Initializr tool to easily bootstrap your application with
required dependencies.

```text
server.port=9092
server.error.include-stacktrace=ALWAYS
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:postgresql://localhost:5432/food
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.jpa.show-sql=true
```

`spring.jpa.hibernate.ddl-auto` will turn off hibernate auto-creation of the tables from the entity objects. Generally,
hibernate runs it if there is an `Entity` defined. But we will be using a native SQL query with JdbcTemplate, hence, we
can turn this off as we will not be creating an `Entity`.

- `server.error.include-stacktrace=ALWAYS` show any errors while running the application
- `spring.jpa.show-sql=true` will continue show running sql queries in the background window
- `spring.datasource.url` URL of the Postgres DB. It can be a remote DB as well
- `spring.datasource.username` username for the database
- `spring.datasource.password` password for the database

Now let's update the Category model.

```java
package com.food.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    public Category(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Category() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
```

The code above will generate the following table in our database.

![](lecture/images/02-img.png)

- The above `Category` class has three attributes (`id`, `name`, and `description`) and two constructors. The
  no-argument constructor is only required for the JPA. The other constructor is the one you should use to create
  instances of `Category` to be saved to the database.

- The `Category` class is annotated with `@Entity`, indicating that it is a JPA entity. If no `@Table` annotation is
  provided, it is assumed that this entity is mapped to a table named `Category`.

- The `id` property is annotated with `@Id` so that JPA recognizes it as the object’s `ID`. The `id` property is also
  annotated with `@GeneratedValue` to indicate that the ID should be generated automatically.

- The other two properties, `name` and `description`, are left unannotated. It means that they are mapped to columns
  that have the same names as the properties themselves.

The next step is to create a repository interface for the above `Category` entity.

```java
package com.food.demo.repository;

import com.food.demo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String categoryName);
}
```

`CategoryRepository` extends the `JpaRepository` interface provided by the Spring Data Commons project. The type of
entity and `ID` that it works with, `Category` and `Long`, are specified in the generic parameters on `JpaRepository`.
By extending `JpaRepository`, `CategoryRepository` inherits several methods for saving, deleting, and finding `Category`
entities.

Spring Data JPA also allows you to define other query methods by declaring their method signature. For example,
`CategoryRepository` declares one additional method: `findByName()`.

In a typical Java application, you have to write a class that implements `CategoryRepository` interface methods.
However, it is no longer required with Spring Data JPA. It will create the repository implementation automatically, at
runtime, from the repository interface. That is what makes Spring Data JPA so much powerful.

### Handling exceptions

Response status is an important part of building a robust application. Spring Boot offers more than one way of doing it.
As the name suggests, `@ResponseStatus` allows us to modify the HTTP status of our response. It can be applied in the
following places: To address this we can we annotate our Exception class with `@ResponseStatus` and pass in the desired
HTTP response status in its value property:

```java
package com.food.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class InformationExistException extends RuntimeException {
    public InformationExistException(String message) {
        super(message);
    }
}
```

```java
package com.food.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InformationNotFoundException extends RuntimeException {
    public InformationNotFoundException(String message) {
        super(message);
    }
}
```

In the code above we decided to override all `RuntimeException` occurrences. While throwing our newly created
CustomException was less generic than throwing an Exception, it still did not provide enough specificity. a
`RuntimeException` can be, for example, an `ArithmeticException`, a `NullPointerException`, a `NumberFormatException`,
or an `IndexOutOfBoundsException`. It would be a good idea to take some time to understand the hierarchy of Java
Exception classes and Java exception handling to ensure that we’re overriding the correct exception class when writing
user-defined exceptions. With our newly acquired knowledge, we should be able to create descriptive, precise and helpful
user-defined exceptions that help us keep our program running, and our sanity in check.

Now let's update the `CategoryController.java`

```java
package com.food.demo.controller;

import com.food.demo.exception.InformationExistException;
import com.food.demo.exception.InformationNotFoundException;
import com.food.demo.model.Category;
import com.food.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class CategoryController {

    private CategoryRepository categoryRepository;

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/categories")
    public List<Category> getCategories() {
        System.out.println("calling getCategories ==>");
        return categoryRepository.findAll();
    }

    @GetMapping(path = "/categories/{categoryId}")
    public Optional<Category> getCategory(@PathVariable Long categoryId) {
        System.out.println("calling getCategory ==>");
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isPresent()) {
            return category;
        } else {
            throw new InformationNotFoundException("category with id " + categoryId + " not found");
        }
    }

    @PostMapping("/categories/")
    public Category createCategory(@RequestBody Category categoryObject) {
        System.out.println("calling createCategory ==>");

        Category category = categoryRepository.findByName(categoryObject.getName());
        if (category != null) {
            throw new InformationExistException("category with name " + category.getName() + " already exists");
        } else {
            return categoryRepository.save(categoryObject);
        }
    }

    @PutMapping("/categories/{categoryId}")
    public Category updateCategory(@PathVariable(value = "categoryId") Long categoryId, @RequestBody Category categoryObject) {
        System.out.println("calling updateCategory ==>");
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isPresent()) {
            if (categoryObject.getName().equals(category.get().getName())) {
                System.out.println("Same");
                throw new InformationExistException("category " + category.get().getName() + " is already exists");
            } else {
                Category updateCategory = categoryRepository.findById(categoryId).get();
                updateCategory.setName(categoryObject.getName());
                updateCategory.setDescription(categoryObject.getDescription());
                return categoryRepository.save(updateCategory);
            }
        } else {
            throw new InformationNotFoundException("category with id " + categoryId + " not found");
        }
    }

    @DeleteMapping("/categories/{categoryId}")
    public Optional<Category> deleteCategory(@PathVariable(value = "categoryId") Long categoryId) {
        System.out.println("calling deleteCategory ==>");
        Optional<Category> category = categoryRepository.findById(categoryId);

        if (category.isPresent()) {
            categoryRepository.deleteById(categoryId);
            return category;
        } else {
            throw new InformationNotFoundException("category with id " + categoryId + " not found");
        }
    }
}
```

Note :- Before you proceed make sure to test all the endpoints.

| Request Type | URL                          |
|--------------|------------------------------|
| GET          | /api/categories/             |
| POST         | /api/categories/             |
| GET          | /api/categories/{categoryId} |
| PUT          | /api/categories/{categoryId} |
| DELETE       | /api/categories/{categoryId} |

### Three-tier Architecture

Let’s admit it! Our application is not that big, but the codebase has already started looking quite messy. Figuring out
where a particular functionality is located takes a bit of thinking, and we don’t even have ten classes in our
application. Now imagine what would happen if we keep going to tens and even hundreds of classes - which is quite
normal in a real-world application. It would be a nightmare to unit test, debug, or add new functionality.

Fortunately, there is a widely accepted solution for this problem. It is called three-tier (or three-layer)
architecture. According to this architecture, the codebase is divided into three separate layers with distinctive
responsibilities:

- **Presentation layer**: This is the user interface of the application that presents the application's features and
  data to the user

- **Business logic (or Application) layer**: This layer contains the business logic that drives the application's core
  functionalities. Like making decisions, calculations, evaluations, and processing the data passing between the other
  two layers

- **Data access layer (or Data) layer**: This layer is responsible for interacting with databases to save and restore
  application data

**Controller** classes as the presentation layer. Keep this layer as thin as possible and limited to the mechanics of
the
MVC operations, e.g., receiving and validating the inputs, manipulating the model object, returning the appropriate
MovedAndView object, and so on. All the business-related operations should be done in service classes. Controller
classes are usually put in a controller package.

**Service** classes as the business logic layer. Calculations, data transformations, data processes, and cross-record
validations (business rules) are usually done at this layer. They get called by the controller classes and might call
repositories or other services. Service classes are usually put in a service package.

**Repository** classes as data access layer. This layer’s responsibility is limited to Create, Retrieve, Update, and
Delete (CRUD) operations on a data source, which is usually a relational or non-relational database. Repository classes
are usually put in a repository package.

![](lecture/images/10-img.png)

### Adding Service Layer

This is the place where the business logic goes in. Remember, Spring motto is separation of concerns, for that to happen
we need another layer to separate business logic from controller. Go ahead create a `CategoryService.java` under service
package.

```java
package com.food.demo.service;

import com.food.demo.exception.InformationExistException;
import com.food.demo.exception.InformationNotFoundException;
import com.food.demo.model.Category;
import com.food.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getCategories() {
        System.out.println("service calling getCategories ==>");
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategory(Long categoryId) {
        System.out.println("service getCategory ==>");
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isPresent()) {
            return category;
        } else {
            throw new InformationNotFoundException("category with id " + categoryId + " not found");
        }
    }

    public Category createCategory(Category categoryObject) {
        System.out.println("service calling createCategory ==>");

        Category category = categoryRepository.findByName(categoryObject.getName());
        if (category != null) {
            throw new InformationExistException("category with name " + category.getName() + " already exists");
        } else {
            return categoryRepository.save(categoryObject);
        }
    }

    public Category updateCategory(Long categoryId, Category categoryObject) {
        System.out.println("service calling updateCategory ==>");
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isPresent()) {
            if (categoryObject.getName().equals(category.get().getName())) {
                System.out.println("Same");
                throw new InformationExistException("category " + category.get().getName() + " is already exists");
            } else {
                Category updateCategory = categoryRepository.findById(categoryId).get();
                updateCategory.setName(categoryObject.getName());
                updateCategory.setDescription(categoryObject.getDescription());
                return categoryRepository.save(updateCategory);
            }
        } else {
            throw new InformationNotFoundException("category with id " + categoryId + " not found");
        }
    }

    public Optional<Category> deleteCategory(Long categoryId) {
        System.out.println("service calling deleteCategory ==>");
        Optional<Category> category = categoryRepository.findById(categoryId);

        if (category.isPresent()) {
            categoryRepository.deleteById(categoryId);
            return category;
        } else {
            throw new InformationNotFoundException("category with id " + categoryId + " not found");
        }
    }
}
```

Now let's refactor `CategoryController.java`

```java
package com.food.demo.controller;

import com.food.demo.model.Category;
import com.food.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public List<Category> getCategories() {
        System.out.println("calling getCategories ==>");
        return categoryService.getCategories();
    }

    @GetMapping(path = "/categories/{categoryId}")
    public Optional<Category> getCategory(@PathVariable Long categoryId) {
        System.out.println("calling getCategory ==>");
        return categoryService.getCategory(categoryId);
    }

    @PostMapping("/categories/")
    public Category createCategory(@RequestBody Category categoryObject) {
        System.out.println("calling createCategory ==>");
        return categoryService.createCategory(categoryObject);
    }

    @PutMapping("/categories/{categoryId}")
    public Category updateCategory(@PathVariable(value = "categoryId") Long categoryId, @RequestBody Category categoryObject) {
        System.out.println("calling updateCategory ==>");
        return categoryService.updateCategory(categoryId, categoryObject);
    }

    @DeleteMapping("/categories/{categoryId}")
    public Optional<Category> deleteCategory(@PathVariable(value = "categoryId") Long categoryId) {
        System.out.println("calling deleteCategory ==>");
        return categoryService.deleteCategory(categoryId);
    }
}
```

Note :- Before you proceed make sure to test all the endpoints.

| Request Type | URL                          |
|--------------|------------------------------|
| GET          | /api/categories/             |
| POST         | /api/categories/             |
| GET          | /api/categories/{categoryId} |
| PUT          | /api/categories/{categoryId} |
| DELETE       | /api/categories/{categoryId} |

## Day 03

_After this lesson, you will be able to_:

- Explain how the Repository Pattern one-to-many relationship is used in Spring Data.

### One-To-Many Relationship

A one-to-many relationship refers to the relationship between two entities/tables A and B in which one element/row of A
may only be linked to many elements/rows of B, but a member of B is linked to only one element/row of A.

For instance, think of A as a Category, and B as Recipe. A Category can have many Recipes, but a Recipe can only exist
in one Category, forming a one-to-many relationship. The opposite of one-to-many is many-to-one relationship.

Let us model the above relationship in the database by creating two tables, one for the books and another for the pages,
as shown below in an Entity-Relationship (ER) diagram:

![](lecture/images/03-img.png)

### Creating `Recipe` model

```java
package com.food.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "`recipes`")
public class Recipe {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String time;

    @Column
    private Integer portions;

    @Column
    private String ingredients;

    @Column
    private String steps;

    @Column
    private boolean isPublic;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Recipe() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getPortions() {
        return portions;
    }

    public void setPortions(Integer portions) {
        this.portions = portions;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", portions=" + portions +
                ", ingredients='" + ingredients + '\'' +
                ", steps='" + steps + '\'' +
                '}';
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @JsonProperty("isPublic")
    public boolean getIsPublic() {
        return isPublic;
    }

    @JsonProperty("isPublic")
    public void setIsPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }
}
```

Now, let's update `Category.java` model

```java
package com.food.demo.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    // one category can contain more than one recipe
    @OneToMany(mappedBy = "category", orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Recipe> recipeList;

    public Category(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Category() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public List<Recipe> getRecipeList() {
        return recipeList;
    }

    public void setRecipeList(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }
}
```

Both Category and Recipe classes are annotated with the Entity annotation to indicate that they are JPA entities.
The `@Table` annotation is used to specify the name of the database table that should be mapped to this entity.

### @OneToMany Annotation

The id attributes are annotated with both `@Id` and `@GeneratedValue` annotations. The former annotation indicates that
they are the primary keys of the entities. The latter annotation defines the primary key generation strategy. In the
above case, we have declared that the primary key should be an AUTO INCREMENT field.

A one-to-many relationship between two entities is defined by using the `@OneToMany` annotation in Spring Data JPA. It
declares the `mappedBy` element to indicate the entity that owns the **bidirectional** relationship. Usually, the child
entity is one that owns the relationship and the parent entity contains the `@OneToMany` annotation.

### @ManyToOne

`@ManyToOne` Annotation The `@ManyToOne` annotation is used to define a many-to-one relationship between two entities in
Spring Data JPA. The child entity, that has the join column, is called the owner of the relationship defined using the
`@ManyToOne` annotation.

### Create Repositories

Let us now define the repository interfaces to store and access the data from the database. We'll be extending our
repositories from Spring Data JPA's CrudRepository interface that provides methods for generic CRUD operations.

The `@ManyToOne` annotation is used to define a many-to-one relationship between two entities in Spring Data JPA. The
child entity, that has the join column, is called the owner of the relationship defined using the `@ManyToOne`
annotation.

```java
package com.food.demo.repository;

import com.food.demo.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    Recipe findByName(String recipeName);

    Recipe findByNameAndIdIsNot(String recipeName, Long recipeId);

    List<Recipe> findByCategoryId(Long recipeId);
}
```

In the above repositories, we also defined some derived query methods like `findByName()` to fetch a recipe by its name.

That's it. You have successfully defined a one-to-many relationship mapping in Spring Data JPA. You don't need to
implement the above interfaces thanks to Spring Data JPA.

Finally, let's update the `CategoryController` and `CategoryService` to match all our end points.

```java
package com.food.demo.controller;

import com.food.demo.model.Category;
import com.food.demo.model.Recipe;
import com.food.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public List<Category> getCategories() {
        System.out.println("calling getCategories ==>");
        return categoryService.getCategories();
    }

    @GetMapping(path = "/categories/{categoryId}")
    public Optional<Category> getCategory(@PathVariable Long categoryId) {
        System.out.println("calling getCategory ==>");
        return categoryService.getCategory(categoryId);
    }

    @PostMapping("/categories/")
    public Category createCategory(@RequestBody Category categoryObject) {
        System.out.println("calling createCategory ==>");
        return categoryService.createCategory(categoryObject);
    }

    @PutMapping("/categories/{categoryId}")
    public Category updateCategory(@PathVariable(value = "categoryId") Long categoryId, @RequestBody Category categoryObject) {
        System.out.println("calling updateCategory ==>");
        return categoryService.updateCategory(categoryId, categoryObject);
    }

    @DeleteMapping("/categories/{categoryId}")
    public Optional<Category> deleteCategory(@PathVariable(value = "categoryId") Long categoryId) {
        System.out.println("calling deleteCategory ==>");
        return categoryService.deleteCategory(categoryId);
    }

    @PostMapping("/categories/{categoryId}/recipes")
    public Recipe createCategoryRecipe(
            @PathVariable(value = "categoryId") Long categoryId, @RequestBody Recipe recipeObject) {
        System.out.println("calling createCategoryRecipe ==>");
        return categoryService.createCategoryRecipe(categoryId, recipeObject);
    }

    @GetMapping("/categories/{categoryId}/recipes")
    public List<Recipe> getCategoryRecipes(@PathVariable(value = "categoryId") Long categoryId) {
        System.out.println("calling getCategoryRecipes ==>");
        return categoryService.getCategoryRecipes(categoryId);
    }

    @GetMapping("/categories/{categoryId}/recipes/{recipeId}")
    public Recipe getCategoryRecipe(
            @PathVariable(value = "categoryId") Long categoryId, @PathVariable(value = "recipeId") Long recipeId) {
        System.out.println("calling getCategoryRecipe ==>");
        return categoryService.getCategoryRecipe(categoryId, recipeId);
    }

    @PutMapping("/categories/{categoryId}/recipes/{recipeId}")
    public Recipe updateCategoryRecipe(@PathVariable(value = "categoryId") Long categoryId,
                                       @PathVariable(value = "recipeId") Long recipeId,
                                       @RequestBody Recipe recipeObject) {
        System.out.println("calling getCategoryRecipe ==>");
        return categoryService.updateCategoryRecipe(categoryId, recipeId, recipeObject);
    }

    @DeleteMapping("/categories/{categoryId}/recipes/{recipeId}")
    public ResponseEntity<HashMap<String, String>> deleteCategoryRecipe(
            @PathVariable(value = "categoryId") Long categoryId, @PathVariable(value = "recipeId") Long recipeId) {
        System.out.println("calling getCategoryRecipe ==>");
        categoryService.deleteCategoryRecipe(categoryId, recipeId);
        HashMap<String, String> responseMessage = new HashMap<>();
        responseMessage.put("status", "recipe with id: " + recipeId + " was successfully deleted.");
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }
}
```

```java
package com.food.demo.service;

import com.food.demo.exception.InformationExistException;
import com.food.demo.exception.InformationNotFoundException;
import com.food.demo.model.Category;
import com.food.demo.model.Recipe;
import com.food.demo.repository.CategoryRepository;
import com.food.demo.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;
    private RecipeRepository recipeRepository;

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Autowired
    public void setRecipeRepository(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Category> getCategories() {
        System.out.println("service calling getCategories ==>");
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategory(Long categoryId) {
        System.out.println("service getCategory ==>");
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isPresent()) {
            return category;
        } else {
            throw new InformationNotFoundException("category with id " + categoryId + " not found");
        }
    }

    public Category createCategory(Category categoryObject) {
        System.out.println("service calling createCategory ==>");

        Category category = categoryRepository.findByName(categoryObject.getName());
        if (category != null) {
            throw new InformationExistException("category with name " + category.getName() + " already exists");
        } else {
            return categoryRepository.save(categoryObject);
        }
    }

    public Category updateCategory(Long categoryId, Category categoryObject) {
        System.out.println("service calling updateCategory ==>");
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isPresent()) {
            if (categoryObject.getName().equals(category.get().getName())) {
                System.out.println("Same");
                throw new InformationExistException("category " + category.get().getName() + " is already exists");
            } else {
                Category updateCategory = categoryRepository.findById(categoryId).get();
                updateCategory.setName(categoryObject.getName());
                updateCategory.setDescription(categoryObject.getDescription());
                return categoryRepository.save(updateCategory);
            }
        } else {
            throw new InformationNotFoundException("category with id " + categoryId + " not found");
        }
    }

    public Optional<Category> deleteCategory(Long categoryId) {
        System.out.println("service calling deleteCategory ==>");
        Optional<Category> category = categoryRepository.findById(categoryId);

        if (category.isPresent()) {
            categoryRepository.deleteById(categoryId);
            return category;
        } else {
            throw new InformationNotFoundException("category with id " + categoryId + " not found");
        }
    }

    public Recipe createCategoryRecipe(Long categoryId, Recipe recipeObject) {
        System.out.println("service calling createCategoryRecipe ==>");
        try {
            Optional category = categoryRepository.findById(categoryId);
            recipeObject.setCategory((Category) category.get());
            return recipeRepository.save(recipeObject);
        } catch (NoSuchElementException e) {
            throw new InformationNotFoundException("category with id " + categoryId + " not found");
        }
    }

    public List<Recipe> getCategoryRecipes(Long categoryId) {
        System.out.println("service calling getCategoryRecipes ==>");
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isPresent()) {
            return category.get().getRecipeList();
        } else {
            throw new InformationNotFoundException("category with id " + categoryId + " not found");
        }
    }

    public Recipe getCategoryRecipe(Long categoryId, Long recipeId) {
        System.out.println("service calling getCategoryRecipe ==>");
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isPresent()) {
            Optional<Recipe> recipe = recipeRepository.findByCategoryId(categoryId).stream().filter(
                    p -> p.getId().equals(recipeId)).findFirst();
            if (recipe.isEmpty()) {
                throw new InformationNotFoundException("recipe with id " + recipeId + " not found");
            } else {
                return recipe.get();
            }
        } else {
            throw new InformationNotFoundException("category with id " + categoryId + " not found");
        }
    }

    public Recipe updateCategoryRecipe(Long categoryId, Long recipeId, Recipe recipeObject) {
        System.out.println("service calling updateCategoryRecipe ==>");
        try {
            Recipe recipe = (recipeRepository.findByCategoryId(
                    categoryId).stream().filter(p -> p.getId().equals(recipeId)).findFirst()).get();
            recipe.setName(recipeObject.getName());
            recipe.setIngredients(recipeObject.getIngredients());
            recipe.setSteps(recipeObject.getSteps());
            recipe.setTime(recipeObject.getTime());
            recipe.setPortions(recipeObject.getPortions());
            return recipeRepository.save(recipe);
        } catch (NoSuchElementException e) {
            throw new InformationNotFoundException("recipe or category not found");
        }
    }

    public void deleteCategoryRecipe(Long categoryId, Long recipeId) {
        try {
            Recipe recipe = (recipeRepository.findByCategoryId(
                    categoryId).stream().filter(p -> p.getId().equals(recipeId)).findFirst()).get();
            recipeRepository.deleteById(recipe.getId());
        } catch (NoSuchElementException e) {
            throw new InformationNotFoundException("recipe or category not found");
        }
    }
}
```

### Check all the end points

| Request Type | URL                                             |
|--------------|-------------------------------------------------|
| GET          | /api/categories/                                |
| POST         | /api/categories/                                |
| GET          | /api/categories/{categoryId}                    |
| PUT          | /api/categories/{categoryId}                    |
| DELETE       | /api/categories/{categoryId}                    |
| GET          | /api/categories/{categoryId}/recipes            |
| POST         | /api/categories/{categoryId}/recipes            |
| GET          | /api/categories/{categoryId}/recipes/{recipeId} |
| PUT          | /api/categories/{categoryId}/recipes/{recipeId} |
| DELETE       | /api/categories/{categoryId}/recipes/{recipeId} |

## Day 04

_After this lesson, you will be able to_:

- Explain how the Repository Pattern one-to-one relationship is used in Spring Data
- Creating user registration endpoint

### One-To-One Relationship

A one-to-one relationship refers to the relationship between two entities/database tables A and B in which only one
element/row of A may only be linked to one element/row of B, and vice versa. Let us consider an application scenario
where you want to store users' profile information along with login information. We want to make sure that a user can
have just one user profile, and profile can only be associated with a single user.

We can map the above requirement as a one-to-one relationship between the user and the address entities, as shown in the
following Entity-Relationship (ER) diagram:

![](lecture/images/04-img.png)

And our final ERD diagram should have the following relationship

- User can have one and only one user profile
- User can have many categories
- Many categories can belong to a one user
- Categories can have many recipes
- Many recipes belong to a one category

![](lecture/images/05-img.png)

- User model

```java
package com.food.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;

    @Column(unique = true)
    private String emailAddress;

    @Column
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    // one user can have only one profile
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    private UserProfile userProfile;

    // user can have more than one recipe
    @OneToMany(mappedBy = "user")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Recipe> recipeList;

    // user can have more than one category
    @OneToMany(mappedBy = "user")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Category> categoryList;

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public List<Recipe> getRecipeList() {
        return recipeList;
    }

    public void setRecipeList(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public User() {
    }

    public User(Long id, String userName, String emailAddress, String password) {
        this.id = id;
        this.userName = userName;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
```

- User profile

```java
package com.food.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "profiles")
public class UserProfile {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String profileDescription;

    @JsonIgnore
    @OneToOne(mappedBy = "userProfile")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserProfile() {
    }

    public UserProfile(Long id, String firstName, String lastName, String profileDescription) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profileDescription = profileDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfileDescription() {
        return profileDescription;
    }

    public void setProfileDescription(String profileDescription) {
        this.profileDescription = profileDescription;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", profileDescription='" + profileDescription + '\'' +
                '}';
    }
}
```

- Category model

```java
package com.food.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    // one category can contain more than one recipe
    @OneToMany(mappedBy = "category", orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Recipe> recipeList;

    /********** add user **********/
    // many categories belong to a one user
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    /********** end of user **********/

    public Category(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Category() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public List<Recipe> getRecipeList() {
        return recipeList;
    }

    public void setRecipeList(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }

    /********** user getters and setters **********/
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    /********** end of user getters and setters **********/
}
```

- Recipe model

```java
package com.food.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "recipes")
public class Recipe {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String time;

    @Column
    private Integer portions;

    @Column
    private String ingredients;

    @Column
    private String steps;

    @Column
    private boolean isPublic;

    /********** add user **********/
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
    /********** end of add user **********/

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Recipe() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getPortions() {
        return portions;
    }

    public void setPortions(Integer portions) {
        this.portions = portions;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", portions=" + portions +
                ", ingredients='" + ingredients + '\'' +
                ", steps='" + steps + '\'' +
                '}';
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @JsonProperty("isPublic")
    public boolean getIsPublic() {
        return isPublic;
    }

    @JsonProperty("isPublic")
    public void setIsPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    /********** user getters and setters **********/
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    /********** user getters and setters **********/
}
```

Create `UserRepository.java` inside the `com.food.demo.repository` package.

```java
package com.food.demo.repository;

import com.food.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // to register  
    boolean existsByEmailAddress(String userEmailAddress);

    // to login  
    User findUserByEmailAddress(String userEmailAddress);
}
```

### Allowing users to login

Create `UserService.java` inside the package `com.food.demo.service`.

```java
package com.food.demo.service;

import com.food.demo.exception.InformationExistException;
import com.food.demo.model.User;
import com.food.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(User userObject) {
        System.out.println("service calling createUser ==>");
        if (!userRepository.existsByEmailAddress(userObject.getEmailAddress())) {
            userObject.setPassword(passwordEncoder.encode(userObject.getPassword()));
            return userRepository.save(userObject);
        } else {
            throw new InformationExistException("user with email address " + userObject.getEmailAddress() +
                    " already exists");
        }
    }

    public User findUserByEmailAddress(String email) {
        return userRepository.findUserByEmailAddress(email);
    }
}
```

Let's go ahead and create a new package and will call it `security`, and we'll update the `POM.xml` as the following.

```xml

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

`UserDetailsService` is used to load user-specific data. If we are using Spring security in our application for the
authentication and authorization, you might know `UserDetailsService` interface. The `UserDetailsService` is a core
interface in Spring Security framework, which is used to retrieve the user’s `authentication` and `authorization`
information.

Let's create `MyUserDetailsService.java` file inside the package `com.food.demo.security` package.

```java
package com.food.demo.security;

import com.food.demo.model.User;
import com.food.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findUserByEmailAddress(email);
        return new MyUserDetails(user);
    }
}
```

In the above code, we're looking up the user information based on the email address. And the next step is to implement
the `import org.springframework.security.core.userdetails.UserDetails` inside the `UserDetails`.

```java
package security;

import com.food.demo.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;

public class MyUserDetails implements UserDetails {

    private User user;

    public MyUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new HashSet<>();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmailAddress();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User getUser() {
        return user;
    }
}
```

and finally to secure all the endpoint.

```java
package com.food.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.context.WebApplicationContext;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {

    private MyUserDetailsService myUserDetailsService;

    @Autowired
    public void setMyUserDetailsService(MyUserDetailsService myUserDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
    }

    @Bean
    public JwtRequestFilter authenticationJwtTokenFilter() {
        return new JwtRequestFilter();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(
                        "/auth/users", "/auth/users/login", "/auth/users/register").permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().csrf().disable();
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(myUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public MyUserDetails myUserDetails() {
        return (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
    }
}
```

- Spring `@Configuration` annotation is part of the spring core framework. Spring Configuration annotation indicates
  that the class has `@Bean` definition methods. So Spring container can process the class and generate Spring Beans to
  be used in the application
- `@EnableGlobalMethodSecurity`
  enable [global Method Security](https://docs.spring.io/spring-security/reference/servlet/authorization/method-security.html#jc-enable-method-security)
- The `prePostEnabled` property enables Spring Security pre/post-annotations
- Next we modify the security configuration to use the bcrypt encoder. We first create a bean of type
  `BCryptPasswordEncoder`. This bean type is then provided to the `AuthenticationManagerBuilder`
- To enable HTTP Security in Spring, we need to create a `SecurityFilterChain` bean
- An `AuthenticationManager` can do one of 3 things in its `authenticate()` method:
    - Return an `Authentication` (normally with `authenticated=true`) if it can verify that the input represents a valid
      principal
    - Throw an `AuthenticationException` if it believes that the input represents an invalid principal
    - Return `null` if it cannot decide
- The `DaoAuthenticationProvider` use the custom `myUserDetailsService` service to get the user information from the
  database
    - On successful authentication, the authentication object will contain the fully populated object including the
      authorities details
- `SecurityContext` in Spring Security and obtain the name of the currently logged-in user:
    - The object returned by `getContext()` is an instance of the `SecurityContext` interface. This is the object that
      is stored in a thread-local storage
    - The `getPrincipal()` method normally returns `UserDetails` object in Spring Security, which contains all the
      details of currently logged-in user

Now we must allow users to login. To execute this process, we are going to use a custom class.

Inside the `model` folder, create another folder, and we'll call this `request`. Here we are going to
create `LoginRequest` class.

```java
package com.food.demo.model.request;

public class LoginRequest {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
```

Create `UserController.java` inside the `controller` package `com.food.demo.controller`.

```java
package com.food.demo.controller;

import com.food.demo.model.User;
import com.food.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/auth/users")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User createUser(@RequestBody User userObject) {
        System.out.println("calling createUser ==>");
        return userService.createUser(userObject);
    }
}
```

## Day 05

_After this lesson, you will be able to_:

- Explain Token-based authentication in Spring boot
- Personalized data work-flow for individual user

### What Is Token-based Authentication?

Token-based authentication (also known as JSON Web Token authentication) is a new way of handling the authentication of
users in applications. It is an alternative to session-based authentication.

The most notable difference between the session-based and token-based authentication is that session-based
authentication relies heavily on the server. A record is created for each logged-in user.

Token-based authentication is stateless - it does not store anything on the server but creates a unique encoded token
that gets checked every time a request is made.

Unlike session-based authentication, a token approach would not associate a user with login information but with a
unique token that is used to carry client-host transactions. Many applications, including Facebook, Google, and GitHub,
use the token-based approach.

### Benefits of Token-based Authentication

There are several benefits to using such an approach:

### Cross-domain / CORS

Cookies and CORS don't mix well across different domains. A token-based approach allows you to make AJAX calls to any
server, on any domain, because you use an HTTP header to transmit the user information.

### Stateless

Tokens are stateless. There is no need to keep a session store since the token is a self-contained entity that stores
all the user information in it.

### Decoupling

You are no longer tied to a particular authentication scheme. Tokens may be generated anywhere, so the API can be called
from anywhere with a single authenticated command rather than multiple authenticated calls.

### Mobile Ready

Cookies are a problem when it comes to storing user information in native mobile applications. Adopting a token-based
approach simplifies this saving process significantly.

### CSRF (Cross Site Request Forgery)

Because the application does not rely on cookies for authentication, it is invulnerable to cross-site request attacks.

### Performance

In terms of server-side load, a network round-trip (e.g. finding a session on a database) is likely to take more time
than calculating an `HMACSHA256` code to validate a token and parsing its contents. This makes token-based
authentication faster than the traditional alternative.

### How Does Token-based Authentication Work?

The way token-based authentication works is simple. The user enters his or her credentials and sends a request to the
server. If the credentials are correct, the server creates a unique `HMACSHA256` encoded token, also known as JSON web
token (JWT). The client stores the JWT and makes all subsequent requests to the server with the token attached. The
server authenticates the user by comparing the JWT sent with the request to the one it has stored in the database. Here
is a simple diagram of the process :

![](lecture/images/08-img.png)

### What Does a JWT Token Contain?

The token is separated into three `base-64` encoded, dot-separated values. Each value represents a different type of
data:

### Header

Consists of the type of the token (JWT) and the type of encryption algorithm (`HS256`) encoded in base-64.

### Payload

The payload contains information about the user and his or her role. For example, the payload of the token can contain
the e-mail and the password.

### Signature

Signature is a unique key that identifies the service which creates the header. In this case, the signature of the token
will be a base-64 encoded version of the Rails application's secret key (Rails.application.secrets.secret_key_base).
Because each application has a unique base key, this secret key serves as the token signature.

### Anatomy of a JWT

If you encounter a JWT in the wild, you’ll notice that it’s separated into three sections, the header, payload, and
signature. As we dissect the anatomy of a JWT!) Here’s an example of a typical JWT:

```text
eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2Vycy9Uek1Vb2NNRjRwIiwibmFtZSI6IlJvYmVydCBUb2tlbiBNYW4iLCJzY29wZSI6InNlbGYgZ3JvdXBzL2FkbWlucyIsImV4cCI6IjEzMDA4MTkzODAifQ.1pVOLQduFWW3muii1LExVBt2TK1-MdRI4QjhKryaDwc
```

In this example, Section 1 is a header which describes the token. Section 2 is the payload, which contains the JWT’s
claims, and Section 3 is the signature hash that can be used to verify the integrity of the token (if you have the
secret key that was used to sign it).

When we decode the payload we get this nice, tidy JSON object containing the claims of the JWS:

```json
{
  "sub": "users/TzMUocMF4p",
  "name": "Robert Token Man",
  "scope": "self groups/admins",
  "exp": "1300819380"
}
```

The claims tell you, at minimum:

- Who this person is and the URI to their user resource (the sub claim)
- What this person can access with this token (the scope claim)
- When the token expires. Your API should be using this when it verifies the token

Because the token is signed with a secret key you can verify its signature and implicitly trust what is being claimed.

### JWE, JWS, and JWT

Per the “JWTs represent a set of claims as a JSON object that is encoded in a JWS and/or JWE structure.” The term “JWT”
technically only describes an unsigned token; what we refer to as a JWT is most often a JWS or JWS + JWE.

First, let's include the dependency below in your pom for jwt features.

```text
<!-- https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-api -->
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-api</artifactId>
    <version>0.11.5</version>
</dependency>
<!-- https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-impl -->
<dependency>
  <groupId>io.jsonwebtoken</groupId>
  <artifactId>jjwt-impl</artifactId>
  <version>0.11.5</version>
  <scope>runtime</scope>
</dependency>
<!-- https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-jackson -->
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-jackson</artifactId>
    <version>0.11.5</version>
    <scope>runtime</scope>
</dependency>
```

In our package `com.food.demo.security` let's create a new `JWTUtils.java`

In `JWTUtils.java` we have various functions dealing with jwt such as `generateJwtToken`, `getUserNameFromJwtToken`
and `validateJwtToken`. These methods extract and validate user details from token, set expiration of token etc. The
default time expiration is set to 24 hours in milliseconds.

In our code, we should avoid hardcoding secret keys. Now that we know what the two fields are we will update the
`application-dev.properties` with them as follows:

```text
jwt-secret="C6UlILsE6GJwNqwCTkkvJj9O653yJUoteWMLfYyrc3vaGrrTOrJFAUD1wEBnnposzcQl"
jwt-expiration-ms=86400000
```

```java
package com.food.demo.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class JWTUtils {
    Logger logger = Logger.getLogger(JWTUtils.class.getName());

    @Value("${jwt-secret}")
    private String jwtSecret;

    @Value("${jwt-expiration-ms}")
    private int jwtExpirationMs;

    public String generateJwtToken(MyUserDetails myUserDetails) {
        return Jwts.builder()
                .setSubject((myUserDetails.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SecurityException e) {
            logger.log(Level.SEVERE, "Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.log(Level.SEVERE, "Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.log(Level.SEVERE, "JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.log(Level.SEVERE, "JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.log(Level.SEVERE, "JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }
}
```

This `JWTUtils` class has 3 methods:

- `generateJwtToken` generate a JWT from username, issue date, expiration date, secret
- `getUserNameFromJwtToken` get username from JWT
- `validateJwtToken` validate a JWT
- `@Value` annotation populate the Spring environment during the startup, Spring reads all the local and system
  environment variables and stores them as properties file, so they can be assigned to objects

In the next step, we are going to include a class named `LoginResponse` under the `com.food.demo.model.response`
package. When a successful login has been completed, this class will allow us to return a JWT token to the user.

```java
package com.food.demo.model.response;

public class LoginResponse {
    private String message;

    public LoginResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
```

Now we are going to add the following code block to our `UserServices.java` class in order to complete our login
process.

```java
package com.food.demo.service;

import com.food.demo.exception.InformationExistException;
import com.food.demo.model.User;
import com.food.demo.model.request.LoginRequest;
import com.food.demo.model.response.LoginResponse;
import com.food.demo.repository.UserRepository;
import com.food.demo.security.JWTUtils;
import com.food.demo.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private MyUserDetails myUserDetails;


    @Autowired
    public UserService(UserRepository userRepository,
                       @Lazy PasswordEncoder passwordEncoder,
                       JWTUtils jwtUtils,
                       @Lazy AuthenticationManager authenticationManager,
                       @Lazy MyUserDetails myUserDetails) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
        this.myUserDetails = myUserDetails;
    }

    public User createUser(User userObject) {
        System.out.println("service calling createUser ==>");
        if (!userRepository.existsByEmailAddress(userObject.getEmailAddress())) {
            userObject.setPassword(passwordEncoder.encode(userObject.getPassword()));
            return userRepository.save(userObject);
        } else {
            throw new InformationExistException("user with email address " + userObject.getEmailAddress() +
                    " already exists");
        }
    }

    public User findUserByEmailAddress(String email) {
        return userRepository.findUserByEmailAddress(email);
    }

    public ResponseEntity<?> loginUser(LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            myUserDetails = (MyUserDetails) authentication.getPrincipal();
            final String JWT = jwtUtils.generateJwtToken(myUserDetails);
            return ResponseEntity.ok(new LoginResponse(JWT));
        } catch (Exception e) {
            return ResponseEntity.ok(new LoginResponse("Error : user name or password is incorrect"));
        }
    }
}
```

- `@Lazy` annotation indicates whether a bean is to be lazily initialized. By default in Spring, all the defined beans,
  and their dependencies, are created when the application context is created. In contrast, when we configure a bean
  with lazy initialization, the bean will only be created, and its dependencies injected, once they're needed.
- `AuthenticationManager` is a core interface that spring security uses for the authentication process. It has only
  one method authenticate which when implemented in a class that implements an Authentication Manager has all the logic
  for authenticating a user request
- `UsernamePasswordAuthenticationToken` extends `AbstractAuthenticationToken`, An Authentication implementation that is
  designed for simple presentation of a username and password
- `SecurityContextHolder.getContext()` is used to store the details of the currently authenticated user, also known as a
  principle. So, if you have to get the username or any other user details, you need to get this `SecurityContext`
  first. The `SecurityContextHolder` is a helper class, which provide access to the security context.

We have now reached the stage where we have to create the `JwtRequestFilter.class`. The `JwtRequestFilter` executes once
per request, as its name suggests, as soon as the request arrives. It is for this reason we need to create the
`JwtRequestFilter` class that extends `OncePerRequestFilter` and overrides the `doFilterInternal()` method.

```java
package com.food.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private JWTUtils jwtUtils;

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7, headerAuth.length());
        }
        return null;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String jwt = parseJwt(request);
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                String username = jwtUtils.getUserNameFromJwtToken(jwt);
                UserDetails userDetails = this.myUserDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            logger.error("Cannot set user authentication: {}", e);
        }
        filterChain.doFilter(request, response);
    }
}
```

What we do inside `doFilterInternal()`:

- Get JWT from the `Authorization` header (by removing `Bearer` prefix)
- If the request has JWT, validate it, parse username from it
- From username, get `UserDetails` to create an `Authentication` object
- Set the current `UserDetails` in `SecurityContext` using `setAuthentication(authentication)` method

If you wish to access the current logged-in user's information in the future, you can do this by making use of the
`SecurityContext` method in any controller as follows:

```text
public static User getCurrentLoggedInUser(){
    MyUserDetails userDetails=(MyUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return userDetails.getUser();
}
```

Last but not least, we are going to create the `@PostMapping` in order to facilitate user login.

```text
@PostMapping("/login")
public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest){
    System.out.println("calling loginUser ==>");
    return userService.loginUser(loginRequest);
}
```

Finally, let's test the [http://localhost:9092/auth/users/register](http://localhost:9092/auth/users/register) endpoint

![](lecture/images/09-img.png)

Remember we are still not able to create a categories or recipes yet. Since we have all the connections between user and
all the other tables, we must use `MyUserDetails` in order to fetch the information that is belongs to the user.

In addition to implementing authorization and authentication, we must also ensure that only logged-in users will be able
to create and modify categories as well as recipes that belong to them. To accomplish this, we would need to modify
both `CategoryRepository.java` and `RecipeRepository.java` in order to achieve the desired result.

```java
package com.food.demo.repository;

import com.food.demo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String categoryName);

    Category findByIdAndUserId(Long categoryId, Long userId);

    List<Category> findByUserId(Long userId);

    Category findByUserIdAndName(Long UserId, String categoryName);
}
```

```java
package com.food.demo.repository;

import com.food.demo.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    List<Recipe> findByCategoryId(Long recipeId);

    Recipe findByNameAndUserIdAndIdIsNot(String recipeName, Long userId, Long recipeId);

    Recipe findByNameAndUserId(String recipeName, Long userId);
}
```

And now, we're ready to refactor the `CategoryService.java`.

```java
package com.food.demo.service;

import com.food.demo.exception.InformationExistException;
import com.food.demo.exception.InformationNotFoundException;
import com.food.demo.model.Category;
import com.food.demo.model.Recipe;
import com.food.demo.model.User;
import com.food.demo.repository.CategoryRepository;
import com.food.demo.repository.RecipeRepository;
import com.food.demo.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, RecipeRepository recipeRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
    }

    public static User getCurrentLoggedInUser() {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        return userDetails.getUser();
    }

    public List<Category> getCategories() {
        List<Category> category = categoryRepository.findByUserId(CategoryService.getCurrentLoggedInUser().getId());
        if (category.isEmpty()) {
            throw new InformationNotFoundException("no categories found for user id " + CategoryService.getCurrentLoggedInUser().getId());
        } else {
            return category;
        }
    }

    public Category getCategory(Long categoryId) {
        Category category = categoryRepository.findByIdAndUserId(categoryId, CategoryService.getCurrentLoggedInUser().getId());
        if (category == null) {
            throw new InformationNotFoundException("category with id " + categoryId + " not found");
        } else {
            return category;
        }
    }

    public Category createCategory(Category categoryObject) {
        Category category = categoryRepository.findByUserIdAndName(
                CategoryService.getCurrentLoggedInUser().getId(), categoryObject.getName());
        if (category != null) {
            throw new InformationExistException("category with name " + category.getName() + " already exists");
        } else {
            categoryObject.setUser(getCurrentLoggedInUser());
            return categoryRepository.save(categoryObject);
        }
    }

    public Category updateCategory(Long categoryId, Category categoryObject) {
        Category category = categoryRepository.findByIdAndUserId(categoryId, CategoryService.getCurrentLoggedInUser().getId());
        if (category == null) {
            throw new InformationNotFoundException("category with id " + categoryId + " not found");
        } else {
            category.setDescription(categoryObject.getDescription());
            category.setName(categoryObject.getName());
            category.setUser(CategoryService.getCurrentLoggedInUser());
            return categoryRepository.save(category);
        }
    }

    public String deleteCategory(Long categoryId) {
        Category category = categoryRepository.findByIdAndUserId(categoryId, CategoryService.getCurrentLoggedInUser().getId());
        if (category == null) {
            throw new InformationNotFoundException("category with id " + categoryId + " not found");
        } else {
            categoryRepository.deleteById(categoryId);
            return "category with id " + categoryId + " has been successfully deleted";
        }
    }

    public Recipe createCategoryRecipe(Long categoryId, Recipe recipeObject) {
        Category category = categoryRepository.findByIdAndUserId(categoryId, CategoryService.getCurrentLoggedInUser().getId());
        if (category == null) {
            throw new InformationNotFoundException(
                    "category with id " + categoryId + " not belongs to this user or category does not exist");
        }
        Recipe recipe = recipeRepository.findByNameAndUserId(recipeObject.getName(), CategoryService.getCurrentLoggedInUser().getId());
        if (recipe != null) {
            throw new InformationExistException("recipe with name " + recipe.getName() + " already exists");
        }
        recipeObject.setUser(CategoryService.getCurrentLoggedInUser());
        recipeObject.setCategory(category);
        return recipeRepository.save(recipeObject);
    }


    public List<Recipe> getCategoryRecipes(Long categoryId) {
        Category category = categoryRepository.findByIdAndUserId(categoryId, CategoryService.getCurrentLoggedInUser().getId());
        if (category == null) {
            throw new InformationNotFoundException("category with id " + categoryId + " " +
                    "not belongs to this user or category does not exist");
        }
        return category.getRecipeList();
    }

    public Recipe getCategoryRecipe(Long categoryId, Long recipeId) {
        Category category = categoryRepository.findByIdAndUserId(categoryId, CategoryService.getCurrentLoggedInUser().getId());
        if (category == null) {
            throw new InformationNotFoundException("category with id " + categoryId +
                    " not belongs to this user or category does not exist");
        }
        Optional<Recipe> recipe = recipeRepository.findByCategoryId(
                categoryId).stream().filter(p -> p.getId().equals(recipeId)).findFirst();
        if (recipe.isEmpty()) {
            throw new InformationNotFoundException("recipe with id " + recipeId +
                    " not belongs to this user or recipe does not exist");
        }
        return recipe.get();
    }


    public Recipe updateCategoryRecipe(Long categoryId, Long recipeId, Recipe recipeObject) {
        Category category = categoryRepository.findByIdAndUserId(categoryId, CategoryService.getCurrentLoggedInUser().getId());
        if (category == null) {
            throw new InformationNotFoundException("category with id " + categoryId +
                    " not belongs to this user or category does not exist");
        }
        Optional<Recipe> recipe = recipeRepository.findByCategoryId(
                categoryId).stream().filter(p -> p.getId().equals(recipeId)).findFirst();
        if (recipe.isEmpty()) {
            throw new InformationNotFoundException("recipe with id " + recipeId +
                    " not belongs to this user or recipe does not exist");
        }
        Recipe oldRecipe = recipeRepository.findByNameAndUserIdAndIdIsNot(
                recipeObject.getName(), CategoryService.getCurrentLoggedInUser().getId(), recipeId);
        if (oldRecipe != null) {
            throw new InformationExistException("recipe with name " + oldRecipe.getName() + " already exists");
        }
        recipe.get().setName(recipeObject.getName());
        recipe.get().setIngredients(recipeObject.getIngredients());
        recipe.get().setSteps(recipeObject.getSteps());
        recipe.get().setTime(recipeObject.getTime());
        recipe.get().setPortions(recipeObject.getPortions());
        return recipeRepository.save(recipe.get());
    }

    public void deleteCategoryRecipe(Long categoryId, Long recipeId) {
        Category category = categoryRepository.findByIdAndUserId(categoryId, CategoryService.getCurrentLoggedInUser().getId());
        if (category == null) {
            throw new InformationNotFoundException("category with id " + categoryId +
                    " not belongs to this user or category does not exist");
        }
        Optional<Recipe> recipe = recipeRepository.findByCategoryId(
                categoryId).stream().filter(p -> p.getId().equals(recipeId)).findFirst();
        if (recipe.isEmpty()) {
            throw new InformationNotFoundException("recipe with id " + recipeId +
                    " not belongs to this user or recipe does not exist");
        }
        recipeRepository.deleteById(recipe.get().getId());
    }
}
```

The last step is to refactor the file `CategoryController.java`.

```java
package com.food.demo.controller;

import com.food.demo.model.Category;
import com.food.demo.model.Recipe;
import com.food.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public List<Category> getCategories() {
        System.out.println("calling getCategories ==>");
        return categoryService.getCategories();
    }

    @GetMapping(path = "/categories/{categoryId}")
    public Category getCategory(@PathVariable Long categoryId) {
        System.out.println("calling getCategory ==>");
        return categoryService.getCategory(categoryId);
    }

    @PostMapping("/categories/")
    public Category createCategory(@RequestBody Category categoryObject) {
        System.out.println("calling createCategory ==>");
        return categoryService.createCategory(categoryObject);
    }

    @PutMapping("/categories/{categoryId}")
    public Category updateCategory(@PathVariable(value = "categoryId") Long categoryId, @RequestBody Category categoryObject) {
        System.out.println("calling updateCategory ==>");
        return categoryService.updateCategory(categoryId, categoryObject);
    }

    @DeleteMapping("/categories/{categoryId}")
    public String deleteCategory(@PathVariable(value = "categoryId") Long categoryId) {
        System.out.println("calling deleteCategory ==>");
        return categoryService.deleteCategory(categoryId);
    }

    @PostMapping("/categories/{categoryId}/recipes")
    public Recipe createCategoryRecipe(
            @PathVariable(value = "categoryId") Long categoryId, @RequestBody Recipe recipeObject) {
        System.out.println("calling createCategoryRecipe ==>");
        return categoryService.createCategoryRecipe(categoryId, recipeObject);
    }

    @GetMapping("/categories/{categoryId}/recipes")
    public List<Recipe> getCategoryRecipes(@PathVariable(value = "categoryId") Long categoryId) {
        System.out.println("calling getCategoryRecipes ==>");
        return categoryService.getCategoryRecipes(categoryId);
    }

    @GetMapping("/categories/{categoryId}/recipes/{recipeId}")
    public Recipe getCategoryRecipe(
            @PathVariable(value = "categoryId") Long categoryId, @PathVariable(value = "recipeId") Long recipeId) {
        System.out.println("calling getCategoryRecipe ==>");
        return categoryService.getCategoryRecipe(categoryId, recipeId);
    }

    @PutMapping("/categories/{categoryId}/recipes/{recipeId}")
    public Recipe updateCategoryRecipe(@PathVariable(value = "categoryId") Long categoryId,
                                       @PathVariable(value = "recipeId") Long recipeId,
                                       @RequestBody Recipe recipeObject) {
        System.out.println("calling getCategoryRecipe ==>");
        return categoryService.updateCategoryRecipe(categoryId, recipeId, recipeObject);
    }

    @DeleteMapping("/categories/{categoryId}/recipes/{recipeId}")
    public ResponseEntity<HashMap<String, String>> deleteCategoryRecipe(
            @PathVariable(value = "categoryId") Long categoryId, @PathVariable(value = "recipeId") Long recipeId) {
        System.out.println("calling getCategoryRecipe ==>");
        categoryService.deleteCategoryRecipe(categoryId, recipeId);
        HashMap<String, String> responseMessage = new HashMap<>();
        responseMessage.put("status", "recipe with id: " + recipeId + " was successfully deleted.");
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }
}
```

We're almost done, Let’s start by fixing the error message issue. Spring Boot provides some properties with which we can
add the exception message, exception class, or even a stack trace as part of the response payload. We can disable to
current error messages by setting the `server.error.include-stacktrace=NEVER`.

Now let's test all these endpoints.

### Functionality(endpoints)

| Endpoint                                 | Functionality                               | Access  |
|------------------------------------------|---------------------------------------------|---------|
| POST /auth/users/register                | Registers a user                            | PUBLIC  |
| POST /auth/users/login                   | Logs a user in                              | PUBLIC  |
| GET /api/categories                      | Lists all categories                        | PRIVATE |
| GET /api/categories/{id}                 | Gets a single category with the supplied id | PRIVATE |
| POST /api/categories                     | Creates a new category                      | PRIVATE |
| PUT /api/categories/{id}                 | Updates a category with the supplied id     | PRIVATE |
| DELETE /api/categories/{id}              | Deletes a category with the supplied id     | PRIVATE |
| POST /api/categories/{id}/recipes        | Creates a new recipe in the given category  | PRIVATE |
| GET /api/categories/{id}/recipes         | List all recipes in the given category      | PRIVATE |
| PUT /api/categories/{id}/recipes/{id}    | Updates a recipe in the given category      | PRIVATE |
| DELETE /api/categories/{id}/recipes/{id} | Deletes a recipe in the given category      | PRIVATE |

### Build the following endpoints

| Endpoint                     | Functionality                            | Access  |
|------------------------------|------------------------------------------|---------|
| POST /auth/api/profile       | Create a user profile current user       | PRIVATE |
| UPDATE /auth/api/profile     | Update the user profile for current user | PRIVATE |
| GET /api/public-recipes/     | GET public recipes                       | PUBLIC  |
| GET /api/public-recipes/{id} | GET a single public recipe               | PUBLIC  |

## YOU DID IT! :rocket:

<p>
<img src="https://media.giphy.com/media/MCjDs8dcSDcKUNePT0/source.gif">
</p>
