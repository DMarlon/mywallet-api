package com.mywallet.person.domain.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mywallet.core.domain.utilitary.ValidatorUtils;
import com.mywallet.person.domain.model.Person;
import com.mywallet.person.domain.repository.PersonRepository;

@Service
public class PersonListService {

	private PersonRepository personRepository;

	public PersonListService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	public List<Person> findAutoComplete(String term) {
		if (ValidatorUtils.isNullOrEmpty(term))
			return Collections.emptyList();

		return personRepository.findAutoComplete(term.toLowerCase(), personRepository.getPageableAutoComplete());
	}

	public Optional<Person> findById(Long personId) {
		if (ValidatorUtils.isNullOrLessThanOne(personId))
			return Optional.empty();

		return personRepository.findById(personId);
	}

	public boolean existsById(Long id) {
		if (ValidatorUtils.isNullOrLessThanOne(id))
			return false;

		return personRepository.existsById(id);
	}

}
