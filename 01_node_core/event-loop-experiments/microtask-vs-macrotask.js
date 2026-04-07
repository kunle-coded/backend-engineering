console.log("1. Script start (Sync)");

setImmediate(() => {
  console.log("?. setImmediate (Check)");
});

setTimeout(() => {
  console.log("5. setTimeout (Macrotask)");
}, 0);

Promise.resolve().then(() => {
  console.log("4. Promise (Microtask)");
});

process.nextTick(() => {
  console.log("3. nextTick (Microtask)");
});

console.log("2. Script end (Sync)");
