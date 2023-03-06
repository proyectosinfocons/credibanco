package com.hm.credibanco.utils;

import com.hm.credibanco.dto.Response;
import com.hm.credibanco.dto.card.CardRequest;
import com.hm.credibanco.dto.card.EnrollCardRequest;
import com.hm.credibanco.dto.tx.ReverseTransactionRequest;
import com.hm.credibanco.dto.tx.TransactionRequest;
import com.hm.credibanco.dto.tx.TransactionResponse;
import com.hm.credibanco.entity.CardEntity;
import com.hm.credibanco.entity.CardHistoryEntity;
import com.hm.credibanco.entity.TransactionEntity;
import com.hm.credibanco.entity.TransactionHistoryEntity;
import com.hm.credibanco.enums.CardStatusEnum;
import com.hm.credibanco.enums.ResponseEnum;

import java.math.BigDecimal;

public class Data {

    public static final CardEntity CARD =
            CardEntity.builder()
                    .identifier("ce27c452-b30a-11ed-afa1-0242ac120002")
                    .pan("123456****3456")
                    .validationNumber("01")
                    .cardHolder("John Doe")
                    .documentNumber("1234567890")
                    .cardType("DEBITO")
                    .phoneNumber("5555551122")
            .build();

    public static final CardEntity CARD_INACTIVE =
            CardEntity.builder()
                    .identifier("ce27c452-b30a-11ed-afa1-0242ac120002")
                    .pan("123456****3456")
                    .validationNumber("01")
                    .cardHolder("John Doe")
                    .documentNumber("1234567890")
                    .cardType("DEBITO")
                    .phoneNumber("5555551122")
                    .status(CardStatusEnum.INACTIVA.name())
                    .build();

    public static final CardRequest CARD_REQUEST =
            CardRequest.builder()
                    .pan("1234563333345622")
                    .cardHolder("John Doe")
                    .documentNumber("1234567895")
                    .cardType("DEBITO")
                    .phoneNumber("1234567890")
                    .build();

    public static final CardRequest CARD_BAD_REQUEST =
            CardRequest.builder()
                    .pan("111")
                    .cardHolder("John Doe")
                    .documentNumber("1234567895")
                    .cardType("DEBITO")
                    .phoneNumber("1234567890")
                    .build();

    public static final CardEntity CARD_ENROLLED =
            CardEntity.builder()
                    .identifier("2f98f3b24dff7e905019fa636c8f9ac34bbad0756b99b1fe7a76ec96ccf9dce32")
                    .pan("123456****3456")
                    .validationNumber("01")
                    .cardHolder("John Doe")
                    .documentNumber("1234567890")
                    .cardType("DEBITO")
                    .phoneNumber("5555551122")
                    .status(CardStatusEnum.ENROLADA.name())
                    .build();

    public static final CardHistoryEntity CARD_AUDIT =
            CardHistoryEntity.builder()
                    .idCard(1L)
                    .identifier("ce27c452-b30a-11ed-afa1-0242ac120002")
                    .pan("123456****3456")
                    .validationNumber("01")
                    .cardHolder("John Doe")
                    .documentNumber("1234567890")
                    .cardType("DEBITO")
                    .phoneNumber("5555551122")
                    .build();

    public static final EnrollCardRequest ENROLL_CARD_REQUEST =
            EnrollCardRequest.builder()
                    .identifier("ce27c452-b30a-11ed-afa1-0242ac120002")
                    .validationNumber("01")
                    .build();

    public static final EnrollCardRequest ENROLL_CARD_REQUEST_INTEGRATION_TEST =
            EnrollCardRequest.builder()
                    .identifier("472689c8f8a29cd40496bc535b75e0d65c7e37337936ba736c2eb537fa7c22c1")
                    .validationNumber("01")
                    .build();

    public static final EnrollCardRequest ENROLL_CARD_BAD_REQUEST_INTEGRATION_TEST =
            EnrollCardRequest.builder()
                    .identifier("111")
                    .validationNumber("01")
                    .build();

