package com.ms.account.core.ports.in.messaging;

import com.ms.account.core.model.CreateAccountMessagingModel;

public interface IMessagingPort {

    void sendMessage(CreateAccountMessagingModel createAccountModel);
}
