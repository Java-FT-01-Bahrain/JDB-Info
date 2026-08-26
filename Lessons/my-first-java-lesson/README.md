# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) My First Java

| Title | Type | Time | Creator |
| ----- | ---- | -- | ----- |
| My First Java | Lesson | 0:55 | Ryan Fleharty |

### Learning Objectives

At the end of this lesson, you'll be able to:
- Create and run Java files from the command line.
- Write the `main` Java method.

---

## Opening (5 min)

Before we dive into the nitty-gritty of Java functions and data types, let's write some code.

With any Java program, you'll first need to create a `class` and its `main` method. Let's find out how to do that.

## Installing the JDK (5 min)

Before we can write any Java code on our local machines, we need to install the Java Development Kit (JDK).

The JDK is a package of tools for developing Java programs. It runs in the background of your computer while you write Java code in a text editor or IDE. It's available from Oracle, the company that manages the Java language.

Installing JDK is one of the easiest things you'll do in this course:

1. [Click here](https://www.oracle.com/technetwork/java/javase/downloads/index.html) to visit Oracle's website. Find the latest version of JDK at the top of the page.
2. On the next page, accept the license agreement and select the file for your operating system.
3. Follow the installation instructions when the file downloads.

That's it! You're ready to write your first Java code.

## Code-Along: Howdy Pardner (15 min)

We'll complete this activity using a text editor and the command line.

Let's start with the all-time classic function, `Howdy Pardner`. To begin, create a file called `HowdyPardner.java` and save it.

All Java files must be defined as a `class`, so let's begin with a `class` definition. This `class` definition must match the name of the file, so we'll call ours `HowdyPardner`:

```java
  public class HowdyPardner {
 }
```

<!-- **Instructor Note**: Consider writing this and the main method signature on the board so you can underline and point to things (modifiers, parameters) as you go.-->

Then, all Java programs require a `main` method representing the program's entry point. This method will automatically be invoked when we run our Java file. The following function must be placed **inside** the `class` definition: 

```java
  public static void main(String[] args) {
 }
```

What's going on here? 

#### `public`

First, the `public` keyword declares this method available anywhere. On the other hand, a `private` method is only available to other members of the same `class`. You can find more details [here](https://docs.oracle.com/javase/tutorial/java/javaOO/accesscontrol.html).

#### `static`

Next, the `static` keyword indicates that this method belongs to the `class` itself. The opposite of a `static` method would be an **instance** method, where the method belongs to the objects the `class` creates. To run an instance method, you would have to create a new instance of the `class` with the `new` keyword and use it to run that method.

#### `void`

`void` indicates the function's return value, which in our case will be nothing at all. Functions in Java require you to describe the output type using their definition to enable future type-checking.

#### `main`

Finally, we name our function. In this case, the `main` function is required in Java programs and must be named `main`.

#### `String[] args`

Inside the parentheses, you'll notice that the function takes in one parameter: `String[] args`. `String` indicates the type, the array brackets tell the function to accept a list of `String`s, and the parameter is named `args` by convention. This array represents any command line arguments you pass when running the function, and it will be empty for our purposes.

Phew, all that for `Howdy Pardner`? And we haven't even gotten to the one line of code that will actually **do** what we want: Print `"Howdy, Pardner!"` to the console.

How do we do that? At this point, I trust you to figure out that line for yourself through research (i.e., Google). Take a minute to do that now.

<details>

<summary> And the answer is... </summary>

```java
    public class HowdyPardner {
        public static void main(String[] args) {
            System.out.println("Howdy, Pardner!");
 }
 }
```

</details>


A quick note on comments. In Java, we have two options for writing comments:

```java
// I'm a single-line comment. Just lil ol' me.
/*
 I'm a multi-line comment.
 There are several of us.
 And we all get along fabulously.
*/
```

Jump back into `HowdyPardner.java` and add comments to your code explaining what the `class` definition means and what the `main` method does.


## Code-Along: Getting Java to Run (10 min)

Now that our function is ready to roll, how can we get it to run? With Java, we have two steps to complete:
- **Step 1**: Compile the code.
- **Step 2**: Run the code.

> **Knowledge Check**: Can someone give a definition of compiling and explain why we do it? (Hint: We already covered this.)

#### Step 1: Compile

First, we have to **compile** the code into machine code for the Java Virtual Machine to run.

To accomplish this, we must run `javac <file-name>`, which should be `HowdyPardner`. Always expect a compilation error or two (or 12), with a handy message pointing you to the exact line and nature of your problem.

This contrasts with what we did in JavaScript, where a file could be running in our server, and until we hit a broken line of code, everything would be fine. Then, suddenly, we would get a breaking error.

JavaScript lets us get away with errors, while Java will enforce its rules before allowing us to run the code. After all, is it better for a car to tell us our brakes are out and refuse to start rather than let us drive around without working brakes?

> **Fun fact**: What we just described is the difference between **statically typed** languages (like Java) and **dynamically typed** languages (like JavaScript). We won't go into too much detail here, but now you have two more computer science terms to add to your tool belt.

#### Step 2: Run

When the file is successfully compiled, you should notice that a new file with a `.class` extension has been created: This is our compiled `class`. Now, we can run the code with the command `java HowdyPardner`. (That's right — we can't run a file until it's compiled. A file with a `.java` extension won't run, but a compiled file with the `.class` extension will run.)

Once we start using the IDE, we won't have to do any of this. The application will automatically compile our code when we save the file, and you can run the file by clicking one button.

## Variable Declaration (5 min)

To start writing our functions, we'll have to declare variables at some point. 

Creating a variable in Java requires us to define its data type by providing that type before the variable's name.

Here are some examples:

```java
int theAnswerToEverything = 42;
String bestLanguage = "Java";
double priceOfSteak = 6.99;
```

We'll learn more about all of Java's different data types soon, but these are the ones we see here:
- `int`: Short for integer; a whole number.
- `String`: Any combination of letters and words. The word `"String"` is a `class`, so it always begins with a capital letter.
- `double`: A number with a decimal.

Notice anything about how we write variables? That's right — they're in camel case.

Let's contrast that with how we defined the `HowdyPardner` class. In Java, it's conventional for a class name to start each word with an uppercase letter (even the first word).

#### Independent Practice (10 min)

With a partner, take 10 minutes to write these variables and print them to the console:
- How many licks does it take to get to the center of a Tootsie Pop?
- Your home city and state.
- The price of an ice cream cone from Mister Softee.

We'll have a few people demo what they did when they're done.

## Conclusion (5 min)

- Can you explain the difference between compiling and running a Java program?
- Without looking at your notes, can you explain the different parts of the `main` method?
