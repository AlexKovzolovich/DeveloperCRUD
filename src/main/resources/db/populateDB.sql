USE cc7r9bd1vl78tztf;

INSERT INTO skills (name)
VALUES ('java'),
       ('cpp'),
       ('c#'),
       ('php'),
       ('sql');

INSERT INTO account_status (status)
VALUES ('active'),
       ('banned'),
       ('deleted');

INSERT INTO accounts (data, status)
VALUES ('first account', 1),
       ('second account', 1);

INSERT INTO developers (name, account)
VALUES ('Alex', 1),
       ('Ivan', 2);

INSERT INTO developer_skills
VALUES (1, 1),
       (1, 5),
       (2, 4),
       (2, 5);