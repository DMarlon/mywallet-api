package com.mywallet.wallet.domain.service;

import java.util.Objects;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.mywallet.core.domain.exception.AssociatedValueException;
import com.mywallet.core.domain.exception.RequiredFieldException;
import com.mywallet.core.domain.exception.UniqueFieldException;
import com.mywallet.core.domain.utilitary.ValidatorUtils;
import com.mywallet.person.domain.service.PersonListService;
import com.mywallet.wallet.domain.model.Wallet;
import com.mywallet.wallet.domain.repository.WalletRepository;

@Service
public class WalletCreateService {

	private WalletRepository walletRepository;
	private PersonListService personService;

	public WalletCreateService(WalletRepository walletRepository, PersonListService personService) {
		this.walletRepository = walletRepository;
		this.personService = personService;
	}

	public Wallet create(Wallet wallet) {
		checkRequiredFields(wallet);

		wallet.setId(null);
		wallet.setNumber(UUID.randomUUID());
		wallet.setBalance(Long.valueOf(0));

		checkAssociativeFields(wallet);
		checkUniqueFields(wallet);

		return this.walletRepository.save(wallet);
	}

	private void checkRequiredFields(Wallet wallet) {
		if (Objects.isNull(wallet))
			throw new RequiredFieldException("The wallet must be provided!");
		if (Objects.isNull(wallet.getPerson()) || ValidatorUtils.isNullOrLessThanOne(wallet.getPerson().getId()))
			throw new RequiredFieldException("The wallet person must be provided!");
	}

	private void checkAssociativeFields(Wallet wallet) {
		if (!personService.existsById(wallet.getPerson().getId()))
			throw new AssociatedValueException("The wallet person provided is not valid!");
	}

	private void checkUniqueFields(Wallet wallet) {
		if (walletRepository.existsByNumber(wallet.getNumber(), null))
			throw new UniqueFieldException("The Wallet number already exists!");
	}
}
