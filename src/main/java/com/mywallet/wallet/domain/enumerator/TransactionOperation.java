package com.mywallet.wallet.domain.enumerator;

public enum TransactionOperation {
	CREDIT(1, "Credit"),
	DEBIT(2, "Debit");

	private int code;
	private String description;

	private TransactionOperation(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public int getCode() {
		return this.code;
	}

	public String getDescription() {
		return this.description;
	}

	@Override
	public String toString() {
		return this.getDescription();
	}

	public static TransactionOperation getTransactionOperation(int operation) {
        for(TransactionOperation transactionOperation : values()) {
            if(Integer.valueOf(transactionOperation.getCode()).equals(operation))
            	return transactionOperation;
        }

        throw new IllegalArgumentException(operation+" is not a valid transaction operation");
    }

}
