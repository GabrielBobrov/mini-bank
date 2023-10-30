package com.ms.account.dummy;

import com.ms.account.core.model.AccountModel;
import com.ms.account.entrypoint.rest.dto.request.CreateAccountRequestDTO;
import com.ms.account.infrastructure.entity.AccountEntity;

import java.util.UUID;

public class AccountDummy {

    public static AccountModel.AccountModelBuilder accountModelBuilder() {
        return AccountModel.builder()
                .firstName("firstName")
                .id(UUID.randomUUID());
    }

    public static AccountEntity.AccountEntityBuilder accountEntityBuilder() {
        return AccountEntity.builder()
                .firstName("firstName")
                .id(UUID.randomUUID());
    }

    public static CreateAccountRequestDTO.CreateAccountRequestDTOBuilder createAccountRequestDTOBuilder() {
        return CreateAccountRequestDTO.builder()
                .firstName("john");
    }
}
