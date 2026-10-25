| Title | Type | Duration | Author |
| -- | -- | -- | -- |
| Intro to Amazon Web Services | Lesson | 0:55 | Prateek Parekh |      
    
# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Intro to Amazon Web Services

### Learning Objectives

At the end of this lesson, students will be able to:
- Explain how a hypervisor and virtualization work together.
- Launch an EC2 instance.
- Deploy code to a VM.

### Lesson Guide

| Timing  | Type  | Topic  |
|:-:|---|---|
| 5 min   | Introduction  | Overview |
| 15 min  | Introduction  | Amazon Web Services Key Concepts |
| 30 min  | Independent Practice  | Using Amazon Web Services |
| 5 min   | Conclusion  | Review/Recap |

## Introduction (5 min)

Put roughly, the cloud is a series of computers in a data center that are logically or virtually strung together to create some sort of service. The cloud can be used to host images, videos, text, static websites, and web applications. **Amazon Web Services** (AWS) is a collection of cloud-based web infrastructure tools that Amazon offers to the public.

AWS is focused on selling **Infrastructure as a Service** (IaaS). In other words, it manages parts of your infrastructure like storage, networking, virtualization, and servers so that the developer can focus on building applications. They still may have to manage other parts of the application like the data, runtime environment, middleware and so on. 

AWS' tools take the complexity away from hosting or co-locating and scaling large web applications. AWS essentially strives to be the "operating system" of the web.

------

## AWS Key Concepts (15 min)

### What Is EC2?

From the horse's mouth:

> Amazon Elastic Compute Cloud (Amazon EC2) is a web service that provides secure, resizable compute capacity in the cloud. It is designed to make web-scale cloud computing easier for developers.

> Amazon EC2’s simple web service interface allows you to obtain and configure capacity with minimal friction. It provides you with complete control of your computing resources and lets you run on Amazon’s proven computing environment. Amazon EC2 reduces the time required to obtain and boot new server instances to minutes, allowing you to quickly scale capacity, both up and down, as your computing requirements change. Amazon EC2 changes the economics of computing by allowing you to pay only for capacity that you actually use. Amazon EC2 provides developers with the tools to build failure-resilient applications and isolate them from common failure scenarios.

An **EC2 instance** runs on a computer located in one of the many Amazon data centers around the world. This model provides resilience and quick access to data. Many EC2 instances can run on one computer or across many. An EC2 instance is just a logical representation of a server; it doesn't really exist.

### What Is Virtualization?

**Virtualization** is the process of running a virtual instance of a computer system in a layer abstracted from the actual hardware. Those virtual instances are known as **virtual machines**, which we've already learned about. Virtualization is often used to run multiple operating systems on one computer system at the same time.

> **Knowledge Check**: Can anyone explain what a virtual machine (VM) is? Bonus: Compare a VM to a container.

An important new concept here is a **hypervisor**, which is a program for creating and running virtual machines. Hypervisors can be "bare metal" that run a VM directly on a system's hardware, or "hosted," which behave more like traditional applications that can be turned on and off.

AWS provides a hypervisor that offers two types of virtualization to clients, but we won't get into all the nitty gritty now. Instead, let's look at an example of a VM:
- This is a virtual representation of an [Apple II](https://www.scullinsteel.com/apple2/).
- This is what the [processor](http://www.visual6502.org/JSSim/) of the Apple II looks like.

-----

## Using AWS (30 min)

### Sign Up for AWS

Create an account or sign in to the [AWS Console](https://aws.amazon.com/).

**Note**: You'll need a credit or debit card to sign up, but you can select the "free tier" access for the purpose of this lesson.

### AWS Tutorials

Let's get comfortable with performing some basic actions in AWS. AWS provides a number of tutorials for getting started with the service.

You can start with these two:
- [Launch a Linux VM With EC2](https://aws.amazon.com/getting-started/tutorials/launch-a-virtual-machine/?trk=gs_card)
- [Deploy Code to a VM](https://aws.amazon.com/getting-started/tutorials/deploy-code-vm/?trk=gs_card)

If you finish those early, feel free to try out any of the other tutorials listed [here](https://aws.amazon.com/getting-started/tutorials/).

----

## Conclusion (5 min)

Soon, we'll work through deploying an entire application on AWS, which is a lot more complicated than what you've deployed so far.
