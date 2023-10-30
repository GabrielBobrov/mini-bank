package com.ms.account.entrypoint.rest.mapper;


import com.ms.account.core.model.AccountModel;
import com.ms.account.entrypoint.rest.dto.request.CreateAccountRequestDTO;
import com.ms.account.infrastructure.entity.AccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountEntrypointMapper {
    AccountModel fromCreateAccountRequestDTOToAccountModel(CreateAccountRequestDTO accountRequestDTO);

}
