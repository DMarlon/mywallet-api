package com.mywallet.wallet.domain.service;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.mywallet.wallet.domain.enumerator.TransactionType;
import com.mywallet.wallet.domain.model.Transaction;
import com.mywallet.wallet.domain.model.TransactionInformation;

@Service
public class WalletWithdrawService {

	private TransactionDebitService walletWithdrawService;

	public WalletWithdrawService(TransactionDebitService walletWithdrawService) {
		this.walletWithdrawService = walletWithdrawService;
	}

	@Transactional
	public Transaction withdraw(UUID walletNumber, Long value) {
		return walletWithdrawService.debit(TransactionInformation.valueOf(walletNumber, TransactionType.WITHDRAW, value, null));
	}

}
