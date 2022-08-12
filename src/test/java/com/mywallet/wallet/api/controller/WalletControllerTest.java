package com.mywallet.wallet.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.mywallet.WrapperConfiguration;
import com.mywallet.core.api.dto.ApiErrorDTO;
import com.mywallet.helper.WrapperHelper;
import com.mywallet.mock.TransactionMock;
import com.mywallet.mock.WalletMock;
import com.mywallet.wallet.api.dto.BalanceTicketDTO;
import com.mywallet.wallet.api.dto.DepositDTO;
import com.mywallet.wallet.api.dto.TransactionDetailDTO;
import com.mywallet.wallet.api.dto.TransactionTicketDTO;
import com.mywallet.wallet.api.dto.TransferDTO;
import com.mywallet.wallet.api.dto.WalletDTO;
import com.mywallet.wallet.api.dto.WithdrawDTO;
import com.mywallet.wallet.domain.model.Transaction;
import com.mywallet.wallet.domain.model.Wallet;
import com.mywallet.wallet.domain.service.TransactionListService;
import com.mywallet.wallet.domain.service.WalletListService;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@Import(WrapperConfiguration.class)
public class WalletControllerTest {

	@Autowired
	private MockMvc controller;

	@Autowired
	private WrapperHelper wrapperHelper;

	@MockBean
	private WalletListService walletListService;

	@MockBean
	private TransactionListService transactionListService;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void create_null_wallet() throws Exception {
		MvcResult response = this.controller.perform(
			post("/wallet")
			.contentType(MediaType.APPLICATION_JSON)
			.content("{}")
		)
		.andExpect(status().isBadRequest())
		.andReturn();

		ApiErrorDTO error = wrapperHelper.unwrapper(response.getResponse().getContentAsString(), ApiErrorDTO.class);
		assertEquals(400, error.getStatus());
		assertEquals("Bad Request", error.getError());
		assertEquals("The wallet person must be provided!", error.getMessage());
		assertEquals("/wallet", error.getPath());
		assertNotNull(error.getTimestamp());
	}

	@Test
	public void create_without_person() throws Exception {
		WalletDTO walletDTO = WalletDTO.valueOf(WalletMock.mock());
		walletDTO.setPerson(null);

		MvcResult response = this.controller.perform(
			post("/wallet")
			.contentType(MediaType.APPLICATION_JSON)
			.content(wrapperHelper.wrapper(walletDTO))
		)
		.andExpect(status().isBadRequest())
		.andReturn();

		ApiErrorDTO error = wrapperHelper.unwrapper(response.getResponse().getContentAsString(), ApiErrorDTO.class);
		assertEquals(400, error.getStatus());
		assertEquals("Bad Request", error.getError());
		assertEquals("The wallet person must be provided!", error.getMessage());
		assertEquals("/wallet", error.getPath());
		assertNotNull(error.getTimestamp());
	}

	@Test
	public void deposit_null_deposit() throws Exception {
		MvcResult response = this.controller.perform(
			put("/wallet/deposit")
			.contentType(MediaType.APPLICATION_JSON)
			.content("{}")
		)
		.andExpect(status().isBadRequest())
		.andReturn();

		ApiErrorDTO error = wrapperHelper.unwrapper(response.getResponse().getContentAsString(), ApiErrorDTO.class);
		assertEquals(400, error.getStatus());
		assertEquals("Bad Request", error.getError());
		assertEquals("All information is needed to deposit value!", error.getMessage());
		assertEquals("/wallet/deposit", error.getPath());
		assertNotNull(error.getTimestamp());
	}

	@Test
	public void deposit_without_wallet_number() throws Exception {
		DepositDTO depoistDTO = new DepositDTO();
		depoistDTO.setWalletNumber(null);
		depoistDTO.setValue(Long.valueOf(5000));

		MvcResult response = this.controller.perform(
			put("/wallet/deposit")
			.contentType(MediaType.APPLICATION_JSON)
			.content(wrapperHelper.wrapper(depoistDTO))
		)
		.andExpect(status().isBadRequest())
		.andReturn();

		ApiErrorDTO error = wrapperHelper.unwrapper(response.getResponse().getContentAsString(), ApiErrorDTO.class);
		assertEquals(400, error.getStatus());
		assertEquals("Bad Request", error.getError());
		assertEquals("All information is needed to deposit value!", error.getMessage());
		assertEquals("/wallet/deposit", error.getPath());
		assertNotNull(error.getTimestamp());
	}

	@Test
	public void deposit_without_value() throws Exception {
		DepositDTO depoistDTO = new DepositDTO();
		depoistDTO.setWalletNumber(UUID.randomUUID());
		depoistDTO.setValue(null);

		MvcResult response = this.controller.perform(
			put("/wallet/deposit")
			.contentType(MediaType.APPLICATION_JSON)
			.content(wrapperHelper.wrapper(depoistDTO))
		)
		.andExpect(status().isBadRequest())
		.andReturn();

		ApiErrorDTO error = wrapperHelper.unwrapper(response.getResponse().getContentAsString(), ApiErrorDTO.class);
		assertEquals(400, error.getStatus());
		assertEquals("Bad Request", error.getError());
		assertEquals("All information is needed to deposit value!", error.getMessage());
		assertEquals("/wallet/deposit", error.getPath());
		assertNotNull(error.getTimestamp());
	}

