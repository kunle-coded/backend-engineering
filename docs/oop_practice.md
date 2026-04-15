# Java OOP: User Management System

> After building a server in Node.js, I moved to Java to understand Object-Oriented Programming (OOP) and Clean Architecture. This project isn't just about managing users; it's about building a decoupled, type-safe system that can scale.

---

## 1. The "Three-Tier" Architecture (Separation of Concerns)

In my Node server, logic was often mixed together. In Java, I learned to separate the "Who," the "What," and the "How".

- **The UI Layer (`ConsoleUI`)**: Handles `Scanner` input and `System.out` output. It translates human strings into Java objects.

- **The Service Layer (`UserService`)**: The "Brain." It holds the business rules (e.g., "Does this email already exist?" or "Is this password long enough?").

- **The Repository Layer (`UserRepository`)**: The "Library." It only cares about storing and finding data. It doesn't care why we are looking for a user, just where they are.

## 2. Dependency Injection (The "Wiring" Lesson)

One of my biggest shifts from Node.js was moving away from classes creating their own "tools."

- **The Manual Way**: If the `UserService` creates its own `InMemoryRepository`, they are "married." I can't swap the repository for a Postgres one later without breaking the service.

- **The Injection Way**: I pass the Repository into the Service's Constructor. This makes my code modular and testable. I build the "machine" in `Main.java` by connecting the parts together.

## 3. Defensive Programming: Optional & Custom Exceptions

In Node, I often checked for null or undefined. Java's Type System gave me better tools.

- `Optional<User>`: Instead of returning `null` (which causes crashes), my repository returns an `Optional`. This "forces" me to handle the case where a user isn't found using methods like `.ifPresentOrElse()`.

- **Custom Exceptions**: I created `ValidationException`. This allowed me to "throw" a specific error in the Service and "catch" it in the UI to show a friendly message, rather than letting the whole app crash.

## 4. The Power of Inheritance (The "BaseUser")

I learned how to stay **DRY (Don't Repeat Yourself)** using an abstract class.

- `BaseUser`: Holds the "plumbing" that every entity needs (UUID, Timestamps).

- `User`: Extends `BaseUser` and focuses only on identity (Username, Email, Role).

- **The Lesson**: Inheritance allows me to write the "ID generation" logic once and reuse it for every new class I create in the future.

## 5. Java vs. Node.js: My "Mental Shift"

| Feature     | How it felt in Node.js              | How it feels in Java                   |
| ----------- | ----------------------------------- | -------------------------------------- |
| Data Types  | Flexible (Anything can be anything) | Strict (A UUID is not a String)        |
| Persistence | Manually writing JSON files         | Repository Pattern (In-Memory for now) |
| Errors      | `try/catch` around logic            | Custom Exception classes + `Optional`  |
| Objects     | Plain JSON objects                  | Classes with Methods and Encapsulation |
