package com.mywallet.wallet.domain.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mywallet.wallet.domain.model.Transaction;

public interface TransactionRepository  extends JpaRepository<Transaction, Long> {

	List<Transaction> findAllByWalletNumber(UUID walletNumber);
}
