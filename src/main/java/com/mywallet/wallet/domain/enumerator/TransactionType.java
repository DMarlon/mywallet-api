package com.mywallet.wallet.domain.enumerator;

public enum TransactionType {
	DEPOSIT(1, "Deposit"),
	WITHDRAW(2, "Withdraw"),
	TRANSFER(3, "Transfer");

	private int code;
	private String description;

	private TransactionType(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public int getCode() {
		return this.code;
	}

	public String getDescription() {
		return this.description;
	}

	public boolean isCreditOperation() {
		return this.equals(DEPOSIT) || this.equals(TRANSFER);
	}

	public boolean isDebitOperation() {
		return this.equals(WITHDRAW) || this.equals(TRANSFER);
	}

	@Override
	public String toString() {
		return this.getDescription();
	}

	public static TransactionType getTransactionType(int type) {
        for(TransactionType transactionType : values()) {
            if(Integer.valueOf(transactionType.getCode()).equals(type))
            	return transactionType;
        }

        throw new IllegalArgumentException(type+" is not a valid transaction type");
    }

}
