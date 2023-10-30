package com.ms.account.entrypoint.rest.controller;

import com.ms.account.core.model.AccountModel;
import com.ms.account.core.ports.in.AccountPort;
import com.ms.account.entrypoint.rest.UrlConstant;
import com.ms.account.entrypoint.rest.dto.request.CreateAccountRequestDTO;
import com.ms.account.entrypoint.rest.dto.response.CreateAccountResponseDTO;
import com.ms.account.entrypoint.rest.mapper.AccountEntrypointMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = UrlConstant.ACCOUNT_URI)
public class AccountController {

    private final AccountPort accountPort;
    private final AccountEntrypointMapper accountEntrypointMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createAccount(@RequestBody @Valid CreateAccountRequestDTO accountRequestDTO) {
        log.info("Class {} method save", this.getClass().getName());
        log.info("CreateAccountRequestDTO {}", accountRequestDTO);

        AccountModel accountModel = accountEntrypointMapper.fromCreateAccountRequestDTOToAccountModel(accountRequestDTO);

        accountPort.save(accountModel);
    }
}



