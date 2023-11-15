package com.minibank.transfers.infrastructure.integration.ms.account.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.minibank.transfers.infrastructure.entity.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GetAccountHttpClientResponseDTO extends RepresentationModel<GetAccountHttpClientResponseDTO> implements Serializable {
    @Serial
    private static final long serialVersionUID = -2821698457681870221L;

    private UUID id;
    private String firstName;
    private String email;
    private String document;
    private String password;
    private AccountType type;

}
