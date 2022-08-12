package com.mywallet.wallet.api.dto;

import java.util.UUID;

public class WithdrawDTO {

	private UUID walletNumber;
	private Long value;

	public UUID getWalletNumber() {
		return walletNumber;
	}

	public void setWalletNumber(UUID walletNumber) {
		this.walletNumber = walletNumber;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

}
