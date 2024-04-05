package com.minibank.transfers.core.ports.in.service.notification;

import com.minibank.transfers.infrastructure.entity.transfer.TransferEntity;


public interface INotificationConsumerServicePort {

    void receiveNotification(TransferEntity transaction);

}
