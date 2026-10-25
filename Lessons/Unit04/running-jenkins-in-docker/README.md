# Running Jenkins in Docker

**Total: 195 minutes (3 hours, 15 minutes)**

#### Learning Objectives
- Set up and run Jenkins as a Docker container on your local machine
- Configure Docker volumes to persist Jenkins data across container restarts
- Access and configure Jenkins through its web interface
- Install and configure essential Jenkins plugins for CI/CD workflows
- Create and execute a basic Jenkins pipeline for a Java application

#### Introduction (10 minutes)

Jenkins remains the backbone of CI/CD infrastructure in countless organizations, and for good reason—it's battle-tested, extensible, and gives you complete control over your build pipelines. But here's the thing: installing Jenkins directly on your local machine creates a mess. Configuration files scatter across your filesystem, dependencies conflict with your development environment, and cleanup becomes a nightmare when you want to start fresh.

Docker solves this elegantly. When you run Jenkins in a container, everything—every plugin, every job configuration, every build artifact—lives in an isolated environment. You can destroy and recreate your Jenkins instance in seconds. You can run multiple Jenkins servers simultaneously for different projects. And most importantly, you maintain complete control over persistence through Docker volumes, so you never lose your carefully crafted pipelines and build history.

We're going to set up a production-grade local Jenkins environment. Not a toy setup—something you'd actually use for real development work. You'll learn how Docker's volume system keeps your Jenkins state intact, how to access the container's filesystem when you need to, and how to configure the toolchain (Maven, JDK) that your builds require. By the end, you'll have a Jenkins instance that builds a Java application automatically, and you'll understand every piece of the infrastructure supporting it.

#### Prerequisites and Docker Installation (10 minutes)

Before we proceed, you need Docker installed and running on your machine. If you're on macOS or Windows, Docker Desktop is the standard choice. Linux users should install Docker Engine directly. The installation process is straightforward—follow the official Docker documentation at docs.docker.com/install if you haven't already set this up.

Once Docker is running, verify your installation by checking the version. Open your terminal and run `docker --version`. You should see output indicating Docker version 20 or higher. If you see an error about the Docker daemon not running, start Docker Desktop (or the Docker service on Linux) and try again.

#### Pulling the Jenkins Image (10 minutes)

Jenkins maintains official Docker images that are continuously updated and tested. The image tagged `lts` (long-term support) gives you the stable release channel—this is what you want for any environment where reliability matters. Pull the latest LTS image with this command:

```shell
docker image pull jenkins/jenkins:lts
```

Docker will download several layers totaling around 500MB. This image contains a complete Jenkins installation with the JRE, so you don't need Java installed on your host machine. The download happens once; subsequent container launches will use this cached image instantly.

#### Understanding Docker Volumes for Data Persistence (15 minutes)

Here's a critical concept: containers are ephemeral by design. When you stop and remove a container, everything inside it disappears. For a development tool, that's obviously unacceptable—you can't lose your Jenkins jobs and build history every time you restart your machine.

Docker volumes solve this problem. A volume is a directory managed by Docker that exists independently of any container. When you mount a volume into a container, that directory's contents survive container destruction. Jenkins stores everything important in `/var/jenkins_home` inside the container—job configurations, plugin data, build logs, credentials, everything. By mounting a volume at this path, we make Jenkins's state persistent.

Create a volume explicitly:

```shell
docker volume create jenkins-data
```

This creates a named volume called `jenkins-data`. Docker stores it somewhere on your host filesystem (the exact location varies by operating system), but you rarely need to access it directly. The name is what matters—it's how we'll reference this storage when launching the container.

If you skip explicit volume creation and use the `-v` flag during container launch, Docker creates an anonymous volume automatically. Named volumes are cleaner for management—you can list them with `docker volume ls`, inspect them with `docker volume inspect`, and delete them intentionally when you're done with a project.

#### Launching the Jenkins Container (10 minutes)

Now we'll launch Jenkins with the volume mounted and ports mapped correctly. The command looks intimidating at first, but each piece serves a specific purpose:

