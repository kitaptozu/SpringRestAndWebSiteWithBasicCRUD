package com.restcrud.service;

import java.util.List;

import com.restcrud.exception.PersonNotFoundException;
import com.restcrud.model.Person;

public interface ApiService {

	List<Person> findAllPersons() throws PersonNotFoundException;

	Person findPersonById(Long id) throws PersonNotFoundException;

	List<Person> findPersonsByFirstName(String name) throws PersonNotFoundException;
	
	Boolean isPersonExist(Long id);

	void createPerson(Person person);

	void updatePerson(Person person);

	void deletePersonById(Long id);

}
