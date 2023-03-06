package com.hm.credibanco.impl;

import com.hm.credibanco.exceptions.DataNotFoundException;
import com.hm.credibanco.exceptions.DatabaseException;
import com.hm.credibanco.repository.TransactionRepository;
import com.hm.credibanco.service.impl.TransactionServiceImpl;
import com.hm.credibanco.utils.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {

    @Mock
    TransactionRepository repository;

    @InjectMocks
    TransactionServiceImpl service;

    @Test
    void saveTransaction() {
        // given
        var entity = Data.TX;
        when(repository.save(entity))
                .thenReturn(entity);

        // when
        var result = service.saveTransaction(entity);

        // then
        verify(repository).save(entity);
        Assertions.assertNotNull(result);
    }

    @Test
    void saveTransaction_throw() {
        // given
        var entity = Data.TX;
        when(repository.save(entity))
                .thenThrow(DatabaseException.class);

        // when
        var ex = Assertions.assertThrows(DatabaseException.class, () -> service.saveTransaction(entity));

        // then
        Assertions.assertNotNull(entity.getIdentifier(), ex.getMessage());
    }

    @Test
    void reverseTransaction() {
        // given
        var identifier = "ce27c452-b30a-11ed-afa1-0242ac120002";
        var referenceNumber = "155623235";
        when(repository.findByIdentifierAndReferenceNumber(identifier, referenceNumber))
                .thenReturn(Optional.of(Data.TX));
        when(repository.save(Data.TX))
                .thenReturn(Data.TX);

        // when
        service.reverseTransaction(identifier, referenceNumber);

        // then
        verify(repository).findByIdentifierAndReferenceNumber(identifier, referenceNumber);
        verify(repository).save(Data.TX);
    }

    @Test
    void reverseTransaction_throw() {
        // given
        var identifier = "ce27c452-b30a-11ed-afa1-0242ac120002";
        var referenceNumber = "155623235";
        when(repository.findByIdentifierAndReferenceNumber(identifier, referenceNumber))
                .thenReturn(Optional.empty());

        // when
        var ex = Assertions.assertThrows(DataNotFoundException.class, () -> service.reverseTransaction(identifier, referenceNumber));

        // then
        verify(repository, never()).save(Data.TX);
        Assertions.assertEquals(identifier, ex.getMessage());
    }
}