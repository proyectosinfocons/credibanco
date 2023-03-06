package com.hm.credibanco.events;

import com.hm.credibanco.entity.CardEntity;
import com.hm.credibanco.mapper.CardHistoryMapper;
import com.hm.credibanco.service.ICardHistoryService;
import com.hm.credibanco.service.ICardService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Aspect
public class CardHistoryEvent {

    @Autowired
    private ICardHistoryService iCardHistoryService;

    @Autowired
    private ICardService iCardService;

    @AfterReturning(pointcut = "execution(* com.hm.credibanco.service.impl.CardServiceImpl.saveCard(..)) && args(entity)", returning = "response")
    public void saveCreateAuditCard(CardEntity entity, CardEntity response) {
        iCardHistoryService.saveAuditCard(CardHistoryMapper.buildEntity(response));
    }

    @AfterThrowing(pointcut = "execution(* com.hm.credibanco.service.impl.CardServiceImpl.saveCard(..)) && args(entity)", throwing = "exception")
    public void exceptionSaveCard(CardEntity entity, Exception exception) {
        log.info("creation card failed");
    }

    @AfterReturning(pointcut = "execution(* com.hm.credibanco.service.impl.CardServiceImpl.updateCard(..)) && args(validationNumber, identifier, status)", returning = "response")
    public void updateCreateAuditCard(String validationNumber, String identifier, String status, CardEntity response) {
        iCardHistoryService.saveAuditCard(CardHistoryMapper.buildEntity(response));
    }

    @AfterThrowing(pointcut = "execution(* com.hm.credibanco.service.impl.CardServiceImpl.updateCard(..)) && args(validationNumber, identifier, status)", throwing = "exception")
    public void exceptionUpdateCard(String validationNumber, String identifier, String status, Exception exception) {
        log.info("enroll card failed");
    }

    @AfterReturning(pointcut = "execution(* com.hm.credibanco.service.impl.CardServiceImpl.deleteCard(..)) && args(identifier)")
    public void deleteCreateAuditCard(String identifier) {
        iCardHistoryService.saveAuditCard(CardHistoryMapper.buildEntity(iCardService.queryCard(identifier).get()));
    }

    @AfterThrowing(pointcut = "execution(* com.hm.credibanco.service.impl.CardServiceImpl.deleteCard(..)) && args(identifier)", throwing = "exception")
    public void exceptionDeleteCard(String identifier, Exception exception) {
        log.info("delete card failed");
    }

}
