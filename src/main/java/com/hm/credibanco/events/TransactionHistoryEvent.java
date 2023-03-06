package com.hm.credibanco.events;

import com.hm.credibanco.entity.CardEntity;
import com.hm.credibanco.entity.TransactionEntity;
import com.hm.credibanco.mapper.TransactionHistoryMapper;
import com.hm.credibanco.service.ITransactionHistoryService;
import com.hm.credibanco.service.ITransactionService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Aspect
public class TransactionHistoryEvent {

    @Autowired
    private ITransactionHistoryService iTransactionHistoryService;

    @Autowired
    private ITransactionService iTransactionService;

    @AfterReturning(pointcut = "execution(* com.hm.credibanco.service.impl.TransactionServiceImpl.saveTransaction(..)) && args(entity)", returning = "response")
    public void saveCreateAuditTx(TransactionEntity entity, TransactionEntity response) {
        iTransactionHistoryService.saveAuditTransaction(TransactionHistoryMapper.buildEntity(response));
    }

    @AfterThrowing(pointcut = "execution(* com.hm.credibanco.service.impl.TransactionServiceImpl.saveTransaction(..)) && args(entity)", throwing = "exception")
    public void exceptionSaveTx(CardEntity entity, Exception exception) {
        log.info("creation tx failed");
    }

    @AfterReturning(pointcut = "execution(* com.hm.credibanco.service.impl.TransactionServiceImpl.reverseTransaction(..)) && args(identifier, referenceNumber)")
    public void reverseCreateAuditTx(String identifier, String referenceNumber) {
        iTransactionHistoryService.saveAuditTransaction(TransactionHistoryMapper.buildEntity(iTransactionService.queryTransaction(identifier, referenceNumber).get()));
    }

    @AfterThrowing(pointcut = "execution(* com.hm.credibanco.service.impl.TransactionServiceImpl.reverseTransaction(..)) && args(entity)", throwing = "exception")
    public void exceptionReverseTx(CardEntity entity, Exception exception) {
        log.info("creation tx failed");
    }

}
