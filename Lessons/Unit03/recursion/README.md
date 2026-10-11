# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Exploring Recursion

## Introduction

Recursion is a powerful concept in programming where a method calls itself to solve problems that can be broken down into smaller, similar sub-problems. It's a key technique in many algorithms, especially those dealing with data structures like trees and graphs.

## Learning Objectives

By the end of this lesson, students will be able to:

- Understand the basic principles of recursion, including direct and indirect recursion.
- Implement recursive algorithms to solve problems such as summing elements in a list and checking if a word is a palindrome.
- Analyze the benefits and drawbacks of using recursion in programming.

## Lesson Overview (90 minutes)

1. [Understanding Recursion](#Understanding-Recursion)
2. [Implementing Recursion with Summation](#Implementing-Recursion-with-Summation)
3. [Recursively Solving Palindromes](#Recursively-Solving-Palindromes)
4. [Summary and Takeaways](#Summary-and-Takeaways)

## Understanding Recursion

Recursion occurs when a method calls itself, either directly or indirectly through another method. This concept can be
seen in single or multiple recursive calls within the same method.

Understanding recursive algorithms can be challenging as it is not how humans typically approach problem-solving. For
instance, when painting a fence, most people would start at one end and continue to the other, rather than splitting the
task into smaller, recursive parts. However, certain structures, like trees, inherently lend themselves to recursive
solutions due to their natural, self-repeating patterns.

## Thought experiment

Let's explore a practical implementation of recursion by summing a list of numbers. It could be any numbers, such as the number of marbles you and each of your friends have in their pockets. The Java code below demonstrates a non-recursive approach and sets the stage for our recursive version:

```java
import java.util.ArrayList;
import java.util.List;

public class SumNumbersNoRecursive {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(10);
        list.add(9);
        int sum = 0;
        for (Integer integer : list) sum += integer;
        System.out.println("sum-> " + sum);
    }
}
```
## Implementing Recursion with Summation

Imagine if the creators of Java decided to eliminate any form of iteration, such as for and while loops. You still need to sum a list of numbers, so how could you accomplish this without using loops?

Computer scientists and some savvy coders use a different approach to break down problems, which might initially seem like magic. Let's explore this method by revisiting the problem of summing a list of numbers. This approach involves two key components: a base case and a recursive case.

The base case is the simplest scenario you can think of. In this context, what is the simplest list of numbers you can sum? An empty list, which sums to zero, of course!

Next is the recursive case, where we solve a smaller version of the same problem. Here's how it works: take the first item in the list and add it to the sum of the rest of the list. This way, the problem of summing the list is reduced step by step until it reaches the base case.

Here's how you would write the summation using recursion if iteration tools were unavailable:

```java
import java.util.ArrayList;
import java.util.List;

public class SumNumbersRecursive {
    // Method to calculate sum recursively
    public static int sumOfArray(Integer[] a, int n) {
        if (n == 0)
            // base case
            return a[n];
        else
            // recursive case
            return a[n] + sumOfArray(a, n - 1);
    }

    // Method- main()
    public static void main(String[] args) {
        // Creating a List of Integer type
        // Declaring an object- 'al'
        List<Integer> al = new ArrayList<Integer>();

        // Adding elements to the List
        // Custom inputs
        al.add(1);
        al.add(2);
        al.add(3);
        al.add(4);
        al.add(5);

        // Converting above List to array
        // using toArray() method
        Integer a[] = new Integer[al.size()];
        al.toArray(a);

        // Display message
        System.out.print("Elements in List : ");

        // Printing array of objects
        // using for each loop
        for (Integer obj : a) {
            System.out.print(obj + " ");
        }

        // Recursion math to calculate sum snd
        // storing sum in a variable
        int sum = sumOfArray(a, a.length - 1);

        // Next line
        System.out.println();

        // Print the sum returned above
        System.out.println("Sum of elements  : " + sum);
    }
}
```
## Recursively Solving Palindromes
Adapting your brain to think recursively can indeed require extra effort, but the payoff is immense—think less about the
challenge as a struggle, and more as an opportunity for growth. While we could spend considerable time dissecting the
SumNumbersRecursive example, the real mastery of recursive thinking comes from practice. The more you engage with
recursive problems and code them, the more intuitive this style of thinking will become.

Consider palindromes—words or phrases that read the same forward and backward, like “tacocat.” Other examples include
“madam,” “radar,” and “kayak,” or even entire phrases such as “a nut for a jar of tuna” or “a man, a plan, a canal:
panama” once you disregard punctuation and whitespace.

So, how about writing a recursive function to detect palindromes? What would we gain? Let’s explore. Writing a recursive
function typically involves two components: a base case and a recursive case:

- **Base case**: The simplest scenario you can directly solve. An empty string is a palindrome by default since it reads
  the same forwards and backwards. Similarly, a single letter is always a palindrome because it remains unchanged when
  reversed.
- **Recursive case**: This involves reducing the problem size slightly and then applying the function recursively. For
  instance, if the first and last characters of a string match, the string is a palindrome if the substring between them
  is also a palindrome.

Armed with our base and recursive cases, we're prepared to develop our recursive solution. Implementing the base case is
straightforward: check if the string is empty or a single character. The recursive case involves more nuanced thinking:
after confirming the first and last characters match, the remainder of the string (excluding these characters) is
checked recursively for palindrome properties. If they don't match, we immediately return False.

This structured approach to recursion not only makes the problem more manageable but also reinforces the recursive
mindset, helping you solve increasingly complex problems with this elegant method.

A palindrome is a sequence that reads the same forwards and backwards. Recursive solutions can elegantly check for palindromes:

```java
import java.util.Scanner;

public class PalindromeCheckRecursive {
    public static boolean isPal(String s) {
        // if length is 0 or 1 then String is palindrome
        if (s.isEmpty() || s.length() == 1)
            return true;
        if (s.charAt(0) == s.charAt(s.length() - 1))
            /* check for first and last char of String:
             * if they are same then do the same thing for a substring
             * with first and last char removed. and carry on this
             * until you string completes or condition fails
             * Function calling itself: Recursion
             */
            return isPal(s.substring(1, s.length() - 1));

        /* If program control reaches to this statement it means
         * the String is not palindrome hence return false.
         */
        return false;
    }

    public static void main(String[] args) {
        //For capturing user input
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the String for check:");
        String string = scanner.nextLine();
        /* If function returns true then the string is
         * palindrome else not
         */
        if (isPal(string))
            System.out.println(string + " is a palindrome");
        else
            System.out.println(string + " is not a palindrome");
    }
}
```

### No Question is Too Simple

- **Question**: How can we be sure a recursive function will eventually terminate?
    - **Answer**: The key to understanding when a recursive function will stop is the concept of the base case. This is
      a specific scenario within the problem that can be solved directly, without further recursion. Once the function
      reaches this base case, it stops calling itself, effectively ending the recursion.
- **Question**: How do we ensure that the function will reach the base case?
    - **Answer**: Each recursive call typically works with a slightly smaller or simpler version of the original
      problem. As long as the problem is continuously reduced in size or complexity at each step, and the reduction is
      correctly designed, the function will inevitably converge on the base case.
- **Question**How do function parameters remain consistent across recursive calls without getting mixed up?
    - **Answer**: Excellent observation! In recursion, each function call has its own separate execution context,
      including parameters and local variables. This isolation means that changes to parameters or variables in one
      recursive call do not affect those in previous or future calls. Even though it might seem as if the same function
      is being called, each instance operates independently with its own set of data, thanks to how modern programming
      languages manage function calls' execution stacks. This management ensures that each recursive call is
      self-contained and consistent.

## Summary and Key Takeaways

### Summary

This lesson on recursion has covered both theoretical and practical aspects of recursive programming, providing a foundational understanding of how recursive functions operate and their application in solving common computational problems.

### Key Takeaways

- **Recursive Thinking**: Transitioning to recursive thinking requires viewing problems as a series of smaller,
  self-similar tasks.
- **Base and Recursive Cases**: Effective recursive functions have clear base cases for direct solutions and recursive
  cases for reducing problem size.
- **Problem Suitability**: While recursion can provide elegant solutions for problems like tree traversal, summing
  elements, or checking palindromes, it's important to use it when it enhances clarity and efficiency.
- **Memory Considerations**: Each recursive call adds a layer to the call stack, which can lead to stack overflow in
  cases of deep recursion or large input sizes.

### Continuing Practice

To further solidify your understanding of recursion:
- Try solving more complex problems using recursion, such as sorting algorithms (e.g., quicksort, mergesort) or graph traversal algorithms (e.g., depth-first search).
- Experiment with converting traditional iterative algorithms to recursive ones to understand the trade-offs between the two approaches.
- Discuss and share your recursive solutions with peers or mentors to get feedback and new insights.

### Reflection

Reflect on how recursion has changed or enhanced your approach to programming. Consider how you might apply recursive thinking to other areas of problem-solving, both within and outside of software development.






