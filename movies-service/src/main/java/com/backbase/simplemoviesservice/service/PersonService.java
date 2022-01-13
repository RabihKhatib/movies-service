package com.backbase.simplemoviesservice.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.User;

import com.backbase.simplemoviesservice.jpa.Person;

public interface PersonService {

    String login(String username, String password);
    Optional<User> findByToken(String token);
    Person findById(Long id);
	Person registerPerson(String username, String password);
}
