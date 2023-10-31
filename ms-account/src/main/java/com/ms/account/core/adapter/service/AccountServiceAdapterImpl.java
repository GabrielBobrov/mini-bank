package com.ms.account.core.adapter.service;

import com.ms.account.core.model.CreateAccountModel;
import com.ms.account.core.model.GetAccountModel;
import com.ms.account.core.ports.in.service.IAccountServicePort;
import com.ms.account.core.ports.out.repository.IAccountRepositoryPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 The AccountServiceAdapterImpl class is an implementation of the IAccountServicePort interface.
 It provides the implementation for saving and retrieving account information.

 This class is annotated with @Service to indicate that it is a Spring service component.

 The class has a constructor that takes an instance of IAccountRepositoryPort as a parameter.
 This dependency is injected using constructor injection.

 The class has the following methods:
 save: Saves the account information provided in the CreateAccountModel object.
 getAccount: Retrieves the account information for the specified ID.

 This class also uses the SLF4J logging framework to log information.

 @see IAccountServicePort
 @see IAccountRepositoryPort
 @see CreateAccountModel
 @see GetAccountModel
 */
@Slf4j
@Service
@AllArgsConstructor
public class AccountServiceAdapterImpl implements IAccountServicePort {
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
