---
title: Arrays and Array Lists
type: Lesson
duration: "1:30"
creator:
    name: Kristen Tonga
    city: NYC
---

# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Arrays and Array Lists

### Learning Objectives
At the end of this lesson, you'll be able to:
- Create and manipulate arrays and array lists.
- Work with `for` loops to iterate over collections.

---
<a name="opening"></a>

### Lesson Guide

| Timing  | Type  | Topic  |
|:-:|---|---|
| 5 min  | [Opening](#opening-5-min)  | Learning Objectives |
| 5 min  | [Introduction](#introduction-basic-arrays-5-min)  | Basic Arrays |
| 5 min  | [Demo](#demo-creating-arrays-5-min)  | Creating Arrays |
| 10 min  | [Independent Practice](#independent-practice-creating-arrays-10-min)  | Creating Arrays |
| 15 min  | [Demo](#demo-manipulating-arrays-15-min)  | Manipulating Arrays |
| 10 min  | [Demo](#demo-problems-with-arrays-10-min)  | Problems With Arrays |
| 15 min  | [Demo](#demo-collections-15-min)  | Collections |
| 15 min  | [Guided Practice](#guided-practice-iterating-through-a-list-with-for-loops-15-min)  | Iterating Through a List With `for` Loops |
| 15 min  | [Independent Practice](#independent-practice-arrays-and-array-lists-15-min)  | Arrays and Array Lists  |
| 5 min  | [Conclusion](#conclusion-5-min)  | Review/Recap |

## Opening (5 min)

So far, we've stored all of the information for our programs in individual variables. That works for a small amount of information, but what if we had to manage more extensive data sets? For instance, what if we had credit card transactions in a customer's banking app? Should we store every transaction in its variable or have a single variable hold all transactions simultaneously?

> In pairs, take 1–2 minutes to come up with a few more examples of when storing our data in a collection would be beneficial (data situations or sample programs).

----

## Introduction: Basic Arrays (5 min)

An array is a container object that holds a **fixed** number of values of **a single type**. You've already seen an example using the `main` method.

<!-- **Instructor Note**: Draw a picture on the board similar to the one below to visually display each element and corresponding index.-->

<details>

<summary> Knowledge Check: This is different than arrays in JavaScript. How so?</summary>

 In JS, arrays aren't fixed in size.

</details>

Each item in an array is called an **element**, and each element can be accessed by its **index**. The index of elements starts at `0`. Visually, this means:

 INDEX  | 0 | 1 | 2 | 3 | 4
 -------|---|---|---|---|---
| ELEMENT| x | x | x | x | x |

> **Knowledge Check**: How would we add more space if a fixed size is required?

----

## Demo: Creating Arrays (5 min)

Let's create an array together:

<!-- **Instructor Note**: Explain each piece of syntax here.-->

```java
  class ArrayDemo {
    public static void main(String[] args) {
      // Declares an array of integers.
      int[] anArray; // Note data type, followed by [] indicating array.

      // Allocates memory for ten integers:
 anArray = new int[10];

      // Assign elements:
 anArray[0] = 111;
 anArray[1] = 222; // And so on...

      // Access elements:
      System.out.println("Element at index 0: " + anArray[0]);
      System.out.println("Element at index 1: " + anArray[1]); // and so on
      System.out.println("The array has a size of " + anArray.length);
 }
 }
```

> **Knowledge Check**: How would you find the index of the last element in the array using the `length` property?

----

## Independent Practice: Creating Arrays (10 min)

Create a `String` array containing **three** of your favorite things. Print the result to the command line.

<!--**Instructor Note**: Review how you would create `String[] favoriteThings;`-->

<details>
  
<summary>Check: What are the three array creation steps?</summary>

  1. Declare variable.
  2. Allocate memory.
  3. Initialize or assign values.

</details> 

----

## Demo: Manipulating Arrays (15 min)

Arrays can also be created and initialized with one statement. The number of elements inside the curly braces automatically determines the array's length.

```java
  class FavoriteThings {
    public static void main(String[] args) {
      // Shortened syntax: Assign variable, allocate memory, and initialize values in one statement.
      String[] favoriteThings = {
        "raindrops",
        "roses",
        "whiskers on kittens"
 } // Length will be inferred as 3.
 }
 }
```

#### Manipulating Basic Arrays

Let's look at some basic things you can do with an array.

Note that indexing (getting or setting the item based on its position in the array) and determining its length have built-in syntax. At the same time, some less fundamental operations are performed using methods on the `Arrays` object. This is similar to adding two numbers together by writing `a + b`, but using methods on the `Math` object to do more esoteric things like rounding.

```java
  import java.util.Arrays;

  class ArrayManipulationDemo {
        // Initialize array:
        int[] primeNumbers = {5,3,11,7,2}; // next 13, 17, 19, 23

        // Get the element at a specified index:
        int firstPrime = primeNumbers[0];
 System.out.println("index 0: " + firstPrime);

        // Print it:
 System.out.println(Arrays.toString(primeNumbers));

        // Sort it:
 Arrays.sort(primeNumbers);
 System.out.println(Arrays.toString(primeNumbers));

        // Get its length:
 System.out.println(primeNumbers.length); // it's a constant, not a method (we'll talk about this later)

        // See if the value is found in the array; return -1 if not found.
        // Note: binarySearch() only works if the array is sorted:
        int indexOf11 = Arrays.binarySearch(primeNumbers, 11);
 System.out.println("index of 11: " + indexOf11);

        // Check: How do I get the value?
        // if(indexOf11 >= 0) {
 System.out.println("looking for 11, found value: " + primeNumbers[indexOf11]);
        // }
 }
```

#### Error Checking

What if we searched for a value that isn't in the array? Let's see if `10` is a prime number.

<!-- **Instructor Note**: Add the lines below, leaving out the commented code. Throw the exception, ask the students why it was thrown, and ONLY THEN should you add in the >=0 check commented out above and below.-->

```java
int[] primeNumbers = {5,3,11,7,2};

int indexOf10 = Arrays.binarySearch( primeNumbers, 10 );
System.out.println( "index of 10: " + indexOf10 );
  // if(indexOf10 >= 0) {
    System.out.println("looking for 10, found value: " + primeNumbers[indexOf10]);
  // }
```

An exception is thrown when the computer is asked to do something it can't do, like accessing index `-1` or `4` in a four-element array. Exceptions bubble up and, unless caught (which we'll talk about in a later lesson), cause the program to quit.

Consider when the results of our actions might throw an exception and add checks as needed.

> **Knowledge Check**: If an array is five elements long, what happens if we look for `myArray[5]`?

----

## Demo: Problems With Arrays (10 min)

What if we want an array to include four things instead of three? For example, let's go back to our `favoriteThings` array and add `"chocolate"` as the fourth favorite thing:

```java
  public static void addFourthFav() {
    String[] favoriteThings = {"roses","whiskers on kittens","raindrops"};
 favoriteThings[3] = "chocolate"; // check: why 3 and not 4?
    System.out.println(Arrays.toString(favoriteThings));
 }
```

That throws an `ArrayIndexOutOfBoundsException`.

Why? As mentioned, arrays are fixed in size. To add `"chocolate"` as a fourth item in the array, we would have to create a new array of a larger size, copy the info over, and then initialize the additional elements:

```java
  public static void addFourthFav() {
    String[] favoriteThings = {"roses","whiskers on kittens","raindrops"};
    String[] favoriteThingsLarge = new String[4];

    System.arraycopy(favoriteThings, 0, favoriteThingsLarge, 0, 3);
 favoriteThingsLarge[3] = "chocolate";

    System.out.println(Arrays.toString(favoriteThingsLarge));
 }
```

Fortunately, Java has provided something better: **collections**.

----

## Demo: Collections (15 min)

Collections not only provide us with more convenient methods — allowing us to do more things with the data we have — but they also **increase in size automatically** if needed.

Like data types, we can use many different collection classes, each providing distinct functionality. But today, we'll focus on one of them: `ArrayList`.

#### `ArrayList`

Let's take the array we made of favorite things and convert it into an `ArrayList`:

```java
  import java.util.ArrayList;

  public static void main(String[] args) {
      // initialize ArrayList
      ArrayList<String> favoriteThings = new ArrayList<>();
      
      // add items
      favoriteThings.add("bright copper kettles");
      favoriteThings.add("brown paper packages tied up with strings");
 }
```

Note that the data type of each element is defined in angle brackets, `<>`. This data type could be any object type. So, if you created a `Person` object, you could make an `ArrayList` of `Person` (i.e., an `ArrayList<Person>`).

To make a primitive ArrayList, you must use a "boxed" version of that type in the angle brackets. For example, if you want to store type `int` data, you would use an `ArrayList<Integer>`.

#### Manipulating an `ArrayList`

An `ArrayList` is an object with methods, which makes it much easier to manipulate than a simple array.

Check out the following methods:

```java
    // To print, no need to explicitly convert it to a String first
    // because ArrayList has a toString() method, which automatically gets called here:
    System.out.println("favoriteThings = " + favoriteThings);

    // add(item) adds to the end of the list:
    favoriteThings.add("chocolate");
    System.out.println("favoriteThings = " + favoriteThings);

    // add(index, item) adds to the list at the specified index and moves all other entries.
    // Check: What would you have to do with a simple array to do that?
    favoriteThings.add(1, "warm woolen mittens");
    System.out.println("favoriteThings = " + favoriteThings);

    // set(index, item) replaces the item at the given index with a new one:
    favoriteThings.set(0, "tarnished copper kettles");
    System.out.println("favoriteThings = " + favoriteThings);

    // To search for an entry:
    int indexOfIceCream = favoriteThings.indexOf("icecream");
    if(indexOfIceCream != -1) {
      String ic = favoriteThings.get(indexOfIceCream);
      System.out.println("ic = " + ic);
 } else {
      System.out.println("icecream not found");
 }

    // Here's another good one: To get the number of things in the ArrayList:
    favoriteThings.size();
```

> **Knowledge Check**: What are the advantages of using an `ArrayList` over an array?

-----

## Guided Practice: Iterating Through a List With `for` Loops (15 min)

#### The `for` Loop

Remember the `for` loop? We used it several times in the control flow lesson to print something to the command line.

> **Knowledge Check**: What's the syntax of a basic `for` loop?

The `for` loop is also commonly used with arrays and collections to iterate through each element and perform a task with each entry.

For example, let's create a list of five movies and iterate through it, printing each one to the command line.

<!-- **Instructor Note**: Get movie suggestions from the class. -->

```java
  public static void main(String[] args) {
    ArrayList<String> movieList = new ArrayList<>();
    movieList.add(/*student suggestion here*/); // x5
    printMovies(movieList);
 }

  public static void printMovies(ArrayList<String> movies) {
    for (int i = 0; i < movies.size(); i++) {
      System.out.println("where i = " + i + ": " + movies.get(i));
 }
 }

```

To recap, a `for` loop has an initialization stage (where `i` is initialized), a termination condition (which includes the logic for when the loop stops), and an increment stage (which will occur upon the completion of each loop).

#### The Enhanced `for` Loop

Another `for` loop syntax is made especially for arrays and lists. This is sometimes referred to as the **enhanced** `for` loop:

```java
 for (String movie : movieList) {
  System.out.println("I love " + movie + "!");
 }
```

The enhanced `for` loop is the form Oracle recommends for arrays and collections.

A normal `for` loop is still useful sometimes: It makes it easier to work with the index of each item alongside its value or modify the collection while you're iterating over it. But, if you need to do something with each element, the enhanced `for` loop is cleaner and more efficient.

> **Knowledge Check**: Why might you want to iterate through an array?

----

## Independent Practice: Arrays and Array Lists (15 min)

In the next 15 minutes, work through as many of the following challenges as you can. Each should be completed using its own method.

#### Find the Size

1. Create an array of type `int`.
1. Print the length of the array to the command line.

#### Concrete Jungle

1. Create an `ArrayList` of New York City wildlife.
1. Create a function that prints for each element, given an `ArrayList` of strings: "Today, I spotted a /*Thing here*/ in the concrete jungle."

#### Sum It Up 

Create a method that, given an array of type `int`, returns the sum of the first two elements in the array. If the array length is `1`, return the single element, and return `0` if the array is empty (has `length 0`).
    
 -----   

## Conclusion (5 min)

Quick review:
- List some differences between arrays and array lists.

Arrays and array lists are fundamental tools for storing and manipulating data collections. Now that you know how to use them, you can start creating apps with collections of data. Excited?

### Additional Resources
- [Oracle Java Docs: Arrays](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/arrays.html)
- [Oracle Java Docs: The `for` Statement](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/for.html)
- [CodingBat: Array Code Practice](https://codingbat.com/java/Array-1)
- [Autoboxing](https://docs.oracle.com/javase/tutorial/java/data/autoboxing.html)

