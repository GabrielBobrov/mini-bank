package com.ms.account.core.mapper;


import com.ms.account.core.model.CreateAccountMessagingModel;
import com.ms.account.core.model.CreateAccountModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IMessagingCoreMapper {
    CreateAccountMessagingModel fromCreateAccountModelToCreateAccountMessagingModel(CreateAccountModel createAccountModel);

}