```shell
docker container run -d \
    -p 8082:8080 \
    -v jenkins-data:/var/jenkins_home \
    --name jenkins-local \
    jenkins/jenkins:lts
```

Breaking this down: `-d` runs the container in detached mode (background), so it doesn't block your terminal. `-p 8082:8080` maps port 8080 inside the container (where Jenkins listens by default) to port 8082 on your host machine. We use 8082 instead of 8080 because many development web servers claim 8080, and port conflicts are annoying. If 8082 is also taken on your system, pick any available port above 1024.

The `-v jenkins-data:/var/jenkins_home` argument is where persistence happens. It mounts our `jenkins-data` volume at `/var/jenkins_home` inside the container. Every file Jenkins writes to that directory actually lives in the volume, outside the container's lifecycle. `--name jenkins-local` gives the container a memorable name, so we can reference it later without looking up container IDs.

After running this command, Docker starts Jenkins in the background. Verify it's running with `docker ps`. You should see your `jenkins-local` container in the list with status "Up" and port mapping "0.0.0.0:8082->8080/tcp".

#### Initial Jenkins Configuration (15 minutes)

Open your browser and navigate to `localhost:8082`. Jenkins will display a security screen asking for the initial admin password. This password is randomly generated during first launch and stored inside the container. We need to read it from the container's filesystem using `docker exec`, which executes a command inside a running container:

```shell
docker container exec jenkins-local sh -c "cat /var/jenkins_home/secrets/initialAdminPassword"
```

This command runs a shell inside the `jenkins-local` container and uses `cat` to print the password file's contents. Copy the alphanumeric password that appears in your terminal and paste it into the Jenkins web interface.

After unlocking Jenkins, you'll see the plugin installation screen. Click "Install suggested plugins"—this installs a curated set of common plugins including Git integration, pipeline support, and various build tools. The installation takes a few minutes as Jenkins downloads and configures each plugin. This happens inside the container, but remember, plugin data is written to `/var/jenkins_home`, which is backed by your volume, so these installations persist across container restarts.

Once plugins finish installing, Jenkins prompts you to create the first admin user. Fill in a username, password, and email address. These credentials are how you'll log in from now on, so make them memorable but secure. After user creation, Jenkins asks about the instance URL. Since this is a local development setup, leave it as `http://localhost:8082`. Click "Save and Finish" and then "Start using Jenkins."

#### Installing Additional Plugins (15 minutes)

Jenkins's true power comes from its plugin ecosystem. We need several plugins that aren't in the default set, particularly for Docker integration and the Blue Ocean modern UI. From the Jenkins home page, click "Manage Jenkins" in the left sidebar, then "Manage Plugins."

The "Available" tab lists all plugins in the Jenkins plugin repository. Use the search box to find these plugins:

- **Blue Ocean**: A complete UI redesign that makes pipeline visualization much clearer than the classic Jenkins interface. It's particularly good for understanding multi-stage pipelines at a glance.
- **Docker**: Allows Jenkins to use Docker containers as build agents and provides Docker-related build steps.
- **Docker Pipeline**: Extends pipeline syntax with Docker-specific capabilities, letting you run pipeline stages inside Docker containers.

Select the checkbox next to each plugin, then click "Download now and install after restart" at the bottom of the page. Jenkins downloads the plugins and their dependencies. When the download completes, check the box that says "Restart Jenkins when installation is complete and no jobs are running." Jenkins will restart automatically—your browser might show a "Jenkins is restarting" message for about 30 seconds.

#### Configuring Java and Maven (15 minutes)

Jenkins needs to know where to find build tools. Rather than installing Java and Maven in the container manually (which defeats the purpose of containerization), we configure Jenkins to install them automatically when needed. This configuration lives in Jenkins's global tool settings.

From the Jenkins home page, go to "Manage Jenkins" → "Global Tool Configuration." Scroll down to the "JDK" section and click "Add JDK." Configure it like this:

- **Name**: `jdk17` (this identifier appears in pipeline scripts)
- **Install automatically**: Checked
- **Add Installer** → **Install from adoptium.net**
- **Version**: Select "jdk-17.0.9+9" or the latest Java 17 LTS version available

