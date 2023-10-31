
package com.ms.account.infrastructure.repository.adapter;

import com.ms.account.core.exception.NotFoundException;
import com.ms.account.core.model.CreateAccountModel;
import com.ms.account.core.model.GetAccountModel;
import com.ms.account.dummy.AccountDummy;
import com.ms.account.infrastructure.entity.AccountEntity;
import com.ms.account.infrastructure.mapper.IAccountInfrastructureMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountRepositoryAdapterTest {

    @Mock
    private ISpringAccountRepositoryAdapter springAccountRepository;

    @Mock
    private IAccountInfrastructureMapper IAccountInfrastructureMapper;

    @InjectMocks
    private AccountRepositoryAdapter accountRepositoryAdapter;

    private CreateAccountModel mockCreateAccountModel;
    private AccountEntity mockAccountEntity;
    private GetAccountModel mockGetAccountModel;
    private UUID mockAccountId;

    @BeforeEach
    public void setup() {
        mockCreateAccountModel = AccountDummy.createAccountModelBuilder().build();
        mockAccountEntity = AccountDummy.accountEntityBuilder().build();
        mockGetAccountModel = AccountDummy.getAccountModelBuilder().build();
        mockAccountId = UUID.randomUUID();
    }

    @Test
    void testSaveWhenValidAccountModelThenSaveAccountEntity() {
        when(IAccountInfrastructureMapper.fromAccountModelToAccountEntity(mockCreateAccountModel)).thenReturn(mockAccountEntity);

        accountRepositoryAdapter.save(mockCreateAccountModel);

        verify(springAccountRepository, times(1)).save(mockAccountEntity);
    }

    @Test
    void testSaveWhenNullAccountModelThenDoNotSaveAccountEntity() {
        accountRepositoryAdapter.save(null);

        verify(springAccountRepository, times(0)).save(any(AccountEntity.class));
    }

    @Test
    void testGetAccountWhenValidAccountIdThenReturnGetAccountModel() {
        when(springAccountRepository.findById(mockAccountId)).thenReturn(Optional.of(mockAccountEntity));
        when(IAccountInfrastructureMapper.fromAccountEntityTGetAccountModel(mockAccountEntity)).thenReturn(mockGetAccountModel);

        GetAccountModel result = accountRepositoryAdapter.getAccount(mockAccountId);

        assertEquals(mockGetAccountModel, result);
    }

    @Test
    void testGetAccountWhenInvalidAccountIdThenThrowNotFoundException() {
        when(springAccountRepository.findById(mockAccountId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> accountRepositoryAdapter.getAccount(mockAccountId));
    }
}