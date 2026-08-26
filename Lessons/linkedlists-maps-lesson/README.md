---
title: Linked Lists and Maps
type: Lesson
duration: "1:30"
creator:
    name: Drew Mahrt
    city: NYC
---

# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Linked Lists and Maps

### Learning Objectives
At the end of this lesson, students will be able to:
- Create and manipulate linked lists.
- Compare arrays and linked lists.
- Convert a list into an array (and vice versa).
- Describe and implement a hash map.

---

### Lesson Guide

| Timing  | Type  | Topic  |
|:-:|---|---|
| 5 min  | [Opening](#opening-5-min)  | Learning Objectives |
| 10 min | [Introduction](#introduction-lists-10-min)  |  Lists |
| 5 min  | [Demo](#demo-lists-5-min)  | Lists |
| 10 min | [Independent Practice](#independent-practice-lists-10-min)  | Lists |
| 10 min | [Introduction](#introduction-maps-10-min)  |  Maps |
| 10 min | [Demo](#demo-maps-10-min)  | Maps |
| 10 min | [Guided Practice](#guided-practice-maps-10-min)  | Maps |
| 15 min | [Independent Practice](#independent-practice-collections-15-min)  | Collections |
| 5 min  | [Conclusion](#conclusion-5-min)  | Review/Recap |

## Opening (5 min)

One of Java's most commonly used libraries is the `Collections` class. We've already used arrays and array lists. Today, we'll be covering lists and maps in more detail.

We'll learn about and compare them today to understand their strengths and weaknesses and how to decide which to use. Once you better understand how to organize the information in your apps, you'll be able to write much cleaner and more efficient code.

> **Knowledge Check**: As a quick review, what are some differences between arrays and array lists?

***

## Introduction: Lists (10 min)

First, we'll cover lists, one of the most popular collection types. They hold data collections and provide a large set of helper functions that make it much easier to manipulate data. You can search, sort, add, and remove with ease. However, one of the significant drawbacks of lists is that they have a larger overhead than a simple array (i.e., they have more memory but are slightly slower because of all the behind-the-scenes work).

The two most commonly used types of lists are the `ArrayList` and the `LinkedList`. We've already met the `ArrayList` — it's backed by an array to hold data. One significant advantage of the `ArrayList` over arrays is that you don't need to create larger arrays if your data set grows manually. Remember that because `ArrayList` takes care of this for you, the operation isn't free. Therefore, an `ArrayList` isn't the best choice for rapidly growing or shrinking data sets.

<!--**Instructor Note**: Draw out nodes on the board to illustrate a `LinkedList`.-->

A `LinkedList` is backed by a collection of objects linked together by pointers to each memory location. While we won't be going over how these are implemented, it's important to know that each element in the list is stored in empty spots in memory, so you don't need to worry about the changing size of the data set. Data is inserted and removed like links in a chain. Accessing a specific element in a linked list is generally slower than an array because it isn't stored in sequential memory.

<details>

<summary>Knowledge Check: What are some examples of use cases for ArrayList and LinkedList?</summary>

  - `ArrayList`: Contact lists
  - `LinkedList`: To-do lists

</details> 

***

## Demo: Lists (5 min)

Let's look at how to define lists.

We'll begin by reviewing how to define an `ArrayList`. Let's create an `ArrayList` called `cookieFlavors`.

<!-- **Instructor Note**: Have students shout out or walk you through how to do this.-->

```java
  public static void main(String[] args) {
      // initialize ArrayList
      ArrayList<String> cookieFlavors = new ArrayList<>();
      
      // add items
      cookieFlavors.add("chocolate chip");
      cookieFlavors.add("oatmeal raisin");
 }
```

**Fun fact**: You can convert between arrays and lists easily:

``` java
String[] cookieFlavors = new String[]{"chocolate chip", "oatmeal raisin"};
List<String> stringArrayList = Arrays.asList(cookieFlavors);
```

The process for creating a `LinkedList` and an `ArrayList` is the same, but with the class name changed:

```java
  import java.util.LinkedList;
  public static void main(String[] args) {
      // initialize LinkedList
      LinkedList<String> iceCreamFlavors = new LinkedList<>();
      
      // add items
      iceCreamFlavors.add("Oreo");
      iceCreamFlavors.add("Cherry Garcia");
 }
```

***

## Independent Practice: Lists (10 min)

1. Create a `LinkedList` containing the numbers 1–10.
2. Test to see if the values 5–15 exist in the list and remove the existing elements (i.e., remove 5–10).
3. Finally, print the size of the list.

***

## Introduction: Maps (10 min)

The following `Collections` type we'll cover is the **map**. A map is similar to a list, but each entry contains two parts: a key and a value. The key is unique in the map; think of it like the index in an array. Each key maps to a certain value, but a map can have duplicate values. 

One of the most popular implementations of a map is the `HashMap`. A `HashMap` is a map where the data is stored in an array. The index where the data is stored is generated by a hash function. You give the `HashMap` the object you want to use as a key, and it returns an integer to use as the index. When you try to retrieve the value from the `HashMap`, it uses the same function to generate the same index. `String`s are often used as the keys for a `HashMap`.

> **Knowledge Check**: Create an example using a `Map`.

***

### Collision (10 min)

The hash function isn't flawless. What happens if it gives us the same index for two keys? This is called a **collision**.

> **Knowledge Check**: How might this be resolved?

Java handles this by using a structure like a `LinkedList` instead of the actual objects in the `HashMap`. If a collision occurs, the object is added to the end of that list.

***

## Demo: Maps (10 min)

Let's try implementing a `HashMap`. Let's say we have a `Student` class containing the student's name, grades, and more. Each student has a unique student ID we'll use as the key:

``` java
HashMap<String,Student> studentMap = new HashMap<String,Student>();

studentMap.put("195abc",mabelStudent);
studentMap.put("542ijk",kareemStudent);
...
Student retrievedStudent1 = studentMap.get("195abc"); // mabelStudent
Student retrievedStudent2 = studentMap.get("111"); // null

studentMap.remove("542ijk");

```

As you can see, a map isn't different from a list: You can add and retrieve objects. One significant difference is you have no control over the underlying data structure. There's no way for you to sort or control the order of elements in a map.

> **Knowledge Check**: Write the code for removing an object through the key. You'll probably have to look it up in the documentation.

***

## Guided Practice: Maps (10 min)

Let's create a `HashMap` that represents a dictionary. The key will be the word, and the value will be the definition. We must add the value to the map, retrieve it, and print it to the console.

For example:
- `"apple"`: `"A fruit from a tree"`
- `"lake"`: `"A large body of water"`

***

<a name="ind-practice"></a>

## Independent Practice: Collections (15 min)

- Create a `LinkedList` of strings with the values `{"One","Two","Three","Four"}`.
- Create a `HashMap` with the keys `{"One","Two","Three","Four"}` taken from the list and the integer values `{1,2,3,4}` taken from the array.
 - Print out the `HashMap` size after adding the above items.
 
<details>
    <summary>Solution code</summary>

```java
import java.util.HashMap;
import java.util.LinkedList;

class Main {
  public static void main(String[] args) {
    LinkedList<String> keys = new LinkedList<String>();
    keys.add("One");
    keys.add("Two");
    keys.add("Three");
    keys.add("Four");

    HashMap<String, Integer> combos = new HashMap<String, Integer>();
    combos.put(keys.get(0), 1);
    combos.put(keys.get(1), 2);
    combos.put(keys.get(2), 3);
    combos.put(keys.get(3), 4);

    System.out.println(combos.size());
    System.out.println(combos);
 }
}
```
</details>

***

## Conclusion (5 min)

We've covered the major components of the `Collections` class and how to use them. From sorting to searching, collections make organizing your data fast and easy. Arrays, lists, and maps will become integral parts of your apps as you continue development, and working with them will become second nature.

***

### Additional Resources

- [Oracle: `Array`](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/arrays.html)
- [Oracle: `List`](https://docs.oracle.com/javase/8/docs/api/java/util/List.html)
- [Oracle: `HashMap`](https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html)
