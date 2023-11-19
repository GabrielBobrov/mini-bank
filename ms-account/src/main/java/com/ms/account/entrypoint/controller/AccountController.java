package com.ms.account.entrypoint.controller;

import com.ms.account.core.model.CreateAccountModel;
import com.ms.account.core.model.GetAccountModel;
import com.ms.account.core.ports.in.service.IAccountServicePort;
import com.ms.account.entrypoint.assembler.AccountAssembler;
import com.ms.account.entrypoint.dto.request.UpdateBalanceRequestDTO;
import com.ms.account.entrypoint.mapper.IAccountEntrypointMapper;
import com.ms.account.entrypoint.openapi.controller.AccountControllerOpenApi;
import com.ms.account.entrypoint.UrlConstant;
import com.ms.account.entrypoint.dto.request.CreateAccountRequestDTO;
import com.ms.account.entrypoint.dto.response.GetAccountResponseDTO;
import com.ms.account.infrastructure.filter.AccountFilter;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.UUID;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = UrlConstant.ACCOUNT_URI)
public class AccountController implements AccountControllerOpenApi {

    private final IAccountServicePort accountServicePort;
    private final IAccountEntrypointMapper accountEntrypointMapper;
    private final AccountAssembler accountAssembler;
    private final PagedResourcesAssembler<GetAccountModel> getAccountModelPagedResourcesAssembler;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createAccount(@RequestBody @Valid CreateAccountRequestDTO accountRequestDTO) {
        log.info("Class {} method createAccount", this.getClass().getName());
        log.info("CreateAccountRequestDTO {}", accountRequestDTO);

        CreateAccountModel createAccountModel = accountEntrypointMapper.fromCreateAccountRequestDTOToCreateAccountModel(accountRequestDTO);

        accountServicePort.createAccount(createAccountModel);
    }

    @GetMapping("{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public GetAccountResponseDTO getAccount(@PathVariable UUID accountId) {
        log.info("Class {} method getAccount", this.getClass().getName());

        GetAccountModel account = accountServicePort.getAccount(accountId);

        GetAccountResponseDTO response = accountAssembler.toModel(account);
        log.info("GetAccountResponseDTO {}", response);

        return response;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PagedModel<GetAccountResponseDTO> getAccounts(@Valid AccountFilter accountFilter,
                                                         @PageableDefault Pageable pageable) {
        log.info("Class {} method getAccounts", this.getClass().getName());

        Page<GetAccountModel> accounts = accountServicePort.getAccounts(accountFilter, pageable);
        PagedModel<GetAccountResponseDTO> responseDTOS = getAccountModelPagedResourcesAssembler.toModel(accounts, accountAssembler);
        log.info("PagedModel<GetAccountResponseDTO> {}", responseDTOS);

        return responseDTOS;
    }

    @PatchMapping("{accountId}/balance")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBalance(@PathVariable UUID accountId,
                              @Valid @RequestBody UpdateBalanceRequestDTO updateBalanceRequestDTO) {

        log.info("Class {} method updateBalance", this.getClass().getName());

        accountServicePort.updateBalance(updateBalanceRequestDTO.getBalance(), accountId);
    }
}



