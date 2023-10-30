package com.ms.account.infrastructure.repository.adapter;

import com.ms.account.core.exception.NotFoundException;
import com.ms.account.core.model.CreateAccountModel;
import com.ms.account.core.model.GetAccountModel;
import com.ms.account.core.ports.out.repository.IAccountRepositoryPort;
import com.ms.account.infrastructure.entity.AccountEntity;
import com.ms.account.infrastructure.mapper.AccountInfrastructureMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@AllArgsConstructor
public class AccountRepositoryAdapter implements IAccountRepositoryPort {

    private final ISpringAccountRepositoryAdapter springAccountRepository;
    private final AccountInfrastructureMapper accountInfrastructureMapper;

    @Override
    @Transactional
    public void save(CreateAccountModel createAccountModel) {
        log.info("Class {} method save", this.getClass().getName());
        log.info("AccountModel {}", createAccountModel);

        AccountEntity accountEntity = accountInfrastructureMapper.fromAccountModelToAccountEntity(createAccountModel);
        log.info("AccountEntity {}", accountEntity);

        springAccountRepository.save(accountEntity);
    }

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
