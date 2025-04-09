package com.message.routing.input.consumer;

import com.message.routing.domain.exception.TechnicalException;
import com.message.routing.domain.model.BackOfficeMessage;
import com.message.routing.output.data.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {
    private static final Logger log = LoggerFactory.getLogger(MessageConsumer.class);
    public static final String JMSX_DELIVERY_COUNT = "JMSXDeliveryCount";
    private final MessageService messageService;
    final int jmsXDeliveryCount;

    public MessageConsumer(final MessageService messageService,
                           @Value("${app.message.jmsXDeliveryCount}") final int jmsXDeliveryCount) {
        this.messageService = messageService;
        this.jmsXDeliveryCount = jmsXDeliveryCount;
    }

    @JmsListener(destination = "${app.queue.name}")
    public void receiveMessage(final Message message) {
        if (!isMaxDelivery(message)) {
            process(message);
        } else {
            //TODO: send the message to Dead-Letter queue
        }
    }

    private void process(final Message message) {
        try {
            final BackOfficeMessage backOfficeMessage = (BackOfficeMessage) message.getPayload();
            messageService.save(backOfficeMessage);
            log.info("Message received {}", message);
        } catch (final Exception ex) {
            throw new TechnicalException(ex);
        }
    }

    private boolean isMaxDelivery(final Message message) {
        final MessageHeaders headers = message.getHeaders();
        return headers.containsKey(JMSX_DELIVERY_COUNT) && (int) headers.get(JMSX_DELIVERY_COUNT) > jmsXDeliveryCount;
    }

}
