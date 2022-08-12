package com.mywallet.wallet.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.mywallet.mock.WalletMock;
import com.mywallet.wallet.domain.enumerator.TransactionOperation;
import com.mywallet.wallet.domain.enumerator.TransactionType;

public class TransactionRegisterTest {

	@Test
	public void valueOf_null_values() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> TransactionRegister.valueOf(null, null, null, null, null));
		assertEquals("All information is needed to create a transactional register!", exception.getMessage());
	}

	@Test
	public void valueOf_wallet_null() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> TransactionRegister.valueOf(null, TransactionOperation.CREDIT, TransactionType.DEPOSIT, Long.valueOf(50), "teste"));
		assertEquals("All information is needed to create a transactional register!", exception.getMessage());
	}

	@Test
	public void valueOf_operation_null() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> TransactionRegister.valueOf(WalletMock.mock(), null, TransactionType.DEPOSIT, Long.valueOf(50), "teste"));
		assertEquals("All information is needed to create a transactional register!", exception.getMessage());
	}

	@Test
	public void valueOf_type_null() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> TransactionRegister.valueOf(WalletMock.mock(), TransactionOperation.CREDIT, null, Long.valueOf(50), "teste"));
		assertEquals("All information is needed to create a transactional register!", exception.getMessage());
	}

	@Test
	public void valueOf_value_null() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> TransactionRegister.valueOf(WalletMock.mock(), TransactionOperation.CREDIT, TransactionType.DEPOSIT, null, "teste"));
		assertEquals("All information is needed to create a transactional register!", exception.getMessage());
	}

	@Test
	public void valueOf_value_less_than_one() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> TransactionRegister.valueOf(WalletMock.mock(), TransactionOperation.CREDIT, TransactionType.DEPOSIT, Long.valueOf(0), "teste"));
		assertEquals("All information is needed to create a transactional register!", exception.getMessage());
	}

	@Test
	public void valueOf_observation_null() {
		TransactionRegister register = TransactionRegister.valueOf(WalletMock.mock(), TransactionOperation.CREDIT, TransactionType.DEPOSIT, Long.valueOf(1000), null);
		assertNull(register.getObservation());
	}

	@Test
	public void valueOf_create() {
		Wallet wallet = WalletMock.mock();
		Long value = Long.valueOf(1000);

		TransactionRegister register = TransactionRegister.valueOf(wallet, TransactionOperation.CREDIT, TransactionType.DEPOSIT, value, "test");
		assertEquals(wallet, register.getWallet());
		assertEquals(TransactionOperation.CREDIT, register.getOperation());
		assertEquals(TransactionType.DEPOSIT, register.getType());
		assertEquals(value, register.getValue());
		assertEquals("test", register.getObservation());
	}
}
