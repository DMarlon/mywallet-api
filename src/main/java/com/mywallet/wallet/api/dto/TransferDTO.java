package com.mywallet.wallet.api.dto;

import java.util.UUID;

public class TransferDTO {

	private UUID fromWalletNumber;
	private UUID toWalletNumber;
	private Long value;

	public UUID getFromWalletNumber() {
		return fromWalletNumber;
	}

	public void setFromWalletNumber(UUID fromWalletNumber) {
		this.fromWalletNumber = fromWalletNumber;
	}

	public UUID getToWalletNumber() {
		return toWalletNumber;
	}

	public void setToWalletNumber(UUID toWalletNumber) {
		this.toWalletNumber = toWalletNumber;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

}
