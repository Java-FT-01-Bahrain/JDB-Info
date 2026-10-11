# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Lab 3: Recursive Functions Lab

## Introduction

Recursion is a fundamental concept in computer science where a function calls itself to solve smaller instances of the same problem. This approach can simplify the solution of complex problems by breaking them down into more manageable sub-problems. In this lab, you will explore various recursive functions and learn how to implement them in Python. Each exercise is designed to enhance your understanding of recursion and its applications.

## Learning Objectives

By the end of this lab, you will be able to:
- Understand the concept of recursion and its importance in programming.
- Implement recursive functions to solve mathematical problems.
- Apply recursion to manipulate strings and solve combinatorial problems.
- Develop problem-solving skills by breaking down complex problems into simpler recursive cases.

## Lesson Overview (120 minutes)

1. [Exercise 1: Factorial](#exercise-1-factorial)
2. [Exercise 2: Bunny Ears](#exercise-2-bunny-ears)
3. [Exercise 3: Fibonacci](#exercise-3-fibonacci)
4. [Exercise 4: Bunny Ears 2](#exercise-4-bunny-ears-2)
5. [Exercise 5: Triangle](#exercise-5-triangle)
6. [Exercise 6: Sum of Digits](#exercise-6-sum-of-digits)
7. [Exercise 7: Count 7](#exercise-7-count-7)
8. [Exercise 8: Count 8](#exercise-8-count-8)
9. [Exercise 9: Count "hi"](#exercise-9-count-hi)
10. [Exercise 10: Change "x" to "y"](#exercise-10-change-x-to-y)
11. [Exercise 11: Remove "x"](#exercise-11-remove-x)
12. [Exercise 12: Pair Star](#exercise-12-pair-star)
13. [Exercise 13: String Clean](#exercise-13-string-clean)
14. [Exercise 14: Count "hi" (except with "x" before it)](#exercise-14-count-hi-except-with-x-before-it)
15. [Exercise 15: Largest Substring](#exercise-15-largest-substring)
16. [Bonus Probelm](#Bonus-Problem-Reversing-a-String)
17. [Key Takeaways & Overview](#key-takeaways-overview)

## Exercise 1: Factorial
Given `n` of `1` or more, return the factorial of `n`, which is `n * (n-1) * (n-2) ... 1`. Compute the result
   recursively (without loops).
   ```
   factorial(1) → 1
   factorial(2) → 2
   factorial(3) → 6
   ```
   
## Exercise 2: Bunny Ears
We have a number of bunnies and each bunny has two big floppy ears. We want to compute the total number of ears across all the bunnies recursively (without loops or multiplication).
   ```
   bunnyEars(0) → 0
   bunnyEars(1) → 2
   bunnyEars(2) → 4
   ```
   
## Exercise 3: Fibonacci
The fibonacci sequence is a famous bit of mathematics, and it happens to have a recursive definition. The first two
   values in the sequence are `0` and `1` (essentially 2 base cases). Each subsequent value is the sum of the previous
   two values, so the whole sequence is: `0, 1, 1, 2, 3, 5, 8, 13, 21` and so on. Define a recursive `fibonacci(n)`
   method that returns the nth fibonacci number, with `n=0` representing the start of the sequence.
   ```
   fibonacci(0) → 0
   fibonacci(1) → 1
   fibonacci(2) → 1
   ```
   
## Exercise 4: Bunny Ears 2
We have bunnies standing in a line, numbered `1, 2, ...` The odd bunnies `(1, 3, ..)` have the normal `2` ears. The
   even bunnies `(2, 4, ..)` we'll say have `3` ears, because they each have a raised foot. Recursively return the
   number of "ears" in the bunny line `1, 2, ... n` (without loops or multiplication).
   ```
   bunnyEars2(0) → 0
   bunnyEars2(1) → 2
   bunnyEars2(2) → 5
   ```

## Exercise 5: Triangle
We have triangle made of blocks. The topmost row has `1` block, the next row down has `2` blocks, the next row
   has `3` blocks, and so on. Compute recursively (no loops or multiplication) the total number of blocks in such a
   triangle with the given number of rows.
   ```
   triangle(0) → 0
   triangle(1) → 1
   triangle(2) → 3
   ```
   
## Exercise 6: Sum of Digits
Given a non-negative int n, return the sum of its digits recursively (no loops). Note that `mod (%)` by `10` yields
   the rightmost digit `(126 % 10 is 6)`, while divide `(/)` by `10` removes the rightmost digit `(126 / 10 is 12)`.
   ```
   sumDigits(126) → 9
   sumDigits(49) → 13
   sumDigits(12) → 3
   ```
   
## Exercise 7: Count 7
Given a non-negative int n, return the count of the occurrences of `7` as a digit, so for example `717` yields `2`. (
   no loops). Note that mod `(%)` by `10` yields the rightmost digit `(126 % 10 is 6)`, while divide `(/)` by `10`
   removes the rightmost digit `(126 / 10 is 12)`.
   ```
   count7(717) → 2
   count7(7) → 1
   count7(123) → 0
   ```

## Exercise 8: Count 8
Given a non-negative int n, compute recursively (no loops) the count of the occurrences of `8` as a digit, except
   that an `8` with another `8` immediately to its left counts double, so `8818` yields `4`. Note that mod `(%)` by `10`
   yields the rightmost digit `(126 % 10 is 6)`, while divide `(/)` by `10` removes the rightmost
   digit `(126 / 10 is 12)`.
   ```
   count8(8) → 1
   count8(818) → 2
   count8(8818) → 4
   ```

## Exercise 9: Count "hi"
Given a string, compute recursively (no loops) the number of times lowercase `hi` appears in the string.
   ```
   countHi("xxhixx") → 1
   countHi("xhixhix") → 2
   countHi("hi") → 1
   ```

## Exercise 10: Change "x" to "y"
Given a string, compute recursively (no loops) a new string where all the lowercase `x` chars have been changed
    to `y` chars.
   ```
   changeXY("codex") → "codey"
   changeXY("xxhixx") → "yyhiyy"
   changeXY("xhixhix") → "yhiyhiy"
   ```

## Exercise 11: Remove "x"
Given a string, compute recursively a new string where all the `x` chars have been removed.
   ```
   noX("xaxb") → "ab"
   noX("abc") → "abc"
   noX("xx") → ""
   ```

## Exercise 12: Pair Star
Given a string, compute recursively a new string where identical chars that are adjacent in the original string are
    separated from each other by a `*`.
   ```
   pairStar("hello") → "hel*lo"
   pairStar("xxyy") → "x*xy*y"
   pairStar("aaaa") → "a*a*a*a"
   ```

## Exercise 13: String Clean
Given a string, return recursively a **cleaned** string where adjacent chars that are the same have been reduced to
    a single char. So `yyzzza` yields `yza`.
   ```
   stringClean("yyzzza") → "yza"
   stringClean("abbbcdd") → "abcd"
   stringClean("Hello") → "Helo"
   ```

## Exercise 14: Count "hi" (except with "x" before it)
Given a string, compute recursively the number of times lowercase `hi` appears in the string, however do not
    count `hi` that have an `x` immediately before them.
   ```
   countHi2("ahixhi") → 1
   countHi2("ahibhi") → 2
   countHi2("xhixhi") → 0
   ```
## Exercise 15: Largest Substring
Given a string and a non-empty substring sub, compute recursively the largest substring which starts and ends with
    sub and return its length.
   ```
   strDist("catcowcat", "cat") → 9
   strDist("catcowcat", "cow") → 3
   strDist("cccatcowcatxx", "cat") → 9
   ```

## Bonus Problem: Reversing a String
Create a recursive function to reverse a string.

**Instructions**:
1. Define a function reverseString(s) that takes a string s.
2. Implement the base case: if the string is empty, return an empty string.
3. Implement the recursive case: return the last character of s plus the result of reverseString on all but the last character.
4. Test your function with various strings, such as "hello", "recursion", and "12345".

## Key Takeaways & Overview

### Key Takeaways
- **Recursive Thinking**: Recursion involves solving a problem by solving smaller instances of the same problem.
- **Base Case and Recursive Case**: Every recursive function must have a base case to terminate and a recursive case to call itself.
- **Problem-Solving**: Recursion can simplify the solution of complex problems, making the code more intuitive and easier to understand.
- **Applications**: Recursion is widely used in algorithms, such as sorting, searching, and navigating data structures like trees and graphs.

### Overview
In this lab, you learned to implement various recursive functions to solve problems ranging from mathematical calculations to string manipulations. By breaking down each problem into smaller, more manageable parts, you developed a deeper understanding of how recursion works and its benefits. Recursion is a powerful tool in your programming toolkit, and mastering it will greatly enhance your problem-solving abilities.
