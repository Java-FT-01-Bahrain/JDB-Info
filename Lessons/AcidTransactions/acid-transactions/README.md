---
title: ACID Transactions
type: Morning Exercise
duration: "1:00"
creator:
    name: Melissa Arliss
---

# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) ACID Transactions

## Introduction 

Get ready, because we've got another acronym for you: **ACID**.

ACID is a series of principles that should be followed whenever you modify a database. The goal in using these principles is to maintain a stable, consistent database, both before and after whatever operation you're performing.

These properties are the reason why all banking, healthcare, financial companies use relational databases.

---

## First Things First: Definitions

There's a lot to break down in the term "ACID transaction."

First: What exactly do we mean by transaction?

> A **transaction** is a single unit of work that modifies a database. It might be several individual actions, but think of them as a unit, all working on the same task.

Great. Now let's break down that acronym: ACID.

* **Atomicity**: Transactions are all or nothing; they go all in. There's no cutting corners with transactions. However you want to put it, *atomicity* just means that all parts of a transaction take place at once and run to completion, or it doesn't happen at all. You can't have Step 3 unless Steps 1 and 2 (and all others after Step 3) happen as well.

* **Consistency**: The database should be consistent before and after the transaction happens; kind of like how energy can neither be created nor destroyed, it just changes forms. If you're moving or removing something in a database, it has to go *somewhere*. This principle is pretty stringent: If the transaction can't be fully completed at that time, everything will roll back to the original version.

* **Isolation**: Transactions must be able to occur independently, without interference, while still maintaining the ultimate goal of *consistency* in the database. What's more, transactions must be able to happen concurrently while still keeping things consistent.

* **Durability:** Once a transaction is complete, it doesn't just say "See ya!" and ride off into the sunset. Any changes made must be permanently stored in the database's memory. And, if something goes wrong, the record of the transaction still exists and is always accessible in the database.

The goal of any good database is to be **ACID compliant**, meaning that all transactions that occur abide by these rules.

--- 

## Make It Real

We know what ACID is all about. Now, it's time to bring it into real life!

We've listed a few real-world scenarios depicting database transactions that happen all the time. With a partner, review them and think through:
- With which ACID principle(s) is the scenario dealing?
- Assuming the DB is ACID compliant, what should happen?
- What would happen if the DB was **not** ACID compliant? What could go wrong in this transaction?

**Meet the Scenarios**

- **Scenario 1**: You and your brother are both trying to buy the same 12-pack of Pamplemousse La Croix from Amazon at the same time. There are 10 packs available; your brother wants five and you want six.
- **Scenario 2**: You're trying to buy tickets from Ticketmaster to see Taylor Swift's new tour. The tickets go on sale at 12 p.m., and you're ready to purchase as soon as the clock strikes noon. After being stuck in the digital waiting room for some time, you’re finally able to add those tickets to your cart, make the purchase, and get your confirmation. All of a sudden, the site goes down (probably overrun with ravenous T Swift fans).
- **Scenario 3**: You send a five-page document to the printer. While it's working on Page 3, the printer runs out of toner.

Let's look at a more general example, too: Imagine that you're transferring $1,000 out of your checking account to pay off your credit card bill.

For this scenario, think through:
- What are all of the steps that make up this transaction?
- How does each ACID component come into play during the transaction?
- What should happen if everything goes smoothly?
- What should happen if something goes wrong?

---

## What's the Big Deal?

Thinking through these scenarios should give you a pretty good handle on the difference ACID makes in our applications. Luckily for us, most databases are ACID compliant without us even having to think about it. And Spring takes care a lot of this work for us, too. Phew!

That being said, it's important to understand these concepts so that you know what's happening behind the scenes.

> **Knowledge Check**: As you're working on your project (the posting application), what ACID properties might come into play in terms of the way your app handles data?
