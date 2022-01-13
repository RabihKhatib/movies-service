open the link http://localhost:8080/swagger-ui.html to see the complete list of services

Use an API testing tool like Curl or Postman.

Register User:
To register user POST the following URL:"http://localhost:8080/register"
With the request params: "username" and "password"
You shall get a JSON string showing user id/username/password/token

Retrieve Bearer Token:
TO retrieve the user token POST the following URL:"http://localhost:8080/token"
With the request params: "username" and "password"
You shall get a JSON string showing user id/username/password/token

To get user details:
GET "http://localhost:8080/api/users/user/{id}"
Where id is a path variable (i.e. "http://localhost:8080/api/users/user/11")
You need to set the Authorization Bearer Token in the request header 

To rate a movie:
You need to POST to "http://localhost:8080/api/movie/{moviename}/rating/{rating}"
where moviename is the movie name and the rating is the rating value which should be between 1 and 10 
Example: http://localhost:8080/api/movie/The Pursuit of Happyness/rating/8.9
You need to set the Authorization Bearer Token in the request header

To get the Top 10 rated movie ordered by Box Office value:
GET "http://localhost:8080/api/movie/topratedbyboxoffice"
This will return a list of the top 10 rated movies using the local application rating ordered by Box office value from OMDB API (movie name, year, box office value)
You need to set the Authorization Bearer Token in the request header

To check if a movie is a Best Picture Winner:
GET "http://localhost:8080/api/bestpicture/{moviename}"
Example http://localhost:8080/api/bestpicture/Juno
This will return a message string stating if this movie won or didn't the best picture award.
You need to set the Authorization Bearer Token in the request header




