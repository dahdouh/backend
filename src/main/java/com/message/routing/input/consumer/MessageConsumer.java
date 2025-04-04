package com.message.routing.input.consumer;

import com.message.routing.domain.model.BackOfficeMessage;
import com.message.routing.input.exception.TechnicalException;
import com.message.routing.output.data.entity.MessageEntity;
import com.message.routing.output.data.service.MessageDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.JmsException;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {
    private static final Logger log = LoggerFactory.getLogger(MessageConsumer.class);
    private final MessageDataService messageDataService;

    public MessageConsumer(final MessageDataService messageDataService) {
        this.messageDataService = messageDataService;
    }

    @JmsListener(destination = "${app.queue.name}")

    public void receiveMessage(final Message message) {
        try {
            final BackOfficeMessage backOfficeMessage = (BackOfficeMessage) message.getPayload();
            messageDataService.save(new MessageEntity().setPayload(backOfficeMessage.payload()));
            log.debug(String.format("Message received %s", message));
        } catch (final JmsException ex) {
            throw new TechnicalException(ex);
        }
    }

}
