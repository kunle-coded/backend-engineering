const hello = () => {
  console.log("Hello");
};

const greeting = () => {
  console.log("Good day!");

  setTimeout(() => {
    console.log("Timeout");
  }, 0);

  new Promise((resolve, reject) => resolve("Promise")).then((resolve) =>
    console.log(resolve),
  );

  setImmediate(() => {
    console.log("immediate");
  });

  process.nextTick(() => {
    console.log("nextTick");
  });

  hello();
};

greeting();
