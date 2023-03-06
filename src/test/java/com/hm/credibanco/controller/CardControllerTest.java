package com.hm.credibanco.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hm.credibanco.application.ICardApplication;
import com.hm.credibanco.utils.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(controllers = CardController.class)
class CardControllerTest {

    @MockBean
    ICardApplication iCardApplication;

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @Test
    void createCard() throws Exception {
        // given
        var request = Data.CARD_REQUEST;

        // when
        mvc.perform(MockMvcRequestBuilders.post("/card")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }

    @Test
    void createCard_BAD_REQUEST() throws Exception {
        // given
        var request = Data.CARD_BAD_REQUEST;

        // when
        mvc.perform(MockMvcRequestBuilders.post("/card")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void enrollCard() throws Exception {
        // given
        var request = Data.ENROLL_CARD_REQUEST_INTEGRATION_TEST;

        // when
        mvc.perform(MockMvcRequestBuilders.patch("/card/enroll")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    void enrollCard_BAD_REQUEST() throws Exception {
        // given
        var request = Data.ENROLL_CARD_BAD_REQUEST_INTEGRATION_TEST;

        // when
        mvc.perform(MockMvcRequestBuilders.patch("/card/enroll")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void queryCard() throws Exception {
        // given
        var identifier = "472689c8f8a29cd40496bc535b75e0d65c7e37337936ba736c2eb537fa7c22c1";

        //when
        mvc.perform(MockMvcRequestBuilders.get("/card/{identifier}", identifier)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void queryCard_BAD_REQUEST() throws Exception {
        // given
        var identifier = "111";

        //when
        mvc.perform(MockMvcRequestBuilders.get("/card/{identifier}", identifier)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteCard() throws Exception {
        // given
        var identifier = "472689c8f8a29cd40496bc535b75e0d65c7e37337936ba736c2eb537fa7c22c1";

        // when
        mvc.perform(MockMvcRequestBuilders.delete("/card/{identifier}", identifier)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deleteCard_BAD_REQUEST() throws Exception {
        // given
        var identifier = "1111";

        // when
        mvc.perform(MockMvcRequestBuilders.delete("/card/{identifier}", identifier)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}