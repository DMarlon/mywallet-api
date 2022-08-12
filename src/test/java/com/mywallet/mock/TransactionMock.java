package com.mywallet.mock;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mywallet.wallet.domain.enumerator.TransactionOperation;
import com.mywallet.wallet.domain.enumerator.TransactionType;
import com.mywallet.wallet.domain.model.Transaction;
import com.mywallet.wallet.domain.model.Wallet;

public class TransactionMock {
	private static final Long IDMOCK = Long.valueOf(99999);
	private static final Wallet WALLET = WalletMock.mock();
	private static final UUID NUMBER = UUID.randomUUID();
	private static final LocalDateTime DATETIME = LocalDateTime.of(2022, 8, 11, 02, 06);
	private static final TransactionOperation OPERATION = TransactionOperation.CREDIT;
	private static final TransactionType TYPE = TransactionType.DEPOSIT;
	private static final Long VALUE = Long.valueOf(1000);
	private static final String OBSERVATION = "observation";


	public static Transaction mock() {
		Transaction transaction = new Transaction();
		transaction.setId(IDMOCK);
		transaction.setWallet(WALLET);
		transaction.setNumber(NUMBER);
		transaction.setDateTime(DATETIME);
		transaction.setOperation(OPERATION);
		transaction.setType(TYPE);
		transaction.setValue(VALUE);
		transaction.setObservation(OBSERVATION);

		return transaction;
	}
}
