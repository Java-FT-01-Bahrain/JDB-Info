# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Docker Ladder Challenge

| Title                   | Type    | Duration | Author        |
|-------------------------|---------|----------|---------------|
| Docker Ladder Challenge | Lab | 2:00     | Suresh Sigera |

The Docker Ladder Challenge is designed to guide students from basic Docker commands to advanced usage, culminating in
the successful deployment of a Dockerized Nginx server. Each step of the ladder introduces new concepts and commands,
building upon the previous ones.

1. Run `docker --version` to confirm the installation.
2. Use `docker run hello-world` to pull and run the 'hello-world' image from Docker Hub.
3. Use the command `docker pull ubuntu` to download the Ubuntu image from Docker Hub. Then, display a list of all
   downloaded Docker images with the command `docker images`.
4. Run an Ubuntu container with an interactive shell using `docker run -it ubuntu /bin/bash`. Exit without stopping the
   container.
5. Download the Apache Docker image using the command `docker pull httpd`.
6. Launch the Apache image as a web server with the command `docker run -d -p 80:80 --name my-apache-server httpd`.
7. Visit [http://localhost/](http://localhost/) and verify web server is running.
8. List all running containers using `docker ps`.
9. Stop the web server by executing the command `docker stop my-apache-server`.
10. List all stopped containers using `docker ps -a` command.
11. Delete the `my-apache-server` container by using the command `docker rm [container-id]`, where `[container-id]` is
    the specific ID of the container.
12. Create a new Nginx container with the name `my-web-server` by executing the Docker
    command: `docker run --name my-web-server -d -p 80:80 nginx`.
13. To verify that the `my-web-server` container is running, you can use the Docker command `docker ps`.
14. Create a new Docker network using `docker network create my-network`.
15. To see all the networks in your Docker environment, execute the command `docker network ls`.
16. Connect your `my-web-server` container to the `my-network` network by using the Docker command: `docker network
    connect my-network my-web-server`.
17. To verify if the `my-web-server` container is using the `my-network` network, use the `docker inspect my-web-server`
    command. This command provides detailed information about the container configuration, including its network
    settings.
18. Alternatively, you can execute the command `docker network inspect my-network` to view a list of containers that are
    connected to the `my-network`.
19. Create a new Docker volume called `my-volume` by executing the command: `docker volume create my-volume`. This
    command will create a new volume in Docker with the name `my-volume` which you can then use for persistent data
    storage with Docker containers.
20. To attach the `my-volume` Docker volume to your `my-web-server` container, you need to specify the volume when you
    run or create the container. If `my-web-server` is already running, you'll need to stop and remove it first, then
    recreate it with the volume attached.
    - Stop the Existing Container (if it's running): `docker stop my-web-server`
    - Remove the Existing Container: `docker rm my-web-server`
21. Run the Container with the Volume Attached: Use the `docker run` command to recreate `my-web-server` and attach
    `my-volume`. For example, you might want to attach the volume to a specific directory inside the container, like
    `/usr/share/nginx/html` for website content. The command looks like
    this: `docker run --name my-web-server -d -p 80:80 -v my-volume:/usr/share/nginx/html nginx`. In this command, `-v
    my-volume:/usr/share/nginx/html` attaches `my-volume` to the `/usr/share/nginx/html` directory inside the container.
    You can adjust the container path (`/usr/share/nginx/html`) based on where you want to use the volume in the
    container.
22. To update the "Welcome to nginx!" message to "Hello world!" in your Dockerized Nginx server, you should overwrite
    the `index.html` file in the container with the new message.
    - Access the shell of your running Nginx container named "my-web-server" by executing the
      command: `docker exec -it my-web-server /bin/bash`.
    - Next, execute the command `echo "Hello world!" > /usr/share/nginx/html/index.html` within the Nginx container to
      update the content, and then type `exit` to leave the container.
    - To confirm the changes, open your web browser and go to [http://localhost/](http://localhost/).
23. Use the `docker stop` command to gracefully stop the container.
24. Then remove `my-web-server` using `docker rm my-web-server` command.
25. Finally, delete the volume `docker volume rm my-volume`.
