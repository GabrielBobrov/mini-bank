package com.minibank.transfers.core.adapter.service.notification;

import com.minibank.transfers.core.ports.in.service.notification.INotificationProducerServicePort;
import com.minibank.transfers.infrastructure.entity.transfer.TransferEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Adapter for the notification producer service.
 */
@Slf4j
@Service
@AllArgsConstructor
public class NotificationProducerServiceAdapter implements INotificationProducerServicePort {

    private final KafkaTemplate<String, TransferEntity> kafkaTemplate;

    public void sendNotification(TransferEntity transaction) {
        kafkaTemplate.send("transfer-notification", transaction);
    }


}
