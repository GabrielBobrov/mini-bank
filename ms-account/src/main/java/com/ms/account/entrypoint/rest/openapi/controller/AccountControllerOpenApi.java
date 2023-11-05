package com.ms.account.entrypoint.rest.openapi.controller;


import com.ms.account.entrypoint.rest.dto.request.CreateAccountRequestDTO;
import com.ms.account.entrypoint.rest.dto.response.GetAccountResponseDTO;
import com.ms.account.entrypoint.rest.exception.model.Problem;
import com.ms.account.infrastructure.filter.AccountFilter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@Tag(name = "Accounts")
public interface AccountControllerOpenApi {
    @Operation(summary = "Criar conta",
            responses = {
                    @ApiResponse(responseCode = "201"),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content(schema = @Schema(implementation = Problem.class)))
            })
    void createAccount(@RequestBody @Valid CreateAccountRequestDTO accountRequestDTO);

    @Operation(summary = "Recuperar conta",
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetAccountResponseDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Conta não encontrada", content = @Content(schema = @Schema(implementation = Problem.class)))
            })
    GetAccountResponseDTO getAccount(@PathVariable UUID accountId);

    @Operation(summary = "Recuperar contas",
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetAccountResponseDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Contas não encontradas", content = @Content(schema = @Schema(implementation = Problem.class)))
            })
    PagedModel<GetAccountResponseDTO> getAccounts(@Valid AccountFilter accountFilter,
                                                  @PageableDefault Pageable pageable);


}
