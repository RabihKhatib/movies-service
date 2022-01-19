package com.backbase.simplemoviesservice.jpa;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(scripts = {"file:src/test/resources/data.sql"})
public class MoviesRepositoryTest {
	
    @Autowired
    private MoviesRepository moviesRepository;
    private Long userId;
    private String movieTitle;
    private Movies movie;
    
    @BeforeEach
    public void setUp() {
    	movieTitle = "Juno";
    	userId = 100001L;
    }
    
    @AfterEach
    public void tearDown() {
    	moviesRepository.deleteAll();
    	movieTitle = null;
    	userId = null;
    }
    
    @Test
    public void givenUserIdAndMovieTitleShouldReturnMovie(){
    	movie = moviesRepository.findByUserIdAndTitle(userId,movieTitle).get();
         assertNotNull(movie);
    }
    @Test
    public void shouldReturnTop10ByAvgRatingByTitleDesc(){
    	List<String> top10Movies = moviesRepository.findTop10ByAvgRatingByTitleDesc();
        assertFalse(top10Movies.isEmpty());
   }
}
