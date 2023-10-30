package com.ms.account.core.adapter;

import com.ms.account.core.model.AccountModel;
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

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountAdapterTest {

    @Mock
    private IAccountRepositoryPort accountRepositoryPort;

    @InjectMocks
    private AccountAdapter accountAdapter;

    private AccountModel accountModel;

    @BeforeEach
    public void setUp() {
        accountModel = AccountDummy.accountModelBuilder().build();
    }

    @Test
    @DisplayName("Test the 'save' method when a valid account model is passed")
    void testSaveWhenValidAccountModelThenSaveAccount() {
        doNothing().when(accountRepositoryPort).save(accountModel);

        accountAdapter.save(accountModel);

        verify(accountRepositoryPort, times(1)).save(accountModel);
    }

    @Test
    @DisplayName("Test the 'save' method when a null account model is passed")
    void testSaveWhenNullAccountModelThenDoNothing() {
        accountAdapter.save(null);

        verify(accountRepositoryPort, times(0)).save(any(AccountModel.class));
    }
}