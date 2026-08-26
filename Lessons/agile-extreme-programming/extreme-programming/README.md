![GA logo](https://camo.githubusercontent.com/6ce15b81c1f06d716d753a61f5db22375fa684da/68747470733a2f2f67612d646173682e73332e616d617a6f6e6177732e636f6d2f70726f64756374696f6e2f6173736574732f6c6f676f2d39663838616536633963333837313639306533333238306663663535376633332e706e67)

| Title | Type | Duration | Author |
| -- | -- | -- | -- |
| Extreme Programming | Lesson | 1:25 | Rachel Vigier |

# Extreme Programming

## Learning Objectives

- Describe extreme programming.
- Explain the value of extreme programming to software development.
- Define extreme programming rules and principles.

## Lesson Overview 

| Topic | Type | Timing |
| --- | --- | -- |
| Opening | Intro | 5 min |
| History of Extreme Programming | Lecture | 15 min |
| History of Extreme Programming | Review | 10 min |
| Principles of Extreme Programming | Lecture | 20 min |
| Principles of Extreme Programming | Review | 10 min |
| Extreme Programming Scenarios | Exercise | 20 min |
| Wrap up | Conclusion | 5 min |

## Opening (5 min)

*Embrace Change!*

This is the tagline in the title of Kent Beck’s seminal work, "Extreme Programming Explained," published in 2000. According to Beck, **extreme programming** asks a simple question:

*How little can we do and still build great software?*

Sounds pretty appealing, right? Here’s a more formal definition from the Agile Alliance:

> "Extreme Programming (XP) is an Agile software development framework that aims to produce higher quality software and higher quality of life for the development team." 

Extreme programming stresses customer satisfaction and emphasizes teamwork. It's closely associated with TDD (test-driven development) and characterized by:

- Early, iterative releases of software.
- An incremental planning approach.
- Flexible scheduling.
- Automated testing.
- Frequent customer feedback.
- Teams made up of managers, customers, and developers.

![xp](./images/xp.png)

## History of XP (15 min)

### Who Invented Extreme Programming?

Kent Beck, an American software engineer and one of the original signers of the Agile Manifesto, is credited with “inventing” extreme programming. Look him up on LinkedIn, and you’ll see that he’s been a force in the software world since the 1980s, including two years at Apple and seven years at Facebook.

### Where Did It Come From?

The Chrysler Comprehensive Compensation (or C3) project is often considered the first highly visible project completed through extreme programming.

The project's goal was to build a new system to support payroll processing for Chrysler's more than 85,000 employees. It was initiated in 1994, and Beck came aboard as a senior programmer in 1996.

Beck committed to completing the system within a year. The development team adopted a way of working that evolved into extreme programming.

There are different opinions surrounding the project's success and the value of extreme programming. The project was delivered a few months before the promised timeline. Unclear business requirements and performance issues delayed the full release of the system. Development continued for two more years before the project was canceled.

The project was supposed to provide a payroll system for more than 85,000 employees and would have been rolled out in phases. However, it was rolled out to only 10,000 employees before it was canceled.

So what do you think? Was it a success?

<!--**Instructor Note**: Have a quick discussion on whether or not this was a "successful" project. There are no right answers, only opinions on either side.-->

### How Extreme Is "Extreme"?

The "programming" part of extreme programming is self-explanatory, but why "extreme"? Beck says he took common-sense principles and practices and pushed them to "extreme" levels:

- If code reviews are reasonable, we’ll always review code.
- If testing is good, everybody will test all the time.
- If design is good, we’ll make it part of everybody’s daily business.
- If simplicity is good, we’ll keep system designs simple.
- If architecture is important, everyone should be involved in defining it.
- If integration testing is important, let’s do it as often as possible.
- If short iterations are good, let’s make them short.

### How Does Extreme Programming Fit In?

Here’s the Agile Alliance’s subway map of Agile practices:

![map](./images/agile-map.png)

Follow the extreme programming (XP) line, and you’ll see that XP shares many of its practices with other Agile frameworks, such as daily meetings, iterations, and user stories.

## Review: The History of XP (10 min)

Let's review what we've covered so far. Turn to a partner and discuss the following questions:

- Who is credited with inventing extreme programming?
- What practices does extreme programming share with other Agile methodologies?
- Given what you learned about Chrysler's original extreme programming project, do you consider the project a success?
- Given the mixed results from this original extreme programming project, why do you think its methods became important?

## Principles of Extreme Programming (20 min)

Let's get into the weeds of what extreme programming is and how it works by going through a few of its core principles:

- Team members and roles
- Testing and TDD
- Pair programming
- Coding standards
- Sustainable pace
- Continuous integration

### Whole Team: Extreme Programming Team Members and Roles

A typical extreme programming team includes the following members:
- Developer/programmer
- Customer/user
- Tester
- Manager/tracker
- Coach

#### Developer/Programmer

It's pretty straightforward: This is someone like you!

#### Customer/User

One thing that distinguishes extreme programming from other methodologies is the addition of a customer to the team. A customer knows the business problem and the desired solution and is best positioned to articulate user stories. 

A customer will use the system when it is in production. If you’re building a sales system, your customer might be a salesperson. If you’re building a business intelligence system, your customer might be a team of business analysts.

A customer is responsible for:
- Sitting with the extreme programming team.
- Writing and explaining user stories.
- Assisting in writing and validating tests.
- Resolving business questions as they come up for developers.
- Set priorities for developers as they come up.

What are some benefits of having the customer actively participate in code development?

<details>

<summary>Possible answers:</summary>

- False starts and errors can be caught early when it’s least expensive to fix problems.
- Developers don’t have to guess what the user wants or needs. They can ask the customer.

</details>

#### Tester

The **tester** is responsible for testing the product and is not a separate person. It's a role shared between programmers and the customer.

Programmers assist the customer in writing appropriate unit tests and running them regularly. The customer may also write and run acceptance tests regularly.

The project must have a process and tools for capturing and sharing test results.

#### Manager/Tracker

The **manager**, or tracker, monitors software development progress on the project and is responsible for detecting potential problems. They do things such as:

- Monitoring progress on the overall project.
- Estimating time on tasks.
- Maintaining a log of test results and reported bugs.

#### Coach

The **coach** monitors the overall **process** (compare this to the manager/tracker, who attends to the project's health). They might ask questions such as:

- Does everyone on the team understand their role?
- Do they need help applying the principles?

### Customer Tests and Test Driven Development (TDD)

Our next extreme programming principle is all about testing.

> The programmers write tests method by method. The customers write tests story by story.
*(Extreme Programming Explained)*

Testing is where extreme programming starts to differentiate itself.

In extreme programming, customers and programmers write tests, which they write them **before coding begins**. I'll say it again: In extreme programming, we write tests **before coding begins**.

Extreme programming flips the testing process on its head. Before Agile and extreme programming, testing occurred after coding was complete. When product development was complete (or almost complete), testing started. But no more!

The following diagram illustrates this process. The process is repeated each time the developer writes code for a feature or component and each time a customer adds a feature or element to the program:

![tdd](./images/tdd.png)

Extreme programming tests are:
- **Isolated**: Tests should not interact with other tests.
- **Automatic**: To minimize the possibility of human error.
- **Iterative**: Tests should be repeated when refactoring code.

### Pair Programming

Pairing means two people working on the same code using a single computer.

The latter part (using only one computer) is important because pairing is about actively collaborating, not just two people working on the same project or sitting next to each other.

At any given time, one person will be the "driver" and the other will be the "navigator."
- The **driver** sits before the keyboard, typing the code.
- The **navigator** sits beside the driver and reads the same screen. The navigator will discuss ideas, direct the driver, and consider how the currently written code fits into the larger project.

We'll often work through pair programming during this course, so you'll soon gain a lot of experience.

### Coding Standards

Extreme programming embraces changes. Without coding standards, this approach can quickly lead to chaotic code. With coding standards, your team will mitigate differences brought on by the collective ownership of code, switching up in pair programming, and constant code refactoring.

Each organization (and each extreme programming team) will have its own set of standards, including:
- Naming conventions
- Layout conventions
- Commenting conventions
- Language guidelines

It’s important that coding standards be communicated to every team member and that every team member follows them.

### Sustainable Pace

More hours do not translate into more or better code.

Releases and iterations should be planned sustainably to keep the team productive and fresh. Working long hours over a project's life cycle will result in a tired, demoralized team. Set a pace that works for the project and team. If necessary, modify the scope and timing of iterations.

### Continuous Integration

**Continuous integration** is the practice of integrating and committing code into a central code repository every few hours.

Developers should do a build several times a day, whenever a task is complete.

Integrating and testing code regularly will ensure that:
- Everyone is working with the most recent version of the code.
- Everyone knows what code can be reused or shared.
- Changes are always made to the most recent version of the code.

## Review: Principles of Extreme Programming (10 min)

We covered several principles related to extreme programming. Let's review each one and some related core information.

Can someone shout out each principle we talked about?

<!--**Instructor Note**: Write principles on the board as people say them.-->

Great! Now that we have them all, let's get some information about each on the board.

<!--**Instructor Note**: Jot down some information each person shouts out.-->

## Exercise: Extreme Programming Scenarios (20 min)

We're going to use extreme programming principles as we work on projects in this course. Let's think through how to start an extreme programming initiative. In this exercise, we'll identify what’s important to discuss and plan at the start of a project.

Let's divide into groups of 4–5 people. Take about 10 minutes to create an agenda for the initial kickoff meeting of an extreme programming project. Each group should identify the important points and activities needed to start such a project.

You can assume the following:
- Creating the agenda is the manager’s responsibility.
- The project is a business intelligence reporting application for the sales unit of a retail company with store locations across the United States.
- The business unit has not provided a specific time frame other than “as soon as possible.”
- There are eight employees assigned to the team: one manager, one coach, one customer, and five programmers.
- Three of the eight team members have worked together before.
- Two of the five programmers are highly skilled and experienced.
- The customers and programmers have never worked on an extreme programming team.
- The manager and coach have some experience with extreme programming. 

<details>

<summary>The proposed agendas might include items such as:</summary>

- Introducing project goals and scope.
- Introducing team members.
- Reviewing roles on the team.
- Reviewing TDD process.
- Determining pairs for pair programming.
- Reviewing project milestones and schedules.
- Review of tools for tracking tasks and schedules.
- Reviewing questions and concerns.
    
</details>

After 10 minutes, I will ask each group to share its proposed agenda with the class!

## Conclusion (5 min)

We got through all the extreme programming things! 

We'll have a lot more time to practice extreme programming when working through this course's projects. It's helpful to know what extreme programming is, how it works, and where it fits into the bigger picture of working styles and processes.

### Additional Resources

- [Twenty Years of Extreme Programming: Kent Beck Talks About Extreme Programming 20 Years Later](https://www.youtube.com/watch?v=cGuTmOUdFbo)
- [Agile Alliance Resources](https://www.agilealliance.org/) - Agile Alliance is a nonprofit organization supporting people who explore and apply Agile values, principles, and practices.
- [Kent Beck: Website of Extreme Programming Founder and Original Signer of the Agile Manifesto](https://www.kentbeck.com/)
- [Kent Beck on Agile Programming](https://www.youtube.com/watch?v=d4qldY0g_dI)

