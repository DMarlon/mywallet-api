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
import com.mywallet.wallet.domain.exception.InsufficientBalanceException;
import com.mywallet.wallet.domain.model.Transaction;
import com.mywallet.wallet.domain.model.TransactionInformation;
import com.mywallet.wallet.domain.model.Wallet;

public class TransactionDebitServiceTest {

	@Mock
	private WalletLockService walletLockService;
	@Mock
	private TransactionRegisterService transactionRegisterService;
	private TransactionDebitService transactionDebitService;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		transactionDebitService = new TransactionDebitService(walletLockService, transactionRegisterService);
	}

	@Test
	public void debit_with_null() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> transactionDebitService.debit(null));
		assertEquals("The transaction information must be provided!", exception.getMessage());
	}

	@Test
	public void debit_with_invalid_operation() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> transactionDebitService.debit(TransactionInformation.valueOf(UUID.randomUUID(), TransactionType.DEPOSIT, Long.valueOf(1000), "test")));
		assertEquals("The transaction type not allow debit!", exception.getMessage());
	}

	@Test
	public void debit_with_not_exists_wallet() {
		when(walletLockService.findByNumber(any())).thenReturn(Optional.empty());

		AssociatedValueException exception = assertThrows(AssociatedValueException.class, () -> transactionDebitService.debit(TransactionInformation.valueOf(UUID.randomUUID(), TransactionType.WITHDRAW, Long.valueOf(1000), "test")));
		assertEquals("The wallet number provided to debit is not valid!", exception.getMessage());
	}

	@Test
	public void debit_with_insufficient_balance() {
		Wallet wallet = WalletMock.mock();
		wallet.setBalance(Long.valueOf(1000));

		when(walletLockService.findByNumber(any())).thenReturn(Optional.of(wallet));

		InsufficientBalanceException exception = assertThrows(InsufficientBalanceException.class, () -> transactionDebitService.debit(TransactionInformation.valueOf(wallet.getNumber(), TransactionType.WITHDRAW, Long.valueOf(2000), "test")));
		assertEquals("The wallet balance is insufficient!", exception.getMessage());
	}

	@Test
	public void debit_with_values() {
		Wallet wallet = WalletMock.mock();
		Transaction transaction = TransactionMock.mock();
		transaction.setType(TransactionType.WITHDRAW);

		when(walletLockService.findByNumber(any())).thenReturn(Optional.of(wallet));
		when(transactionRegisterService.register(any())).thenReturn(transaction);

		transactionDebitService.debit(TransactionInformation.valueOf(wallet.getNumber(), transaction.getType(), transaction.getValue(), transaction.getObservation()));

		ArgumentCaptor<Wallet> walletArgument = ArgumentCaptor.forClass(Wallet.class);
		verify(walletLockService, atLeastOnce()).updateBalance(walletArgument.capture());

		assertEquals(0, walletArgument.getValue().getBalance());
	}

}
