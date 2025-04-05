package com.message.routing.application;

import com.message.routing.input.rest.MessageController;
import com.message.routing.input.rest.PartnerController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BackOfficeMessageEntityRoutingApplicationTest {

    @Autowired
    private MessageController messageController;
    @Autowired
    private PartnerController partnerController;

    @Test
    void contextLoads() {
        Assertions.assertThat(messageController).isNotNull();
        Assertions.assertThat(partnerController).isNotNull();
    }

}