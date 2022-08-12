package com.mywallet.wallet.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mywallet.wallet.domain.model.Wallet;
import com.mywallet.wallet.domain.repository.WalletRepository;

public class WalletListServiceTest {

	@Mock
	private WalletRepository walletRepository;
	private WalletListService walletListService;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		walletListService = new WalletListService(walletRepository);
	}

	@Test
	public void findByNumber_with_null_number() {
		Optional<Wallet> wallet = walletListService.findByNumber(null);
		verify(walletRepository, never()).findByNumber(any());

		assertEquals(wallet, Optional.empty());
	}

	@Test
	public void findByNumber_with_number() {
		walletListService.findByNumber(UUID.randomUUID());
		verify(walletRepository, atLeastOnce()).findByNumber(any());
	}

}
