package com.ms.account.core.ports.out.repository;

import com.ms.account.core.model.CreateAccountModel;

public interface IAccountRepositoryPort {

    void save(CreateAccountModel createAccountModel);
}