	@Test
	public void withdraw_null_withdraw() throws Exception {
		MvcResult response = this.controller.perform(
			put("/wallet/withdraw")
			.contentType(MediaType.APPLICATION_JSON)
			.content("{}")
		)
		.andExpect(status().isBadRequest())
		.andReturn();

		ApiErrorDTO error = wrapperHelper.unwrapper(response.getResponse().getContentAsString(), ApiErrorDTO.class);
		assertEquals(400, error.getStatus());
		assertEquals("Bad Request", error.getError());
		assertEquals("All information is needed to withdraw value!", error.getMessage());
		assertEquals("/wallet/withdraw", error.getPath());
		assertNotNull(error.getTimestamp());
	}

	@Test
	public void withdraw_without_wallet_number() throws Exception {
		WithdrawDTO withdrawDTO = new WithdrawDTO();
		withdrawDTO.setWalletNumber(null);
		withdrawDTO.setValue(Long.valueOf(5000));

		MvcResult response = this.controller.perform(
			put("/wallet/withdraw")
			.contentType(MediaType.APPLICATION_JSON)
			.content(wrapperHelper.wrapper(withdrawDTO))
		)
		.andExpect(status().isBadRequest())
		.andReturn();

		ApiErrorDTO error = wrapperHelper.unwrapper(response.getResponse().getContentAsString(), ApiErrorDTO.class);
		assertEquals(400, error.getStatus());
		assertEquals("Bad Request", error.getError());
		assertEquals("All information is needed to withdraw value!", error.getMessage());
		assertEquals("/wallet/withdraw", error.getPath());
		assertNotNull(error.getTimestamp());
	}

	@Test
	public void withdraw_without_value() throws Exception {
		WithdrawDTO withdrawDTO = new WithdrawDTO();
		withdrawDTO.setWalletNumber(UUID.randomUUID());
		withdrawDTO.setValue(null);

		MvcResult response = this.controller.perform(
			put("/wallet/withdraw")
			.contentType(MediaType.APPLICATION_JSON)
			.content(wrapperHelper.wrapper(withdrawDTO))
		)
		.andExpect(status().isBadRequest())
		.andReturn();

		ApiErrorDTO error = wrapperHelper.unwrapper(response.getResponse().getContentAsString(), ApiErrorDTO.class);
		assertEquals(400, error.getStatus());
		assertEquals("Bad Request", error.getError());
		assertEquals("All information is needed to withdraw value!", error.getMessage());
		assertEquals("/wallet/withdraw", error.getPath());
		assertNotNull(error.getTimestamp());
	}

	@Test
	public void transfer_null_transfer() throws Exception {
		MvcResult response = this.controller.perform(
			put("/wallet/transfer")
			.contentType(MediaType.APPLICATION_JSON)
			.content("{}")
		)
		.andExpect(status().isBadRequest())
		.andReturn();

		ApiErrorDTO error = wrapperHelper.unwrapper(response.getResponse().getContentAsString(), ApiErrorDTO.class);
		assertEquals(400, error.getStatus());
		assertEquals("Bad Request", error.getError());
		assertEquals("All information is needed to transfer value!", error.getMessage());
		assertEquals("/wallet/transfer", error.getPath());
		assertNotNull(error.getTimestamp());
	}

	@Test
	public void transfer_without_from_wallet_number() throws Exception {
		TransferDTO transferDTO = new TransferDTO();
		transferDTO.setFromWalletNumber(null);
		transferDTO.setToWalletNumber(UUID.randomUUID());
		transferDTO.setValue(Long.valueOf(5000));

		MvcResult response = this.controller.perform(
			put("/wallet/transfer")
			.contentType(MediaType.APPLICATION_JSON)
			.content(wrapperHelper.wrapper(transferDTO))
		)
		.andExpect(status().isBadRequest())
		.andReturn();

		ApiErrorDTO error = wrapperHelper.unwrapper(response.getResponse().getContentAsString(), ApiErrorDTO.class);
		assertEquals(400, error.getStatus());
		assertEquals("Bad Request", error.getError());
		assertEquals("All information is needed to transfer value!", error.getMessage());
		assertEquals("/wallet/transfer", error.getPath());
		assertNotNull(error.getTimestamp());
	}

