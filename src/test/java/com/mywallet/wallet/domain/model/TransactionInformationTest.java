package com.mywallet.wallet.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.mywallet.wallet.domain.enumerator.TransactionType;

public class TransactionInformationTest {

	@Test
	public void valueOf_null_values() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> TransactionInformation.valueOf(null, null, null, null));
		assertEquals("All information is needed to create a transactional information!", exception.getMessage());
	}

	@Test
	public void valueOf_wallet_null() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> TransactionInformation.valueOf(null, TransactionType.DEPOSIT, Long.valueOf(50), "teste"));
		assertEquals("All information is needed to create a transactional information!", exception.getMessage());
	}


	@Test
	public void valueOf_type_null() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> TransactionInformation.valueOf(UUID.randomUUID(), null, Long.valueOf(50), "teste"));
		assertEquals("All information is needed to create a transactional information!", exception.getMessage());
	}

	@Test
	public void valueOf_value_null() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> TransactionInformation.valueOf(UUID.randomUUID(), TransactionType.DEPOSIT, null, "teste"));
		assertEquals("All information is needed to create a transactional information!", exception.getMessage());
	}

	@Test
	public void valueOf_value_less_than_one() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> TransactionInformation.valueOf(UUID.randomUUID(), TransactionType.DEPOSIT, Long.valueOf(0), "teste"));
		assertEquals("All information is needed to create a transactional information!", exception.getMessage());
	}

	@Test
	public void valueOf_observation_null() {
		TransactionInformation information = TransactionInformation.valueOf(UUID.randomUUID(), TransactionType.DEPOSIT, Long.valueOf(1000), null);
		assertNull(information.getObservation());
	}

	@Test
	public void valueOf_create() {
		UUID walletNumber = UUID.randomUUID();
		Long value = Long.valueOf(1000);

		TransactionInformation information = TransactionInformation.valueOf(walletNumber, TransactionType.DEPOSIT, value, "test");
		assertEquals(walletNumber, information.getWalletNumber());
		assertEquals(TransactionType.DEPOSIT, information.getType());
		assertEquals(value, information.getValue());
		assertEquals("test", information.getObservation());
	}
}
