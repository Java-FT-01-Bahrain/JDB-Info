---
Title: Exception Handling Lesson
Duration: "1:30"
Creators: Drew Mahrt and Isha Arora
---

# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Exception Handling


### Learning Objectives

At the end of this lesson, students will be able to:
- Describe an exception.
- Describe the purpose of `try-catch` blocks.
- Implement `try-catch` blocks.

---

### Lesson Guide

| Timing  | Type  | Topic  |
|:-:|---|---|
| 5 min  | [Opening](#opening-5-min)  | Lesson Objectives |
| 5 min  | [Introduction](#introduction-exceptions-5-min)  | Exceptions |
| 10 min  | [Introduction](#introduction-try-catch-block-10-min)  | `try-catch` Block |
| 10 min  | [Demo](#demo-indexoutofboundsexception-10-min)  | `IndexOutOfBoundsException` |
| 10 min  | [Introduction](#when-you-should-use-try-catch-blocks-10-min)  | When You Should Use `try-catch` blocks |
| 45 min  | [Lab](#lab-file-reading-and-writing-45-min)  | File Reading and Writing |
| 5 min  | [Conclusion](#conclusion-5-min)  | Review/Recap |

<a name="opening"></a>

## Opening (5 min)

We often manually check for code that could cause errors through conditional statements. But Java (as well as many other programming languages) provides a valuable tool called a **`try-catch` block**. This helps shape our apps' behavior if an error occurs and stops our apps from completely crashing if an error-prone portion of code is used (such as file-streaming or networking operations).

> **Knowledge Check**: Have you encountered any errors with `Exception` as part of their name?

***

<a name="introduction"></a>

## Introduction: Exceptions (5 min)

Before discussing the `try-catch` block, we must discuss exceptions. **Exceptions** are events that occur while a program is running and interrupt the normal flow of the code. These can be null-pointer exceptions, divide-by-zero exceptions, index-out-of-bounds exceptions, and more. You can review many built-in exceptions in the [Java documentation](https://docs.oracle.com/javase/7/docs/api/java/lang/Exception.html).

There are two types of exceptions: checked and unchecked. A **checked exception** occurs at compile time, which means a programmer is forced to handle these exceptions; otherwise, the program won't compile. Checked exceptions are subclasses of the `Exception` class. An **unchecked exception** — also known as a **runtime exception** — occurs when a program executes. You don't have to handle them, but you can if you'd like.

Below is an illustration of the exception hierarchy. Red denotes unchecked exceptions, while blue denotes checked ones:

![](https://cdn2.howtodoinjava.com/wp-content/uploads/ExceptionHierarchyJava.png)
<sub>[Source](https://howtodoinjava.com/java/exception-handling/checked-vs-unchecked-exceptions-in-java/)</sub>

When an exception occurs, we say that it's **thrown**. This will become important when we look at `try-catch` blocks. While many parts of Java throw exceptions independently, you can also manually throw exceptions.

***

<a name="introduction"></a>
## Introduction: `try-catch` Block (10 min)

Exceptions aren't particularly useful, but they become important when paired with a `try-catch` block. A `try-catch` block looks like this:

```java
try {
  // Your code goes here.
} catch(Exception e) {
  // Execute this code if an error occurs.
}
```

Earlier, we said that exceptions are **thrown**. We also say that they're **caught** in the `catch` statement. `Exception` is the generic type from which all other exceptions are derived, and you can catch multiple types of exceptions from a single block of code.

It's important to note that the code in the `catch` block is only executed if an exception that matches the type of `Exception` declared is thrown.

> **Knowledge Check**: Discuss with the person next to you why we want to catch multiple exceptions.

***

<a name="demo"></a>

## Demo: `IndexOutOfBoundsException` (10 min)

Let's look at a case where we try to access a value from an array with an index outside its bounds. Open up the starter code for [`IndexOutOfBounds`](https://git.generalassemb.ly/GA-Cognizant/foundational-java/tree/master/java-basics/exception-handling-lesson/starter-code/IndexOutOfBounds). As you can see, this program has a list of superheroes and lets us enter a number to access our favorite one.

If we run the program and type in a number outside of the bounds of the `ArrayList`, we get an [`IndexOutOfBoundsException`](https://docs.oracle.com/javase/8/docs/api/index.html?java/lang/IndexOutOfBoundsException.html) — an example of an unchecked exception — and everything crashes.

Instead of having the program crash, we can log the error and allow the program to handle the exception gracefully by letting the user know that what they entered isn't valid.

Let's add a `try-catch` block around the code causing the error.

> **Knowledge Check**: Take a minute to discuss what code you think should be inside the `try` block.

```java
try {
    System.out.println(superheroes.get(num));
} catch(IndexOutOfBoundsException e) {
    System.out.println("The number you entered is invalid. Please enter a number from 0 to 2.");
}
```

As you can see, the code causing the error is `superheroes.get(num)`. By placing that inside the `try` block, we can catch the exception and show a message to the user. The program will continue running after the error, but we know the error occurred.

> **Knowledge Check**: Why didn't we put `Exception` instead of `IndexOutOfBoundsException`?

***

## When You Should Use `try-catch` Blocks (10 min)

In most situations, `try-catch` blocks aren't mandatory, so using them is entirely up to you. If you expect that a particular error has a decent chance of occurring, you can add a `try-catch` block to handle it.

However, sometimes, you must have `try-catch` blocks, such as file reading, writing, and networking. Let's look at a few lines of code that force us to use `try-catch`. You can use any open Java project to add these two lines of code:

```java
URL url = new URL("http://www.google.com");
HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
```
After importing the dependencies, the IDE still shows a compiler error. If you hover over the error, you'll see two options: surround the code with `try-catch` OR add a `throws` declaration. Let's go with `try-catch` for now, and we'll discuss the other option later.

We can consolidate the two `try-catch` blocks like this:

```java
URL url = null;

try {
 url = new URL("http://www.google.com");
    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
} catch(MalformedURLException e) {
    e.printStackTrace();
} catch(IOException e) {
    e.printStackTrace();
}
```
> Both `IOException` and `MalformedURLException` are examples of checked exceptions. You have to handle them for the program to compile.

Post-Java 8, we can combine our `catch` blocks into something shown below. It will do the same thing as above but with fewer lines of code:

```
URL url = null;

try {
 url = new URL("http://www.google.com");
 HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
} catch (MalformedURLException | IOException e) {
 e.printStackTrace();
}
```

<a name="guided-practice"></a>

### `throws` keyword

Now, let's discuss the other option our IDE gave us: adding a `throws` declaration. Another way to handle a checked exception is to use the `throws` keyword. We use this keyword to tell the calling method it's responsible for handling the error this code might throw. 

In the previous example, where we were trying to connect to a URL...

```java
URL url = new URL("http://www.google.com");
HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
```

...instead of `try-catch`, we could have used the `throws` keyword.

Again, you can use any open project to write this code:

```java
public void connectToURL() throws IOException {
    URL url = new URL("http://www.google.com");
    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
}
```
Now, whichever method will call `connectToURL()` can either handle the exception there or throw it to the calling method.

The most significant advantage of using this keyword is having all exception handling in one place as your application grows and becomes more complex. Also, it helps declutter your code.

### `throw` keyword
We use this keyword to throw an exception from anywhere in the code manually. This concept is best explained through an example. 

In this example, we'll look at a program that divides two numbers for us and displays the result. Open up the [`DivisionExample` starter code](https://git.generalassemb.ly/GA-Cognizant/foundational-java/tree/master/java-basics/exception-handling-lesson/starter-code/DivisionExample).

> **Knowledge Check**: Please take two minutes to review the code and determine how to handle an error if it occurs.

The program runs smoothly if you use any value higher than zero as a divisor. But, as soon as you input zero as a divisor, the program fails with the exception `/ by zero`. This is a type of [`ArithmeticException`](https://docs.oracle.com/javase/8/docs/api/?java/lang/ArithmeticException.html).

In this case, in the `division()` method, we need to check if the divisor is zero, then throw an exception. We can handle this exception by surrounding our code with a `try-catch` block, but this time, we'll let the calling method — in our case, `main()` — handle the exception:

```java
public static void division(int numerator, int divisor) {
        
    if(divisor == 0)
        throw new ArithmeticException("Divisor is 0");
    
    float result = numerator/divisor;
    System.out.println("Result of the division is: " + result);
}
```

We need to put the actual division operation inside the `try` block because that's where the error will occur. In our `catch` block, we let the user know they can't use zero as a divisor.

```
public static void main(String[] args) {
...
 try {
 division(numerator, divisor);
 } catch(ArithmeticException e) {
 System.out.println("Error: Divisor must be greater than 0");
 }
}
```

> **Knowledge Check**: Is it better to check for division by zero with an exception or manually check to see if the divisor is zero?

***


<a name="ind-practice"></a>

## Lab: File Reading and Writing (45 min)

Create an app that quizzes users on superhero trivia. Open the `Superheroes` [starter code](/Week4/Code/2-Tuesday/Exception-Handling/Starter-Code/SuperHeroes) and start working from there.

Your app reads the superhero info from an `input.txt` file and stores it in an `ArrayList`. Based on the answers, count every right and wrong one. If the number of correct answers is greater than the number of wrong ones, declare the user a winner and write the user's name and the quiz result in an `output.txt` file.

Before you start, let's do some research.

1. [BufferedReader](https://www.javatpoint.com/java-bufferedreader-class)
2. [BufferedWriter](https://www.javatpoint.com/java-bufferedwriter-class)
3. [Scanner](https://www.javatpoint.com/Scanner-class)


You can refer to the solution code [here](https://www.youtube.com/watch?v=oHg5SJYRHA0).
***



<a name="conclusion"></a>

## Conclusion (5 min)

Exceptions are an important part of keeping our apps running when problems occur. Sometimes, these problems — such as file I/O or networking situations — are out of our hands, and we must be prepared to handle them.

***

### Additional Resources
- [`try` Blocks](https://docs.oracle.com/javase/tutorial/essential/exceptions/try.html)
- [`catch` Blocks](https://docs.oracle.com/javase/tutorial/essential/exceptions/catch.html)
