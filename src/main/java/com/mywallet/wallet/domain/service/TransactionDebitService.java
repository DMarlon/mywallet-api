package com.mywallet.wallet.domain.service;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.mywallet.core.domain.exception.AssociatedValueException;
import com.mywallet.wallet.domain.enumerator.TransactionOperation;
import com.mywallet.wallet.domain.exception.InsufficientBalanceException;
import com.mywallet.wallet.domain.model.Transaction;
import com.mywallet.wallet.domain.model.TransactionInformation;
import com.mywallet.wallet.domain.model.TransactionRegister;
import com.mywallet.wallet.domain.model.Wallet;

@Service
public class TransactionDebitService {

	private WalletLockService walletLockService;
	private TransactionRegisterService transactionRegisterService;

	public TransactionDebitService(WalletLockService walletLockService, TransactionRegisterService transactionRegisterService) {
		this.walletLockService = walletLockService;
		this.transactionRegisterService = transactionRegisterService;
	}

	public Transaction debit(TransactionInformation information) {
		if (Objects.isNull(information))
			throw new IllegalArgumentException("The transaction information must be provided!");

		if(!information.getType().isDebitOperation())
			throw new IllegalArgumentException("The transaction type not allow debit!");

		Wallet wallet = walletLockService.findByNumber(information.getWalletNumber()).orElseThrow(() -> new AssociatedValueException("The wallet number provided to debit is not valid!"));
		if (wallet.getBalance() < information.getValue())
			throw new InsufficientBalanceException("The wallet balance is insufficient!");

		Transaction transaction = transactionRegisterService.register(TransactionRegister.valueOf(wallet, TransactionOperation.DEBIT, information.getType(), information.getValue(), information.getObservation()));

		wallet.setBalance(wallet.getBalance() - transaction.getValue());
		walletLockService.updateBalance(wallet);

		return transaction;
	}

}
