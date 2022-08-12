package com.mywallet.mock;

import com.mywallet.person.domain.model.Person;

public class PersonMock {
	private static final Long IDMOCK = Long.valueOf(99999);
	private static final String NAME = "name";
	private static final String SURNAME = "surname";

	public static Person mock() {
		Person person = new Person();
		person.setId(IDMOCK);
		person.setName(NAME);
		person.setSurname(SURNAME);

		return person;
	}
}
