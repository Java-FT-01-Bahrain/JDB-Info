# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Java Lab – Methods And Scope

| Title | Type | Time | Creator |
| ----- | ---- | -- | ----- |
| Methods And Scope | Lab | 00:45 | Saad Iqbal |

# 🧪 Interactive Q&A Program

## 🎯 Objective
Create a Java console application that interacts with the user by responding to specific types of questions.  
The program will:
- Ask the user to enter a question.
- Recognize words such as **who**, **what**, **why**, **when**, and **where**.
- Reply with a predefined answer depending on the keyword.
- Keep asking questions until the user types **exit** or **quit**.
- Ask for confirmation before ending the program.

---

## 🧰 Tools Required
- **Java JDK (version 17 or later)**
- **IntelliJ IDEA**
- Basic understanding of:
  - Methods
  - Conditional statements (`if-else`)
  - User input using the `Scanner` class

---

## 🪜 Instructions

### Step 1: Create a New Java Project
1. Open your IDE and create a new Java project named **QandAProgram**.
2. Inside the project, create a new Java class named **QandAGame**.

---

### Step 2: Program Overview
Your program should:
- Display a message asking the user to input a question (like *who*, *what*, *why*, *when*, or *where*).
- Read the user’s input from the console.
- Respond with the correct answer depending on the word found.
- Continue asking for new questions in a loop until the user types **exit** or **quit**.
- When the user tries to exit, ask them:
  > “Are you sure you have no more questions? (y/n)”
- If the user types `y`, the program ends.
- If the user types `n`, the program continues asking questions.

---

### Step 3: Behavior and Flow
| User Input | Expected Output |
|-------------|-----------------|
| who | We're the Java class. |
| what | We're learning Java. |
| why | It is the best language to learn in the world. |
| when | At this very moment. |
| where | BIBF. |
| exit or quit | Asks if the user really wants to quit. |
| anything else | Displays a message: “I don’t know how to answer that question.” |

---

### Step 4: Testing the Program
1. Run the program.
2. Type each of the words (*who*, *what*, *why*, *when*, *where*) one by one and check if the response matches.
3. Type a random question (e.g., *how are you?*) and see how the program responds.
4. Finally, test the **exit** and **quit** commands to ensure the confirmation message appears.

---


### 💡 Bonus Challenge
Try enhancing your program by:

- Adding more question types such as how or which.

- Personalizing the responses or including emojis to make it more engaging.

- Optionally handling case insensitivity, so the program works regardless of whether the user types in uppercase, lowercase, or mixed case.

- Optionally detecting keywords within longer sentences, for example recognizing “Who are you?” instead of only “Who.”

- Displaying the total number of questions asked before the program ends.

---

