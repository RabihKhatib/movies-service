package com.backbase.simplemoviesservice.controller;

import java.util.List;

import javax.management.BadAttributeValueExpException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.backbase.simplemoviesservice.jpa.Movies;
import com.backbase.simplemoviesservice.response.OMDBApiResponse;
import com.backbase.simplemoviesservice.service.MoviesService;

@RestController
public class MoviesController {
	
	@Autowired
	private MoviesService moviesService;
	
	@PostMapping(value="/api/movie/{movieName}/rating/{rating}")
	public Movies rateMovie(@PathVariable String movieName, @PathVariable float rating
			, @RequestHeader (name="Authorization") String token) throws BadAttributeValueExpException {
		if (rating<1 || rating>10) {
			throw new BadAttributeValueExpException("Rating should be between 1 and 10");
		    }
		
		return moviesService.rateMovie(movieName, rating, token);
	}

	@GetMapping(value="/api/movie/topratedbyboxoffice")
	public List<OMDBApiResponse> returnTopTenMovies() {
		return moviesService.returnTopTenMovies();
	}
}
