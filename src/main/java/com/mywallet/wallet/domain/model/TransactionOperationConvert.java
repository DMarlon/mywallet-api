package com.mywallet.wallet.domain.model;

import javax.persistence.AttributeConverter;

import com.mywallet.wallet.domain.enumerator.TransactionOperation;

public class TransactionOperationConvert implements AttributeConverter<TransactionOperation, Integer> {

	@Override
	public Integer convertToDatabaseColumn(TransactionOperation operation) {
		return operation.getCode();
	}

	@Override
	public TransactionOperation convertToEntityAttribute(Integer operation) {
		return TransactionOperation.getTransactionOperation(operation);
	}

}
