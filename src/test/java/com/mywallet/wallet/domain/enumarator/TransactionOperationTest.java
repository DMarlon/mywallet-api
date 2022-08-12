package com.mywallet.wallet.domain.enumarator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.mywallet.wallet.domain.enumerator.TransactionOperation;

public class TransactionOperationTest {

	@Test
	public void getCode_credit() {
		assertEquals(1, TransactionOperation.CREDIT.getCode());
	}

	@Test
	public void getDescription_credit() {
		assertEquals("Credit", TransactionOperation.CREDIT.getDescription());
	}

	@Test
	public void getCode_debit() {
		assertEquals(2, TransactionOperation.DEBIT.getCode());
	}

	@Test
	public void getDescription_debit() {
		assertEquals("Debit", TransactionOperation.DEBIT.getDescription());
	}


	@Test
	public void getTransactionOperation_invalid_code() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> TransactionOperation.getTransactionOperation(10));
		assertEquals("10 is not a valid transaction operation", exception.getMessage());
	}
}
