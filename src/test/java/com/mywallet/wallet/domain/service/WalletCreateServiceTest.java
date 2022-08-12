package com.mywallet.wallet.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.mywallet.core.domain.exception.AssociatedValueException;
import com.mywallet.core.domain.exception.RequiredFieldException;
import com.mywallet.core.domain.exception.UniqueFieldException;
import com.mywallet.mock.PersonMock;
import com.mywallet.mock.WalletMock;
import com.mywallet.person.domain.model.Person;
import com.mywallet.person.domain.service.PersonListService;
import com.mywallet.wallet.domain.model.Wallet;
import com.mywallet.wallet.domain.repository.WalletRepository;

public class WalletCreateServiceTest {

	@Mock
	private WalletRepository walletRepository;
	@Mock
	private PersonListService personService;
	private WalletCreateService walletCreateService;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		walletCreateService = new WalletCreateService(walletRepository, personService);
	}

	@Test
	public void create_with_null() {
		RequiredFieldException exception = assertThrows(RequiredFieldException.class, () -> walletCreateService.create(null));
		assertEquals("The wallet must be provided!", exception.getMessage());
	}

	@Test
	public void create_with_person_null() {
		Wallet wallet = WalletMock.mock();
		wallet.setPerson(null);

		RequiredFieldException exception = assertThrows(RequiredFieldException.class, () -> walletCreateService.create(wallet));
		assertEquals("The wallet person must be provided!", exception.getMessage());
	}

	@Test
	public void create_with_person_id_null() {
		Wallet wallet = WalletMock.mock();
		Person person = PersonMock.mock();
		person.setId(null);
		wallet.setPerson(person);

		RequiredFieldException exception = assertThrows(RequiredFieldException.class, () -> walletCreateService.create(wallet));
		assertEquals("The wallet person must be provided!", exception.getMessage());
	}

	@Test
	public void create_with_person_id_less_than_one() {
		Wallet wallet = WalletMock.mock();
		Person person = PersonMock.mock();
		person.setId(Long.valueOf(0));
		wallet.setPerson(person);

		RequiredFieldException exception = assertThrows(RequiredFieldException.class, () -> walletCreateService.create(wallet));
		assertEquals("The wallet person must be provided!", exception.getMessage());
	}

	@Test
	public void create_with_person_not_exist() {
		Wallet wallet = WalletMock.mock();

		when(personService.existsById(anyLong())).thenReturn(Boolean.FALSE);
		when(walletRepository.existsByNumber(any(), any())).thenReturn(Boolean.FALSE);

		AssociatedValueException exception = assertThrows(AssociatedValueException.class, () -> walletCreateService.create(wallet));
		assertEquals("The wallet person provided is not valid!", exception.getMessage());
	}

	@Test
	public void create_with_number_conflit() {
		Wallet wallet = WalletMock.mock();

		when(personService.existsById(anyLong())).thenReturn(Boolean.TRUE);
		when(walletRepository.existsByNumber(any(), any())).thenReturn(Boolean.TRUE);

		UniqueFieldException exception = assertThrows(UniqueFieldException.class, () -> walletCreateService.create(wallet));
		assertEquals("The Wallet number already exists!", exception.getMessage());
	}

	@Test
	@SuppressWarnings("rawtypes")
	public void create_with_person_all_data() {
		Wallet wallet = WalletMock.mock();

		when(personService.existsById(anyLong())).thenReturn(Boolean.TRUE);
		when(walletRepository.existsByNumber(any(), any())).thenReturn(Boolean.FALSE);

		ArgumentCaptor<Wallet> argument = ArgumentCaptor.forClass(Wallet.class);
		Mockito.when(walletRepository.save(argument.capture())).thenAnswer(
			new Answer() {
				public Object answer(InvocationOnMock invocation) {
					return argument.getValue();
				}
			}
		);

		Wallet savedWallet = walletCreateService.create(wallet);
		assertEquals(null, savedWallet.getId());
		assertNotNull(savedWallet.getNumber());
		assertEquals(Long.valueOf(0), savedWallet.getBalance());
	}

}
