package com.hm.credibanco.service.impl;

import com.hm.credibanco.entity.TransactionHistoryEntity;
import com.hm.credibanco.repository.TransactionHistoryRepository;
import com.hm.credibanco.service.ITransactionHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TransactionHistoryServiceImpl implements ITransactionHistoryService {

    @Autowired
    private TransactionHistoryRepository repository;

    @Override
    public void saveAuditTransaction(TransactionHistoryEntity entity) {
        try {
            repository.save(entity);
        } catch (Exception ex) {
            log.error("TransactionHistoryServiceImpl::saveAuditTransaction --exMessage [{}] --exCause [{}]", ex.getMessage(), ex.getCause());
        }
    }
}
