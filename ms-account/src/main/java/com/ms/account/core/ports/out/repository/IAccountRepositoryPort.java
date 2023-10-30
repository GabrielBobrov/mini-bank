package com.ms.account.core.ports.out.repository;

import com.ms.account.core.model.CreateAccountModel;
import com.ms.account.core.model.GetAccountModel;

import java.util.UUID;

public interface IAccountRepositoryPort {

    void save(CreateAccountModel createAccountModel);

    GetAccountModel getAccount(UUID id);
}
