---
Title: Intro to Relational Databases
Type: Morning Exercise
Duration: "1:00"
Author:
    Name: Alex De Marco
    City: NYC
---

# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Intro to Relational Databases

## Lesson Objectives

By the end of this lesson, students should be able to: 

- Explain the purpose of relational databases.
- Explain how data is organized in relational databases.   
- Describe the relationship between tables, rows, and columns.
- Understand the basics of database normalization.

## Overview (5 min)

**Relational databases** are a way of storing and retrieving data on disk (or many disks). They provide more powerful storage and retrieval capabilities than simple files and are used in banking, eCommerce, healthcare, and all kinds of web and enterprise applications. 

Knowing the basics of how they work and how to use them (with SQL) allows us to build “full-stack” applications that include business logic (Java back-end), a UI (HTML/CSS/JS front-end), plus permanent storage in a database.

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

## We Do: Let's Draw on The Board (20 min)

In this exercise, we'll explain the basic idea around taking a real-world data problem and organizing a solution in tables and relationships between tables.

Let's say we're building an app for a library. Consider what some tables would look like (e.g., what information or attributes would be associated with each table).

> **Instructor Note**: Maybe call on random students to come up to the board to draw different tables with rows and columns. If we secretly guide them toward building individual models that should be related, we can naturally draw connections between those models to show relationships.

- What would the table for a book look like?
- What would the table for an author look like?
- What would the table for a category look like?

This is when we start seeing relationships form. This is great! You can imagine duplicate pieces of data being stored naturally, especially when an author has multiple books (for instance). That's a waste of space! We'll later talk about how we can connect these tables so we don't have tons of duplicate data all over the place.

------

## Relational Databases vs. Spreadsheets (15 min) 

In spreadsheets, you'll often see a lot of duplicated data. Consider this example for a [tire store's inventory](https://drive.google.com/file/d/1Zhm-Kzv8LK0ihKgJihaGnhgEYS44lzn4/view?usp=sharing). 

Notice how the word `Pirelli` is listed 35 times? If you spelled it wrong and wanted to correct the spelling, you'd need to do a search and replace for each of the 35 rows. 

Also notice that model names seem to be unique for each vendor. If you were adding new rows to your spreadsheet, it might get tricky to avoid entering models for the wrong vendor.

Finally, notice how you'd want to sum up the `Quantity in Stock` to find out how many tires you have in your inventory. You would never want to sum up aspect ratios; that just doesn't make sense.  

The process of de-duplicating data when you design a relational database is called **normalization**. Going the other way is called **denormalization**, or "flattening" your data.

------

## Conclusion (10 min)

Let's recap:

- Why would you use a relational database? 
- Why use a relational database instead of a NoSQL database (assuming NoSQL has been taught in a prior lesson)?
- How would you describe "normalization" to your grandma? Think about the metaphor of storing data like a spreadsheet.



