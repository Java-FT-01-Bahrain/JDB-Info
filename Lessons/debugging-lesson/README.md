---
title: Debugging Fundamentals in Java
type: Lesson
duration: "0:55"
creator:
    name: James Davis
    city: NYC

---

# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Debugging Fundamentals in Java

### Learning Objectives
At the end of this lesson, students will be able to:
* Set a breakpoint and look at the current state of the given sample code.
* Identify which lines are within your application given a stack trace.
* Describe what a stack trace is saying.

### Lesson Guide

| Timing  | Type  | Topic  |
|:-:|---|---|
| 5 min  | [Opening](#opening-5-min)  | Learning Objectives |
| 10 min  | [Introduction](#introduction-stack-trace-10-min)  | Stack Trace |
| 5 min  | [Demo](#demo-stack-trace-5-min)  | Stack Trace |
| 10 min  | [Introduction](#introduction-breakpoints-10-min)  | Breakpoints |
| 10 min  | [Demo](#demo-breakpoints-10-min)  | Breakpoints |
| 10 min  | [Independent Practice](#independent-practice-bug-fixing-10-min)  | Bug Fixing |
| 5 min  | [Conclusion](#conclusion-5-min)  | Review/Recap |

## Opening (5 min)

Even the best programmers make mistakes when writing code, and a lot of development time is often spent fixing errors in code, also known as **bugs**. The process of fixing bugs is called **debugging**. Many tools and techniques can be used for debugging, but today, we'll concentrate on **reading a stack trace** and **using breakpoints**.

## Introduction: Stack Trace (10 min)

Making mistakes is a natural part of programming, so it's critical to be able to debug your code. The first thing we'll look at is the **stack trace**. You've probably seen a stack trace in the past few days and have yet to realize it.

Take the following example. Can you spot the problem?

```java
public class Main {

    public static void main(String[] args) {
        printSomething(null);
 }

    public static void printSomething(String s) {
        System.out.println(s.length());
 }
}
```

If we run this code, we get the following error in the console:

```
Exception in thread "main" java.lang.NullPointerException
 at Main.printSomething(Main.java:11)
 at Main.main(Main.java:7)
 at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
 at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
 at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
 at java.lang.reflect.Method.invoke(Method.java:497)
 at com.intellij.rt.execution.application.AppMain.main(AppMain.java:144)
```

While this might seem intimidating, you only need to focus on a few essential parts. The first is the top line, which tells you the error. In this case, we had a `NullPointerException`, meaning we tried to call a method on a null variable.

Next, concentrate on the lines that contain your file name (in this case, `Main.java`). This is called the stack trace: It shows the path of execution for your program. Because the errors in the code are in the files we wrote, those are the lines we'll focus on. The higher up in the stack trace, the more recent the execution.

The number after the colon next to the file name is the line number where the error occurred. The error was in `Main.java` on Line 11 in our example.

> **Knowledge Check**: What does the second line of the stack trace after the `printSomething` call mean?

## Demo: Stack Trace (5 min)

Let's take a look at another example. Can you find the error?

```java
public class Main {

    public static void main(String[] args) {
        int[] arr = {};

        printSomethingArray(arr);
 }

    public static void printSomethingArray(int[] arr) {
        System.out.println(arr[0]);
 }
}
```

If we run this code, we get the following error:

```
Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: 0
 at Main.printSomethingArray(Main.java:13)
 at Main.main(Main.java:9)
 at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
 at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
 at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
 at java.lang.reflect.Method.invoke(Method.java:497)
 at com.intellij.rt.execution.application.AppMain.main(AppMain.java:144)
```

> **Knowledge Check**: Who can explain what's happening here?

The two errors we just saw, `NullPointerException` and `ArrayIndexOutOfBoundsException`, are common, so you'll probably see them in your stack traces.

> **Knowledge Check**: How could we avoid the `ArrayIndexOutOfBoundsException`?

## Introduction: Breakpoints (10 min)

The second topic we'll be covering today is the **breakpoint**. Generally, a breakpoint is a position in the code where you want to halt execution.

> **Knowledge Check**: Why would we want to do this?

Adding a breakpoint allows you to:
- Trace execution of the code step by step.
- Inspect the value of all variables in scope.

That second point can make debugging your code much faster. Instead of adding a lot of `print` statements to monitor your variables, you can stop your code in place.

> **Knowledge Check**: Why are breakpoints cleaner for debugging than `print` statements?

## Demo: Breakpoints (10 min)

Let's continue with the array example from the previous demo. Enabling breakpoints in IntelliJ is simple. Click the margin next to Line 13, where we access Index 0 in the array.

Next, you need to run the program in debugging mode. Instead of pressing the "Run" icon like usual, press the "Debug" button.

The program should run, but a new window will pop up at the bottom, and you should see your code change slightly.

The console now shows us the variables' values and any errors that might appear. The actual code also shows us the variables' values.

Let's add one more breakpoint in `main`, which we call the `printSomethingArray()` method. If we initialize the array with a few values and rerun the program, we should see these values change, and the error should disappear.

> **Knowledge Check**: How can you use breakpoints and a stack trace together?

## Independent Practice: Bug Fixing (10 min)

You've been provided the starter code for [a simple calculator](starter-code). Given that the user enters two numbers, a result is shown depending on the user's choice from the main menu.

However, it's broken in some instances. Sometimes, it behaves unexpectedly or throws errors.

## Conclusion (5 min)

Learning to debug well is a crucial skill, and what we covered today is a great way to start. You can apply it to almost any programming language you learn in the future.
