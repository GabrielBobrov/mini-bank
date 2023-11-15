package com.minibank.transfers.infrastructure.integration.ms.account.adapter;

import com.minibank.transfers.infrastructure.integration.ms.account.client.MsAccountHttpClient;
import com.minibank.transfers.infrastructure.integration.ms.account.model.response.GetAccountHttpClientResponseDTO;
import com.minibank.transfers.infrastructure.integration.ms.account.port.in.IMsAccountHttpClientPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@AllArgsConstructor
public class MsAccountHttpClientAdapter implements IMsAccountHttpClientPort {
    private final MsAccountHttpClient msAccountHttpClient;
    @Override
    public GetAccountHttpClientResponseDTO getAccount(UUID id) {
        log.info("Class {} method getAccount", this.getClass().getName());

        GetAccountHttpClientResponseDTO account = msAccountHttpClient.getAccount(id);
        log.info("GetAccountHttpClientResponseDTO {}", account);

        return account;
    }
}
