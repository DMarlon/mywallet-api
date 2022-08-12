package com.mywallet.wallet.domain.service;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.mywallet.wallet.domain.model.Wallet;
import com.mywallet.wallet.domain.repository.WalletRepository;

@Service
public class WalletListService {

	private WalletRepository walletRepository;

	public WalletListService(WalletRepository walletRepository) {
		this.walletRepository = walletRepository;
	}

	public Optional<Wallet> findByNumber(UUID number) {
		if (Objects.isNull(number))
			return Optional.empty();

		return walletRepository.findByNumber(number);
	}

}
