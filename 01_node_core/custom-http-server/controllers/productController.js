const Product = require("../models/productModel");
const { getReqBody } = require("../utils/utils");

/**
 * @desc Get all products
 * @route GET /api/products
 * @param {*} req
 * @param {*} res
 */
async function getProducts(req, res) {
  const products = await Product.findAll();
  res.writeHead(200, { "Content-Type": "application/json" });
  res.end(JSON.stringify(products));
}

/**
 * @desc Get single product
 * @route GET /api/products/:id
 * @param {*} req
 * @param {*} res
 */
async function getProduct(req, res, id) {
  const product = await Product.findById(id);

  res.writeHead(200, { "Content-Type": "application/json" });
  res.end(JSON.stringify(product));
}

/**
 * @desc Create new product
 * @route POST /api/products
 * @param {*} req
 * @param {*} res
 */
async function createProduct(req, res) {
  const body = await getReqBody(req);

  const { name, description, price, category, stock } = JSON.parse(body);

  const product = { name, description, price, category, stock };

  const newProduct = await Product.create(product);
  res.writeHead(201, { "Content-Type": "application/json" });
  res.end(JSON.stringify(newProduct));
}

/**
 * @desc Update a product
 * @route PUT /api/products/:id
 * @param {*} req
 * @param {*} res
 * @param {*} id
 */
async function updateProduct(req, res, id) {
  const product = await Product.findById(id);

  const body = await getReqBody(req);

  const { name, description, price, category, stock } = JSON.parse(body);

  const productData = {
    name: name || product.name,
    description: description || product.description,
    price: price || product.price,
    category: category || product.category,
    stock: stock || product.stock,
  };

  const updatedProduct = await Product.update(id, productData);

  res.writeHead(200, { "Content-Type": "application/json" });
  res.end(JSON.stringify(updatedProduct));
}

/**
 * @desc Delete a product
 * @route DELETE /api/products/:id
 * @param {*} req
 * @param {*} res
 * @param {*} id
 */
async function deleteProduct(req, res, id) {
  const product = await Product.findById(id);

  if (product) {
    await Product.remove(id);
    res.writeHead(200, { "Content-Type": "application/json" });
    res.end(JSON.stringify({ message: `Product ${id} deleted successfully.` }));
  }
}

module.exports = {
  getProducts,
  getProduct,
  createProduct,
  updateProduct,
  deleteProduct,
};
