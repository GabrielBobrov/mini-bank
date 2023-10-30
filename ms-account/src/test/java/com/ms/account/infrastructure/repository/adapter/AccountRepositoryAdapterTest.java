package com.ms.account.infrastructure.repository.adapter;

import com.ms.account.core.model.CreateAccountModel;
import com.ms.account.dummy.AccountDummy;
import com.ms.account.infrastructure.entity.AccountEntity;
import com.ms.account.infrastructure.mapper.AccountInfrastructureMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountRepositoryAdapterTest {

    @Mock
    private ISpringAccountRepositoryAdapter springAccountRepository;

    @Mock
    private AccountInfrastructureMapper accountInfrastructureMapper;

    @InjectMocks
    private AccountRepositoryAdapter accountRepositoryAdapter;

    private CreateAccountModel mockCreateAccountModel;
    private AccountEntity mockAccountEntity;

    @BeforeEach
    public void setup() {
        mockCreateAccountModel = AccountDummy.accountModelBuilder().build();

        mockAccountEntity = AccountDummy.accountEntityBuilder().build();
    }

    @Test
    void testSaveWhenValidAccountModelThenSaveAccountEntity() {
        when(accountInfrastructureMapper.fromAccountModelToAccountEntity(mockCreateAccountModel)).thenReturn(mockAccountEntity);

        accountRepositoryAdapter.save(mockCreateAccountModel);

        verify(springAccountRepository, times(1)).save(mockAccountEntity);
    }

    @Test
    void testSaveWhenNullAccountModelThenDoNotSaveAccountEntity() {
        accountRepositoryAdapter.save(null);

        verify(springAccountRepository, times(0)).save(any(AccountEntity.class));
    }
}