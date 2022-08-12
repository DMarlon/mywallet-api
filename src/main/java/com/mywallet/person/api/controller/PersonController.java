package com.mywallet.person.api.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.mywallet.core.domain.exception.RequiredFieldException;
import com.mywallet.core.domain.utilitary.ValidatorUtils;
import com.mywallet.person.api.dto.PersonDTO;
import com.mywallet.person.domain.model.Person;
import com.mywallet.person.domain.service.PersonCreateService;
import com.mywallet.person.domain.service.PersonListService;

@RestController
@RequestMapping("/person")
public class PersonController {

	private PersonCreateService personCreateService;
	private PersonListService personListService;

	public PersonController(PersonCreateService personCreateService, PersonListService personListService) {
		this.personCreateService = personCreateService;
		this.personListService = personListService;
	}

	@PostMapping
	public PersonDTO create(@RequestBody PersonDTO personDTO) {
		try {
			return PersonDTO.valueOf(personCreateService.create(personDTO.toEntity()));
		} catch (RequiredFieldException exception) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
		}
	}

	@GetMapping
    public List<PersonDTO> findAutoComplete(@RequestParam("term") String term) {
		if (ValidatorUtils.isNullOrEmpty(term))
    		return Collections.emptyList();

    	return personListService.findAutoComplete(term).stream().map(PersonDTO::valueOf).collect(Collectors.toList());
	}

	@GetMapping("/{id:[0-9]+}")
	public PersonDTO findById(@PathVariable("id") Long personId) {
		Optional<Person> person = personListService.findById(personId);
		if (person.isEmpty())
			throw new ResponseStatusException(HttpStatus.NO_CONTENT);

		return PersonDTO.valueOf(person.get());
	}

}
