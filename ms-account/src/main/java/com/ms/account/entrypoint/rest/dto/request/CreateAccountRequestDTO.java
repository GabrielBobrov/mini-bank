package com.ms.account.entrypoint.rest.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CreateAccountRequestDTO  implements Serializable {

    @Serial
    private static final long serialVersionUID = -2821698457681870221L;

    @NotBlank
    private String firstName;

    @Email
    @NotBlank
    private String email;

    @CPF
    @NotBlank
    private String document;

    @NotBlank
    private String password;
}
