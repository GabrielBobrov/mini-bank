package com.ms.account.infrastructure.mapper;


import com.ms.account.core.model.CreateAccountModel;
import com.ms.account.core.model.GetAccountModel;
import com.ms.account.infrastructure.entity.AccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAccountInfrastructureMapper {
    AccountEntity fromAccountModelToAccountEntity(CreateAccountModel createAccountModel);

    GetAccountModel fromAccountEntityTGetAccountModel(AccountEntity accountEntity);


}
