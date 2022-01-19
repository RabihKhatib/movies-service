package com.backbase.simplemoviesservice.controller;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.backbase.simplemoviesservice.MoviesServiceApplication;
import com.backbase.simplemoviesservice.jpa.Movies;
import com.backbase.simplemoviesservice.jpa.PersonRepository;
import com.backbase.simplemoviesservice.service.MoviesServiceImpl;

@ExtendWith(MockitoExtension.class)
class MoviesControllerTest extends MoviesServiceApplication{

	@Mock
	private MoviesServiceImpl moviesService;
	
	@Mock
	private PersonRepository personRepository;

	@InjectMocks
	private MoviesController moviesController;

	@Autowired
	private MockMvc mockMvc;

	private Movies movie;

	private String 	adminToken = "4a7b79a1-8cbe-4c6b-9cb2-afe5f390ba12";
	
	@BeforeEach
	public void setup() {
		adminToken = "4a7b79a1-8cbe-4c6b-9cb2-afe5f390ba12";
		mockMvc = MockMvcBuilders.standaloneSetup(moviesController).build();
	}

	@AfterEach
	void tearDown() {
		movie = null;
	}
	
	@Test
	public void verifyMoviesRating() throws Exception {
		doReturn(movie).when(moviesService).rateMovie("Juno",9L,adminToken);
		
	    mockMvc.perform(post("/api/movie/{movieName}/rating/{rating}","Juno",9L)
		           .contentType(MediaType.ALL_VALUE)
		           .header(HttpHeaders.AUTHORIZATION,adminToken))
	                   .andDo(MockMvcResultHandlers.print());
	    verify(moviesService).rateMovie("Juno",9L,adminToken);
	    verify(moviesService,times(1)).rateMovie("Juno",9L,adminToken);
	}
}