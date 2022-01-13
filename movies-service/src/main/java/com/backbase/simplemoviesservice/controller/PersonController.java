package com.backbase.simplemoviesservice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backbase.simplemoviesservice.jpa.Person;
import com.backbase.simplemoviesservice.service.PersonService;

@RestController
public class PersonController {

	@Autowired
	private PersonService personService;

	@GetMapping(value = "/api/users/user/{id}", produces = "application/json")
	public Person getUserDetail(@PathVariable @Valid Long id) {

		return personService.findById(id);
	}

	@PostMapping("/register")
	public Person registerPerson(@Valid @RequestParam String username, @Valid @RequestParam String password) {
		try {
			Person person = personService.registerPerson(username, password);
			if (person != null) {
				String token = personService.login(username, password);
				person.setToken(token);
			}
			return person;
		} catch (IllegalArgumentException e) {
			throw e;
		}
	}
}
