package com.ms.account.infrastructure.filter;

import com.ms.account.infrastructure.entity.enums.AccountType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class    AccountFilter {
    private UUID id;
    private AccountType type;
    private LocalDateTime createdAt;
    private String firstName;
    @CPF
    private String document;
}
