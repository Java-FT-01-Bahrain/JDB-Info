# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Understanding Bubble Sort

## Introduction

Sorting algorithms are a fundamental topic in computer science, with numerous variations developed over the years. These
algorithms address a common challenge: sorting an unsorted array into ascending order. In this lesson, we will explore several sorting algorithms, starting with the simpler ones. These "simple sorts" are straightforward and serve as an excellent introduction to sorting concepts, although they are generally less efficient
than more advanced sorting algorithms.

## Learning Objectives

By the end of this lesson, students will be able to:

- Understand the operational mechanics of Bubble Sort and how it repeatedly traverses the array to sort elements.
- Identify the advantages of using Bubble Sort for small data sets, including efficiency, ease of implementation, memory efficiency, and stability.
- Recognize the disadvantages of Bubble Sort, such as its time inefficiency and scalability issues for large data sets.
- Implement Bubble Sort in Java, understanding the step-by-step process of comparing and swapping elements.
- Apply knowledge of Bubble Sort to make informed decisions about its use in different scenarios, balancing its simplicity and performance constraints.

## Table of Contents

1. [Introduction](#introduction)
2. [How Bubble Sort Works](#how-bubble-sort-works)
3. [Advantages](#advantages)
4. [Disadvantages](#disadvantages)
5. [Implementation in Java](#implementation-in-java)
6. [Summary and Key Takeaways](#summary-and-key-takeaways)

## How Bubble Sort Works

One of the basic sorting algorithms we'll discuss is Bubble Sort. Here's how it works:

1. **Initialize Pointers**: Start by pointing to the first two consecutive elements in the array.
2. **Compare and Swap**: Compare these two elements:
    - If the left element is greater than the right element (i.e., they are out of order), swap them.
    - If they are in the correct order, no action is required for this step.
3. **Move Pointers**: Shift the pointers one position to the right to compare the next pair of consecutive elements.
4. **Repeat**: Continue repeating steps `1` through `3` for each pair of elements until the end of the array is reached.
    - This completes one full pass through the array.
5. **Reset and Iterate**: After completing one pass, reset the pointers to the first two elements of the array and start
   another pass. Repeat the process for multiple passes through the array.
6. **Termination Condition**: Continue performing these passes until a complete pass is made without any swaps. This
   indicates that the array is fully sorted, and no further passes are necessary.

This methodical approach to Bubble Sort ensures a clear understanding of the algorithm and its implementation. As we
progress, the nuances of this and other sorting algorithms will be explored in greater depth.

## Time and Space Complexity

Considering each comparison in the sorting process takes a constant time `C`, the total time complexity can be analyzed
by summing up the comparisons required for each pass. In Bubble Sort, the first pass requires `N-1` comparisons, the
second pass requires `N-2` comparisons, and so forth, down to just 1 comparison in the last pass. This sequence of
comparisons can be expressed as:

```text
C × (N-1 + N-2 + … + 2 + 1)
```

This arithmetic series sums up to `N(N-1)/2`, which simplifies to `O(N^2)` time complexity in Big O notation. This
quadratic time complexity indicates that Bubble Sort becomes inefficient as the size of the array increases,
particularly for large datasets.

## Space Complexity

Bubble Sort is an in-place sorting algorithm. This means it does not require any additional storage as it rearranges the
numbers within the original array itself. Consequently, the space complexity of Bubble Sort is `O(1)`, signifying that
it requires a constant amount of additional memory regardless of the array size.

Here is a simple text diagram illustrating how Bubble Sort processes an array:

```text
Initial Array: [5, 3, 8, 4, 2]

First Pass:

Compare 5 and 3, swap -> [3, 5, 8, 4, 2]
Compare 5 and 8, no swap -> [3, 5, 8, 4, 2]
Compare 8 and 4, swap -> [3, 5, 4, 8, 2]
Compare 8 and 2, swap -> [3, 5, 4, 2, 8]

Second Pass:

Compare 3 and 5, no swap -> [3, 5, 4, 2, 8]
Compare 5 and 4, swap -> [3, 4, 5, 2, 8]
Compare 5 and 2, swap -> [3, 4, 2, 5, 8]

Third Pass:

Compare 3 and 4, no swap -> [3, 4, 2, 5, 8]
Compare 4 and 2, swap -> [3, 2, 4, 5, 8]

Fourth Pass:

Compare 3 and 2, swap -> [2, 3, 4, 5, 8]
Final Sorted Array: [2, 3, 4, 5, 8]
```

In this example, it is evident that Bubble Sort repeatedly traverses the array, making swaps as needed, until no more
swaps are required, indicating the array is sorted. This step-by-step approach provides clarity on the operational
mechanics of Bubble Sort.

## Advantages

Bubble sort offers several benefits, particularly when used with small data sets:

- **Efficiency with Small Data**: It performs well with smaller arrays where the lesser number of elements makes its
  simplicity advantageous.
- **Ease of Implementation**: The algorithm is straightforward and easy to understand, making it simple to implement.
- **Memory Efficiency**: As an in-place sorting algorithm, bubble sort does not require additional memory allocation,
  which makes it memory efficient.
- **Stability**: Bubble sort is a stable sorting algorithm, meaning it maintains the relative order of records with
  equal keys. This is particularly useful when the order of items is significant.

## Disadvantages

Despite its advantages, bubble sort has notable drawbacks that limit its use in larger data sets:

- **Time Inefficiency**: Bubble sort is inherently slow with a time complexity of \(O(N^2)\). This makes it inefficient
  for large data sets where the time required for sorting grows quadratically with the number of elements.
- **Scalability Issues**: Due to its \(O(N^2)\) time complexity, the performance of bubble sort deteriorates rapidly as
  the size of the data set increases. This exponential growth in time makes it unsuitable for practical use in
  large-scale applications.

These characteristics should guide the selection of bubble sort in situations where its advantages can be best utilized,
and its disadvantages are least detrimental.

## Implementation in Java

Here’s what the implementation of a bubble sort looks like in Java:

### Bubble Sort Definition

```java
public class BubbleSort {
    public static void bubbleSort(int[] arr) {
        int noOfPasses = arr.length - 1;
        //run the 'for' loop 'noOfPasses' times.
        boolean isSwapped;
        for (int i = 0; i < noOfPasses; i++) {
            //no of comparisons for each pass
            isSwapped = false;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    //swap arr[j] and arr[j+1]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    isSwapped = true;
                }
            }
            if (!isSwapped) {
                break;
            }
        }
    }

    public static void printArray(int[] arr) {
        for (int j : arr) {
            System.out.print("\t" + j);
        }
    }
}
```

### Usage Example

```java
public class BubbleSortDemo {
    public static void main(String[] args) {
        int[] arr = {9, 7, 5, 3, 1};
        System.out.println("\nArray elements before sorting: ");
        BubbleSort.printArray(arr);
        BubbleSort.bubbleSort(arr);
        System.out.println("\nArray elements after sorting: ");
        BubbleSort.printArray(arr);
    }
}
```

## Summary and Key Takeaways

### Summary

Bubble sort is a classic sorting algorithm that is particularly favored for its simplicity and ease of implementation.
It works by repeatedly stepping through the list, comparing adjacent elements, and swapping them if they are in the
wrong order. This process is repeated until the list is sorted. The algorithm's primary advantages include its memory
efficiency (as it sorts in place), stability (preserving the relative order of equal elements), and effectiveness on
small data sets. However, due to its \(O(N^2)\) time complexity, bubble sort is generally inefficient for large data
sets where the time required to sort grows quadratically with the size of the array.

### Key Takeaways

1. **Simplicity**: Bubble sort is easy to understand and implement, making it an excellent educational tool for
   introducing sorting algorithms.
2. **In-Place Sorting**: It does not require additional memory beyond the input array, making it memory efficient.
3. **Stability**: Maintains the relative order of the same elements, which is crucial for certain applications where the
   order of equivalent elements should not be changed.
4. **Performance Constraints**: Due to its quadratic time complexity, bubble sort is not suitable for large data sets as
   it becomes increasingly inefficient compared to more advanced sorting algorithms like quicksort or mergesort.
5. **Use Case Specificity**: Best used in situations where data sets are small, or memory conservation is critical, and
   where ease of implementation outweighs the need for speed.

