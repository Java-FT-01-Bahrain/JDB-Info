# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Lambdas and Streams Lab

## Introduction

> **Instructor Note**: This lab/HW provides an opportunity to practice filtering, mapping, and statistics. It can be a pair-programming activity or done independently.


------

## Exercise

### Requirements

Write code that uses lambdas and streams to satisfy the requirements listed below.

1. First, complete the following method to print out all the elements in the supplied list:

```java
private <E> void printList(List<E> list) {

}
```

2. Next, print a list of all employees that earn $50,000 or more:

```java
public void getEmployeesOver50k() {
    List<Employee> employees = null;
    printList(employees);
}
```

3. Next, print a list of the names (not the `Employee` instances) of all employees who were hired on or after Jan. 1, 2012:

```java
public void getEmployeeNamesHiredAfter2012() {
    List<Employee> employees = null;
    printList(employees);
}
```

4. Now, print the maximum salary of all employees...

```java
public void getMaxSalary() {
    double max = 0;
    System.out.println("Max: " + max);
}
```

5. ...and the minimum salary:

```java
public void getMinSalary() {
    double min = this.employees.stream()
        .mapToDouble(Employee::getSalary)
        .min()
        .orElse(0);
        System.out.println("Min:" + min);
}
```

6. Now, print the average salary of all Female and Male employees:

```java
public void getAverageSalaries() {
    double averageMale = 0;
    double averageFemale = 0;

    System.out.println("Averages: Male: " + averageMale + " Female: " + averageFemale);
}
```

7. Finally, use the `reduce()` operation to find the `Employee` instance of the employee with the highest salary:

```java
public void getMaximumPaidEmployee() {
    Employee highest = null;
    System.out.println(highest);
}
```


----

## Additional Resources

- [Oracle Java Docs: Defining Methods](https://docs.oracle.com/javase/tutorial/java/javaOO/methods.html)
- [Oracle Java Docs: A Closer Look at the "Hello, World!" Application](https://docs.oracle.com/javase/tutorial/getStarted/application/)
- [Princeton: Java Programming Cheat Sheet](https://introcs.cs.princeton.edu/java/11cheatsheet/)
- [Java Modifier Types](http://www.tutorialspoint.com/java/java_modifier_types.htm)
- [Princeton: Recursion](http://introcs.cs.princeton.edu/java/23recursion/)

----

## Algorithm question

Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".

```
Example 1:

Input: strs = ["flower","flow","flight"]
Output: "fl"

Example 2:

Input: strs = ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.
```

```java
public class CommonPrefix {

    public static String longestCommonPrefix(String[] words) {
        return null;
    }

    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
        System.out.println(longestCommonPrefix(new String[]{"dog", "racecar", "car"}));
    }
}
```

Before you write code, think about the tools you have in your arsenal.
- for loop, while loop
- arrays (char, int, double, long, Strings etc.)
- Inbuilt methods inside the String class such as; `length()`, `charAt()`, and `ValueOf()`
- Remember Strings are immutable, StringBuffer and StringBuilder are mutable
- Draw out each step of the algorithm first (tracing)
- Then, choose the correct tool to build your algorithm

