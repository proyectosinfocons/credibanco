package com.hm.credibanco.controller;

import com.hm.credibanco.application.ICardApplication;
import com.hm.credibanco.constants.Route;
import com.hm.credibanco.dto.Response;
import com.hm.credibanco.dto.card.CardRequest;
import com.hm.credibanco.dto.card.CreateCardResponse;
import com.hm.credibanco.dto.card.EnrollCardRequest;
import com.hm.credibanco.dto.card.QueryCardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.security.NoSuchAlgorithmException;

@Validated
@RestController
@RequestMapping(Route.CARD_PATH)
public class CardController {

    @Autowired
    private ICardApplication iCardApplication;

    @PostMapping
    public ResponseEntity<Response<CreateCardResponse>> createCard(
            @Valid @RequestBody CardRequest request) throws NoSuchAlgorithmException {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(iCardApplication.createCard(request));
    }

    @PatchMapping(Route.ENROLL)
    public ResponseEntity<Response<CreateCardResponse>> enrollCard(
            @Valid @RequestBody EnrollCardRequest request) {
        return ResponseEntity.ok(iCardApplication.enrollCard(request));
    }

    @GetMapping(Route.IDENTIFIER)
    public ResponseEntity<Response<QueryCardResponse>> queryCard(
            @PathVariable
            @Pattern(regexp = "[a-zA-Z0-9]{64}", message = "no cumple con el formato") String identifier
    ) {
        return ResponseEntity.ok(iCardApplication.queryCard(identifier));
    }

    @DeleteMapping(Route.IDENTIFIER)
    public ResponseEntity<Response<?>> deleteCard(
            @PathVariable
            @Pattern(regexp = "[a-zA-Z0-9]{64}", message = "no cumple con el formato") String identifier
    ) {
        return ResponseEntity.ok(iCardApplication.deleteCard(identifier));
    }

}
