---
title: Relational Models
type: Lesson
duration: "2:00"
author: Isha Arora

---

# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Relational Models

### Learning Objectives

*After this lesson, students will be able to:*

- Create one-to-one, one-to-many, and many-to-many relationships in SQL.
- Determine when each type of relationship is most useful for certain data sets.

### Lesson Overview

| TIMING  | TYPE  | TOPIC  |
|:-:|---|---|
| 10 min | Opening         | Lesson Objectives |
| 30 min | Guided Practice | One-to-One |
| 30 min | Guided Practice | One-to-Many |
| 30 min | Guided Practice | Many-to-Many |
| 10 min | Conclusion      | Review/Recap |

## Introduction (10 min)

As we discussed earlier, what makes SQL databases relational is that each table is "related" to other tables in some way. This model organizes data into one or more tables (or "relations") of columns and rows, using a unique key to identify each row. Rows are also called **records** or **tuples**. Columns are also called **attributes**.

### Relational Mapping

Relationships happen when we start seeing multiple occurrences of duplicative information, or when one object needs to "connect" to another object.

There are three ways in which one table can be linked to another. Each is used in particular scenarios. We'll look at all three and their implementations.

-----

## One-to-One (30 min)

The first way of linking tables is called a **one-to-one** relationship. It's not frequently used, but it's important to know about this option.

In our previous lesson, we were working on a `students` table that had few attributes. Now, let's say that each student should have an address attached.

In real-world applications, `address` is created as a separate table linked to `students`. We say that each student can have only one address and each address is linked to a unique student. In such a case, we say that `Student` and  `Address` have a one-to-one relationship.

### Code-Along

Next, we'll see how this relationship is implemented in SQL.

First, let's create the `address` table with a few necessary attributes:

```sql
CREATE TABLE address (
	address_id SERIAL PRIMARY Key,
	street VARCHAR(150),
	city VARCHAR(50),
	state VARCHAR(10)
);
```
>If you are using pgAdmin, create a new table using the GUI as shown in the previous lesson.

Now, to create a relationship between these two tables, we'll have to add a new column in the `students` table that will eventually store the reference to the `Address` record for that student. 

We'll use `ALTER` to add a new column; the `ALTER` keyword is used to change the description of the existing table. Our query will look like this:

```sql
ALTER TABLE students ADD COLUMN student_address_id INT;
```
>Again, if using pgAdmin, right-click on the Columns to create a new integer column named `student_address_id`.

Let's go over the query. We're adding a new column of the integer data type, named `student_address_id` , to our `students` table.

Once the column is added, we can add a foreign key constraint to this column so that `student_address_id` in `students` table will have the reference to the data in the `address` table.

A **foreign key** is a key used to link two tables together. It's a field (or collection of fields) in one table that refers to the primary key in another table.

In `pgAdmin`, we will right-click on the Constraints option under `students` table to create a new Foreign Key constraint.

![](./images/create_fk.png)

![](./images/create_fk_1.png)

![](./images/create_fk_2.png)
> Don't worry if this query looks little different from the one given below. pgAdmin gives us some extra options.

**OR**

In the `psql` prompt, we'll again `ALTER` the `students` table to create this constraint:

```sql
ALTER TABLE students 
ADD CONSTRAINT fk_student_address 
FOREIGN KEY (student_address_id) 
REFERENCES address (address_id);
```

Let's go over this query. With `ALTER TABLE students`, we're again altering the `students` table to a foreign key on one of its columns. Meanwhile, `fk_student_address` is just the name of the constraint, so we can give any name here. Think of it in terms of a variable in any programming language: It's just a placeholder.

With `FOREIGN KEY (student_address_id) REFERENCES address (address_id)`, we're declaring the column in the `students` table, which will be the foreign key (and what it will reference). As mentioned before, this is the primary key for `address_id` of `address` table.

Let's run `\d students` to have one final look at the table.

```sql
generalassembly=# \d students;
                                             Table "public.students"
       Column       |         Type          | Collation | Nullable |                   Default                    
--------------------+-----------------------+-----------+----------+----------------------------------------------
 student_id         | integer               |           | not null | nextval('students_student_id_seq'::regclass)
 name               | text                  |           | not null | 
 age                | integer               |           | not null | 
 mobile             | character varying(50) |           |          | 
 student_address_id | integer               |           |          | 
Indexes:
    "students_pkey" PRIMARY KEY, btree (student_id)
    "fki_fk_student_address" btree (student_address_id)
Foreign-key constraints:
    "fk_students_address" FOREIGN KEY (student_address_id) REFERENCES address(address_id)
```

### You Do

Add records in the `address` table for each student in the `students` table. Then, update the `students` table to associate the address.

