package com.mywallet.wallet.api.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.mywallet.mock.WalletMock;
import com.mywallet.wallet.domain.model.Wallet;

public class BalanceTicketDTOTest {

	@Test
	public void valueOf_with_null() {
		BalanceTicketDTO balanceTicketDTO = BalanceTicketDTO.valueOf(null);
		assertNull(balanceTicketDTO.getWallet());
		assertNull(balanceTicketDTO.getBalance());
	}

	@Test
	public void valueOf_with_values() {
		Wallet wallet = WalletMock.mock();

		BalanceTicketDTO balanceTicketDTO = BalanceTicketDTO.valueOf(wallet);
		assertEquals(balanceTicketDTO.getWallet().getNumber(), wallet.getNumber());
		assertEquals(balanceTicketDTO.getBalance(), wallet.getBalance());
	}
}
