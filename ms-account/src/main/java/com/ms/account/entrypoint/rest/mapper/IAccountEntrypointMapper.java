package com.ms.account.entrypoint.rest.mapper;


import com.ms.account.core.model.CreateAccountModel;
import com.ms.account.core.model.GetAccountModel;
import com.ms.account.entrypoint.rest.dto.request.CreateAccountRequestDTO;
import com.ms.account.entrypoint.rest.dto.response.GetAccountResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAccountEntrypointMapper {
    CreateAccountModel fromCreateAccountRequestDTOToAccountModel(CreateAccountRequestDTO accountRequestDTO);
    GetAccountResponseDTO fromGetAccountModelToGetAccountResponseDTO(GetAccountModel getAccountModel);


}
