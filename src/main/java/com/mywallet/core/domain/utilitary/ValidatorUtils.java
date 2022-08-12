package com.mywallet.core.domain.utilitary;

import java.util.Collection;
import java.util.Objects;

public class ValidatorUtils {

	private static final Long LONG_ZERO = Long.valueOf(0);
	private static final Long LONG_ONE = Long.valueOf(1);

	public static boolean isNullOrLessThanZero(Long value) {
		return Objects.isNull(value) || LONG_ZERO > value;
	}

	public static boolean isNullOrLessThanOne(Long value) {
		return Objects.isNull(value) || LONG_ONE > value;
	}

	public static boolean isNullOrEmpty(String value) {
		return Objects.isNull(value) || value.isEmpty();
	}

	public static boolean isNullOrEmpty(Collection<?> value) {
		return Objects.isNull(value) || value.isEmpty();
	}

}
