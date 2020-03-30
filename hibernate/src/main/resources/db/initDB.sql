USE crud_app;

CREATE TABLE IF NOT EXISTS skills
(
    id   BINARY(16)  NOT NULL PRIMARY KEY,
    name VARCHAR(45) NOT NULL
);

CREATE TABLE IF NOT EXISTS accounts
(
    id     BINARY(16)   NOT NULL PRIMARY KEY,
    data   VARCHAR(255) NOT NULL,
    status VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS developers
(
    id      BINARY(16)  NOT NULL PRIMARY KEY,
    name    VARCHAR(45) NOT NULL,
    account BINARY(16) REFERENCES accounts
);

CREATE TABLE IF NOT EXISTS developer_skills
(
    developer_id BINARY(16) NOT NULL REFERENCES developers,
    skill_id     BINARY(16) NOT NULL REFERENCES skills
);

CREATE TABLE IF NOT EXISTS users
(
    user_id   INT         NOT NULL PRIMARY KEY,
    user_name VARCHAR(45) NOT NULL,
    password  VARCHAR(45) NOT NULL,
    enabled   BOOLEAN
);

CREATE TABLE IF NOT EXISTS user_roles
(
    user_role_id INT         NOT NULL PRIMARY KEY,
    user_id      INT         NOT NULL REFERENCES users,
    authority    VARCHAR(45) NOT NULL
);

CREATE TABLE IF NOT EXISTS tasks
(
    id           BINARY(16)   NOT NULL PRIMARY KEY,
    description  VARCHAR(255) NOT NULL,
    developer_id BINARY(16) REFERENCES developers
);

