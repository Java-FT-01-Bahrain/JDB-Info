# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Concurrent CSV Data Processor Lab

|             Title             |  Type  | Duration  |       Creator        |
|:-----------------------------:|:------:|:---------:|:--------------------:|
| Concurrent CSV Data Processor | Lab/HW | 1:5 days	 | Suresh Melvin Sigera |

# Project Title: Concurrent CSV Data Processor	

## Project Scope:

The Concurrent CSV Data Processor project aims to develop a Java application for processing employee data stored in a
CSV file concurrently. The application will utilize multithreading techniques, including thread pooling and concurrency
control mechanisms, to efficiently read the CSV file, process the data, and perform concurrent operations on the
employee records. The CSV file will contain the name of the employee, the current salary, joined date, role in the company and percentage of completed projects. Your job will be to apply an increment on the employees' salary.

### Project Objectives:
Create a Spring Boot project with:

- CSV File Reading: Implement functionality to read employee data from a CSV file.
- Concurrency Control: Utilize concurrency control mechanisms such as locks and semaphores to ensure thread safety
  during data processing.
- Thread Pooling: Implement thread pooling using Java's Executor framework to concurrently process employee data.
- Concurrency Control Enhancements: Explore additional concurrency control techniques, such as atomic
  variables or synchronized blocks, to optimize performance and ensure data integrity.
- Project % Completed: If the employee completed less than 60% of the projects they will NOT receive any increase in salary
- Dates: based on the joined date, they'll receive 2% increase for each year worked only if they completed one year of work
- Roles: Director, Manager, Employee. Director receives 5% increase, Manager receives 2% increase. Employee receives 1% increase.
- Documentation: Provide comprehensive documentation including project setup instructions, code documentation, and a brief report summarizing the project implementation and outcomes.
- GIT: The project should have at least 20 commits with consistent and clear messages


### Bonus Objectives:
- Frontend
- Interactivity
  - Example: Having a button that shows the before and after of the salaries of the employees, making the % of increase based on an input
- Project % Completed: If the employee completed more than 80% of the projects, raise will be multiplied by 1.5
  - Example: If employee normally receives 1% but is above the 80% threshold, then they should receive 1.5% increase

### Deliverables:

- Source Code: Complete Java source code for the Concurrent CSV Data Processor application, including classes for CSV
  reading, concurrency control, and data processing.
- Documentation: Detailed documentation covering project setup, class/method documentation, and a project report
  summarizing the implementation details and outcomes.
- CSV File with Sample Data: A sample CSV file containing employee data for testing the application.