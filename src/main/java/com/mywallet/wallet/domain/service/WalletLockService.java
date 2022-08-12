package com.mywallet.wallet.domain.service;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.mywallet.core.domain.utilitary.ValidatorUtils;
import com.mywallet.wallet.domain.model.Wallet;
import com.mywallet.wallet.domain.repository.WalletRepository;

@Service
public class WalletLockService {

	private WalletRepository walletRepository;

	public WalletLockService(WalletRepository walletRepository) {
		this.walletRepository = walletRepository;
	}

	public Optional<Wallet> findByNumber(UUID walletNumber) {
		if (Objects.isNull(walletNumber))
			return Optional.empty();

		return walletRepository.findByNumberWithLock(walletNumber);
	}

	public Wallet updateBalance(Wallet wallet) {
		if (Objects.isNull(wallet) || ValidatorUtils.isNullOrLessThanZero(wallet.getBalance()))
			throw new IllegalArgumentException("Balance cannot be less than zero!");

		return walletRepository.save(wallet);
	}
}