Jenkins will download this JDK when a pipeline first requests it. We use Java 17 because it's the current LTS release with strong ecosystem support. The JDK name `jdk17` is arbitrary but should be descriptive—you'll reference it in pipeline definitions.

Scroll down to the "Maven" section and click "Add Maven." Configure it:

- **Name**: `Maven 3.9.6` (use the actual latest Maven 3.9.x version)
- **Install automatically**: Checked
- **Install from Apache**: Version 3.9.6

Maven installs itself similarly to the JDK. The version number in the name helps you remember what you configured later. Click "Apply" and then "Save" at the bottom of the page.

#### Creating a Java Build Pipeline (30 minutes)

Pipelines are where Jenkins's real value emerges. A pipeline defines your build process as code—the exact sequence of steps Jenkins executes to pull your source, compile it, test it, and produce artifacts. Pipeline definitions live in Jenkinsfiles (usually checked into your repository), but for this example, we'll define the pipeline directly in Jenkins's UI.

We're going to build a simple Java application using Maven. First, you need a Java project to build. Fork this repository to your GitHub account: `https://github.com/sureshmelvinsigera/hello-world`. This gives you a basic Maven project with a `pom.xml` and some source code. Clone it locally if you want to examine the structure, but Jenkins will clone it directly from GitHub during the build.

