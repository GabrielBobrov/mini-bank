package com.ms.account.entrypoint.rest.controller;

import com.ms.account.core.ports.in.AccountPort;
import com.ms.account.entrypoint.rest.UrlConstant;
import com.ms.account.entrypoint.rest.dto.request.CreateAccountRequestDTO;
import com.ms.account.entrypoint.rest.dto.response.CreateAccountResponseDTO;
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
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateAccountResponseDTO createAccount(@RequestBody CreateAccountRequestDTO clientRequest) {
        return CreateAccountResponseDTO.builder().build();
    }
}



