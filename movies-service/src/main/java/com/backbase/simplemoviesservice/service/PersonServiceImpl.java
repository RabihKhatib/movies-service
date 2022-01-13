package com.backbase.simplemoviesservice.service;

import java.util.Optional;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.backbase.simplemoviesservice.jpa.Person;
import com.backbase.simplemoviesservice.jpa.PersonRepository;

@Service("personService")
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;

    @Override
    public String login(String username, String password) {
    	
        Optional<Person> person = personRepository.login(username,password);
        
        if(person.isPresent()){
            String token = UUID.randomUUID().toString();
            Person tokenizedPerson= person.get();
            tokenizedPerson.setToken(token);
            personRepository.save(tokenizedPerson);
            return token;
        }

        return StringUtils.EMPTY;
    }

    @Override
    public Optional<User> findByToken(String token) {
    	
        Optional<Person> personOptional = personRepository.findByToken(token);
        
        if(personOptional.isPresent()){
            Person person = personOptional.get();
            
            User user= new User(person.getUserName()
            		, person.getPassword(), true, true, true, true,
                    AuthorityUtils.createAuthorityList("USER"));
            
            return Optional.of(user);
        }
        
        return  Optional.empty();
    }

    @Override
    public Person findById(Long id) {
    	
        Optional<Person> person= personRepository.findById(id);
        
        return person.orElse(null);
    }
    
	@Override
    public Person registerPerson(String username, String password) {
		Optional<Person> person = personRepository.findByUserName(username);
		if(person.isPresent())
		{
			throw new IllegalArgumentException("User Already Exist!");
		}
    	return personRepository.save(new Person(username, password));
    }
}
