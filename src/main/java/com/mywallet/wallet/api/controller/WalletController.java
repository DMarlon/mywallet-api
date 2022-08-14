package com.mywallet.wallet.api.controller;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.mywallet.core.domain.exception.AssociatedValueException;
import com.mywallet.core.domain.exception.RequiredFieldException;
import com.mywallet.core.domain.exception.UniqueFieldException;
import com.mywallet.core.domain.utilitary.ValidatorUtils;
import com.mywallet.wallet.api.dto.BalanceTicketDTO;
import com.mywallet.wallet.api.dto.DepositDTO;
import com.mywallet.wallet.api.dto.TransactionDTO;
import com.mywallet.wallet.api.dto.StatementDTO;
import com.mywallet.wallet.api.dto.TransferDTO;
import com.mywallet.wallet.api.dto.TransferTicketDTO;
import com.mywallet.wallet.api.dto.WalletDTO;
import com.mywallet.wallet.api.dto.WithdrawDTO;
import com.mywallet.wallet.domain.exception.InsufficientBalanceException;
import com.mywallet.wallet.domain.model.Transaction;
import com.mywallet.wallet.domain.model.Wallet;
import com.mywallet.wallet.domain.service.TransactionListService;
import com.mywallet.wallet.domain.service.WalletCreateService;
import com.mywallet.wallet.domain.service.WalletDepositService;
import com.mywallet.wallet.domain.service.WalletListService;
import com.mywallet.wallet.domain.service.WalletTransferService;
import com.mywallet.wallet.domain.service.WalletWithdrawService;

@RestController
@RequestMapping("/wallet")
public class WalletController {

	private WalletCreateService walletCreateService;
	private WalletDepositService walletDepositService;
	private WalletWithdrawService walletWithdrawService;
	private WalletTransferService walletTransferService;
	private WalletListService walletListService;
	private TransactionListService transactionListService;

	public WalletController(WalletCreateService walletCreateService, WalletDepositService walletDepositService, WalletWithdrawService walletWithdrawService, WalletTransferService walletTransferService, WalletListService walletListService, TransactionListService transactionListService) {
		this.walletCreateService = walletCreateService;
		this.walletDepositService = walletDepositService;
		this.walletWithdrawService = walletWithdrawService;
		this.walletTransferService = walletTransferService;
		this.walletListService = walletListService;
		this.transactionListService = transactionListService;
	}

	@GetMapping
	public List<WalletDTO> list() {
		return walletListService.findAll().stream().map(WalletDTO::valueOf).collect(Collectors.toList());
	}

	@PostMapping
	public WalletDTO create(@RequestBody WalletDTO walletDTO) {
		try {
			return WalletDTO.valueOf(walletCreateService.create(walletDTO.toEntity()));
		} catch (RequiredFieldException | AssociatedValueException | UniqueFieldException exception) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
		}
	}

	@PutMapping("/deposit")
	public TransactionDTO deposit(@RequestBody DepositDTO depositDTO) {
		if (Objects.isNull(depositDTO) || Objects.isNull(depositDTO.getWalletNumber()) || ValidatorUtils.isNullOrLessThanOne(depositDTO.getValue()))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "All information is needed to deposit value!");

		try {
			return TransactionDTO.valueOf(walletDepositService.deposit(depositDTO.getWalletNumber(), depositDTO.getValue()));
		} catch (RequiredFieldException | AssociatedValueException exception) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
		}
	}

	@PutMapping("/withdraw")
	public TransactionDTO withdraw(@RequestBody WithdrawDTO withdrawDTO) {
		if (Objects.isNull(withdrawDTO) || Objects.isNull(withdrawDTO.getWalletNumber()) || ValidatorUtils.isNullOrLessThanOne(withdrawDTO.getValue()))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "All information is needed to withdraw value!");

		try {
			return TransactionDTO.valueOf(walletWithdrawService.withdraw(withdrawDTO.getWalletNumber(), withdrawDTO.getValue()));
		} catch (RequiredFieldException | AssociatedValueException | InsufficientBalanceException exception) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
		}
	}

	@PutMapping("/transfer")
	public TransferTicketDTO withdraw(@RequestBody TransferDTO transferDTO) {
		if (Objects.isNull(transferDTO) || Objects.isNull(transferDTO.getFromWalletNumber()) || Objects.isNull(transferDTO.getToWalletNumber()) || ValidatorUtils.isNullOrLessThanOne(transferDTO.getValue()))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "All information is needed to transfer value!");

		try {
			return TransferTicketDTO.valueOf(walletTransferService.transfer(transferDTO.getFromWalletNumber(), transferDTO.getToWalletNumber(), transferDTO.getValue()));
		} catch (RequiredFieldException | AssociatedValueException | InsufficientBalanceException exception) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
		}
	}

	@GetMapping("/{walletNumber:^[0-9a-f]{8}-[0-9a-f]{4}-[0-5][0-9a-f]{3}-[089ab][0-9a-f]{3}-[0-9a-f]{12}$}/balance")
	public BalanceTicketDTO balance(@PathVariable("walletNumber") UUID walletNumber) {
		Optional<Wallet> wallet = walletListService.findByNumber(walletNumber);
		if (wallet.isEmpty())
			throw new ResponseStatusException(HttpStatus.NO_CONTENT);

		return BalanceTicketDTO.valueOf(wallet.get());
	}

	@GetMapping("/{walletNumber:^[0-9a-f]{8}-[0-9a-f]{4}-[0-5][0-9a-f]{3}-[089ab][0-9a-f]{3}-[0-9a-f]{12}$}/statement")
	public StatementDTO statement(@PathVariable("walletNumber") UUID walletNumber) {
		Optional<Wallet> wallet = walletListService.findByNumber(walletNumber);
		if (wallet.isEmpty())
			throw new ResponseStatusException(HttpStatus.NO_CONTENT);

		List<Transaction> transactions = transactionListService.findAllByWalletNumber(walletNumber);

		return StatementDTO.valueOf(wallet.get(), transactions);

	}

}
