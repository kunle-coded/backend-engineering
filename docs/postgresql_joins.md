# PostgresSQL

> This is how I actually use and understand Postgres. No fluff, just the stuff that keeps my database running and my scripts from breaking.

---

## 1. The "Plumbing" (Connection & Commands)

Before I write a single line of SQL, I need to be "in" the database.

- `\conninfo`: My "Where am I?" command. Tells me which database I'm on and what port I'm using.
- `\d table_name`: The "Inspector." Shows me the columns, if they are UNIQUE, and what the DEFAULT values are.
- `\i path/to/file.sql`: The "Script Runner." Executes the SQL commands in the specified file. This is how I set up my database schema and seed data.
- `psql -d db_name -f file.sql`: The "Terminal Shortcut." Runs the script without even opening the SQL shell.

**Pro-Tip on Paths**: If a folder name has a space, wrap it in "quotes" or use a \ before the space, or I'll get a "No such file" error.

## 2. The "Building Blocks" (Data Types & Schemas)

PostgreSQL supports a wide range of data types:

- `SERIAL`: Auto-incrementing integer, often used for primary keys. The "Auto-Counter." I don't type the ID; Postgres handles it for me (1, 2, 3...).

- `VARCHAR(n)`: Variable-length string with a maximum length of n.

- `TEXT`: Variable-length string with no maximum length.

- `TIMESTAMPTZ`: Timestamp with time zone, useful for storing date and time information.

- Array types: `INTEGER[]`, `TEXT[]`, etc., allowing you to store arrays of values in a single column.

- JSON types: `JSON` and `JSONB`, allowing you to store JSON data directly in a column with efficient queryin
  capabilities.

- There are many more data types available, including `BOOLEAN`, `DATE`, `TIME`, `UUID`, and custom types.

## 3. The "Guardrails" (Constraints)

Constraints are just rules I set so I don't accidentally mess up my data.

- `PRIMARY KEY`: The "Unique ID." Every row needs one so I can find it later.

- `FOREIGN KEY`: The "Link." Like how `role_id` in my users table points to a real `id` in the roles table.

- `UNIQUE`: The "No Duplicates" rule. Perfect for `email` and `username`.

- `NOT NULL`: The "Required Field" rule. I use this for anything that should always have a value, like `username` or `created_at`.

- `DEFAULT`: The "Fallback Value." If I forget to provide a value, Postgres will use this instead. Great for `created_at` with `DEFAULT CURRENT_TIMESTAMP` or `price` with `DEFAULT 0.00`.

## 4. The "Matchmakers" (JOINS)

Joins are just different ways to link my tables together. It's all about who the "VIP" is.

- `INNER JOIN`: The "Exclusive Club." Only shows rows that have a match in both tables. No role? No entry.

- `LEFT JOIN (or LEFT OUTER JOIN)`: The "Everyone's Welcome" (on the left). Shows all my users, even if they don't have a role yet. The missing info just shows up as NULL.

- `RIGHT JOIN (or RIGHT OUTER JOIN)`: The "Everyone's Welcome" (on the right). Shows all roles, even if no user has that role. The missing info just shows up as NULL.

- `FULL OUTER JOIN`: The "Open House." Shows all users and all roles. If there's no match, the missing info shows up as NULL.

- `JOIN... ON u.id = r.user_id`: I use aliases (u for users, r for roles) so I don't have to type long table names over and over. It's cleaner.

**Pro-Tip on Joins**: Always check which table is the "main" one in your query. The type of join you choose depends on whether you want to keep all rows from that main table or not.

## 5. Making it "Idempotent" (Safe to re-run)

I hate errors. Using ON CONFLICT makes my scripts "smart" so I can run them 100 times without crashing.

```sql
INSERT INTO users (username, email)
VALUES ('kunle_dev', 'kunle@backend.dev')
ON CONFLICT (username) DO NOTHING;
```

This means if I try to insert a user with the same username again, it just ignores the error and moves on. No duplicates, no crashes.

## 6. My Workflow Pattern

1. Write/Edit my `.sql` file in my code editor.
2. Save the file.
3. Run it from the Mac Terminal using `psql -f`.
4. Verify the results in the shell with a quick `SELECT`.
5. If I need to make changes, I just go back to step 1 and repeat.
