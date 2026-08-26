| Title | Type | Duration | Creator |
| --- | -- | -- | --- |
| Subclasses | Lesson | 1:00 | Suresh Sigera |


# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Subclasses

### Learning Objectives

At the end of this lesson, students will be able to:
- Create a subclass.
- Use a subclass to augment the functionality of the base class.

### Lesson Guide

| Timing  | Type  | Topic  |
|:-:|---|---|
| 5 min  | [Opening](#opening-5-min)  | Learning Objectives | 
| 15 min | [Guided Practice](#guided-practice-superclasses-and-subclasses-15-min) | Superclasses and Subclasses |
| 15 min | [Guided Practice](#guided-practice-upcasting-and-downcasting-15-min) | Upcasting and Downcasting |
| 10 min | [Conclusion](#conclusion-10-min)  | Review/Recap |

## Initial Setup (5 minutes)

For instructions on how to fork and clone the lab, please refer to
this [link](https://git.generalassemb.ly/ENT-JAVA-Accelerator/start-here/blob/main/SETUP.md#how-do-i-fork-and-clone-lessonslabs).

## Opening (5 min)

In our use of language and observations of the world, we naturally categorize objects of similar types. For example, we understand what a cat is and that a lion is a member of the cat family. Although we have no confusion between a cat and a lion, we know that lions share qualities with cats.

![](resources/cat-lion.png)

<!-- Taken from https://odditymall.com/includes/content/thumb1366252920.png. -->

We can say that `Lion` is a subclass of `Cat` and will inherit basic properties and functionality from its **superclass** (its parent class). 

> **Note**: You may also hear subclasses and superclasses referred to as "parent" and "child" classes, which may help visualize what is meant by "inheritance."

-----

## Guided Practice: Superclasses and Subclasses (15 min)

Let's create a `Cat` class: 

```java
public class Cat {
    private String name;
    
    public String getName() {
        return name;
 }

    public void setName(String name) {
        this.name = name;
 }

    public boolean hasWhiskers() {
        return true;
 }

    protected String favoriteSnack() {
        return "Mice";
 }

    public int getLives() {
        return 9;
 }
}
```

We create a subclass of `Cat` by using the `extends` keyword:

````java
class Lion extends Cat {
 @Override
    public int getLives() {
        return 1;
 }
}
````

In this case, we say `Lion` `extends` `Cat`, or `Lion` is a **subclass** of `Cat`. And we say `Cat` is a **superclass** of `Lion`. 

The `Lion` class inherits members from its superclass, so the calls below are legal. Even though they're not in the `Lion` class, they're inherited from the `Cat` class:

```java
Lion lion = new Lion();
lion.hasWhiskers();     
lion.favoriteSnack();  
lion.getLives();       
```

> **Think**: What will each of the calls above return?

<details>
<summary>Answers</summary>

* `hasWhiskers()` returns `true` (inherited from the `Cat` class).
* `favoriteSnack()` returns `"Mice"` (inherited from the `Cat` class).
* `getLives()` returns `1`. Why? A cat may have nine lives, but a `Lion` doesn't because its `getLives()` method was **overridden**.

</details>

A subclass will inherit every field and method it can see, i.e., every `public` member, `protected` member, and default member (provided they're in the same package).

`private` members can't be inherited, except by classes defined in the **same file**.
 
### Best Practice: `@Override`

For the `Lion` subclass, notice how we put `@Override` above the `getLives()` method:

````java
class Lion extends Cat {
 @Override
    public int getLives() {
        return 1;
 }
}
````

It's best practice to annotate an overridden method by placing the `@Override` annotation above it. That tells the compiler to ensure it overrides a base class method.

Although this isn't required, it's an excellent defensive practice. In the future, if the base method happens to get renamed along the way, the code will fail to compile (because it's no longer overriding anything). Therefore, this will help with the early detection of some subtle bugs that result from calling an overridden method, which is a new method.

An overridden method can be of the same visibility as its overriding method or be more public. However, it can't be more private.

Here are a few quick-hit facts about annotations:
* They start with the `@` symbol.
* They don't affect code functionality.
* They do affect the compiler's actions.
* They're not required, but it's best practice to use them.

> For more details about annotations, read through [this article](https://www.geeksforgeeks.org/annotations-in-java/).

### Restricting Inheritance

Sometimes, you want to ensure that no subclasses can be created from your class. In such cases, you can declare your class to be `final`. If you try to make a subclass from a `final` class, the compiler will warn you with a message such as:

```java
Error: java: cannot inherit from final class
``` 

Sometimes, you may want your class to be inherited from, but you don't want the subclass to change a particular method or field in your class. If needed, specific members can be marked `final`.

A subclass can override any method visible to it — except if the method is `final` or `static` — by defining the method using the same name and signature as the base method it's overriding.

-----

## Guided Practice: Upcasting and Downcasting (15 min)

There's a subtle feature going on here that's leveraged in just about every Java library you'll use: You can declare a variable of a particular type and assign it any subtype.

For example:

````java
Cat cat = new Lion();
````

> **Knowledge Check**: If you call `cat.getLives()` after declaring it as we did above, what do you expect will be returned: the nine lives of the `Cat` or the one life of the `Lion`?

<details>
<summary>The answer may be surprising.</summary>

The rule to remember is that when you call a method on a variable, you call the method on the instance contained in that variable. So, in our case, because the `cat` variable contains a `Lion` instance, the `getLives()` method will return the one life of the `Lion` and not the nine of the parent `Cat`. This is generally a good thing because library vendors can now define subclasses, and they'll function as the vendor intends.

In plain English, maybe you want a collection (like an `Array` or `ArrayList`) of cats, but you want to be able to put lions, cats, and anything else in the cat family — say tigers or leopards — in that same collection. That's known as **upcasting**.

Did the answer surprise you?

</details>

### Downcasting

Downcasting also works as long as you're casting to the correct object type.

So, we can say `Cat cat = new Lion()`, which — as we saw above — works fine because a lion is a cat. But we can't say `Lion lion = new Cat()` because, in general, cats are not lions.

However, given the assignment `Cat cat = new Lion()`, we know the `cat` variable contains a `Lion` instance; therefore, we should be able to assign it:

````java
Cat cat = new Lion();
Lion lion = cat;
````

However, that fails to compile because the compiler has no way to be sure that, by the time the `cat` is assigned, it still contains a `Lion` instance. All lions are cats, but not all cats are lions.

To make that assignment, you must explicitly downcast it using the **cast** operator as follows:

```java
Lion lion = (Lion)cat;
```

By preceding the `cat` variable with `(Lion)`, you're **casting** the `cat` instance to a `Lion` class, which is your way of signing a pact with the compiler guaranteeing that the variable will contain a `Lion` instance.

And if you break that pact, like below...

```java
public static void main(String[] args) {
    Cat cat = new Cat();
    Lion lion = (Lion)cat;
}
```
...You'll get a `ClassCastException` from the compiler.

----


## Conclusion (10 min)

That was an intense lesson! Let's recap.

### Tying Back to the Four Pillars

Combining subclasses and visibility, we can hide the functionality details inside a class and expose an API (application programming interface). Now that we've got inheritance in our tool belt, encapsulation (including things like the access level), abstraction, and polymorphism become much more salient.

#### Remember Polymorphism?

Polymorphism may sound scary, and it will keep cropping up. However, the concept is easier to understand than the word suggests.

Now that we have a specific example of polymorphism let's revisit the topic. We can create various subclasses of the same class and implement particular functionality for each.

In our first example, we had `Cat` and `Lion`, both of which had a `getLives()` method. However, their implementation was different despite sharing the same name.

In our second example, we had five different shapes — `Shape`, `Triangle`, `Circle`, `Rectangle`, and `Square` — all with varying implementations of their `getCircumference()` and `getArea()` functionality. Yet they all worked just fine, and we didn't need to alter how we used the instance of each shape.

### Summary

Inheritance is here for our convenience and to keep our code DRY (that stands for "Don't Repeat Yourself"). It might seem like an intimidating concept, but once you master it, it can be a powerful tool. In real life, you'll find many examples of inheritance, shared properties, and functionality, precisely what our programming languages are meant to emulate and model.
