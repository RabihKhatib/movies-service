package com.backbase.simplemoviesservice.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MoviesRepository extends JpaRepository<Movies, Long> {

    Optional<Movies> findByUserIdAndTitle(Long userId, String title);
    
    @Query(value = "SELECT title from (SELECT avg(rating) as rating, title FROM movie_rating group by title order by rating desc limit 10) toptitles limit 10", 
    		  nativeQuery = true)
    List<String> findTop10ByAvgRatingByTitleDesc();
}
