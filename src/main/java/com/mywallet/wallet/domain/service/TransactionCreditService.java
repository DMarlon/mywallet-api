package com.mywallet.wallet.domain.service;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.mywallet.core.domain.exception.AssociatedValueException;
import com.mywallet.wallet.domain.enumerator.TransactionOperation;
import com.mywallet.wallet.domain.model.Transaction;
import com.mywallet.wallet.domain.model.TransactionInformation;
import com.mywallet.wallet.domain.model.TransactionRegister;
import com.mywallet.wallet.domain.model.Wallet;

@Service
public class TransactionCreditService {

	private WalletLockService walletLockService;
	private TransactionRegisterService transactionRegisterService;

	public TransactionCreditService(WalletLockService walletLockService, TransactionRegisterService transactionRegisterService) {
		this.walletLockService = walletLockService;
		this.transactionRegisterService = transactionRegisterService;
	}

	public Transaction credit(TransactionInformation information) {
		if (Objects.isNull(information))
			throw new IllegalArgumentException("The transaction information must be provided!");

		if(!information.getType().isCreditOperation())
			throw new IllegalArgumentException("The transaction type not allow credit!");

		Wallet wallet = walletLockService.findByNumber(information.getWalletNumber()).orElseThrow(() -> new AssociatedValueException("The wallet number provided to credit is not valid!"));
		Transaction transaction = transactionRegisterService.register(TransactionRegister.valueOf(wallet, TransactionOperation.CREDIT, information.getType(), information.getValue(), information.getObservation()));

		wallet.setBalance(wallet.getBalance() + transaction.getValue());
		walletLockService.updateBalance(wallet);

		return transaction;
	}

}
