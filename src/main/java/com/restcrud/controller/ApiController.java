package com.restcrud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.restcrud.exception.PersonNotFoundException;
import com.restcrud.model.Person;
import com.restcrud.service.ApiService;

@RestController
@RequestMapping("/rest")
public class ApiController {

	@Autowired
	private ApiService apiService;

	/*
	 * Body example:
	 * 
	 * {
	 *  	"firstName": "Mustafa Alp", 
	 *  	"lastName": "Cetin", 
	 *  	"address": "My addrres",
	 * 		"city": "Istanbul", 
	 * 		"age": 24 
	 * } 
	 * 
	 * For inserting new person record
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/persons")
	public ResponseEntity<Person> createPerson(@RequestBody Person person) throws Exception {

		apiService.createPerson(person);

		return new ResponseEntity<Person>(person, HttpStatus.CREATED);

	}

	/*
	 * For updating exist person record
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/persons")
	public ResponseEntity<Person> updatePerson(@RequestBody Person person) throws Exception {

		if (person.getId() == null) {
			throw new Exception("id cannot be null");
		}
		if (!apiService.isPersonExist(person.getId())) {
			throw new PersonNotFoundException("Person not found");
		}
		apiService.updatePerson(person);

		return new ResponseEntity<Person>(person, HttpStatus.OK);

	}

	/*
	 * For getting all person records
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/persons")
	@ResponseBody
	public ResponseEntity<List<Person>> getAllPersons() {
		List<Person> persons = apiService.findAllPersons();

		if (persons.size() == 0) {
			throw new PersonNotFoundException("Person not found");
		}

		return new ResponseEntity<List<Person>>(persons, HttpStatus.OK);
	}

	/*
	 * For searching person records by name
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/persons/name")
	@ResponseBody
	public ResponseEntity<List<Person>> getPersonsByName(@RequestParam("person_name") String name) {
		List<Person> persons = apiService.findPersonsByFirstName(name);

		if (persons.size() == 0) {
			throw new PersonNotFoundException("Person not found");
		}
		return new ResponseEntity<List<Person>>(persons, HttpStatus.OK);
	}

	/*
	 * For getting specific person record by id
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/persons/{id}")
	@ResponseBody
	public ResponseEntity<Person> getPersonById(@PathVariable("id") Long id) {

		Person person = apiService.findPersonById(id);

		if (person == null) {
			throw new PersonNotFoundException("Person not found");
		}

		return new ResponseEntity<Person>(person, HttpStatus.OK);
	}

	/*
	 * For deleting specific person record
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/persons/{id}")
	public ResponseEntity<?> deletePerson(@PathVariable("id") Long id) {

		if (!apiService.isPersonExist(id)) {
			throw new PersonNotFoundException("Person not found");
		}

		apiService.deletePersonById(id);
		return ResponseEntity.ok().build();

	}

}
