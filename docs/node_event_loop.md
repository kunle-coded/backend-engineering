# Node Event Loop

The Node.js Event Loop is the orchestration layer that allows Node.js to perform non-blocking I/O operations by offloading tasks to the system kernel whenever possible. The event loop is what allows Node.js to handle multiple operations concurrently without blocking the main thread.

## Macrotask Queues (The Phases)

The event loop moves through these phases in a specific order. Each phase has a FIFO (First-In-First-Out) queue of callbacks to execute.

1. **Timers Phase**: Handles callbacks from expired setTimeout() and setInterval(). This is the first phase of a new loop iteration.

2. **I/O Poll Phase**: The "heart" of the loop. It retrieves new I/O events (file system, network) and executes their callbacks. If the loop is idle, it may block here to wait for new events.

3. **Check Phase**: Specifically for setImmediate() callbacks. This phase runs immediately after the I/O Poll phase.

4. **Close Handlers Phase**: Handles cleanup callbacks, such as socket.on('close', ...).

## Special Microtask Queues

These are not phases of the loop. Instead, they are processed immediately after the Call Stack clears and between every single phase of the event loop.

1. **Next Tick Queue**: Holds callbacks from process.nextTick(). This has the highest priority and is emptied before any other microtask or macrotask.

2. **Promise Microtask Queue**: Holds resolved Promise callbacks (.then, .catch). These are emptied only after the nextTick queue is completely empty.

### Execution Rule

The event loop checks and empties the Microtask Queues (Next Tick, then Promises) immediately after the main script finishes, and again after every single callback executed in the Macrotask phases. If the loop finishes the Close phase and has no more active handles (timers or open requests) to wait for, it exits.
