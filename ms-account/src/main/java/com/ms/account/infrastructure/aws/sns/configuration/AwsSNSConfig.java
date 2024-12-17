package com.ms.account.infrastructure.aws.sns.configuration;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsSNSConfig {

  @Value("${spring.cloud.aws.region.static}")
  private String region;

  @Bean
  public AmazonSNSClient getSnsClient() {
    return (AmazonSNSClient) AmazonSNSClientBuilder.standard()
            .withRegion(region)
            .withCredentials(DefaultAWSCredentialsProviderChain.getInstance())
            .build();
  }
}