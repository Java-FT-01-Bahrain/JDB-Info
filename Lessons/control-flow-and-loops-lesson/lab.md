# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Java Lab – Loops

| Title | Type | Time | Creator |
| ----- | ---- | -- | ----- |
| Loops | Lab | 00:45 | Saad Iqbal |

# 1. 🧪 Looping and Pattern Generation

## 🎯 Objective
In this lab, you’ll learn how to use **loops** to perform repetitive tasks and display visual patterns in the console.  
By the end of this exercise, you’ll:
- Understand how counting repetition works in Java.
- Display symbols repeatedly.
- Use nested repetition to draw shapes (like triangles).

---

## 🧰 Tools Required
- Java JDK 17 or later
- IntelliJ IDEA

---

## 🪜 Instructions

### Task 1: Printing Symbols Repeatedly
Write a program that prints the `*` symbol **20 times** on one line.  
Each `*` should appear next to the previous one with no spaces or line breaks in between.

🧠 **Hint:**  
Use a counter that starts from 1 and runs until 20.  
Display the `*` symbol inside your repetition block.

---

### Task 2: Creating a Triangle Pattern
Next, use the same concept of repetition to print a **right-angled triangle** made of `*` symbols, like this:

```
*
**
***
...
```
🧠 **Hint:**  
You’ll need one repetition to handle the **rows** and another repetition inside it to handle the **columns** (nested repetition).

---

### Task 3 (Optional Challenge): Inverted Triangle
Print the triangle **upside down**, like this:
```
***
**
*
...
```

🧠 **Hint:**  
Start from 10 symbols and reduce one on each line.

---

### Step 4: Testing Your Output
Make sure:
1. All lines appear correctly in the console.  
2. The triangle patterns are properly aligned.  
3. No extra blank lines appear between shapes. 

____
---

# 2. Java Lab: FizzBuzz

## Instructions

1. **Setup**
   - Create a new Java project or a single Java class named `FizzBuzzLab`.
   - Include a `main` method where all your code will run.

2. **Task**
   - Write a program that prints numbers from **1 to 100**.
   - For each number:
     - If the number is divisible by **3**, print `"Fizz"` instead of the number.
     - If the number is divisible by **5**, print `"Buzz"` instead of the number.
     - If the number is divisible by both **3 and 5**, print `"FizzBuzz"` instead of the number.
     - Otherwise, print the number itself.

3. **Output**
   - Each result should appear on a **new line**.
   - Example snippet of expected output:
     ```
     1
     2
     Fizz
     4
     Buzz
     Fizz
     7
     8
     Fizz
     Buzz
     ...
     ```

4. **Hints**
   - Use a **loop** to iterate from 1 to 100.
   - Use **conditional statements** to check divisibility.
   - The modulus operator `%` will help determine if a number is divisible by another.

5. **Extra Challenge (Optional)**
   - Modify your program to take a number input from the user and print FizzBuzz up to that number.
   - Try printing the output in a **single line separated by commas**.

---

