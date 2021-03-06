CREATE TABLE books (
	isbn BIGINT NOT NULL PRIMARY KEY,
	title VARCHAR(255) NOT NULL,
	author VARCHAR(255),
	price  DECIMAL(6,2),
	genre INT NOT NULL
);

CREATE TABLE genres (

	id INT PRIMARY KEY AUTO_INCREMENT,
	genre VARCHAR(255) NOT NULL
	
);

CREATE TABLE teams (
	id VARCHAR(3) NOT NULL PRIMARY KEY,
	city VARCHAR(255),
	name VARCHAR(255)
);

CREATE TABLE players (
	id INT PRIMARY KEY AUTO_INCREMENT,
	firstName VARCHAR(255) NOT NULL,
	lastName VARCHAR(255) NOT NULL,
	number INT,
	team VARCHAR(3)
);
