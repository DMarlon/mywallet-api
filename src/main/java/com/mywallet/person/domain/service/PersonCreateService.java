package com.mywallet.person.domain.service;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.mywallet.core.domain.exception.RequiredFieldException;
import com.mywallet.core.domain.utilitary.ValidatorUtils;
import com.mywallet.person.domain.model.Person;
import com.mywallet.person.domain.repository.PersonRepository;

@Service
public class PersonCreateService {

	private PersonRepository personRepository;

	public PersonCreateService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	public Person create(Person person) {
		checkRequiredFields(person);

		person.setId(null);
		return personRepository.save(person);
	}

	private void checkRequiredFields(Person person) {
		if (Objects.isNull(person))
			throw new RequiredFieldException("The person must be provided!");
		if (ValidatorUtils.isNullOrEmpty(person.getName()))
			throw new RequiredFieldException("The person name must be provided!");
		if (ValidatorUtils.isNullOrEmpty(person.getSurname()))
			throw new RequiredFieldException("The person surname must be provided!");
	}
}
