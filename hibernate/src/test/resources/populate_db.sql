INSERT INTO skills (name)
VALUES ('java'),
       ('cpp'),
       ('c#'),
       ('php'),
       ('sql');

INSERT INTO accounts (data, status)
VALUES ('first account', 'ACTIVE'),
       ('second account', 'ACTIVE');

INSERT INTO developers (name, account)
VALUES ('Alex', 1),
       ('Ivan', 2);

INSERT INTO developer_skills
VALUES (1, 1),
       (1, 5),
       (2, 4),
       (2, 5);

INSERT INTO users (user_id, user_name, password, enabled)
VALUES (1, 'admin', '{noop}nimda', true),
       (2, 'user', '{noop}resu', true),
       (3, 'Alex', '{noop}xelA', true);

INSERT INTO user_roles (user_role_id, user_id, authority)
VALUES (1, 1, 'ROLE_ADMIN'),
       (2, 2, 'ROLE_USER'),
       (3, 3, 'ROLE_USER');