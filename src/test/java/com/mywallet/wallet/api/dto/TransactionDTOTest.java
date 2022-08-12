package com.mywallet.wallet.api.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.mywallet.mock.TransactionMock;
import com.mywallet.wallet.domain.model.Transaction;

public class TransactionDTOTest {

	@Test
	public void valueOf_with_null() {
		TransactionDTO transactionDTO = TransactionDTO.valueOf(null);
		assertNull(transactionDTO.getWallet());
		assertNull(transactionDTO.getNumber());
		assertNull(transactionDTO.getDateTime());
		assertNull(transactionDTO.getOperation());
		assertNull(transactionDTO.getType());
		assertNull(transactionDTO.getValue());
		assertNull(transactionDTO.getObservation());
	}

	@Test
	public void valueOf_with_values() {
		Transaction transaction = TransactionMock.mock();

		TransactionDTO transactionDTO = TransactionDTO.valueOf(transaction);
		assertEquals(transactionDTO.getWallet().getNumber(), transaction.getWallet().getNumber());
		assertEquals(transactionDTO.getNumber(), transaction.getNumber());
		assertEquals(transactionDTO.getDateTime(), transaction.getDateTime());
		assertEquals(transactionDTO.getOperation(), transaction.getOperation());
		assertEquals(transactionDTO.getType(), transaction.getType());
		assertEquals(transactionDTO.getValue(), transaction.getValue());
		assertEquals(transactionDTO.getObservation(), transaction.getObservation());
	}
}
