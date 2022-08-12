package com.mywallet.wallet.api.dto;

import java.util.Objects;

import com.mywallet.wallet.domain.model.Wallet;

public class BalanceTicketDTO {

	private WalletDTO wallet;
	private Long balance;

	public static BalanceTicketDTO valueOf(Wallet wallet) {
		BalanceTicketDTO balanceTicketDTO = new BalanceTicketDTO();

		if (Objects.nonNull(wallet)) {
			balanceTicketDTO.setWallet(WalletDTO.valueOf(wallet));
			balanceTicketDTO.setBalance(wallet.getBalance());
		}

		return balanceTicketDTO;
	}

	public WalletDTO getWallet() {
		return wallet;
	}

	public void setWallet(WalletDTO wallet) {
		this.wallet = wallet;
	}

	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}

}
