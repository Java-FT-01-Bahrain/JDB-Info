# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) MongoDB Lab: Practicing CRUD Queries

## Lab Overview

In this lab, you will practice working with MongoDB collections and queries using **MongoDB Atlas** and **MongoDB Compass**. The focus of this lab is to help you become comfortable writing and understanding MongoDB **CRUD (Create, Read, Update, Delete)** queries.

You will work with a collection that stores information about animals in a shelter. You will insert data, query it in different ways, and remove documents based on specific conditions.

⚠️ This lab provides **instructions only**. You are expected to write and execute the queries yourself.

---

## Learning Objectives

By completing this lab, you will be able to:

- Create and populate a MongoDB collection with documents
- Write READ queries using filters, comparison operators, and logical operators
- Retrieve documents by `_id`
- Apply sorting and limiting to query results
- Safely delete documents based on given conditions
- Understand how CRUD operations are applied in real-world scenarios

---

## Lab Setup

1. Log in to **MongoDB Atlas**
2. Connect to your cluster using **MongoDB Compass**
3. Create or select a database named:

```
animalShelterDB
```

4. Create a collection named:
```
pets
```


---

## Part 1: Insert Documents (Create)

Insert **at least 6 documents** into the `pets` collection.  
Each document should include the following fields:

- `name` (String)
- `type` (String, e.g., dog, cat, rabbit)
- `age` (Number)
- `description` (String)
- `adopted` (Boolean)

### Sample Data Guidelines (Do NOT copy directly)

Use **different values**, such as:

- Names like: Luna, Rocky, Milo, Daisy, Coco, Bruno
- Ages between: 1 and 10
- Descriptions such as:
  - playful and energetic
  - calm and friendly
  - shy but affectionate
  - very active and curious

Ensure that:
- Some pets are marked as adopted
- Some pets are not adopted

---

## Part 2: READ Operations (Querying Data)

Write queries to perform the following tasks:

1. Retrieve **all pets** in the collection
2. Find all pets of a specific type (for example, only dogs)
3. Find pets that are **older than 5 years**
4. Find pets that are **not adopted**
5. Retrieve pets that are:
   - Older than 3 years **AND**
   - Not adopted
6. Retrieve pets that are:
   - Either very young (age less than 2) **OR**
   - Already adopted
7. Sort pets by age:
   - From youngest to oldest
   - From oldest to youngest
8. Limit the result to show only the **first 3 pets**
9. Retrieve a single pet using its `_id`

---

## Part 3: UPDATE Operations (Modify Data)

In this section, you will practice updating existing documents in the `pets` collection. Update operations allow you to change specific fields without replacing the entire document.

1. Select one pet by its name and update the `description` field to reflect a change in the pet’s behavior or condition.

2. Identify a pet that is currently not adopted and update the `adopted` field so that the pet is now marked as adopted.

3. Find all pets of a specific type (for example, cats or dogs) and add a new field called `vaccinated` with the value set to `true`.

4. Choose a single pet and increase its `age` value by 1 to represent the passage of time.

5. Find all pets older than 6 years and update their `description` to indicate that they require special care.

6. Select one pet document and remove the `description` field from that document.

7. Find all pets that are not adopted and add a new field called `priorityAdoption` with its value set to `true`.

8. After completing the update scenarios, run READ queries to verify that only the intended documents were updated and no unintended changes occurred.

---

### Notes on UPDATE Operations

- Always use clear and specific filter conditions
- Avoid updating entire documents unless necessary
- Verify results immediately after running update queries

---

## Part 4: DELETE Scenarios

⚠️ Read each scenario carefully before running delete queries.

Write delete queries for the following scenarios:

1. Delete **one pet** by its name
2. Delete **all pets** that are marked as adopted
3. Delete pets that are **older than 8 years**
4. Delete all documents in the `pets` collection (do not drop the collection)

> Always double-check your filter condition before executing a delete query.

---

## Lab Rules and Notes

- Do not store any personal or sensitive information
- Do not run delete queries without understanding the condition
- Execute queries step by step and observe the results in MongoDB Compass
- Ask questions if a query result is not what you expect

---

## Submission Instructions

- Create a single text or markdown file for your lab submission.
- Copy **all MongoDB queries** you executed in MongoDB Compass, including CREATE, READ, UPDATE, and DELETE queries.
- Paste the queries into the file in the order you executed them.
- Do **not** include query results or screenshots unless explicitly instructed.
- Review your file to ensure all queries are readable and properly formatted.
- Submit the file according to the instructions provided during the class session.

⚠️ Ensure that the submitted file contains **only your queries** and does not include any sensitive or personal data.


---

## Key Takeaways

- MongoDB collections store flexible, document-based data
- READ queries can be customized using filters, operators, and logical conditions
- Sorting and limiting results helps control query output
- DELETE operations are permanent and must be used carefully
- Practicing queries builds confidence and query accuracy

---

## Conclusion

This lab provided hands-on experience with MongoDB CRUD queries using a realistic dataset. By practicing CREATE, READ, and DELETE operations, you strengthened your understanding of how MongoDB manages data and how queries are used in real-world applications. Mastering these fundamentals prepares you for integrating MongoDB into backend applications.

---




