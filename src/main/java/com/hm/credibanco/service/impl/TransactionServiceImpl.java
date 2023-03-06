package com.hm.credibanco.service.impl;

import com.hm.credibanco.entity.TransactionEntity;
import com.hm.credibanco.enums.TransactionStatusEnum;
import com.hm.credibanco.exceptions.DataNotFoundException;
import com.hm.credibanco.exceptions.DatabaseException;
import com.hm.credibanco.repository.TransactionRepository;
import com.hm.credibanco.service.ITransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
public class TransactionServiceImpl implements ITransactionService {

    @Autowired
    private TransactionRepository repository;

    @Override
    @Transactional
    public TransactionEntity saveTransaction(TransactionEntity entity) {
        try {
            return repository.save(entity);
        } catch (Exception ex) {
            log.error("TransactionServiceImpl::createTransaction --identifier[{}] --exMessage [{}] --exCause [{}]", entity.getIdentifier(), ex.getMessage(), ex.getCause());
            throw new DatabaseException(entity.getIdentifier());
        }
    }

    @Override
    @Transactional
    public void reverseTransaction(String identifier, String referenceNumber) {
        var oTx = this.queryTransaction(identifier, referenceNumber);
        if (oTx.isEmpty()) {
            log.info("TransactionServiceImpl::reverseTransaction tx not found --identifier[{}] --referenceNumber [{}]", identifier, referenceNumber);
            throw new DataNotFoundException(identifier);
        }
        oTx.get().setStatus(TransactionStatusEnum.CANCELED.name());
        try {
            repository.save(oTx.get());
        } catch (Exception ex) {
            log.error("TransactionServiceImpl::reverseTransaction --identifier[{}] --referenceNumber [{}] --exMessage [{}] --exCause [{}]", identifier, referenceNumber, ex.getMessage(), ex.getCause());
            throw new DatabaseException(identifier);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TransactionEntity> queryTransaction(String identifier, String referenceNumber) {
        try {
            return repository.findByIdentifierAndReferenceNumber(identifier, referenceNumber);
        } catch (Exception ex) {
            log.error("TransactionServiceImpl::queryTransaction --identifier[{}] --referenceNumber [{}] --exMessage [{}] --exCause [{}]", identifier, referenceNumber, ex.getMessage(), ex.getCause());
            throw new DatabaseException(identifier);
        }
    }
}
