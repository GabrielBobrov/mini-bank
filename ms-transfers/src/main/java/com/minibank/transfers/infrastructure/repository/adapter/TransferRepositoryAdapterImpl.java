package com.minibank.transfers.infrastructure.repository.adapter;


import com.minibank.transfers.core.model.CreateTransferModel;
import com.minibank.transfers.core.ports.out.repository.ITransferRepositoryPort;
import com.minibank.transfers.infrastructure.entity.transfer.TransferEntity;
import com.minibank.transfers.infrastructure.mapper.ITransferInfrastructureMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;



@Slf4j
@Component
@AllArgsConstructor
public class TransferRepositoryAdapterImpl implements ITransferRepositoryPort {

    private final ISpringTransferRepositoryAdapter springTransferRepository;
    private final ITransferInfrastructureMapper transferInfrastructureMapper;

    @Override
    @Transactional
    public void create(CreateTransferModel createTransferModel) {
        log.info("Class {} method create", this.getClass().getName());
        log.info("CreateTransferModel {}", createTransferModel);

        TransferEntity transferEntity = transferInfrastructureMapper.fromCreateTransferModelToTransferEntity(createTransferModel);
        log.info("TransferEntity {}", transferEntity);

        springTransferRepository.save(transferEntity);
    }


}
