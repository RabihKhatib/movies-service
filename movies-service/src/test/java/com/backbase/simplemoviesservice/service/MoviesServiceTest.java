package com.backbase.simplemoviesservice.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.reactive.function.client.WebClient;

import com.backbase.simplemoviesservice.jpa.MoviesRepository;
import com.backbase.simplemoviesservice.jpa.PersonRepository;
import com.backbase.simplemoviesservice.response.OMDBApiResponse;

@ExtendWith(MockitoExtension.class)
@Sql(scripts = {"file:src/test/resources/data.sql"})
public class MoviesServiceTest {

	@Mock
	private MoviesRepository moviesRepository;
	@Mock
	private PersonRepository personRepository;

	@Autowired
	@InjectMocks
	private MoviesServiceImpl moviesService = new MoviesServiceImpl(WebClient.builder());

	private List<OMDBApiResponse> top10Movies ;

	@Test
	void checkIfTheListIsNotNullEnsuringNoExceptions() {
		top10Movies = moviesService.returnTopTenMovies();
		assertNotNull(top10Movies);
	}


}
