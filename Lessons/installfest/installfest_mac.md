# Text Editor(VS Code)

## Install visual studio code on mac.
### Four easy steps can do the job for you.

1. Download [Visual Studio Code for MAC](https://code.visualstudio.com/download)
1. Double-click on the downloaded archive to expand the contents.
1. Drag Visual Studio Code.app to the Applications folder, making it available in the Launchpad.
1. Add VS Code to your Dock by right-clicking on the icon and choosing  Options  Keep in Doc.

After installing the VS Code, we can also configure the code to open from a command line, and it is pretty darn easy for us to do that.

![vscode image](uploads/vscode.png)
1. Launch VS Code.
1. Open the Command Palette (⇧⌘P) and type `shell command` to find the Shell Command: Install `code` command in PATH command.
Now, if you have created any project that goes into that folder and hit the following command to open that project into the Visual Studio Code.

```
code .

```

# Installing HomeBrew
### How to Install Homebrew on Mac OS

The simplest way to install Homebrew is through ruby and curl, accomplished with a single command. This approach is the same for installing Homebrew in all supported versions of Mac OS and Mac OS X.

1. Open the “Terminal” application, found in /Applications/Utilities/
2. Enter the following command into a single line of the terminal:

```bash
  /usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
```
3. Hit Return and you’ll see a series of lines about what the script will install and where, hit Return again to agree or hit Escape to cancel
4. Enter the administrator password (required by sudo execution) to begin installation

>Installation of Homebrew will take a while depending on the speed of your Mac and internet connection, as each necessary package is downloaded and installed by the script.

When complete, you will see an “Installation successful!” message.

## Install GIT for Mac
The easiest way to install Git on a Mac is via the stand-alone installer but If you have installed `Homebrew` to manage packages on OS X, you can follow these instructions to install Git:

1. Open your terminal and install Git using Homebrew:

```bash
  $ brew install git
```
Skip to `3` after this is completed else follow the steps below.

1. Download the latest [Git for Mac installer](https://sourceforge.net/projects/git-osx-installer/files/).
2. Follow the prompts to install Git.
3. Open a terminal and verify the installation was successful by typing git --version:
```bash
  $ git --version
  git version 2.9.2
```
4. Configure your Git username and email using the following commands, replacing Emma's name with your own. These details will be associated with any commits that you create:
```bash
  $ git config --global user.name "Emma Paris"
  $ git config --global user.email "eparis@atlassian.com"
```
(Optional) To make Git remember your username and password when working with HTTPS repositories, configure the [git-credential-osxkeychain helper](https://www.atlassian.com/git/tutorials/install-git#install-the-git-credential-osx).

## Java SE Development Kit 

Run `java -version` on the Windows cmd prompt. If the JDK version is 11.00 or higher, you can skip this step.

[Download and install Java JDK (Version 17)](https://www.oracle.com/bh/java/technologies/downloads/#java17)

## IntelliJ IDEA

[Download and install IntelliJ IDEA Community Edition](https://www.jetbrains.com/idea/download/#section=windows)

Make sure to download the Community edition and not the Ultimate edition.


