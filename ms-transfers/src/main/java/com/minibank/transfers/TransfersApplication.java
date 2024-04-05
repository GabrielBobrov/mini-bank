package com.minibank.transfers;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

@SpringBootApplication
public class TransfersApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransfersApplication.class, args);
    }

}
