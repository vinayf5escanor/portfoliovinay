CREATE TABLE IF NOT EXISTS Item (
id LONG PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(255),
quantity int,
price double
);
insert into Item(name,quantity,price) values('PS5',2,499);
insert into Item(name,quantity,price) values('Nintendo',2,499);
insert into Item(name,quantity,price) values('Xbox',2,499);
insert into Item(name,quantity,price) values('Sega',2,499);