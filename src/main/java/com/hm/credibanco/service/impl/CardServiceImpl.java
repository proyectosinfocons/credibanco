package com.hm.credibanco.service.impl;

import com.hm.credibanco.entity.CardEntity;
import com.hm.credibanco.enums.CardStatusEnum;
import com.hm.credibanco.exceptions.DataNotFoundException;
import com.hm.credibanco.exceptions.DatabaseException;
import com.hm.credibanco.repository.CardRepository;
import com.hm.credibanco.service.ICardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
public class CardServiceImpl implements ICardService {

    @Autowired
    private CardRepository repository;

    @Override
    @Transactional
    public CardEntity saveCard(CardEntity entity) {
        try {
            return repository.save(entity);
        } catch (Exception ex) {
            log.error("CardServiceImpl::createCard --identifier [{}] --exMessage [{}] --exCause [{}]", entity.getIdentifier(), ex.getMessage(), ex.getCause());
            throw new DatabaseException(entity.getIdentifier());
        }
    }

    @Override
    @Transactional
    public CardEntity updateCard(String validationNumber, String identifier, String status) {
        var oCard = this.queryCard(identifier, validationNumber);
        if (oCard.isEmpty()) {
            log.info("CardServiceImpl::updateCard Card not found --identifier [{}]", identifier);
            throw new DataNotFoundException(identifier);
        }
        oCard.get().setStatus(status);
        try {
            return repository.save(oCard.get());
        } catch (Exception ex) {
            log.error("CardServiceImpl::updateCard --identifier [{}] --exMessage [{}] --exCause [{}]", identifier, ex.getMessage(), ex.getCause());
            throw new DatabaseException(identifier);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CardEntity> queryCard(String identifier) {
        try {
            return repository.findByIdentifier(identifier);
        } catch (Exception ex) {
            log.error("CardServiceImpl::queryCard --identifier [{}] --exMessage [{}] --exCause [{}]", identifier, ex.getMessage(), ex.getCause());
            throw new DatabaseException(identifier);
        }
    }

    @Override
    @Transactional
    public void deleteCard(String identifier) {
        var oCard = this.queryCard(identifier);
        if (oCard.isEmpty()) {
            log.info("CardServiceImpl::deleteCard Card not found --identifier [{}]", identifier);
            throw new DataNotFoundException(identifier);
        }
        oCard.get().setStatus(CardStatusEnum.INACTIVA.name());
        try {
            repository.save(oCard.get());
        } catch (Exception ex) {
            log.error("CardServiceImpl::deleteCard --identifier [{}] --exMessage [{}] --exCause [{}]", identifier, ex.getMessage(), ex.getCause());
            throw new DatabaseException(identifier);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CardEntity> queryCard(String identifier, String validationNumber) {
        try {
            return repository.findByIdentifierAndValidationNumber(identifier, validationNumber);
        } catch (Exception ex) {
            log.error("CardServiceImpl::queryCard --identifier [{}] --validationNumber [{}] --exMessage [{}] --exCause [{}]", identifier, validationNumber, ex.getMessage(), ex.getCause());
            throw new DatabaseException(identifier);
        }
    }

    @Override
    public Optional<CardEntity> queryCardByPanAndDocumentNumber(String pan, String documentNumber) {
        try {
            return repository.findByPanAndDocumentNumber(pan, documentNumber);
        } catch (Exception ex) {
            log.error("CardServiceImpl::queryCard --documentNumber [{}] --exMessage [{}] --exCause [{}]", documentNumber, ex.getMessage(), ex.getCause());
            throw new DatabaseException(documentNumber);
        }
    }
}
