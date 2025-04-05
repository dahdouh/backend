package com.message.routing.input.consumer;

import com.message.routing.application.MessageRoutingApplication;
import com.message.routing.domain.model.BackOfficeMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.jms.core.JmsTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = MessageRoutingApplication.class)
@ExtendWith(OutputCaptureExtension.class)
class MessageConsumerTest {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${app.queue.name}")
    private String queueName;

    @Test
    void shouldConsumeMessage(final CapturedOutput output) throws InterruptedException {
        //given
        final BackOfficeMessage backOfficeMessage = new BackOfficeMessage("payload");
        //when
        jmsTemplate.convertAndSend(queueName, backOfficeMessage);
        Thread.sleep(1500);
        // then
        assertThat(output).contains("Message received");
    }


}