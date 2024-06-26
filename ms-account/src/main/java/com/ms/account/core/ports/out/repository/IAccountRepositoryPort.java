package com.ms.account.core.ports.out.repository;

import com.ms.account.core.model.CreateAccountModel;
import com.ms.account.core.model.GetAccountModel;
import com.ms.account.infrastructure.filter.AccountFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.UUID;

public interface IAccountRepositoryPort {

    void create(CreateAccountModel createAccountModel);

    GetAccountModel getAccount(UUID id);

    Page<GetAccountModel> getAccounts(AccountFilter accountFilter, Pageable pageable);

    Boolean existsByDocumentOrEmail(String document, String email);

    void updateBalance(BigDecimal balance,  GetAccountModel getAccountModel);

    GetAccountModel getAccountByEmail(String emailAddress);


}
