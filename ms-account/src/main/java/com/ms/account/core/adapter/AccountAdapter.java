package com.ms.account.core.adapter;

import com.ms.account.core.model.AccountModel;
import com.ms.account.core.ports.in.AccountPort;
import com.ms.account.core.ports.out.repository.IAccountRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountAdapter implements AccountPort {
    private final IAccountRepositoryPort accountRepositoryPort;
    @Override
    public void save(AccountModel accountModel) {
        accountRepositoryPort.save(accountModel);
    }
}
