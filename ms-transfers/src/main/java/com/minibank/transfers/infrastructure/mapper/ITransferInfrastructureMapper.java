package com.minibank.transfers.infrastructure.mapper;


import com.minibank.transfers.core.model.CreateTransferModel;
import com.minibank.transfers.infrastructure.entity.transfer.TransferEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ITransferInfrastructureMapper {
    TransferEntity fromCreateTransferModelToTransferEntity(CreateTransferModel createTransferModel);


}
