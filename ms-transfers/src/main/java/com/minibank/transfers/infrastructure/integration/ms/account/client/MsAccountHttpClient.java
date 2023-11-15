package com.minibank.transfers.infrastructure.integration.ms.account.client;

import com.minibank.transfers.infrastructure.integration.ms.account.model.response.GetAccountHttpClientResponseDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.UUID;

@HttpExchange("/accounts")
public interface MsAccountHttpClient {

    @GetExchange("/{accountId}")
    GetAccountHttpClientResponseDTO getAccount(@PathVariable UUID accountId);
}
