# Backend Engineering Roadmap: Troubleshooting & Insights

> **Purpose**: A living document to record technical hurdles, "Aha!" moments, and
> solutions encountered while building the backend roadmap.
>
> **Stack**: PostgreSQL, Node.js, Java/Spring Boot, Docker.

---

## 1. Node.js

### 1.0 Memory and Persistence in Vanilla Node.js

- **The Problem**: If I update a variable in Node, it disappears when the server restarts. If I update a JSON file, it stays (Persistence).
- **The Catch**: `fs.readFileSync` is blocking. If 1,000 people hit my server at once, the 1,000th person has to wait for the first 999 file reads to finish.
- **The Lesson**: This is why we use Databases like Postgres or an external caching system, they handle multiple "reads" and "writes" at the same time much better than a single JSON file. In-memory storage is only suitable for temporary data that doesn't need to survive server restarts.

### 1.1 Error Handling in Vanilla Node.js

- **The Problem**: No `app.use((err, req, res, next) => ...)` in vanilla Node.js.
- **The Catch**: Middlewares are just functions. In vanilla, I am the "Error Manager." I have to manually write the `res.writeHead(500)` or the server will just hang and the client will wait forever.
- **The Lesson**: Always wrap `async` logic in `try/catch` and remember to close the response (`res.end()`) even when an error occurs.
