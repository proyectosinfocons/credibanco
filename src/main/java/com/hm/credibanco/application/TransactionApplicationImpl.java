package com.hm.credibanco.application;

import com.hm.credibanco.dto.Response;
import com.hm.credibanco.dto.tx.ReverseTransactionRequest;
import com.hm.credibanco.dto.tx.TransactionRequest;
import com.hm.credibanco.dto.tx.TransactionResponse;
import com.hm.credibanco.entity.TransactionEntity;
import com.hm.credibanco.enums.CardStatusEnum;
import com.hm.credibanco.enums.ResponseEnum;
import com.hm.credibanco.enums.TransactionStatusEnum;
import com.hm.credibanco.exceptions.BusinessException;
import com.hm.credibanco.exceptions.DataNotFoundException;
import com.hm.credibanco.mapper.TransationMapper;
import com.hm.credibanco.service.ICardService;
import com.hm.credibanco.service.ITransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class TransactionApplicationImpl implements ITransactionApplication {

    @Autowired
    private ITransactionService iTransactionService;

    @Autowired
    private ICardService iCardService;

    @Override
    public Response<TransactionResponse> createTransaction(TransactionRequest request) {
        var oCard = iCardService.queryCard(request.getIdentifier());
        Optional<TransactionEntity> oTx;
        if (oCard.isEmpty()) {
            log.info("TransactionApplicationImpl::createTransaction --identifier [{}]", request.getIdentifier());
            throw new BusinessException("Card not found");
        }
        if (!Objects.equals(oCard.get().getStatus(), CardStatusEnum.ENROLADA.name())) {
            throw new BusinessException("Card is not enrolled");
        }
        oTx = iTransactionService.queryTransaction(request.getIdentifier(), request.getReferenceNumber());
        if (oTx.isPresent()) {
            log.info("TransactionApplicationImpl::createTransaction --identifier [{}] --referenceNumber [{}]",
                    request.getIdentifier(),
                    request.getReferenceNumber());
            throw new BusinessException("Tx already exists");
        }
        return new Response<>(
                ResponseEnum.SUCCESS.getCode(),
                ResponseEnum.SUCCESS.getMsg(),
                TransationMapper.createEntityToDto(iTransactionService.saveTransaction(TransationMapper.dtoToEntity(request)))
        );
    }

    @Override
    public Response<TransactionResponse> reverseTransaction(ReverseTransactionRequest request) {
        var oTx = iTransactionService.queryTransaction(request.getIdentifier(), request.getReferenceNumber());
        if (oTx.isEmpty()) {
            log.info("TransactionApplicationImpl::reverseTransaction data not found --identifier [{}] --referenceNumber [{}]",
                    request.getIdentifier(),
                    request.getReferenceNumber());
            throw new DataNotFoundException(request.getIdentifier());
        }
        if (Objects.equals(oTx.get().getStatus(), TransactionStatusEnum.CANCELED.name())) {
            log.info("TransactionApplicationImpl::reverseTransaction Tx already canceled --identifier [{}] --referenceNumber [{}]",
                    request.getIdentifier(),
                    request.getReferenceNumber());
            throw new BusinessException("Tx already canceled");
        }
        if (oTx.get().getCreatedAt().plusMinutes(5).isBefore(LocalDateTime.now())) {
            log.info("TransactionApplicationImpl::reverseTransaction Cannot cancel transaction --identifier [{}] --referenceNumber [{}]",
                    request.getIdentifier(),
                    request.getReferenceNumber());
            throw new BusinessException("Cannot cancel transaction");
        }

        oTx.get().setStatus(TransactionStatusEnum.CANCELED.name());
        return new Response<>(
                ResponseEnum.SUCCESS.getCode(),
                ResponseEnum.SUCCESS.getMsg(),
                TransationMapper.reverseEntityToDto(iTransactionService.saveTransaction(oTx.get()))
        );
    }
}
