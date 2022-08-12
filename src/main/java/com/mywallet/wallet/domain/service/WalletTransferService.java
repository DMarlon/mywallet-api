package com.mywallet.wallet.domain.service;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.mywallet.wallet.domain.enumerator.TransactionType;
import com.mywallet.wallet.domain.model.Transaction;
import com.mywallet.wallet.domain.model.TransactionInformation;
import com.mywallet.wallet.domain.model.Transfer;

@Service
public class WalletTransferService {

	private TransactionDebitService transactionDebitService;
	private TransactionCreditService transactionCreditService;

	public WalletTransferService(TransactionDebitService transactionDebitService, TransactionCreditService transactionCreditService) {
		this.transactionDebitService = transactionDebitService;
		this.transactionCreditService = transactionCreditService;
	}

	@Transactional
	public Transfer transfer(UUID fromWalletNumber, UUID toWalletNumber, Long value) {
		Transaction withdraw = transactionDebitService.debit(TransactionInformation.valueOf(fromWalletNumber, TransactionType.TRANSFER, value, "to wallet: " + toWalletNumber));
		Transaction deposit = transactionCreditService.credit(TransactionInformation.valueOf(toWalletNumber,TransactionType.TRANSFER, value, "from wallet: " + fromWalletNumber));

		return Transfer.valueOf(withdraw, deposit);
	}

}
