package com.minibank.transfers.core.adapter.service.notification;

import com.minibank.transfers.core.ports.in.service.notification.INotificationServicePort;
import com.minibank.transfers.infrastructure.entity.transfer.TransferEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Adapter for the notification service.
 */
@Slf4j
@Service
@AllArgsConstructor
public class NotificationServiceAdapter implements INotificationServicePort {

    private NotificationProducerServiceAdapter notificationProducer;

    @Override
    public void notify(TransferEntity transaction) {
        log.info("notifying transaction {}...", transaction);

        notificationProducer.sendNotification(transaction);
    }


}
