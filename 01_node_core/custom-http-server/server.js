const http = require("http");
const {
  getProducts,
  getProduct,
  createProduct,
  updateProduct,
  deleteProduct,
} = require("./controllers/productController");
const { handleError } = require("./utils/errorHandler");

const server = http.createServer(async (req, res) => {
  try {
    //   res.statusCode = 200;
    //   res.setHeader("Content-Type", "text/html");
    //   res.write("<h1>Page from server</h1>");
    //   res.end();

    if (req.url === "/api/products" && req.method === "GET") {
      await getProducts(req, res);
    } else if (
      req.url.match(/\/api\/products\/([0-9]+)/) &&
      req.method === "GET"
    ) {
      const id = Number(req.url.split("/")[3]);
      await getProduct(req, res, id);
    } else if (req.url === "/api/products" && req.method === "POST") {
      await createProduct(req, res);
    } else if (
      req.url.match(/\/api\/products\/([0-9]+)/) &&
      req.method === "PUT"
    ) {
      const id = Number(req.url.split("/")[3]);
      await updateProduct(req, res, id);
    } else if (
      req.url.match(/\/api\/products\/([0-9]+)/) &&
      req.method === "DELETE"
    ) {
      const id = Number(req.url.split("/")[3]);
      await deleteProduct(req, res, id);
    } else {
      const error = new Error("Route not found");
      error.statusCode = 404;
      throw error;
    }
  } catch (error) {
    handleError(res, error);
  }
});

const PORT = process.env.PORT || 5000;

server.listen(PORT, () => {
  console.log(`Server running on ${PORT}...`);
});
