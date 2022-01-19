This API connects to MySql and create a new schema, tables and DML statetements
Adjust application.properties spring.datasource to connect to DB

You can run this application by running the executable jar file "/target/movies-service-1.0.0.jar"
------------OR--------------
Import the movies-service maven project into eclipse (Version: 2020-09 (4.17.0) and up)
run the main Application class as Java Application "MoviesServiceApplication.java"
-----------------OR------------------
got to the project root directory and type:
mvn spring-boot:run

--------------Tests----------------------
got to the project root directory and type:
mvn test -Dtest=MoviesServiceApplicationTests