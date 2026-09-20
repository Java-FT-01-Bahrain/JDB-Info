
# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Create a Todo App Lab

## Opening 

We're going to create a todoApp during this Spring week. You'll continue to add functionality to your app as we learn more about Spring and its modules. 

This will be a fairly open-ended lab. We want you to be able to think through the design decisions and understand the reason why you choose one thing over the other. There's just one main ground rule to follow: Make sure you only work with the technologies/tools we covered during the lesson.

## Deliverables

Push your changes to a GitHub repo.

You will maintain a `README` that will include the following:

- A link to the GitHub repo.
- All of your design decisions. 
- The reasons behind each decision.
- What went right.
- Challenges you faced.
- Which part you enjoyed working on the most.

At the end of this lab, after testing is done, we will hear a **five-minute presentation** from each student. Each student will go through its `README`, talking through each point. Be prepared to answer some questions. 

----

## Exercise

We'll build this back-end app incrementally.

### Step 1 (Spring Boot)

Today, you'll just set up your app using Spring Boot, as we did in the lesson earlier. 

- Use Maven to download and build all of the dependencies.
- Create a REST controller.
- Create a `/hello` endpoint that returns a `'Hello World!'` string.
- Use Postman to test the API.

#### COMMIT MESSAGE: Step 1 - COMPLETED

----

### Step 2 (Spring Profile)

In the next step, you'll use Spring Profile to create a development-specific environment in your app. This is where all of your environment-specific configuration will go.

#### COMMIT MESSAGE: Step 2 - COMPLETED

----

### Step 3 (Spring Data)

Create `Category` and `Item` model. You'll use PostgreSQL as your database and Spring Data to talk to your database.

This step will be done in two parts. 

#### 3(a)

In this step, you'll only create a `Category` model. 

- The `Category` should have at least two fields: `name` and `description`.
-  You'll create full CRUD endpoints for `Category` controller.

Let's test all the endpoints

| Request Type | URL|
|--|--|
| GET | /api/categories/ |
|POST|/api/categories/|
|GET|/api/categories/{categoryId}|
|PUT|/api/categories/{categoryId}|
|DELETE|/api/categories/{categoryId}|

#### COMMIT MESSAGE: Step 3A - COMPLETED

----

#### 3(b)

In the next step, you'll add the `Item` model and map it with `Category`.

- The `Item` table should have at least three columns: `name` and `description`, and `dueDate`.
- The `Category` and `Item` tables should be mapped to each other.
- You'll create full CRUD endpoints for `Item` controller.

| Request Type | URL|
|--|--|
| GET | /api/categories/ |
|POST|/api/categories/|
|GET|/api/categories/{categoryId}|
|PUT|/api/categories/{categoryId}|
|DELETE|/api/categories/{categoryId}|
|GET|/api/categories/{categoryId}/items|
|POST|/api/categories/{categoryId}/items|
|GET|/api/categories/{categoryId}/items/{itemId}|
|PUT|/api/categories/{categoryId}/items/{itemId}|
|DELETE|/api/categories/{categoryId}/items/{itemId}|

#### COMMIT MESSAGE: Step 3B - COMPLETED

----

#### 3(c)
In the next step, you'll add the `User` model and map it with `UserProfile`.
- The `User` table should have at least three columns: `userName` and `emailAddress`, and`password`.
- The `UserProfile` table should have at least three columns: `firstName` and `lastName`, and`profileDescription`.
- The `User` and `UserProfile` tables should be mapped to each other (1:1).
-  Under the UserController, you'll create register endpont and block all the other URLs

TEST: http://localhost:8080/auth/users/register

#### COMMIT MESSAGE: Step 3C - COMPLETED

----

### Step 4 (Spring Security)

You'll now add user authentication and authorization to your existing app, using JSON Web Tokens (JWT) to authenticate your requests. You already have a `User` model for security purposes.
- Again, make sure to use only JWT for authentication.
- Only login API endpoint will only return a JWT in response.
- All other requests will use that token for authentication. 

### Functionality(endpoints)
HTTP Method |Endpoint | Functionality| Access
------------ |------------ | ------------- | ------------- 
GET | /api/categories | Lists all categories | PRIVATE
POST | /api/categories | Creates a new category | PRIVATE
GET | /api/categories/{categoryId} | Gets a single category with the supplied id | PRIVATE
PUT | /api/categories/{categoryId} | Updates a category with the supplied id | PRIVATE
DELETE | /api/categories/{categoryId} | Deletes a category with the supplied id | PRIVATE
GET | /api/categories/{categoryId}/items | List all item in the given category | PRIVATE
POST | /api/categories/{categoryId}/items | Creates a new item in the given category | PRIVATE
GET | /api/categories/{categoryId}/items/{itemId}| Gets a single item with the supplied itemId and CategoryId | PRIVATE
PUT | /api/categories/{categoryId}/items/{itemId}| Updates a item in the given category | PRIVATE
DELETE | /api/categories/{categoryId}/items/{itemId} | Deletes a item in the given category | PRIVATE
POST | /auth/users/register | Registers a user | PUBLIC
POST | /auth/users/login |Logs a user in | PUBLIC

#### COMMIT MESSAGE: Step 4 - COMPLETED
