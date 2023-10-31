package com.ms.account.entrypoint.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.account.core.model.CreateAccountModel;
import com.ms.account.core.model.GetAccountModel;
import com.ms.account.core.ports.in.service.IAccountServicePort;
import com.ms.account.dummy.AccountDummy;
import com.ms.account.entrypoint.rest.UrlConstant;
import com.ms.account.entrypoint.rest.assembler.AccountAssembler;
import com.ms.account.entrypoint.rest.dto.request.CreateAccountRequestDTO;
import com.ms.account.entrypoint.rest.dto.response.GetAccountResponseDTO;
import com.ms.account.entrypoint.rest.mapper.AccountEntrypointMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountController.class)
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IAccountServicePort IAccountServicePort;

    @MockBean
    private AccountEntrypointMapper accountEntrypointMapper;

    @MockBean
    private AccountAssembler accountAssembler;

    @Test
    void testGetAccountWhenValidAccountIdThenReturnAccount() throws Exception {
        UUID accountId = UUID.randomUUID();
        GetAccountModel getAccountModel = AccountDummy.getAccountModelBuilder()
                .id(accountId)
                .build();
        GetAccountResponseDTO responseDTO = AccountDummy.getAccountResponseDTOBuilder()
                .id(accountId)
                .build();

        when(IAccountServicePort.getAccount(accountId)).thenReturn(getAccountModel);
        when(accountAssembler.toResponse(getAccountModel)).thenReturn(responseDTO);

        mockMvc.perform(get(UrlConstant.ACCOUNT_URI + "/" + accountId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(accountId.toString()));

        verify(IAccountServicePort).getAccount(accountId);
    }

    @Test
    void testGetAccountWhenInvalidAccountIdThenReturnNotFound() throws Exception {
        UUID accountId = UUID.randomUUID();

        when(IAccountServicePort.getAccount(accountId)).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND));

        mockMvc.perform(get(UrlConstant.ACCOUNT_URI + "/" + accountId))
                .andExpect(status().isNotFound());

        verify(IAccountServicePort).getAccount(accountId);
    }

    @Test
    void testGetAccountWhenAccountPortThrowsExceptionThenReturnInternalServerError() throws Exception {
        UUID accountId = UUID.randomUUID();

        when(IAccountServicePort.getAccount(accountId)).thenThrow(new RuntimeException());

        mockMvc.perform(get(UrlConstant.ACCOUNT_URI + "/" + accountId))
                .andExpect(status().isInternalServerError());

        verify(IAccountServicePort).getAccount(accountId);
    }

    @Test
    void testCreateAccountWhenValidRequestThenReturnCreated() throws Exception {
        CreateAccountRequestDTO requestDTO = AccountDummy.createAccountRequestDTOBuilder().build();
        CreateAccountModel createAccountModel = AccountDummy.createAccountModelBuilder().build();

        when(accountEntrypointMapper.fromCreateAccountRequestDTOToAccountModel(requestDTO)).thenReturn(createAccountModel);

        mockMvc.perform(MockMvcRequestBuilders.post(UrlConstant.ACCOUNT_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated());

        verify(IAccountServicePort).save(createAccountModel);
    }

    @Test
    void testCreateAccountWhenInvalidRequestThenReturnBadRequest() throws Exception {
        CreateAccountRequestDTO requestDTO = AccountDummy.createAccountRequestDTOBuilder()
                .firstName("")
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post(UrlConstant.ACCOUNT_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isBadRequest());
    }
}