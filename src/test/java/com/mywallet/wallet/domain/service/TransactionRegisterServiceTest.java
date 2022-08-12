package com.mywallet.wallet.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mywallet.core.domain.exception.RequiredFieldException;
import com.mywallet.mock.WalletMock;
import com.mywallet.wallet.domain.enumerator.TransactionOperation;
import com.mywallet.wallet.domain.enumerator.TransactionType;
import com.mywallet.wallet.domain.model.TransactionRegister;
import com.mywallet.wallet.domain.repository.TransactionRepository;

public class TransactionRegisterServiceTest {

	@Mock
	private TransactionRepository transactionRepository;
	private TransactionRegisterService transactionRegisterService;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		transactionRegisterService = new TransactionRegisterService(transactionRepository);
	}

	@Test
	public void register_with_null() {
		RequiredFieldException exception = assertThrows(RequiredFieldException.class, () -> transactionRegisterService.register(null));
		assertEquals("The transaction register must be provided!", exception.getMessage());
	}

	@Test
	public void register_with_values() {
		transactionRegisterService.register(TransactionRegister.valueOf(WalletMock.mock(), TransactionOperation.CREDIT, TransactionType.DEPOSIT, Long.valueOf(1000), null));
		verify(transactionRepository, atLeastOnce()).save(any());
	}
}
