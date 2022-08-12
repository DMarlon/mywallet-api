package com.mywallet.wallet.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

import com.mywallet.mock.WalletMock;
import com.mywallet.wallet.domain.model.Wallet;
import com.mywallet.wallet.domain.repository.WalletRepository;

public class WalletLockServiceTest {

	@Mock
	private WalletRepository walletRepository;
	private WalletLockService walletLockService;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		walletLockService = new WalletLockService(walletRepository);
	}

	@Test
	public void findByNumber_with_null_number() {
		Optional<Wallet> wallet = walletLockService.findByNumber(null);
		verify(walletRepository, never()).findByNumberWithLock(any());

		assertEquals(wallet, Optional.empty());
	}

	@Test
	public void findByNumber_with_number() {
		walletLockService.findByNumber(UUID.randomUUID());
		verify(walletRepository, atLeastOnce()).findByNumberWithLock(any());
	}

	@Test
	public void updateBalace_with_null_wallet() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> { walletLockService.updateBalance(null); });
		assertEquals("Balance cannot be less than zero!", exception.getMessage());
	}

	@Test
	public void updateBalace_with_balance_less_then_zero() {
		Wallet wallet = WalletMock.mock();
		wallet.setBalance(Long.valueOf(-1));

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> { walletLockService.updateBalance(wallet); });
		assertEquals("Balance cannot be less than zero!", exception.getMessage());
	}

	@Test
	public void updateBalace_with_balance_zero() {
		Wallet wallet = WalletMock.mock();
		wallet.setBalance(Long.valueOf(0));

		walletLockService.updateBalance(wallet);
		verify(walletRepository, atLeastOnce()).save(any());
	}

}
