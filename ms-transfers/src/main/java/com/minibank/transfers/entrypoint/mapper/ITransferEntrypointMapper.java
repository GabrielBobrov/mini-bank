package com.minibank.transfers.entrypoint.mapper;


import com.minibank.transfers.core.model.CreateTransferModel;
import com.minibank.transfers.entrypoint.dto.request.CreateTransferRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ITransferEntrypointMapper {
    CreateTransferModel fromCreateTransferRequestDTOToCreateTransferModel(CreateTransferRequestDTO createTransferRequestDTO);
}
