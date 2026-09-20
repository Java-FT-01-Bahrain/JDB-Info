# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png)  Spring Boot Homework: Build Your First REST API


| Title                                  | Type   | Duration | Author               |
|----------------------------------------|--------|----------|----------------------|
| Build Your First REST API | Homework | 01:00    | Saad Iqbal |

### 🚀 From Your First Spring Boot App to Your First Real API

You’ve now taken your first steps into **Spring Boot**. It’s time to put that knowledge into practice.

For this homework, you will build a small Spring Boot REST API around a theme of your choice. Your goal is not simply to make endpoints that return data—you should start thinking like a backend developer designing an API that another application could actually consume.

---

## 🎯 Learning Goals

By completing this homework, you should become comfortable with:

* Creating and running a Spring Boot application
* Creating REST controllers
* Using different HTTP methods
* Working with path variables and query parameters
* Accepting data through request bodies
* Returning appropriate responses
* Designing meaningful REST endpoints
* Organizing your application logically
* Using Spring Boot configuration and profiles
* Testing your API using Postman, Insomnia, or another API client

---

# Part 1 — Create Your Spring Profile

Before building your API, create your own **Spring profile**.

Your profile should represent the developer behind the API.

For example, you could choose a theme such as:

* 🎮 Gaming Profile
* 🎬 Movie & TV Profile
* ⚽ Sports Profile
* 📚 Book/Learning Profile
* ✈️ Travel Profile
* 🍔 Food & Restaurant Profile
* 🎵 Music Profile
* 💻 Developer Profile
* 🏎️ Car Enthusiast Profile
* 🏋️ Fitness Profile
* 🌍 City Explorer Profile
* ☕ Coffee Shop Profile
* 🐾 Pet/Lifestyle Profile
* 🎨 Creative/Artist Profile

You are encouraged to come up with your **own theme**.

### Your profile should include information such as:

* Your name
* A short introduction
* Your chosen theme
* A few interesting facts related to your theme
* Your favourite item/entity related to that theme
* Something you are currently learning or interested in

For example, your instructor's profile could be represented around software development and teaching:

> **Name:** Saad Iqbal
> **Role:** Senior Lead Instructor & Software Engineer
> **Interests:** Software Engineering, Java, Spring Boot, AI & Technology
> **Current Mission:** Helping developers build practical, job-ready software engineering skills.

Make your profile your own. **There is no single correct answer.**

---

# Part 2 — Build Your REST API

Now build a REST API based on your chosen theme.

Your application must contain **at least 10 REST endpoints**.

The endpoints should perform **different types of operations** and should not simply return the same type of response with different names.

You should demonstrate that you understand how a REST API can expose different functionality.

---

## 🌐 Required REST Endpoints

Your application must include the following types of functionality.

### 1. Welcome / Introduction

Create an endpoint that introduces your application.

It should provide information such as:

* Application name
* Your name
* A short description
* Your chosen theme

---

### 2. Get All Items

Create an endpoint that returns a collection of items related to your application.

For example:

* Movies
* Books
* Games
* Restaurants
* Cars
* Courses
* Songs
* Travel destinations

Choose something appropriate for your theme.

---

### 3. Get One Item

Create an endpoint that allows a client to retrieve **one specific item**.

The item should be identified using information provided in the URL.

---

### 4. Search

Create an endpoint that allows users to search your data.

The client should be able to provide a search value and receive matching results.

Think about how a real application might allow users to search.

---

### 5. Filter

Create an endpoint that allows users to filter your data based on a particular criterion.

Examples could include:

* Category
* Rating
* Location
* Price range
* Difficulty
* Year
* Type
* Status

Choose something meaningful for your application.

---

### 6. Create Something New

Create an endpoint that allows a client to add a new item to your application.

The client should provide the information required to create that item.

---

### 7. Update Something

Create an endpoint that allows an existing item to be updated.

Think about what information a user might reasonably want to change.

---

### 8. Delete Something

Create an endpoint that allows an item to be removed.

The API should identify which item should be deleted.

---

### 9. Statistics / Summary

Create an endpoint that provides some interesting statistics about your data.

For example:

* Total number of items
* Average rating
* Most popular category
* Highest-rated item
* Number of items by category

Be creative.

---

### 10. Your Own Feature ⭐

The final endpoint is completely up to you.

This is your opportunity to add something interesting that makes your API different from everyone else's.

For example:

* Recommendation endpoint
* Random item generator
* "What's popular?" endpoint
* Daily recommendation
* Favourite item
* Trending items
* Compare two items
* Calculate something
* Generate a personalized result
* "Surprise me!" endpoint

**Your endpoint should demonstrate your own thinking.**

---

# ⭐ Challenge Requirements

Your 10 endpoints should demonstrate a variety of REST concepts.

Make sure your API uses an appropriate combination of:

* `GET`
* `POST`
* `PUT` or `PATCH`
* `DELETE`
* Path variables
* Query parameters
* Request bodies

Don't create 10 endpoints that all work in exactly the same way.

The purpose of this homework is to start understanding **how APIs are designed**, not just how to create URLs.

---

# 🧠 Think Like an API Developer

Before creating each endpoint, ask yourself:

> "If another developer were using my API, what would they actually need this endpoint for?"

Your API should tell a small story.

For example, if you build a **Movie API**, a user might:

1. Discover available movies
2. Search for a movie
3. Filter movies
4. View movie details
5. Add a movie
6. Update movie information
7. Remove a movie
8. See statistics
9. Get recommendations
10. Get a personalized suggestion

That is much more meaningful than simply creating ten unrelated endpoints.

---

# 📦 What to Submit

Submit your complete Spring Boot project.

Your project should include:

* Spring Boot application
* Your profile
* At least 10 REST endpoints
* Meaningful endpoint names
* Appropriate HTTP methods
* Sample data
* A README describing your API
* Screenshots or evidence showing your endpoints working

You should be able to run the application and demonstrate your API using **Postman or another API client**.

---

# ⭐ Bonus Challenge

If you finish the required functionality and want an additional challenge, add:

**One endpoint that combines multiple pieces of information to provide a useful result to the client.**

Don't worry about implementing a database yet.

At this stage, focus on understanding how **Spring Boot + REST APIs + HTTP** work together.

---

## Final Thought

This is your first step toward building backend applications that other developers can actually use.

Don't focus only on making the endpoints work.

Focus on asking:

> **"Does this API make sense?"**

Good APIs are designed around **useful functionality**, not just URLs.

**Build something you're proud to show.**

