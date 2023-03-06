package com.hm.credibanco.mapper;

import com.hm.credibanco.dto.card.CardRequest;
import com.hm.credibanco.dto.card.CreateCardResponse;
import com.hm.credibanco.dto.card.QueryCardResponse;
import com.hm.credibanco.entity.CardEntity;
import com.hm.credibanco.enums.CardStatusEnum;

public class CardMapper {

    public static CardEntity createDtoToEntity(CardRequest request) {
        return CardEntity.builder()
                .pan(request.getPan())
                .cardHolder(request.getCardHolder())
                .phoneNumber(request.getPhoneNumber())
                .documentNumber(request.getDocumentNumber())
                .status(CardStatusEnum.CREADA.name())
                .cardType(request.getCardType())
                .build();
    }

    public static CreateCardResponse entityToDto(CardEntity entity) {
        return CreateCardResponse.builder()
                .identifier(entity.getIdentifier())
                .pan(entity.getPan())
                .validateNumber(entity.getValidationNumber())
                .build();
    }

    public static QueryCardResponse queryEntityToDto(CardEntity entity) {
        return QueryCardResponse.builder()
                .cardHolder(entity.getCardHolder())
                .documentNumber(entity.getDocumentNumber())
                .pan(entity.getPan())
                .phoneNumber(entity.getPhoneNumber())
                .status(entity.getStatus())
                .build();
    }

    public static CreateCardResponse enrollEntityToDto(CardEntity entity) {
        return CreateCardResponse.builder()
                .pan(entity.getPan())
                .build();
    }

}
