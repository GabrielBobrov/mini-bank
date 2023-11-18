package com.ms.account.core.adapter.service;

import com.ms.account.core.exception.AccountAlreadyExistsException;
import com.ms.account.core.model.CreateAccountModel;
import com.ms.account.core.model.GetAccountModel;
import com.ms.account.core.ports.out.repository.IAccountRepositoryPort;
import com.ms.account.dummy.AccountDummy;
import com.ms.account.infrastructure.filter.AccountFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceAdapterImplTest {

    @Mock
    private IAccountRepositoryPort accountRepositoryPort;

    @InjectMocks
    private AccountServiceAdapterImpl accountServiceAdapter;

    private CreateAccountModel createAccountModel;

    @BeforeEach
    public void setUp() {
        createAccountModel = AccountDummy.createAccountModelBuilder().build();
    }

    @Test
    @DisplayName("Test the 'updateBalance' method when a valid balance and UUID are passed")
    void testUpdateBalanceWhenValidBalanceAndUUIDThenUpdateBalance() {
        UUID uuid = UUID.randomUUID();
        BigDecimal balance = BigDecimal.valueOf(1000);

        doNothing().when(accountRepositoryPort).updateBalance(balance, uuid);

        accountServiceAdapter.updateBalance(balance, uuid);

        verify(accountRepositoryPort, times(1)).updateBalance(balance, uuid);
    }

    @Test
    @DisplayName("Test the 'save' method when a valid account model is passed")
    void testSaveWhenValidAccountModelThenSaveAccount() {
        when(accountRepositoryPort.existsByDocumentOrEmail(createAccountModel.getDocument(), createAccountModel.getEmail())).thenReturn(false);

        accountServiceAdapter.createAccount(createAccountModel);

        verify(accountRepositoryPort, times(1)).create(createAccountModel);
    }

    @Test
    @DisplayName("Test the 'save' method when account already exists")
    void testSaveWhenAccountAlreadyExistsThenThrowException() {
        when(accountRepositoryPort.existsByDocumentOrEmail(createAccountModel.getDocument(), createAccountModel.getEmail())).thenReturn(true);

        assertThrows(AccountAlreadyExistsException.class, () -> accountServiceAdapter.createAccount(createAccountModel));

        verify(accountRepositoryPort, times(0)).create(any(CreateAccountModel.class));
    }

    @Test
    @DisplayName("Test the 'getAccount' method when a valid UUID is passed")
    void testGetAccountWhenValidUUIDThenReturnAccount() {
        UUID uuid = UUID.randomUUID();
        GetAccountModel getAccountModel = GetAccountModel.builder().id(uuid).build();

        when(accountRepositoryPort.getAccount(uuid)).thenReturn(getAccountModel);

        GetAccountModel result = accountServiceAdapter.getAccount(uuid);

        assertEquals(getAccountModel, result);
        verify(accountRepositoryPort, times(1)).getAccount(uuid);
    }

    @Test
    @DisplayName("Test the 'getAccount' method when the repository returns null")
    void testGetAccountWhenRepositoryReturnsNullThenReturnNull() {
        UUID uuid = UUID.randomUUID();

        when(accountRepositoryPort.getAccount(uuid)).thenReturn(null);

        GetAccountModel result = accountServiceAdapter.getAccount(uuid);

        assertNull(result);
        verify(accountRepositoryPort, times(1)).getAccount(uuid);
    }

    @Test
    @DisplayName("Test the 'getAccounts' method when a valid 'AccountFilter' and 'Pageable' are passed")
    void testGetAccountsWhenValidAccountFilterAndPageableThenReturnPage() {
        AccountFilter accountFilter = mock(AccountFilter.class);
        Pageable pageable = mock(Pageable.class);
        Page<GetAccountModel> expectedPage = new PageImpl<>(List.of(AccountDummy.getAccountModelBuilder().build()));

        when(accountRepositoryPort.getAccounts(accountFilter, pageable)).thenReturn(expectedPage);

        Page<GetAccountModel> result = accountServiceAdapter.getAccounts(accountFilter, pageable);

        assertEquals(expectedPage, result);
        verify(accountRepositoryPort, times(1)).getAccounts(accountFilter, pageable);
    }

    @Test
    @DisplayName("Test the 'getAccounts' method when a null 'AccountFilter' is passed")
    void testGetAccountsWhenNullAccountFilterThenReturnPage() {
        Pageable pageable = mock(Pageable.class);
        Page<GetAccountModel> expectedPage = new PageImpl<>(Collections.emptyList());

        when(accountRepositoryPort.getAccounts(null, pageable)).thenReturn(expectedPage);

        Page<GetAccountModel> result = accountServiceAdapter.getAccounts(null, pageable);

        assertEquals(expectedPage, result);
        verify(accountRepositoryPort, times(1)).getAccounts(null, pageable);
    }

    @Test
    @DisplayName("Test the 'getAccounts' method when a null 'Pageable' is passed")
    void testGetAccountsWhenNullPageableThenReturnPage() {
        AccountFilter accountFilter = mock(AccountFilter.class);
        Page<GetAccountModel> expectedPage = new PageImpl<>(Collections.emptyList());

        when(accountRepositoryPort.getAccounts(accountFilter, null)).thenReturn(expectedPage);

        Page<GetAccountModel> result = accountServiceAdapter.getAccounts(accountFilter, null);

        assertEquals(expectedPage, result);
        verify(accountRepositoryPort, times(1)).getAccounts(accountFilter, null);
    }
}