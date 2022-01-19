package com.backbase.simplemoviesservice.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.backbase.simplemoviesservice.jpa.Person;
import com.backbase.simplemoviesservice.service.PersonService;

@ExtendWith(MockitoExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class PersonControllerTest {
	
	@Mock
	private PersonService personService;

	@InjectMocks
	private PersonController personController;

	@Autowired
	private MockMvc mockMvc;

	private Person person;
	
	@BeforeEach
	public void setup() {
		person = new Person("admin", "admin");
		
		mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
	}

	@AfterEach
	void tearDown() {
		person = null;
	}

	@Test
	public void GetMappingOfPerson() throws Exception {
		when(personService.findById(any())).thenReturn(person);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/users/user/{id}", 1)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andDo(MockMvcResultHandlers.print());
		verify(personService).findById(1L);
		verify(personService, times(1)).findById(1L);
	}

	@Test
	public void PostMappingOfPerson() throws Exception {
		when(personService.registerPerson(any(), any())).thenReturn(person);
		mockMvc.perform(post("/register")
				.contentType(MediaType.ALL_VALUE)
				.param("username", person.getUserName())
				.param("password", person.getPassword()))
		.andExpect(status().isOk());
		verify(personService, times(1)).registerPerson(any(), any());
	}

}