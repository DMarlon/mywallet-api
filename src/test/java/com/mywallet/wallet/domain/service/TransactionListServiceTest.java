package com.mywallet.wallet.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mywallet.wallet.domain.model.Transaction;
import com.mywallet.wallet.domain.repository.TransactionRepository;

public class TransactionListServiceTest {

	@Mock
	private TransactionRepository transactionRepository;
	private TransactionListService transactionListService;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		transactionListService = new TransactionListService(transactionRepository);
	}

	@Test
	public void findByNumber_with_null_number() {
		List<Transaction> transactions = transactionListService.findAllByWalletNumber(null);
		verify(transactionRepository, never()).findAllByWalletNumber(any());

		assertEquals(transactions, Collections.emptyList());
	}

	@Test
	public void findByNumber_with_number() {
		transactionListService.findAllByWalletNumber(UUID.randomUUID());
		verify(transactionRepository, atLeastOnce()).findAllByWalletNumber(any());
	}
}
