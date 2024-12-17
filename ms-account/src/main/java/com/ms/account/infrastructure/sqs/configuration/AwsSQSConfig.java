package com.ms.account.infrastructure.sqs.configuration;

import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

@Configuration
public class AwsSQSConfig {

  @Value("${spring.cloud.aws.region.static}")
  private String region;

  @Bean
  SqsAsyncClient sqsAsyncClient(){
    return SqsAsyncClient
            .builder()
            .region(Region.of(region))
            .credentialsProvider(DefaultCredentialsProvider.create())
            .build();
  }

  @Bean
  public SqsTemplate sqsTemplate(SqsAsyncClient sqsAsyncClient){
    return SqsTemplate.builder().sqsAsyncClient(sqsAsyncClient).build();
  }
}