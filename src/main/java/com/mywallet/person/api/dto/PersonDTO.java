package com.mywallet.person.api.dto;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.mywallet.person.domain.model.Person;

public class PersonDTO {

	@JsonProperty(access = Access.WRITE_ONLY)
	private Long id;
	private String name;
	private String surname;

	public static PersonDTO valueOf(Person person) {
		PersonDTO personDTO = new PersonDTO();

		if (Objects.nonNull(person)) {
			personDTO.setId(person.getId());
			personDTO.setName(person.getName());
			personDTO.setSurname(person.getSurname());
		}

		return personDTO;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Person toEntity() {
		Person person = new Person();
		person.setId(getId());
		person.setName(getName());
		person.setSurname(getSurname());

		return person;
	}

}
