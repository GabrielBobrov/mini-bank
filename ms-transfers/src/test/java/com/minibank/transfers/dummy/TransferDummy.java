package com.minibank.transfers.dummy;

import com.minibank.transfers.core.model.CreateTransferModel;
import com.minibank.transfers.infrastructure.entity.transfer.TransferEntity;
import com.minibank.transfers.infrastructure.entity.transfer.enums.TransferStatusType;

import java.math.BigDecimal;
import java.util.UUID;

public class TransferDummy {

    public static CreateTransferModel.CreateTransferModelBuilder createTransferModelBuilder() {

        return CreateTransferModel.builder()
                .payer(UUID.randomUUID())
                .payee(UUID.randomUUID())
                .amount(BigDecimal.TEN)
                .status(TransferStatusType.SUCCESS);

    }

    public static TransferEntity.TransferEntityBuilder transferEntityBuilder() {
        return TransferEntity.builder()
                .payer(UUID.randomUUID().toString())
                .amount(BigDecimal.TEN)
                .payee(UUID.randomUUID().toString())
                .status(TransferStatusType.SUCCESS);
    }
}
