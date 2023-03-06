package com.hm.credibanco.application;

import com.hm.credibanco.dto.Response;
import com.hm.credibanco.dto.card.CardRequest;
import com.hm.credibanco.dto.card.CreateCardResponse;
import com.hm.credibanco.dto.card.EnrollCardRequest;
import com.hm.credibanco.dto.card.QueryCardResponse;
import com.hm.credibanco.entity.CardEntity;
import com.hm.credibanco.enums.CardStatusEnum;
import com.hm.credibanco.enums.ResponseEnum;
import com.hm.credibanco.exceptions.BusinessException;
import com.hm.credibanco.exceptions.DataNotFoundException;
import com.hm.credibanco.mapper.CardMapper;
import com.hm.credibanco.service.ICardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Slf4j
@Service
public class CardApplicationImpl implements ICardApplication {

    @Autowired
    private ICardService service;

    @Override
    public Response<CreateCardResponse> createCard(CardRequest request) throws NoSuchAlgorithmException {
        var oCard = service.queryCardByPanAndDocumentNumber(request.getPan(), request.getDocumentNumber());
        CardEntity cardEntity;
        if (oCard.isPresent()) {
            throw new BusinessException("card is already registered");
        }
        cardEntity = CardMapper.createDtoToEntity(request);
        cardEntity.buildIdentifier();
        cardEntity.buildValidationNumber();
        return new Response<>(ResponseEnum.SUCCESS.getCode(),
                ResponseEnum.SUCCESS.getMsg(),
                CardMapper.entityToDto(service.saveCard(cardEntity)));

    }

    @Override
    public Response<CreateCardResponse> enrollCard(EnrollCardRequest request) {
        var oCard = service.queryCard(request.getIdentifier(), request.getValidationNumber());
        if (oCard.isEmpty()) {
            log.info("CardApplicationImpl::enrollCard data not found --identifier [{}]", request.getIdentifier());
            throw new DataNotFoundException(request.getIdentifier());
        }
        if (!Objects.equals(oCard.get().getValidationNumber(), request.getValidationNumber())) {
            log.info("CardApplicationImpl::enrollCard validationNumber it does not match --identifier [{}] --validationNumber [{}]", request.getIdentifier(), request.getValidationNumber());
            throw new BusinessException("Invalid validation number");
        }
        if (Objects.equals(oCard.get().getStatus(), CardStatusEnum.ENROLADA.name())) {
            log.info("CardApplicationImpl::enrollCard is already enrolled --identifier [{}]", request.getIdentifier());
            throw new BusinessException("Card is already enrolled");
        }
        oCard.get().setStatus(CardStatusEnum.ENROLADA.name());
        return new Response<>(ResponseEnum.SUCCESS.getCode(),
                ResponseEnum.SUCCESS.getMsg(),
                CardMapper.enrollEntityToDto(service.saveCard(oCard.get())));
    }

    @Override
    public Response<QueryCardResponse> queryCard(String identifier) {
        var oCard = service.queryCard(identifier);
        if (oCard.isEmpty()) {
            log.info("CardApplicationImpl::queryCard data not found --identifier [{}]", identifier);
            throw new DataNotFoundException(identifier);
        }
        return new Response<>(ResponseEnum.SUCCESS.getCode(),
                ResponseEnum.SUCCESS.getMsg(),
                CardMapper.queryEntityToDto(oCard.get()));
    }

    @Override
    public Response<?> deleteCard(String identifier) {
        var oCard = service.queryCard(identifier);
        if (oCard.isEmpty()) {
            log.info("CardApplicationImpl::deleteCard data not found --identifier [{}]", identifier);
            throw new DataNotFoundException(identifier);
        }
        if (Objects.equals(oCard.get().getStatus(), CardStatusEnum.INACTIVA.name())) {
            log.info("CardApplicationImpl::deleteCard data not found --identifier [{}]", identifier);
            throw new BusinessException("Card is already inactivated");
        }
        oCard.get().setStatus(CardStatusEnum.INACTIVA.name());
        service.saveCard(oCard.get());
        return new Response<>(ResponseEnum.DELETE_SUCCESS.getCode(),
                ResponseEnum.SUCCESS.getMsg(), null);
    }
}
