package com.ms.account.infrastructure.aws.s3.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("aws.s3")
public class S3Properties {

    private String bucket;
    private String directory;

}
