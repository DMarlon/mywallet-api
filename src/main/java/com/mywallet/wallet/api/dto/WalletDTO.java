package com.mywallet.wallet.api.dto;

import java.util.Objects;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mywallet.person.api.dto.PersonDTO;
import com.mywallet.wallet.domain.model.Wallet;

public class WalletDTO {

	@JsonIgnore
	private Long id;
	private PersonDTO person;
	private UUID number;

	public static WalletDTO valueOf(Wallet wallet) {
		WalletDTO walletDTO = new WalletDTO();

		if (Objects.nonNull(wallet)) {
			walletDTO.setId(wallet.getId());
			walletDTO.setPerson(PersonDTO.valueOf(wallet.getPerson()));
			walletDTO.setNumber(wallet.getNumber());
		}

		return walletDTO;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PersonDTO getPerson() {
		return person;
	}

	public void setPerson(PersonDTO person) {
		this.person = person;
	}

	public UUID getNumber() {
		return number;
	}

	public void setNumber(UUID number) {
		this.number = number;
	}

	public Wallet toEntity() {
		Wallet wallet = new Wallet();
		wallet.setId(getId());
		wallet.setNumber(getNumber());

		PersonDTO person = getPerson();
		if (Objects.nonNull(person))
			wallet.setPerson(person.toEntity());

		return wallet;
	}

}
