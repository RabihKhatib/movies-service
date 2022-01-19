package com.backbase.simplemoviesservice.jpa;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PersonRepositoryTest {
	
    @Autowired
    private PersonRepository personRepository;
    private Person person;
    
    @BeforeEach
    public void setUp() {
    	person = new Person("testName","testPassw0rd");
    }
    @AfterEach
    public void tearDown() {
    	personRepository.deleteAll();
        person = null;
    }
    
    @Test
    public void providedAPersonToAddShouldReturnAddedPerson(){
         personRepository.save(person);
         Person fetchedPerson = personRepository.findById(person.getId()).get();
         assertNotNull(fetchedPerson.getId());
    }

    @Test
    public void providedAPersonToLoginShouldReturnPerson(){
         personRepository.save(person);
         Person fetchedPerson = personRepository.login(person.getUserName(),person.getPassword()).get();
         assertNotNull(fetchedPerson.getId());
    }
    
    @Test
    public void providedAPersonNameToFindShouldReturnPerson(){
        personRepository.save(person);
    	Person fetchedPerson = personRepository.findByUserName(person.getUserName()).get();
         assertNotNull(fetchedPerson.getId());
    }
}
