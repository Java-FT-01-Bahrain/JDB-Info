---
title: SQL JOINs
type: Lab
duration: "1:00"
creator:
    name: Yuliya Kaleda (and Alex De Marco)
---

# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) SQL JOINs Lab

## Introduction

> This should be done independently.

In this lab, you'll write SQL scripts for PostgreSQL that will create and initialize a database with two tables (`Employee` and `Job`) and then query the data in those tables. 

The `Employee` table contains the following data:  

<p align="center">
  <img src="./screenshots/employee.png">  
</p>

The `Job` table looks like this:  

<p align="center">
  <img src="./screenshots/job.png">   
</p>

The main purpose of our scripts is to query information from both tables:  

1.  Show the full names of employees working at Macy's.
2.  Show the companies located in Boston.  
3.  Show the full name of the employee with the highest salary.  

-------

## Exercise

### Requirements

1. Connect to postgres `psql postgres`

2. Create database company `CREATE DATABASE joins`

3. Connect to the newly creates database `\c joins`

4. Create table `employees` where `ssn` is the primary key.

5. Create table `jobs` such that `ssn` is a foreign key referring to `employee` table column `ssn`
> What relationship do employees-jobs tables have? Look at the image to find your answer.
6.  Add data to the tables using the csv files given in [starter-code](./starter-code)

```
\copy employees(ssn,first_name,last_name,year_of_birth,city) FROM '<path-to-dir>/databases/sql-joins-lab/starter-code/employees.csv' DELIMITER ',' CSV HEADER;

\copy jobs(ssn,company,salary,experience) FROM '<path-to-dir>/databases/sql-joins-lab/starter-code/jobs.csv' DELIMITER ',' CSV HEADER;
```

Write select queries for each of the following:
  1. **Employees working at Macy's**: The full names of employees working at Macy's.
  1. **Companies in Boston**: The companies located in Boston.
  1. **Employee with the highest salary**: The full name of the employee with the highest salary.

> Note: Those two files can call other files if you'd like.

