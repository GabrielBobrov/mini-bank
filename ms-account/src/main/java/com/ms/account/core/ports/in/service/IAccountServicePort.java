package com.ms.account.core.ports.in.service;

import com.ms.account.core.model.CreateAccountModel;
import com.ms.account.core.model.GetAccountModel;

import java.util.UUID;

public interface IAccountServicePort {

    void save(CreateAccountModel createAccountModel);

    GetAccountModel getAccount(UUID id);
}
