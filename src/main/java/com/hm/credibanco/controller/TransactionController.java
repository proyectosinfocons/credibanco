package com.hm.credibanco.controller;

import com.hm.credibanco.application.ITransactionApplication;
import com.hm.credibanco.constants.Route;
import com.hm.credibanco.dto.Response;
import com.hm.credibanco.dto.tx.ReverseTransactionRequest;
import com.hm.credibanco.dto.tx.TransactionRequest;
import com.hm.credibanco.dto.tx.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping(Route.TX_PATH)
public class TransactionController {

    @Autowired
    private ITransactionApplication iTransactionApplication;

    @PostMapping
    public ResponseEntity<Response<TransactionResponse>> createTransaction(
            @Valid @RequestBody TransactionRequest request
            ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(iTransactionApplication.createTransaction(request));
    }

    @PatchMapping(Route.REVERSE_TX)
    public ResponseEntity<Response<TransactionResponse>> reverseTransaction(
            @Valid @RequestBody ReverseTransactionRequest request
            ) {
        return ResponseEntity.ok(iTransactionApplication.reverseTransaction(request));
    }

}
