# A0 Reading: What Is an API?

Optional background for the code-along. Ten minutes or so.

## Start
An API is the published list of things one piece of software will do for
another. That definition is vague enough to cover two situations that feel
nothing alike when you are the one typing.

A **library API** runs inside your program. `list.add("Doobi")` executes in your
process, in your memory, before the next line runs. Java's standard library is an
API in this sense, and so is the BRIDGES library that Maven is about to
download into your project.

A **web API** runs on a machine you do not own. You send a message, wait, and get
one back. The waiting is what changes how you write code. A local method call
costs nanoseconds; a network round trip costs a few hundred milliseconds, which
is around a million times longer. It can also fail for reasons that have
nothing to do with your code, and it usually wants proof of who you are before it
does anything at all.

BRIDGES is both at once, which is what makes it a reasonable first example. You
call ordinary Java methods on a local object, and then one of those methods opens
a socket.

## The shape of a web API call

Most web APIs you will meet follow a loose convention called REST. Four parts of
a request matter right now.

**The method** says what kind of operation this is. `GET` reads, `POST` creates,
`PUT` replaces, `DELETE` removes. Reading is safe to repeat. The other three are
not, which is why your browser interrupts you when you refresh a page after
submitting a form.

**The URL** names the thing being operated on. REST URLs are built out of nouns,
and they nest:

```
GET  /courses/210/students          -> everyone in COMP210
GET  /courses/210/students/8817     -> one student
POST /courses/210/students          -> enroll a new one
```

**Headers** carry everything about the request that is not the request itself:
who is calling, what format they want back, how the body is encoded.

**The body** appears on `POST` and `PUT` and holds the data being sent. In
practice, JSON.

## JSON

JSON is how programs write structured data down as text. There are four scalar
types (string, number, boolean, and `null`) and two containers: objects, which
map string keys to values, and arrays, which hold an ordered run of them.

```json
{
  "onyen": "dbeasley",
  "name": "Doobi Beasley",
  "year": 2,
  "enrolled": true,
  "advisor": null,
  "courses": ["COMP210", "MATH233"],
  "office": {
    "building": "Sitterson",
    "room": "150"
  }
}
```

Read the shape and not only the values. `year` is a number, so you can sort on
it. `courses` is an array, so a student can have more than one. `office` is an
object, so the building and the room stay attached instead of drifting apart as
`officeBuilding` and `officeRoom` and eventually disagreeing with each other.
Somebody sat down and decided all of that.

## Status codes

Before the server sends you any data it sends you a three-digit verdict.

| Range | Meaning | Ones you will actually see |
|---|---|---|
| 2xx | It worked | `200 OK`, `201 Created` |
| 3xx | Look somewhere else | `301 Moved Permanently` |
| 4xx | You messed up | `400 Bad Request`, `401 Unauthorized`, `403 Forbidden`, `404 Not Found`, `429 Too Many Requests` |
| 5xx | The server messed up | `500 Internal Server Error`, `503 Service Unavailable` |

The 4xx-versus-5xx split is the one to internalize, because it tells you where to
start looking. A 4xx means the problem is in what you sent, so stop rereading the
documentation and go inspect your own request. A 5xx means the server fell over
on its own and there may be nothing on your end to fix.

`401` and `403` get swapped constantly. `401 Unauthorized` means the server does
not know who you are and would like some credentials. `403 Forbidden` means it
knows precisely who you are and the answer is still no.

## API keys

An API key is a long random string that tells a server which account is calling.
BRIDGES issues you one so it can file your visualizations under your name.

Treat it as a password, because that is what it is. Anybody holding your key can
act as you.

So it cannot live in your source code. Code gets committed, pushed, forked,
screenshotted for a question, and pasted into chat windows. A key sitting in a
string literal rides along to all of those places, and git keeps it in history
long after you delete the line. There are bots that watch the public GitHub
commit firehose looking for exactly this.

The usual arrangement is a file called `.env` holding `NAME=value` lines, with
`.env` listed in `.gitignore` so git leaves it alone. Your program reads the file
when it starts.

```
BRIDGES_USERNAME=dbeasley
BRIDGES_APIKEY=1234567890ab
```

You will set this up in the code-along, and every BRIDGES assignment this
semester reuses it.

## Rate limits

Servers cap how often you may call them. One student with a `while (true)` loop
can outpace a small university's normal traffic, so the cap is not paranoia. Go
over it and you get `429 Too Many Requests`.

BRIDGES rate-limits. If you trip it, wait a minute, then go find the
`visualize()` call that ended up inside a loop. You want one call, at the end,
after the structure is finished.

## What BRIDGES is

A teaching library out of UNC Charlotte, doing two jobs for this course.

It hands you real data. USGS earthquake feeds, Shakespeare's complete works, IMDB
credits, OpenStreetMap road networks. You will build a hash map over something
other than five hardcoded strings.

And it draws your structures in a browser. Build a linked list, call
`visualize()`, and you get a picture of the nodes and links you actually created
rather than the ones you meant to create. A list with a bad pointer looks wrong
in a way you can point at, which is a much shorter debugging session than reading
`toString()` output and trying to hold the whole thing in your head.

## Glossary

An **API** is the set of operations one piece of software offers another.
**REST** is a convention for web APIs built on HTTP methods and noun-shaped URLs;
a single method-and-URL pattern like `GET /students/{id}` is an **endpoint**.
**JSON** is the text format nearly all of them speak. Every response opens with a
**status code**, the three-digit verdict on your request. Your **API key** is the
secret string that identifies you to the server, and the **rate limit** governs
how often you are allowed to use it.
