package com.restcrud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restcrud.exception.PersonNotFoundException;
import com.restcrud.model.Person;
import com.restcrud.repository.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	PersonRepository personRepository;

	@Override
	public List<Person> findAllPersons() throws PersonNotFoundException {

		return personRepository.findAll();
	}

	@Override
	public Person findPersonById(Long id) throws PersonNotFoundException {

		return personRepository.findPersonById(id);
	}

	@Override
	public List<Person> findPersonsByFirstName(String firstName) throws PersonNotFoundException {

		return personRepository.findPersonByFirstName(firstName);
	}

	@Override
	public Boolean isPersonExist(Long id) {
		
		return personRepository.existsById(id);
	}

	@Override
	public void createPerson(Person person) {
		person.setId(null); // Eğer id değeri girilir ve bu id değerinde bir kaydımız söz konusu
							// olursa güncelleme işlemi yapmaması için null değeri verdik.

		personRepository.save(person);
	}

	@Override
	public void updatePerson(Person person) {
		personRepository.save(person);
	}

	@Override
	public void deletePersonById(Long id) {
		personRepository.deleteById(id);
	}

	@Override
	public void save(Person person) {
		personRepository.save(person);
		
	}



}
