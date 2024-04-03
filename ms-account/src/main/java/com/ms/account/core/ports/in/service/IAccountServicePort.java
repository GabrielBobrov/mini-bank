package com.ms.account.core.ports.in.service;

import com.ms.account.core.model.CreateAccountModel;
import com.ms.account.core.model.GetAccountModel;
import com.ms.account.infrastructure.filter.AccountFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.UUID;

public interface IAccountServicePort {

    void createAccount(CreateAccountModel createAccountModel);

    GetAccountModel getAccount(UUID id);

    GetAccountModel getAccountByEmail(String email);


    Page<GetAccountModel> getAccounts(AccountFilter accountFilter, Pageable pageable);

    void updateBalance(BigDecimal amount, UUID id);
}
