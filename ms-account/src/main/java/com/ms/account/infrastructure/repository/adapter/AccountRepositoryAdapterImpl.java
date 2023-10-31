package com.ms.account.infrastructure.repository.adapter;

import com.ms.account.core.exception.NotFoundException;
import com.ms.account.core.model.CreateAccountModel;
import com.ms.account.core.model.GetAccountModel;
import com.ms.account.core.ports.out.repository.IAccountRepositoryPort;
import com.ms.account.infrastructure.entity.AccountEntity;
import com.ms.account.infrastructure.mapper.IAccountInfrastructureMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;


/**
 * The AccountRepositoryAdapterImpl class is an implementation of the IAccountRepositoryPort interface.
 * It provides the implementation for saving and retrieving account information from the Spring Data JPA repository.
 * <p>
 * This class is annotated with @Component to indicate that it is a Spring component.
 * <p>
 * The class has a constructor that takes instances of ISpringAccountRepositoryAdapter and IAccountInfrastructureMapper as parameters.
 * These dependencies are injected using constructor injection.
 * <p>
 * The class has the following methods:
 * save: Saves the account information provided in the CreateAccountModel object.
 * getAccount: Retrieves the account information for the specified ID.
 * <p>
 * This class also uses the SLF4J logging framework to log information.
 *
 * @see IAccountRepositoryPort
 * @see ISpringAccountRepositoryAdapter
 * @see IAccountInfrastructureMapper
 * @see CreateAccountModel
 * @see GetAccountModel
 * @see AccountEntity
 * @see NotFoundException
 */
@Slf4j
@Component
@AllArgsConstructor
public class AccountRepositoryAdapterImpl implements IAccountRepositoryPort {

    private final ISpringAccountRepositoryAdapter springAccountRepository;
    private final IAccountInfrastructureMapper accountInfrastructureMapper;

    /**
     * Saves the account information provided in the CreateAccountModel object.
     *
     * @param createAccountModel The CreateAccountModel object containing the account information to be saved.
     */
    @Override
    @Transactional
    public void save(CreateAccountModel createAccountModel) {
        log.info("Class {} method save", this.getClass().getName());
        log.info("AccountModel {}", createAccountModel);

        AccountEntity accountEntity = accountInfrastructureMapper.fromAccountModelToAccountEntity(createAccountModel);
        log.info("AccountEntity {}", accountEntity);

        springAccountRepository.save(accountEntity);
    }

    /**
     * Retrieves the account information for the specified ID.
     *
     * @param id The ID of the account to retrieve.
     * @return The GetAccountModel object containing the retrieved account information.
     * @throws NotFoundException if the account with the specified ID is not found.
     */
    @Override
    public GetAccountModel getAccount(UUID id) {
        log.info("Class {} method getAccount", this.getClass().getName());

        AccountEntity accountEntity = springAccountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Conta não encontrada com id " + id));
        log.info("AccountEntity {}", accountEntity);

        GetAccountModel getAccountModel = accountInfrastructureMapper.fromAccountEntityTGetAccountModel(accountEntity);
        log.info("GetAccountModel {}", getAccountModel);
        return getAccountModel;
    }
}
