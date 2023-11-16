package com.ms.account.core.model;

import com.ms.account.infrastructure.entity.enums.AccountType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class AccountModelBase {

    private String firstName;
    private String email;
    private String document;
    private String password;
    private AccountType type;
    private BigDecimal balance;

}
