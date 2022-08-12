package com.mywallet.wallet.domain.model;

import javax.persistence.AttributeConverter;

import com.mywallet.wallet.domain.enumerator.TransactionType;

public class TransactionTypeConvert implements AttributeConverter<TransactionType, Integer> {

	@Override
	public Integer convertToDatabaseColumn(TransactionType type) {
		return type.getCode();
	}

	@Override
	public TransactionType convertToEntityAttribute(Integer type) {
		return TransactionType.getTransactionType(type);
	}

}
