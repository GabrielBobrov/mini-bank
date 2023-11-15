package com.minibank.transfers.core.ports.out.repository;

import com.minibank.transfers.core.model.CreateTransferModel;

public interface ITransferRepositoryPort {

    void create(CreateTransferModel createTransferModel);
}
