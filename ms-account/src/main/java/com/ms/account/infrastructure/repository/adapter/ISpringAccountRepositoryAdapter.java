package com.ms.account.infrastructure.repository.adapter;

import com.ms.account.infrastructure.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ISpringAccountRepositoryAdapter extends JpaRepository<AccountEntity, UUID>, JpaSpecificationExecutor<AccountEntity> {
}
