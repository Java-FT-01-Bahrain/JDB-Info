# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Lab Lesson 2: Manipulating Linked Lists

## Introduction

In this lab, you will tackle practical challenges involving linked lists, a fundamental data structure in computer
science. You will enhance your understanding by implementing functions to manipulate linked lists to perform specific
tasks, such as arithmetic operations and reordering elements based on their properties.

## Learning Objectives

By the end of this lab, you will be able to:

1. Add two numbers represented as reversed linked lists and return the result as a new linked list.
2. Rearrange a linked list such that all even numbers appear before any odd numbers while maintaining the original
   relative order of even and odd elements.

## Table of Contents

1. [Adding Two Numbers](#adding-two-numbers)
2. [Rearranging Even and Odd Numbers](#rearranging-even-and-odd-numbers)
3. [Summary and Takeaways](#summary-and-takeaways)

## Adding Two Numbers

### Description

You are given two **non-empty** linked lists representing two non-negative integers. The digits are stored in reverse
order, with each node containing a single digit. Your task is to add these two numbers and return the sum as a linked
list.

```
Input: l1 = [2,4,3], l2 = [5,6,4]
Output: [7,0,8]
Explanation: 342 + 465 = 807.

Input:** l1 = [0], l2 = [0]
Output:** [0]

Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
Output: [8,9,9,9,0,0,0,1]
```

### Instructions

- Implement a class `AddTwoNumbers` with a method that takes two linked lists as inputs and returns the sum as a linked
  list.
- Consider edge cases such as different lengths of lists and carry-over during addition.

## Rearranging Even and Odd Numbers

### Description

Given a linked list of integers, write a function to modify the list so that all even numbers appear before all the odd
numbers in the modified linked list, maintaining the original order of even and odd numbers.

```
Input: 17->15->8->12->10->5->4->1->7->6->NULL
Output: 8->12->10->4->6->17->15->5->1->7->NULL
```

### Instructions

- Implement a class `ExchangeEvenOddList` with a method that rearranges the nodes in a linked list based on their
  values.
- Ensure the original relative order of even and odd numbers is preserved.

## Summary and Takeaways

This lab provided hands-on experience with manipulating linked lists for arithmetic operations and customized
reordering. Through these exercises, you should have deepened your understanding of linked list operations and their
applications in solving practical problems. Key takeaways include:

- The ability to manipulate the pointers in a linked list to solve complex problems.
- Insights into optimizing operations on linked lists for efficiency and clarity.

Remember to review your solutions and reflect on how different approaches can affect the performance and readability of
your code.