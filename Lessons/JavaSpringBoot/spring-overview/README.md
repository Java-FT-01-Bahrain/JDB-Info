| Title | Type | Duration | Author |
| -- | -- | -- | -- |
| Spring Overview | Lesson | 1:30 | Isha Arora |

# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Spring Overview

## Learning Objectives

*After this lesson, students will be able to:*

- Explain why developers use frameworks when building applications.
- Explain what makes the Spring framework so useful.
- Define inversion of control, dependency injection, and aspect oriented programming.

## Lesson Guide

| TIMING  | TYPE  | TOPIC  |
|:-:|---|---|
| 5 min  | Opening    | Lesson Objectives |
| 15 min | Exercise   | What Is a Framework?      |
| 15 min | Lecture    | What Is Spring?           |
| 30 min | Exercise   | Spring Characteristics    |
| 5 min  | Lecture    | Spring IoC Container      |
| 10 min | Lecture    | Spring Config             |
| 5 min  | Conclusion | Review/Recap            |

## Opening (5 min)

Developers use frameworks to make building applications easier and more efficient. One of the most popular Java frameworks is called [Spring](https://spring.io/), and we'll be using it and its modules throughout the course.

Let's find out what the big deal is!

----

## What Is a Framework? (15 min)

Before we get into the details of Spring, we should explore the concept of a **web framework**.

To understand why these frameworks are so useful, let's play a game:

Your ultimate goal is to build a house. It should have two stories, a two-car garage, three bedrooms, and two bathrooms. With a partner, I want you to come up with a plan for doing this. You might want to think about things such as...

- **The blueprint**: How should the house be laid out?
- **Materials**: What do we need to build it?
- **Labor**: Who will actually do all of the heavy lifting?
- **Cost**: Where will our money be going and how much should we budget for?

I'm going to set a timer for three minutes. With your partner, get as far with your plan as you can. Ready, set, go!

So, how did it go? Chances are you didn't get to _everything_ you needed to build a house. It's time-consuming and even overwhelming to think through. And hasn't it been done before many times? Why should we bother starting from scratch when there must be some approach (perhaps a *framework*) for doing this?

The same logic applies to web frameworks. Almost every programming language relies on some framework to make it easy to start writing applications quickly. Ruby has Rails, Python has Django, JavaScript has Angular... and Java has Spring.

> **Sidebar**: You have probably heard of "libraries" for programming languages that are used in a similar way (to give developers shortcuts and structure for doing things). There aren't precise definitions for frameworks and libraries (e.g., React), but we won't get into that existential debate here. The long and short of it: Frameworks and libraries are awesome tools that you'll come to know and love.

---

## What Is Spring? (15 min)

Spring was released in 2003 and has since grown to be one of the most (if not *the* most) popular Java development frameworks. Here's the official definition:

> "Spring Framework is a powerful, lightweight application development framework used for Enterprise Java (JEE)."

What does that actually mean? Let's break it down piece by piece:

- "Powerful, lightweight:" This just means that it uses as little hardware and software resources as possible to run. It also uses less memory than other frameworks. Despite that, it's very powerful; it's fast and can handle a lot of tasks. Sounds pretty great, right?

- "Application development framework:" We just learned this!

- "Enterprise Java (EE):" This refers to the enterprise (aka, business) edition of the Java development platform. Think of it as all of the features and capabilities of the code you'll be writing.

### What Makes It So Great?

There are many things that make Spring so powerful and useful for Java developers. 

As with many web frameworks, Spring minimizes the boilerplate code that developers have to write. You already know how much code it takes to print "Hello, World" in Java, plus everything you need to build even basic classes and methods. Spring reduces the time you have to spend dealing with this.

Another cool thing about Spring is the ecosystem around it. Spring can be used on its own or with one of the many modules that are built on top of it (which is what we'll do in this course):

- **Spring Boot**: Quickly and easily getting your applications running.
- **Spring Data**: Dealing with data.
- **Spring Security**: Keeping hackers (and other undesirable stuff) out of your applications.
- **Spring MVC**: A handy framework for developing web apps.

It also easily integrates with other frameworks, including Hibernate for data.

But, in this lesson, we'll spend the most time on the following characteristics. They're the backbone of how Spring operates:

- It's based on the concept of **inversion of control** (IoC).
- Modules are loosely coupled due to **dependency injection**.
- It offers the ability to add functionality to objects declaratively using **aspect-oriented programming**.
- Java **POJOs** (plain old Java objects) help with lightweight development.

## Characteristics of Spring (20 min)

Let's dive into each of these characteristics.

To start, I'll give you a quick explanation of what these concepts mean. After that, I'll turn it over to *you* to tell *me* what's so important about each of them.

### Inversion of Control

According to Martin Fowler, who popularized the concept, inversion of control means that the control flow of a program is switched: Instead of the programmer controlling the flow of a program, the external sources (framework, services, other components) take control of it. Does that sound like the robots are taking over? Don't worry — it's actually not that crazy!

![](https://image.slidesharecdn.com/solidandiocprinciples-160330085218/95/solid-and-ioc-principles-17-638.jpg?cb=1459327949)
<sub>[Source](https://www.slideshare.net/eleksdev/solid-and-ioc-principles)

This just means that objects do not create objects, but rather that objects are created by other resources. In turn, this makes our objects **loosely coupled** — that is, the objects are not tightly dependent on each other.

### Dependency Injection

In large applications, blocks of code (most often separate classes) can become "coupled," or tightly dependent on each other. Updating one class will probably affect your other classes.

Dependency injection is a subset of IOC. When we "inject" (or pass in) an already-instantiated instance of a dependency to our current class, we remove the need for our class to know low-level details about the dependency. All our class knows is that it received an object and that it can call some methods on that object.

> What do we mean by "dependencies"? They're all of the things you add to your application, beyond the regular code, to make it ready for use: databases, messaging systems, logs, etc. You "depend" on them to make the program work properly.

<details>

<summary>Why dependency injection is great:</summary> 

We'll see a common example of this in our MVC controller classes. These classes will need to access data from the database. Rather than tell the controller itself how to do this, we'll "inject" an instance of another class that knows how. The controller won't need to know how to create that class instance or any details about the database connection. All the controller will need to know is that the class that is injected has a method called `findAll()` that will return some data.

</details>

### Aspect-Oriented Programming

Just as object-oriented programming (OOP) is based on the concept of an `Object`, aspect-oriented programming (AOP) is based on the concept of an `Aspect`. So, what does that mean?

Aspects enable the encapsulation of things such as transaction management, logging, and security that cut across multiple types and objects. These are often termed **cross-cutting concerns** in AOP literature. Aspects can be reused in multiple locations.

AOP is a key component of Spring. While you don't *need* to use AOP if you don't want to, AOP complements Spring IoC to provide a very capable middleware solution. 

<details>

<summary>Why AOP is great:</summary> 

Let's assume that you are working on an existing application that contains 50 classes. Your manager comes to you with a request to log all of the exceptions that database throws in your app. Now, let's assume there are 10 classes that are part of the data access layer; you get right on it and start adding the logs in all catch blocks. However, your manager comes to you again and asks you to add logs before each transaction starts and after each transaction ends. You start adding more code in all of the required places. Your manager, forgetful as they are, then asks you to update the previous logs with additional information and log all the errors that occur due to data validation.
	
Are you starting to see the problem here? First of all, for every method used for data access, we now have an excess of code to handle the logging that has nothing to do with actual logic. All of this greatly reduces the readability of your code. Also, if you update your logs, you'll have to change the code in all of those places. This affects maintainability, so it's certainly not a feasible option.

</details>

### POJOs and Bean Scope

**Beans** are POJOs (plain old Java objects).

A **bean scope** refers to the life cycle of a bean (how many instances are created). A bean's scope in Spring is **singleton** by default. 

> **Knowledge Check**: Can someone recap what a singleton is and what it might mean for our bean friends?

It means that only one instance of the bean will be created throughout the application. All requests for the bean will have shared reference to the same object of the bean. Singleton beans are called as **stateless beans**; the only state they have is shared. 

While singleton is the default, you can also specify **prototype** scope. Prototype creates a new instance for every request. Prototype scope is mainly used to keep track of stateful data, or beans that carry instance variables. Think of it this way: Every time a request is made, the `new` operator is used to create an instance of that bean.

Less common scopes include:
- **Request**: Only for web apps; scoped to an HTTP request.
- **Session**: Only for web apps; scoped to an HTTP session.
- **Global session**: Only for web apps; scoped to a global HTTP session.

## Spring IoC Container (5 min)

The Spring IoC container is at the core of Spring framework. It creates and manages objects, injects objects' dependencies, wires them together, and configures them. 

The container gets its instructions about the beans and how to configure them by reading configuration metadata, which we'll discuss next. 

Spring provides two types of containers:

- `BeanFactory` 
- `ApplicationContext`

There aren't too many differences between the two. The primary functions of both are the same, but you can say that `ApplicationContext` is a superset of `BeanFactory`. It adds more enterprise-specific functionality than the `BeanFactory` container. For this reason, `ApplicationContext` is recommended over `BeanFactory`, and we'll be looking at that one in more detail.

## Spring Configuration (10 min)

There are three major methods that are used for configuring Spring applications:

- **XML-based** configuration uses XML to configure beans. It takes a lot of time and is a little harder to understand. It's an old way of doing things, so some legacy apps still use it.
- **Annotation-based** configurations are nothing but special labels added to Java classes. They provide metadata about the class. Annotations minimize XML config and are the latest way of doing things.
- **Java-based** configuration is when no XML is used for any configuration. Everything is done using Java classes.

> **Knowledge Check**: Remember the `@Override` annotation from our Java lessons? Can someone define it? 

`@Override` tells the compiler that this method is overridden so that, during compilation, the compiler verifies if this is `true` and otherwise throws an error. 

> **Knowledge Check**: Take a second and think: How this is helpful in the context of Spring?

Spring scans code for annotations and automatically registers beans in the container we discussed above. Spring does a lot of work in the background. For the remainder of this course, we're going to use Java annotations for all Spring configuration.

## Conclusion (5 min)

Welcome to Spring, your new best friend! 

Before we wrap up the lesson, let's review:

- What is a web framework?
- What is the Spring framework all about?
- What are some of the key characteristics of Spring?

## Resources

- [More About AOP](https://docs.spring.io/spring/docs/2.5.x/reference/aop.html)
- [More About Inversion of Control From the OG Author](https://martinfowler.com/bliki/InversionOfControl.html)
