package com.backbase.simplemoviesservice.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.backbase.simplemoviesservice.jpa.Person;
import com.backbase.simplemoviesservice.jpa.PersonRepository;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

	@Mock
	private PersonRepository personRepository;

	@Autowired
	@InjectMocks
	private PersonServiceImpl personService;

	private Person person1;

	@BeforeEach
	public void setUp() {
		person1 = new Person("Rickardo", "Rickardo password");
	}

	@AfterEach
	public void tearDown() {
		person1 = null;
	}

	@Test
	void providedAPersonToAddShouldReturnAddedPerson() {
		when(personRepository.save(any())).thenReturn(person1);
		personService.registerPerson(person1.getUserName(), person1.getPassword());
		verify(personRepository, times(1)).save(any());
	}


}
