CREATE DATABASE movie

CREATE TABLE showing(
movie_no INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
title VARCHAR(50) NOT NULL,
filmrating VARCHAR(30) NOT NULL,
grade VARCHAR(50),
releaseddata VARCHAR(30),
rp VARCHAR(20),
imgurl VARCHAR(500) NOT NULL
);
CREATE TABLE scheduled(
movie_no INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
title VARCHAR(50) NOT NULL,
filmrating VARCHAR(30) NOT NULL,
grade VARCHAR(50),
releaseddata VARCHAR(30),
rp VARCHAR(20),
imgurl VARCHAR(500) NOT NULL
);


SHOW TABLES
SELECT * FROM showing
SELECT * FROM scheduled

 
