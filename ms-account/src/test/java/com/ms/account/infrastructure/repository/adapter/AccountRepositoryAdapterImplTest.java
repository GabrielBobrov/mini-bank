package com.ms.account.infrastructure.repository.adapter;

import com.ms.account.core.exception.NotFoundException;
import com.ms.account.core.model.CreateAccountModel;
import com.ms.account.core.model.GetAccountModel;
import com.ms.account.dummy.AccountDummy;
import com.ms.account.infrastructure.entity.AccountEntity;
import com.ms.account.infrastructure.filter.AccountFilter;
import com.ms.account.infrastructure.mapper.IAccountInfrastructureMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
class AccountRepositoryAdapterImplTest {

    @MockBean
    private ISpringAccountRepositoryAdapter springAccountRepository;

    @MockBean
    private IAccountInfrastructureMapper IAccountInfrastructureMapper;

    @Autowired
    private AccountRepositoryAdapterImpl accountRepositoryAdapterImpl;

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

        accountRepositoryAdapterImpl.save(mockCreateAccountModel);

        verify(springAccountRepository, times(1)).save(mockAccountEntity);
    }

    @Test
    void testSaveWhenNullAccountModelThenDoNotSaveAccountEntity() {
        accountRepositoryAdapterImpl.save(null);

        verify(springAccountRepository, times(0)).save(any(AccountEntity.class));
    }

    @Test
    void testGetAccountWhenValidAccountIdThenReturnGetAccountModel() {
        when(springAccountRepository.findById(mockAccountId)).thenReturn(Optional.of(mockAccountEntity));
        when(IAccountInfrastructureMapper.fromAccountEntityToGetAccountModel(mockAccountEntity)).thenReturn(mockGetAccountModel);

        GetAccountModel result = accountRepositoryAdapterImpl.getAccount(mockAccountId);

        assertEquals(mockGetAccountModel, result);
    }

    @Test
    void testGetAccountWhenInvalidAccountIdThenThrowNotFoundException() {
        when(springAccountRepository.findById(mockAccountId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> accountRepositoryAdapterImpl.getAccount(mockAccountId));
    }

    @Test
    void testGetAccountsWhenValidFilterAndPageableThenReturnPageOfGetAccountModels() {
        AccountFilter mockFilter = new AccountFilter();
        Pageable mockPageable = Pageable.ofSize(2);
        Page<AccountEntity> mockPage = new PageImpl<>(Collections.singletonList(mockAccountEntity));

        when(springAccountRepository.findAll(any(Specification.class), eq(mockPageable))).thenReturn(mockPage);
        when(IAccountInfrastructureMapper.fromListAccountEntityToListGetAccountModel(mockPage.getContent())).thenReturn(Collections.singletonList(mockGetAccountModel));

        Page<GetAccountModel> result = accountRepositoryAdapterImpl.getAccounts(mockFilter, mockPageable);

        assertEquals(1, result.getContent().size());
        assertEquals(mockGetAccountModel, result.getContent().get(0));
    }

    @Test
    void testExistsByDocumentOrEmailWhenValidAccountModelThenReturnBoolean() {
        when(springAccountRepository.existsByDocumentOrEmail(mockCreateAccountModel.getDocument(), mockCreateAccountModel.getEmail())).thenReturn(true);

        Boolean result = accountRepositoryAdapterImpl.existsByDocumentOrEmail(mockCreateAccountModel);

        assertEquals(true, result);
    }
}