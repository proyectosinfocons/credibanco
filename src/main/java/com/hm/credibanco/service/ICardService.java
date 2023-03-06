package com.hm.credibanco.service;

import com.hm.credibanco.entity.CardEntity;

import java.util.Optional;

public interface ICardService {

    CardEntity saveCard(CardEntity entity);

    CardEntity updateCard(String validationNumber, String identifier, String status);

    Optional<CardEntity> queryCard(String identifier);

    void deleteCard(String identifier);

    Optional<CardEntity> queryCard(String identifier, String validationNumber);

    Optional<CardEntity> queryCardByPanAndDocumentNumber(String pan, String documentNumber);

}
