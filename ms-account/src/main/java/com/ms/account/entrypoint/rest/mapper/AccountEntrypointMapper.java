package com.ms.account.entrypoint.rest.mapper;


import com.ms.account.core.model.CreateAccountModel;
import com.ms.account.entrypoint.rest.dto.request.CreateAccountRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountEntrypointMapper {
    CreateAccountModel fromCreateAccountRequestDTOToAccountModel(CreateAccountRequestDTO accountRequestDTO);

}
