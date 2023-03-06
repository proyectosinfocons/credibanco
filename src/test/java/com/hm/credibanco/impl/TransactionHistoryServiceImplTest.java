package com.hm.credibanco.impl;

import com.hm.credibanco.repository.TransactionHistoryRepository;
import com.hm.credibanco.service.impl.TransactionHistoryServiceImpl;
import com.hm.credibanco.utils.Data;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionHistoryServiceImplTest {

    @Mock
    TransactionHistoryRepository repository;

    @InjectMocks
    TransactionHistoryServiceImpl service;

    @Test
    void saveAuditTransaction() {
        // given
        var entity = Data.TX_AUDIT;
        when(repository.save(entity))
                .thenReturn(entity);

        // when
        service.saveAuditTransaction(entity);

        // then
        verify(repository).save(entity);
    }
}