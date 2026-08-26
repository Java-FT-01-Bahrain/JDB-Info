[![General Assembly Logo](https://camo.githubusercontent.com/1a91b05b8f4d44b5bbfb83abac2b0996d8e26c92/687474703a2f2f692e696d6775722e636f6d2f6b6538555354712e706e67)](https://generalassemb.ly/education/web-development-immersive)

# Installfest: Electives

### A Word on Electives

The core Installfest is all you need to be prepared for the course. However,
there are additional tools that instructors and former students found useful
during the course that are just outside the level of complexity of the core
installfest.

We made a separate [Electives](./electives.md) document for these topics. If you
are willing to take the plunge, you'll start with Part One of the Electives and
install `zsh` before continuing with the normal Instructions.

Using a different shell affects some of the later steps, so it is a little
simpler to if you decide from the start to opt into the Electives. However, you
can 100% add the Elective material later on so don't feel like you're missing
out!

## Zsh va oh-my-zsh

```bash
sh -c "$(curl -fsSL https://raw.github.com/robbyrussell/oh-my-zsh/master/tools/install.sh)"
```

## Additional Homebrew Installs

```bash

brew install wget tree htop axel git-flow

brew cask install iterm2 1password alfred dropbox firefox spotify vagrant virtualbox
```

### Cool Zsh plugins

- [z](https://github.com/robbyrussell/oh-my-zsh/tree/master/plugins/z)
- [zsh-nvm](https://github.com/lukechilds/zsh-nvm)
- [theme: spaceship](https://github.com/denysdovhan/spaceship-prompt)

## .zshrc aliases (can adjust for bash)

```bash
alias zshconfig="code ~/.zshrc"
alias srczsh="source ~/.zshrc"
alias ohmyzsh="code ~/.oh-my-zsh"
alias brewup='brew update; brew upgrade; brew cleanup; brew doctor'
frmtmd() { prettier --print-width 80 --prose-wrap always "$*" --write; }
```
