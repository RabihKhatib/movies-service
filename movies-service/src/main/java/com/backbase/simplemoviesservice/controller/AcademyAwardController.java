package com.backbase.simplemoviesservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.backbase.simplemoviesservice.service.AcademyAwardService;

@RestController
public class AcademyAwardController {

	@Autowired
	private AcademyAwardService academyAwardService;
	
	@GetMapping(value = "/api/bestpicture/{movieName}")
	public String bestPictureWinner(@PathVariable String movieName) {
		return academyAwardService.isBestPictureWinner(movieName);
	}

}
