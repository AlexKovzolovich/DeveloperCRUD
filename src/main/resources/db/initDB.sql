USE cc7r9bd1vl78tztf;

CREATE TABLE skills
(
    id   INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(45) NOT NULL
);

CREATE TABLE account_status
(
    id     INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    status VARCHAR(10) NOT NULL
);

CREATE TABLE accounts
(
    id     INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    data   VARCHAR(255) NOT NULL,
    status INT REFERENCES account_status
);

CREATE TABLE developers
(
    id      INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name    VARCHAR(45) NOT NULL,
    account INT REFERENCES accounts
);

CREATE TABLE developer_skills
(
    developer_id INT NOT NULL REFERENCES developers,
    skill_id     INT NOT NULL REFERENCES skills
);