Back in Jenkins, click "New Item" from the home page (or "Create new jobs" if you're starting fresh). Name the item something descriptive like `java-maven-build`. Select "Pipeline" as the project type and click OK. This creates a pipeline project, which is the modern approach to defining Jenkins jobs.

Scroll down to the "Pipeline" section. The default is "Pipeline script," which means we'll define the build process right here in the UI. In a real project, you'd select "Pipeline script from SCM" and point Jenkins at a Jenkinsfile in your repository, but inline scripts are fine for learning.

In the script editor, enter this pipeline definition:

```groovy
pipeline {
    agent any
    
    tools { 
        maven 'Maven 3.9.6' 
        jdk 'jdk17' 
    }

    stages {
        stage('Hello') {
            steps {
                echo 'Starting Java Maven build pipeline'
            }
        }
        
        stage('Clone Repository') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/sureshmelvinsigera/hello-world.git'
            }
        }
        
        stage('Build') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }
        
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
    }
    
    post {
        always {
            echo 'Pipeline execution completed'
        }
        success {
            echo 'Build succeeded'
        }
        failure {
            echo 'Build failed'
        }
    }
}
```

This pipeline does several things worth understanding:

The `agent any` line tells Jenkins this pipeline can run on any available agent (in our single-node setup, that's the Jenkins master itself). The `tools` block references our configured JDK and Maven by name—Jenkins makes these available in the pipeline's PATH before executing any stages.

Each `stage` represents a logical phase of the build. The `Hello` stage just prints a message—it's there to show that the pipeline started. The `Clone Repository` stage uses Jenkins's Git integration to clone your fork. We explicitly specify the branch because GitHub changed its default from `master` to `main`, and old tutorials sometimes cause confusion here.

The `Build` stage runs `mvn clean install -DskipTests`. This tells Maven to delete previous build artifacts (`clean`), compile the code, package it, and install the resulting JAR into Maven's local repository (`install`). We skip tests here because we run them separately in the next stage—this gives us finer-grained feedback when something fails.

The `Test` stage runs `mvn test`, which executes JUnit tests. If any test fails, the pipeline stops here and reports failure. The `post` block defines actions that run after the pipeline completes, regardless of success or failure. These `echo` statements would typically be replaced with notifications (Slack messages, emails) in a real pipeline.

Click "Apply" and then "Save." This stores the pipeline configuration in Jenkins.

#### Running the Pipeline (15 minutes)

From the pipeline's main page, click "Build Now" in the left sidebar. Jenkins queues the build and starts execution. You'll see a new build appear in the "Build History" section with a number (#1 for the first build). Click on that build number, then click "Console Output" to watch the build progress in real time.

The first execution takes several minutes because Jenkins needs to download the JDK and Maven, then Maven needs to download all the project dependencies. Subsequent builds are much faster because Docker volumes persist these downloads between runs.

Watch for each stage to complete. You'll see Maven output showing compilation, dependency resolution, and test execution. If everything works, the build succeeds and you see "Finished: SUCCESS" at the end of the console output. If something fails—maybe a test doesn't pass or there's a compilation error—Jenkins marks the build as failed and highlights the stage where it broke.

#### Visualizing with Blue Ocean (15 minutes)

The classic Jenkins UI shows build history as a list, but Blue Ocean presents pipelines visually. Click "Open Blue Ocean" from the left sidebar (you installed this plugin earlier). Blue Ocean shows your pipeline as a horizontal flow diagram with each stage as a box. Successful stages are green, failed stages are red, and running stages pulse with animation.

Click the "Run" button in Blue Ocean's interface to trigger another build. As it executes, you can click on individual stages to see their logs without navigating through multiple pages. Blue Ocean's visualization makes it immediately obvious where in a complex pipeline something went wrong—much more intuitive than the classic view for pipelines with many stages or parallel execution paths.

#### Managing Container Lifecycle (15 minutes)

Your Jenkins instance runs continuously until you explicitly stop it. To stop the container without destroying anything: `docker container stop jenkins-local`. Jenkins shuts down gracefully and the container stops, but all your data remains in the `jenkins-data` volume. Start it again with `docker container start jenkins-local` and everything is exactly as you left it—jobs, build history, plugins, everything.

If you want to completely remove the container (but keep the volume): `docker container rm jenkins-local`. This deletes the container itself but leaves the volume intact. You can create a new container with the same volume mount, and Jenkins will pick up exactly where the old container left off. This is useful when upgrading Jenkins versions—pull the new image, remove the old container, and launch a new one with the same volume.

To truly start fresh and delete everything: stop and remove the container first, then `docker volume rm jenkins-data`. This permanently deletes all Jenkins data. Only do this when you're certain you don't need anything in that Jenkins instance.

#### Understanding What Persists (15 minutes)

The volume mount means job configurations, plugin installations, credentials, build artifacts, and build history all survive container restarts. What doesn't persist is anything outside `/var/jenkins_home`—but Jenkins puts everything important in that directory by design.

You can inspect the volume's contents if needed: `docker volume inspect jenkins-data` shows metadata including the mount point on your host filesystem. On Linux, you could navigate to that directory directly. On macOS and Windows, Docker Desktop uses a VM, so the path isn't directly accessible, but you can use `docker exec` to explore the container's filesystem:

```shell
docker container exec -it jenkins-local sh
```

This gives you a shell inside the running container. Navigate to `/var/jenkins_home` and look around. You'll see directories like `jobs` (job configurations), `plugins` (installed plugins), `workspace` (where builds execute), and `secrets` (credential storage). Exit the shell with `exit` when you're done exploring.

#### Summary (10 minutes)

Running Jenkins in Docker transforms it from a system-level installation into a manageable development tool. The container isolates Jenkins completely from your host machine, preventing configuration pollution and dependency conflicts. Docker volumes provide transparent persistence—you get the benefits of containerization without losing your work when containers stop.

The setup we built handles the complete lifecycle of a Java application build. Jenkins automatically provisions the JDK and Maven, clones your repository, compiles the code, and runs tests. The pipeline definition lives as code (even though we put it in the UI for this example), making build processes reproducible and versionable. Blue Ocean's visualization makes complex pipelines comprehensible at a glance.

This foundation scales to real-world use. You can add stages for code quality analysis, security scanning, Docker image building, and deployment to staging environments. You can configure Jenkins to trigger builds automatically on repository commits using webhooks. You can add more agents (in containers or VMs) to parallelize builds across multiple machines. The patterns you've learned here—volume persistence, tool auto-provisioning, pipeline as code—apply to Jenkins installations of any size.

Most importantly, you can experiment fearlessly. Break things, try different plugin combinations, test pipeline syntax—if something goes wrong, destroy the container and volume, and rebuild in minutes. This disposability makes Docker-based Jenkins perfect for learning and development work, while the same architecture supports production workloads when you're ready to deploy beyond your local machine.
