package com.minibank.transfers.infrastructure.integration.ms.account.port.in;

import com.minibank.transfers.infrastructure.integration.ms.account.model.request.UpdateBalanceHttpClientRequestDTO;
import com.minibank.transfers.infrastructure.integration.ms.account.model.response.GetAccountHttpClientResponseDTO;

import java.util.UUID;

public interface IMsAccountHttpClientPort {
    GetAccountHttpClientResponseDTO getAccount(UUID id);

    void updateBalance(UUID id, UpdateBalanceHttpClientRequestDTO updateBalanceHttpClientRequestDTO);
}
