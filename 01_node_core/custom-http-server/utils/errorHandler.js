const handleError = (res, error) => {
  const stausCode = error.stausCode || 500;
  const message = error.message || "Internal Server Error";

  console.error(`Error ${stausCode}: ${message}`);

  res.writeHead(stausCode, { "Content-Type": "application/json" });
  res.end(JSON.stringify({ status: "error", message: message }));
};

module.exports = { handleError };
