package com.mywallet.person.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.mywallet.WrapperConfiguration;
import com.mywallet.core.api.dto.ApiErrorDTO;
import com.mywallet.helper.WrapperHelper;
import com.mywallet.mock.PersonMock;
import com.mywallet.person.api.dto.PersonDTO;
import com.mywallet.person.domain.service.PersonListService;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@Import(WrapperConfiguration.class)
public class PersonControllerTest {

	@Autowired
	private MockMvc controller;

	@Autowired
	private WrapperHelper wrapperHelper;

	@MockBean
	private PersonListService personService;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void findById_empty() throws Exception {
		when(personService.findById(any())).thenReturn(Optional.empty());
		this.controller.perform(get("/person/2")).andExpect(status().isNoContent());
	}

	@Test
	public void findById_value() throws Exception {
		PersonDTO personDTOMock = PersonDTO.valueOf(PersonMock.mock());
		when(personService.findById(any())).thenReturn(Optional.of(personDTOMock.toEntity()));

		MvcResult response = this.controller
				.perform(get("/person/1"))
				.andExpect(status().isOk())
		.andReturn();

		PersonDTO personDTO = wrapperHelper.unwrapper(response.getResponse().getContentAsString(), PersonDTO.class);

		assertNotNull(personDTO);
		assertEquals(personDTOMock.getId(), personDTO.getId());
		assertEquals(personDTOMock.getName(), personDTO.getName());
		assertEquals(personDTOMock.getSurname(), personDTO.getSurname());
	}

	@Test
	public void findAutoComplet_no_param() throws Exception {
		PersonDTO personDTOMock = PersonDTO.valueOf(PersonMock.mock());
		when(personService.findAutoComplete(any())).thenReturn(List.of(personDTOMock.toEntity()));

		this.controller.perform(get("/person")).andExpect(status().isBadRequest());
	}

	@Test
	public void findAutoComplet_empty() throws Exception {
		MvcResult response = this.controller
				.perform(get("/person?term="))
				.andExpect(status().isOk())
		.andReturn();

		verify(personService, never()).findAutoComplete(anyString());
		List<PersonDTO> persons = wrapperHelper.unwrapperList(response.getResponse().getContentAsString(), PersonDTO.class);

		assertNotNull(persons);
		assertEquals(0, persons.size());
	}


	@Test
	public void findAutoComplet_value() throws Exception {
		PersonDTO personDTOMock = PersonDTO.valueOf(PersonMock.mock());
		when(personService.findAutoComplete(any())).thenReturn(List.of(personDTOMock.toEntity()));

		MvcResult response = this.controller
				.perform(get("/person?term=test"))
				.andExpect(status().isOk())
		.andReturn();

		List<PersonDTO> persons = wrapperHelper.unwrapperList(response.getResponse().getContentAsString(), PersonDTO.class);

		assertNotNull(persons);
		assertEquals(1, persons.size());

		PersonDTO personDTO = persons.stream().findFirst().get();
		assertEquals(personDTOMock.getId(), personDTO.getId());
		assertEquals(personDTOMock.getName(), personDTO.getName());
		assertEquals(personDTOMock.getSurname(), personDTO.getSurname());
	}

	@Test
	public void create_null_person() throws Exception {
		MvcResult response = this.controller.perform(
			post("/person")
			.contentType(MediaType.APPLICATION_JSON)
			.content("{}")
		)
		.andExpect(status().isBadRequest())
		.andReturn();

		ApiErrorDTO error = wrapperHelper.unwrapper(response.getResponse().getContentAsString(), ApiErrorDTO.class);
		assertEquals(400, error.getStatus());
		assertEquals("Bad Request", error.getError());
		assertEquals("The person name must be provided!", error.getMessage());
		assertEquals("/person", error.getPath());
		assertNotNull(error.getTimestamp());
	}

	@Test
	public void create_person_without_name() throws Exception {
		PersonDTO personDTO = PersonDTO.valueOf(PersonMock.mock());
		personDTO.setName(null);

		MvcResult response = this.controller.perform(
			post("/person")
			.contentType(MediaType.APPLICATION_JSON)
			.content(wrapperHelper.wrapper(personDTO))
		)
		.andExpect(status().isBadRequest())
		.andReturn();

		ApiErrorDTO error = wrapperHelper.unwrapper(response.getResponse().getContentAsString(), ApiErrorDTO.class);
		assertEquals(400, error.getStatus());
		assertEquals("Bad Request", error.getError());
		assertEquals("The person name must be provided!", error.getMessage());
		assertEquals("/person", error.getPath());
		assertNotNull(error.getTimestamp());
	}

	@Test
	public void create_person_without_surname() throws Exception {
		PersonDTO personDTO = PersonDTO.valueOf(PersonMock.mock());
		personDTO.setSurname(null);

		MvcResult response = this.controller.perform(
			post("/person")
			.contentType(MediaType.APPLICATION_JSON)
			.content(wrapperHelper.wrapper(personDTO))
		)
		.andExpect(status().isBadRequest())
		.andReturn();

		ApiErrorDTO error = wrapperHelper.unwrapper(response.getResponse().getContentAsString(), ApiErrorDTO.class);
		assertEquals(400, error.getStatus());
		assertEquals("Bad Request", error.getError());
		assertEquals("The person surname must be provided!", error.getMessage());
		assertEquals("/person", error.getPath());
		assertNotNull(error.getTimestamp());
	}

}
