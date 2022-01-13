package com.backbase.simplemoviesservice.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.backbase.simplemoviesservice.jpa.AcademyAwards;
import com.backbase.simplemoviesservice.jpa.Movies;
import com.backbase.simplemoviesservice.jpa.MoviesRepository;
import com.backbase.simplemoviesservice.jpa.Person;
import com.backbase.simplemoviesservice.jpa.PersonRepository;
import com.backbase.simplemoviesservice.response.OMDBApiResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service("moviesService")
public class MoviesServiceImpl implements MoviesService {

	@Autowired
	MoviesRepository moviesRepository;

	@Autowired
	PersonRepository personRepository;

	@Autowired
	AcademyAwardService academyAwardsService;

	// Should be read from property file
	private static final String omdbApiKey = "7d458709";

	@Override
	public Movies rateMovie(String movieName, float rating, String token) {

		token = StringUtils.removeStart(token, "Bearer").trim();
		Optional<Person> personOptional = personRepository.findByToken(token);

		if (personOptional.isPresent()) {

			Person person = personOptional.get();

			// Validate movie name
			List<AcademyAwards> academyAwards = academyAwardsService.returnAcademyAwards();
			AcademyAwards selectedMovie = academyAwards.stream().filter((movie) -> movie.getNominee().equals(movieName))
					.findAny().get();

			if (selectedMovie != null && StringUtils.isNoneEmpty(selectedMovie.getNominee())) {

				Optional<Movies> movieRatingOptional = moviesRepository.findByUserIdAndTitle(person.getId(),
						movieName);

				Movies movieRating = null;

				if (movieRatingOptional.isPresent()) {

					movieRating = movieRatingOptional.get();

					if (Math.abs(movieRating.getRating() - rating) > Float.MIN_VALUE) {
						movieRating.setRating(Math.round(rating));
						movieRating = moviesRepository.save(movieRating);
					}
				} else {
					movieRating = moviesRepository.save(new Movies(null, person.getId(), rating, movieName));
				}

				return movieRating;
			}
		}

		return null;
	}

	private final WebClient webClient;

	public MoviesServiceImpl(WebClient.Builder webClientBuilder) {
		this.webClient = webClientBuilder.baseUrl("http://www.omdbapi.com/").build();
	}

	public Mono<OMDBApiResponse> returnMoviesDetails(String movieName) {
		return this.webClient.get().uri(uriBuilder -> uriBuilder
				.queryParam("t", movieName)
				.queryParam("apiKey", omdbApiKey)
				.queryParam("type", "movie").build())
				.retrieve().bodyToMono(OMDBApiResponse.class);
	}

	@Override
	public List<OMDBApiResponse> returnTopTenMovies() {

		List<String> localTop10RatedMovies = moviesRepository.findTop10ByAvgRatingByTitleDesc();

		List<OMDBApiResponse> moviesList = Flux.fromIterable(localTop10RatedMovies)
				.flatMap(this::returnMoviesDetails)
				.collectList().block();
		
		Collections.sort(moviesList, new Comparator<Object>() {

			public int compare(Object o1, Object o2) {
				OMDBApiResponse p1 = (OMDBApiResponse) o1;
				OMDBApiResponse p2 = (OMDBApiResponse) o2;
				int a = 0, b = 0;
				a = parse(p1.getBoxOffice());
				b = parse(p2.getBoxOffice());
				return a - b;
			}

		});

		return moviesList;

	}

	public static int parse(final String amount) {
		String numberedAmount = amount.replaceAll("[^\\d.]+", "");
		return Integer.parseInt(numberedAmount);
	}

}
