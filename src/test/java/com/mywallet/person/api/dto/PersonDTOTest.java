package com.mywallet.person.api.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.mywallet.mock.PersonMock;
import com.mywallet.person.domain.model.Person;

public class PersonDTOTest {

	@Test
	public void valueOf_with_null() {
		PersonDTO personDTO = PersonDTO.valueOf(null);
		assertNull(personDTO.getId());
		assertNull(personDTO.getName());
		assertNull(personDTO.getSurname());
	}

	@Test
	public void valueOf_with_values() {
		Person person = PersonMock.mock();

		PersonDTO personDTO = PersonDTO.valueOf(person);
		assertEquals(personDTO.getId(), person.getId());
		assertEquals(personDTO.getName(), person.getName());
		assertEquals(personDTO.getSurname(), person.getSurname());
	}

	@Test
	public void toEntity_with_empty() {
		PersonDTO personDTO = new PersonDTO();
		Person person = personDTO.toEntity();

		assertNull(person.getId());
		assertNull(person.getName());
		assertNull(person.getSurname());
	}

	@Test
	public void toEntity_with_values() {
		PersonDTO personDTO = PersonDTO.valueOf(PersonMock.mock());
		Person person = personDTO.toEntity();

		assertEquals(personDTO.getId(), person.getId());
		assertEquals(personDTO.getName(), person.getName());
		assertEquals(personDTO.getSurname(), person.getSurname());
	}

}
