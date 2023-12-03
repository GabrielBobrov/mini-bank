package com.minibank.transfers.core.ports.in.service;

import com.minibank.transfers.core.model.CreateTransferModel;


public interface ITransferServicePort {

    void createTransfer(CreateTransferModel createTransferModel);

}
