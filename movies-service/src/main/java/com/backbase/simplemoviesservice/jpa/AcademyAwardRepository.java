package com.backbase.simplemoviesservice.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademyAwardRepository extends JpaRepository<Person, Long> {

}
