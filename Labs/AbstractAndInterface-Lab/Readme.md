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

