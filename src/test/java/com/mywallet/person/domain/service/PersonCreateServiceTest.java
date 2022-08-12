package com.mywallet.person.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mywallet.core.domain.exception.RequiredFieldException;
import com.mywallet.mock.PersonMock;
import com.mywallet.person.domain.model.Person;
import com.mywallet.person.domain.repository.PersonRepository;

public class PersonCreateServiceTest {

	@Mock
	private PersonRepository personRepository;
	private PersonCreateService personCreateService;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		personCreateService = new PersonCreateService(personRepository);
	}

	@Test
	public void create_with_null() {
		RequiredFieldException exception = assertThrows(RequiredFieldException.class, () -> personCreateService.create(null));
		assertEquals("The person must be provided!", exception.getMessage());
	}

	@Test
	public void create_with_person_with_null_name() {
		Person person = PersonMock.mock();
		person.setName(null);

		RequiredFieldException exception = assertThrows(RequiredFieldException.class, () -> personCreateService.create(person));
		assertEquals("The person name must be provided!", exception.getMessage());
	}

	@Test
	public void create_with_person_with_blank_name() {
		Person person = PersonMock.mock();
		person.setName("");

		RequiredFieldException exception = assertThrows(RequiredFieldException.class, () -> personCreateService.create(person));
		assertEquals("The person name must be provided!", exception.getMessage());
	}

	@Test
	public void create_with_person_with_null_surname() {
		Person person = PersonMock.mock();
		person.setSurname(null);

		RequiredFieldException exception = assertThrows(RequiredFieldException.class, () -> personCreateService.create(person));
		assertEquals("The person surname must be provided!", exception.getMessage());
	}

	@Test
	public void create_with_person_with_blank_surname() {
		Person person = PersonMock.mock();
		person.setSurname("");

		RequiredFieldException exception = assertThrows(RequiredFieldException.class, () -> personCreateService.create(person));
		assertEquals("The person surname must be provided!", exception.getMessage());
	}

	@Test
	public void create_with_person_clean_id() {
		ArgumentCaptor<Person> person = ArgumentCaptor.forClass(Person.class);

		personCreateService.create(PersonMock.mock());
		verify(personRepository, atLeastOnce()).save(person.capture());
		assertNull(person.getValue().getId());
	}

	@Test
	public void create_with_person_with_values() {
		personCreateService.create(PersonMock.mock());
		verify(personRepository, atLeastOnce()).save(any());
	}

}
