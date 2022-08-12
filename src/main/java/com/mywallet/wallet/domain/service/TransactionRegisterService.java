package com.mywallet.wallet.domain.service;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.mywallet.core.domain.exception.RequiredFieldException;
import com.mywallet.wallet.domain.model.Transaction;
import com.mywallet.wallet.domain.model.TransactionRegister;
import com.mywallet.wallet.domain.repository.TransactionRepository;

@Service
public class TransactionRegisterService {

	private TransactionRepository transactionRepository;

	public TransactionRegisterService(TransactionRepository transactionRepository) {
		this.transactionRepository = transactionRepository;
	}

	public Transaction register(TransactionRegister transactionRegister) {
		checkRequiredFields(transactionRegister);

		return this.transactionRepository.save(Transaction.valueOf(transactionRegister));
	}

	private void checkRequiredFields(TransactionRegister transactionRegister) {
		if (Objects.isNull(transactionRegister))
			throw new RequiredFieldException("The transaction register must be provided!");
	}

}
