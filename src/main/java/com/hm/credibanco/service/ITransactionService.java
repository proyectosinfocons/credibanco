package com.hm.credibanco.service;

import com.hm.credibanco.entity.TransactionEntity;

import java.util.Optional;

public interface ITransactionService {

    TransactionEntity saveTransaction(TransactionEntity entity);

    void reverseTransaction(String identifier, String referenceNumber);

    Optional<TransactionEntity> queryTransaction(String identifier, String referenceNumber);

}
