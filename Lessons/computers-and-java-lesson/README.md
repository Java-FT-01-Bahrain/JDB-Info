| Title       | Type   | Duration | Author        |
| ----------- | ------ | -------- |   ----------  |
| Computers and Java | Lesson | 0:30   |  Victor Grazi |

# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Computers and How Java Works

## Learning Objectives 

At the end of this lesson, you'll be able to:
- Explain what a computer is and how its parts work together.
- Differentiate between compiled and interpreted programming languages.
- Tell all of your friends what makes Java great.

### What Is a Computer?

**Group Activity**

Before we get into Java, let's start with the basics. How would you define a computer?

Take a moment to jot down a one-sentence definition of a computer. Then, get into a group of 3–4 people. Have everyone share their definitions and create one shared definition based on everyone's answers. Be prepared to share it with the class.

**In (Sort of) Technical Terms...**

A computer is a device that processes instructions — an instruction processor, more commonly known as a CPU or **central processing unit**.

When you open an application on your computer, the machine processes a series of instructions called a computer program that instructs the machine on what to display and how to respond to your input. This can't happen in isolation.

We need a way to enter a program, so we have keyboards and mice. We need to display information, so we have monitors and printers. We need a place to store instructions and files, so we have disks.

We also need a location to execute the instructions and store data, which is why our computers have memory. We need a way to read and write to disk and talk to other computers in our organization or across the World Wide Web, which is why we have networks.

Ultimately, every computer must have an operating system, which is just a fancy name for a computer program that manages all the accessories, allocates time for processing, manages disk accesses, coordinates multiple threads to process concurrently, and all of the other millions of operations that occur every single second your computer is functioning.

<img src="https://cdn-images-1.medium.com/max/1600/1*rk1o0WQWtR1tEGcsEMIpEQ.png"/>

### So, What Is a Computer Programming Language?

What **we** call instructions and what a **computer** calls instructions are two different things. We want to think of instructions as a set of natural language commands we can feed to the computer, just like we might tell our friend.

Unfortunately, computer data is a sequence of zeros and ones. And by extension, computer instructions are sequences of zeros and ones. These instructions comprise the machine code understood by your particular operating system. Depending on the operating system and hardware you're using — be it Windows, Mac, Android, or others — a compiler must convert the computer program to the machine code understood by that particular operating system. That's why we have computer programming languages.

![](https://git.generalassemb.ly/GA-Cognizant/foundational-java/blob/master/java-basics/computers-and-java-lesson/0s-and-1s.jpeg)
*It's just The Matrix in there.*

### What Is a Compiler?

Computer programming languages consist of natural commands that closely mimic how we speak and think. Once a computer program is written, it's handed off to a **compiler**, another program that converts computer programs into machine code instructions — the sequences of zeros and ones that instruct the computer what to do.

Compiled languages include C, C++, and Go (also known as Golang).

Compilers produce the fastest code possible without actually writing the machine code. However, depending on the language and size of the program, it could take some time to compile. Additionally, it needs to be compiled for each target platform, so if you want to run the same program in Windows and Linux, you generally have to compile it for each platform.

### What Is an Interpreter?

On the other hand, interpreted languages such as JavaScript, Ruby, and Python don't generally compile down to machine code; rather, they execute within an interpreter "shell," a program that interprets natural language programs and executes the corresponding instructions.

Interpreted languages would generally execute slower than compiled languages. Still, there's no compilation step, so you can enter code and see it evaluated in real-time or as you type it. Besides the slower execution speed, interpreted languages tend to be dynamically typed, which makes it difficult for tools such as your IDE (integrated development environment) to catch errors in the code.

### JVM Languages

There's a happy medium between compiled and interpreted languages: the class of bytecode-compiled languages such as C#, Scala, and your soon-to-be favorite language, Java.

These languages are compiled into an intermediate form called "bytecode," similar to most platforms' machine code. The bytecode executes in an interpreter called a **JVM** (Java virtual machine) in Java. Such languages are "write once, run anywhere" because they don't generally need to be compiled for each platform.

In the case of Java, there's also a JIT (just in time) compiler that inspects the code as it executes. Based on those inspections, it makes assumptions that allow it to compile segments of code directly to machine code, making it blazing fast!

### What's the Big Deal With Java?

Java was initially designed as an embedded language to control devices like refrigerators and clocks. Because it was platform-independent, it took off more as a server-side development environment for building client-server tools. Years later, Java returned to its roots and became an embedded language popular in IoT (Internet of Things) devices.

Here are a few reasons why Java is the most popular language for server-side development:

1. **Platform independence**: Where .Net requires you to use Windows (and pay for Microsoft Windows licenses), Java can run anywhere. The term "write once, run anywhere" was made for Java.

2. **Maturity and ecosystem**: If you need tools, they're present, free, and mature, with huge developer support in the open-source industry. If you want to write something using Java, chances are you'll find libraries that can help you.

3. **Memory management**: The Java virtual machine has a built-in garbage collector. This facility (running mostly in the background) locates objects that no longer have anything referring to them and removes them from the heap. Java silently performs this housekeeping, allowing you to worry about coding your business logic without getting bogged down in the vagaries of memory management.

4. **Backward compatibility**: Java goes to great lengths to ensure backward compatibility. Every time a new version comes out, you can be confident that the old version of your code will still work. This is important when you're building a long-term enterprise application.

### A Word on Background

The language was initially branded "Oak," but someone realized that name was already taken. So, while contemplating alternate options in a cafe over a cup of hot Java coffee, someone inspired the name we've all come to know and love.

> And remember: Just like fire is not the same thing as firefly, Java is not JavaScript. The two languages have nothing in common other than the first four letters of their names.

## Check for Understanding

- Explain how the different parts of a computer fit together.
- What's the difference between compiled and interpreted languages?
- Why do we love Java?
