package com.minibank.transfers.infrastructure.repository.adapter;

import com.minibank.transfers.core.model.CreateTransferModel;
import com.minibank.transfers.dummy.TransferDummy;
import com.minibank.transfers.infrastructure.entity.transfer.TransferEntity;
import com.minibank.transfers.infrastructure.mapper.ITransferInfrastructureMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransferRepositoryAdapterImplTest {

    @Mock
    private ISpringTransferRepositoryAdapter springTransferRepository;

    @Mock
    private ITransferInfrastructureMapper transferInfrastructureMapper;

    @InjectMocks
    private TransferRepositoryAdapterImpl transferRepositoryAdapter;

    private CreateTransferModel createTransferModel;
    private TransferEntity transferEntity;

    @BeforeEach
    public void setUp() {
        createTransferModel = TransferDummy.createTransferModelBuilder().build();

        transferEntity = TransferDummy.transferEntityBuilder().build();
    }

    @Test
    void testCreateWhenEverythingIsSetUpCorrectlyThenSaveTransferEntity() {
        when(transferInfrastructureMapper.fromCreateTransferModelToTransferEntity(createTransferModel)).thenReturn(transferEntity);

        transferRepositoryAdapter.create(createTransferModel);

        verify(springTransferRepository, times(1)).save(transferEntity);
    }

    @Test
    void testCreateWhenCreateTransferModelIsNullThenDoNothing() {
        transferRepositoryAdapter.create(null);

        verify(springTransferRepository, never()).save(any(TransferEntity.class));
    }
}