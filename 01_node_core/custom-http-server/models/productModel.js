let products = require("../data/products");
const { writeToFile } = require("../utils/utils");

// Find all products
const findAll = () => {
  return new Promise((resolve, reject) => {
    resolve(products);
  });
};

// Find single product by id
const findById = (id) => {
  return new Promise((resolve, reject) => {
    const product = products.find((prod) => prod.id === id);

    if (product) {
      resolve(product);
    } else {
      reject(new Error(`Product with id ${id} not found`));
    }
  });
};

// Add new product
const create = (product) => {
  return new Promise((resolve, reject) => {
    const id = products.length + 1;
    const newProduct = { id, ...product };
    products.push(newProduct);

    // add to file
    writeToFile("./data/products.json", products);
    resolve(newProduct);
  });
};

// Update a product
const update = (id, product) => {
  return new Promise((resolve, reject) => {
    const index = products.findIndex((p) => p.id === id);
    products[index] = { id, ...product };

    writeToFile("./data/products.json", products);
    resolve(products[index]);
  });
};

// Delete a product
const remove = (id) => {
  return new Promise((resolve, reject) => {
    products = products.filter((p) => p.id !== id);

    writeToFile("./data/products.json", products);
    resolve();
  });
};

module.exports = { findAll, findById, create, update, remove };
