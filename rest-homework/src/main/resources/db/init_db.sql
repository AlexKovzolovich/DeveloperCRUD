CREATE SCHEMA user;

USE user;

CREATE TABLE user (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(45) NOT NULL,
    last_name VARCHAR(45) NOT NULL,
    email VARCHAR(45) NOT NULL
);

INSERT INTO user (first_name, last_name, email) VALUES ('Alex', 'Alex', 'alex@mail.com'),
                                                       ('Vania', 'Vania', 'vania@mail.com'),
                                                       ('Petia', 'Petia', 'petia@mail.com');