---
title: Intro to SQL
type: lesson
duration: "1:55"
creator:
    name: Jay Nappy & Isha Arora
---

# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Intro to SQL

### Learning Objectives

*After this lesson, students will be able to:*

- Create a database table.
- Insert, retrieve, update, and delete a row (or rows) of a database table.

### Lesson Overview

| TIMING  | TYPE  | TOPIC  |
|:-:|---|---|
| 10 min | Opening         | Discuss lesson objectives |
| 15 min | Guided Practice | Installing Postgres |
| 15 min | Guided Practice | Connect and Create a Database |
| 15 min | Guided Practice | Create a Table and Insert Data |
| 15 min | Independent Practice | Insert Data |
| 15 min | Guided Practice | What's in Our Database? |
| 10 min | Guided Practice | Updates to Our Database |
| 15 min | Independent Practice | SQL Challenges |
| 5 min  | Conclusion      | Review / Recap |

## Overview (5 min)

**Relational databases** are a way of storing and retrieving data on disk (or many disks). They provide more powerful storage and retrieval capabilities than simple files and are used in banking, eCommerce, healthcare, and all kinds of web and enterprise applications. 

Knowing the basics of how they work and how to use them (with SQL) allows us to build “full-stack” applications that include business logic (Javascript back-end), a UI (HTML/CSS/JS front-end), plus permanent storage in a database.

--------

## Intro: What Are Databases? (15 min)

A **database** is a place where information is stored in a hard drive (or distributed across multiple hard drives) somewhere on a computer. Much as we've been creating and storing data here and there, a database represents a collection of individual pieces of data stored in a highly structured and searchable way; they represent a model of reality, which is why we call them models in MVC.

Inside a database, we carry out basic actions such as **c**reating, **r**eading, **u**pdating, and **d**eleting data (or CRUD)!

In modern web development, there are different categories of databases: relational (aka, SQL), NoSQL, key-value, and more. We're focusing on relational databases here.

SQL stands for **Structured Query Language**, and it's a language used to manage and get information from what are considered "relational" databases (we'll talk more about SQL in the next lesson).

We call these databases "relational" because different models (or pieces of data) can be linked to other models — i.e., "related." 

Relational databases store data in a **table**; think of it like a spreadsheet. The table holds all of the data for one model, while the columns define the model's attributes; we often call columns "attributes" or "fields." A row is an instance (remember instantiation!); think of it as a unique copy of the blueprint that is our model (often called a "record").

