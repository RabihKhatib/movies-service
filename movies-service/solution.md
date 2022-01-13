This solution is made as a response to an API Backend Challenge, providing Restful services for movies listing and rating.
The used Platform is Spring Boot (for building the restful API) with Spring Security (for basic authentication using Bearer token authorization).
Project is built with Java MAVEN.
Dependencies used Spring Security, Spring Web, lombok, Spring JPA, MySql, Apache commons-lang3, Spring WebFlux, Spring Validation, opencsv, OpenAPI.
Spring WebClient is used to Async call external APIs.
Provided CSV file is loaded into project resources.
initial sql data is loaded as reources/data.sql.
used database: MySql (8.0.27 connector).
All services are wrapped into one service avoiding over engineering.
Included diagrams (Sequence diagram.pdf, ERD diagram.jpg, Class diagram.png)