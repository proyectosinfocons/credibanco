package com.hm.credibanco.application;

import com.hm.credibanco.dto.Response;
import com.hm.credibanco.dto.tx.ReverseTransactionRequest;
import com.hm.credibanco.dto.tx.TransactionRequest;
import com.hm.credibanco.dto.tx.TransactionResponse;

public interface ITransactionApplication {

    Response<TransactionResponse> createTransaction(TransactionRequest request);

    Response<TransactionResponse> reverseTransaction(ReverseTransactionRequest request);

}
