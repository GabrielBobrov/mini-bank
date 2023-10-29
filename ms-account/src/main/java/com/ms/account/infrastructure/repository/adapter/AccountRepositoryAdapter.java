package com.ms.account.infrastructure.repository.adapter;

import com.ms.account.core.model.AccountModel;
import com.ms.account.core.ports.out.repository.IAccountRepositoryPort;
import com.ms.account.infrastructure.entity.AccountEntity;
import com.ms.account.infrastructure.mapper.AccountInfrastructureMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class AccountRepositoryAdapter implements IAccountRepositoryPort {

    private final ISpringAccountRepositoryAdapter springAccountRepository;
    private final AccountInfrastructureMapper accountInfrastructureMapper;

    @Override
    @Transactional
    public void save(AccountModel accountModel) {
        log.info("Class {} method save", this.getClass().getName());
        log.info("AccountModel {}", accountModel);

        AccountEntity accountEntity = accountInfrastructureMapper.fromAccountModelToAccountEntity(accountModel);
        log.info("AccountEntity {}", accountEntity);

        springAccountRepository.save(accountEntity);
    }
}
