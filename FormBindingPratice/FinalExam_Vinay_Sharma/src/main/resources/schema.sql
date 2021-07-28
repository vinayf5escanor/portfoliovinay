DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS Students;
create table users (
 username varchar(50) not null primary key,
 password varchar(120) not null,
 enabled boolean not null
);
create table authorities (
 username varchar(50) not null,
 authority varchar(50) not null,
 foreign key (username) references users (username)
);

CREATE TABLE Students(
id LONG PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(255),
course VARCHAR(255),
mark DOUBLE
);