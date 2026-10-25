# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Intro to Docker

| Title | Type | Duration | Author |
| -- | -- | -- | -- |
| Intro to Docker | Lesson | 0:55 | Prateek Parekh |

## Learning Objectives

- Differentiate between VMs and containers.
- Identify when (and when not) to use Docker.

## Lesson Overview 

| Topic | Type | Timing |
| --- | --- | -- |
| Opening       | Intro | 5 min |
| The Problems Docker Solves | Lecture | 15 min |
| How Docker Works | Lecture | 10 min |
| Debate and Defend | Exercise | 20 min |
| Wrap Up | Conclusion | 5 min |

## Opening (5 min)

![whales!](https://www.brianweet.com/assets/docker-blog-1/docker-logo.png)

Docker is a tool designed to make it easier to create, deploy, and run applications by using **containers**. Containers allow a developer to package up an application with all of the parts it needs, such as libraries and other dependencies, and ship it all out as one component. The container becomes the unit for distributing and testing your application.

We know that's a bit of mumbo jumbo, so let's see what that means.

## The Problems Docker Solves (15 min)

Let's tell a little story.

In the olden days of [Web 2.0](https://en.wikipedia.org/wiki/Web_2.0) (basically, the early 2000s), there was a huge difference between the machine that a developer used to build an app and what was used to deploy that app. It took days, if not weeks, to "provision" or build a computer with the right hardware and software to deploy an app. More often than not, an app that was built on a developer's machine did not work properly in production. These issues could range from the dependencies not connecting, to a different operating system, to versioning problems... or worse.

Or, to put it another way:

![meme](https://static.packt-cdn.com/products/9781787286986/graphics/B06455_07_01.jpg)

### One New Way

Things started getting a bit better when **virtual machines** were introduced.

In computing, a virtual machine (VM) is an emulation of a computer system. Virtual machines are based on computer architectures and provide the functionality of a physical computer. VMs can keep applications on the same hardware completely separate.

VMs work because they reduce conflicts among software components and minimize competition for hardware resources. However, they're bulky (each machine requires its own operating system), difficult to maintain, and use up a lot of storage and resources.

### The BETTER New Way

While VMs are still used by many companies, other developers kept trying to make things better.

Enter containers.

In contrast to VMs, containers isolate applications’ execution environments *but* share the underlying operating system kernel. They use far fewer resources than VMs and start up almost immediately. What's more, they can be packed far more densely on the same hardware and spun up and down en masse with far less effort and overhead. 

> **Knowledge Check**: Does anyone know what we mean by an operating system "kernel"?

Containers are an efficient, granular tool for combining software into enterprise-level applications and services.

## How Docker Works (10 min)

Docker is an open-source platform (the most popular one) for building applications using containers.

However, Docker isn't a completely new technology. Many of the components and principles existed previously. Docker is written in Go and takes advantage of several features of the Linux kernel to deliver its functionality, including namespaces and cgroups (more on those in the Additional Resources section).

The ultimate goal of Docker is to mirror our dev environment with our production environment. This is mostly useful for your back-end but can be applied to your front-end applications as well! It's cool to run Docker locally, but its real benefit comes into play while running it on some production machine.

### The Main Components of Docker

OK, yes, the short answer is "containers," but those don't just grow on trees. Where do containers come from, you might ask? Let's find out! 

**The High Level**

`Dockerfile --> Image --> Container --> Docker Engine`

**The Details**

- **Dockerfile**: Instructions to create an image.
	- Specifies the OS, languages, etc.
	- Explains what the container will do when it's run.
	- You can create your own or use premade ones.

- **Image**: A blueprint to build a container.
	- A portable file system that may contain files for OS, files from framework, and files for app.
	- Tells each component what to do and when.
	- Containers can share image layers, making them very efficient. 

- **Container**: You already know this! An isolated instance created from an image, running the app on whatever OS you have.

- **Docker Engine**: Integrates with the OS to run the Docker containers; sits directly on top of the OS. It's very fast and inexpensive.

In this course, we'll also be using **Docker Compose**, a tool for simplifying the process of developing and testing multi-container applications.

## Docker: Debate and Defend (20 min)

These days in the developer world, Docker feels a little bit like this:

![meme2](https://i.imgur.com/pgSrnRE.png)

But, like anything, Docker isn't right for every situation. So let's think things through!

I'm going to split half the room into "Pro Docker" and half the room into "No Docker." 

Take the next 5–10 mins in your group to do some research to support your argument. Look for:

- The benefits/drawbacks of Docker.
- Types of apps or businesses for which Docker is best suited.
- Examples of businesses that use (or don't use) Docker.

Then, we'll host the Great Docker Debate and hear from both sides!

<!--**Instructor Note**: Give teams time to research the pros and cons of Docker and then have each side present for a few minutes. There doesn't need to be a "winner" in the debate — the point is more to explore the pros and cons of each side, especially because Docker is SO popular and everyone is crazy about it right now.-->

## Conclusion (5 min)

Before we get to installing and using Docker, let's take a moment to recap.

<!--**Instructor Not**e: Lead discussion with the class.-->

1. What's a container?
2. What's a VM?
3. How does Docker work?
4. Why is Docker so popular right now?

## Additional Resources

- [Docker Underlying Technology](https://docs.docker.com/engine/docker-overview/#the-underlying-technology)
- [Wikipedia: Namespaces](https://en.wikipedia.org/wiki/Linux_namespaces)
- [Cgroups](https://en.wikipedia.org/wiki/Cgroups)
