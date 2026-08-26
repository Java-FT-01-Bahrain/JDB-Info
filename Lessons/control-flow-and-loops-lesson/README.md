---
title: Control Flow and Loops
type: Lesson
duration: "1:35"
creator:
    name: Kristen Tonga
    city: NYC

---

# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Control Flow and Loops

### Learning Objectives
At the end of this lesson, students will be able to:
- Use `if...else` conditionals to control program flow based on Boolean conditions.
- Use `switch` conditionals to control program flow based on explicit conditions.
- Use comparison operators to evaluate and compare statements.
- Use Boolean logic to combine and manipulate conditionals.
- Loop over a code block one or more times.

### Lesson Guide

| Timing  | Type  | Topic  |
|:-:|---|---|
| 5 min  | [Opening](#opening-5-min)  | Learning Objectives |
| 10 min  | [Introduction](#introduction-logical-operators-and-control-flow-10-min)  | Logical Operators and Control Flow |
| 10 min  | [Demo](#demo-conditional-statements-10-min)  | Conditional Statements |
| 15 min  | [Guided Practice](#guided-practice-boolean-or-logical-operators-15-min)  | Boolean or Logical Operators |
| 10 min  | [Demo](#demo-comparison-operators-10-min)  | Comparison Operators |
| 10 min  | [Introduction](#introduction-switch-statements-10-min)  | `switch` Statements |
| 15 min  | [Demo](#demo-loops-15-min)  | Loops |
| 15 min  | [Independent Practice](#independent-practice-fizz-buzz-15-min)  |  Fizz Buzz |
| 5 min  | [Conclusion](#conclusion-5-min)  | Review/Recap |

## Opening (5 min)

> Control flow structures are an essential aspect of programming languages that allow your code to take certain actions based on various scenarios. Control flow is a fundamental concept in programming that will enable you to dictate how your code runs under different conditions or until a certain condition is met.

*The text above is from culttt.com.*

Control flow is a concept that's consistent across most programming languages. Its components (loops, `if...else` conditionals, `switch` statements, and comparison operators) are universal principles.

The way different control flow components are implemented varies from language to language. Let's see how it's done in Java.

Java supports a compact set of statements—specifically control flow statements—that you can use to incorporate interactivity into your application.

-----

## Introduction: Logical Operators and Control Flow (10 min)

#### Block Statements

Statements meant to be executed after a control flow operation will be grouped into a **block statement**. These statements are wrapped around a pair of curly braces:

```java
{
  System.out.println("hello");
  System.out.println("roar");
}
```

#### Block Scope

Scope changes depending on whether a variable is defined in the class (we use the `mVariableName` convention for these) or in a method (these variables have local scope only and are not available outside that method).

In Java, variables defined in **block statements** modify scope, meaning those variables are unavailable outside the block.

<!-- **Instructor Note**: Show the scope error in the IDE, then correct it as shown in the second code block.-->

For example:

```java
boolean beautiful = true;

if (beautiful)
{
  String name = "lydia";
}

System.out.println(name); // symbol 'name' cannot be resolved
```

Variables defined in **block statements** are not available outside of the curly braces. How might we resolve this issue?

<!-- **Instructor Note**: First, try to print out `name` without an `else` statement, then add the `else` when the program still won't compile.-->

```java
boolean beautiful = true;
String name = "pepe";

if (beautiful)
{
 name = "robin"; // Use the predefined variable.
}

System.out.println(name); //=> robin
```

-----

## Demo: Conditional Statements (10 min)

Conditional statements are a way of skipping over a code block if it doesn't pass a Boolean expression. Java supports two conditional statements: `if...else` and `switch`.

#### `if...else` Statements

```java
if (expr) {
 code
} else {
 other code
}
```

This means run the `code` block if `expr` is `true`; otherwise (if `expr` is `false`), run the `other code` block:

```java
int i = 9;

if (i > 0) {
    System.out.println("hi");
} else {
    System.out.println("goodbye");
}
//=> hi
```

When you need to test more than one case, you may use `else...if`:

```java
String name = "kittens";

if (name.equals("puppies")) {
 name += "!!!";
} else if (name.equals("kittens")) {
 name += "!!";
} else {
 name = "!" + name;
}

System.out.println(name); //=> "kittens!!"
```

#### Ternary Operator

Java has a ternary operator for conditional expressions. You can think of the ternary operator as a concise `if...else` in one line:

```java
int age = 12;

String allowed = (age > 18) ? "yes" : "no";

System.out.println(allowed); //=> "no"
```

#### `true` or `false`

It's important to know that all of the following become `false` when converted to a `Boolean`:

- `false`
- `0`
- `""` (empty string)
- `NaN`
- `null`
- `undefined`

For example:

```java
Boolean b = Boolean.valueOf("");

System.out.println(b); //=> false
```

This can be helpful when checking if conditions exist, are undefined, or if variables don't hold value.

-----

## Guided Practice: Boolean or Logical Operators (15 min)

[Logical operators](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/opsummary.html) will always return a Boolean value, `true` or `false`.

There are two binary operators that require two values:
- **`AND`**, denoted `&&`.
- **`OR`**, denoted `||`.

A third unary operator requires only one value:

* **`NOT`**, denoted `!`.

#### `&&` (`AND`)

The `&&` operator requires both values to its left and right to be `true` to evaluate the entire statement as `true`:

```java
boolean result = false;

if (true && true) {
 result = true;
}

System.out.println(result); //=> true
```

Any other combination using the `&&` operator is `false`.

What happens if I check `true && false`?

```java
boolean result = false;

if (true && false) {
 result = true;
}

System.out.println(result); //=> false
```

```java
boolean result = false;

if (false && false) {
 result = true;
}

System.out.println(result); //=> false
```

#### `||` (`OR`)

The `||` operator requires just one of the left or right values to be `true` to evaluate the statement as `true`.

So, what will be returned if I do `true || false`?

```java
if (true || false) {
  System.out.println("true");
}
//=> true

if (false || true) {
  System.out.println("true");
}
//=> true

if (false || false) {
    System.out.println("true");
}
//=> ... silence ...
```

> **Knowledge Check**: What is the only combination that will return `false`?

Only `false || false` will return `false`.

The `!` takes a value and returns the opposite Boolean value:

```java
!(true)
//=> false
```

The `&&` and `||` operators use short-circuit logic, which means whether or not they execute their second operand depends on the first.

> **Knowledge Check**: Can you think of a time when this might be useful?

This is useful for checking for `null` objects before accessing their attributes:

```java
if (instructor != null && instructor.getName().equals("drew")) {
  System.out.println("davis");
}
```

In this case, if the first operand, `instructor != null`, is `false`, then the second operand, `instructor.getName().equals("drew")`, will not be evaluated. The expression says, "We already know the whole `&&` expression is false because `instructor != null` is false. Why bother dealing with the second operand?"

This is also important because a `NullPointerException` will be thrown if we try to call a method using "dot notation" on a `null` object reference.

----

## Demo: Comparison Operators (10 min)

[Comparisons](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/op2.html) in Java can be made on primitives using `<`, `>`, `<=`, and `>=`.

> **Knowledge Check**: What is a primitive data type?

```java
'A' > 'a'
//=> false

'b' > 'a'
//=> true

12 > 12
//=> false

```

Note that you **cannot** do:

```java
12 >= "12"

// or

"Apple" > "Oranges"
```

#### Equality Operator `==`

When verifying equality between primitives, use `==`:

```java
System.out.println(1 == 2);
//=> false
```

But what about objects like `String`s?

##### A Special Note on Equality Among `String`s

There are two ways to compare the equality of `String`s:

```java
String blue = "blue";
boolean withSign = (blue == "blue");            //=> true
boolean withWords = (blue).equals("blue");      //=> true
```

Which one of these would be preferred?

Let's do another example to show you which and why:

```java
String blue = "blue";
String bl = "bl";
String ue = "ue";

System.out.println(bl+ue);                      //=> blue
boolean withSigns = (bl+ue == blue);            //=> false
boolean withWords = (bl+ue).equals(blue);       //=> true
```

Why isn't `withSigns` `true` when the output looks the same?

Objects and arrays are complex collections of values, and when we refer to them, we're referencing where they live in memory. That's why we say objects are **passed by reference**, while `int` and `float` are **passed by value**.

`==` compares the place where the object was stored on the computer. This means that Java doesn't care if they look similar; it only compares whether or not they're the same object in memory. When checking for equality in each of the cases above, we compare two objects in two different places in memory.

`String blue` refers to where it's stored on the computer, which is different from where `String bl` is stored. According to `==`, they're not the same.

`equals()`, on the other hand, can be called on an instance of a `String` object. This method checks if the `char` arrays in each `String` are the same, not if the references are the same.

In short, use `equals()` when comparing `String`s.

> **Knowledge Check**: Why can we call methods on a variable of a `String` data type but not on an `int`?

#### Don't Forget About Me: `!=`

There's also an `!=` operator, the inverse of `==`.

-----

## Introduction: `switch` Statements (10 min)

The `switch` statement can be used for multiple branches based on several `String`:

```java
  String food = "apple";

  switch(food) {
    case "pear":
      System.out.println("I like pears");
      break;
    case "apple":
      System.out.println("I like apples");
      break;
    default:
      System.out.println("No favorite");
 }
//=> I like apples
```

In this case, the `switch` statement compares `food` to each of the cases (`pear` and `apple`) and evaluates the expressions beneath them if there's a match. It uses the `String.equals` method in this case, or `==` in the case of primitives, to evaluate equality.

The default clause is optional.

**Note: Breaks are essential.** If you don't put a `break` statement, the expression will continue to be evaluated for the following cases. This might cause unintended consequences.

For example, if you eliminate the `break` statements:

```java
  String food = "apple";

  switch(food) {
    case "pear":
      System.out.println("I like pears");
    case "apple":
      System.out.println("I like apples");
    default:
      System.out.println("No favorite");
 }
```

The result would be:

```
I like apples 
No favorite
```

If `food = "pear"`, then the output would be:

```
I like pears  
I like apples 
No favorite
```

We intended something else.

------

## Demo: Loops (15 min)

Looping exists in almost all programming languages. A loop is a statement or block of code that continues to execute while or until a condition exists.

`while` loops, for example, will run a block of code **while** a condition is `true`. Java has `while` loops and `do...while` loops.

The `while` loop is good for basic looping, but it may never run.

> **Knowledge Check**: In what case will the `while` loop never run?

```java
while (true) {
  // an infinite loop!
}
```

Using a `do...while` loop makes sure the body of the loop is executed at least once because `while` isn't evaluated until after the block of code runs:

```java
int input = 0;

do {
  System.out.println(input++);
} while (input < 10);
```

You can use looping in combination with iteration to repeat a task incrementally.

For example, using a `for` loop:

```java
int iterations = 10;

for (int i = 0; i < iterations; i++) {
  System.out.println(i);
}
```

Notice the placement of the semicolons. Now, let's take a look at what each of the parts do:

1. `int i = 0;` is the **initialization** phase.
  - This is executed once before the loop begins.
  - Note that `int i` is declared within this phase. This means the lifespan of `i` is limited to within the `for` loop, which is much cleaner and leads to fewer problems.

2. `i < iterations;` is the **termination** phase.
  - Every time the loop evaluates, it checks this statement.
  - The loop terminates if this statement is evaluated as `false`.
  - This is equivalent to the `while` section of the `do...while` loop.

3. `i++` is the **increment** expression.
  - This happens every time the loop evaluates.
  - This is equivalent to the `do` section of the `do...while` loop.
  - In this case, for each loop, `i` is incremented by `1`.
  
-----

## Independent Practice: Fizz Buzz (15 min)

Fizz Buzz is a game about division. Create a program that iterates through numbers `1` to `101` and logs each number in the console.

- If the number is divisible by **three**, the word `fizz` should be logged instead of the number itself.
- If the number is divisible by **five**, the word `buzz` should be logged.
- If the number is divisible by both **three** and **five**, the word `fizzbuzz` should be logged.

**Hint**: Remember the **modulo** operator?

A typical output would look like this:

<img src="https://i.imgur.com/avioQC8.png" width="400px">

<details> 

<summary>Solution</summary>

```java
for (int i = 1; i < 101; i++) {

  if((i % 3 == 0) && (i % 5 == 0)) {
    System.out.println("fizzbuzz");
 } else if(i % 3 == 0) {
    System.out.println("fizz");
 } else if(i % 5 == 0) {
    System.out.println("buzz");
 } else {
    System.out.println(i);
 }
}
```

</details>

----

## Conclusion (5 min)

These are foundational tools you'll use in many of your applications. You'll need to refresh yourself on the exact syntax before you memorize it. But it's important to remember these core control flow concepts because they'll appear in almost every programming language you encounter. The Java documentation on [control flow statements](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/flow.html) is a helpful reference.
