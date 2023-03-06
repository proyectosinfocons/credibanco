package com.hm.credibanco.mapper;

import com.hm.credibanco.entity.CardEntity;
import com.hm.credibanco.entity.CardHistoryEntity;

public class CardHistoryMapper {

    public static CardHistoryEntity buildEntity(CardEntity entity) {
        return CardHistoryEntity.builder()
                .idCard(entity.getId())
                .cardHolder(entity.getCardHolder())
                .cardType(entity.getCardType())
                .pan(entity.getPan())
                .documentNumber(entity.getDocumentNumber())
                .phoneNumber(entity.getPhoneNumber())
                .status(entity.getStatus())
                .validationNumber(entity.getValidationNumber())
                .identifier(entity.getIdentifier())
                .build();
    }

}
