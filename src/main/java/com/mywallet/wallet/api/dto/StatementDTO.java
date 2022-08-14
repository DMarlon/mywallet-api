package com.mywallet.wallet.api.dto;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.mywallet.core.domain.utilitary.ValidatorUtils;
import com.mywallet.wallet.domain.model.Transaction;
import com.mywallet.wallet.domain.model.Wallet;

public class StatementDTO {

	private WalletDTO wallet;
	private List<TransactionTicketDTO> transactions;
	private Long balance;

	public static StatementDTO valueOf(Wallet wallet, List<Transaction> transactions) {
		StatementDTO balanceTicketDTO = new StatementDTO();

		if (Objects.nonNull(wallet)) {
			balanceTicketDTO.setWallet(WalletDTO.valueOf(wallet));
			balanceTicketDTO.setBalance(wallet.getBalance());
			balanceTicketDTO.setTransactions(ValidatorUtils.isNullOrEmpty(transactions) ? Collections.emptyList() : transactions.stream().map(TransactionTicketDTO::valueOf).collect(Collectors.toList()));
		}

		return balanceTicketDTO;
	}

	public WalletDTO getWallet() {
		return wallet;
	}

	public void setWallet(WalletDTO wallet) {
		this.wallet = wallet;
	}

	public List<TransactionTicketDTO> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<TransactionTicketDTO> transactions) {
		this.transactions = transactions;
	}

	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}

}
