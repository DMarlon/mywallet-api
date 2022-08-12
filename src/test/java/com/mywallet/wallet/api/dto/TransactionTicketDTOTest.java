package com.mywallet.wallet.api.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.mywallet.mock.TransactionMock;
import com.mywallet.mock.WalletMock;
import com.mywallet.wallet.domain.model.Transaction;
import com.mywallet.wallet.domain.model.Wallet;

public class TransactionTicketDTOTest {

	@Test
	public void valueOf_with_null() {
		TransactionTicketDTO transactionTicketDTO = TransactionTicketDTO.valueOf(null, null);
		assertNull(transactionTicketDTO.getWallet());
		assertNull(transactionTicketDTO.getTransactions());
		assertNull(transactionTicketDTO.getBalance());
	}

	@Test
	public void valueOf_with_transactions_null() {
		Wallet wallet = WalletMock.mock();

		TransactionTicketDTO transactionTicketDTO = TransactionTicketDTO.valueOf(wallet, null);
		assertEquals(transactionTicketDTO.getWallet().getNumber(), wallet.getNumber());
		assertNotNull(transactionTicketDTO.getTransactions());
		assertEquals(transactionTicketDTO.getTransactions().size(), 0);
		assertEquals(transactionTicketDTO.getBalance(), wallet.getBalance());
	}

	@Test
	public void valueOf_with_transactions_empty() {
		Wallet wallet = WalletMock.mock();

		TransactionTicketDTO transactionTicketDTO = TransactionTicketDTO.valueOf(wallet, Collections.emptyList());
		assertEquals(transactionTicketDTO.getWallet().getNumber(), wallet.getNumber());
		assertNotNull(transactionTicketDTO.getTransactions());
		assertEquals(transactionTicketDTO.getTransactions().size(), 0);
		assertEquals(transactionTicketDTO.getBalance(), wallet.getBalance());
	}

	@Test
	public void valueOf_with_values() {
		Wallet wallet = WalletMock.mock();
		Transaction transaction =  TransactionMock.mock();

		TransactionTicketDTO transactionTicketDTO = TransactionTicketDTO.valueOf(wallet, List.of(transaction));
		assertEquals(transactionTicketDTO.getWallet().getNumber(), wallet.getNumber());
		assertNotNull(transactionTicketDTO.getTransactions());
		assertEquals(transactionTicketDTO.getTransactions().get(0).getNumber(), transaction.getNumber());
		assertEquals(transactionTicketDTO.getBalance(), wallet.getBalance());
	}
}
