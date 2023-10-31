package com.ms.account.entrypoint.rest.controller;

import com.ms.account.core.model.CreateAccountModel;
import com.ms.account.core.model.GetAccountModel;
import com.ms.account.core.ports.in.service.IAccountServicePort;
import com.ms.account.entrypoint.rest.UrlConstant;
import com.ms.account.entrypoint.rest.assembler.AccountAssembler;
import com.ms.account.entrypoint.rest.dto.request.CreateAccountRequestDTO;
import com.ms.account.entrypoint.rest.dto.response.GetAccountResponseDTO;
import com.ms.account.entrypoint.rest.mapper.AccountEntrypointMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = UrlConstant.ACCOUNT_URI)
public class AccountController {

    private final IAccountServicePort IAccountServicePort;
    private final AccountEntrypointMapper accountEntrypointMapper;
    private final AccountAssembler accountAssembler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createAccount(@RequestBody @Valid CreateAccountRequestDTO accountRequestDTO) {
        log.info("Class {} method save", this.getClass().getName());
        log.info("CreateAccountRequestDTO {}", accountRequestDTO);

        CreateAccountModel createAccountModel = accountEntrypointMapper.fromCreateAccountRequestDTOToAccountModel(accountRequestDTO);

        IAccountServicePort.save(createAccountModel);
    }

    @GetMapping("{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public GetAccountResponseDTO getAccount(@PathVariable UUID accountId) {
        log.info("Class {} method getAccount", this.getClass().getName());

        GetAccountModel account = IAccountServicePort.getAccount(accountId);

        GetAccountResponseDTO response = accountAssembler.toResponse(account);
        log.info("GetAccountResponseDTO {}", response);

        return response;
    }
}



