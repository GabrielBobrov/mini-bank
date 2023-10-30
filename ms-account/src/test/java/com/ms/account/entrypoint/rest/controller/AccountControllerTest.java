package com.ms.account.entrypoint.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.account.core.model.AccountModel;
import com.ms.account.core.ports.in.AccountPort;
import com.ms.account.dummy.AccountDummy;
import com.ms.account.entrypoint.rest.UrlConstant;
import com.ms.account.entrypoint.rest.dto.request.CreateAccountRequestDTO;
import com.ms.account.entrypoint.rest.mapper.AccountEntrypointMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebMvcTest(AccountController.class)
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AccountPort accountPort;

    @MockBean
    private AccountEntrypointMapper accountEntrypointMapper;

    @Test
    void testCreateAccountWhenValidRequestThenReturnCreated() throws Exception {
        CreateAccountRequestDTO requestDTO = AccountDummy.createAccountRequestDTOBuilder().build();
        AccountModel accountModel = AccountDummy.accountModelBuilder().build();

        when(accountEntrypointMapper.fromCreateAccountRequestDTOToAccountModel(requestDTO)).thenReturn(accountModel);

        mockMvc.perform(MockMvcRequestBuilders.post(UrlConstant.ACCOUNT_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        verify(accountPort).save(accountModel);
    }

    @Test
    void testCreateAccountWhenInvalidRequestThenReturnBadRequest() throws Exception {
        CreateAccountRequestDTO requestDTO = AccountDummy.createAccountRequestDTOBuilder()
                .firstName("")
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post(UrlConstant.ACCOUNT_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}