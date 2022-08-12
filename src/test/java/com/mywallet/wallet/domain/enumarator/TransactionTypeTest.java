package com.mywallet.wallet.domain.enumarator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.mywallet.wallet.domain.enumerator.TransactionType;

public class TransactionTypeTest {

	@Test
	public void getCode_deposit() {
		assertEquals(1, TransactionType.DEPOSIT.getCode());
	}

	@Test
	public void getDescription_deposit() {
		assertEquals("Deposit", TransactionType.DEPOSIT.getDescription());
	}

	@Test
	public void getCode_debit() {
		assertEquals(2, TransactionType.WITHDRAW.getCode());
	}

	@Test
	public void getDescription_debit() {
		assertEquals("Withdraw", TransactionType.WITHDRAW.getDescription());
	}

	@Test
	public void getCode_transfer() {
		assertEquals(3, TransactionType.TRANSFER.getCode());
	}

	@Test
	public void getDescription_transfer() {
		assertEquals("Transfer", TransactionType.TRANSFER.getDescription());
	}

	@Test
	public void getTransactionType_invalid_code() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> TransactionType.getTransactionType(10));
		assertEquals("10 is not a valid transaction type", exception.getMessage());
	}
}
