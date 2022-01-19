package com.backbase.simplemoviesservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.jdbc.Sql;

import com.backbase.simplemoviesservice.jpa.AcademyAwards;

@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(scripts = {"file:src/test/resources/data.sql"})
public class AcademyAwardServiceTest {

    @Autowired
    @InjectMocks
    private AcademyAwardServiceImpl academyAwardService;
    
    private String movieTitle;

    @BeforeEach
    public void setUp() {
    	movieTitle = "Juno";
    }
    
    @AfterEach
    public void tearDown() {
    	movieTitle = null;
    }

    @Test
    public void checkifAcademyAwardsListIsLoaded(){
         List<AcademyAwards> academyAwards = academyAwardService.returnAcademyAwards();
         assertFalse(academyAwards.isEmpty());
    }
    
    @Test
    public void givenWinnerMovieNameReturnTrue() {
    	String winner = academyAwardService.isBestPictureWinner(movieTitle);
    	assertEquals("This movie won the Best Picture Award!",winner);
    }
    
}
