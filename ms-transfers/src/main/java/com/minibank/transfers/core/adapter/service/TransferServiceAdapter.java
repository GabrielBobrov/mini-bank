package com.minibank.transfers.core.adapter.service;

import com.minibank.transfers.core.exception.InvalidPayerTypeException;
import com.minibank.transfers.core.model.CreateTransferModel;
import com.minibank.transfers.core.ports.in.service.ITransferServicePort;
import com.minibank.transfers.core.ports.out.repository.ITransferRepositoryPort;
import com.minibank.transfers.infrastructure.entity.account.enums.AccountType;
import com.minibank.transfers.infrastructure.entity.transfer.enums.TransferStatusType;
import com.minibank.transfers.infrastructure.integration.ms.account.model.response.GetAccountHttpClientResponseDTO;
import com.minibank.transfers.infrastructure.integration.ms.account.port.in.IMsAccountHttpClientPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@AllArgsConstructor
public class TransferServiceAdapter implements ITransferServicePort {

    private final IMsAccountHttpClientPort msAccountHttpClientPort;
    private final ITransferRepositoryPort transferRepositoryPort;

    @Override
    public void createTransfer(CreateTransferModel createTransferModel) {
        log.info("Class {} method createTransfer", this.getClass().getName());
        log.info("CreateTransferModel {}", createTransferModel);

        GetAccountHttpClientResponseDTO payee = msAccountHttpClientPort.getAccount(createTransferModel.getPayee());
        log.info("Payee {}", payee);

        GetAccountHttpClientResponseDTO payer = msAccountHttpClientPort.getAccount(createTransferModel.getPayer());
        log.info("Payee {}", payer);

        if (Objects.equals(payer.getType(), AccountType.SHOPKEEPERS)) {
            throw new InvalidPayerTypeException("O pagador não pode ser do tipo " + AccountType.SHOPKEEPERS);
        }

        //TODO: update account balance

        createTransferModel.setStatus(TransferStatusType.SUCCESS);
        transferRepositoryPort.create(createTransferModel);


    }
}
