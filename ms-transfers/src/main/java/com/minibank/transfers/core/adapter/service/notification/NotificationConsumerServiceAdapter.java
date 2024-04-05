package com.minibank.transfers.core.adapter.service.notification;

import com.minibank.transfers.core.ports.in.service.notification.INotificationConsumerServicePort;
import com.minibank.transfers.infrastructure.entity.transfer.TransferEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * Adapter for the notification consumer service.
 */
@Slf4j
@Service
@AllArgsConstructor
public class NotificationConsumerServiceAdapter implements INotificationConsumerServicePort {


    @Override
    @KafkaListener(topics = "transfer-notification", groupId = "transfer-notification-consumer")
    public void receiveNotification(TransferEntity transaction) {
        log.info("notifying transaction {}...", transaction);

        log.info("notification has been sent ...");
    }


}
