package com.ms.account.core.adapter.service;

import com.ms.account.core.exception.AccountAlreadyExistsException;
import com.ms.account.core.mapper.IMessagingCoreMapper;
import com.ms.account.core.model.CreateAccountModel;
import com.ms.account.core.model.GetAccountModel;
import com.ms.account.core.ports.in.messaging.IMessagingPort;
import com.ms.account.core.ports.in.pubsub.IPubSubPort;
import com.ms.account.core.ports.in.service.IAccountServicePort;
import com.ms.account.core.ports.out.repository.IAccountRepositoryPort;
import com.ms.account.infrastructure.filter.AccountFilter;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

/**
 * The AccountServiceAdapterImpl class is an implementation of the IAccountServicePort interface.
 * It provides the implementation for saving and retrieving account information.
 * <p>
 * This class is annotated with @Service to indicate that it is a Spring service component.
 * <p>
 * The class has a constructor that takes an instance of IAccountRepositoryPort as a parameter.
 * This dependency is injected using constructor injection.
 * <p>
 * The class has the following methods:
 * save: Saves the account information provided in the CreateAccountModel object.
 * getAccount: Retrieves the account information for the specified ID.
 * <p>
 * This class also uses the SLF4J logging framework to log information.
 *
 * @see IAccountServicePort
 * @see IAccountRepositoryPort
 * @see CreateAccountModel
 * @see GetAccountModel
 */
@Slf4j
@Service
@AllArgsConstructor
public class AccountServiceAdapterImpl implements IAccountServicePort {

    private final IAccountRepositoryPort accountRepositoryPort;
    private final IMessagingPort messagingPort;
    private final IPubSubPort pubSubPort;
    private final IMessagingCoreMapper messagingCoreMapper;

    @Override
    @Transactional
    public void createAccount(CreateAccountModel createAccountModel) {
        log.info("Class {} method createAccount", this.getClass().getName());
        log.info("CreateAccountModel {}", createAccountModel);

        Boolean accountAlreadyExists = accountRepositoryPort.existsByDocumentOrEmail(createAccountModel.getDocument(), createAccountModel.getEmail());

        accountRepositoryPort.create(createAccountModel);
        messagingPort.sendMessage(messagingCoreMapper.fromCreateAccountModelToCreateAccountMessagingModel(createAccountModel));
    }
    @Override
    public GetAccountModel getAccount(UUID id) {
        log.info("Class {} method getAccount", this.getClass().getName());

        return accountRepositoryPort.getAccount(id);
    }

    @Override
    public GetAccountModel getAccountByEmail(String email) {
        log.info("Class {} method getAccountByEmail", this.getClass().getName());

        return accountRepositoryPort.getAccountByEmail(email);
    }

    @Override
    public Page<GetAccountModel> getAccounts(AccountFilter accountFilter, Pageable pageable) {
        log.info("Class {} method getAccounts", this.getClass().getName());

        return accountRepositoryPort.getAccounts(accountFilter, pageable);
    }

    @Override
    @Transactional
    public void updateBalance(BigDecimal balance, UUID id) {
        log.info("Class {} method updateBalance", this.getClass().getName());
        GetAccountModel account = accountRepositoryPort.getAccount(id);

        accountRepositoryPort.updateBalance(balance, account);
    }
}
