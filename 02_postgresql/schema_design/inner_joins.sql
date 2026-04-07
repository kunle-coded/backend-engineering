\c backend_training

-- INSERT INTO roles (name, description)
-- VALUES ('admin', 'Administrator'), ('editor', 'Editor'), ('customer', 'Customer')
-- ON CONFLICT (name) DO NOTHING;

-- INSERT INTO users (username, email, role_id)
-- VALUES ('kunle_dev', 'kunledev@backend.dev', 1), ('john_doe', 'johndoe@frontend.com', 3)
-- ON CONFLICT (email) DO NOTHING;

-- INSERT INTO products (sku, name, description, price, stock_quantity)
-- VALUES ('LAP-MBP-14', 'MacBook Pro 14', 'The new MacBook Pro 14 by Apple.', 1999.99, 10)
-- ON CONFLICT (sku) DO NOTHING;

-- INSERT INTO users (username, email)
-- VALUES ('lagbaja', 'lagbaja@email.com')
-- ON CONFLICT (email) DO NOTHING;

-- INNER JOIN
SELECT u.username, u.email, r.name AS role_title
FROM users u
INNER JOIN roles r ON u.role_id = r.id;

-- SELECT username, email, ROLES.name AS role_title FROM users
-- INNER JOIN roles ON USERS.ROLE_ID = ROLES.ID;

-- LEFT JOIN/LEFT OUTER JOIN/COMPREHENSIVE JOIN
SELECT r.name AS role_name, r.description, u.username
FROM roles r
LEFT JOIN users u ON r.id = u.role_id;

-- IS NULL JOIN/MISSING LINK/FINDING ORPHANS
SELECT u.username
FROM users u
LEFT JOIN roles r ON u.role_id = r.id
WHERE r.id IS NULL;

-- RIGHT JOIN/RIGHT OUTER JOIN/COMPREHENSIVE JOIN
SELECT r.name AS role_name, r.description, u.username
FROM roles r
RIGHT JOIN users u ON r.id = u.role_id;

-- MISSING CONNECTION
SELECT u.username, p.name AS product_name, r.name AS role_name
FROM users u
JOIN roles r ON u.role_id = r.id
JOIN user_favorites uf ON u.id = uf.user_id
JOIN products p ON uf.product_id = p.id;