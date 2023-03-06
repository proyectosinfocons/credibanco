package com.hm.credibanco.mapper;

import com.hm.credibanco.dto.tx.TransactionRequest;
import com.hm.credibanco.dto.tx.TransactionResponse;
import com.hm.credibanco.entity.TransactionEntity;
import com.hm.credibanco.enums.TransactionStatusEnum;

public class TransationMapper {

    public static TransactionEntity dtoToEntity(TransactionRequest request) {
        return TransactionEntity.builder()
                .address(request.getAddress())
                .amount(request.getAmount())
                .identifier(request.getIdentifier())
                .referenceNumber(request.getReferenceNumber())
                .status(TransactionStatusEnum.APPROVED.name())
                .build();
    }

    public static TransactionResponse createEntityToDto(TransactionEntity entity) {
        return TransactionResponse.builder()
                .referenceNumber(entity.getReferenceNumber())
                .status(entity.getStatus())
                .build();
    }

    public static TransactionResponse reverseEntityToDto(TransactionEntity entity) {
        return TransactionResponse.builder()
                .referenceNumber(entity.getReferenceNumber())
                .build();
    }

}
