package com.hm.credibanco.mapper;

import com.hm.credibanco.entity.TransactionEntity;
import com.hm.credibanco.entity.TransactionHistoryEntity;

public class TransactionHistoryMapper {

    public static TransactionHistoryEntity buildEntity(TransactionEntity entity) {
        return TransactionHistoryEntity.builder()
                .idTransaction(entity.getId())
                .identifier(entity.getIdentifier())
                .address(entity.getAddress())
                .amount(entity.getAmount())
                .status(entity.getStatus())
                .referenceNumber(entity.getReferenceNumber())
                .build();
    }

}
