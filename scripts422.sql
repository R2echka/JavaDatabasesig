CREATE TABLE car (
id serial PRIMARY KEY,
brand varchar(100),
model varchar(100),
cost numeric
);

CREATE TABLE people (
id serial PRIMARY KEY,
name varchar(50),
age int,
has_license boolean,
car_id int,
FOREIGN KEY (car_id) REFERENCES car (id)
);
