package com.mywallet.wallet.domain.service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.mywallet.wallet.domain.model.Transaction;
import com.mywallet.wallet.domain.repository.TransactionRepository;

@Service
public class TransactionListService {

	private TransactionRepository transactionRepository;

	public TransactionListService(TransactionRepository transactionRepository) {
		this.transactionRepository = transactionRepository;
	}

	public List<Transaction> findAllByWalletNumber(UUID number) {
		if (Objects.isNull(number))
			return Collections.emptyList();

		return transactionRepository.findAllByWalletNumber(number);
	}

}
