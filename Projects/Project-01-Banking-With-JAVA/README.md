# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Project 1 : Banking with Java

| Title                         | Type    | Duration | Author               |
|-------------------------------|---------|----------|----------------------|
| Project 1 : Banking with Java | Project | 5-6 Days   | Suresh Melvin Sigera |

Welcome to your first project - a Java command-line banking application! This is a great opportunity to get creative and
tackle some challenging programming problems.

You will work individually on this project, but remember, we are here to support you, and peer support is encouraged.
Let's see what you can create!

## Project Overview
Your task is to design a file database for ACME Bank and write the entire Java program using OOP principles, file
handling, exception handling, and unit testing to meet the below functional requirements.

## Technical Requirements

You should implement:

- Concrete classes
- Abstract classes
- Interfaces
- File handling
- Exception handling
- Lambda expressions and Optionals
- Unit testing

## Functional Requirements

1. **Login Functionality** 
    - User should be able to login into the system.
    - System should recognize the user and its role. (Banker/ Customer).

2. **Add New Customer**
    - Customers may have a checking account, a savings account, or both.
    - Customers should be able to set a password for their account(s).

3. **Account Transactions**
    - **Withdraw Money** (requires login)
        - From savings or checking accounts.
    - **Deposit Money** (requires login)
        - Into savings or checking accounts.
    - **Transfer Money** (requires login)
        - Between a customer's own accounts or to another customer's account.

4. **Overdraft Protection** (requires login)
    - Charge an ACME overdraft protection fee of $35 when overdrafting.
    - Prevent withdrawing more than $100 if the account balance is negative.
    - Deactivate the account after 2 overdrafts; reactivate if the customer resolves the negative balance and pays the
      overdraft fees.

## Advanced Features

5. **Display Transaction Data** (requires login)
    - Track all transactions for a customer in a separate file.
    - Display transaction history including date, type, and post-transaction balance.

6. **Password Encryption** Use password encryption. Password for the newly created account should be encrypted.

7. **Debit Card Types** 3 types of Mastercard (1 per Account).  
    - Mastercard Platinum, 
    - Mastercard Titanium,  
    - Mastercard 

- Transaction limits and benefits varies for each card.

<table>
<tr>
    <td>
    Operations
    </td>
    <td>
    Mastercard Platinum
    </td>
    <td>
    Mastercard Titanium
    </td>
    <td>
    Mastercard
    </td>
</tr>

<tr>
    <td>
    Withdraw Limit Per Day
    </td>
    <td>
    $20000
    </td>
    <td>
    $10000
    </td>
    <td>
    $5000
    </td>
</tr>

<tr>
    <td>
    Transfer Limit Per Day
    </td>
    <td>
    $40000
    </td>
    <td>
    $20000
    </td>
    <td>
    $10000
    </td>
</tr>

<tr>
    <td>
    Transfer Limit Per Day (Own Account)
    </td>
    <td>
    $80000
    </td>
    <td>
    $40000
    </td>
    <td>
    $20000
    </td>
</tr>

<tr>
    <td>
    Deposit Limit Per Day
    </td>
    <td>
    $100,000
    </td>
    <td>
    $100,000
    </td>
    <td>
    $100,000
    </td>
</tr>

<tr>
    <td>
    Deposit Limit Per Day (Own Account)
    </td>
    <td>
    $200,000
    </td>
    <td>
    $200,000
    </td>
    <td>
    $200,000
    </td>
</tr>

</table>

8. **Detailed Account Statment** It Should return the total amount in the account and transactions with date and time.

9. **Filtering transactions** User can query the program and get transactions with certain conditions (e.g. today, yesterday, last week, last 7 days, last month, last 30 days and filtering on the basics of date and time).

10. **Fraud Detection** If there are 3 failed login attempts so design a mechanism to lock the account for 1 min, before trying again with the login information.


<i>Please note: File naming conventions for Banker and Customer files.</i><br>
Banker 
```html
Banker-<BankerName>-<BankerID>
```

Customer 
```html
Customer-<CustomerName>-<CustomerID>
```


## Bonus

<i>Before starting any bonus point, please discuss in detail with Mr. Saad.</i>

- **PDF Bank Statement** Design a professional looking PDF bank statement for customers by writing the data into a proper tabular form and include a company logo.
(For Samples, please contact Mr.Saad)

- **Currency management** Design a currency management and conversion system. We can have 3-5 currencies initially. Like Bahraini Dinar, US Dollar, British Pound, Euros, Saudi Riyal.

- **Friends feature** Design a frequent transfer feature. For example, if i am trying to transfer money to my friend frequently, so next time it will appear in the list of frequent transfers.

- **Credit Card Component** Design a Credit Card feature and rewards feature.

- **Scheduled Payments** Design scheduled payments feature. Every month subtract the amount of e.g. car payment or utility bills or phone plan, from the account automatically.

- **Notification System** Develop a feature for sending the notification to the customers. Send notification wherever it is necessary in your Banking System Project. Write the notifications in the Customer file.

## User Stories

- As a user, I should be able to interact securely with my bank accounts through a command-line interface.
- As a user, I want to be able to view a history of all transactions to track my spending and deposits.
- and so on ...

## README.md Suggestions

- List technologies used.
- Link to Trello (User Stories and planning).
- Link to additional resources (If any).
- Document your planning and development process, and your problem-solving strategy.
- List unresolved issues that could be addressed in future versions.
- Describe some of your favorite functions and how they work.
- ERD diagram.

## Submission

- Projects are due on Tuesday 09th Dec 2025 at 06.00 PM.

**DO NOT FORK THIS REPOSITORY!** Create a **new** repository on your personal GitHub account. 

Your submission must include a link to your hosted solution on GitHub and frequent commits dating back to the beginning
of the project. Please include any questions or specific feedback requests with your submission.

- Fill out this [Google Sheet](https://docs.google.com/spreadsheets/d/1C58qQAwjK1AMqKWhuPh-JRS1BscVcWt99M95S-5RgHw/edit?gid=0#gid=0)

## Evaluation

Your instructors will use the following rubric to assess your project: [Evaluation Rubric](evaluation.md)

## Presentations

Presentations are scheduled for 7 minutes each. Suggested talking points:

- What would you do differently?
- What are you most proud of?
- What would you do next?
- How did you plan your project?
- What did you learn during this process?
- Open Q & A session.

## Plagiarism Policy

Please review the plagiarism policy. If you're struggling with the material, first review previous lessons. If problems
persist, ask an instructor for help. Do not copy and paste code from other sources or students.

## Get Started

![Get Started](https://media1.tenor.com/images/757db74e0691919301cb3414f642beef/tenor.gif?itemid=3561747)

## Additional Resources

- [Java Documentation](https://docs.oracle.com/en/java/javase/11/docs/api/index.html)
