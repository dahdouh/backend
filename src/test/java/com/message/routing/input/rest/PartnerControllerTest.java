package com.message.routing.input.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.message.routing.application.MessageRoutingApplication;
import com.message.routing.domain.model.Partner;
import com.message.routing.input.rest.factory.PartnerFactory;
import com.message.routing.output.data.entity.PartnerEntity;
import com.message.routing.output.data.service.PartnerService;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = MessageRoutingApplication.class)
@AutoConfigureMockMvc
class PartnerControllerTest {
    public static final String PARTNER_PATH = "/partner";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PartnerService partnerService;

    @Test
    @Order(1)
    void shouldCreatePartner() throws Exception {
        //given
        final Partner partner = new PartnerFactory().build();
        //when && then
        mockMvc.perform(post(PARTNER_PATH)
                        .content(asJsonString(partner))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().string(""));
    }

    @Test
    @Order(2)
    void shouldReturnNoPartner() throws Exception {
        mockMvc.perform(get(PARTNER_PATH))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(result -> assertThat(result.getResponse().getContentLength()).isZero());
    }

    @Test
    @Order(3)
    void shouldReturnListOfPartners() throws Exception {
        //given
        final PartnerEntity partnerEntity = partnerService.findAll().getFirst();
        //when && then
        mockMvc.perform(get(PARTNER_PATH))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    final String contentAsString = result.getResponse().getContentAsString();
                    assertThat(contentAsString).isNotEmpty();
                    final Partner[] partners = new ObjectMapper().readValue(contentAsString, Partner[].class);
                    assertThat(partners[0]).usingRecursiveComparison().isEqualTo(partnerEntity);
                });
    }

    @Test
    @Order(4)
    void shouldDeletePartner() throws Exception {
        //given && when && then
        mockMvc.perform(delete(PARTNER_PATH.concat("/1"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andExpect(content().string(""));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

}