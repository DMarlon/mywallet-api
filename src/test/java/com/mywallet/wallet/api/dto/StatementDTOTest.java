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

public class StatementDTOTest {

	@Test
	public void valueOf_with_null() {
		StatementDTO statementDTO = StatementDTO.valueOf(null, null);
		assertNull(statementDTO.getWallet());
		assertNull(statementDTO.getTransactions());
		assertNull(statementDTO.getBalance());
	}

	@Test
	public void valueOf_with_transactions_null() {
		Wallet wallet = WalletMock.mock();

		StatementDTO statementDTO = StatementDTO.valueOf(wallet, null);
		assertEquals(statementDTO.getWallet().getNumber(), wallet.getNumber());
		assertNotNull(statementDTO.getTransactions());
		assertEquals(statementDTO.getTransactions().size(), 0);
		assertEquals(statementDTO.getBalance(), wallet.getBalance());
	}

	@Test
	public void valueOf_with_transactions_empty() {
		Wallet wallet = WalletMock.mock();

		StatementDTO statementDTO = StatementDTO.valueOf(wallet, Collections.emptyList());
		assertEquals(statementDTO.getWallet().getNumber(), wallet.getNumber());
		assertNotNull(statementDTO.getTransactions());
		assertEquals(statementDTO.getTransactions().size(), 0);
		assertEquals(statementDTO.getBalance(), wallet.getBalance());
	}

	@Test
	public void valueOf_with_values() {
		Wallet wallet = WalletMock.mock();
		Transaction transaction =  TransactionMock.mock();

		StatementDTO statementDTO = StatementDTO.valueOf(wallet, List.of(transaction));
		assertEquals(statementDTO.getWallet().getNumber(), wallet.getNumber());
		assertNotNull(statementDTO.getTransactions());
		assertEquals(statementDTO.getTransactions().get(0).getNumber(), transaction.getNumber());
		assertEquals(statementDTO.getBalance(), wallet.getBalance());
	}
}
