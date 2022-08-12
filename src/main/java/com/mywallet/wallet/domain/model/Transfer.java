package com.mywallet.wallet.domain.model;

import java.util.Objects;

public class Transfer {

	Transaction withdraw;
	Transaction deposit;

	private Transfer(Transaction withdraw, Transaction deposit) {
		this.withdraw = withdraw;
		this.deposit = deposit;
	}

	public static Transfer valueOf(Transaction withdraw, Transaction deposit) {
		if (Objects.isNull(withdraw) || Objects.isNull(deposit))
			throw new IllegalArgumentException("All information is needed to create a transfer!");

		return new Transfer(withdraw, deposit);
	}

	public Transaction getWithdraw() {
		return withdraw;
	}

	public Transaction getDeposit() {
		return deposit;
	}

}
