package com.mywallet.wallet.api.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.mywallet.mock.TransactionMock;
import com.mywallet.wallet.domain.model.Transaction;

public class TransactionDetailDTOTest {

	@Test
	public void valueOf_with_null() {
		TransactionDetailDTO transactionDTO = TransactionDetailDTO.valueOf(null);
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

		TransactionDetailDTO transactionDTO = TransactionDetailDTO.valueOf(transaction);
		assertEquals(transactionDTO.getNumber(), transaction.getNumber());
		assertEquals(transactionDTO.getDateTime(), transaction.getDateTime());
		assertEquals(transactionDTO.getOperation(), transaction.getOperation());
		assertEquals(transactionDTO.getType(), transaction.getType());
		assertEquals(transactionDTO.getValue(), transaction.getValue());
		assertEquals(transactionDTO.getObservation(), transaction.getObservation());
	}
}
