package com.mywallet.wallet.domain.model;

import java.util.Objects;

import com.mywallet.core.domain.utilitary.ValidatorUtils;
import com.mywallet.wallet.domain.enumerator.TransactionOperation;
import com.mywallet.wallet.domain.enumerator.TransactionType;

public class TransactionRegister {

	private Wallet wallet;
	private TransactionOperation operation;
	private TransactionType type;
	private Long value;
	private String observation;

	private TransactionRegister(Wallet wallet, TransactionOperation operation, TransactionType type, Long value, String observation) {
		this.wallet = wallet;
		this.operation = operation;
		this.type = type;
		this.value = value;
		this.observation = observation;
	}

	public static TransactionRegister valueOf(Wallet wallet, TransactionOperation operation, TransactionType type, Long value, String observation) {
		if (Objects.isNull(wallet) || Objects.isNull(operation) || Objects.isNull(type) || ValidatorUtils.isNullOrLessThanOne(value))
			throw new IllegalArgumentException("All information is needed to create a transactional register!");

		return new TransactionRegister(wallet, operation, type, value, observation);
	}

	public Wallet getWallet() {
		return wallet;
	}

	public TransactionOperation getOperation() {
		return operation;
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
