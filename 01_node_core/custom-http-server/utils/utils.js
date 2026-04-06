const fs = require("fs");

// const writeToFile = (path, content) => {
//   fs.writeFileSync(path, JSON.stringify(content), "utf-8", (err) => {
//     if (err) {
//       console.log(err);
//       throw new Error("Cannot write data to file");
//     }
//   });
// };

const writeToFile = (path, content) => {
  try {
    // Sync methods throw errors directly; they don't use callbacks
    fs.writeFileSync(path, JSON.stringify(content, null, 2), "utf-8");
  } catch (err) {
    console.error(err);
    throw new Error("Disk Write Error: System could not save data.");
  }
};

const getReqBody = (req) => {
  return new Promise((resolve, reject) => {
    try {
      let body = "";

      req.on("data", (chunk) => {
        body += chunk.toString();
      });

      req.on("end", () => {
        resolve(body);
      });
    } catch (error) {
      console.log(error);
    }
  });
};

module.exports = { writeToFile, getReqBody };
