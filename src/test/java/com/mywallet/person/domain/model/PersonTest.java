package com.mywallet.person.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class PersonTest {
		private static final String NAME = "name";
		private static final String SURNAME = "surname";

		@Test
		public void setName_null_value() {
			Person person = new Person();
			person.setName(null);

			assertNull(person.getName());
		}

		@Test
		public void setName_trim_value() {
			Person person = new Person();
			person.setName(" "+NAME+" ");

			assertEquals(person.getName(), NAME);
		}

		@Test
		public void setSurname_null_value() {
			Person person = new Person();
			person.setSurname(null);

			assertNull(person.getSurname());
		}

		@Test
		public void setSurname_trim_value() {
			Person person = new Person();
			person.setSurname(" "+SURNAME+" ");

			assertEquals(person.getSurname(), SURNAME);
		}
}
