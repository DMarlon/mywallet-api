package com.mywallet.person.domain.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.mywallet.person.domain.repository.PersonRepository;

public class PersonListServiceTest {

	@Mock
	private PersonRepository personRepository;
	private PersonListService personListService;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		personListService = new PersonListService(personRepository);
	}

	@Test
	public void findAutoComplete_with_null_term() {
		personListService.findAutoComplete(null);
		verify(personRepository, never()).findAutoComplete(anyString(), any());
	}

	@Test
	public void findAutoComplete_with_empty_term() {
		personListService.findAutoComplete("");
		verify(personRepository, never()).findAutoComplete(anyString(), any());
	}

	@Test
	public void findAutoComplete_with_call_repository() {
		personListService.findAutoComplete("MARLON");
		verify(personRepository, atLeastOnce()).findAutoComplete(anyString(), any());
	}

	@Test
	public void findAutoComplete_with_term_lowercase() {
		personListService.findAutoComplete("MARLON");
		verify(personRepository, atLeastOnce()).findAutoComplete(Mockito.eq("marlon"), any());
	}

	@Test
	public void findById_with_id_null() {
		personListService.findById(null);
		verify(personRepository, never()).findById(anyLong());
	}

	@Test
	public void findById_with_id_less_than_one() {
		personListService.findById(Long.valueOf(0));
		verify(personRepository, never()).findById(anyLong());
	}

	@Test
	public void findById_with_id_call_repository() {
		personListService.findById(Long.valueOf(1));
		verify(personRepository, atLeastOnce()).findById(anyLong());
	}

	@Test
	public void existsById_with_id_null() {
		personListService.existsById(null);
		verify(personRepository, never()).existsById(anyLong());
	}

	@Test
	public void existsById_with_id_less_than_one() {
		personListService.existsById(Long.valueOf(0));
		verify(personRepository, never()).existsById(anyLong());
	}

	@Test
	public void existsById_with_id_call_repository() {
		personListService.existsById(Long.valueOf(1));
		verify(personRepository, atLeastOnce()).existsById(anyLong());
	}
}