    public static final EnrollCardRequest ENROLL_CARD_REQUEST_INVALID_VALIDATION_NUMBER =
            EnrollCardRequest.builder()
                    .identifier("ce27c452-b30a-11ed-afa1-0242ac120002")
                    .validationNumber("99")
                    .build();

    public static final TransactionEntity TX =
            TransactionEntity.builder()
                    .identifier("ce27c452-b30a-11ed-afa1-0242ac120002")
                    .referenceNumber("155623235")
                    .amount(BigDecimal.valueOf(5000))
                    .address("Cr 70 # cl 100 - 99")
                    .status("APPROVED")
                    .build();

    public static final TransactionEntity TX_CANCELED =
            TransactionEntity.builder()
                    .identifier("ce27c452-b30a-11ed-afa1-0242ac120002")
                    .referenceNumber("155623235")
                    .amount(BigDecimal.valueOf(5000))
                    .address("Cr 70 # cl 100 - 99")
                    .status("CANCELED")
                    .build();

    public static final TransactionEntity TX_TIME =
            TransactionEntity.builder()
                    .identifier("ce27c452-b30a-11ed-afa1-0242ac120002")
                    .referenceNumber("155623235")
                    .amount(BigDecimal.valueOf(5000))
                    .address("Cr 70 # cl 100 - 99")
                    .status("APPROVED")
                    .build();

    public static final TransactionHistoryEntity TX_AUDIT =
            TransactionHistoryEntity.builder()
                    .idTransaction(1L)
                    .identifier("ce27c452-b30a-11ed-afa1-0242ac120002")
                    .referenceNumber("155623235")
                    .amount(BigDecimal.valueOf(5000))
                    .address("Cr 70 # cl 100 - 99")
                    .status("APPROVED")
                    .build();

    public static final TransactionRequest TX_REQUEST =
            TransactionRequest.builder()
                    .address("Cr 70 # cl 100 - 99")
                    .referenceNumber("155623235")
                    .amount(BigDecimal.valueOf(5000))
                    .identifier("ce27c452-b30a-11ed-afa1-0242ac120002")
                    .build();

    public static final ReverseTransactionRequest REVERSE_TX_REQUEST =
            ReverseTransactionRequest.builder()
                    .referenceNumber("155623235")
                    .amount(BigDecimal.valueOf(5000))
                    .identifier("ce27c452-b30a-11ed-afa1-0242ac120002")
                    .build();

    public static final Response<TransactionResponse> TX_CREATED =
            new Response<>(
                    ResponseEnum.SUCCESS.getCode(),
                    ResponseEnum.SUCCESS.getMsg(),
                    new TransactionResponse()
            );

    public static final TransactionRequest TRANSACTION_REQUEST =
            TransactionRequest.builder()
                    .identifier("472689c8f8a29cd40496bc535b75e0d65c7e37337936ba736c2eb537fa7c22c1")
                    .referenceNumber("111112")
                    .address("cr 70 # cl 100 - 99")
                    .amount(BigDecimal.valueOf(5000))
                    .build();

    public static final TransactionRequest TRANSACTION_REQUEST_BAD_IDENTIFIER =
            TransactionRequest.builder()
                    .identifier("121122")
                    .referenceNumber("111112")
                    .address("cr 70 # cl 100 - 99")
                    .amount(BigDecimal.valueOf(5000))
                    .build();

    public static final TransactionRequest REVERSE_TRANSACTION_REQUEST =
            TransactionRequest.builder()
                    .identifier("472689c8f8a29cd40496bc535b75e0d65c7e37337936ba736c2eb537fa7c22c1")
                    .referenceNumber("111112")
                    .amount(BigDecimal.valueOf(5000))
                    .build();

    public static final TransactionRequest REVERSE_TRANSACTION_BAD_REQUEST =
            TransactionRequest.builder()
                    .identifier("11111")
                    .referenceNumber("111112")
                    .amount(BigDecimal.valueOf(5000))
                    .build();
}
