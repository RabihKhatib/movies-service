package com.backbase.simplemoviesservice.service;

import java.util.List;

import com.backbase.simplemoviesservice.jpa.Movies;
import com.backbase.simplemoviesservice.response.OMDBApiResponse;

public interface MoviesService {

	Movies rateMovie(String movieName, float rating, String token);
	List<OMDBApiResponse> returnTopTenMovies();
}
