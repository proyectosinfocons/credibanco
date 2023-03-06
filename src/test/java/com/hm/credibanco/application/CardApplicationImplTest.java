package com.hm.credibanco.application;

import com.hm.credibanco.entity.CardEntity;
import com.hm.credibanco.enums.CardStatusEnum;
import com.hm.credibanco.exceptions.BusinessException;
import com.hm.credibanco.exceptions.DataNotFoundException;
import com.hm.credibanco.service.ICardService;
import com.hm.credibanco.utils.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CardApplicationImplTest {

    @Mock
    ICardService service;

    @InjectMocks
    CardApplicationImpl application;

    @Captor
    ArgumentCaptor<CardEntity> captor;

    @Test
    void createCard() throws NoSuchAlgorithmException {
        // given
        var request = Data.CARD_REQUEST;
        when(service.queryCardByPanAndDocumentNumber(request.getPan(), request.getDocumentNumber()))
                .thenReturn(Optional.empty());
        when(service.saveCard(any(CardEntity.class))).thenReturn(Data.CARD);

        // when
        application.createCard(request);

        // then
        verify(service).queryCardByPanAndDocumentNumber(request.getPan(), request.getDocumentNumber());
        verify(service).saveCard(any(CardEntity.class));
    }

    @Test
    void createCard_CARD_ALREADY_EXISTS() {
        // given
        var request = Data.CARD_REQUEST;
        when(service.queryCardByPanAndDocumentNumber(request.getPan(), request.getDocumentNumber()))
                .thenReturn(Optional.of(Data.CARD));

        // when
        var ex = Assertions.assertThrows(BusinessException.class, () -> application.createCard(request));

        // then
        verify(service).queryCardByPanAndDocumentNumber(request.getPan(), request.getDocumentNumber());
        verify(service, never()).saveCard(Data.CARD);
        Assertions.assertEquals("card is already registered", ex.getMessage());

    }

    @Test
    void enrollCard() {
        // given
        var request = Data.ENROLL_CARD_REQUEST;
        when(service.queryCard(request.getIdentifier(), request.getValidationNumber()))
                .thenReturn(Optional.of(Data.CARD));
        when(service.saveCard(any(CardEntity.class)))
                .thenReturn(Data.CARD);

        // when
        application.enrollCard(request);

        // then
        verify(service).queryCard(request.getIdentifier(), request.getValidationNumber());
        verify(service).saveCard(captor.capture());
        Assertions.assertEquals(CardStatusEnum.ENROLADA.name(), captor.getValue().getStatus());
    }

    @Test
    void enrollCard_CARD_NOT_EXISTS() {
        // given
        var request = Data.ENROLL_CARD_REQUEST;
        when(service.queryCard(request.getIdentifier(), request.getValidationNumber()))
                .thenReturn(Optional.empty());

        // when
        var ex = Assertions.assertThrows(DataNotFoundException.class, () -> application.enrollCard(request));

        // then
        verify(service).queryCard(request.getIdentifier(), request.getValidationNumber());
        verify(service, never()).saveCard(captor.capture());
        Assertions.assertEquals(request.getIdentifier(), ex.getMessage());
    }

    @Test
    void enrollCard_INVALID_VALIDATION_NUMBER() {
        // given
        var request = Data.ENROLL_CARD_REQUEST_INVALID_VALIDATION_NUMBER;
        when(service.queryCard(request.getIdentifier(), request.getValidationNumber()))
                .thenReturn(Optional.of(Data.CARD));

        // when
        var ex = Assertions.assertThrows(BusinessException.class, () -> application.enrollCard(request));

        // then
        verify(service).queryCard(request.getIdentifier(), request.getValidationNumber());
        verify(service, never()).saveCard(captor.capture());
        Assertions.assertEquals("Invalid validation number", ex.getMessage());
    }

    @Test
    void enrollCard_CARD_ALREADY_ENROLLED() {
        // given
        var request = Data.ENROLL_CARD_REQUEST;
        when(service.queryCard(request.getIdentifier(), request.getValidationNumber()))
                .thenReturn(Optional.of(Data.CARD_ENROLLED));

        // when
        var ex = Assertions.assertThrows(BusinessException.class, () -> application.enrollCard(request));

        // then
        verify(service).queryCard(request.getIdentifier(), request.getValidationNumber());
        verify(service, never()).saveCard(captor.capture());
        Assertions.assertEquals("Card is already enrolled", ex.getMessage());
    }

    @Test
    void queryCard() {
        // given
        var identifier = "ce27c452-b30a-11ed-afa1-0242ac120002";
        when(service.queryCard(identifier))
                .thenReturn(Optional.of(Data.CARD));

        // when
        application.queryCard(identifier);

        // then
        verify(service).queryCard(identifier);

    }

    @Test
    void queryCard_DATA_NOT_FOUND() {
        // given
        var identifier = "ce27c452-b30a-11ed-afa1-0242ac120002";
        when(service.queryCard(identifier))
                .thenReturn(Optional.empty());

        // when
        var ex = Assertions.assertThrows(DataNotFoundException.class, () -> application.queryCard(identifier));

        // then
        verify(service).queryCard(identifier);
        Assertions.assertEquals(identifier, ex.getMessage());

    }

    @Test
    void deleteCard() {
        // given
        var identifier = "ce27c452-b30a-11ed-afa1-0242ac120002";
        when(service.queryCard(identifier))
                .thenReturn(Optional.of(Data.CARD));

        // when
        application.deleteCard(identifier);

        // then
        verify(service).queryCard(identifier);
    }

    @Test
    void deleteCard_DATA_NOT_FOUND() {
        // given
        var identifier = "ce27c452-b30a-11ed-afa1-0242ac120002";
        when(service.queryCard(identifier))
                .thenReturn(Optional.empty());

        // when
        var ex = Assertions.assertThrows(DataNotFoundException.class, () -> application.deleteCard(identifier));

        // then
        verify(service).queryCard(identifier);
        Assertions.assertEquals(identifier, ex.getMessage());
    }

    @Test
    void deleteCard_CARD_ALREADY_INACTIVE() {
        // given
        var identifier = "ce27c452-b30a-11ed-afa1-0242ac120002";
        when(service.queryCard(identifier))
                .thenReturn(Optional.of(Data.CARD_INACTIVE));

        // when
        var ex = Assertions.assertThrows(BusinessException.class, () -> application.deleteCard(identifier));

        // then
        verify(service).queryCard(identifier);
        Assertions.assertEquals("Card is already inactivated", ex.getMessage());
    }
}