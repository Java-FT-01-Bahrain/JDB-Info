---
title: Data Types and Variables
type: Lesson
duration: "1:30"
creator:
    name: Kristen Tonga
    city: NYC
---


# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Data Types and Variables


### Learning Objectives
At the end of this lesson, students will be able to:
- Identify and describe Java data types and their use cases.
- Describe the different types of variables and when to use them.
- Use class methods to manipulate data in the `Math` and `String` classes.
- Describe the difference between `NaN` and `null`.

---

### Lesson Guide

| Timing  | Type  | Topic  |
|:-:|---|---|
| 5 min  | [Opening](#opening-5-min)  | Learning Objectives |
| 10 min  | [Introduction](#introduction-data-types-in-java-10-min)  | Data Types in Java |
| 15 min  | [Demo](#demo-lets-start-with-numbers-15-min)  | Let's Start With Numbers |
| 10 min  | [Demo](#demo-using-special-number-operators-10-min)  | Using Special Number Operators |
| 10 min  | [Introduction](#introduction-primitives-vs-objects-10-min)  | Primitives vs. Objects |
| 15 min  | [Demo](#demo-creating-a-new-string-15-min)  | Creating a New String |
| 10 min  | [Demo](#demo-converting-between-data-types-10-min)  | Converting Between Data Types |
| 15 min  | [Independent Practice](#independent-practice-15-min)  | Practice |
| 5 min  | [Conclusion](#conclusion-5-min)  | Review/Recap |

<a name="opening"></a>

## Opening (5 min)

In programming, we need a way to store information while our programs run. This could be anything from names and numbers to dates and many other things, known as **data types**. These data types are stored in **variables**, like in algebra class. Today, we'll explore how data types and variables form the basic building blocks of programming and how we use them to store information in our programs.

----

## Introduction: Data Types in Java (10 min)

In computer science and computer programming, a data type — or simply, type — is a classification identifying one of the various types of data that determines:
- The possible values for that type.
- The operations that can be done on values of that type.
- The meaning of the data.
- The way values of that type can be stored.

Data types are similar across different languages, including English.

<!-- **Instructor Note**: Put the categories on the board and ask students to provide the data type or, in some other way, create a list on the board that includes the data types below. Include both lowercase primitive and capitalized object types. This chart will be referenced later.-->

|Category    | Data Type                     | Description          | Example |
|------------|------------------------------|----------------------|---------|
|True or False  | `boolean`, `Boolean` | Represents either true or false.                               |`true`, `false`|
|Integers    | `short`, `int`, `Integer`, `long`, `Long` | Whole numbers; can optionally have underscores to make large numbers easier to read.  | `42`, `1024`, `1_000_000` |
|Decimals    | `float`, `Float`, `double`, `Double` | Decimals.                                   | `42.123`, `2.5` |
|Characters  | `char` | A single character surrounded by single quotation marks.                 | `'a'`, `'A'`|
|Strings     | `String` | Single words or sentences surrounded by double quotation marks.        | `"lots of kittens"`, `"a lazy lizard"` |                                                                | `true`, `false`

There are also a few odd ones:
- **Byte**, which is one bit of data. You don't need to worry about it right now.
- **Collections** (we'll talk more about these soon).

We'll elaborate on all the categories on the board and show you some helper methods to manipulate them.

> **Knowledge Check**: Provide a few examples of data and predict what type it falls into.

-----

## Demo: Let's Start With Numbers (15 min)

#### Decimals vs. Integers

<!-- **Instructor Note**: As you code the following examples, ask the students the questions below. If they guess correctly, ask them to explain why.-->

First off, let's talk about numeric data types.

What do you expect to be printed on the console?

```java
int num1 = 5;
System.out.println(num1);
=> 5
```

How about here?

```java
int num2 = 5 / 2;
System.out.println(num2);
=> 2
```

But why is `num2` not 2.5? In low-level languages (unlike JavaScript, Ruby, or PHP), numbers are strictly typed, and a type is either an integer or a decimal. As demonstrated in the previous function, an `int` stores an integer, not a decimal.

What data type would we use to assign a variable to a decimal?

How about `float`?

```java
float num3 = 5 / 2;
System.out.println(num3);
=> 2
```

> **Knowledge Check**: That didn't work quite as expected. Can anyone guess why?

Because both `5` and `2` are automatically assigned the `int` data type, the answer is also an `int` when the calculation is done. We must tell the computer that both the dividend and divisor are of a decimal type, not an integer type:

```java
float num4 = 5f / 2f;
System.out.println(num4);
=> 2.5
```

```java
double num5 = 5d / 2d;
System.out.println(num5);
=> 2.5
```

Note: In the previous example, we saved decimal numbers using both a `float` and a `double` data type.

> **Knowledge Check**: What is the difference between `float` and `double`, and when should we use each one?

#### Numeric Data Types and Bits

To answer the previous question, it's helpful to understand that a data type defines the kind of data and the methods that can be used to manipulate that data. **Primitive** data types in Java also have a specific preassigned memory size. This is represented in several bits:

| Name  | Width in Bits | Range    |
|-------|---------------|----------|
| `float` | 32          | 3.4e–038 to 3.4e+038 |
| `double` | 64          | 1.7e–308 to 1.7e+308 |

More memory means more information can fit into a variable. A `double` data type is much larger than a `float` data type.

What does that mean for working with decimals? `float` data types are more memory efficient, while `double` provides more accuracy (it's recommended for currency).

A `BigDecimal` class is also used when even more decimal points are needed.

The same data type differentiation exists in integers, between `short`, `int`, and `long`:

| Name  | Width in Bits | Range    |
|-------|---------------|----------|
| `short` | 16           | -32,768 to 32,767                  |
| `int` | 32           | -(2^31) to (2^31)-1 (approx. 2 billion) |
| `long` | 64           | -(2^63) to (2^63)-1                    |

`int` will cover almost all of your integer needs.

> **Knowledge Check**: What's the most common data type for decimals? What's the most common data type for integers?

#### Using Standard Arithmetic Operators

Now that we understand the numeric data types let's examine what we can do with them.

Here are the standard arithmetic operators (those you've been learning since grade school):

<!-- **Instructor Note**: Depending on time and student understanding, this can be breezed over (it will have been covered during the JS unit). Be sure to mention modulo, as it's an odd one. When going through each example, ask students to calculate what will be printed to the console.-->

``` java
System.out.println(2 + 2);
System.out.println(2 - 2);
System.out.println(2 / 2);
System.out.println(2 * 2);
System.out.println(2 % 2); // What does this do?
```

----

## Demo: Using Special Number Operators (10 min)

Coding languages can sometimes be limiting in terms of the number of operations they allow you to perform. For example, how do you square or cube a number?

Java provides a special `Math` object with some practical methods.

Taking a number to a power? Use `Math.pow(num1,num2)`:

``` java
// 3^2 becomes
System.out.println( Math.pow(3,2) );
=> 9
```

Taking a square root? Use `Math.sqrt(num1)`:

``` java
// √(4) becomes
Math.sqrt(4);
=> 2
```

Need a random number? Use `Math.random()`:

``` java
// Returns double value with positive sign greater than or equal to 0.0 and less than 1.0.
Math.random()
=> ?
// Returns a random number in the range.
int range = Math.abs(max - min) + 1;
(Math.random() * range) + min;
```

> **Knowledge Check**: Who provides the `Math` object? Where might you be able to find more information? ([Oracle Math Documentation](https://docs.oracle.com/javase/7/docs/api/java/lang/Math.html)).

----

## Introduction: Primitives vs. Objects (10 min)

Let's take a step back before we get into `String`s. Have you noticed that all the data types we've used begin with a lowercase letter? What about the `String` data type? Did you pick up that it begins with a capital letter?

This is a naming convention used to distinguish between primitive and object data types. Primitive types always start with a lowercase letter (e.g., `int`, `double`, `boolean`), while objects start with an uppercase letter (e.g., `String`, `Integer`).

**Primitive data types** are **pass by value**. Using a primitive as a parameter is like writing a number on a Post-it note and handing it off. `int a = 1;` is a **copy** of the numeric data, not a reference to where the data is stored.

**Object data types** contain attributes and methods and start with a capital letter. These are **pass by reference**.

In other words, using an object is like using a Dewey Decimal System in the library. A variable assigned to an object is given a number that references where a book can be found in the computer's library but is not a copy of the book itself. `Person a = new Person(Nancy, Drew);` refers to the data object that contains all information and methods in the class of that object.

#### Java Naming Convention Tips

Classes and objects should start with a capital letter (e.g., `String`, `Integer`), primitives always begin with a lowercase letter (e.g., `int`, `boolean`), variables and method names start with a lowercase letter (e.g., `main()`), and multi-word names are camel case (e.g., `vannaWhite`). Class constants (i.e., `final` instance and static variables) are generally all capitalized with words separated by underscores (e.g., `private final Object MY_MUTEX`).

> **Discuss with the person next to you**: What does a primitive contain? What does an object contain? What's one easy way to distinguish between an object and a primitive data type? Be ready to share your answers.

#### Words: `char` and `String`

With that basic introduction to primitives and objects, let's talk about words:

* A `char` is a primitive data type. What's an example of a `char`?

* A `String` begins with a capital letter because a `String` is an object.

> **Discuss with the person next to you**: What's an object? Be ready to share your answer.

`String`s are collections of letters and symbols known as **characters**, and we use them to deal with words and text.

A `String` is an array of `char` data:

``` java
String str = "abc";
// is actually
char data[] = {'a', 'b', 'c'};
```

-----

## Demo: Creating a New `String` (15 min)

`String`s are a particular type of object.

Try this with me. You can instantiate (or create an instance) of a `String` like this:

``` java
// Variable can be assigned like a primitive
String a = "I'm a string."
```

This is short for:

``` java
// Variable assigned like an object
String a = new String("I'm a string too!")
```

#### `String` Helper Methods

Because a `String` is an object, it has predefined methods we can use.

To find the length of a `String`, use its `length` property:

```java
"hello".length();
=> 5
```

To get the first letter of a `String`:

```java
"hello".charAt(0);
=> "h"
```

To replace part of a `String`:

```java
"hello world".replace("hello", "goodbye");
=> "goodbye world"
```

To make a `String` uppercase:

```java
"hello".toUpperCase();
=> "HELLO"
```

To add two `String`s together:

```java
"hello".concat(" world");
=> "hello world"
```

Or, to concatenate (add together) using `+`:

```java
String twoStringsTogether = "Hello" + " World";
=> "Hello World"
```

##### A Special Note on Equality Among `String`s

What if you want to compare two `String`s?

```java
String blue = "blue";
boolean withSign = (blue == "blue");            //=> true
boolean withWords = (blue).equals("blue");      //=> true
```

Which one of these would be preferred? Let's walk through another example to show you which and why:

```java
String blue = "blue";
String bl = "bl";
String ue = "ue";
System.out.println(bl+ue);                      //=> blue
boolean withSigns = (bl+ue == blue);            //=> false
boolean withWords = (bl+ue).equals(blue);       //=> true
```

Why isn't `withSigns` `true` when the output looks the same? Remember, `String` is an object, and objects are **passed by reference.**

`==` compares the place where the object was stored on the computer to see whether or not they're the same. `String blue` contains a reference to where it's stored on the computer, and that's different from where `String bl` is stored. `equals()`, on the other hand, is a method that can be called on an instance of a `String` object and compares whether or not the `char` arrays in each `String` are the same, not whether or not the references are the same.

In short, use `equals` when comparing `String`s.

> **Knowledge Check**: Why can we call methods on a variable with a `String` data type but not on an `int`?

-----

## Demo: Converting Between Data Types (10 min)

Sometimes, it's necessary to convert between data types. User input is **always** a `String`, such as entering your email address, age, income, and more. However, if you'd like to operate on those numbers, you'll have to convert it to a numeric data type.

Remember how we talked about the size of primitive data types? A `float` is smaller than a `double`, and a `double` is smaller than a `long`.

Conversion is done automatically when converting from smaller types to larger types — for example, `int` (4 bytes) to `double` (8 bytes). This is called **implicit casting**:

```java
int a = 100;
double b = a;
System.out.println(b);
```

On the other hand, if you're converting from a bigger data type to a smaller one — for example, `double` (8 bytes) to `int` (4 bytes) — the change in data type must be clearly marked. This is called **explicit casting**:

```java
double a = 100.7;
int b = (int) a;
System.out.println(b);
```

While that's useful for numbers, to cast successfully, a variable must be an **instance** of the second object.

What would happen if you tried to cast a `String` to an `int`? There's a different way to convert `String`s into numbers.

Did you notice there's both an `int` and an `Integer` data type? The `Integer` data type is a wrapper around an `int` that provides specific methods.

For example, to convert a `String` to an `Integer`, you can use:

```java
String strValue = "42";
int intValue = Integer.valueOf(strValue);
```

Similar methods exist for all of the wrappers.

#### `NaN`

If a `String` is converted to a number but contains an invalid character, the result of the conversion will be **`NaN`**, which stands for "Not a Number."

`NaN` is toxic, and if a calculation is attempted on that variable or a method called subsequently, your program will break.

Use `isNaN()` to test for `NaN`.

#### `null`

A `null` value is an empty value. According to a StackOverflow post:

```
"Zero" is a value. It is the unique, known quantity of zero, which is meaningful in arithmetic and other math.

"Null" is a non-value. It is a "placeholder" for an unknown or not specified data value.
```

> **Knowledge Check**: When might you get the `NaN` value? What is a `null` value?

<a name="ind-practice"></a>

------

## Independent Practice (15 min)

<!-- **Instructor Note**: This can be a pair programming activity or done independently.-->

Grab the starter code from `VariablePractice` and complete all the tasks. We'll go over the answers in 12 minutes.

***

<a name="conclusion"></a>

----

## Conclusion (5 min)

- Identify the different types of data.
- What type of data is passed as a web request?

### Additional Resources
- [Oracle Java Docs on Primitive Data Types](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html)
- [Oracle Java Docs on the `Math` Object](https://docs.oracle.com/javase/7/docs/api/java/lang/Math.html)
