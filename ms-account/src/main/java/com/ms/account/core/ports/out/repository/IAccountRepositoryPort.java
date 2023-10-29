package com.ms.account.core.ports.out.repository;

import com.ms.account.core.model.AccountModel;

public interface IAccountRepositoryPort {

    void save(AccountModel accountModel);
}
