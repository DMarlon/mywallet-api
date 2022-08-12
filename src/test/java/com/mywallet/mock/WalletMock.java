package com.mywallet.mock;

import java.util.UUID;

import com.mywallet.person.domain.model.Person;
import com.mywallet.wallet.domain.model.Wallet;

public class WalletMock {
	private static final Long IDMOCK = Long.valueOf(99999);
	private static final Person PERSON = PersonMock.mock();
	private static final UUID NUMBER = UUID.randomUUID();
	private static final Long BALANCE = Long.valueOf(1000);

	public static Wallet mock() {
		Wallet wallet = new Wallet();
		wallet.setId(IDMOCK);
		wallet.setPerson(PERSON);
		wallet.setNumber(NUMBER);
		wallet.setBalance(BALANCE);

		return wallet;
	}
}
