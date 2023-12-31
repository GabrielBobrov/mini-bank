package com.ms.account.infrastructure.mapper;


import com.ms.account.core.model.CreateAccountModel;
import com.ms.account.core.model.GetAccountModel;
import com.ms.account.infrastructure.entity.AccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAccountInfrastructureMapper {
    AccountEntity fromCreateAccountModelToAccountEntity(CreateAccountModel createAccountModel);

    GetAccountModel fromAccountEntityToGetAccountModel(AccountEntity accountEntity);

    List<GetAccountModel> fromListAccountEntityToListGetAccountModel(List<AccountEntity> accountEntity);

    AccountEntity fromGetAccountModelToAccountEntity(GetAccountModel getAccountModel);

}
