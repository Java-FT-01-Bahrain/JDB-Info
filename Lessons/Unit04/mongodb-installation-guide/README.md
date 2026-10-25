# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) MongoDB Setup and Installation Guide

## Learning Objectives

By the end of this installation guide, you will be able to:

- Understand what MongoDB Atlas is and when to use it
- Create and configure a MongoDB Atlas account
- Deploy a MongoDB database cluster in the cloud
- Configure basic security settings, including database users and network access
- Obtain and understand the MongoDB connection string
- Install and set up MongoDB Compass (GUI interface for MongoDB)
- Connect MongoDB Compass to a MongoDB Atlas cluster
- Navigate the MongoDB Compass interface to view databases and collections
- Perform basic database operations using MongoDB Compass
- Verify that the database setup and connections are working correctly

## Introduction
Welcome to the MongoDB Atlas Installation Guide. This guide will walk you through the process of setting up a MongoDB Atlas account, which is used to create and manage non-relational (NoSQL) databases in the cloud. MongoDB Atlas provides a secure, scalable, and easy-to-use platform for hosting MongoDB databases.

This guide is organized into clear, step-by-step sections. You will begin by creating an account, followed by deploying a database cluster, configuring basic security settings, and finally obtaining the connection string needed to connect your applications to the database. Each step includes detailed instructions and screenshots to ensure a smooth and straightforward setup experience.

By the end of this installation guide, you will have a fully configured MongoDB Atlas environment and a solid understanding of how to deploy and manage MongoDB databases for your projects.

Let's get started!

## Create an Atlas account

