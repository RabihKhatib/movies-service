CREATE TABLE  IF NOT EXISTS person(
id UNSIGNED BIGINT PRIMARY KEY,
user_name Varchar(30) NOT NULL,
password Varchar(50),
token Varchar(255)
);

CREATE TABLE  IF NOT EXISTS movie_rating(
id BIGINT PRIMARY KEY,
movie_name Varchar(255) NOT NULL,
rating DECIMAL(2,1)
);

insert ignore into person(id,user_name,password) values(100001,'admin','admin');