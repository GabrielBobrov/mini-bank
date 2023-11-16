package com.ms.account.entrypoint.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ms.account.infrastructure.entity.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GetAccountResponseDTO extends RepresentationModel<GetAccountResponseDTO> implements Serializable {
    @Serial
    private static final long serialVersionUID = -2821698457681870221L;

    private UUID id;
    private String firstName;
    private String email;
    private String document;
    private String password;
    private AccountType type;
    private BigDecimal balance;
}
