package com.ms.account.core.adapter.pubsub;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.ms.account.core.ports.in.pubsub.IPubSubPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SnsPubSub implements IPubSubPort {

    private final AmazonSNSClient snsTemplate;

    @Value("${aws.sns.topic.id}")
    private String topicId;


    @Override
    public void subscribe(String email) {
        SubscribeRequest subscribeRequest = new SubscribeRequest(topicId, "email", email);

        snsTemplate.subscribe(subscribeRequest);
        log.info("Email {} Subscribed!", email);
    }

    @Override
    public void publish(String message, String subject) {
        PublishRequest publishRequest = new PublishRequest(topicId, message, subject);
        snsTemplate.publish(publishRequest);
        log.info("Message published to all subscribers of topic {}!", topicId);
    }
}
