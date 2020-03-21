CREATE TABLE IF NOT EXISTS skills
(
    id   INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(45) NOT NULL
);

CREATE TABLE IF NOT EXISTS account_status
(
    id     INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    status VARCHAR(10) NOT NULL
);

CREATE TABLE IF NOT EXISTS accounts
(
    id     INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    data   VARCHAR(255) NOT NULL,
    status INT REFERENCES account_status
);

CREATE TABLE IF NOT EXISTS developers
(
    id      INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name    VARCHAR(45) NOT NULL,
    account INT REFERENCES accounts
);

CREATE TABLE IF NOT EXISTS developer_skills
(
    developer_id INT NOT NULL REFERENCES developers,
    skill_id     INT NOT NULL REFERENCES skills
);

CREATE TABLE users
(
    user_id   INT         NOT NULL PRIMARY KEY,
    user_name VARCHAR(45) NOT NULL,
    password  VARCHAR(45) NOT NULL,
    enabled   BOOLEAN
);

CREATE TABLE user_roles
(
    user_role_id INT         NOT NULL PRIMARY KEY,
    user_id      INT         NOT NULL REFERENCES users,
    authority    VARCHAR(45) NOT NULL
);

