DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM museum;
DELETE FROM exhibition;
DELETE FROM choice_museum;

-- user@yandex.ru : user
-- admin@gmail.com: admin

INSERT INTO users (id, email, name, password) VALUES (0, 'user@yandex.ru', 'User', '$2a$04$BJGG/TXtpoBcHBWcbR2JuOZb316ThHVlPyATPPivLsqv/dLC3g.7e'), (1, 'admin@gmail.com', 'Admin', '$2a$04$n/osmjB//rURpDif2AFzMepdJMhQ4fAHUlVJN2PaytD6srcFo4J3y');
INSERT INTO user_roles (role, user_id) VALUES ('ROLE_USER', 0), ('ROLE_ADMIN', 1);
INSERT INTO museum (id, name) VALUES (0,'Geek Museum'), (1,'Illusion Museum'), (2,'Historical Museum');
INSERT INTO exhibition (ID, EXHIBITION_DATE, MUSEUM_ID, NAME, PRICE) VALUES (0, today(), 0, 'History of Playstation', 100), (1, '2021-06-01', 0, 'Optical Illusions Gallery', 75), (2, today(), 1, 'History of Greek Culture in Ukraine', 115);
INSERT INTO CHOICE_MUSEUM (ID, USER_ID, CHOICE_DATE, MUSEUM_ID) VALUES (0, 0, '2021-05-29', 0);