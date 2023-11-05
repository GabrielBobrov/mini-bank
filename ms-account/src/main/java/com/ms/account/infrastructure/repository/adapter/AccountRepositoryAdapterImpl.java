package com.ms.account.infrastructure.repository.adapter;

import com.ms.account.core.exception.NotFoundException;
import com.ms.account.core.model.CreateAccountModel;
import com.ms.account.core.model.GetAccountModel;
import com.ms.account.core.ports.out.repository.IAccountRepositoryPort;
import com.ms.account.infrastructure.data.PageableTranslator;
import com.ms.account.infrastructure.entity.AccountEntity;
import com.ms.account.infrastructure.filter.AccountFilter;
import com.ms.account.infrastructure.mapper.IAccountInfrastructureMapper;
import com.ms.account.infrastructure.specs.AccountSpecs;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
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

        GetAccountModel getAccountModel = accountInfrastructureMapper.fromAccountEntityToGetAccountModel(accountEntity);
        log.info("GetAccountModel {}", getAccountModel);
        return getAccountModel;
    }


    /**
     * Retrieves the account information based on the provided filter and pagination configuration.
     *
     * @param accountFilter The filter to apply when retrieving the accounts.
     * @param pageable      The pagination configuration.
     * @return A Page of GetAccountModel objects containing the retrieved account information.
     */
    @Override
    public Page<GetAccountModel> getAccounts(AccountFilter accountFilter, Pageable pageable) {
        log.info("Class {} method getAccounts", this.getClass().getName());

        pageable = translatePageable(pageable);
        Specification<AccountEntity> accountEntitySpecification = AccountSpecs.usingFilter(accountFilter);
        Page<AccountEntity> accounts = springAccountRepository.findAll(accountEntitySpecification, pageable);

        List<GetAccountModel> getAccountModels = accountInfrastructureMapper.fromListAccountEntityToListGetAccountModel(accounts.getContent());
        return new PageImpl<>(getAccountModels, pageable, accounts.getTotalElements());
    }

    /**
     * Checks if a user exists based on the provided document or email.
     *
     * @param createAccountModel The CreateAccountModel object containing the document and email to be checked.
     * @return true if a user exists with the provided document or email, false otherwise.
     */
    @Override
    public Boolean existsByDocumentOrEmail(CreateAccountModel createAccountModel) {
        log.info("Class {} method existsByDocumentOrEmail", this.getClass().getName());

        return springAccountRepository.existsByDocumentOrEmail(createAccountModel.getDocument(), createAccountModel.getEmail());
    }

    private Pageable translatePageable(Pageable apiPageable) {
        var mapping = Map.of(
                "firstName", "firstName",
                "email", "email",
                "document", "document",
                "password", "password",
                "type", "type"
        );

        return PageableTranslator.translate(apiPageable, mapping);
    }
}
