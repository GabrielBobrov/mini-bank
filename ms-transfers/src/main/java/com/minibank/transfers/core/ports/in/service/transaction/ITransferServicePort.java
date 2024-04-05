package com.minibank.transfers.core.ports.in.service.transaction;

import com.minibank.transfers.core.model.CreateTransferModel;


public interface ITransferServicePort {

    void createTransfer(CreateTransferModel createTransferModel);

}
