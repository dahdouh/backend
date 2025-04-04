package com.message.routing.input.adapter;

import com.message.routing.domain.model.Message;
import com.message.routing.domain.port.MessagePublisherPort;
import com.message.routing.input.exception.TechnicalException;
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
    public void publish(final Message message) {
        try {
            jmsTemplate.convertAndSend(queueName, message);
            log.debug(String.format("Message Sent to queue %s, message %s", queueName, message));
        } catch (final JmsException ex) {
            throw new TechnicalException(ex);
        }
    }
}
