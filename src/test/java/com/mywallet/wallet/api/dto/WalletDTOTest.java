package com.mywallet.wallet.api.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.mywallet.mock.WalletMock;
import com.mywallet.wallet.domain.model.Wallet;

public class WalletDTOTest {

	@Test
	public void valueOf_with_null() {
		WalletDTO walletDTO = WalletDTO.valueOf(null);
		assertNull(walletDTO.getId());
		assertNull(walletDTO.getPerson());
		assertNull(walletDTO.getNumber());
	}

	@Test
	public void valueOf_with_values() {
		Wallet wallet = WalletMock.mock();

		WalletDTO walletDTO = WalletDTO.valueOf(wallet);
		assertEquals(walletDTO.getId(), wallet.getId());
		assertEquals(walletDTO.getPerson().getId(), wallet.getPerson().getId());
		assertEquals(walletDTO.getNumber(), wallet.getNumber());
	}

	@Test
	public void toEntity_with_empty() {
		WalletDTO walletDTO = new WalletDTO();
		Wallet wallet = walletDTO.toEntity();

		assertNull(wallet.getId());
		assertNull(wallet.getPerson());
		assertNull(wallet.getNumber());
	}

	@Test
	public void toEntity_with_values() {
		WalletDTO walletDTO = WalletDTO.valueOf(WalletMock.mock());
		Wallet wallet = walletDTO.toEntity();

		assertEquals(walletDTO.getId(), wallet.getId());
		assertEquals(walletDTO.getPerson().getId(), wallet.getPerson().getId());
		assertEquals(walletDTO.getNumber(), wallet.getNumber());
	}

}
