# Java Inheritance Labs

## Lab 1 - Banking System (Estimated Time: 40 mins)

### Learning Objectives
- Understand inheritance
- Override methods
- Apply simple business rules

## Part 1
Create a base `BankAccount` class.

1. Bank accounts keep track of their current balance.
2. Bank accounts have a `check_balance()` method that returns the current balance.
3. `deposit(amount)` adds money and returns the new balance.
4. `withdraw(amount)` returns the amount successfully withdrawn.
5. Return `False` if a negative amount is deposited or withdrawn.
6. Default interest rate is **2%**.
7. `accumulate_interest()` updates the balance using the interest rate.
8. Return the updated balance.

## Part 2
Create a `ChildrensAccount` class.

1. Inherit from `BankAccount`.
2. Interest rate is **0%**.
3. Every call to `accumulate_interest()` adds **$10** instead.

## Part 3
Create an `OverdraftAccount` class.

1. Inherit from `BankAccount`.
2. Default overdraft penalty = **$40**.
3. If withdrawal exceeds balance:
   - Return `False`
   - Deduct only the overdraft penalty.
4. Do not accumulate interest when balance is below zero.

---

# Lab 2 - Library Management System (Estimated Time: 50 mins)

### Learning Objectives
- Practice inheritance and overriding
- Apply borrowing rules
- Perform fee calculations

## Part 1
Create a `LibraryMember` class.
1. Store member name and borrowed books.
2. Default borrowing limit = 5.
3. `borrow_book()` returns True if successful.
4. `return_book(daysLate)` decreases borrowed books and returns late fee.
5. Default late fee = $2/day.
6. Negative late days return False.
7. `member_status()` returns borrowed books and limit.

## Part 2
Create `StudentMember`.
1. Limit = 3 books.
2. Late fee = $1/day.
3. Every 5 successful borrows earns one free late day on the next return.

## Part 3
Create `FacultyMember`.
1. Limit = 10 books.
2. Late fee = $0.50/day.
3. No fee if returned within 2 late days.

## Part 4
Create `PremiumMember`.
1. Limit = 15 books.
2. First 10 late days each month are free.
3. Remaining days charged at $1/day.

---
---

# Bonus
# Lab 3 - Employee Payroll System (Estimated Time: 1 Hour)

### Learning Objectives
- Multi-level inheritance
- Method overriding
- Salary calculations

## Part 1
Create an `Employee` class.
1. Store employee name and base salary.
2. Reject negative salaries.
3. `calculate_salary()`
4. `annual_salary()`
5. `display_info()`

## Part 2
Create `HourlyEmployee`.
1. Store hourly rate and hours worked.
2. First 160 hours paid normally.
3. Additional hours paid at 1.5× rate.
4. Override `calculate_salary()`.

## Part 3
Create `SalesEmployee`.
1. Base salary + commission.
2. First $10,000 sales: 5%.
3. Remaining sales: 8%.

## Part 4
Create `Manager`.
1. Bonus based on team size:
   - 1–5: $500
   - 6–10: $1000
   - Greater than 10: $2000
2. Apply 5% tax after bonus.
3. Override `calculate_salary()`.

### Bonus Challenge
Create an array of mixed employee objects and demonstrate runtime polymorphism by calling `calculate_salary()` for each object.
