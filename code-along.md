# A0 Code-Along: Hello, BRIDGES

Goal: get a picture you made showing up on the BRIDGES server, under your name.

The Java is already written. What is left for you is IntelliJ, an account, a key,
a `.env` file, and some pixel art. Budget thirty minutes, most of it spent
watching IntelliJ download things.

Here is the one you are aiming at, already on the server:
https://bridges-cs.herokuapp.com/assignments/0/prg

If something breaks, check [When it breaks](#when-it-breaks) at the bottom before
posting in the class channel. Almost every A0 failure is already on that list.

---

## Step 0: Open the project in IntelliJ

We run everything in this course through IntelliJ IDEA. IntelliJ ships with its
own copy of Maven and will download a JDK for you, so there is nothing to install
from a terminal. If you type `mvn -v` in a terminal and get "command not found",
that is expected and it is not a problem. Do not go install Maven separately.

1. Install **IntelliJ IDEA** if you do not have it. Community Edition is free;
   Ultimate is free with your UNC email at https://www.jetbrains.com/student.
2. **File > Open**, select the folder you cloned (the one holding `pom.xml`),
   and open it. Trust the project when IntelliJ asks.
3. IntelliJ reads `pom.xml` and imports this as a Maven project on its own. You
   will know it worked when a **Maven** tool window appears on the right edge and
   `src/main/java` shows up as a sources root, in blue.
4. Check the JDK: **File > Project Structure > Project**. The SDK needs to be 17
   or newer. If the box is empty, open the dropdown, pick **Download JDK**, and
   take version 21 from any vendor.
5. Wait for the progress bar in the bottom right to finish. The first import
   downloads BRIDGES from JitPack, which takes a minute or two.

If `src/main/java` stays a plain gray folder and every `import` is red, you
almost certainly opened the wrong folder. `pom.xml` has to sit at the top level
of whatever you opened. Close the project and open the cloned folder itself.

---

## Step 1: Get a BRIDGES account and key

1. Go to https://bridges-cs.herokuapp.com/signup and make an account. Use your
   UNC email. Pick a username you are willing to see on a shared screen.
2. Log in.
3. Click your name in the upper right to open your profile.
4. You are looking at two things: your **username** and your **API key**. The key
   is a long string of characters. Copy it.

Do not close that tab yet. If you lose the key you can come back and regenerate
it, which invalidates the old one.

---

## Step 2: Make your `.env`

In the Project tool window, right-click `.env.example`, choose **Copy**, then
paste it into the same folder and name the copy `.env`. It belongs next to
`pom.xml`, at the top level of the project.

Open `.env` and replace the two placeholders with your real values. No quotes, no
spaces around the `=`.

```
BRIDGES_USERNAME=dbeasley
BRIDGES_APIKEY=1234567890abcdef
BRIDGES_ASSIGNMENT=0
```

Leave `BRIDGES_ASSIGNMENT` at `0`. Later assignments will tell you what to use.

`.env` is listed in `.gitignore`, so git will leave it alone. If you are keeping
this in a repo, confirm that yourself: your `.env` should never appear in
IntelliJ's Commit tool window.

---

## Step 3: Run it

Open `src/main/java/comp210/a0/HelloBridges.java`, find `main`, and click the
green arrow in the gutter to its left. Choose **Run 'HelloBridges.main()'**.

The output shows up in the Run window at the bottom. When it works, the last
lines look like this:

```
Loaded credentials: username=dbeasley, apiKey=1234************, assignment=0

Check Your Visualization at the following link:

https://bridges-cs.herokuapp.com/assignments/0/dbeasley
```

Open the URL. There is your grid: Carolina blue, a plum X, a kiwi-green border.
Same as https://bridges-cs.herokuapp.com/assignments/0/prg, except it is yours.

Everything in the reading just happened on your laptop. You called methods on a
local object, and `visualize()` serialized your grid into JSON, opened an HTTP
connection to a server in another state, attached your key to the request, and
handed back a URL where the result is now sitting.

---

## Step 4: Make it yours

In `HelloBridges.java`, find `makeGrid()`. Replace the starter pattern with
something of your own.

Two rules: it fits in 16 by 16, and it uses at least three colors.

The whole API you need is one method:

```java
grid.set(row, col, color);
```

Row 0 is the top. Column 0 is the left. Colors come from RGB values, each 0 to
255:

```java
Color hotPink = new Color(255, 105, 180);
grid.set(3, 7, hotPink);
```

Ideas, in rising order of effort: your initials in block letters, a heart, a
flag, a space invader, a 16x16 Doobi. Write loops if you see a pattern worth
looping over, or set forty pixels by hand.

For a bigger canvas, change `SIZE`. It is one constant and the rest of the code
reads from it. Past about 64 the squares get small enough that it stops looking
like pixel art.

Run it again when you are done, with the same green arrow. Each run overwrites
the same URL.

---

## Step 5: Screenshot it

Take a screenshot of the browser window showing your grid, with the address bar
in the shot so your username shows. Upload that one image to the A0 assignment
on Gradescope. That is the whole submission.

Screenshot the browser, not IntelliJ. The Run window and your `.env` both have
your key in them, and a key that ends up in a screenshot has to be regenerated.

---

## When it breaks

**Every `import` is red, or `src/main/java` is a plain gray folder**
IntelliJ did not import this as a Maven project, which happens when the folder
you opened does not have `pom.xml` at its top level. Close the project and open
the cloned folder itself. If the folder is right, open the Maven tool window and
click the reload icon in its top left.

**`Could not find a .env file`**
The file is not where the program is looking. Check that it is named exactly
`.env`, with the dot, sitting next to `pom.xml` rather than inside `src/`. On
Windows, watch for `.env.txt`: File Explorer hides the real extension unless you
turn on "File name extensions" in the View tab. If the file is in the right place
and you still get this, open **Run > Edit Configurations** and make sure the
working directory is the cloned folder, the one holding `pom.xml`.

**`BRIDGES_APIKEY is missing or still set to the placeholder`**
You copied `.env.example` and did not edit it, or you left the value blank.

**`your api key or username is invalid`**
The credentials loaded but the server rejected them. In order: check the masked
key that the program printed against the first four characters on your profile
page; check that the username matches the account the key came from; check for a
trailing space or a stray quote in `.env`; make sure you copied the API key and
not your user ID.

**Downloads fail, or you see `Could not resolve dependencies`**
BRIDGES comes from JitPack, which occasionally builds slowly on a first request.
Wait a minute, then hit the reload icon in the Maven tool window to try again.

**`Too many requests` or a `429`**
You called `visualize()` in a loop. Build the whole grid first, call
`visualize()` once at the end, and wait a minute before retrying.

**The page loads but the grid is blank or tiny**
Make sure `bridges.setDataStructure(grid)` still runs before `visualize()`, and
that you did not delete the `new ColorGrid(...)` line.

**Something else entirely**
Post in the class channel with the full error text and whatever you already tried
from this list. Leave out your `.env` and your key. That includes screenshots: a
Run window usually has more scrollback in it than you meant to share.
