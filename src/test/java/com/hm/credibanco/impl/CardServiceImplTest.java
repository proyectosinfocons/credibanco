package com.hm.credibanco.impl;

import com.hm.credibanco.enums.CardStatusEnum;
import com.hm.credibanco.exceptions.DataNotFoundException;
import com.hm.credibanco.exceptions.DatabaseException;
import com.hm.credibanco.repository.CardRepository;
import com.hm.credibanco.service.impl.CardServiceImpl;
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
class CardServiceImplTest {

    @Mock
    CardRepository repository;

    @InjectMocks
    CardServiceImpl service;

    @Test
    void saveCard() {
        // given
        var entity = Data.CARD;
        when(repository.save(entity))
                .thenReturn(entity);

        // when
        var result = service.saveCard(entity);

        // then
        verify(repository).save(entity);
        Assertions.assertNotNull(result);
    }

    @Test
    void saveCard_throw() {
        // given
        var entity = Data.CARD;
        when(repository.save(entity))
                .thenThrow(DatabaseException.class);

        // when
        var ex = Assertions.assertThrows(DatabaseException.class, () -> service.saveCard(entity));

        // then
        Assertions.assertEquals(entity.getIdentifier(), ex.getMessage());
    }

    @Test
    void updateCard() {
        // given
        var identifier = "ce27c452-b30a-11ed-afa1-0242ac120002";
        var validationNumber = "01";
        var status = CardStatusEnum.ENROLADA.name();
        when(repository.findByIdentifierAndValidationNumber(identifier, validationNumber))
                .thenReturn(Optional.of(Data.CARD));
        when(repository.save(Data.CARD))
                .thenReturn(Data.CARD);

        // when
        var result = service.updateCard(validationNumber, identifier, status);

        // then
        verify(repository).findByIdentifierAndValidationNumber(identifier, validationNumber);
        verify(repository).save(Data.CARD);
        Assertions.assertEquals(status, result.getStatus());
    }

    @Test
    void updateCard_throw() {
        // given
        var identifier = "ce27c452-b30a-11ed-afa1-0242ac120002";
        var validationNumber = "01";
        var status = CardStatusEnum.ENROLADA.name();
        when(repository.findByIdentifierAndValidationNumber(identifier, validationNumber))
                .thenReturn(Optional.empty());

        // when
        var ex = Assertions.assertThrows(DataNotFoundException.class, () -> service.updateCard(validationNumber, identifier, status));

        // then
        verify(repository, never()).save(Data.CARD);
        Assertions.assertEquals(identifier, ex.getMessage());
    }

    @Test
    void queryCard() {
        // given
        var identifier = "ce27c452-b30a-11ed-afa1-0242ac120002";
        when(repository.findByIdentifier(identifier))
                .thenReturn(Optional.of(Data.CARD));

        // when
        var result = service.queryCard(identifier);

        // then
        verify(repository).findByIdentifier(identifier);
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    void queryCard_throw() {
        // given
        var identifier = "ce27c452-b30a-11ed-afa1-0242ac120002";
        when(repository.findByIdentifier(identifier))
                .thenThrow(DatabaseException.class);

        // when
        var ex = Assertions.assertThrows(DatabaseException.class, () -> service.queryCard(identifier));

        // then
        Assertions.assertEquals(identifier, ex.getMessage());
    }

    @Test
    void deleteCard() {
        // given
        var identifier = "ce27c452-b30a-11ed-afa1-0242ac120002";
        when(repository.findByIdentifier(identifier))
                .thenReturn(Optional.of(Data.CARD));
        when(repository.save(Data.CARD))
                .thenReturn(Data.CARD);

        // when
        service.deleteCard(identifier);

        // then
        verify(repository).findByIdentifier(identifier);
        verify(repository).save(Data.CARD);
    }

    @Test
    void deleteCard_throw() {
        // given
        var identifier = "ce27c452-b30a-11ed-afa1-0242ac120002";
        when(repository.findByIdentifier(identifier))
                .thenReturn(Optional.empty());

        // when
        var ex = Assertions.assertThrows(DataNotFoundException.class, () -> service.deleteCard(identifier));

        // then
        verify(repository, never()).save(Data.CARD);
        Assertions.assertEquals(identifier, ex.getMessage());
    }
}