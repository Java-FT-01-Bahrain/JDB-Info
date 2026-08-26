# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png)  Java Classes Labs


### 🌟 Four Hands-On Exercises

---

## 🚀 Lab 1: Build an ATM Class  
⏱️ *Estimated Time: 30 minutes*

In this exercise, you will create an **ATM** class with properties, methods, and extended behavior.

### 🔧 Requirements

### 🏦 Properties
- `type` → e.g., `"checking"` (set via constructor)
- `money` → starts at `0`
- `transactionHistory` → keeps track of all deposits and withdrawals  
  - Include whether each transaction increased or decreased the balance.
- `backupAccount` → optional reference to another ATM object

### 🛠️ Methods
- `withdraw(amount)` → decreases the balance  
- `deposit(amount)` → increases the balance  
- `showBalance()` → prints the current balance  

### ⚠️ Special Behavior
- If the ATM’s balance goes **below 0**, it should automatically pull money from its `backupAccount` to bring the balance back to **0**.
- The backup account should perform a **withdrawal** equal to (or up to) the required amount.

---

## 🎵 Lab 2: Create a `RecordAlbums` Class  
⏱️ *Estimated Time: 30 minutes*

You will build a class to model a music album and navigate through its songs.

### 🔧 Properties
- `artistName` (string)
- `albumName` (string)
- `songs` (array/list of strings)
- `currentSong` (one of the songs)

### 🛠️ Methods
- `nextSong()` → prints the next song  
- `previousSong()` → prints the previous song  

### 🧩 Getters & Setters
Create getters and setters for:
- `artistName`
- `albumName`
- `songs`

---

## 🚗 Lab 3: Car Prototype  
⏱️ *Estimated Time: 40 minutes*

Model a realistic car with multiple phases of functionality.

---

## 🔹 Phase I : Constructor & Basic Behaviors

### 🧱 Constructor Parameters
- `make`
- `model`
- `year`
- `color`
- `seats`

### 🏗️ Default Constructor Values
- `previousOwners` → `[]`
- `owner` → `"manufacturer"`
- `running` → `false`

### 🛠️ Methods
- `sell(newOwner)`
  - Move current owner to the end of `previousOwners`
  - Set new owner
- `paint(newColor)`
  - Update the car’s color

---

## 🔹 Phase II : Engine & Movement Controls

### 🛠️ Methods
- `start()` → sets `running = true`
- `off()` → sets `running = false`
- `driveTo(destination)`
  - Works only when the car is running  
  - Prints `driving to <destination>`  
  - Returns `true`/`false`
- `park()`
  - Works only when the car is **not** running  
  - Prints `parked!!`  
  - Returns `true`/`false`

---

## 🔹 Phase III : Passenger System

### 👥 Additional Constructor Parameter
- `passengers` (optional; defaults to empty array)

### 🛠️ Methods
- `pickUp(name)`
  - Only if car is running AND seats are available  
  - Prints `driving to pick up <name>`  
  - Adds passenger  
  - Returns `true`/`false`
- `dropOff(name)`
  - Only if car is running AND passenger exists  
  - Prints `driving to drop off <name>`  
  - Removes passenger  
  - Returns `true`/`false`
- `passengerCount()`
  - Returns number of passengers

> 💡 **Note:** Driver takes 1 seat but is **not** included in the passenger count.

---

## 📱 Lab 4: Smartphone Interface & Implementations  
⏱️ *Estimated Time: 40 minutes*

In this lab, you will design and implement an interface-based system for smartphones, exploring **interfaces**, **method overriding**, and **class structure**.

---

### 🧩 Step-by-Step Requirements

### 1️⃣ Create a New Project & Package
- Start by creating a **separate project** and a **new package** dedicated to this lab.

---

### 2️⃣ Create a `Smartphones` Interface
Your interface should contain **at least 7 method definitions** related to smartphone behavior.  
Example ideas (you may choose your own):
- `makeCall()`
- `sendMessage()`
- `takePhoto()`
- `connectToWifi()`
- `installApp()`
- `openCamera()`
- `playMusic()`

---

### 3️⃣ Create a `Smartphone` Base Class
- Implement **all interface methods** inside this class.
- This class will act as the **base implementation** for all smartphone types.

---

### 4️⃣ Create a `Main` Class (Driver)
- Your `Main` class should:
  - Create objects
  - Call methods
  - Print outputs  
- This will serve as the **entry point** for the whole application.

---

### 5️⃣ Create 5 Different Smartphone Classes
Create **five subclasses**, each representing a different smartphone brand/model.  
- Each subclass should **override methods** from the base class.  
- Follow the same approach as your **Automobile example**.

Examples:
- `iPhone`
- `Samsung`
- `Huawei`
- `Pixel`
- `OnePlus`

---

### 6️⃣ Define at Least 2 Common Methods  
All smartphone classes must implement at least **two shared behaviors**, such as:
- `unlockPhone()`
- `chargeBattery()`

These should still be **overridden** to show class-specific behavior.

---

### 7️⃣ Print Smartphone Details
- In your `Main` class, print meaningful details about each smartphone object.  
- Example outputs:
  - Phone type
  - Unique features
  - Overridden method behaviors  

Use organized, readable console output for better clarity.

---

✨ *This lab reinforces interfaces, polymorphism, class structure, and method overriding in Java.*  

