CREATE TABLE masters(
    id serial,
    name varchar,
    pin int
);

INSERT INTO masters (name, pin)  VALUES ('Tatyana', 1111);
INSERT INTO masters (name, pin)  VALUES ('Данил', 1122);


SELECT name FROM Masters WHERE pin = 1111