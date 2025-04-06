package com.message.routing.input.rest.mapper;

import com.message.routing.domain.model.BackOfficeMessage;
import com.message.routing.output.data.entity.MessageEntity;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper {
    public BackOfficeMessage toMessage(final MessageEntity messageEntity) {
        return new BackOfficeMessage(messageEntity.getPayload());
    }

    public MessageEntity toMessageEntity(final BackOfficeMessage backOfficeMessage) {
        return new MessageEntity().setPayload(backOfficeMessage.payload());
    }
}
