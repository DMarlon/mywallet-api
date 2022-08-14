package com.mywallet.wallet.api.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.mywallet.mock.TransactionMock;
import com.mywallet.wallet.domain.model.Transaction;

public class TransactionTicketDTOTest {

	@Test
	public void valueOf_with_null() {
		TransactionTicketDTO transactionTicketDTO = TransactionTicketDTO.valueOf(null);
		assertNull(transactionTicketDTO.getNumber());
		assertNull(transactionTicketDTO.getDateTime());
		assertNull(transactionTicketDTO.getOperation());
		assertNull(transactionTicketDTO.getType());
		assertNull(transactionTicketDTO.getValue());
		assertNull(transactionTicketDTO.getObservation());
	}

	@Test
	public void valueOf_with_values() {
		Transaction transaction = TransactionMock.mock();

		TransactionTicketDTO transactionTicketDTO = TransactionTicketDTO.valueOf(transaction);
		assertEquals(transactionTicketDTO.getNumber(), transaction.getNumber());
		assertEquals(transactionTicketDTO.getDateTime(), transaction.getDateTime());
		assertEquals(transactionTicketDTO.getOperation(), transaction.getOperation());
		assertEquals(transactionTicketDTO.getType(), transaction.getType());
		assertEquals(transactionTicketDTO.getValue(), transaction.getValue());
		assertEquals(transactionTicketDTO.getObservation(), transaction.getObservation());
	}
}
