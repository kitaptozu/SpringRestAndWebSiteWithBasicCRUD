package com.restcrud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.restcrud.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{

	@Query(value = "SELECT p FROM Person p WHERE p.firstName LIKE %:firstName%")
	List<Person> findPersonByFirstName(@Param("firstName") String firstName);
	
	@Query(value = "SELECT p FROM Person p WHERE p.id LIKE :id")
	Person findPersonById(@Param("id") Long id);
	
	
}
