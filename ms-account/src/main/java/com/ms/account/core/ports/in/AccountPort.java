package com.ms.account.core.ports.in;

import com.ms.account.core.model.CreateAccountModel;

public interface AccountPort {

    void save(CreateAccountModel createAccountModel);
}
