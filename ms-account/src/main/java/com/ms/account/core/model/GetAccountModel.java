package com.ms.account.core.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class GetAccountModel extends AccountModelBase {

    private UUID id;

}
