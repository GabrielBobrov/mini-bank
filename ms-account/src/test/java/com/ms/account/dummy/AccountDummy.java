package com.ms.account.dummy;

import com.github.javafaker.Faker;
import com.ms.account.core.model.CreateAccountModel;
import com.ms.account.core.model.GetAccountModel;
import com.ms.account.entrypoint.rest.dto.request.CreateAccountRequestDTO;
import com.ms.account.entrypoint.rest.dto.response.GetAccountResponseDTO;
import com.ms.account.infrastructure.entity.AccountEntity;
import com.ms.account.infrastructure.entity.enums.AccountType;

import java.util.UUID;

public class AccountDummy {

    private static final Faker faker = new Faker();

    public static CreateAccountModel.CreateAccountModelBuilder createAccountModelBuilder() {
        return CreateAccountModel.builder()
                .firstName(faker.name().firstName())
                .document("50109351126")
                .email(faker.internet().emailAddress())
                .password(faker.internet().password())
                .type(faker.options().option(AccountType.class));
    }

    public static AccountEntity.AccountEntityBuilder accountEntityBuilder() {
        return AccountEntity.builder()
                .firstName(faker.name().firstName())
                .id(UUID.randomUUID());
    }

    public static CreateAccountRequestDTO.CreateAccountRequestDTOBuilder createAccountRequestDTOBuilder() {
        return CreateAccountRequestDTO.builder()
                .firstName(faker.name().firstName())
                .document("50109351126")
                .email(faker.internet().emailAddress())
                .password(faker.internet().password())
                .type(faker.options().option(AccountType.class));

    }

    public static GetAccountModel.GetAccountModelBuilder getAccountModelBuilder() {
        return GetAccountModel.builder()
                .firstName(faker.name().firstName())
                .id(UUID.randomUUID());
    }

    public static GetAccountResponseDTO.GetAccountResponseDTOBuilder getAccountResponseDTOBuilder() {
        return GetAccountResponseDTO.builder()
                .firstName(faker.name().firstName())
                .id(UUID.randomUUID());
    }
}
