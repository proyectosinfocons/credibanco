package com.hm.credibanco.application;

import com.hm.credibanco.dto.Response;
import com.hm.credibanco.dto.card.CardRequest;
import com.hm.credibanco.dto.card.CreateCardResponse;
import com.hm.credibanco.dto.card.EnrollCardRequest;
import com.hm.credibanco.dto.card.QueryCardResponse;

import java.security.NoSuchAlgorithmException;

public interface ICardApplication {

    Response<CreateCardResponse> createCard(CardRequest request) throws NoSuchAlgorithmException;

    Response<CreateCardResponse> enrollCard(EnrollCardRequest request);

    Response<QueryCardResponse> queryCard(String identifier);

    Response<?> deleteCard(String identifier);

}
