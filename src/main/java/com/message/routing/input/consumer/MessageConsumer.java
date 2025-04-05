package com.message.routing.input.consumer;

import com.message.routing.domain.model.BackOfficeMessage;
import com.message.routing.input.exception.TechnicalException;
import com.message.routing.output.data.entity.MessageEntity;
import com.message.routing.output.data.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {
    private static final Logger log = LoggerFactory.getLogger(MessageConsumer.class);
    private final MessageService messageService;

    public MessageConsumer(final MessageService messageService) {
        this.messageService = messageService;
    }

    @JmsListener(destination = "${app.queue.name}")
    public void receiveMessage(final Message message) {
        try {
            final BackOfficeMessage backOfficeMessage = (BackOfficeMessage) message.getPayload();
            messageService.save(new MessageEntity().setPayload(backOfficeMessage.payload()));
            log.info(String.format("Message received %s", message));
        } catch (final Exception ex) {
            throw new TechnicalException(ex);
        }
    }

}