	@Test
	public void transfer_without_to_wallet_number() throws Exception {
		TransferDTO transferDTO = new TransferDTO();
		transferDTO.setFromWalletNumber(UUID.randomUUID());
		transferDTO.setToWalletNumber(null);
		transferDTO.setValue(Long.valueOf(5000));

		MvcResult response = this.controller.perform(
			put("/wallet/transfer")
			.contentType(MediaType.APPLICATION_JSON)
			.content(wrapperHelper.wrapper(transferDTO))
		)
		.andExpect(status().isBadRequest())
		.andReturn();

		ApiErrorDTO error = wrapperHelper.unwrapper(response.getResponse().getContentAsString(), ApiErrorDTO.class);
		assertEquals(400, error.getStatus());
		assertEquals("Bad Request", error.getError());
		assertEquals("All information is needed to transfer value!", error.getMessage());
		assertEquals("/wallet/transfer", error.getPath());
		assertNotNull(error.getTimestamp());
	}

	@Test
	public void transfer_without_value() throws Exception {
		TransferDTO transferDTO = new TransferDTO();
		transferDTO.setFromWalletNumber(UUID.randomUUID());
		transferDTO.setToWalletNumber(UUID.randomUUID());
		transferDTO.setValue(null);

		MvcResult response = this.controller.perform(
			put("/wallet/transfer")
			.contentType(MediaType.APPLICATION_JSON)
			.content(wrapperHelper.wrapper(transferDTO))
		)
		.andExpect(status().isBadRequest())
		.andReturn();

		ApiErrorDTO error = wrapperHelper.unwrapper(response.getResponse().getContentAsString(), ApiErrorDTO.class);
		assertEquals(400, error.getStatus());
		assertEquals("Bad Request", error.getError());
		assertEquals("All information is needed to transfer value!", error.getMessage());
		assertEquals("/wallet/transfer", error.getPath());
		assertNotNull(error.getTimestamp());
	}

	@Test
	public void balance_empty() throws Exception {
		this.controller.perform(get("/wallet/" + UUID.randomUUID() + "/balance")).andExpect(status().isNoContent());
	}

	@Test
	public void balance_value() throws Exception {
		Wallet wallet = WalletMock.mock();
		when(walletListService.findByNumber(any())).thenReturn(Optional.of(wallet));

		MvcResult response = this.controller
				.perform(get("/wallet/" + wallet.getNumber() + "/balance"))
				.andExpect(status().isOk())
		.andReturn();

		BalanceTicketDTO balanceTicketDTO = wrapperHelper.unwrapper(response.getResponse().getContentAsString(), BalanceTicketDTO.class);

		assertNotNull(balanceTicketDTO);
		assertEquals(wallet.getBalance(), balanceTicketDTO.getBalance());

		assertNotNull(balanceTicketDTO.getWallet());
		assertEquals(wallet.getNumber(), balanceTicketDTO.getWallet().getNumber());

		assertNotNull(balanceTicketDTO.getWallet().getPerson());
		assertEquals(wallet.getPerson().getName(), balanceTicketDTO.getWallet().getPerson().getName());
	}

	@Test
	public void transaction_empty() throws Exception {
		this.controller.perform(get("/wallet/" + UUID.randomUUID() + "/transaction")).andExpect(status().isNoContent());
	}

	@Test
	public void transaction_value() throws Exception {
		Wallet wallet = WalletMock.mock();
		Transaction transaction = TransactionMock.mock();

		when(walletListService.findByNumber(any())).thenReturn(Optional.of(wallet));
		when(transactionListService.findAllByWalletNumber(any())).thenReturn(List.of(transaction));

		MvcResult response = this.controller
				.perform(get("/wallet/" + wallet.getNumber() + "/transaction"))
				.andExpect(status().isOk())
		.andReturn();

		TransactionTicketDTO transactionTicketDTO = wrapperHelper.unwrapper(response.getResponse().getContentAsString(), TransactionTicketDTO.class);

		assertNotNull(transactionTicketDTO);
		assertEquals(wallet.getBalance(), transactionTicketDTO.getBalance());

		assertNotNull(transactionTicketDTO.getWallet());
		assertEquals(wallet.getNumber(), transactionTicketDTO.getWallet().getNumber());

		assertNotNull(transactionTicketDTO.getWallet().getPerson());
		assertEquals(wallet.getPerson().getName(), transactionTicketDTO.getWallet().getPerson().getName());

		assertNotNull(transactionTicketDTO.getTransactions());
		assertFalse(transactionTicketDTO.getTransactions().isEmpty());

		TransactionDetailDTO transactionDetailDTO = transactionTicketDTO.getTransactions().get(0);
		assertEquals(transaction.getNumber(), transactionDetailDTO.getNumber());
		assertEquals(transaction.getDateTime(), transactionDetailDTO.getDateTime());
		assertEquals(transaction.getOperation(), transactionDetailDTO.getOperation());
		assertEquals(transaction.getType(), transactionDetailDTO.getType());
		assertEquals(transaction.getValue(), transactionDetailDTO.getValue());
		assertEquals(transaction.getObservation(), transactionDetailDTO.getObservation());
	}

}
