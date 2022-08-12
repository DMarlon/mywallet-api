package com.mywallet.wallet.api.dto;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.mywallet.core.domain.utilitary.ValidatorUtils;
import com.mywallet.wallet.domain.model.Transaction;
import com.mywallet.wallet.domain.model.Wallet;

public class TransactionTicketDTO {

	private WalletDTO wallet;
	private List<TransactionDetailDTO> transactions;
	private Long balance;

	public static TransactionTicketDTO valueOf(Wallet wallet, List<Transaction> transactions) {
		TransactionTicketDTO balanceTicketDTO = new TransactionTicketDTO();

		if (Objects.nonNull(wallet)) {
			balanceTicketDTO.setWallet(WalletDTO.valueOf(wallet));
			balanceTicketDTO.setBalance(wallet.getBalance());
			balanceTicketDTO.setTransactions(ValidatorUtils.isNullOrEmpty(transactions) ? Collections.emptyList() : transactions.stream().map(TransactionDetailDTO::valueOf).collect(Collectors.toList()));
		}

		return balanceTicketDTO;
	}

	public WalletDTO getWallet() {
		return wallet;
	}

	public void setWallet(WalletDTO wallet) {
		this.wallet = wallet;
	}

	public List<TransactionDetailDTO> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<TransactionDetailDTO> transactions) {
		this.transactions = transactions;
	}

	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}

}
