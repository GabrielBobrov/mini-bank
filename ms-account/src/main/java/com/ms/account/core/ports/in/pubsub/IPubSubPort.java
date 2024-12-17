package com.ms.account.core.ports.in.pubsub;

public interface IPubSubPort {

    void subscribe(String email);

    void publish(String message, String subject);
}
