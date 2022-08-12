package com.mywallet.wallet.api.dto;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import com.mywallet.wallet.domain.enumerator.TransactionOperation;
import com.mywallet.wallet.domain.enumerator.TransactionType;
import com.mywallet.wallet.domain.model.Transaction;

public class TransactionDTO {

	private WalletDTO wallet;
	private UUID number;
	private LocalDateTime dateTime;
	private TransactionOperation operation;
	private TransactionType type;
	private Long value;
	private String observation;

	public static TransactionDTO valueOf(Transaction transaction) {
		TransactionDTO transactionDTO = new TransactionDTO();

		if (Objects.nonNull(transaction)) {
			transactionDTO.setWallet(WalletDTO.valueOf(transaction.getWallet()));
			transactionDTO.setNumber(transaction.getNumber());
			transactionDTO.setDateTime(transaction.getDateTime());
			transactionDTO.setOperation(transaction.getOperation());
			transactionDTO.setType(transaction.getType());
			transactionDTO.setValue(transaction.getValue());
			transactionDTO.setObservation(transaction.getObservation());
		}

		return transactionDTO;
	}

	public WalletDTO getWallet() {
		return wallet;
	}

	public void setWallet(WalletDTO wallet) {
		this.wallet = wallet;
	}

	public UUID getNumber() {
		return number;
	}

	public void setNumber(UUID number) {
		this.number = number;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public TransactionOperation getOperation() {
		return operation;
	}

	public void setOperation(TransactionOperation operation) {
		this.operation = operation;
	}

	public TransactionType getType() {
		return type;
	}

	public void setType(TransactionType type) {
		this.type = type;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

}
