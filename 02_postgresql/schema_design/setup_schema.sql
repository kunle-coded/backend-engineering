\c backend_training



CREATE TABLE IF NOT EXISTS roles (
    id SERIAL PRIMARY KEY,
    name VARCHAR(20) UNIQUE NOT NULL,
    description TEXT
);

CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    role_id INTEGER REFERENCES roles(id),
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS products (
    id SERIAL PRIMARY KEY,
    sku VARCHAR(100) UNIQUE NOT NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price MONEY NOT NULL DEFAULT 0.00,
    stock_quantity INTEGER DEFAULT 0
);

CREATE TABLE user_favorites (
    user_id INTEGER REFERENCES users(id),
    product_id INTEGER REFERENCES products(id),
    PRIMARY KEY (user_id, product_id)
);