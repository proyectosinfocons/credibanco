package com.hm.credibanco.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hm.credibanco.application.ITransactionApplication;
import com.hm.credibanco.utils.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(controllers = TransactionController.class)
class TransactionControllerTest {

    @MockBean
    @Qualifier("transactionApplicationImpl")
    ITransactionApplication iTransactionApplication;

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @Test
    void createTransaction() throws Exception {
        // given
        var request = Data.TRANSACTION_REQUEST;
        when(iTransactionApplication.createTransaction(request))
                .thenReturn(Data.TX_CREATED);

        // when
        mvc.perform(MockMvcRequestBuilders.post("/transaction")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }

    @Test
    void createTransaction_BAD_REQUEST() throws Exception {
        // given
        var request = Data.TRANSACTION_REQUEST_BAD_IDENTIFIER;
        when(iTransactionApplication.createTransaction(request))
                .thenReturn(Data.TX_CREATED);

        // when
        mvc.perform(MockMvcRequestBuilders.post("/transaction")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void reverseTransaction() throws Exception {
        // given
        var request = Data.REVERSE_TRANSACTION_REQUEST;

        // when
        mvc.perform(MockMvcRequestBuilders.patch("/transaction/reverse")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isOk());

    }

    @Test
    void reverseTransaction_BAD_REQUEST() throws Exception {
        // given
        var request = Data.REVERSE_TRANSACTION_BAD_REQUEST;

        // when
        mvc.perform(MockMvcRequestBuilders.patch("/transaction/reverse")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());

    }
}