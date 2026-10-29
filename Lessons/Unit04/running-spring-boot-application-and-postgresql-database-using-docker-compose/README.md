| Title | Type | Duration | Author |
| -- | -- | -- | -- |
| Running Spring Boot Application and PostgreSQL Database Using Docker Compose | Lesson | 2:25 | Suresh Melvin Sigera |

### Learning Objectives

*By the end of this lesson, students will be able to:*

- Pull an image from Docker Hub and run a container on your machine.
- Use common Docker CLI commands.
- Run source code as a container volume.

## Setting Up PostgreSQL Base Project
I’ve created a simple REST API using Spring Boot with PostgreSQL database usage. First download or clone from  [GitHub](https://git.generalassemb.ly/sureshmelvinsigera/Java-Spring-Boot-Recipe-App).

Or else you can skip this step and try docker compose setup with your own spring boot application which uses PostgreSQL database.

## Setup Docker Image For Spring Boot Application

In this lesson, our docker-compose setup using three main services or components. Those are the  **Spring Boot application**, **PostgreSQL database.**, **PgAdmin4.**

PostgreSQL is already available as a docker image from  [docker hub](https://hub.docker.com/_/postgres). You can just go and select the  [version](https://hub.docker.com/_/postgres?tab=tags&page=1&ordering=last_updated)  you need from there.

But your  **project is still not pushed or published as a docker image**. Hence first you need to have docker image for your spring boot application. Then you can use both images with docker-compose.

Great! Let’s start the docker image creation,

Now we can create a file naming as  **Dockerfile**  in your project root folder. Then copy the following content into that file.

```
# For Java 11  
FROM openjdk:latest  
  
# create temporary drive  
VOLUME /tmp  
  
# expose ports 8080 for spring boot  
EXPOSE 8080  
  
# create app dir  
RUN mkdir -p /app/  
  
# create logs dir  
RUN mkdir -p /app/logs/  
  
# cp demo-0.0.1-SNAPSHOT.jar /app/application.jar  
ADD target/demo-0.0.1-SNAPSHOT.jar /app/app.jar  
  
# run the .jar file with the active profile dev  
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=dev", "-jar", "/app/app.jar"]
```

Now you have two ways of building docker compose setup.

1.  Pointing  **Dockerfile**  into the docker-compose setup.
2.  Giving created docker image to the docker-compose setup.

**Let’s go forward with pointing docker file way, since giving docker image part will cover with postgreSQL image in this same setup.**

## Docker Compose File With Spring Boot and PostgreSQL

Now our application has the instruction to building the Docker image. So let’s create the  **docker-compose.yml**  file which  **allows us to combine Spring Boot application and PostgreSQL**  database in this setup.

First, go to the project root and create a file named  **docker-compose.yml**

#### Using links to Build Communication Between Services.

There are  **two ways of building communication**  between spring boot application and the PostgreSQL database**. You can use one from the following two methods to build this docker-compose setup**.

I’ll start with using links to build communication between both. So add the following content into the docker-compose.yml file,

Here we have out two main services as,

1.  **api_service**  – Spring Boot REST API which runs on port 8080
2.  **postgres_db**  – PostgreSQL DB which runs on port 5432
3. **pgadmin**  – PostgreSQL DB which runs on port 5050


```
version: "3.7"  
  
services:  
  api_service:  
    build: .  
    restart: always  
    ports:  
      - 8080:8080  
    depends_on:  
      - postgres_db  
    links:  
      - postgres_db:database  
    networks:  
      - postgres  
  
  postgres_db:  
    image: "postgres:11.4"  
  restart: always  
    ports:  
      - 5432:5432  
    environment:  
      POSTGRES_DB: java_to_dev_app_db  
      POSTGRES_PASSWORD: nE5kMc7JCGNqwDQM  
    volumes:  
      - postgres:/data/postgres  
    networks:  
      - postgres  
  
  pgadmin:  
    container_name: pgadmin_container  
    image: dpage/pgadmin4  
    environment:  
      PGADMIN_DEFAULT_EMAIL: ${PGADMIN_DEFAULT_EMAIL:-pgadmin4@pgadmin.org}  
      PGADMIN_DEFAULT_PASSWORD: ${PGADMIN_DEFAULT_PASSWORD:-admin}  
    volumes:  
      - pgadmin:/root/.pgadmin  
    ports:  
      - "${PGADMIN_PORT:-5050}:80"  
  networks:  
      - postgres  
    restart: unless-stopped  
  
networks:  
  postgres:  
    driver: bridge  
  
volumes:  
  postgres:  
  pgadmin:
``` 

Explanation about the configurations I have used in this docker-compose file,

**build**  – Here we should introduce the image name to build under that service, Here we have our Dockerfile which developed for the API in the same level with docker-compose.yml so using “.” it will capture that docker file and build the handle the api_service when executing this file.

Additionally we have introduced latest postgres as the docker image for postgreSQL DB. It will download from docker registry and start when needed.

More to research: We can do the same to spring boot API docker image as well, Just push that image to docker-hub and you can directly use given image name and version for this setup as well.

**restart**  –  **always**  Always restart the container if it stops. If it is manually stopped, it is restarted only when Docker daemon restarts or the container itself is manually restarted.

**ports**  – Here we are mapping  **local machine port**  along with  **port inside a docker container**.

**links**  – Here we are setting a link with postres_db service. So we can use  **database**  when defining ip address for database connection on our spring boot application. Then it will connects with the database running with this docker-compose.

**environment**  – In this section we are setting the PostgreSQL database and postgres user password for this specific postgreSQL instance.

All done, Now let’s change our  **application.properties**  to support this database and application server setup.

```
spring.datasource.url=jdbc:postgresql://postgres_db:5432/java_to_dev_app_db  
spring.datasource.username=postgres  
spring.datasource.password=nE5kMc7JCGNqwDQM  
spring.datasource.driver-class-name=org.postgresql.Driver  
spring.jpa.hibernate.ddl-auto=update  
spring.datasource.initialization-mode=always
```

Here as you can see we are using that link value we used in docker-compose definition for host/IP address. additionally, password and DB has changed accordingly.

## Using Depends On to Build Communication Between Services**

**The docker compose documentation specifies that `links` is deprecated and should be replaced with `depends_on`.**

So here I have set the depends_on flag.


## Running Spring Boot Application and PostgreSQL Database Using Docker Compose

Now we have our docker compose setup for this application. So first create a jar build for this application using following command,

Navigate to application root folder and execute,

```
$ mvn package -Dmaven.test.skip=true
```

Now there should be a newly created jar file with all the necessary files to run this application on  **build/libs**  folder.

Then create the build with docker compose to build docker image using built jar file.

```
$ docker-compose build
```

Then use following command to run whole setup using docker compose.

```
$ docker-compose up
```

Then It will capture the docker-compose.yml and start running using the instructions given on that file.

## Conclusion

The Docker Compose Spring Boot with PostgreSQL with practical usage. Hope you got a good understanding of how we can use docker, docker-compose with spring boot application development and deployment.


## Additional notes


### Access to postgres: 
* `localhost:5432`
* **Username:** `postgres` (as a default)
* **Password:** `nE5kMc7JCGNqwDQM` (as a default)

### Access to PgAdmin: 
* **URL:** `http://localhost:5050`
* **Username:** pgadmin4@pgadmin.org (as a default)
* **Password:** admin (as a default)

### To add a new server in PgAdmin:
* **Host name/address** `postgres_db`
* **Port** `5432`
* **Username** as `POSTGRES_USER`, by default: `postgres`
* **Password** as `POSTGRES_PASSWORD`, by default `nE5kMc7JCGNqwDQM`

### Spring boot service:
* **Host name/address** `localhost`
* **Port** `8080`


## Lab: You do 

- Dockerized your Mini Concurrency project.
