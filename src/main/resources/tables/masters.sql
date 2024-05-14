CREATE TABLE masters(
    id serial,
    name varchar,
    pin int
);

INSERT INTO masters (id, name, pin)  VALUES (1, 'Татьяна', 1111);
INSERT INTO masters (id, name, pin)  VALUES (2, 'Данил', 1122);


SELECT name FROM Masters WHERE pin = 1111