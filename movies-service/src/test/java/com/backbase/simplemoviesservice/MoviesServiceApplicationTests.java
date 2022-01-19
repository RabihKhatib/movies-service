package com.backbase.simplemoviesservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;


@ActiveProfiles("test") 
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = {"file:src/test/resources/data.sql"})
class MoviesServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}