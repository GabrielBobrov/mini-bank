package com.ms.account.core.adapter.service;

import com.ms.account.core.model.CreateAccountModel;
import com.ms.account.core.ports.in.AccountPort;
import com.ms.account.core.ports.out.repository.IAccountRepositoryPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountPort {
    private final IAccountRepositoryPort accountRepositoryPort;

    @Override
    public void save(CreateAccountModel createAccountModel) {
        log.info("Class {} method save", this.getClass().getName());
        log.info("AccountModel {}", createAccountModel);

        accountRepositoryPort.save(createAccountModel);
    }
}
