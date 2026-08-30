# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Java Homework – Methods and Scope

| Title | Type | Time | Creator |
| ----- | ---- | -- | ----- |
| Methods and Scope | Homework | 01:00 | Saad Iqbal |

# Calculator - Methods and Scope

### Objective:

Practice creating and using **methods with different access modifiers** and **variable scopes** in Java.

---

### Scenario: Simple Calculator

You are tasked to create a **Simple Calculator program** that can perform operations on numbers and keep track of some internal state.

---

### Instructions:

1. **Variables**

   * Create a **private static variable** to keep track of the total number of calculations done.
   * Create a **protected variable** for storing the last result of a calculation.
   * Create a **public variable** for storing the name of the calculator (e.g., "MyCalculator").

2. **Methods**

   * Create a **public method** `add(int a, int b)` that returns the sum of two numbers and updates the last result and total calculations.
   * Create a **protected method** `subtract(int a, int b)` that returns the difference, updates the last result, and total calculations.
   * Create a **private method** `resetCalculator()` that resets the last result to zero.
   * Create a **public method** `monthlyReset()` that internally calls the private `resetCalculator()` method.
   * Create a **static method** `showTotalCalculations()` that prints the total number of calculations done.
   * Create a method `showLastResult()` that prints the last result and demonstrates **local variable shadowing** (e.g., declare a local variable named `lastResult` that hides the instance variable).

3. **Main Method**

   * From the `main` method, call all the public and protected methods to test your program.
   * **Do not call private methods directly**; call them through a public method.
   * See the difference between **local and instance variables**.

4. **Extra Challenge (Optional)**

   * Add more operations like multiplication and division.
   * Optionally, handle **floating-point numbers**.
   * Optionally, use **static vs instance variables** in creative ways to track results for multiple calculators if you extend the program later.

---

### Learning Goals

* Understand **public, private, and protected methods**.
* Understand **local vs instance vs static variables**.
* Practice **method calls from `main`**.
* Demonstrate **private method usage through public methods**.
* Understand **variable shadowing** and its effect.
