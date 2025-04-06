package com.message.routing.input.rest.adapter;

import com.message.routing.domain.exception.TechnicalException;
import com.message.routing.domain.model.BackOfficeMessage;
import com.message.routing.domain.port.MessagePublisherPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageMessagePublisherAdapter implements MessagePublisherPort {
    private static final Logger log = LoggerFactory.getLogger(MessageMessagePublisherAdapter.class);

    private final JmsTemplate jmsTemplate;

    private final String queueName;

    public MessageMessagePublisherAdapter(final JmsTemplate jmsTemplate, @Value("${app.queue.name}") final String queueName) {
        this.jmsTemplate = jmsTemplate;
        this.queueName = queueName;
    }

    @Override
    public void publish(final BackOfficeMessage backOfficeMessage) {
        try {
            jmsTemplate.convertAndSend(queueName, backOfficeMessage);
            log.debug("Message Sent to queue {}, message {}", queueName, backOfficeMessage);
        } catch (final JmsException ex) {
            throw new TechnicalException(ex);
        }
    }
}
