package com.mywallet.wallet.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.mywallet.mock.TransactionMock;

public class TransferTest {

	@Test
	public void valueOf_null_values() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Transfer.valueOf(null, null));
		assertEquals("All information is needed to create a transfer!", exception.getMessage());
	}

	@Test
	public void valueOf_withdraw_null() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Transfer.valueOf(null, new Transaction()));
		assertEquals("All information is needed to create a transfer!", exception.getMessage());
	}

	@Test
	public void valueOf_deposit_null() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Transfer.valueOf(new Transaction(), null));
		assertEquals("All information is needed to create a transfer!", exception.getMessage());
	}

	@Test
	public void valueOf_create() {
		Transaction withdraw = TransactionMock.mock();
		Transaction deposit = TransactionMock.mock();

		Transfer transfer = Transfer.valueOf(withdraw, deposit);
		assertEquals(transfer.getWithdraw(), withdraw);
		assertEquals(transfer.getDeposit(), deposit);
	}



}
