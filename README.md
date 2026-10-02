# Assignment 0: BRIDGES Setup

**COMP210, Fall 2026**

| | |
|---|---|
| Released | Tuesday, August 18 |
| Due | Tuesday, August 25, 11:59pm |
| Points | 0, but required |
| Collaboration | Talk to anybody. Never share your API key. |

## Why this exists

A few of this semester's assignments run on BRIDGES, a teaching library from UNC
Charlotte that hands you real datasets and draws your data structures in a
browser. Using it requires an account, an API key, a build tool, and a network
call that works.

Every one of those is somewhere to get stuck, and none of them will teach you
anything about data structures. So we are getting stuck now, in week one, over a
grid of colored squares, rather than in October with a linked list due at
midnight.

## What you do

This repo is a Maven project that is already written. You open it in IntelliJ,
make a `.env`, put your BRIDGES key in it, click Run, and then turn the picture
into something of your own.

`code-along.md` has the walkthrough and a troubleshooting list at the bottom.
Read that list before posting a question; it covers nearly everything that goes
wrong here. IntelliJ comes with its own Maven and JDK, so there is nothing to
install from a terminal.

If you want the background on what an API key is and why it lives in `.env`,
`what-is-an-api.md` covers it in about ten minutes. It is optional, and the
rest of the semester's BRIDGES assignments assume you know what it says.

## What to turn in

**One screenshot**, uploaded to the A0 assignment on Gradescope. Take it of the
browser window showing your grid, with the address bar in the shot. The address
should read `https://bridges-cs.herokuapp.com/assignments/0/yourusername`. Mine
is at https://bridges-cs.herokuapp.com/assignments/0/prg if you want to see what
a finished one looks like.

Keep your API key out of the picture. Screenshot the browser, not IntelliJ's Run
window or your `.env`. On every graded BRIDGES assignment after this one, a key
in your submission is an automatic zero (better a zero than getting fired from
an internship or racking up a 50k AWS bill).

## When A0 counts as done

A0 is worth 0 points, and it is required. It counts as done when your
screenshot shows:

- your own BRIDGES username in the address bar, and
- a grid you changed from the starter, with three or more colors.

Getting BRIDGES running is not optional on any timeline. A3 in September
assumes it already works.

## Using AI on this one

Same rules as the rest of the course. Ask an assistant what a `403` means, or why
git still has a file you deleted.

## Files

```
README.md              this file
code-along.md          the step-by-step code-along
what-is-an-api.md      optional background reading
pom.xml                Maven build, pulls BRIDGES and dotenv-java
.env.example           template for your credentials
.gitignore             keeps .env out of git
src/main/java/comp210/a0/
  BridgesConfig.java   reads .env, no edits needed
  HelloBridges.java    the program, one TODO for you
```

Open the folder holding all of this (the one you cloned, with `pom.xml` at its
top level) in IntelliJ. `pom.xml` and `src/` have to stay at the top level.
Move them into a subfolder and IntelliJ stops seeing a Maven project, which
means no dependencies and no sources root.
