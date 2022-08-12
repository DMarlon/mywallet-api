package com.mywallet.wallet.api.dto;

import java.util.Objects;

import com.mywallet.wallet.domain.model.Transfer;

public class TransferTicketDTO {

	private TransactionDTO from;
	private TransactionDTO to;

	public static TransferTicketDTO valueOf(Transfer transfer) {
		TransferTicketDTO transferTicketDTO = new TransferTicketDTO();

		if (Objects.nonNull(transfer)) {
			transferTicketDTO.setFrom(TransactionDTO.valueOf(transfer.getWithdraw()));
			transferTicketDTO.setTo(TransactionDTO.valueOf(transfer.getDeposit()));
		}

		return transferTicketDTO;
	}

	public TransactionDTO getFrom() {
		return from;
	}

	public void setFrom(TransactionDTO from) {
		this.from = from;
	}

	public TransactionDTO getTo() {
		return to;
	}

	public void setTo(TransactionDTO to) {
		this.to = to;
	}

}
