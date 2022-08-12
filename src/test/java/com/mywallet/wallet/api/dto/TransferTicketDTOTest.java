package com.mywallet.wallet.api.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.mywallet.mock.TransactionMock;
import com.mywallet.wallet.domain.model.Transaction;
import com.mywallet.wallet.domain.model.Transfer;

public class TransferTicketDTOTest {

	@Test
	public void valueOf_with_null() {
		TransferTicketDTO transferTicketDTO = TransferTicketDTO.valueOf(null);
		assertNull(transferTicketDTO.getFrom());
		assertNull(transferTicketDTO.getTo());
	}

	@Test
	public void valueOf_with_values() {
		Transaction withdraw = TransactionMock.mock();
		Transaction deposit = TransactionMock.mock();

		TransferTicketDTO transferTicketDTO = TransferTicketDTO.valueOf(Transfer.valueOf(withdraw, deposit));

		assertEquals(transferTicketDTO.getFrom().getNumber(), withdraw.getNumber());
		assertEquals(transferTicketDTO.getTo().getNumber(), deposit.getNumber());
	}

}
