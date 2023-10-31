package com.ms.account.core.adapter.service;

import com.ms.account.core.model.CreateAccountModel;
import com.ms.account.core.model.GetAccountModel;
import com.ms.account.core.ports.in.service.IAccountServicePort;
import com.ms.account.core.ports.out.repository.IAccountRepositoryPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class IAccountServiceServiceImpl implements IAccountServicePort {
    private final IAccountRepositoryPort accountRepositoryPort;

    @Override
    public void save(CreateAccountModel createAccountModel) {
        log.info("Class {} method save", this.getClass().getName());
        log.info("AccountModel {}", createAccountModel);

        accountRepositoryPort.save(createAccountModel);
    }

    @Override
    public GetAccountModel getAccount(UUID id) {
        log.info("Class {} method getAccount", this.getClass().getName());

        return accountRepositoryPort.getAccount(id);
    }
}