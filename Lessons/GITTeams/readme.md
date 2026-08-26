[![General Assembly Logo](https://camo.githubusercontent.com/1a91b05b8f4d44b5bbfb83abac2b0996d8e26c92/687474703a2f2f692e696d6775722e636f6d2f6b6538555354712e706e67)](https://generalassemb.ly/education/web-development-immersive)

# Git Teams & Workflow

## Learning Objectives

- Distinguish between git workflow models for collaborating on a project
- Explain and weigh the pros and cons of each of the 4 workflows we'll discuss
- Use branches and pull requests to isolate changes tied to specific features
- Efficiently and correctly resolve merge conflicts

## Framing

Although you've all been using Git and Github for a couple months, you've
largely been doing so individually. In the Real World™, you'll rarely develop a
project on your own - more likely, you'll be working as part of a team of
developers.

For the upcoming Project 3, we'll be working in small teams to gain a sense of
what collaborative development is like. Clear, repeatable version control
practices, combined with good communication make collaboration easier and more
efficient. In order to build up to that, we need to make sure we're building on
a solid foundation of Git basics.

## Review Git: Branching & Merging

### Why Git?

Apart from being free and open source, git is also a superior system to other
methods of version control in many ways. Git is a _"distributed" version control
tool_, meaning that there will be a 'redundant' copy of the repository held by
everyone working on the project.

It also means that there is no centralized approval structure for making changes
to a project; instead, every developer who clones the repository has their own
**complete copy**, which they can edit and change as they wish. This makes it
much easier to use when working in groups, since each member can have an
up-to-date and complete repository.

### Review Git Branching

You can think of branches as similar to forks that are contained within the
repository. They're useful for developers and teams of developers to maintain
different versions of a project from within the same repository.

You'll see branches used for a couple of common reasons:

1. **Experimentation**. By switching to a new branch, we can experiment with
   something. If the experiment fails, we can easily switch back to master (or
   another branch of our choice). If it succeeds, we can merge those changes
   into master.

2. **Isoloate Specific Features**. We can have one branch for the production
   version of the app and another branch for the version currently under
   development. If a customer files a bug report, we can make a branch from the
   production version of the app to fix the bug without affecting the features
   that are still under active development and not ready to be deployed.

3. **Parallel work**. By creating a branch, we can work on a feature in
   isolation (without affecting the rest of the codebase). When the feature is
   complete, we can merge it into master.

![Git Branch
Diagram](https://wac-cdn.atlassian.com/dam/jcr:389059a7-214c-46a3-bc52-7781b4730301/hero.svg)

> From
> [Atlassian - Git Branching Tutorial](https://www.atlassian.com/git/tutorials/using-branches)

### Merging and Merge Conflicts

As we've learned, we can create branches to create new versions of our project
from within a repository. We can do this to build out a new feature in isolation
and then merge those features into the rest of our codebase when we're ready.

When we go to merge our work, our coworkers or teammates have likely continued
working off of master and may have already merged their work. So when we go to
merge our work we may find that we've changed a file or files that one of our
teammates also changed. When this happens, we get merge conflicts.

Merge conflicts look like this:

```
<<<<<<< HEAD
var x = 1,
    y = 2;
=======
var x;
>>>>>>> other_branch
```

The first section is the version that exists on the current branch; the second
section is the version that exists on the branch you're trying to merge in.
Figure out which version of the code makes the most sense moving forward, delete
the version that doesn't and all the extra stuff that Git adds (`<<<<<<<`,
`=======`, etc.) and run `git commit` to finalize the merge.

For example, if we decided we only needed `var x`, delete the other "stuff":

```diff
- <<<<<<< HEAD
- var x = 1,
-     y = 2;
- =======
+ var x;
- >>>>>>> other_branch
```

Now, we have only the code we need and can commit the changes we made to resolve
the merge conflict.

### Common Git Commands

Here are most of the commands you're going to use today and during your project.

#### Branches

| Command                        | Description                                                                                  |
| ------------------------------ | -------------------------------------------------------------------------------------------- |
| `git branch`                   | List branches on your local repo                                                             |
| `git branch -a`                | List branches on on your local repo and all remotes                                          |
| `git branch <branchName>`      | Create a new branch but don't switch to it                                                   |
| `git checkout <branchName>`    | Switch to a branch that already exists                                                       |
| `git checkout -b <branchName>` | Create a new branch and switch to it                                                         |
| `git branch -D <branchName>`   | Hard delete a branch (works if commits haven't be pushed to remote, `-d` is the soft delete) |
| `git merge <branchName>`       | Merges the specified branch (`<branchName>`) into the current branch                         |

#### Remotes

| Command                                       | Description                                              |
| --------------------------------------------- | -------------------------------------------------------- |
| `git remote`                                  | List remote names                                        |
| `git remote -v`                               | List remote names and the full URL (whether HTTP or SHA) |
| `git remote add <remoteName> <remoteURL>`     | Add a new remote with the given name and url             |
| `git remote set-url <remoteName> <remoteURL>` | Update the URL of the named remote repo                  |
| `git remote remove <remoteName>`              | Remove a remote from the local repo                      |

#### Working with Remotes

| Command                                             | Description                                                            |
| --------------------------------------------------- | ---------------------------------------------------------------------- |
| `git fetch`                                         | Retrieve changes from the remote (usually `master`)                    |
| `git fetch --all`                                   | Retrieve all changes from all remotes and all remote branches          |
| `git pull <remoteName> <branchName>`                | Retrieve changes from the remote and merges them into the local branch |
| `git push --set-upstream <remoteName> <branchName>` | Upload changes from to the remote (`-u` is shorthand for the option)   |
| `git push`                                          | When upstream is set, can use command without options                  |

### You do: Merging and Merge Conflicts

With a pair, follow along to this exercise on
[Exercise: creating and resolving merge conflicts](https://git.generalassemb.ly/java-interapt-3-13-2023/merge-conflicts).



## Git Workflows

Git is an extremely flexible tool and you can use it in many different ways.
You'll already be familiar with some of the following workflows, though maybe
not by name. You'll also find many variations on the below workflows.

### Centralized Workflow

The Centralized workflow is good for people just starting out with git: there is
low overhead and it's easy to get started. The remote repo has only a single
branch, `master`. All collaborators have separate clones of the repository. They
can each work independently on separate things. However, before anyone runs a
`git push`, they need to run `git pull` to make sure that their **local** copy
of the `master` branch isn't out of sync with the **remote** `master` branch.

![Centralized Workflow
Diagram](https://wac-cdn.atlassian.com/dam/jcr:0869c664-5bc1-4bf2-bef0-12f3814b3187/01.svg)

> From
> [Atlassian - Centralized Workflow](https://www.atlassian.com/git/tutorials/comparing-workflows#centralized-workflow)

**(+)** Very simple

**(-)** Collaboration is clunky and error-prone

_Use this model when working alone on a project or with only one other
collaborator and the project is small/insignificant._

### Feature Branch Workflow

The Feature Branch model is very similar to the Centralized workflow but with
one big difference: branches! The remote repo has a `master` branch and a branch
for each feature that is under active development. All collaborators have a
clone of the repository with the `master` branch and any feature branches that
they are currently working on. When you finish working on your feature you will:

1. Push up your changes to the remote feature branch
2. Submit a pull request from the feature branch asking for it to be merged into
   the remote `master` branch.

![Feature Branch
Workflow](https://wac-cdn.atlassian.com/dam/jcr:80d671b1-8a4b-4378-914c-e25fe3d2dcce/07.svg?cdnVersion=dj)

> From
> [Atlassian - Feature Branch Workflow](https://www.atlassian.com/git/tutorials/comparing-workflows/feature-branch-workflow)

**(+)** Better isolation than the Centralized model, but sharing is still easy.
Very flexible.

**(-)** Sometimes it's too flexible - it doesn't distinguish in any meaningful
way between different branches, and that lack of structure can be problematic
for larger projects.

_Use this model when working on a small to medium sized project with others that
doesn't require strict collaboration_


### Gitflow

The Gitflow workflow builds on the Feature Branch model by assigning very
specific roles to different branches and providing strict guidelines for how
these branches interact. Under Gitflow, a repository will have a `master`
branch, a branch for each feature under development, branches for each
environment (i.e. staging and production) and each release.

![Feature Branch
Workflow](<https://wac-cdn.atlassian.com/dam/jcr:61ccc620-5249-4338-be66-94d563f2843c/05%20(2).svg>)

> From
> [Atlassian - Gitflow Workflow](https://www.atlassian.com/git/tutorials/comparing-workflows/gitflow-workflow)

**(+)** Features are most isolated from each other and sharing work is still
easy. It's easy to work on multiple, separate features simultaneously and merge
them in a single release. It's designed to fit in nicely with Agile.

**(-)** The structure is very rigid - that can be complex and difficult to
maintain and require more on-boarding for new team members.

_Use this model when working on medium to large sized project with others,
especially if working on a team of 5+ developers._

**The key steps in GitFlow are:**

- Working off of the `develop` or `dev` branch
- Creating a feature branch
  - committing to the feature branch
- Merging changes in to the `develop` branch
- "Cutting a release": creating a release branch off of `master`, merging
  `develop` into the release branch and then merging the release branch into
  `master`
- Doing a "Hot Fix": Creating a `hot-fix` branch off of `master`, fixing a bug,
  then merging the fix in to `master`

### Fork and PR Workflow (5 min / 1:50)

The Fork and PR approach is the model we're all most familiar with: it's how we
submit our homework, labs and projects. Under this model, everyone maintains
their own remote repository (their fork). Changes are submitted via pull
request.

How It Works: One collaborator plays the role of 'Integration Manager'. They are
responsible for managing the official repository and either accepting or
rejecting pull requests as they come in.

**(+)** One student integrates all changes, so there's consistency. It ensures
the integrity of the original remote repository (the master `master`, if you
will).

**(-)** Could get overwhelming for large projects: one person will spend most of
their time reviewing and merging pull requests.

_Use this model when working on an open source project or when working with or
as an outside contractor or freelancer._

> From
> [Atlassian - Forking Workflow](https://www.atlassian.com/git/tutorials/comparing-workflows/forking-workflow)


### Cheat Sheets

- [Github Official Cheat Sheet](https://github.github.com/training-kit/downloads/github-git-cheat-sheet/)

- [Atlassian Cheat Sheet](https://www.atlassian.com/git/tutorials/atlassian-git-cheatsheet)

- [Dash App](https://kapeli.com/dash) – "Dash gives your Mac instant offline
  access to 200+ API documentation sets."

- [Oh-my-zsh git aliases](https://github.com/robbyrussell/oh-my-zsh/wiki/Cheatsheet#git)

### Tools & Resources

- [hub](https://github.com/github/hub) – "is a command line tool that wraps git
  in order to extend it with extra features and commands that make working with
  GitHub easier." Hub is built and maintained by GitHub

- [git flow](https://github.com/nvie/gitflow) – A collection of Git extensions
  to provide high-level repository operations for the Gitflow Workflow

### Further Reading

- [How to Write a Git Commit Message](https://chris.beams.io/posts/git-commit/)
- [How to use Git efficiently](https://medium.freecodecamp.org/how-to-use-git-efficiently-54320a236369)
- [GitHub docs on resolving a merge conflict](https://help.github.com/articles/resolving-a-merge-conflict-using-the-command-line/)
- [Git Workflows Overview](https://www.atlassian.com/git/tutorials/comparing-workflows)
- [Git Teams](http://gitforteams.com/)
- [Git Alias](https://githowto.com/aliases)

## Bonus

## Tips & Tricks

**Using `git fetch` and `git diff`**

What if you are a little out of sync with your teammates and are worried that a
`git pull` will result in hours of working through merge conflicts?

Use `git fetch` and `git diff` to see the changes instead!

```bash
git fetch <remote> <branch>
git diff <remote>/<branch>
```

One of the common undos takes place when you commit too early and possibly
forget to add some files, or you mess up your commit message. If you want to try
that commit again, you can run `git commit --amend` with the `--amend` option...

```sh
git commit --amend
```

### Deleting Branches: Locally VS Remote

Locally...

`git branch -d <branch>` Deletes local branch

`git branch -D <branch>` Forces delete for un-merged branches

Remote...

`git push origin :<branch>` Deletes Remote Branch

### Git Blame

`git blame <file_name>`

Git blame allows you to see who has made changes to a file, or when the file was
last changed by someone. This can be used to find out what feature(s) were
added.

To find out who changed a file, you can run git blame against a single file, and
you get a breakdown of the file, line-by-line, with the change that last
affected that line.

Additionally, this feature is available to view on Github!

To use git blame on GitHub:

- Navigate to a repository, and click on a file that you're interested in.
- Click on the Blame button in the upper-right tab list.
- Browse the list of changes in a file.

### Git Aliases

Aliases allow us to configure shortcuts and can help speed up our workflow!

We can configure them in our `.gitconfig` or `.bash_profile`

Example Aliases...

```sh
[alias]
  g = git
  current = rev-parse --abbrev-ref HEAD
  gl = git log --all --oneline --graph --decorate
```

If you are adding an Alias to your bash profile you might have to reload to see
your updates by running `$ source ~/.bash_profile`

Oh-my-zsh also includes great
[git aliases](https://github.com/robbyrussell/oh-my-zsh/wiki/Cheatsheet#git) out
of the box.

## Rebasing

Rebasing allows us to rearrange and effectively rewrite our commit history.
Rather than combining the most recent commits from two different branches via a
single commit, it combines the two branches themselves, rearranging their
commits while **_re-writing_** the repo's commit history. For that reason, it
can be dangerous.

### Git Merge

![Git
Pull/Merge](https://git.generalassemb.ly/storage/user/6376/files/aee6a68e-81b6-11e7-9fb7-31c12053681f)

### Git Rebase

![Git
Rebase](https://git.generalassemb.ly/storage/user/6376/files/c6f3d54e-81b6-11e7-9f1b-c5a81d1e0207)

[Document Dive](https://www.atlassian.com/git/tutorials/merging-vs-rebasing/)

### Rebase vs Merge

![Rebase vs
Merge](https://raw.githubusercontent.com/gitforteams/diagrams/master/flowcharts/rebase-or-merge.png)

<details>

<summary>Example Scenario</summary>

Here's what a rebase looks like. Suppose we have two branches, a master and a
feature branch.

One day, someone makes a commit onto the master branch. We want to include those
changes into our feature branch, so that our code doesn't conflict with theirs.
From our feature branch, if we run the command
`git pull --rebase <remote> <branch>`, we can tell git to rewrite the history of
our feature branch as if the new commit on master had always been there.

Rebase is extremely useful for cleaning up your commit history, but it also
carries risk; when you rebase, you are in fact discarding your old commits and
replacing them with new (though admittedly, similar) commits, and this can
seriously screw up a collaborator if you're working in a shared repo. **The
golden rule for git rebase is "Only rebase before sharing your code, never
after."**

Like git merge, git rebase also sometimes runs into merge conflicts that need to
be resolved. The procedure for doing this is almost the same; once you fix the
conflicts, run `git rebase --continue` to complete the rebase.

</details>
