package com.backbase.simplemoviesservice.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query(value = "SELECT u FROM Person u where u.userName = ?1 and u.password = ?2 ")
    Optional<Person> login(String username,String password);

    Optional<Person> findByToken(String token);

	Optional<Person> findByUserName(String username);
}
