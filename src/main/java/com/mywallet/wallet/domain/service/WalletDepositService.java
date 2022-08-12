package com.mywallet.wallet.domain.service;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.mywallet.wallet.domain.enumerator.TransactionType;
import com.mywallet.wallet.domain.model.Transaction;
import com.mywallet.wallet.domain.model.TransactionInformation;

@Service
public class WalletDepositService {

	private TransactionCreditService walletDepositService;

	public WalletDepositService(TransactionCreditService walletDepositService) {
		this.walletDepositService = walletDepositService;
	}

	@Transactional
	public Transaction deposit(UUID walletNumber, Long value) {
		return walletDepositService.credit(TransactionInformation.valueOf(walletNumber, TransactionType.DEPOSIT, value, null));
	}

}
