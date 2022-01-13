package com.backbase.simplemoviesservice.service;

import java.util.List;

import com.backbase.simplemoviesservice.jpa.AcademyAwards;

public interface AcademyAwardService {

	List<AcademyAwards> returnAcademyAwards();
	String isBestPictureWinner(String movieName);
}
