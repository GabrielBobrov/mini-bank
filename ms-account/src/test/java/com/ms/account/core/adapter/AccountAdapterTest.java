package com.ms.account.core.adapter;

import com.ms.account.core.model.CreateAccountModel;
import com.ms.account.core.ports.out.repository.IAccountRepositoryPort;
import com.ms.account.dummy.AccountDummy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountAdapterTest {

    @Mock
    private IAccountRepositoryPort accountRepositoryPort;

    @InjectMocks
    private AccountAdapter accountAdapter;

    private CreateAccountModel createAccountModel;

    @BeforeEach
    public void setUp() {
        createAccountModel = AccountDummy.accountModelBuilder().build();
    }

    @Test
    @DisplayName("Test the 'save' method when a valid account model is passed")
    void testSaveWhenValidAccountModelThenSaveAccount() {
        doNothing().when(accountRepositoryPort).save(createAccountModel);

        accountAdapter.save(createAccountModel);

        verify(accountRepositoryPort, times(1)).save(createAccountModel);
    }

    @Test
    @DisplayName("Test the 'save' method when a null account model is passed")
    void testSaveWhenNullAccountModelThenDoNothing() {
        accountAdapter.save(null);

        verify(accountRepositoryPort, times(0)).save(any(CreateAccountModel.class));
    }
}