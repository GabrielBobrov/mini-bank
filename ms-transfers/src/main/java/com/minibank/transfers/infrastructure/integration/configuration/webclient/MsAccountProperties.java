package com.minibank.transfers.infrastructure.integration.configuration.webclient;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Getter
@Setter
@Validated
@ConfigurationProperties("account")
public class MsAccountProperties {

    @NotBlank
    private String host;

}
