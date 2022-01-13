package com.backbase.simplemoviesservice.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.backbase.simplemoviesservice.jpa.AcademyAwards;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

@Service("academyAwardService")
public class AcademyAwardServiceImpl implements AcademyAwardService{

	public List<AcademyAwards> returnAcademyAwards() {

		InputStream stream = null;
		try {
	    	File file = ResourceUtils.getFile("classpath:academy_awards.csv");
	    	stream = new FileInputStream(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	 try (Reader reader = new BufferedReader(new InputStreamReader(stream))) {

             CsvToBean<AcademyAwards> csvToBean = new CsvToBeanBuilder<AcademyAwards>(reader)
                     .withType(AcademyAwards.class)
                     .withIgnoreLeadingWhiteSpace(true)
                     .build();
             return  csvToBean.parse();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    	 return null;
	}
	
	public String isBestPictureWinner(String movieName) {
		List<AcademyAwards> academyAwards = returnAcademyAwards();
		AcademyAwards selectedMovie = academyAwards.stream()
				.filter((movie) -> movie.getNominee().equals(movieName) 
						&& movie.getCategory().equals("Best Picture"))
				.findAny().get();
		if(selectedMovie==null)
		{
			return "This movie didn't win the Best Picture Award!";
		}
		return "This movie won the Best Picture Award!";
	}
}
