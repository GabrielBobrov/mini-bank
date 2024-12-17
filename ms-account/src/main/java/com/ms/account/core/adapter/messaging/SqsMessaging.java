package com.ms.account.core.adapter.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.account.core.adapter.pubsub.SnsPubSub;
import com.ms.account.core.model.CreateAccountMessagingModel;
import com.ms.account.core.ports.in.messaging.IMessagingPort;
import com.ms.account.core.ports.in.service.log.ILogServicePort;
import io.awspring.cloud.sqs.annotation.SqsListener;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SqsMessaging implements IMessagingPort {

    private final SqsTemplate sqsTemplate;
    private final SnsPubSub snsPubSub;
    private final ILogServicePort s3Service;


    private final ObjectMapper objectMapper;

    @Value("${spring.cloud.aws.sqs.queue-url}")
    private String queueUrl;

    @Override
    public void sendMessage(CreateAccountMessagingModel createAccountModel) {
        String messageBody = null;
        try {
            messageBody = objectMapper.writeValueAsString(createAccountModel);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        sqsTemplate.send(queueUrl, messageBody);
    }


    @SqsListener("${spring.cloud.aws.sqs.queue-url}")
    public void receiveMessage(String message) throws JsonProcessingException {
        CreateAccountMessagingModel createAccountModel = objectMapper.readValue(message, CreateAccountMessagingModel.class);
        log.info("Received message to create account: {}", createAccountModel);

        s3Service.log("/" + createAccountModel.getDocument(), createAccountModel);

        //snsPubSub.subscribe(createAccountModel.getEmail());
    }

}
