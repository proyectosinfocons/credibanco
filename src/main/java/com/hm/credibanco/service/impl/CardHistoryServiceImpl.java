package com.hm.credibanco.service.impl;

import com.hm.credibanco.entity.CardHistoryEntity;
import com.hm.credibanco.repository.CardHistoryRepository;
import com.hm.credibanco.service.ICardHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CardHistoryServiceImpl implements ICardHistoryService {

    @Autowired
    private CardHistoryRepository repository;

    @Override
    public void saveAuditCard(CardHistoryEntity entity) {
        try {
            repository.save(entity);
        } catch (Exception ex) {
            log.error("CardHistoryServiceImpl::saveAuditCard --exMessage [{}] --exCause [{}]", ex.getMessage(), ex.getCause());
        }
    }
}
