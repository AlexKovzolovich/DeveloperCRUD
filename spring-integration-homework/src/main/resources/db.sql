CREATE SCHEMA user;

USE user;

CREATE TABLE account (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    balance DECIMAL NOT NULL,
    email VARCHAR(45) NOT NULL
);

CREATE TABLE user (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(45) NOT NULL,
    last_name VARCHAR(45) NOT NULL,
    account_id INT NOT NULL REFERENCES account(id)
);

INSERT INTO account (balance, email) VALUES (20, 'test@mail.test');

INSERT INTO user (first_name, last_name, account_id) VALUES ('test', 'test', 1);