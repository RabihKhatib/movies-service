package com.backbase.simplemoviesservice.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.backbase.simplemoviesservice.jpa.Person;
import com.backbase.simplemoviesservice.service.PersonService;

@ExtendWith(MockitoExtension.class)
@Sql(scripts = {"file:src/test/resources/data.sql"})
class TokenControllerTest {
	@Mock
	private PersonService personService;

	private Person person;

	@InjectMocks
	private TokenController tokenController;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	public void setup() {
		person = new Person("admin", "admin");
		mockMvc = MockMvcBuilders.standaloneSetup(tokenController).build();
	}

	@AfterEach
	void tearDown() {
		person = null;
	}

	@Test
	public void getTokenOfPerson() throws Exception {
		String token = person.getToken();
	    when(personService.login(any(),any())).thenReturn(token);
	    mockMvc.perform(post("/token")
		           .contentType(MediaType.ALL_VALUE)
		            .param("username", person.getUserName())
		            .param("password", person.getPassword()))
	                   .andDo(MockMvcResultHandlers.print());
	    verify(personService).login(person.getUserName(),person.getPassword());
	    verify(personService,times(1)).login(person.getUserName(),person.getPassword());
	}
}