# Node.js

> This is how I learned Node.js by building a simple server that can handle HTTP requests and responses. It's not a full framework like Express, but it gives me a solid understanding of how things work under the hood.

---

## 1. The "Entry Point" (Setting Up a Basic Server)

In Vanilla Node, the entire server lives inside one function. This function is an **Event Listener** that fires every time a request hits our port. To create a basic HTTP server in Node.js, I use the built-in `http` module, on which I called the `createServer` method.

- `req` **(IncomingMessage)**: This is a massive object containing everything the client sent (URL, Method, Headers, Body).
- `res` **(ServerResponse)**: This is a "blank slate." If I don't manually fill it and call res.end(), the client will hang forever.

## 2. Routing: The "If-Else" Engine

Since I'm not using a framework, I have to manually check the `req.url` and `req.method` to determine how to respond.

- **The Logic**: Unlike Express where I can just use app.get() or app.post(), in Vanilla Node I have to use a switch statement or if/else blocks to "triage" the request and check the URL and HTTP method. For example, if `req.url === '/users' && req.method === 'GET'`, then I know the client is asking for a list of users, and I can respond accordingly.

- **The Trap**: If I forget to check the method or URL, I might end up sending the wrong response or even crashing the server. Always make sure to handle all possible routes and methods, and always remember to handle the "Default" case (404) if no route matches.

```javascript
if (req.url === "/api/users" && req.method === "GET") {
  // Logic for getting users
} else {
  res.writeHead(404);
  res.end(JSON.stringify({ message: "Route not found" }));
}
```

## 3. Data Persistence: The JSON "Database"

Since I'm not using a real DB yet, I am using the `fs` (File System) module to mimic one.

- **Reading**: I use `fs.readFileSync` (or the promise version) to turn a string from a file into a JavaScript object using `JSON.parse()`.

- **Writing**: When I "Create" a user, I must `push()` to my array and then use `fs.writeFileSync` to save it back to the disk.

- **The Catch**: JSON files don't have "Auto-incrementing IDs" like Postgres. I have to manually calculate the next ID (e.g., `data.length + 1`).

## 4. Handling the Request Body (The Stream)

This was my biggest "Aha!" moment. Unlike Express (res.body), in Vanilla Node, the request body comes in as a stream of data (**Chunks**).

- **The Buffer**: I have to listen for the `'data'` event to collect chunks and the `'end'` event to finally use the full body.

- **Parsing**: Once the "Stream" ends, I have to `JSON.parse` that gathered string before I can use it.

## 5. Error Handling: The "Manual Try/Catch"

- **The Problem**: If I forget to wrap my logic in a `try/catch`, any error will crash the server and leave the client hanging.
- **The Catch**: In Express, I can just call `next(err)` and it will automatically go to the error-handling middleware. In Vanilla Node, I have to manually send a response with `res.writeHead(500)` and `res.end()` inside the catch block.
- **The Lesson**: Always wrap your logic in `try/catch` and remember to close the response (`res.end()`) even when an error occurs. Otherwise, the client will wait forever and eventually time out.

## 5. My Vanilla Node "Rules of Thumb"

| Feature      | How I handle it in Vanilla                             |
| ------------ | ------------------------------------------------------ |
| Status Codes | `res.writeHead(200, ...)`                              |
| Content Type | Must manually set `'Content-Type': 'application/json'` |
| Sending Data | res.end(JSON.stringify(data))                          |
| Errors       | Wrap everything in try/catch to avoid server crashes   |
