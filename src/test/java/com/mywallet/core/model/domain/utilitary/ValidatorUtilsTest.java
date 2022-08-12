package com.mywallet.core.model.domain.utilitary;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.mywallet.core.domain.utilitary.ValidatorUtils;

public class ValidatorUtilsTest {

	@Test
	public void isNullOrLessThanZero_with_long_null() {
		Long value = null;
		assertTrue(ValidatorUtils.isNullOrLessThanZero(value));
	}

	@Test
	public void isNullOrLessThanZero_with_long_less_than_zero() {
		assertTrue(ValidatorUtils.isNullOrLessThanZero(Long.valueOf(-1)));
	}

	@Test
	public void isNullOrLessThanZero_with_long_zero() {
		assertFalse(ValidatorUtils.isNullOrLessThanZero(Long.valueOf(0)));
	}

	@Test
	public void isNullOrLessThanZero_with_long_bigger_than_zero() {
		assertFalse(ValidatorUtils.isNullOrLessThanZero(Long.valueOf(05)));
	}

	@Test
	public void isNullOrLessThanOne_with_long_null() {
		Long value = null;
		assertTrue(ValidatorUtils.isNullOrLessThanOne(value));
	}

	@Test
	public void isNullOrLessThanOne_with_long_less_than_zero() {
		assertTrue(ValidatorUtils.isNullOrLessThanOne(Long.valueOf(-1)));
	}

	@Test
	public void isNullOrLessThanOne_with_long_zero() {
		assertTrue(ValidatorUtils.isNullOrLessThanOne(Long.valueOf(0)));
	}

	@Test
	public void isNullOrLessThanOne_with_long_bigger_than_zero() {
		assertFalse(ValidatorUtils.isNullOrLessThanOne(Long.valueOf(5)));
	}

	@Test
	public void isNullOrEmpty_with_string_null() {
		String value = null;
		assertTrue(ValidatorUtils.isNullOrEmpty(value));
	}

	@Test
	public void isNullOrEmpty_with_string_empty() {
		assertTrue(ValidatorUtils.isNullOrEmpty(""));
	}

	@Test
	public void isNullOrEmpty_with_string_black() {
		assertFalse(ValidatorUtils.isNullOrEmpty(" "));
		assertFalse(ValidatorUtils.isNullOrEmpty("     "));
	}

	@Test
	public void isNullOrEmpty_with_string_value() {
		assertFalse(ValidatorUtils.isNullOrEmpty("a"));
	}

	@Test
	public void isNullOrEmpty_with_collection_null() {
		Collection<String> value = null;
		assertTrue(ValidatorUtils.isNullOrEmpty(value));
	}

	@Test
	public void isNullOrEmpty_with_collection_empty() {
		assertTrue(ValidatorUtils.isNullOrEmpty(Collections.emptyList()));
	}

	@Test
	public void isNullOrEmpty_with_collection_null_values() {
		List<String> list = new ArrayList<String>();
		list.add(null);

		assertFalse(ValidatorUtils.isNullOrEmpty(list));
	}

	@Test
	public void isNullOrEmpty_with_collection_with_values() {
		assertFalse(ValidatorUtils.isNullOrEmpty(List.of("a")));
	}
}
