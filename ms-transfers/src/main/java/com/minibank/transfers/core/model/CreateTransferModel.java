package com.minibank.transfers.core.model;

import com.minibank.transfers.infrastructure.entity.transfer.enums.TransferReasonType;
import com.minibank.transfers.infrastructure.entity.transfer.enums.TransferStatusType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateTransferModel {

    private UUID payer;
    private UUID payee;
    private BigDecimal amount;
    private TransferStatusType status;
    private TransferReasonType reason;
}
