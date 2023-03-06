package com.hm.credibanco.application;

import com.hm.credibanco.exceptions.BusinessException;
import com.hm.credibanco.exceptions.DataNotFoundException;
import com.hm.credibanco.service.ICardService;
import com.hm.credibanco.service.ITransactionService;
import com.hm.credibanco.utils.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionApplicationImplTest {

    @Mock
    ITransactionService iTransactionService;

    @Mock
    ICardService iCardService;

    @InjectMocks
    TransactionApplicationImpl application;

    @Test
    void createTransaction() {
        // given
        var request = Data.TX_REQUEST;
        when(iCardService.queryCard(request.getIdentifier()))
                .thenReturn(Optional.of(Data.CARD_ENROLLED));
        when(iTransactionService.queryTransaction(request.getIdentifier(), request.getReferenceNumber()))
                .thenReturn(Optional.empty());
        when(iTransactionService.saveTransaction(Data.TX))
                .thenReturn(Data.TX);

        // when
        application.createTransaction(request);

        // then
        verify(iCardService).queryCard(request.getIdentifier());
        verify(iTransactionService).queryTransaction(request.getIdentifier(), request.getReferenceNumber());
        verify(iTransactionService).saveTransaction(Data.TX);
    }

    @Test
    void createTransaction_CARD_NOT_FOUND() {
        // given
        var request = Data.TX_REQUEST;
        when(iCardService.queryCard(request.getIdentifier()))
                .thenReturn(Optional.of(Data.CARD));

        // when
        var ex = Assertions.assertThrows(BusinessException.class, () -> application.createTransaction(request));

        // then
        verify(iCardService).queryCard(request.getIdentifier());
        verify(iTransactionService, never()).queryTransaction(request.getIdentifier(), request.getReferenceNumber());
        verify(iTransactionService, never()).saveTransaction(Data.TX);
        Assertions.assertEquals("Card is not enrolled", ex.getMessage());
    }

    @Test
    void createTransaction_CARD_NOT_ENROLLED() {
        // given
        var request = Data.TX_REQUEST;
        when(iCardService.queryCard(request.getIdentifier()))
                .thenReturn(Optional.empty());

        // when
        var ex = Assertions.assertThrows(BusinessException.class, () -> application.createTransaction(request));

        // then
        verify(iCardService).queryCard(request.getIdentifier());
        verify(iTransactionService, never()).queryTransaction(request.getIdentifier(), request.getReferenceNumber());
        verify(iTransactionService, never()).saveTransaction(Data.TX);
        Assertions.assertEquals("Card not found", ex.getMessage());
    }

    @Test
    void createTransaction_TX_ALREADY_EXISTS() {
        // given
        var request = Data.TX_REQUEST;
        when(iCardService.queryCard(request.getIdentifier()))
                .thenReturn(Optional.of(Data.CARD_ENROLLED));
        when(iTransactionService.queryTransaction(request.getIdentifier(), request.getReferenceNumber()))
                .thenReturn(Optional.of(Data.TX));

        // when
        var ex = Assertions.assertThrows(BusinessException.class, () -> application.createTransaction(request));

        // then
        verify(iCardService).queryCard(request.getIdentifier());
        verify(iTransactionService).queryTransaction(request.getIdentifier(), request.getReferenceNumber());
        verify(iTransactionService, never()).saveTransaction(Data.TX);
        Assertions.assertEquals("Tx already exists", ex.getMessage());
    }

    @Test
    void reverseTransaction() {
        // given
        var request = Data.REVERSE_TX_REQUEST;
        var tx = Data.TX;
        tx.setCreatedAt(LocalDateTime.now());
        when(iTransactionService.queryTransaction(request.getIdentifier(), request.getReferenceNumber()))
                .thenReturn(Optional.of(tx));
        when(iTransactionService.saveTransaction(Data.TX))
                .thenReturn(Data.TX);

        // when
        application.reverseTransaction(request);

        // then
        verify(iTransactionService).queryTransaction(request.getIdentifier(), request.getReferenceNumber());
        verify(iTransactionService).saveTransaction(Data.TX);
    }

    @Test
    void reverseTransaction_TX_NOT_EXISTS() {
        // given
        var request = Data.REVERSE_TX_REQUEST;
        when(iTransactionService.queryTransaction(request.getIdentifier(), request.getReferenceNumber()))
                .thenReturn(Optional.empty());

        // when
        var ex = Assertions.assertThrows(DataNotFoundException.class, () -> application.reverseTransaction(request));

        // then
        verify(iTransactionService).queryTransaction(request.getIdentifier(), request.getReferenceNumber());
        verify(iTransactionService, never()).saveTransaction(Data.TX);
        Assertions.assertEquals(request.getIdentifier(), ex.getMessage());
    }

    @Test
    void reverseTransaction_TX_ALREADY_CANCELED() {
        // given
        var request = Data.REVERSE_TX_REQUEST;
        when(iTransactionService.queryTransaction(request.getIdentifier(), request.getReferenceNumber()))
                .thenReturn(Optional.of(Data.TX_CANCELED));

        // when
        var ex = Assertions.assertThrows(BusinessException.class, () -> application.reverseTransaction(request));

        // then
        verify(iTransactionService).queryTransaction(request.getIdentifier(), request.getReferenceNumber());
        verify(iTransactionService, never()).saveTransaction(Data.TX);
        Assertions.assertEquals("Tx already canceled", ex.getMessage());
    }

    @Test
    void reverseTransaction_TX_IT_IS_TOO_LATE_FOR_CANCEL() {
        // given
        var request = Data.REVERSE_TX_REQUEST;
        var tx = Data.TX_TIME;
        tx.setCreatedAt(LocalDateTime.now().minusDays(1));
        when(iTransactionService.queryTransaction(request.getIdentifier(), request.getReferenceNumber()))
                .thenReturn(Optional.of(tx));

        // when
        var ex = Assertions.assertThrows(BusinessException.class, () -> application.reverseTransaction(request));

        // then
        verify(iTransactionService).queryTransaction(request.getIdentifier(), request.getReferenceNumber());
        verify(iTransactionService, never()).saveTransaction(Data.TX);
        Assertions.assertEquals("Cannot cancel transaction", ex.getMessage());
    }
}