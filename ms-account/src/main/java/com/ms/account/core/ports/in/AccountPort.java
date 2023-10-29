package com.ms.account.core.ports.in;

import com.ms.account.core.model.AccountModel;

public interface AccountPort {

    void save(AccountModel accountModel);
}
