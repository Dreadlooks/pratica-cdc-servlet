create table author(
	id BIGINT NOT NULL AUTO_INCREMENT,
	name VARCHAR(100) UNIQUE,
	description VARCHAR(255),
	primary key(id)
);