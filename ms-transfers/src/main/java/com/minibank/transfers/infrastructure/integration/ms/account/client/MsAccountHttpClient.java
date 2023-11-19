package com.minibank.transfers.infrastructure.integration.ms.account.client;

import com.minibank.transfers.infrastructure.integration.ms.account.model.request.UpdateBalanceHttpClientRequestDTO;
import com.minibank.transfers.infrastructure.integration.ms.account.model.response.GetAccountHttpClientResponseDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PatchExchange;

import java.util.UUID;

@HttpExchange("/accounts")
public interface MsAccountHttpClient {

    @GetExchange("/{accountId}")
    GetAccountHttpClientResponseDTO getAccount(@PathVariable UUID accountId);

    @PatchExchange ("/{accountId}/balance")
    void updateBalance(@PathVariable UUID accountId,
                       @RequestBody UpdateBalanceHttpClientRequestDTO updateBalanceHttpClientRequestDTO);
}