- Jack now wants to make an honest living and has moved to 200 Horton Ave., Lynbrook, NY.
- Captain Barbossa is somewhere out at the sea, refusing to settle down.
- Jill lives at 123 Webdev Dr. Boston, MA.
- John lives at 555 Five St, Fivetowns, NY.
- Jackie lives at 2 OldForThis Ct, Fivetowns, NY.
- Slagathorn prefers not to share their address.

<details>
<summary>Examples</summary>
	
```sql
INSERT INTO address VALUES (DEFAULT, '200 Horton Ave', 'Lynbrook', 'NY');
INSERT INTO address VALUES (DEFAULT, '123 Webdev Dr', 'Boston', 'MA');
INSERT INTO address VALUES (DEFAULT, '555 Five St', 'Fivetowns', 'NY');
INSERT INTO address VALUES (DEFAULT, '2 OldForThis Ct', 'Fivetowns', 'NY');

update students set student_address_id = 1 where name = 'Jack Sparrow';
update students set student_address_id = 2 where student_id = 3;
update students set student_address_id = 3 where student_id = 4;
update students set student_address_id = 4 where student_id = 5;

```
	
</details>

#### A sample one to one query:

```sql
select * from students, address where address_id = student_address_id;
```

![](https://i.imgur.com/5JHdGIT.png)

<br>

------

## One-to-Many (30 min)

The [**one-to-many**](https://www.tech-recipes.com/rx/56738/one-to-one-one-to-many-table-relationships-in-sql-server) relationship is defined as a relationship between two tables where a row from one table can have multiple matching rows in another table. This relationship can be created using the primary key-foreign key relationship.	

<!--- ![](https://www.ntu.edu.sg/home/ehchua/programming/sql/images/ManyToOne.png) --->

You simply put the ID of the "one" resource in the "many," as shown above. This is called a **foreign key**, because it is the key (or ID) of an item in a different table. 

### Code-Along

In our example, we'll now create two new tables: `courses` and `instructors`. Let's first create the `courses` table, which will have at least two attributes: `course_code` and `course_name`.

```sql
CREATE TABLE courses (
	course_id SERIAL PRIMARY KEY,
	course_code VARCHAR(10),
	course_name VARCHAR(100)
);
```

While we're at it, let's put in some data:

```sql
INSERT INTO courses VALUES (DEFAULT, 'SEI', 'Software Engineering Immersive');
INSERT INTO courses VALUES (DEFAULT, 'DSI', 'Data Science Immersive');
```

```sql
generalassembly=# SELECT * FROM courses;
 course_id | course_code |          course_name           
-----------+-------------+--------------------------------
         1 | SEI         | Software Engineering Immersive
         2 | DSI         | Data Science Immersive
(2 rows)

```

We say that each course can be taught by multiple instructors, but only one instructor can teach a course at a time. Therefore, there is a one-to-many relationship between course and instructors.

Now, when we create the `instructors` table, we'll also add **referential integrity** to it, just as we did before:

```sql
CREATE TABLE instructors (
	instructor_id SERIAL PRIMARY KEY, 
	name VARCHAR(255) NOT NULL, 
	email VARCHAR(200) NOT NULL, 
	instructor_course_id INT REFERENCES courses(course_id) NOT NULL DEFAULT (0)
);
```
>In pgAdmin you can create it as part of creating the `instructors` table.

We've created a new column, `instructor_course_id`, which is the foreign key referencing `course_id`, the primary key in the `Courses` table. An instructor should always be teaching a course; that's why we have put a `NOT NULL` constraint. 

But, what if an instructor is not teaching any courses or is on hiatus at the moment? What do we do then? One option is to delete the record, but this is definitely not the best one. The better option is to set the default value to `0`. Now, we'll know the total number of instructors we have and how many are currently teaching.

```psql
generalassembly=# \d instructors
                                                Table "public.instructors"
        Column        |          Type          | Collation | Nullable |                      Default                       
----------------------+------------------------+-----------+----------+----------------------------------------------------
 instructor_id        | integer                |           | not null | nextval('instructors_instructor_id_seq'::regclass)
 name                 | character varying(255) |           | not null | 
 email                | character varying(200) |           | not null | 
 instructor_course_id | integer                |           | not null | 0
Indexes:
    "instructors_pkey" PRIMARY KEY, btree (instructor_id)
Foreign-key constraints:
    "instructors_instructor_course_id_fkey" FOREIGN KEY (instructor_course_id) REFERENCES courses(course_id)

```

### You Do

You have the tables ready and the relationships created, so now you can have some fun with the data!

Add some new instructors to the `instructors` table; try to match them up with the courses they feel comfortable teaching.

Oh! I almost forgot, Captain Barbossa no longer wants to be a student. He is insisting (not so nicely) to be an instructor of a new course: *How to Be a Pirate*. I would suggest we give in to his demands.

![](https://i.pinimg.com/originals/88/54/51/8854517cf5fac7e61bfb6d69eebae510.gif)

------

## Many-to-Many (30 min)

Let's think about a high school situation where students have many courses and courses have many students. 

How do we do this? We _could_ attempt to use the previous approach (wrong) and put ALL of the student IDs associated with each course in each row of the `courses` table AND ALL of the course IDs associated with each student in each row on the `students` table. 

However, we'd just be putting arbitrary amounts of columns in our tables, and the end result is not pretty:

![](https://media.giphy.com/media/N9sfGVpuo4p56/giphy.gif)

Fortunately, the eggheads of computer science yesteryear came up with a beautiful, elegant solution: The **JOIN table**. 

### The JOIN Table

![](https://media.giphy.com/media/jDiUeDQpIkGIM/giphy.gif)

![](https://smehrozalam.files.wordpress.com/2010/06/erd-many-to-many-2.jpg)

We use a JOIN table! It's a table with the IDs of BOTH tables, thus connecting our data across databases! YAY!

A JOIN table might be JUST a JOIN table, meaning it might have nothing but the two IDs, or it might represent something bigger.

For example, the JOIN table above represents a real thing: **enrollment**! Enrollment might have some of its own properties, such as start and stop dates. Other times, the JOIN table might not really represent anything that has a real-life analogy, and it might not need to hold any data besides the IDs. 

### Code-Along

We already have `students` and `courses` tables in our database. We just have to create a JOIN table signifying the enrollment, just as we discussed in the example above. For now, we'll keep things simple and just have a column for `student_id` and a column for `course_id`. 

Let's create our JOIN table. We'll call it `student_course_enrollment`:

```
CREATE TABLE student_course_enrollment (
	enrollment_id SERIAL PRIMARY KEY,
	student_id INT REFERENCES students(student_id) NOT NULL,
	course_id INT REFERENCES courses(course_id) NOT NULL,
	UNIQUE (student_id, course_id)
);
```

There's a lot happening in this `CREATE TABLE` query, so let's go over it.

First, we're creating a JOIN table with a primary key: `CREATE TABLE student_course_enrollment ( enrollment_id SERIAL PRIMARY KEY`.

Then, we're adding a `NOT NULL` constraint to both of our foreign keys. A student shouldn't be able to enroll without a course, and vice versa: `student_id INT REFERENCES students(student_id) NOT NULL, course_id INT REFERENCES courses(course_id) NOT NULL`.

Finally, a student should only enroll in a course once; that's why we made the combination of both `student_id` and `course_id` unique: `UNIQUE (student_id, course_id)`.

If you describe your table, you can see all of the constraints above.

```
generalassembly=# \d student_course_enrollment
                                     Table "public.student_course_enrollment"
    Column     |  Type   | Collation | Nullable |                             Default                              
---------------+---------+-----------+----------+------------------------------------------------------------------
 enrollment_id | integer |           | not null | nextval('student_course_enrollment_enrollment_id_seq'::regclass)
 student_id    | integer |           | not null | 
 course_id     | integer |           | not null | 
Indexes:
    "student_course_enrollment_pkey" PRIMARY KEY, btree (enrollment_id)
    "student_course_enrollment_student_id_course_id_key" UNIQUE CONSTRAINT, btree (student_id, course_id)
Foreign-key constraints:
    "student_course_enrollment_course_id_fkey" FOREIGN KEY (course_id) REFERENCES courses(course_id)
    "student_course_enrollment_student_id_fkey" FOREIGN KEY (student_id) REFERENCES students(student_id)
```

**OR** 

In `pgAdmin` adding join table will look like,

![](./images/join_table.png)

![](./images/jt_col.png)

![](./images/jt_constraints.png)

![](./images/jt_constraints_2.png)

![](./images/jt_unique.png)

![](./images/jt_sql.png)

### You Do

The stage is set. You have courses you can offer; you have instructors ready to teach them; you also have interested students. Now is the time to start making some money. Using the JOIN table we just created, enroll students in the courses they're interested in.

Make sure your Captain Barbossa gets at least a few students enrolled in his course. Let us all strive for peace on campus.

-----

## Conclusion (10 min)

Your instructors are happy, your students are happy, and most importantly, your pirates are happy.

To make sure _you_ are happy with what you learned in this lesson, find a partner and take a few minutes to discuss the following questions:

- When would you use a one-to-one relationship?
- What about a one-to-many relationship?
- And finally, when would you use a many-to-many relationship/JOIN table?
