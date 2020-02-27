package com.restcrud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.restcrud.model.Person;
import com.restcrud.service.PersonService;

@Controller
@RequestMapping("/persons")
public class WebSiteController {

	@Autowired
	private PersonService personService;


	@GetMapping("/list")
	public String listPersons(Model model) {

		
		List<Person> persons = personService.findAllPersons();

	
		model.addAttribute("persons", persons);

		return "persons/list-persons";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {

	
		Person person = new Person();

		model.addAttribute("person", person);

		return "persons/person-form";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("personId") Long personId, Model theModel) {

		// get the employee from the service
		Person person = personService.findPersonById(personId);

		theModel.addAttribute("person", person);

		return "persons/person-form";
	}

	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("person") Person person) {

		// save the employee
		personService.save(person);

		// use a redirect to prevent duplicate submissions
		return "redirect:/persons/list";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("personId") Long personId) {

		// delete the employee
		personService.deletePersonById(personId);

		// redirect to /employees/list
		return "redirect:/persons/list";

	}

}
