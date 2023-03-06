package com.hm.credibanco.impl;

import com.hm.credibanco.repository.CardHistoryRepository;
import com.hm.credibanco.service.impl.CardHistoryServiceImpl;
import com.hm.credibanco.utils.Data;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CardHistoryServiceImplTest {

    @Mock
    CardHistoryRepository repository;

    @InjectMocks
    CardHistoryServiceImpl service;

    @Test
    void saveAuditCard() {
        // given
        var entity = Data.CARD_AUDIT;
        when(repository.save(entity))
                .thenReturn(entity);

        // when
        service.saveAuditCard(entity);

        // then
        verify(repository).save(entity);
    }
}