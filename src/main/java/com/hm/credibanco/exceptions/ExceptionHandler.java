package com.hm.credibanco.exceptions;

import com.hm.credibanco.constants.Constants;
import com.hm.credibanco.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ErrorResponse> exceptionHandler(ConstraintViolationException ex) {
        return ResponseEntity.badRequest().body(new ErrorResponse(ex.getMessage()));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ErrorResponse> exceptionHandler(MethodArgumentNotValidException ex) {
        List<String> errors =
                ex.getBindingResult()
                        .getFieldErrors().stream()
                        .map(e -> e.getField().concat(Constants.BEFORE_ERROR_DESC_MSG)
                                .concat(Objects.requireNonNull(e.getDefaultMessage())))
                        .collect(Collectors.toList());
        return ResponseEntity.badRequest().body(new ErrorResponse(String.join(Constants.SEPARATOR_MSG, errors)));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ErrorResponse> exceptionHandler(DatabaseException ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(ex.getMessage()));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ErrorResponse> exceptionHandler(DataNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(HttpStatus.NOT_FOUND.getReasonPhrase()
                        .concat(Constants.BEFORE_ERROR_DESC_MSG)
                        .concat(ex.getMessage())));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ErrorResponse> exceptionHandler(BusinessException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ErrorResponse(ex.getMessage()));
    }

}
