package com.mywallet.wallet.domain.model;

import java.util.Objects;
import java.util.UUID;

import com.mywallet.core.domain.utilitary.ValidatorUtils;
import com.mywallet.wallet.domain.enumerator.TransactionType;

public class TransactionInformation {

	private UUID walletNumber;
	private TransactionType type;
	private Long value;
	private String observation;

	private TransactionInformation(UUID walletNumber, TransactionType type, Long value, String observation) {
		this.walletNumber = walletNumber;
		this.type = type;
		this.value = value;
		this.observation = observation;
	}

	public static TransactionInformation valueOf(UUID walletNumber, TransactionType type, Long value, String observation) {
		if (Objects.isNull(walletNumber) || Objects.isNull(type) || ValidatorUtils.isNullOrLessThanOne(value))
			throw new IllegalArgumentException("All information is needed to create a transactional information!");

		return new TransactionInformation(walletNumber, type, value, observation);
	}

	public UUID getWalletNumber() {
		return walletNumber;
	}

	public TransactionType getType() {
		return type;
	}

	public Long getValue() {
		return value;
	}

	public String getObservation() {
		return observation;
	}

}
