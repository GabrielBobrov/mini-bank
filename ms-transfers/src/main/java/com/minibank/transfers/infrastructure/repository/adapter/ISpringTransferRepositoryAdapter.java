package com.minibank.transfers.infrastructure.repository.adapter;

import com.minibank.transfers.infrastructure.entity.transfer.TransferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ISpringTransferRepositoryAdapter extends JpaRepository<TransferEntity, UUID>, JpaSpecificationExecutor<TransferEntity> {

}
