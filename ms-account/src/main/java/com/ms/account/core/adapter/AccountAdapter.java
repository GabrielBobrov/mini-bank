package com.ms.account.core.adapter;

import com.ms.account.core.model.AccountModel;
import com.ms.account.core.ports.in.AccountPort;
import com.ms.account.core.ports.out.repository.IAccountRepositoryPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class AccountAdapter implements AccountPort {
    private final IAccountRepositoryPort accountRepositoryPort;

    @Override
    public void save(AccountModel accountModel) {
        log.info("Class {} method save", this.getClass().getName());
        log.info("AccountModel {}", accountModel);

        accountRepositoryPort.save(accountModel);
    }
}
