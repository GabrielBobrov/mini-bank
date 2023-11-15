package com.minibank.transfers.infrastructure.integration.configuration.webclient.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class WebClientErrorResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = -1014649972014841902L;
    private String userMessage;
    private String title;
    private String type;
}
