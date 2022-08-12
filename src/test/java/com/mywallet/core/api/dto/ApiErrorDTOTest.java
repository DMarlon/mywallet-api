package com.mywallet.core.api.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class ApiErrorDTOTest {

	private static final int STATUS = 200;
	private static final String ERROR = "Error";
	private static final String MESSAGE_ERROR = "Message Error";
	private static final String PATH = "/here";

	@Test
	public void create_with_values() {
		ApiErrorDTO error = ApiErrorDTO.valueOf(STATUS, ERROR, MESSAGE_ERROR, PATH);
		assertEquals(error.getStatus(), STATUS);
		assertEquals(error.getError(), ERROR);
		assertEquals(error.getMessage(), MESSAGE_ERROR);
		assertEquals(error.getPath(), PATH);
		assertNotNull(error.getTimestamp());
	}

}
