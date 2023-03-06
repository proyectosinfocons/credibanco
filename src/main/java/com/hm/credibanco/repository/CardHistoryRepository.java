package com.hm.credibanco.repository;

import com.hm.credibanco.entity.CardHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardHistoryRepository extends JpaRepository<CardHistoryEntity, Long> {
}
