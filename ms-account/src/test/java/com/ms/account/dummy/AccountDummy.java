package com.ms.account.dummy;

import com.ms.account.core.model.CreateAccountModel;
import com.ms.account.core.model.GetAccountModel;
import com.ms.account.entrypoint.rest.dto.request.CreateAccountRequestDTO;
import com.ms.account.entrypoint.rest.dto.response.GetAccountResponseDTO;
import com.ms.account.infrastructure.entity.AccountEntity;

import java.util.UUID;

public class AccountDummy {

    public static CreateAccountModel.CreateAccountModelBuilder createAccountModelBuilder() {
        return CreateAccountModel.builder()
                .firstName("firstName");
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

    public static GetAccountModel.GetAccountModelBuilder getAccountModelBuilder() {
        return GetAccountModel.builder()
                .firstName("firstName")
                .id(UUID.randomUUID());
    }

    public static GetAccountResponseDTO.GetAccountResponseDTOBuilder getAccountResponseDTOBuilder() {
        return GetAccountResponseDTO.builder()
                .firstName("Gabriel")
                .id(UUID.randomUUID());
    }
}
