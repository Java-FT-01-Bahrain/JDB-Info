---
title: MVC Design Pattern
type: Morning Exercise
duration: "1:00"
creator:
    name: Melissa Arliss
---

# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) MVC Design Pattern

The **MVC design pattern** is an OG design pattern. Believe it or not, it was first introduced in the 1970s, before the internet and computers functioned in any way that's recognizable today. So, you could say it's got some legs!

In the most basic terms, MVC is a way to organize the code for your application. The whole theory is that each component of your code has a distinct, unique purpose. Those purposes are different, so your code should be treated differently, too. MVC helps you organize your code's core functions into neat boxes that reflect what that code does. This process is known as **separation of concerns**.

So, what are those boxes, you say? We've got the **model**, the **view**, and the **controller**!

**Model**

The model typically reflects real-world ideas. It might be the raw data you're working with or the essential components of your application.

**View**

The view is made up of everything that directly interacts with the user. It's all of the UI elements and code that makes your app look polished and works in tandem to define how a user sees and uses the app.

**Controller**

The controller is the go-between for the model and the view. It takes in user input and decides what to do with it. It's the brains behind the whole operation and ties everything together.

This isn't the prettiest diagram, but it's a good overview of the circle of life in MVC:

![](./images/mvc.png)
*([Source](https://www.geeksforgeeks.org/mvc-design-pattern/))*

MVC is very commonly used for designing how web applications function.

1. You (the user) enter a URL in the browser, visit a website, and click a link on a page.
1. That sends a request to the server, where the controller gets it.
1. The controller deals with the request and passes it to the right model.
1. The model retrieves the data you need and assembles it based on your request.
1. The model hands the data back to the controller, which assembles it using a template from the view.
1. The controller takes that assembled view and sends it to the browser for you to enjoy.

![](./images/mvc-browser.png)
*([Source](https://blog.yechiel.me/welcome-to-the-mvc-restaurant-fb1709047914))*

### Sketch It Out

Let's bring that down to reality. We'll recreate the diagram above for an actual app:

- A "to-do" app that lists tasks and allows you to check them off when complete.
- An address book app that returns a person's name, email address, and phone number.
- A means of checking your account balance on your bank's website.
- A place to shop for and purchase books online.

With a partner, take a few minutes to sketch out what these apps might look like on the board.

### Turn and Talk

So, now we know how MVC works. But why do we use this approach? Why has it stuck around for so long?

With a partner, discuss the following questions based on what you've learned about MVC so far:

- What are some of the benefits of separation of concerns?
- What types of applications would benefit the most from MVC?
- What might some MVC drawbacks be?

### MVC in Spring

As with many web frameworks, Spring helps us separate concerns and build applications using a model, view, and controller. 

**Spring MVC** is an MVC framework in Spring (frameworks on frameworks on frameworks) that's used for building web applications. It uses a DispatcherServlet to receive requests and map them to the best models, views, or controllers.

Let's take a look at one of the Spring applications we've been working on recently. With a partner, investigate the existing code to see how it maps to the MVC pattern.

- What do you notice about how the project is set up?
- How does the code for each component differ?
- Take a look at our Maven dependencies in `pom.xml`. Is there anything in there that maps to MVC?
