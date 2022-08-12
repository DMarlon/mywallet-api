package com.mywallet.wallet.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.mywallet.mock.WalletMock;
import com.mywallet.wallet.domain.enumerator.TransactionOperation;
import com.mywallet.wallet.domain.enumerator.TransactionType;

public class TransactionTest {

	private static final String OBSERVATION = "observation";

	@Test
	public void setObservation_null_value() {
		Transaction transaction = new Transaction();
		transaction.setObservation(null);

		assertNull(transaction.getObservation());
	}

	@Test
	public void setObservation_trim_value() {
		Transaction transaction = new Transaction();
		transaction.setObservation(" "+OBSERVATION+" ");

		assertEquals(transaction.getObservation(), OBSERVATION);
	}

	@Test
	public void valueOf_null() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Transaction.valueOf(null));
		assertEquals("Transaction register is needed to create a transaction!", exception.getMessage());
	}

	@Test
	public void valueOf_create() {
		Wallet wallet = WalletMock.mock();
		Long value = Long.valueOf(1000);

		Transaction transaction = Transaction.valueOf(TransactionRegister.valueOf(wallet, TransactionOperation.CREDIT, TransactionType.DEPOSIT, value, "test"));
		assertNull(transaction.getId());
		assertEquals(wallet, transaction.getWallet());
		assertNotNull(transaction.getNumber());
		assertNotNull(transaction.getDateTime());
		assertEquals(TransactionOperation.CREDIT, transaction.getOperation());
		assertEquals(TransactionType.DEPOSIT, transaction.getType());
		assertEquals(value, transaction.getValue());
		assertEquals("test", transaction.getObservation());
	}

}
