package com.mywallet.wallet.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mywallet.core.domain.exception.AssociatedValueException;
import com.mywallet.mock.TransactionMock;
import com.mywallet.mock.WalletMock;
import com.mywallet.wallet.domain.enumerator.TransactionType;
import com.mywallet.wallet.domain.model.Transaction;
import com.mywallet.wallet.domain.model.TransactionInformation;
import com.mywallet.wallet.domain.model.Wallet;

public class TransactionCreditServiceTest {

	@Mock
	private WalletLockService walletLockService;
	@Mock
	private TransactionRegisterService transactionRegisterService;
	private TransactionCreditService transactionCreditService;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		transactionCreditService = new TransactionCreditService(walletLockService, transactionRegisterService);
	}

	@Test
	public void credit_with_null() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> transactionCreditService.credit(null));
		assertEquals("The transaction information must be provided!", exception.getMessage());
	}

	@Test
	public void credit_with_invalid_operation() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> transactionCreditService.credit(TransactionInformation.valueOf(UUID.randomUUID(), TransactionType.WITHDRAW, Long.valueOf(1000), "test")));
		assertEquals("The transaction type not allow credit!", exception.getMessage());
	}

	@Test
	public void credit_with_not_exists_wallet() {
		when(walletLockService.findByNumber(any())).thenReturn(Optional.empty());

		AssociatedValueException exception = assertThrows(AssociatedValueException.class, () -> transactionCreditService.credit(TransactionInformation.valueOf(UUID.randomUUID(), TransactionType.DEPOSIT, Long.valueOf(1000), "test")));
		assertEquals("The wallet number provided to credit is not valid!", exception.getMessage());
	}

	@Test
	public void credit_with_values() {
		Wallet wallet = WalletMock.mock();
		Transaction transaction = TransactionMock.mock();

		when(walletLockService.findByNumber(any())).thenReturn(Optional.of(wallet));
		when(transactionRegisterService.register(any())).thenReturn(transaction);

		transactionCreditService.credit(TransactionInformation.valueOf(wallet.getNumber(), transaction.getType(), transaction.getValue(), transaction.getObservation()));

		ArgumentCaptor<Wallet> walletArgument = ArgumentCaptor.forClass(Wallet.class);
		verify(walletLockService, atLeastOnce()).updateBalance(walletArgument.capture());

		assertEquals(2000, walletArgument.getValue().getBalance());
	}

}
