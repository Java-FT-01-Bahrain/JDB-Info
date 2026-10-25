| Title | Type | Duration | Author |
| -- | -- | -- | -- |
| Twelve-Factor Application Design | Lesson | 1:30 | Prateek Parekh |     

# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Twelve-Factor Application Design

## Learning Objectives

*After this lesson, students will be able to:*

- Define cloud native and the Twelve-Factor App Methodology.
- Describe each of the 12 factors.
- Group each factor into critical, high, medium, or low importance.

## Lesson Guide

| TIMING  | TYPE  | TOPIC  |
|:-:|---|---|
| 5 min  | Opening   | Lesson Objectives |
| 50 min | Exercise  | The 12 Factors|
| 25 min | Exercise  | Prioritize the Factors |
| 5 min  | Conclusion | Review/Recap |

## Opening (5 min)

A **cloud-native application** is an application that has been designed and implemented to run on a Platform-as-a-Service (PaaS) installation and embrace horizontal elastic scaling. 

The Twelve-Factor App Methodology was developed at PaaS provider [Heroku](https://www.heroku.com/) (now a Salesforce subsidiary); its official manifesto can be found [here](https://12factor.net/). These patterns emerged based on a large number of deployments by Agile teams developing web applications.

> **Note**: The terms "cloud native" and "twelve-factor" are sometimes used interchangeably.

----

## The 12 Factors (45 min) 

Through this methodology, developers can achieve straightforward deployment on numerous cloud platforms and high scalability without the need to change tooling, architecture, or how a team works. 

The goal is to help developers build modern distributed web applications. It prioritizes automation, continuous deployment, easy onboarding of new developers, and portability between execution environments.

These best practices can be applied to apps written in any language, using any combination of backing services. They can help transform a legacy application into an Agile, highly scalable, and resilient one.

So, let's explore each of the factors! I'll assign one of the 12 factors to every person in the class (you might work in pairs). On your own, do some research on your assigned factor. Focus on:
- Why is this important? What benefit does it bring to our applications?
- What happens if we *don't* do this?
- What tools are useful here?

You'll have a few minutes to research, then you'll share your findings with the class.

### 1. Codebase

> One codebase tracked in revision control; many deploys.

A twelve-factor application is built in a single codebase that's deployable on multiple environments (staging, QA, production). The entire history of code should be trackable.

Imagine that you have an application that's made up of a dozen or more source code repositories. It becomes extremely challenging to automate the build and deployment phases of your application's life cycle. That's not twelve-factor compliant!

Similarly, one codebase should not produce multiple applications. This is often an indication of multiple teams managing the same codebase. Ideally, each of these applications should be split into its own microservice with its own codebase. Any code that's common across multiple applications should be in its own codebase. 

**Tool tip**: Git and Subversion are two common tools for tracking the history of a codebase. 
  
### 2. Dependency Management
   
> Explicitly declare and isolate dependencies.

You should define dependencies in a manifest configuration file and build all system packages within these dependencies during deployment. 

Properly managing your application’s dependencies is all about the concept of repeatable deployments. Unless the runtime environment that an application is deployed into is automated, we can't assume how it works.

In an ideal world, the application’s container is bundled (or bootstrapped, as some frameworks call it) inside the app’s release artifact. Or, better yet, the application has no container at all. In doing so, the application is provided true portability, as it doesn’t rely on any binaries specific to your platform.

**Tool tip**: Maven, Gradle, and Ant are some of the most common tools that do this for Java applications.

### 3. Application Configuration
 
> Store config in the environment.
 
External configuration supports our ability to deploy immutable builds to multiple environments automatically (via CD pipelines) and helps us maintain parity across the development/production environments.
 
**Configuration** refers to any value that can *vary* across deployments. Such as: 
  - URLs and other information about backing services, such as web services and SMTP servers.
  - Information necessary to locate and connect to databases.
  - Credentials to third-party services such as Amazon AWS or APIs such as Google Maps, Twitter, or Facebook.
    
Configuration does *not* include internal information that is part of the application itself. If the value remains the same across all deployments (i.e., if it is intentionally part of your immutable build artifact), then it isn’t configuration.

**Tool tip**: Some server products such as Spring Cloud Configuration Server are designed to expose configuration. 

### 4. Backing Services

> Treat backing services as attached resources.

Backing services allow an application to easily attach or detach bound resources as needed. For example, if one of your dependent services is not responding, the administrator can launch a new instance of this service and change the binding of your application to this new service with minimal impact and no cascading failures.

Spring framework provides this abstraction via config, which makes it easy to swap out services.
 
Below is an application that relies on a set of backing services. It is not relying directly on OS-level disk access but instead uses an abstraction for file storage (in this case, Amazon S3).

![https://i.imgur.com/7tDiCob.png](https://i.imgur.com/7tDiCob.png)
 
### 5. Build, Release, Run (aka, Continuous Integration/Continuous Delivery) 

> Strictly separate build and run stages.

A twelve-factor app is built in distinct phases via pipelines in a non-production environment.

In this diagram, each of the stages are isolated and take place separately. 

1. The codebase is taken through the build process to produce a **compiled artifact**. 
1. This artifact is then merged with configuration information that's external to the application to produce an immutable release.
1. This is delivered to a cloud environment and run. 

It is important for this release to be immutable for the purposes of testing, ensuring dev/prod parity, and predicting deployment results.

![https://i.imgur.com/rxVpFGD.png](https://i.imgur.com/rxVpFGD.png)

**Tool tip**: Jenkins or Travis are two common tools for building and automating pipelines.
 
### 6. Stateless Processes

> Execute the app as one or more stateless processes.

A stateless application makes no assumptions about the contents of memory prior to handling a request, nor does it make assumptions about memory content after handling that request.

In other words, all long-lasting states must be external to the application, provided by backing services. Your application does have a state, but it cannot be maintained within the application.

For example, a microservice exposing functionality for user management is stateless, but the list of all users is served by a backing service such as a MongoDB database.

### 7. Port Binding 

> Export services via port binding.

Your application consists of processes that communicate over protocols usually bound to non-standard ports so that multiple processes running in the same OS container over same protocols can run in isolation. The Spring framework handles this for you via dependency injection, when environment variables are injected during runtime.

In non-cloud environments, a container is responsible for assigning ports for applications during start-up. However, in cloud-native applications, the cloud provider manages port assignment for your application, in addition to routing, scaling, and fault tolerance. It also handles routing host names to ports and mapping external port numbers to container-internal ports.

### 8. Concurrency 

> Scale out via the process model.
 
When we scale with processes, multiple services are broken down into unique applications and run as distinct processes. The benefit is that, under increased load, processes scale horizontally (spawn a new process) and not vertically (increase existing system resources). This gives us concurrency during increased demand.

Horizontal scaling and running multiple instances of your application are inherent benefits of building disposable, stateless, and share-nothing processes.

**Tool tip**: Cloud providers such as Amazon AWS and Microsoft Azure make it very straightforward to configure rules that will dynamically scale the number of instances of your application when needed.

### 9. Disposability
  
> Maximize robustness with fast startup and graceful shutdown.
  
Disposability means that an app should be able to start and stop rapidly on demand. This helps with auto-scaling application instances and is critical for security and fault tolerance during crashes.

For your application to be truly disposable, it cannot perform any long-running activity (such as fetching data to populate cache or preparing runtime dependencies) during start-up. Instead, these activities should be delegated to backing services so that your application spends as little time as possible when starting up or shutting down. Otherwise, it can also end up triggering alerts and warnings from the application failing its periodic health checks.

### 10. Development/Production Parity

> Keep development, staging, and production as similar as possible.

The difference between production and non-production code should be within a few working features at most. Otherwise, the time to roll out a fix for a production bug can increase significantly. Resources and technologies should also match as closely as possible between the two environments.

A very well-known cloud-native **anti-pattern** is the application behaving differently between QA and production environments. This could be caused by different security rules, firewall settings, configuration, database drivers on prod and QA environments... anything! The problem is exacerbated when the automation tests only work on one of the environments, further eroding the confidence in application deployment.

Ideally, a build pipeline triggers application deployment across all possible environments with limited, if any, manual steps. This allows every commit to be a candidate for deployment to production.
 
### 11. Logs

> Treat logs as event streams.
 
Twelve-factor apps leverage logging frameworks and log routing technologies to allow efficient log aggregation, retrieval, and storage. As your system grows, logs help you understand the current state of your system and track errors when your system has a bug.

Logs should be treated as event streams: A sequence of events emitted from an application in time-ordered sequence. 

**Tool tip**: Your application should not be concerned with aggregating, processing, or storage of logs. Instead, this should be handled by the cloud provider or with tools such as the ELK stack (Elasticsearch, Logstash, and Kibana), Splunk, or Sumo Logic. Your application code not only becomes simpler, but it gives you the flexibility to change how you store and process logs without any underlying change to your application.
 
#### 12. Admin Processes

> Run admin/management tasks as one-off processes. 

Tasks are applications themselves with their own codebase. They are spawned as needed, usually by some overall management application, and run in their own execution environment until they complete and shut down.  

Below is a classic enterprise architecture of an application that has its regular duties and supports batch or timed operation via the execution of shell scripts. This is obviously not a suitable candidate for cloud.

![](https://i.imgur.com/8XKJvkf.png)

To address this issue, one can expose a RESTful endpoint that can be used to invoke ad-hoc functionality. Alternatively, the batch-related code can be extracted into its own microservice:

![](https://i.imgur.com/QOdi6Nu.png)

---

## Factor Ranking (20 min) 

Each of the 12 factors is valuable in its own right, but some are more critical than others. Let's determine what they are!

**Part 1**

On your own, take a few minutes to prioritize the factors into four categories:

- Critical
- High
- Medium
- Low

**Part 2**

When everyone is done, find a partner and share how you prioritized the factors and why.

Based on what each of you came up with, create a common version on which you both agree.

**Part 3**

And now for the big reveal! Believe it or not, there *is* a correct answer (according to the powers that be). Let's review the official categorization as a class and discuss if we agree with the approach.

<details>
	<summary>Critical</summary>

  - Codebase

</details>

<details>
	<summary>High</summary>

  - Dependency Management
  - Backing Services
  - Stateless Processes
  - Admin Processes

</details>

<details>

<summary>Medium</summary>

  - Application Configuration
  - Disposability
  - Port Binding 
  - Dev/Prod Parity

</details>

<details>

<summary>Low</summary>

  - Concurrency
  - Logs

</details>

<details>

<summary>It Depends...</summary>

  - Build, Release, Run (CI/CD)

</details>

----
 
## Conclusion (5 min) 
 
While the applications we'll be building for this course aren't entirely twelve-factor compliant themselves, we'll start to incorporate different parts of the methodology into everything we build. As you'll see, there's a lot of overlap between the 12 factors and other concepts we'll learn about, including CI/CD, Agile development, and DevOps.
 
## References

- [Manifesto](http://12factor.net/)  
- [Wikipedia: Twelve-Factor App Methodology](https://en.wikipedia.org/wiki/Twelve-Factor_App_methodology)  
- [Building Microservices by Sam Newman](http://shop.oreilly.com/product/0636920033158.do)     
- [Role-Based Access Control](https://en.wikipedia.org/wiki/Role-based_access_control)    
- [Beyond the Twelve-Factor App by Kevin Hoffman](https://www.oreilly.com/library/view/beyond-the-twelve-factor/9781492042631/)    


