package com.ms.account.infrastructure.mapper;


import com.ms.account.core.model.AccountModel;
import com.ms.account.infrastructure.entity.AccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountInfrastructureMapper {
    AccountEntity fromAccountModelToAccountEntity(AccountModel accountModel);

}
