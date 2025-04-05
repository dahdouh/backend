package com.message.routing.input.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.message.routing.application.MessageRoutingApplication;
import com.message.routing.domain.model.BackOfficeMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.web.servlet.MockMvc;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = MessageRoutingApplication.class)
@AutoConfigureMockMvc
class MessageControllerTest {
    public static final String MESSAGE_PATH = "/message";
    @Autowired
    private MockMvc mockMvc;
    @Value("classpath:data/message.xml")
    private Resource messageFile;

    @Test
    void shouldReturnNoMessage() throws Exception {
        mockMvc.perform(get(MESSAGE_PATH))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(result -> assertThat(result.getResponse().getContentLength()).isZero());
    }

    @Test
    void shouldReturnListOfMessages() throws Exception {
        mockMvc.perform(get(MESSAGE_PATH))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(result -> {
                    final String contentAsString = result.getResponse().getContentAsString();
                    assertThat(contentAsString).isNotEmpty();
                    final BackOfficeMessage[] backOfficeMessages = new ObjectMapper().readValue(contentAsString, BackOfficeMessage[].class);
                    assertThat(backOfficeMessages.length).isEqualTo(1);
                    assertThat(backOfficeMessages[0].payload()).isEqualTo(messageFile.getContentAsString(UTF_8));
                });
    }
}