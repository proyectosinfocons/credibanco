package com.hm.credibanco.service;

import com.hm.credibanco.entity.TransactionHistoryEntity;

public interface ITransactionHistoryService {

    void saveAuditTransaction(TransactionHistoryEntity entity);

}