[MongoDB Atlas](https://www.mongodb.com/atlas/database) is MongoDB’s official cloud platform and the most popular choice for hosting MongoDB databases. We’ll use it throughout the course to host all of our MongoDB databases.

First, you will need to sign up for a free account [here](https://www.mongodb.com/cloud/atlas/register).

![The MongoDB Atlas Sign up page.](./assets/atlas-sign-up.png)

As seen in the image above, you will have two different options to sign up:

1. Click Sign up with Google (***blue highlighted button***)
2. Enter the required information which includes providing an email and a password (***red highlighted inputs and button***)

It is important that you choose only ***one*** of the following two options to proceed:

### Option 1: Sign up ***WITH*** Google

Click the **Sign up with Google** button.

Read the Terms of Service and Privacy Policy, then agree to them.

Select the **Submit** button.

![The MongoDB Atlas privacy policy and terms of service acceptance page.](./assets/google-tos.png)

Proceed to the **Welcome to Atlas** section below.

### Option 2: Sign up ***WITHOUT*** Google

1. Provide the required information (Company is not required). You should store the email address and password you provide somewhere easily accessible and secure (like a password manager).
2. Read the Terms of Service and Privacy Policy, then agree to them.
3. Finally select the **Create your Atlas acount** option.

If you created an account with an email and password, you'll be taken to a page asking you to verify your email, as shown below.

![The MongoDB Atlas email verification page.](./assets/email-verification.png)

1. Find the email from MongoDB in your inbox and verify your email.
2. After you've verified your email, you'll be taken to a page informing you that you've successfully verified your email and a **Continue** button.
3. Click the **Continue** button.

Proceed to the **Welcome to Atlas** section below.

## Welcome to Atlas

1. Answer the marketing questions however you'd like. Some suggestions are shown below, but your selections do not matter and do not impact your experience.
2. Select the **Finish** button when you're done.

![The MongoDB Atlas Welcome to Atlas page. The fields have been prefilled with data, matching the SEB use case, but this information is not important](./assets/marketing.png)

## Deploy your database

It's time to set up the cluster! Clusters are MongoDB's way of collecting your databases all in one place.

1. Select the **M0** option, which is ***FREE***.
2. Select **AWS** as the Provider, and select the **Region** closest to your job market.
3. Name your cluster whatever you want, but know that this name cannot be changed later. We recommend something simple like `student-cluster`. 
4. Ensure that the text directly above the **Create** button says **FREE**.
5. ***Do not select the*** **Create** ***button unless this text says*** **FREE**. If it has a dollar amount, review your selections and ensure you have correctly configured the service from this page.
6. Finally, select the **Create** button.

![The MongoDB Atlas Deploy your database page.](./assets/aws.png)

## Security quickstart

Next, you will be taken to a ***Security Quickstart*** page. There will be a message at the bottom left stating that the database cluster is being provisioned and the approximate time it will take to complete.

While the cluster is provisioned, create a **database user** (*different from your account user*) by following the steps in the **How would you like to authenticate your connection?** section.

### Creating a database user

### Creating a Database User

1. Choose a **username** and **password** for your database user. Make sure not to reuse the credentials shown in any example images. Both the username and password must be **alphanumeric**, meaning they can contain only English letters and numbers. Special characters, symbols, and spaces are not allowed.
2. You may use the **Autogenerate Secure Password** option if you prefer. This option automatically creates a strong password that follows the required rules. You can also keep the default settings if they are already selected.
3. **Pause and save your credentials**: Once your username and password are created, store them in a secure but easily accessible place, such as a password manager. Use the **Copy** button to ensure the password is saved accurately. You will need these credentials shortly, as they will be included in a **connection string**, which is required to connect your applications to the database.
4. After confirming your username and password, click the **Create User** button to complete the process.


![The first step of the MongoDB Atlas Security Quickstart page.](./assets/security-quickstart-1.png)

#### After creating a database user

The page should look something like this after you have the above step:

![The completed first step of the MongoDB Atlas Security Quickstart page.](./assets/security-quickstart-1-complete.png)

### Specifying access from anywhere

Scroll to the second step titled **Where would you like to connect from?**. This section controls which IP addresses are allowed to connect to your database.

1. In the **IP Address** field, enter **`0.0.0.0/0`** exactly as shown.  
   This value represents *all possible IP addresses*, meaning the database will accept connections from any location. Using this setting avoids connection issues while you are developing and testing your application.

2. In the **Description** field, type `Anywhere`.  
   This is simply a label to help you remember why this rule exists. It does not affect functionality, but it makes the configuration easier to understand later.

3. Click the **Add Entry** button to save the rule.  
   Once added, the database will immediately allow connections from any network, as long as the correct credentials are provided.

> ❗️ **IMPORTANT**  
> This configuration is **not suitable for a production environment**. Allowing access from anywhere means that anyone on the internet could attempt to connect to your database if they obtain the username and password.  
>  
> We use this open setting during the course to make development and deployment easier and to reduce setup friction while you are learning. For this reason, and for security best practices, you must **never store sensitive or personally identifying information** in this database. This includes phone numbers, addresses, credit card details, insurance information, or any other private data.  
>  
> In real-world applications, access should always be restricted to specific IP addresses or trusted networks only.


![The second step of the MongoDB Atlas Security Quickstart page.](./assets/security-quickstart-2.png)

#### After specifying access from anywhere

The page should look like this after the above step.

You may receive an email from MongoDB Atlas with a subject of **You've added "Allow access from anywhere (0.0.0.0/0)" to your Network Permission List**. This is normal and is ok!

Select the **Finish and Close** button and then the **Go to Overview** button in the pop-up.

![The completed second step of the MongoDB Atlas Security Quickstart page.](./assets/security-quickstart-2-complete.png)

## Obtain the Connection String

A connection string is a single line of text that contains all the information an application needs to connect to a database securely. It defines where the database is located and provides the required credentials, such as the username and password. Without this connection string, the application would not be able to establish communication with the database or perform any data operations.

> ❗️ **IMPORTANT:** Database connection strings contain the username and password for connecting to the database. ***NEVER*** include the connection string in the project's source code - use a `.env` file to secure this sensitive information.

1. Once you arrive on the **Overview** page, click the **Connect** button as shown below:

![The MongoDB Atlas Overview page.](./assets/atlas-overview.png)

2. Select the only option under the **Connect to your application** header, **Drivers**:

![The connection methods provided by MongoDB Atlas.](./assets/connection-method.png)

3. Click the **copy** button to add the connection string to your clipboard. You do not need to do anything else on this page.

![The grand prize! A first-class one-way ticket to Albuquerque. No actually, a MongoDB Atlas connection string. Sorry for getting your hopes up.](./assets/connection-string.png)

## Use the connection string in your app

> ❗️ Please do not attempt to use any of the connection strings below; they will not function in your applications.

We're almost done, but you'll need to make a couple of changes in your connection string before it can be used:

1. **Add Password:** Replace `<password>` (including the `<` and the `>`) with the password of the database user you created earlier.

    For example, using the password `studentPass123`, a properly formatted connection string would look like this:

    ```bash
    mongodb+srv://student:studentPass123@student-cluster.uwdsn12.mongodb.net/?retryWrites=true&w=majority
    ```

2. **Add Database Name:** It is important to note that the connection string does not include a database name by default. You'll need to add an appropriate database name between the `/` and `?` part of the connection string for each project. For example, if you have a project named `my-project`, you could give the database the same name:

    ```bash
    mongodb+srv://student:studentPass123@student-cluster.uwdsn12.mongodb.net/my-project?retryWrites=true&w=majority
    ```

3. With your password and database name added, your connection string is now ready to use. 

4. Paste the connection string into your application's `.env` file, assigning it to a **MONGODB_URI** environment variable.

    As an example:

    ```bash
    MONGODB_URI=mongodb+srv://student:studentPass123@student-cluster.uwdsn12.mongodb.net/my-project?retryWrites=true&w=majority
    ```

> ❗️ **If you do not have an application built yet, paste your connection string somewhere safe on your computer for future use.**

## Reusing the connection string for multiple projects

The database connection string you've created can be used for various projects. Remember to securely store this string for easy access in the future.

When using the same connection string for different projects, replace the database name within the string. For instance:

For a flights app, the string might be:
```bash
mongodb+srv://student:studentPass123@student-cluster.uwdsn12.mongodb.net/flights?retryWrites=true&w=majority
```

For a tacos app, it could be:
```bash
mongodb+srv://student:studentPass123@student-cluster.uwdsn12.mongodb.net/tacos?retryWrites=true&w=majority
```

**The only change needed is the database name, which is the text between the `/` and `?` in the connection string.**

## Viewing and editing data

You can use Atlas to view and edit data by clicking on the **Browse Collections** button on the **Database Deployments** page, as shown below.

![Accessing data stored in your databases from within Atlas.](./assets/browse-collections.png)

---

# Downloading, Installing, and Connecting MongoDB Compass

MongoDB Compass is a graphical tool that allows you to view and manage your MongoDB data using your Atlas connection string. Follow the steps below based on your operating system.

---

## Step 1: Download MongoDB Compass

1. Open your web browser and go to the [MongoDB Compass download page](https://www.mongodb.com/try/download/compass).
2. Choose the version suitable for your operating system:
   - **Windows**
   - **macOS**
3. Download the installer file.

---

## Step 2: Install MongoDB Compass

### On Windows

1. Locate the downloaded `.msi` installer file.
2. Double-click the file to start the installation.
3. Follow the on-screen instructions and accept the default settings.
4. Once installation is complete, launch **MongoDB Compass** from the Start menu.

### On macOS

1. Locate the downloaded `.dmg` file.
2. Double-click the file to open it.
3. Drag the **MongoDB Compass** icon into the **Applications** folder.
4. Open Compass from the Applications folder (you may need to allow it in Security settings the first time).

---

## Step 3: Connect MongoDB Compass to an Atlas Cluster

1. Log in to your **MongoDB Atlas** account.
2. Open your cluster and click **Connect**.
3. Select **Connect using MongoDB Compass**.
4. Copy the provided **connection string**.
5. Open **MongoDB Compass**.
6. Paste the connection string into the connection field.
7. Replace the placeholders with your database username and password.
8. Click **Connect**.

Once connected, you will be able to browse databases, collections, and documents directly from MongoDB Compass.

---

> 💡 **Tip**  
> If the connection fails, double-check your username, password, and network access settings in MongoDB Atlas.


Your setup is complete, and you can now start creating databases in MongoDB Atlas.
