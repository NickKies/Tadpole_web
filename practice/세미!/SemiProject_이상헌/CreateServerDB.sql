CREATE DATABASE PC;

CREATE TABLE desktop (
	num INT primary key,
	name VARCHAR(50) not null,
	price VARCHAR(20) not null,
	score VARCHAR(5),
	imgURL VARCHAR(200) not null,
	pageURL VARCHAR(1000) not null
);

CREATE TABLE laptop (
	num INT primary key,
	name VARCHAR(50) not null,
	price VARCHAR(20) not null,
	score VARCHAR(5),
	imgURL VARCHAR(200) not null,
	pageURL VARCHAR(1000) not null
);

CREATE TABLE tablet (
	num INT primary key,
	name VARCHAR(50) not null,
	price VARCHAR(20) not null,
	score VARCHAR(5),
	imgURL VARCHAR(200) not null,
	pageURL VARCHAR(1000) not null
);