![relational db](https://cloud.githubusercontent.com/assets/25366/8589355/2646c588-25ca-11e5-9f2d-3d3afe8b7817.png)

-----

## We Do: Let's Draw some tables

In this exercise, we'll explain the basic idea around taking a real-world data problem and organizing a solution in tables and relationships between tables.

Let's say we're building an app for a library. Consider what some tables would look like (e.g., what information or attributes would be associated with each table).

> **Instructor Note**: Maybe call on random students to come up to the board to draw different tables with rows and columns. If we secretly guide them toward building individual models that should be related, we can naturally draw connections between those models to show relationships.

- What would the table for a book look like?
- What would the table for an author look like?
- What would the table for a category look like?

This is when we start seeing relationships form. This is great! You can imagine duplicate pieces of data being stored naturally, especially when an author has multiple books (for instance). That's a waste of space! We'll later talk about how we can connect these tables so we don't have tons of duplicate data all over the place.

------

## Intro: We Know About Databases, But What Is SQL? (10 min)

Let's review: At its simplest, a **relational database** is a mechanism for storing and retrieving data in a tabular form. But, how do we interact with our database (inserting, updating, retrieving, and deleting data)? That's where SQL comes in!

### What Is SQL?

SQL stands for **Structured Query Language**, and it's a language that's universally used and adapted to interact with relational databases. When you use a SQL client and connect to a relational database that contains tables with data, the scope of what you can do with SQL commands includes:

- Inserting data.
- Querying or retrieving data.
- Updating or deleting data.
- Creating new tables and entire databases.
- Controlling permissions of who can access to our data.

Note that all of these actions depend on what the database administrator sets for user permissions. If you're an analyst, for example, you'll only have access to retrieving company data. But, as a developer, you could access all of these commands and be in charge of setting the database permissions for your web or mobile application.

### Why Is SQL Important?

A database is just a repository for storing data, and you need to use systems to dictate how that data will be stored and how clients will interact with it. We call these systems **database management systems**, and they come in _many_ forms:

- MySQL
- SQLite
- PostgreSQL (what we'll be using!)

All of these management systems use SQL (or some adaptation of it) as a language for managing data in the system.

------

## Installing Postgres (15 min)

2.  Let's install Postgres:
	1.  [Postgres installer](https://www.enterprisedb.com/downloads/postgres-postgresql-downloads)
	2.  Follow the steps to install Postgres
	3.  Make sure to [install `pgAdmin` for Mac](https://www.pgadmin.org/download/pgadmin-4-macos/)
	4.  Remember the username and password you set while installing Postgres. This will come in handy later.
	4.  After installation is complete run pgAdmin from your Mac. It'll open in a browser. The username/password will be what you initially set while installing Postgres.

------

## Code-Along: Connect and Create a Database (15 min)


### Connecting to Postgres

There are 2 ways to connect to the Postgres database. Throughout the lesson I will show you both.

We can either use [pgAdmin](https://www.pgadmin.org/) to access our database. `pgAdmin` gives users a GUI for Postgres. Anything you can do by running any query on Postgres, you can do it using this tool. 

You will be asked to put in the password to connect to Postgres. Once you do, you will see a database `postgres` is already created.

**OR**

We can use `psql` prompt from any directory in the Terminal to connect to Postgres. But before we do that we need to add Postgres to our PATH environment variable. The way to do that is [here](https://www.architectryan.com/2018/03/17/add-to-the-path-on-windows-10/).

After PATH is updated. Open Terminal and type in,

```sql
psql -U <username>
```
>This will be the username you had set while installing Postgres.

You will be asked to put in the password. You should see something like:

```sql
postgres=# 
```

Great! Now, you can execute PSQL commands

### Creating a DB

Let's create a database! 

We will create a new database called `generalassembly` by right clicking on the `Databases` option.

**OR**

If you want to create a db from inside of `psql`, here is the query for it

```psql
CREATE DATABASE generalassembly;
```

The semicolon is important! Be sure to always end your SQL queries and commands with semicolons.

### Dropping a DB

When you drop a database, it is gone forever from the memory so make sure to use it with caution.

To remove a database through `pgAdmin` right-click on the database and drop it 

**OR**

In the `psql` prompt run query, `DROP DATABASE <databasename>;`. 

Now in the `psql` prompt let's _use_ that database we just created:

```psql
postgres=# \c generalassembly
You are now connected to database "generalassembly" as user "your_user_name".
generalassembly=#
```

You can also connect directly to your database once it is created in postgres.

```bash
$ psql generalassembly
generalassembly=#
```

To exit from postgres shell either type `exit`, `ctrl+d`, or `\ + q`.

## SQL Style Guide 

1. Fields should *always* be lower case.
2. SQL _keywords_ should always be upper case. 
2. Never name a field `id`; always correlate it to the table name (e.g., `student_id`).
3. Always check your company's style guide or follow the convention. Never create your own style.

These are widely accepted naming conventions. Postgres will not throw errors if any or all of these are not followed. You can read more about it [here](http://www.sqlstyle.guide).

------

## Code-Along: Create a Student Table and Insert Data (15 min)

Now that we have a database, let's create a table. (Think of this like: "Hey, now that we have a workbook/worksheet, let's block off these cells with a border and labels to show it's a unique set of values.")

Let's create a table for students that collects information about:

- An `student_id` (primary key).
- The student's name (cannot be left blank).
- Their age (cannot be left blank).
- Their mobile.

In `pgAdmin` right click on `Tables` to create a new table `students`. 

![](./images/create_table.png)

Click on the Columns tab to add the columns one by one

![](./images/add_columns.png)

Under SQL tab you can see the SQL query pgAdmin will run to create your table.

![](./images/create_table_query.png)
> Note how similar it is to the create query given below

**OR**

Here's how you create a table in using SQL:

```sql
CREATE TABLE students (
  student_id SERIAL PRIMARY KEY,
  name TEXT NOT NULL,
  age INT NOT NULL,
  mobile VARCHAR(50)
);
```

In the `psql` prompt, if you copy and paste that in, it will look like:

```psql
generalassembly=# CREATE TABLE students (
generalassembly(#  student_id  SERIAL   PRIMARY KEY,
generalassembly(#  name        TEXT                NOT NULL,
generalassembly(#  age         INT                 NOT NULL,
generalassembly(#  mobile     VARCHAR(50)
generalassembly(#  );
CREATE TABLE
```

Let's break this query down one by one:

```psql
generalassembly=# CREATE TABLE students (
```

This starts our table creation, it tells PostgreSQL to create a table named `students`...

```psql
generalassembly(#  student_id SERIAL PRIMARY KEY,
generalassembly(#  name 	  TEXT 		        NOT NULL,
generalassembly(#  age        INT               NOT NULL,
```

...then, each successive line denotes a new column we're going to create for this table, what the column will be called, the data type, whether or not it's a primary key, and if the database (when data is added) can allow data without missing values. 

In this case, `student_id ` is our primary key which will be serially generated by our database. A **Primary Key** in relational databases help uniquely identify records in a table. Every table must have a primary key and it cannot be null. 

We're not allowing `name` and `age` to remain blank, this is called as not-null constraint, but we're OK with `mobile` being blank.

You can use `\d+ <tablename>` to describe a table.

### Insert

We'll insert five students: Jack, Jill, John, Jackie, and Slagathorn. The syntax is as follows:

```psql
INSERT INTO table_name VALUES (value1, value2, value3,...);
```

Let's do it for Jack:

```sql
INSERT INTO students VALUES (DEFAULT, 'Jack Sparrow', 28, '999-999-9999');
```

In `pgAdmin` you will righ-click on the table name `students` and click on `Query Tool...`. This will open a tab on the window where you can run the above query.

![](./images/insert_query.png)

*OR**


In `psql`, that will look like:

```psql
generalassembly=# INSERT INTO students VALUES (DEFAULT, 'Jack Sparrow', 28, '999-999-9999');
INSERT 0 1
```

We can change *part* of the columns' values without changing *all* of them. Let's say that we don't want to give someone a `student_id` and a `mobile`, so we'll have to make a small change in the insert query.

```
INSERT INTO students (name, age) VALUES ('Captain Barbossa', 38);
```

------

## Independent Practice: Insert Data (15 min)

Now, try it for the other students and pay attention to the order of Jack's parameters and the single quotes; they both matter.

- Jill's full name is Jilly Cakes; she's 30 years old and can be reached at 910-111-1111.
- John's full name is Johnny Bananas; he's 25 years old and can be reached at 678-111-1234.
- Jackie's full name is Jackie Lackie; she's 101 years old and can be reached at 910-456-7890.
- Slagathorn's full name is Slaggy McRaggy; he's 28 years old and prefers not to give his number.

You should come up with:

```sql
INSERT INTO students VALUES (DEFAULT, 'Jilly Cakes', 30, '910-111-1111');
INSERT INTO students VALUES (DEFAULT, 'Johnny Bananas', 25, '678-111-1234');
INSERT INTO students VALUES (DEFAULT, 'Jackie Lackie', 101, '910-456-7890');
INSERT INTO students VALUES (DEFAULT, 'Slaggy McRaggy', 28);
```

In PSQL, this should look like:

```psql
generalassembly=# INSERT INTO students VALUES (DEFAULT, 'Jilly Cakes', 30, '123 Webdev Dr. Boston, MA');
INSERT 0 1
generalassembly=# INSERT INTO students VALUES (DEFAULT, 'Johnny Bananas', 25, '555 Five St, Fivetowns, NY');
INSERT 0 1
generalassembly=# INSERT INTO students VALUES (DEFAULT, 'Jackie Lackie', 101, '2 OldForThis Ct, Fivetowns, NY');
INSERT 0 1
generalassembly=# INSERT INTO students VALUES (DEFAULT, 'Slaggy McRaggy', 28);
INSERT 0 1
```

-----

## Code-Along: What's in Our Database? (15 min)

Now that we have this data saved, we're going to need to access it at some point, right? We're going to want to _select_ particular data points in our data set, provided certain conditions. The PostgreSQL `SELECT` statement is used to fetch data from a database table that returns data in the form of a result table. These result tables are called **result sets**, and their syntax is just what you would have guessed:

```psql
SELECT column1, column2, column3 FROM table_name;
```

We can pass in the columns we want to look at (such as above), or even get all of our table records:

```psql
SELECT * FROM table_name;
```

In `pgAdmin` run the select query in the query tool,

![](./images/select_query.png)

**OR**

For example, we can get all of the records back:

```psql
generalassembly=# SELECT * FROM students;
 student_id |       name       | age |    mobile    
------------+------------------+-----+--------------
          1 | Jack Sparrow     |  28 | 999-999-9999
          2 | Captain Barbossa |  38 | 
          3 | Jilly Cakes      |  30 | 910-111-1111
          4 | Johnny Bananas   |  25 | 678-111-1234
          5 | Jackie Lackie    | 101 | 910-456-7890
          6 | Slaggy McRaggy   |  28 | 
(6 rows)
```

We can get just the names and ages of our students:

```psql
generalassembly=# SELECT name, age FROM students;
       name       | age 
------------------+-----
 Jack Sparrow     |  28
 Captain Barbossa |  38
 Jilly Cakes      |  30
 Johnny Bananas   |  25
 Jackie Lackie    | 101
 Slaggy McRaggy   |  28
(6 rows)
```

From this point on you will continue to use query tool in `pgAdmin` to get better practice on SQL query syntax.


### Getting More Specific: WHERE Clause

As with Java or JavaScript, all of our comparison and Boolean operators can help us select more specific data.

Say I want the names of all of the students who aren't dinosaurs:

```psql
generalassembly=# SELECT name FROM students WHERE age < 38;
      name
----------------
 Jack Sparrow
 Jilly Cakes
 Johnny Bananas
 Slaggy McRaggy
(4 rows)
```

Done! 

How about the names of students ordered by age?

```psql
generalassembly=# SELECT name, age FROM students ORDER BY age;
       name       | age 
------------------+-----
 Johnny Bananas   |  25
 Jack Sparrow     |  28
 Slaggy McRaggy   |  28
 Jilly Cakes      |  30
 Captain Barbossa |  38
 Jackie Lackie    | 101
(6 rows)
```

Done!

How about reversed?

```psql
generalassembly=# SELECT name, age FROM students ORDER BY age DESC;
       name       | age 
------------------+-----
 Jackie Lackie    | 101
 Captain Barbossa |  38
 Jilly Cakes      |  30
 Jack Sparrow     |  28
 Slaggy McRaggy   |  28
 Johnny Bananas   |  25
(6 rows)
```

Done!

How about those students who live in area code `910`?

```psql
generalassembly=# SELECT * FROM students WHERE mobile LIKE '%Fivetowns%';
 student_id |     name      | age |    mobile    
------------+---------------+-----+--------------
          3 | Jilly Cakes   |  30 | 910-111-1111
          5 | Jackie Lackie | 101 | 910-456-7890
(2 rows)
```

We can find strings within strings, too!

------

## Code-Along: Updates to Our Database (10 min)

OK, there are some mistakes we've made to our database, but that's cool, because we can update it or delete information we don't like. Let's start by adding one more student:

```psql
generalassembly=# INSERT INTO students VALUES (DEFAULT, 'Miss Take', 500, 'asdfasdfasdf');
INSERT 0 1
```

### Update 

But, oh no, we messed them up! Miss Take cannot possbily have the number asdfasdfasdf; her number instead is 000-000-0000 . Let's fix it:  

```psql
UPDATE students
SET mobile = '000-000-0000'
WHERE mobile = 'asdfasdfasdf';
UPDATE 1

generalassembly=# SELECT * FROM students WHERE name = 'Miss Take';
  student_id |   name    | age |    mobile    
------------+-----------+-----+--------------
          7 | Miss Take | 500 | 000-000-0000
(1 row)
```

### Delete

But wait, she just canceled. No big deal!

```psql
generalassembly=# DELETE FROM students where name = 'Miss Take';
DELETE 1

generalassembly=# SELECT * FROM students;
  student_id |       name       | age |    mobile    
------------+------------------+-----+--------------
          1 | Jack Sparrow     |  28 | 999-999-9999
          2 | Captain Barbossa |  38 | 
          3 | Jilly Cakes      |  30 | 910-111-1111
          4 | Johnny Bananas   |  25 | 678-111-1234
          5 | Jackie Lackie    | 101 | 910-456-7890
          6 | Slaggy McRaggy   |  28 | 
(6 rows)
```

------

## Independent Practice: SQL Challenges (15 min)

There's _no way_ you're going to remember the exact syntax of everything we just did, but let's practice something you should have been doing since Week 1: finding and reading documentation. Check out [this PostgreSQL tutorial](http://www.tutorialspoint.com/postgresql/) and, using the same database and data table of users, complete as many of these SQL challenges as possible in the next 15 minutes:

- Insert five more students:
  - Nancy Gong is 40 years old and can be reached at 789-786-6383.
  - Laura Rossi is 70 years old and doesn't want to share her number
  - David Daniele is 28 years old and the number he gave is 876-345-6785.
  - Greg Fitzgerald is 25 years old and did not give any mobile.
  - Randi Fitz is 28 years old and number listed is 123-090-7648.

- Randi wants her mobile to be corrected to 123-090-7646.
- Get a list of names and mobiles for students who are older than 30, then order them by their names alphabetically.
- Get a list of students whose first name begins with the letter "J."
- Get a list of student names whose numbers are not listed.

-----

## Conclusion (5 min)

#### Common PostgreSQL Commands

Here is a list of some common PostgreSQL commands you may need:

- `\c` - Connect to database
- `\l`
- `\d`
- `\d+`
- `\q`
- `\h` - Help

To wrap up, answer these questions:

- How does SQL relate to relational databases?
- What kinds of Boolean and conditional operators can we use in SQL?

