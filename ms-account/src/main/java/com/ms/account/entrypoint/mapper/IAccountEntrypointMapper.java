package com.ms.account.entrypoint.mapper;


import com.ms.account.core.model.CreateAccountModel;
import com.ms.account.core.model.GetAccountModel;
import com.ms.account.entrypoint.dto.request.CreateAccountRequestDTO;
import com.ms.account.entrypoint.dto.response.GetAccountResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAccountEntrypointMapper {
    CreateAccountModel fromCreateAccountRequestDTOToCreateAccountModel(CreateAccountRequestDTO accountRequestDTO);

    GetAccountResponseDTO fromGetAccountModelToGetAccountResponseDTO(GetAccountModel getAccountModel);

    List<GetAccountResponseDTO> fromListGetAccountModelToListGetAccountResponseDTO(List<GetAccountModel> getAccountModel);

}
