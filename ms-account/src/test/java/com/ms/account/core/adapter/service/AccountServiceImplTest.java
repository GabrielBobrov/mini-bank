package com.ms.account.core.adapter.service;

import com.ms.account.core.model.CreateAccountModel;
import com.ms.account.core.model.GetAccountModel;
import com.ms.account.core.ports.out.repository.IAccountRepositoryPort;
import com.ms.account.dummy.AccountDummy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @Mock
    private IAccountRepositoryPort accountRepositoryPort;

    @InjectMocks
    private IAccountServiceServiceImpl accountServiceImpl;

    private CreateAccountModel createAccountModel;

    @BeforeEach
    public void setUp() {
        createAccountModel = AccountDummy.createAccountModelBuilder().build();
    }

    @Test
    @DisplayName("Test the 'save' method when a valid account model is passed")
    void testSaveWhenValidAccountModelThenSaveAccount() {
        doNothing().when(accountRepositoryPort).save(createAccountModel);

        accountServiceImpl.save(createAccountModel);

        verify(accountRepositoryPort, times(1)).save(createAccountModel);
    }

    @Test
    @DisplayName("Test the 'save' method when a null account model is passed")
    void testSaveWhenNullAccountModelThenDoNothing() {
        accountServiceImpl.save(null);

        verify(accountRepositoryPort, times(0)).save(any(CreateAccountModel.class));
    }

    @Test
    @DisplayName("Test the 'getAccount' method when a valid UUID is passed")
    void testGetAccountWhenValidUUIDThenReturnAccount() {
        UUID uuid = UUID.randomUUID();
        GetAccountModel getAccountModel = GetAccountModel.builder().id(uuid).build();

        when(accountRepositoryPort.getAccount(uuid)).thenReturn(getAccountModel);

        GetAccountModel result = accountServiceImpl.getAccount(uuid);

        assertEquals(getAccountModel, result);
        verify(accountRepositoryPort, times(1)).getAccount(uuid);
    }

    @Test
    @DisplayName("Test the 'getAccount' method when the repository returns null")
    void testGetAccountWhenRepositoryReturnsNullThenReturnNull() {
        UUID uuid = UUID.randomUUID();

        when(accountRepositoryPort.getAccount(uuid)).thenReturn(null);

        GetAccountModel result = accountServiceImpl.getAccount(uuid);

        assertNull(result);
        verify(accountRepositoryPort, times(1)).getAccount(uuid);
    }
}