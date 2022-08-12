package com.mywallet.wallet.domain.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.mywallet.wallet.domain.enumerator.TransactionOperation;
import com.mywallet.wallet.domain.enumerator.TransactionType;

@Entity
@Table(name = "transaction")
public class Transaction {

	@Id
	@Column(name = "tra_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tra_wallet_id", nullable = false)
	private Wallet wallet;

	@Column(name = "tra_number", nullable = false)
	private UUID number;

	@Column(name = "tra_datetime", nullable = false)
	private LocalDateTime dateTime;

	@Column(name = "tra_operation", nullable = false)
	@Convert(converter = TransactionOperationConvert.class)
	private TransactionOperation operation;

	@Column(name = "tra_type", nullable = false)
	@Convert(converter = TransactionTypeConvert.class)
	private TransactionType type;

	@Column(name = "tra_value", nullable = false)
	private Long value;

	@Column(name = "tra_observation", length = 255)
	private String observation;

	public static Transaction valueOf(TransactionRegister transactionOperation) {
		if (Objects.isNull(transactionOperation))
			throw new IllegalArgumentException("Transaction register is needed to create a transaction!");

		Transaction transaction = new Transaction();
		transaction.setId(null);
		transaction.setWallet(transactionOperation.getWallet());
		transaction.setNumber(UUID.randomUUID());
		transaction.setDateTime(LocalDateTime.now());
		transaction.setOperation(transactionOperation.getOperation());
		transaction.setType(transactionOperation.getType());
		transaction.setValue(transactionOperation.getValue());
		transaction.setObservation(transactionOperation.getObservation());

		return transaction;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
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
		this.observation = Objects.isNull(observation) ? null : observation.trim();
	}

}
