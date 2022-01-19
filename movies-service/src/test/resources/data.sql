CREATE TABLE  IF NOT EXISTS person(
id BIGINT PRIMARY KEY,
user_name Varchar(30) NOT NULL,
password Varchar(50),
token Varchar(255)
);

CREATE TABLE  IF NOT EXISTS movies(
id BIGINT PRIMARY KEY,
title Varchar(255) NOT NULL,
rating DECIMAL(2,1),
user_id BIGINT
);


INSERT into person (id,user_name,password,token) 
SELECT  100001,'admin','admin','4a7b79a1-8cbe-4c6b-9cb2-afe5f390ba12'
WHERE   NOT EXISTS 
        (   SELECT  1
            FROM    person 
            WHERE   id = 100001
            AND     user_name = 'admin'
        );
INSERT into movies (id,title,rating,user_id) 
SELECT  100001,'Juno',9.9,100001
WHERE   NOT EXISTS 
        (   SELECT  1
            FROM    movies 
            WHERE   id = 100001
            AND     title = 'Juno'
        );
INSERT into movies (id,title,rating,user_id) 
SELECT  100002,'Winter''s Bone',9.9,100001
WHERE   NOT EXISTS 
        (   SELECT  1
            FROM    movies 
            WHERE   id = 100002
            AND     title = 'Winter''s Bone'
        );